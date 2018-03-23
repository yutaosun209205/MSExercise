#!/usr/bin/python
# -*- coding:utf-8 -*-


class Person:

    # 对象初始化,并非构造方法，  __new__()是构造方法
    def __init__(self):
        print("Object Init")


person = Person()


class Car:
    def __init__(self, name, color):
        self.color = color
        self.name = name

    def tostring(self):
        print("name:%s, color: %s" % (self.name, self.color))

car = Car("Au", "Red")
car.tostring()


class Cat:

    def __init__(self):
        print("Hello")

    def __new__(cls, *args, **kwargs):
        return object.__new__(cls)

cat = Cat()

