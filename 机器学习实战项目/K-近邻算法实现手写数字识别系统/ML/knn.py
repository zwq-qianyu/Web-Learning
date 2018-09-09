import numpy as np
from collections import Counter
from .metrics import accuracy_score

def distance(a, b, p):
    '''计算距离   明可夫斯基距离'''
    return np.sum(np.abs(a - b) ** p) ** (1/p)

class KNeighborsClassifier():
    '''K近邻算法封装类'''
    def __init__(self, k=5, p=2):
        '''初始化'''
        assert k > 0, "k需要大于0"
        assert p > 0, "p需要大于0"

        self.k = k
        self.p = p

        self._x_train = None
        self._y_train = None

    def fit(self, x_train, y_train):
        '''训练模型'''
        assert self.k <= x_train.shape[0], "k需要小于等于总分样本数"
        assert x_train.shape[0] == y_train.shape[0], "x_train 中样本数量需要与 y_train 中样本数量相同"

        self._x_train = x_train
        self._y_train = y_train

        return self

    def predict(self, X_predict, k, p):
        '''预测模型'''
        assert self._x_train.shape[1] == X_predict.shape[1], "预测特征数量需要与样本的特征数量相同"

        return np.array([self._predict(x, k, p) for x in X_predict])

    def accuracy_rate(self, X_test, y_test, k, p):
        '''计算准确率'''
        y_predict = self.predict(X_test, k, p)
        return accuracy_score(y_test, y_predict)

    def _predict(self, x, k, p):
        '''预测一组数据'''
        distances = [distance(item, x, p) for item in self._x_train]
        nearest = np.argsort(distances)[:k]
        k_labels = self._y_train[nearest]

        return Counter(k_labels).most_common(1)[0][0]
