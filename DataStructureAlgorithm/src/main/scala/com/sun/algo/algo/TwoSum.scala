package com.sun.algo.algo

import scala.collection.mutable.ArrayBuffer

/**
  * Created by bjsunyutao on 2017/12/22.
  *寻找和为定值的两个数：
  * 输入一个数组和一个数字，在数组中查找两个数，使得它们的和正好是输入的那个数字。
  * 要求时间复杂度是O(N)。如果有多对数字的和等于输入的数字，输出任意一对即可。
  * 例如输入数组1、2、4、7、11、15和数字15。由于4+11=15，因此输出4和11。
  */
object TwoSum {
  def main(args: Array[String]): Unit = {

    val array = Array(-3,4,3,90)
    println(array)
    var map = Map[Int,Int]()

    val target = 0
    twoSum(array,target).foreach(println)
    twoSum1(array,target)
  }

  /**
    *
    * @param nums
    * @param target
    * 如果数组是无序的，先排序(N log N)，然后用两个指针i，j，各自指向数组的首尾两端，令i=0，j=n-1，然后i++，j--，逐次判断a[i]+a[j]?=sum，
    * 如果某一刻a[i]+a[j] > sum，则要想办法让sum的值减小，所以此刻i不动，j--；
    * 如果某一刻a[i]+a[j] < sum，则要想办法让sum的值增大，所以此刻i++，j不动。
    * 所以，数组无序的时候，时间复杂度最终为O(N log N + N)=O(N log N)。
    * 如果原数组是有序的，则不需要事先的排序，直接用两指针分别从头和尾向中间扫描，O(N)搞定，且空间复杂度还是O(1)。
    */
  def twoSum1(nums: Array[Int], target: Int): Unit ={
    var i = 0
    var j = nums.length - 1
    while (i < j){
      val sum = nums(i) + nums(j)
      if(sum == target){
        println(nums(i) + " " + nums(j))
        //需要找出所有满足条件的数组对
        i += 1
        j += 1
      }else {
        if (sum > target) {
          j -= 1
        }
        if (sum < target) {
          i += 1
        }
      }
    }
  }

  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
    var map = Map[Int,Int]()
    var array = ArrayBuffer[Int]()
    nums.zipWithIndex.foreach{f=>
      val value = f._1
      val index = f._2
      val sub = target - value
      if(map.size == 0){
        map += (value -> index )
      }else{
        if(map.keySet.contains(sub)){
          array.append(map.get(sub).get)
          array.append(index)
        }
        map += (value -> index)
      }
    }
    array.toArray
  }
}
