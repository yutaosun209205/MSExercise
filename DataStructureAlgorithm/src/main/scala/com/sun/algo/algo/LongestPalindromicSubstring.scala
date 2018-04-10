package com.sun.algo.algo

/**
  * Created by bjsunyutao on 2018/4/10.
  * 最长回文子串
  * eg： abcbadfg   --->   abcba
  */
object LongestPalindromicSubstring {
  def main(args: Array[String]): Unit = {
    val s = "a"
    println(longestPalindrome(s))
  }
  def longestPalindrome(s: String): String = {
    val builder = new StringBuilder()
    builder.append("$").append("#")
    for(i <- s){
      builder.append(i).append("#")
    }
    builder.append("@")
    println(builder.toString())
    val array = builder.toArray
    val p = new Array[Int](array.length)
    var id = 0
    var mx = 0
    var maxLen = 0
    var index = 0

    for(i <- 1 to array.length - 2){
      if(i < mx){
        p(i) = math.min(p(2 * id - i), mx - i)
      }else{
        p(i) = 1
      }
      while (array(i - p(i)) == array(i + p(i))){
        p(i) += 1
      }

      if(mx < i + p(i)){
        id = i
        mx = i + p(i)
      }

      if(maxLen < p(i) - 1){
        maxLen = p(i) - 1
        index = i
      }
    }
    val b1 = new StringBuilder()
    for(i <- index - maxLen to index + maxLen){
      b1.append(array(i))
    }
    b1.toString().replace("#","")
  }

}
