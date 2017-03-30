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

    val trainingFile = new File("").getAbsolutePath() + "/data/train.csv"
    val testFile = new File("").getAbsolutePath() + "/data/test.csv"

    // val categoricalFeaturesInfo = Map[Int, Int]()
    // val impurity = "variance"
    // val maxDepth = 5
    // val maxBins = 32

    
    val csv: RDD[String] = sc.textFile(trainingFile).cache()

    println("Done")
    sc.stop()
  }

}