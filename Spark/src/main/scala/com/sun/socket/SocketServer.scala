package com.sun.socket

import java.io.PrintWriter
import java.net.ServerSocket
import java.util.concurrent.Executors

/**
  * Created by bjsunyutao on 2018/3/23.
  */
object SocketServer {
  def main(args: Array[String]): Unit = {
    val port = 9999
    val serverSocket = new ServerSocket(port)

    val threadPool = Executors.newFixedThreadPool(10)
    while(true){
      //接收客户端请求，创建socket连接
      val socket = serverSocket.accept()
      //启动线程处理连接
      val runnable = new Runnable {
        override def run(): Unit = {
          try {
            println("开始处理：")
            val inputStream = socket.getInputStream
            val byte = new Array[Byte](1)
            val in = new StringBuilder()
            var len = inputStream.read(byte)
            while (len != -1){
              in.append(new String(byte))
              len = inputStream.read(byte)
            }
            println("accept:" + in)
            val outputStream = socket.getOutputStream
            val printWriter = new PrintWriter(outputStream)


            printWriter.write(in + "\n")
            printWriter.flush()

            printWriter.close()

            inputStream.close()

          }catch {
            case e: Exception => println(e)
          }finally {
            socket.close()
          }
        }
      }
      threadPool.submit(runnable)
    }
  }
}
