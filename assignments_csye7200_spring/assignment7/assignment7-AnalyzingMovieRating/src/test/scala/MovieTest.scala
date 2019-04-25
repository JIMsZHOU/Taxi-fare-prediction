import org.apache.spark.sql.SparkSession
import org.scalatest._

/**
  * @project assignment7-AnalyzingMovieRating
  * @author  Qixiang Zhou
  */
class MovieTest extends FunSuite {

  val spark= SparkSession.builder().appName("Movie").master("local[*]").getOrCreate()
  import spark.implicits._
  val testData = Seq(
    (1, 1.0),
    (2, 1.0),
    (2, 3.0),
    (3, 4.0)
  ).toDF("movieId", "rating")
  //mean(1) = 1.0 mean(2) = 2.0 mean(3) = 4.0
  //std(1,2,4) = 1.527525232

  test("testGetStd") {
    val temp = Movie.getMeanFrame(testData)
    val std = Movie.getStd(temp.toDF("movieId", "rating"))
    assert(Math.abs(std - 1.527525232) < 0.001)
  }

  test("testGetMean") {
    val mean1 = Movie.getMean(testData, 1)
    val mean2 = Movie.getMean(testData, 2)
    val mean3 = Movie.getMean(testData, 3)

    assert(mean1 == 1.0)
    assert(mean2 == 2.0)
    assert(mean3 == 4.0)
  }

  test("testGetMeanFrame") {
    val result = Movie.getMeanFrame(testData)
    assert(result.sortBy(_._1) equals Seq((1, 1.0),(2, 2.0),(3, 4.0)))
  }

}
