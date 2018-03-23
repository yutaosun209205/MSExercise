package com.sun.socket

import java.io.{OutputStream, PrintWriter}
import java.net.Socket
import java.util.Scanner


/**
  * Created by bjsunyutao on 2018/3/23.
  */
object SocketClient {
  def main(args: Array[String]): Unit = {
    var socketClient: Socket = null
    var outputStream: OutputStream = null
    var printWriter : PrintWriter = null
    while (true) {
      try {
        println("输入：")
        val scanner = new Scanner(System.in)
        val str = scanner.nextLine()
        socketClient = new Socket("127.0.0.1", 9999)
        outputStream = socketClient.getOutputStream
        printWriter = new PrintWriter(outputStream)
        printWriter.write(str)
        printWriter.flush()
        printWriter.close()
        socketClient.close()
      } catch {
        case e: Exception => println(e)
      } finally {
        outputStream.close()
        socketClient.close()
      }
    }
  }
}