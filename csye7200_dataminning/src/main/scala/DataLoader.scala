import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * @project csye7200_dataminning
  * @author
  */
object DataLoader extends App {
  private val spark: SparkSession = SparkSession.builder()
          .master("local")
          .appName("Spark file Reader")
          .getOrCreate()

  def readData(path: String, format: String) :DataFrame = {
    val traindata = spark.read.format(format).load(path)
    traindata
  }

  val train = DataLoader.readData("src/data/train.csv", "csv")
  val test = DataLoader.readData("src/data/test.csv", "csv")

  train.printSchema()
  test.show(5)

  train.na.drop()
}
