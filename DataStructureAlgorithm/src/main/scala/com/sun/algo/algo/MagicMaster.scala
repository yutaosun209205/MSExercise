package com.sun.algo.algo

/**
  * Created by bjsunyutao on 2018/3/9.
  * 魔法师发牌
  * 魔术师利用一副牌中的13张黑桃牌，预先将他们排好后叠放在一起，牌面朝下。对观众说：“我不看牌，只数数就可以猜到每张牌是什么，
  * 我大声数数，你们听，不信？现场演示。”魔术师将牌堆最上面的哪张排数为1，把他翻过来正好是黑桃A，将黑桃A从牌堆抽出放在桌子上，
  * 第二次数1、2，将第一张放在牌堆最下面，第二张翻开，正好是黑桃2，也将它抽出放在桌子上。这样依次进行将13将牌全部翻出，准确无误。
  * 问牌最开始的顺序是怎样排的。
  * 简单点说：把13张牌按照一定的顺序排好，然后依次取牌，将每次取到的牌放在最下面，情形如下：
  * 数一次，取牌，黑桃1，放牌堆最下面
  * 数两次，取牌，黑桃2，放牌堆最下面
  * 数三次，取牌，黑桃3，放牌堆最下面
  * 数四次，取牌，黑桃4，放牌堆最下面
  * …直到取到最后的黑桃13(黑桃K)，表演结束！
  *
  * 循环链表，先初始化13个结点，全设置为0，然后开始数数，数到对应的结点，判断是否为0，为0的话插牌，不为0的话，继续后移
  */
object MagicMaster {
  def main(args: Array[String]): Unit = {

    //1.初始化循环链表
    val first = Node(0)
    var last = first
    for(i <- 2 to 13){
      val next = Node(0)
      if(i == 13){
        next.next = first
      }
      last.next = next
      last = next
    }

    //2.设置链表对应节点值
    var tmp = first
    for(i <- 1 to 13){
      var j = 1
      while(j <= i){
        if(tmp.value == 0) {
          if (j == i ) {
            tmp.value = i
          }
          j += 1
        }
        tmp = tmp.next
      }
    }

    //3.打印链表
    var a = first
    for(i <- 1 to 13){
      println(a.value)
      a = a.next

    }

  }

  private class Node(_value: Int){
    var value = _value
    var next: Node = null
  }
  private object Node{
    def apply(_value: Int): Node = new Node(_value)
  }
}
