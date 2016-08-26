package com.sun.mr.corecount.map;

import org.apache.hadoop.hive.serde2.columnar.BytesRefArrayWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by SunYuTao on 16/8/26.
 */
public class RCFileMapper extends Mapper<LongWritable,BytesRefArrayWritable,Text,LongWritable> {

    @Override
    protected void map(LongWritable key, BytesRefArrayWritable value, Context context) throws IOException, InterruptedException {
        Text text = new Text();
        text.set(value.get(0).getData(),value.get(0).getStart(),value.get(0).getLength());
        String line = text.toString();
        String[] fields = line.split("\t");
        String playlistid = fields[34];
        String vv = fields[37] ;
        context.write(new Text(playlistid),new LongWritable(Integer.parseInt(vv)));
    }

}
