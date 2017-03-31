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

    val persons: RDD[Person] = Person.readBatch(sc, trainingFile)

    persons.take(5).foreach(println)
    // val categoricalFeaturesInfo = Map[Int, Int]()
    // val impurity = "variance"
    // val maxDepth = 5
    // val maxBins = 32


    sc.stop()
  }

}