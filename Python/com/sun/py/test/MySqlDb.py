#!/usr/bin/python
# -*- coding:utf-8 -*-

import  MySQLdb
import os

conn = MySQLdb.connect(host='localhost', port=3306, user='root', passwd='root', db='addata')
cursor = conn.cursor()

dir = os.path.split(os.path.realpath(__file__))[0]

data = open(dir + "/monitor_data_statistic_count.txt")
try:
    rows = data.readlines()
except:
    print "IOException"

sql = 'insert into monitor_data_statistic_count (date, interval_type, interval_value, data_type, tag, host, stat_name, value) VALUES (%s, %s, %s, %s, %s, %s, %s, %s)'
param = []
for line in rows:
    columns = line.strip("\n").split("\t")
    date = columns[0]
    interval_type = int(columns[1])
    interval_value = columns[2]
    data_type = int(columns[3])
    tag = int(columns[4])
    host = columns[5]
    stat_name = columns[6]
    value = long(columns[7])

    tuple_values = (date, interval_type, interval_value, data_type, tag, host, stat_name, value)
    param.append(tuple_values)
    # sql = "insert into monitor_data_statistic_count (date, interval_type, interval_value, data_type, tag, host, stat_name, value)" \
          # " values ('" + date + "'," + interval_type + ", '" + interval_value + "', " + data_type + "," + tag + ", '" + host + "','" + stat_name + "'," + value + ")"


print param
cursor.executemany(sql,param)
conn.commit()
conn.close()

