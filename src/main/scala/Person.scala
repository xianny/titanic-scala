import kantan.codecs.Result.Success
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import kantan.csv.ops._
import kantan.csv.rfc


/**
  * Created by dlau on 3/31/17.
  */
sealed trait Sex
case object Male extends Sex
case object Female extends Sex

sealed trait Port
case object Cherbourg extends Port
case object Queenstown extends Port
case object Southampton extends Port

//case class Person(PassengerId: Int,
//                  Survived: Boolean,
//                  Pclass:Int,
//                  Name: String,
//                  Sex: Option[Sex],
//                  Age: Int,
//                  SibSp: Int,
//                  Parch: Int,
//                  Ticket: Int,
//                  Fare: Double,
//                  Cabin: String,
//                  Embarked: Option[Port]) {
//
//}

case class Person(PassengerId: Option[String],
                  Survived: Option[String],
                  Pclass: Option[Int],
                  Name: Option[String],
                  Sex: Option[String],
                  Age: Option[String],
                  SibSp: Option[String],
                  Parch: Option[String],
                  Ticket: Option[String],
                  Fare: Option[String],
                  Cabin: Option[String],
                  Embarked: Option[Port]) {

}

case object Person {
  def readBatch(sc: SparkContext, filepath: String): RDD[Person] = {
    val data: RDD[String] = sc.textFile(filepath).cache()

    val dataWithoutHeader: RDD[String] = data.filter(_ != data.first())//use mapPartitionsWithIndex for more performance
    data.map { row =>
      row
        .readCsv[List, Person](rfc.withHeader)
        .collect { case Success(p) => p }
    }.flatMap { y => y }
  }

}
