package datas

import play.api.data._
import play.api.data.Forms._
import play.api.data.format.Formats._

/**
  * @project CSYE7200_FinalProject
  * @author
  */
case class MyRecord(fare: Option[Double],
                  plng: Double,
                  plat: Double,
                  dlng: Double,
                  dlat: Double,
                  pc: Int)

object MyRecord {
  val form = Form(
    mapping(
      "fare" -> optional(of[Double]),
      "plng" -> of[Double],
      "plat" -> of[Double],
      "dlng" -> of[Double],
      "dlat" -> of[Double],
      "pc" -> of[Int]
    )(MyRecord.apply)(MyRecord.unapply)
  )
}

