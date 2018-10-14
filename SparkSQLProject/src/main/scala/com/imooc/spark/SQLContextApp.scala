package com.imooc.spark

import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext
import org.apache.spark.SparkConf

/**
  * SQLContext的使用
  * 注意：IDEA是在本地，而测试数据是在服务器上，能不能在本地进行开发测试的？
  */
object SQLContextApp {
  def main(args: Array[String]): Unit = {

    val path = args(0)
    println(path)
    // 1) 创建相应的Context
//    val conf = new SparkConf()
//      .setMaster("local")
//      .setAppName("SQLContextApp")
    var sparkConf = new SparkConf()
    sparkConf.setAppName("SQLContextApp")
      .setMaster("local")
//      .setMaster("spark://192.168.31.112:7077")
    val sc = new SparkContext(sparkConf)
    val sqlContext = new SQLContext(sc)

    // 2) 相关的处理: json
    val people = sqlContext.read.format("json").load("D:\\people.json")
    people.printSchema()
    people.show()

    // 3) 关闭资源
    sc.stop();
  }
}
