package com.sun.spark.streaming

import java.io.{File, PrintWriter}
import java.net.ServerSocket
import java.util.concurrent.Executors

import scala.io.Source

/**
  * Created by bjsunyutao on 2018/3/23.
  * Spark Streaming Socket数据流模拟器
  */
object StreamingSimulation {
  def main(args: Array[String]): Unit = {
    //输入参数： 文件名  端口号  毫秒
    if(args.length != 3){
      System.err.println("Usage: <filename> <port> <millisecond>")
      System.exit(1)
    }

    val path = new File(".").getAbsolutePath + "/" + args(0)
    val lines = Source.fromFile(path).getLines().toList
    val fileRows = lines.length
    //创建socket 服务端
    val socketServer = new ServerSocket(args(1).toInt)
    val threadPool = Executors.newFixedThreadPool(10)
    while (true){
      val socket = socketServer.accept()
      val runnable = new Runnable {
        override def run(): Unit = {
          println("Get Client Connected From: " + socket.getInetAddress)
          val out = new PrintWriter(socket.getOutputStream(),true)
          while (true){
            Thread.sleep(args(2).toLong)
            // 当该端口接受请求时，随机获取某行数据发送给对方
            val content = lines(scala.util.Random.nextInt(fileRows))
            println("-------------------------------------------")
            println(s"Time: ${System.currentTimeMillis()}")
            println("-------------------------------------------")
            println(content)
            println()
            out.write(content + '\n')
            out.flush()
          }
          socket.close()
        }
      }
      threadPool.submit(runnable)
    }

  }
}
