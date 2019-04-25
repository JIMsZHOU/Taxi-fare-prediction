import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions._
import scala.collection.mutable.ListBuffer
/**
  * @project assignment7-AnalyzingMovieRating
  * @author  Qixiang Zhou
  */
object Movie extends App {

//  val spark: SparkSession = SparkSession.builder().appName("Movie").master("local[*]").getOrCreate()
//  val data = spark.read.option("header","true").option("inferschema","true").csv("src/main/data/rating.csv")
//  import spark.implicits._

  /**
    * @param frame: DataFrame
    * @param id: Int
    * @tparam _: Double
    *
    * This method is going to get the mean rating value for specific movieId
    * */
  def getMean(frame: DataFrame, id: Int): Double = {

    frame.filter(col("movieId") === id).select(mean("rating")).head().getDouble(0)
  }

  /**
    * @param frame: DataFrame
    * @tparam meanFrame: Seq[Tuple2[Int, Double`]`]
    *
    * This method is going to get a Sequence which contains all mean rating value for every movie
    * Since implicit method to change a Sequence to a DataFrame cannot pass through test class into this object,
    * so the method is separated to two method, one is this another is getMean which is going to receive a DataFrame which contains movieId and meanRating
    *
    * The inner method is used to get a DataFrame which only contain one movieId and all the rating values for that movie.
    * Use the inner method to get DataFrame and then get the mean value
    * */
  def getMeanFrame(frame: DataFrame): Seq[(Int, Double)] = {

    def inner(frame: DataFrame): Double = {
      frame.select(mean("rating")).head.getDouble(0)
    }


    val meanFrame: ListBuffer[(Int, Double)] = ListBuffer()
    for (id <- frame.select("movieId").distinct().collect()) {
      val movieId: Int = id.getInt(0)
      val rating: Double = inner(frame.filter(col("movieId") === movieId))
      meanFrame.append(Tuple2(movieId, rating))
    }
    meanFrame
  }

  /**
    * @param frame: DataFrame
    * @tparam _: Double
    *
    * This method is going to get the standard deviation value for all movieIds.
    * Use the mean rating value for each movie, and use the sql.Functions to calculate the value
    * */
  def getStd(frame: DataFrame): Double= {
    frame.select(stddev("rating")).head().getDouble(0)
  }


  /**
    * Main Function
    * @param args: parameters from console
    *
    * This main function is going to use the input file to get mean and std values
    * */
  override def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession.builder().appName("Movie").master("local[*]").getOrCreate()
    val data = spark.read.option("header","true").option("inferschema","true").csv("src/main/data/rating.csv")
    val m = getMean(data, 1)
    import spark.implicits._
    val s = getStd(getMeanFrame(data).toDF("movieId", "rating"))
    print(m)
    print(s)
  }
}
