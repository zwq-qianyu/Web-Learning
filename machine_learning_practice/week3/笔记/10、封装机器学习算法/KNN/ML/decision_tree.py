import numpy as np
from collections import Counter
from .metrics import accuracy_score

def gini(y):
    '''求基尼系数'''
    counter = Counter(y)
    result = 0
    for v in counter.values():
        result += (v / len(y)) ** 2
    return 1 - result

def cut(X, y, d, v):
    '''根据value值分成两段'''
    left_index = (X[:, d] <= v)
    right_index = (X[:, d] > v)
    return X[left_index], X[right_index], y[left_index], y[right_index]

def try_split(X, y):
    '''对数据集进行 训练数据 与 测试数据 的拆分'''
    best_g = 1
    best_d = -1
    best_v = -1

    for d in range(X.shape[1]):
        sorted_index = np.argsort(X[:, d])
        for i in range(len(X) - 1):
            if X[sorted_index[i], d] == X[sorted_index[i + 1], d]:
                continue

            v = (X[sorted_index[i], d] + X[sorted_index[i + 1], d]) / 2
            # print('d={}, v={}'.format(d, v))
            X_left, X_right, y_left, y_right = cut(X, y, d, v)

            g_all = gini(y_left) + gini(y_right)

            # print('d={}, v={}, g={}'.format(d, v, g_all))

            if g_all < best_g:
                best_g = g_all
                best_d = d
                best_v = v
    return best_d, best_v, best_g

def create_tree(X, y):
    '''创建决策树'''
    d, v, g = try_split(X, y)
    if d == -1 or g == 0:  # 不能再进行划分时
        return None

    node = Node(d, v, g)

    X_left, X_right, y_left, y_right = cut(X, y, d, v)

    node.children_left = create_tree(X_left, y_left)
    if node.children_left is None:
        label = Counter(y_left).most_common(1)[0][0]
        node.children_left = Node(l=label)

    node.children_right = create_tree(X_right, y_right)
    if node.children_right is None:
        label = Counter(y_right).most_common(1)[0][0]
        node.children_right = Node(l=label)

    return node

def _predict(x, node):
    '''对一组数据进行决策树分类预测'''
    if node.label is not None:
        return node.label

    if x[node.dim] < node.value:
        # left
        return _predict(x, node.children_left)
    else:
        # right
        return _predict(x, node.children_right)

def _show_tree(node):
    if node is None:
        return ''

    result = '{} [label="{}"]\n'.format(id(node), node)

    if node.children_left is not None:
        result += '{} [label="{}"]\n'.format(id(node.children_left), node.children_left)
        result += '"{}" -> "{}" \n'.format(id(node), id(node.children_left))
        result += _show_tree(node.children_left)

    if node.children_right is not None:
        result += '{} [label="{}"]\n'.format(id(node.children_right), node.children_right)
        result += '"{}" -> "{}" \n'.format(id(node), id(node.children_right))
        result += _show_tree(node.children_right)

    return result

class DecisionTreeClassifier():
    '''决策树分类器'''
    def __int__(self):
        '''初始化'''
        self.tree_ = None

    def fit(self, X, y):
        '''训练数据得到模型'''
        self.tree_ = create_tree(X, y)
        return self.tree_

    def predict(self, X):
        '''利用测试样本进行预测（多组数据）'''
        assert self.tree_ is not None, "请先调用 fit 方法"

        return np.array([_predict(x, self.tree_) for x in X])

    def accuracy_rate(self, X_test, y_test):
        '''计算预测的准确率'''
        y_predict = self.predict(X_test)
        return accuracy_score(y_test, y_predict)

    def show_tree(self):
        '''输出决策树树状结构文本文件（tree.dot），之后调用 graphviz 将决策树进行可视化话'''
        with open('tree.dot', 'w') as f:
            f.write('digraph {' + _show_tree(self.tree_) + '}')



class Node():
    def __init__(self, d=None, v=None, g=None, l=None):
        self.dim = d
        self.value = v
        self.gini = g
        self.label = l

        self.children_left = None
        self.children_right = None

    def __repr__(self):
        return "d={}, v={}, g={}, l={}".format(self.dim, self.value, self.gini, self.label)