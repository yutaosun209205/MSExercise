#!/usr/bin/python
# -*- coding:utf-8 -*-

import  MySQLdb
import  sys
import os

conn = MySQLdb.connect(host='10.164.169.61', port=3306, user='adhadoop', passwd='adhadoop', db='adcpmtest')
cursor = conn.cursor()

dir = os.path.split(os.path.realpath(__file__))[0]
lines = sys.stdin.readlines()

sql = 'insert into monitor_data_statistic_count (date, interval_type, interval_value, data_type, tag, host, stat_name, value) VALUES (%s, %s, %s, %s, %s, %s, %s, %s)'
param = []
for line in lines:
    columns = line.strip("\n").split("\t")
    date = columns[0]
    interval_type = 0
    interval_value = str(date).replace("-", "")
    data_type = int(columns[1])
    tag = 0
    host = "-"
    stat_name = columns[2]
    value = long(columns[3])

    tuple_values = (date, interval_type, interval_value, data_type, tag, host, stat_name, value)
    param.append(tuple_values)

print param
cursor.executemany(sql,param)
conn.commit()
conn.close()



