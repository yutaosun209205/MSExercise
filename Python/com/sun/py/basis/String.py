#!/usr/bin/python
# -*- coding:utf-8 -*-
import keyword

'''Python保留关键字'''
kws = keyword.kwlist
print(kws)

name = "Kylin"
age = 25
print("My name is %s, %d years old" % (name, age))

'''输出四位数'''
money = 2
'''空格填充'''
print("I have %4d$" % money)

'''数字0填充'''
print("I have %04d$" % money)

'''保留两位小数，加小数点总共为5位，不足的用数字0填充'''
print("I have %05.02f$" % money)

# 输出百分号
persent = 20
print("Win percent: %d%%" % persent)

# 只在python2 中有 raw_input 方法
# in1 = raw_input("Please input your name:")
# input 方法在 Python2 和python3中都有，python2只接受表达式，python接受字符串，
# 在python2中若要接收字符串，用raw_input

# in1 = input("Please input your name: ")
# print(in1)

name = "abcdefghjk"
print(name[:])
print(name[::2])  # 步长为2

dic = {}
dic.values()
dic.items()

# 列表推导式
a = [i for i in range(0, 10)]
print(a)     # [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

# 平方
b = [i ** 2 for i in range(0, 10)]
print(b)      # [0, 1, 4, 9, 16, 25, 36, 49, 64, 81]


# 列表推导式 嵌套循环
# for x in range(1, 3):
#     for y in range(0, 2):
#         print(x)
c = [x for x in range(1, 3) for y in range(0, 2)]
print(c)       # [1, 1, 2, 2]

# 列表推到 元组
d = [(x, y) for x in range(1, 3) for y in range(0, 2)]
print(d)   # [(1, 0), (1, 1), (2, 0), (2, 1)]
