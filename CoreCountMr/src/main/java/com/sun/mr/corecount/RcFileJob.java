package com.sun.mr.corecount;

import com.sun.mr.corecount.map.RCFileMapper;
import com.sun.mr.corecount.reduce.TextReduce;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * Created by SunYuTao on 16/8/26.
 */
public class RcFileJob {
    public static void main(String[] args) throws  Exception{
        Configuration configuration = new Configuration() ;
        Job job = Job.getInstance(configuration) ;

        job.setJarByClass(RcFileJob.class);

        job.setMapperClass(RCFileMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);
        FileInputFormat.setInputPaths(job,new Path(args[0]));

        job.setReducerClass(TextReduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        job.waitForCompletion(true) ;
    }
}
