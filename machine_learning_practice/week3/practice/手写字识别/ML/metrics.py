def accuracy_score(y, y_predict):
    '''计算预测准确度'''
    assert y.shape[0] == y_predict.shape[0], "y与y_predict的长度需要相同"

    return sum(y == y_predict) / len(y)