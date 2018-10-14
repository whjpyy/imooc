package com.imooc.spark

import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * HiveContext的使用
  * 使用时需要通过--jars 把mysql的驱动传递到classpath
  */
object HiveContextApp {
  def main(args: Array[String]): Unit = {

    val path = args(0)

    // 1) 创建相应的Context
    var sparkConf = new SparkConf()
    sparkConf.setAppName("SQLContextApp")
      .setMaster("spark://hadoop001:7077")
    val sc = new SparkContext()
//    val sqlContext = new SQLContext(sc)
    val hiveContext = new HiveContext(sc);

    // 2) 相关的处理: json
    hiveContext.table("emp").show;

    // 3) 关闭资源
    sc.stop();
  }
}
