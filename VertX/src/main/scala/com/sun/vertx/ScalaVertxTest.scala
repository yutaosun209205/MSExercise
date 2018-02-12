package com.sun.vertx

import io.vertx.lang.scala.ScalaVerticle

import scala.concurrent.Future

/**
  * Created by bjsunyutao on 2017/11/30.
  */
class ScalaVertxTest extends ScalaVerticle{
  override def startFuture(): Future[_] = {
    println("dddd")
    vertx.deployVerticleFuture("aaa")
  }
}

object ScalaVertxTest{
  def main(args: Array[String]): Unit = {

  }
}
