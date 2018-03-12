package com.sun.algo.algo

/**
  * Created by bjsunyutao on 2018/3/9.
  * 约瑟夫问题
  */
object JsoephProblem {
  def main(args: Array[String]): Unit = {
    //1.初始化循环链表
    val first = new Node(1)
    var last = first
    val list_len = 10
    val m = 3
    for(i <- 2 to list_len){
      val next = new Node(i)
      if(i == list_len){
        next.next = first
      }
      last.next = next
      last = next
    }

    //2.数m个数，打印节点值后将对应节点删除
    var i = 1;
    var tmp = first
    var pre = first
    while (tmp.next != null){
      if(i < m){
        pre = tmp
        tmp = tmp.next
        i = i + 1
      }else{
        println(tmp.value)
        pre.next = tmp.next
        tmp.next = null
        tmp = pre.next
        i = 1
      }
    }
  }

  private class Node(_value: Int){
    var value = _value
    var next: Node = null
  }
}
