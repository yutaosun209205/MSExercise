package com.sun.mr.corecount.map;

import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by SunYuTao on 16/8/26.
 */
public class RCFileMapper extends Mapper {

    @Override
    protected void map(Object key, Object value, Context context) throws IOException, InterruptedException {
        super.map(key, value, context);
    }
    
}
