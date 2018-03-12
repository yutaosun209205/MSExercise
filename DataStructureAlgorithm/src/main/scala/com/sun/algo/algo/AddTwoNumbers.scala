package com.sun.algo.algo

/**
  * Created by bjsunyutao on 2017/12/25.
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

    println(aa.x)
    println(aa.next.x)
    println(aa.next.next.x)

  }

  def addTwoNumbers(l1: ListNode, l2: ListNode): ListNode = {
    var cur1 = l1
    var cur2 = l2
    var y = 0
    val listNode = new ListNode()
    var cur = new ListNode()
    while (cur1 != null && cur2 != null){
      val sum = l1.x + l2.x + y
      val x = if(sum > 9){
        y = 1
        sum.toString.split("")(1).toInt
      }else{
        y = 0
        sum
      }

      cur1 = cur1.next
      cur2 = cur2.next
    }

    if(l1.next == null && l2.next == null){
      listNode
    }else {
      val a1 = l1.x
      val a2 = l2.x
      val a = a1 + a2 + y
      if (a > 9) {
        val x1 = a.toString.split("")(1).toInt
        listNode.x = x1
        y = 1
      }else{
        y = 0
        listNode.x = a
      }
      listNode.next = addTwoNumbers(l1.next,l2.next)
      listNode
    }
  }
}
class ListNode(var _x: Int = 0) {
  var next: ListNode = null
  var x: Int = _x
}
