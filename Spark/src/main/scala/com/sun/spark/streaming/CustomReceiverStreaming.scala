package com.sun.spark.streaming

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.receiver.CustomReceiver
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Created by bjsunyutao on 2018/3/23.
  * 使用自定义接收器的 Spark Streaming 应用程序
  */
object CustomReceiverStreaming {
  def main(args: Array[String]): Unit = {
    if (args.length < 2) {
      System.err.println("Usage: CustomReceiver <hostname> <port>")
      System.exit(1)
    }
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    val conf = new SparkConf().setAppName("CustomReceiver")
    val ssc = new StreamingContext(conf,Seconds(1))
    val lines = ssc.receiverStream(new CustomReceiver(args(0), args(1).toInt))
    val words = lines.flatMap{f=> f.split(" ")}.map{f=> (f,1)}.reduceByKey(_ + _)
    words.print()
    ssc.start()
    ssc.awaitTermination()

  }
}
