#!/usr/bin/python
# -*- coding:utf-8 -*-

import sys
import os
import datetime
import time
import json
# import commands   # python3.x 中commands废弃掉，subprocess替代
import subprocess


def is_valid_date(date, fmt):
    try:
        time.strptime(date, fmt)
        return True
    except:
        return False

if __name__ == '__main__':
    dirpath = os.getcwd()
    os.system("chmod 600 " + dirpath + "/rsync.pass")
    args = len(sys.argv[1:])
    if args == 0:
        dt_h = (datetime.datetime.now() + datetime.timedelta(hours=-1)).strftime("%Y%m%d %H")
        dt = dt_h.split(" ")[0]
        h = dt_h.split(" ")[1]
    elif args == 1:
        print ("Arguments Error: dt<%Y-%m-%d> h<%H>")
        exit(1)
    elif args == 2:
        dt = str(sys.argv[1]).replace("-", "")
        h = sys.argv[2]
        dt_h = dt + " " + h
        if not is_valid_date(dt_h, "%Y%m%d %H"):
            print ("Date argument Error: fmt: %Y%m%d %H")
            exit(1)

    y, m, d, h = time.strptime(dt_h, "%Y%m%d %H")[0:4]
    dt_h_1hour_after = (datetime.datetime(y, m, d, h) + datetime.timedelta(hours=1)).strftime("%Y%m%d %H")
    dt_1 = dt_h_1hour_after.split(" ")[0]
    h_1 = dt_h_1hour_after.split(" ")[1]
    print (sys.argv[0], dt, h)

    hosts_obj = open(dirpath + "/fileinfo.conf.json")
    try:
        conf = json.load(hosts_obj)
    finally:
        hosts_obj.close()

    logtype = conf["logType"]
    business = conf["business"]
    hosts = conf["hosts"]
    hdfsPaths = conf["path"]

    deffrentFiles = {}
    engineLogFiles = {}
    isDeffrent = True
    while isDeffrent:
        for log in logtype:
            logfiles = ""
            for host in hosts:
                cmd = "timeout 30s rsync --port 12873 --password-file=" + dirpath + "/rsync.pass --include=*" + str(dt) + str(h) + "*.log --include=*" + str(dt_1) + str(h_1) + "00*.log --exclude=* test@" + host + "::" + business + "/" + log + "/ | awk -F ' ' '$5 !=\".\"{print $5}' | sed 's/^/"+ host +"./g;s/$/.lzo/g' "
                # print  cmd
                return_code, files = subprocess.getstatusoutput(cmd)
                logfiles = logfiles + "\n" + files
            # print logfiles.strip("\n")
            engine_files = logfiles.strip("\n").split("\n")
            engineLogFiles[log] = engine_files
            if not engine_files:
                print ("EngineLog Warnning: " + log + " log files does not exists")
            else:
                hdfsLogPath1 = hdfsPaths + "/" + business + "/" + log + "/" + str(dt) + "/" + str(h) + "/*.log.lzo"
                hdfsLogPath2 = hdfsPaths + "/" + business + "/" + log + "/" + str(dt_1) + "/" + str(h_1) + "/*" + str(dt_1) + str(h_1) + "[0-1]0*.log.lzo"
                cmd = "timeout 30s hadoop fs -ls -h " + hdfsLogPath1 + " " + hdfsLogPath2 + " | awk -F '/' '{print $NF}'"
                return_code, files = subprocess.getstatusoutput(cmd)
                hdfsfiles = files.strip("\n").split("\n")
                fileNotRsync = []
                for enginefile in engine_files:
                    if enginefile in hdfsfiles:
                        continue
                    else:
                        fileNotRsync.append(enginefile)
                if fileNotRsync:
                    deffrentFiles[log] = fileNotRsync
                else:
                    if log in deffrentFiles:
                        del deffrentFiles[log]
        print (deffrentFiles)
        if deffrentFiles:
            time.sleep(120)
        else:
            isDeffrent = False




