import numpy as np

def train_test_split(x, y, test_size = 0.25, seed=None):
    '''拆分训练集和测试集'''

    assert x.shape[0] == y.shape[0], "x中样本数量需要等于y中标签数量"
    assert 0 <= test_size <= 1, "test_size有效范围为0到1之间"

    if seed:
        np.random.seed(seed)

    shuffle = np.random.permutation(len(x))

    size = int(len(x) * test_size)

    test_index = shuffle[:size]
    train_index = shuffle[size:]

    x_train = x[train_index]
    y_train = y[train_index]
    x_test = x[test_index]
    y_test = y[test_index]

    return x_train, y_train, x_test, y_test
