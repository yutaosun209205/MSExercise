#!/usr/bin/python
# -*- coding:utf-8 -*-


# 参数默认值 z = 10, z 默认值放在最后
def test1(x, y, z=10):
    print(x, y, z)
test1(1, 2, 20)   # 1 2 20
test1(1, 2)       # 1 2 10


# 变长参数, 参数无名, 包含在元组中
def test2(x, y, *args):
    print(x, y, args)
test2(1, 2)  # 1 2 ()
test2(1, 2, 3, 4)  # 1 2 (3, 4)


# 变长参数，参数有名， 参数包含在字典中
def test3(x, y, *args, **kwargs):
    print(x, y, args, kwargs)
test3(1, 2)                            # 1 2 () {}
test3(1, 2, 3, 4, 5)                   # 1 2 (3, 4, 5) {}
test3(1, 2, 3, 4, 5, arg1=6, arg2=7)   # 1 2 (3, 4, 5) {'arg1': 6, 'arg2': 7}


# 集合拆包， 把集合中的每个元素作为参数传入函数
args = [3, 4, 5]
kwargs = {"arg1": 6, "arg2": 7}
test3(1, 2, *args, **kwargs)            # 1 2 (3, 4, 5) {'arg1': 6, 'arg2': 7}

