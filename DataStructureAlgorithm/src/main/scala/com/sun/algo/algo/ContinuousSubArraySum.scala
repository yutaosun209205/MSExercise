package com.sun.algo.algo

/**
  * Created by bjsunyutao on 2018/3/12.
  * 连续子数组最大和
  */
object ContinuousSubArraySum {
  def main(args: Array[String]): Unit = {
    val array = Array(1,-2,3,10,-4,7,2,-5)
    println(sum1(array))
    println(sum2(array))

  }

  /**
    * 直接求和
    * @param array
    * @return
    *  如果与 i 想加前 sum < 0,则直接丢弃，越加越小
    */
  def sum1(array: Array[Int]): Int ={
    var sum = 0
    var max = 0
    for(i <- array){
      if(sum > 0){
        sum = sum + i
      }else sum = i
      if(sum > max){
        max = sum
      }
    }
    max
  }

  /**
    *动态规划
    * @param array
    * @return
    *令sum为当前最大子数组的和，max为最后要返回的最大子数组的和，
    * 当我们往后扫描时，对第j+1个元素有两种选择：要么放入前面找到的子数组，要么做为新子数组的第一个元素；
    * 如果sum加上当前元素a[j]后不小于a[j]，则令sum加上a[j]，否则sum重新赋值，置为下一个元素，即sum = a[j]。
    * 同时，当sum > max，则更新max = sum，否则保持原值，不更新
    */
  def sum2(array: Array[Int]): Int ={

    var sum = 0
    var max = 0
    for(i <- array){
      sum = if(sum + i < i) i else sum + i
      max = if(sum > max) sum else max
    }
    max
  }
}
