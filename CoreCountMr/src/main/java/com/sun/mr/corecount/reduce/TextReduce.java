package com.sun.mr.corecount.reduce;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;


/**
 * Created by SunYuTao on 16/8/26.
 */
public class TextReduce extends Reducer<Text,LongWritable,Text,LongWritable> {
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        long count = 0 ;
        for (LongWritable longWritable : values){
            count += longWritable.get();
        }
        context.write(key,new LongWritable(count));
    }
}
