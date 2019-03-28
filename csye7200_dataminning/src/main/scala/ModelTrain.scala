import org.apache.spark.ml.Pipeline
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions._
import org.apache.spark.ml.feature._

/**
  * @project csye7200_dataminning
  * @author
  */
object ModelTrain extends App {
  private val spark: SparkSession = SparkSession.builder()
          .master("local")
          .config("executor-memory", 8)
          .config("executor-cores", 4)
          .appName("Spark file Reader")
          .getOrCreate()

  val t = spark.read.option("header","true").option("inferschema","true").csv("/Users/jimzhou/Documents/GitHub/CSYE7200_FinalProject/csye7200_dataminning/src/data/train.csv")

  val train1 = t.withColumn("diff_long",expr("dropoff_longitude - pickup_longitude")).
          withColumn("diff_lat",expr("dropoff_latitude - pickup_latitude"))
  val train2=train1.withColumn("diff_long",abs(col("diff_long"))).
          withColumn("diff_lat",abs(col("diff_lat")))
  val train3 = train2.na.drop()
  val train4 = train3.filter(col("diff_long") < 5).filter(col("diff_lat") < 5).filter(col("fare_amount") > 0).toDF()
  val train5 = train4.drop(col("pickup_datetime")).drop(col("key")).toDF()
  val sampledData = train5.sample(true, 0.001)

  val inputCol = sampledData.columns.filter(!_.equals("fare_amount"))
  val inputVec = new VectorAssembler().setInputCols(inputCol).setOutputCol("features")

  import org.apache.spark.ml.regression.{GBTRegressionModel, GBTRegressor}

  val gbt = new GBTRegressor().setLabelCol("fare_amount").setFeaturesCol("features")
  val pipeline = new Pipeline().setStages(Array(inputVec, gbt))
  val Array(train, test) = sampledData.randomSplit(Array(0.8, 0.2))

  val model = pipeline.fit(train)
  model.save("/Users/jimzhou/Documents/GitHub/CSYE7200_FinalProject/csye7200_dataminning/src/model/" + "trained_model")

  println("wait!")
}
