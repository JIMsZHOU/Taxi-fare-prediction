import org.apache.spark.ml.Pipeline
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions._
import org.apache.spark.ml.feature._


/**
  * @project CSYE7200_FinalProject
  * @author
  */
object datasetProcess {
  private val spark: SparkSession = SparkSession.builder()
          .master("local")
          .config("executor-memory", 8)
          .config("executor-cores", 4)
          .appName("Spark file Reader")
          .getOrCreate()

  def processData(path: String): Unit = {
    val t = spark.read.option("header","true").option("inferschema","true").csv(path)

    val train1 = t.withColumn("diff_long",expr("dropoff_longitude - pickup_longitude")).
            withColumn("diff_lat",expr("dropoff_latitude - pickup_latitude"))
    val train2=train1.withColumn("diff_long",abs(col("diff_long"))).
            withColumn("diff_lat",abs(col("diff_lat")))
    val train3 = train2.na.drop()
    val train4 = train3.filter(col("diff_long") < 5).filter(col("diff_lat") < 5).filter(col("fare_amount") > 0).toDF()
    val train5 = train4.drop(col("pickup_datetime")).drop(col("key")).drop(col("diff_lat")).drop(col("diff_long")).toDF()
    train5.coalesce(1).write.option("format","csv").option("mode","append").option("sep","\t").save("datas/train.csv")
  }

  val
}

