package com.sun.vertx.tcp

import io.vertx.core.Handler
import io.vertx.core.buffer.Buffer
import io.vertx.scala.core.Vertx
import io.vertx.scala.core.net.NetSocket
import scala.util.{Failure, Success}

/**
  * Created by bjsunyutao on 2017/12/1.
  */
class TcpServer {

}
object TcpServer{
  def main(args: Array[String]): Unit = {
     val server = Vertx.vertx().createNetServer()
     server.listenFuture(8088).onComplete{
      case Success(result) => {
        println("Server is now listening")
      }
      case Failure(cause) => {
        println(s"${cause}")
      }
    }

    server.connectHandler(new Handler[NetSocket] {
      override def handle(event: NetSocket): Unit = {
        event.handler(new Handler[Buffer] {
          override def handle(event: Buffer): Unit = {
            println(s"${event.length()}")
          }
        })
      }
    })
  }
}

