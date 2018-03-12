package com.sun.algo.algo

import java.util
import java.util.Scanner

/**
  * Created by bjsunyutao on 2018/3/12.
  * 进制转换
  */
object BaseConversion {
  def main(args: Array[String]): Unit = {
    val cin = new Scanner(System.in);
    //System.out.println("请输入需要转换数字：");
    var num = cin.nextInt();
    //System.out.println("请输入需要转换的进制（2~9）：");
    val R = cin.nextInt();

    val ocNum = num;
    val stack = new util.Stack[Int]();

    while(num != 0) {
      stack.push(num%R);
      num = num/R;
    }

    //System.out.print(ocNum + "转换" + R + "进制：");
    while(!stack.empty()) {
      System.out.print(stack.peek());
      stack.pop();
    }
  }
}
