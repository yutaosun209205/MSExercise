package com.sun.algo.algo

/**
  * Created by bjsunyutao on 2018/3/27.
  * 二分查找
  */
object BinarySearch {
  def main(args: Array[String]): Unit = {
    val a = Array(1,3,4,6,7,8,9,12,23,45,67)
    val b = binarySearch(a, 6)
    println(b)
  }
  def binarySearch(array: Array[Int], des : Int) : Int = {
    var low = 0
    var high = array.length -1

    while (low <= high){
      val middle = (low + high) >>> 1
      if(des == array(middle)){
        return middle
      }else if( des < array(middle)){
        high = middle - 1
      }else{
        low = middle + 1
      }
    }
    -1
  }
}
