package com.sun.algo.algo

/**
  * Created by bjsunyutao on 2018/4/2.
  * 求最长的不重复的子字符串
  * Given "abcabcbb", the answer is "abc", which the length is 3.
  * Given "bbbbb", the answer is "b", with the length of 1.
  * Given "pwwkew", the answer is "wke", with the length of 3.
  * Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
  */
object LongestSubStrNoRepeat {
  def main(args: Array[String]): Unit = {
    val str = "abbbcdb"
    lengthOfLongestSubstring(str)
    lengthOfLongestSubstring2(str)
  }

  def lengthOfLongestSubstring(s: String): Int = {
    var map = Map[String,Int]()
    var maxStr = ""
    val chars = s.toArray
    var i = 0
    while (i < chars.length){
      val c = chars(i).toString
      if(!map.keySet.contains(c)) {
        map += (c -> i)
        if(i == chars.length - 1 && (maxStr == "" || maxStr.length < map.size)) maxStr = map.keySet.mkString("")
        i += 1
      }else{
        if(maxStr.length < map.size) maxStr = map.keySet.mkString("")
        val index = map.get(c).get
        map = map.empty
        i = index + 1
      }
    }
    println(maxStr)
    maxStr.length
  }
  def lengthOfLongestSubstring2(s: String): Int ={
    val chars = s.toCharArray
    val array = new Array[Int](256)
    for(i <- 0 to 255){
      array(i) = -1
    }
    var startIndex = -1
    var originIndex = startIndex
    var maxLen = 0
    for(i <- 0 to chars.length - 1){
      val c = chars(i)
      val index = array(c)
      if(index > startIndex) {
        startIndex = index
      }
      if(i - startIndex > maxLen){
        maxLen = i - startIndex
        originIndex = startIndex
      }
      array(c) = i
    }

    val builder = new StringBuilder()
    for(i <- originIndex + 1 to originIndex + maxLen ){
      builder.append(chars(i))
    }
    println(builder.toString())
    builder.length
  }
}
