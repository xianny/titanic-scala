/**
  * Created by dlau on 3/29/17.
  */
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.mllib.tree.DecisionTree
import org.apache.spark.mllib.tree.model.DecisionTreeModel
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.mllib.regression._
import org.apache.spark.rdd._



import java.io.File

object SimpleApp {

  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName("Simple Application").setMaster("local")
    val sc = new SparkContext(conf)

    val trainingFile = new File("").getAbsolutePath + "/data/train.csv"
    val testFile = new File("").getAbsolutePath + "/data/test.csv"

    val sqlContext = new org.apache.spark.sql.SQLContext(sc)

    val df = sqlContext.read
      .format("com.databricks.spark.csv")
      .option("header", "true") // Use first line of all files as header
      .option("inferSchema", "true") // Automatically infer data types
      .load(trainingFile)
    // Displays the content of the DataFrame to stdout
    df.show()

    sc.stop()
  }

}