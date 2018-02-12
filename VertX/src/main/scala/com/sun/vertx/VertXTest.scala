package com.sun.vertx

import java.util.function.Consumer

import io.vertx.core.http.{HttpServer, HttpServerRequest}
import io.vertx.core._
import io.vertx.ext.web.{Router, RoutingContext}
import io.vertx.ext.web.handler.{BodyHandler, StaticHandler}

/**
  * Created by bjsunyutao on 2017/11/23.
  */

class VertXTest extends AbstractVerticle{

  @throws
  override def start(startFuture: Future[Void]): Unit = {
    val router = Router.router(vertx)
    router.route("/hello").handler(new Handler[RoutingContext]() {
      override def handle(event: RoutingContext): Unit = {
        event.response().putHeader("content-type","text/html").setChunked(true).write("Hello").end("<br/>World")
      }
    })
    router.route("/world").handler(new Handler[RoutingContext] {
      override def handle(event: RoutingContext): Unit = {
        event.response().putHeader("content-type","text/html").setChunked(true).write("<h3>This is the Route world</h3>").end()
      }
    })
    router.route("/webapp/*").handler(StaticHandler.create("webapp"))
    vertx.createHttpServer().requestHandler(new Handler[HttpServerRequest] {
      override def handle(event: HttpServerRequest): Unit = {
//        event.response().putHeader("content-type","text/html").setChunked(true).write("aaaaaa").end("</br>Hello World")
        router.accept(event)
      }
    }).listen(8089,new Handler[AsyncResult[HttpServer]] {
      override def handle(event: AsyncResult[HttpServer]): Unit ={
        if(event.succeeded()) startFuture.complete() else startFuture.fail(event.cause())
      }
    })

}

}

object VertXTest{
  def main(args: Array[String]): Unit = {
    val verticleID = classOf[VertXTest].getName
    run(verticleID)
  }
  def run(verticleID: String): Unit ={
    val vertxOptions = new VertxOptions()
    val runner: Consumer[Vertx] = new Consumer[Vertx] {
      override def accept(t: Vertx): Unit = {
        t.deployVerticle(verticleID)
      }
    }

    val vertx = Vertx.vertx(vertxOptions)
    runner.accept(vertx)
  }
}
