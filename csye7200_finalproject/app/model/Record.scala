package model

import org.apache.spark.sql.execution.streaming.FileStreamSource.Timestamp

/**
  * @project CSYE7200_FinalProject
  * @author
  */
class Record {
  var key: String = _
  var pickup_datetime: Timestamp = _
  var pickup_longtitude: Float = _
  var pickup_latitude: Float = _
  var dropoff_longtitude: Float = _
  var dropoff_latitude: Float = _
  var passenger_count: Int = _
  var fare_amount: Float = _

  def getKey(): String = {
    this.key
  }

  def setKey(key: String) = {
    this.key = key
  }

  def getPickupDatetime(): Timestamp = {
    this.pickup_datetime
  }

  def setPickupDatetime(time: Timestamp) = {
    this.pickup_datetime = time
  }

  def getPickupLongtitude(): Float = {
    this.pickup_longtitude
  }

  def setPickupLongtitude(a: Float) = {
    this.pickup_longtitude = a
  }

  def getPickupLatitude(): Float = {
    this.pickup_latitude
  }

  def setPickupLatitude(a: Float) = {
    this.pickup_latitude = a
  }

  def getDropoffLongtitude(): Float = {
    this.dropoff_longtitude
  }

  def setDropoffLongtitude(a: Float) = {
    this.dropoff_longtitude = a
  }

  def getDropoffLatitude(): Float = {
    this.dropoff_latitude
  }

  def setDropoffLatitude(a: Float) = {
    this.dropoff_latitude = a
  }

  def getPassengerCount(): Int = {
    this.passenger_count
  }

  def setPassengerCount(a: Int): Unit = {
    this.passenger_count = a
  }

  def getFareAmount(): Float = {
    this.fare_amount
  }

  def setFareAmount(a: Float) = {
    this.fare_amount = a
  }

}

object Record {
  def apply(): Record = new Record()
}
