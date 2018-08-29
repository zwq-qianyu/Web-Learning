import numpy as np
from collections import Counter

def kNN_classify(x_train, y_train, X_predict, k=5, p=2):
    '''KNN分类器'''

    # 断言
    assert k > 0, "k需要大于0"
    assert k < x_train.shape[0], "k需要小于等于总分样本数"
    assert p > 0, "p需要大于0"
    assert x_train.shape[0] == y_train.shape[0], "x_train 中样本数量需要与 y_train 中样本数量相同"
    assert x_train.shape[1] == X_predict.shape[1], "预测特征数量需要与样本的特征数量相同"

    return np.array([_predict(x_train, y_train, x, k, p) for x in X_predict])

def _predict(x_train, y_train, x, k, p):
    distances = [distance(item, x, p=p) for item in x_train]
    nearest = np.argsort(distances)[:k]
    k_labels = y_train[nearest]

    return Counter(k_labels).most_common(1)[0][0]

def distance(a, b, p):
    '''计算距离'''
    return np.sum(np.abs(a - b) ** p) ** (1/p)
