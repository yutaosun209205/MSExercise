package com.sun.vertx

import io.vertx.core.{Vertx, VertxOptions}

/**
  * Created by bjsunyutao on 2017/11/23.
  */
object VertXTest {
  def main(args: Array[String]): Unit = {
    val vertx1 = Vertx.vertx()
    val vertx2 = Vertx.vertx(new VertxOptions().setWorkerPoolSize(40))
  }
}
