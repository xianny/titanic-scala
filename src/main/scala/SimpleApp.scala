/**
  * Created by dlau on 3/29/17.
  */
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.DecisionTreeClassifier
import org.apache.spark.ml.classification.DecisionTreeClassificationModel
import org.apache.spark.ml.feature.{StringIndexer, VectorAssembler}
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import java.io.File

import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.sql.DataFrame

object SimpleApp {

  def main(args: Array[String]) {

    val conf = new SparkConf().setAppName("Simple Application").setMaster("local")
    val sc = new SparkContext(conf)

    val trainingFile = new File("").getAbsolutePath + "/data/train.csv"
    val testFile = new File("").getAbsolutePath + "/data/test.csv"

    val sqlContext = new org.apache.spark.sql.SQLContext(sc)

    val trainingData = sqlContext.read
      .format("com.databricks.spark.csv")
      .option("header", "true") // Use first line of all files as header
      .option("inferSchema", "true") // Automatically infer data types
      .load(trainingFile)

    val testData = sqlContext.read
      .format("com.databricks.spark.csv")
      .option("header", "true") // Use first line of all files as header
      .option("inferSchema", "true") // Automatically infer data types
      .load(testFile)

    val dataFrame = sqlContext.createDataFrame(Seq(
      (0, 1, 1, 1.0),
      (1, 2, 2, 2.0),
      (0, 2, 3, 3.0)
    )).toDF("Survived", "f1", "f2", "f3")


    // add columnname_index for each column
    val indexCols: Array[org.apache.spark.ml.PipelineStage] = trainingData.columns.map { cname =>
      new StringIndexer()
        .setInputCol(cname)
        .setOutputCol(s"${cname}_index")
        .setHandleInvalid("skip")
    }

    val labelNames = Array("Survived", "Survived_index")
    val indexedFeatureNames = trainingData.columns.filterNot(labelNames.contains(_)).map(c => s"${c}")
    val aggregateFeatures = new VectorAssembler()
      .setInputCols(indexedFeatureNames) // f1_index, f2_index, f3_index
      .setOutputCol("features")


    ////    // Automatically identify categorical features, and index them.
    ////    val featureIndexer = new VectorIndexer()
    ////      .setInputCol("features")
    ////      .setOutputCol("indexedFeatures")
    ////      .setMaxCategories(4) // features with > 4 distinct values are treated as continuous
    //
    // Train a DecisionTree model.
    val dt = new DecisionTreeClassifier()
      .setLabelCol("Survived_index")
      .setFeaturesCol("features")
    //
    //    // Convert indexed labels back to original labels.
    ////    val labelConverter = new IndexToString()
    ////      .setInputCol("prediction")
    ////      .setOutputCol("predictedLabel")
    ////      .setLabels(labelIndexer.labels)

    // Chain indexers and tree in a Pipeline
    val pipeline = new Pipeline()
      .setStages(indexCols)

    // Train model.  This also runs the indexers.
    val model = pipeline.fit(trainingData)

    val transformed = model.transform(trainingData)

    transformed.show()
    //
    //    // Make predictions.
    //    val predictions = model.transform(testData)
    //
    //    // Select example rows to display.
    //    predictions.select("Survived_index", "features").show(5)
    //
    //    // Select (prediction, true label) and compute test error
    //    val evaluator = new MulticlassClassificationEvaluator()
    //      .setLabelCol("Survived_index")
    //      .setPredictionCol("prediction")
    //      .setMetricName("precision")
    //    val accuracy = evaluator.evaluate(predictions)
    //    println("Test Error = " + (1.0 - accuracy))
    //
    //    val treeModel = model.stages(2).asInstanceOf[DecisionTreeClassificationModel]
    //    println("Learned classification tree model:\n" + treeModel.toDebugString)

    sc.stop()
  }

}