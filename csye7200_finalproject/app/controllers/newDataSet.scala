package controllers

import scala.tools.nsc.io.File

/**
  * @project CSYE7200_FinalProject
  * @author
  */
object newDataSet {
  val newdataSet: Seq[Tuple6[Double,Double,Double,Double,Double,Int]] = Seq()

  def append(myRecord: MyRecord) = {
    val newdata = (myRecord.fare.get, myRecord.plng, myRecord.plat, myRecord.dlng, myRecord.dlat, myRecord.pc)
    this.newdataSet :+ newdata
  }

  def appendtoCSV() = {
    for (a <- newdataSet) File("datas/train.csv").appendAll(a._1+","+a._2+","+a._3+","+a._4+","+a._5+","+a._6)
  }
}
