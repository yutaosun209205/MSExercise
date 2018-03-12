package com.sun.algo.algo

/**
  * Created by bjsunyutao on 2018/2/28.
  * 字符串翻转
  */
object StringReverse {
  def main(args: Array[String]): Unit = {
    val str = "abcdefg".toArray

    println(str(0))
    val len = str.length
    val m = 3
    for{i <- 0 until m}{
      val tmp = str(i)
      str(i) = str(len - m + i)
      str(len - m + i) = tmp
    }

    println(str.mkString(""))
  }
}
