package com.imooc.spark

import org.apache.spark.sql.SparkSession

object SparkSessionApp {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("SparkSessionApp").getOrCreate()

  }
}
