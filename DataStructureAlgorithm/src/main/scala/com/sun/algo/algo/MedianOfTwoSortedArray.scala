package com.sun.algo.algo

import scala.collection.mutable.ArrayBuffer

/**
  * Created by bjsunyutao on 2018/4/3.
  * 两个有序数组的中位数
  * 1.数据个数为奇数时，中位数是最中间的数字
  * 2.数据个数为偶数时，中位数为最中间两个数的平均值
  */
object MedianOfTwoSortedArray {
  def main(args: Array[String]): Unit = {
    val nums1 = Array(1,3,6,7)
    val nums2 = Array(2,4,8,9)
    val median = findMedianSortedArrays(nums1,nums2)
    println(median)
  }
  def findMedianSortedArrays(nums1: Array[Int], nums2: Array[Int]): Double = {
    val len1 = nums1.length
    val len2 = nums2.length
    var i = 0
    var j = 0
    var array = new ArrayBuffer[Int]()
    if (nums1.isEmpty) {
      array ++= nums2
    } else if (nums2.isEmpty) {
      array ++= nums1
    }else if(nums1(len1 -1) < nums2(0)) {
      array ++= (nums1 ++ nums2)
    }else if(nums1(0) > nums2(len2 -1)){
      array ++= (nums2 ++ nums1)
    }else {
      while (i < len1 && j < len2){
        if(nums1(i) <= nums2(j)){
          array += nums1(i)
          i += 1
        }else{
          array += nums2(j)
          j += 1
        }
      }

      if(i < len1){
        while (i < len1){
          array += nums1(i)
          i += 1
        }
      }else{
        while (j < len2){
          array += nums2(j)
          j += 1
        }
      }
    }
    println(array.mkString(" "))
    if((len1 + len2) % 2 == 1){
      array((len1 + len2 + 1)/2 - 1).toDouble
    }else {
      (array((len1 + len2)/2 - 1) + array((len1 + len2)/2 )).toDouble/2
    }
  }
}
