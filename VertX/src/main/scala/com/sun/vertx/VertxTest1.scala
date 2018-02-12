package com.sun.vertx

import io.vertx.core.Future
import io.vertx.scala.core.Vertx

/**
  * Created by bjsunyutao on 2017/11/30.
  */
class VertxTest1 {
  def main(args: Array[String]): Unit = {
    val vertx = Vertx.vertx()
    val future = Future.future()
  }
}
