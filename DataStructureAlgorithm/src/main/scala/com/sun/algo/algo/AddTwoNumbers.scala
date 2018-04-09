package com.sun.algo.algo

import javax.management.ListenerNotFoundException

/**
  * Created by bjsunyutao on 2017/12/25.
  * 两个数相加
  */
object AddTwoNumbers {
  def main(args: Array[String]): Unit = {
    val n1 = new ListNode(2)
    val n2 = new ListNode(4)
    val n3 = new ListNode(3)
    n1.next = n2
    n2.next = n3

    val nn1 = new ListNode(5)
    val nn2 = new ListNode(6)
    val nn3 = new ListNode(4)
    nn1.next = nn2
    nn2.next = nn3


    val aa = addTwoNumbers(n1,nn1)

    println(aa)
    var tmp = aa
    while(tmp != null){
      println(tmp.x)
      tmp = tmp.next
    }
  }

  def addTwoNumbers(l1: ListNode, l2: ListNode): ListNode = {
    var listNode = None : Option[ListNode]
    var last = listNode
    var cur1 = l1
    var cur2 = l2
    var y = 0
    while (cur1 != null || cur2 != null){
      val a = if(cur1 == null) 0 else cur1.x
      val b = if(cur2 == null) 0 else cur2.x
      val sum = a + b + y
      y = 0
      val x = if(sum > 9){
        y = sum / 10
        sum % 10
      }else{
        sum
      }
      val tmp = new ListNode(x)
      tmp.next = null
      if(listNode.isEmpty){
        listNode = Some(tmp)
        last = listNode
      }else{
        last.get.next = tmp
        last = Some(tmp)
      }
      cur1 = if(cur1 == null) cur1 else cur1.next
      cur2 = if(cur2 == null) cur2 else cur2.next
    }
    if(y > 0 && !listNode.isEmpty){
      val tmp = new ListNode(y)
      last.get.next = tmp
    }
    listNode.getOrElse(new ListNode(-1))
  }
}
class ListNode(var _x: Int = 0) {
  var next: ListNode = null
  var x: Int = _x
}
