package com.sun.algo.algo

import scala.collection.mutable.ArrayBuffer

/**
  * Created by bjsunyutao on 2017/12/22.
  */
object TwoSum {
  def main(args: Array[String]): Unit = {

//    var array = new ArrayBuffer[Int]()
//    for(i <- 1 to 20){
//      array.append((Math.random()*100).toInt)
//    }

    val array = Array(-3,4,3,90)
    println(array)
    var map = Map[Int,Int]()

    val target = 0
    twoSum(array,target).foreach(println)
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
