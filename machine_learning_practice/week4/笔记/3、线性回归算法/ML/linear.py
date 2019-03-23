import numpy as np

class LinearRegression():
    def __init__(self):
        self.a_ = None
        self.b_ = None

    def fit(self, x_train, y_train):
        assert len(x_train) == len(y_train), 'x_train 必须与 y_train 的长度相同'
        assert x_train.ndim == 1, '只支持一个特征'

        x_mean = np.mean(x_train)
        y_mean = np.mean(y_train)

        u = 0  # 分子
        v = 0  # 分母
        for x_i, y_i in zip(x_train, y_train):
            u += (x_i - x_mean) * (y_i - y_mean)
            v += (x_i - x_mean) ** 2

        self.a_ = u / v
        self.b_ = y_mean - self.a_ * x_mean

        return self

    def predict(self, predicts):
        assert self.a_ is not None, '请先调用fit()方法'
        assert predicts.ndim == 1, '只支持一个特征'

        return np.array([self.a_ * predict + self.b_ for predict  in predicts])