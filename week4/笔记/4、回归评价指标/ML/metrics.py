import numpy as np

def accuracy_score(y, y_predict):
    '''计算预测准确度'''
    assert y.shape[0] == y_predict.shape[0], "y与y_predict的长度需要相同"

    return sum(y == y_predict) / len(y)

# MSE
def mean_squared_error(y_true, y_predict):
    assert len(y_true) == len(y_predict), 'y_true 与 y_predict 长度需要相同'
    return sum((y_true - y_predict) ** 2) / len(y_true)

# RMSE
def root_mean_squared_error(y_true, y_predict):
    return mean_squared_error(y_true, y_predict) ** (1/2)

# MAE
def mean_absolute_error(y_true, y_predict):
    assert len(y_true) == len(y_predict), 'y_true 与 y_predict 长度需要相同'
    return sum(np.absolute(y_true - y_predict)) / len(y_true)

# R Squared
def r2_score(y_true, y_predict):
    return 1 - mean_squared_error(y_true, y_predict) / np.var(y_true)
