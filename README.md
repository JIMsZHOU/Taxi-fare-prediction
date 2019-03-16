# CSYE7200_FinalProject

### NYC Taxi Fare Prediction
Inspired by a [Kaggle Competition](https://www.kaggle.com/c/new-york-city-taxi-fare-prediction), this project will be based on Spark to ingest 9914 test taxi fare data into a data(ML) model, and generate the successful prediction rate which is expected to be above 75%. Besides, the test prediction result will be showed in map which is built by Play Framework. The whole project will be writed in scala.

### Teammate
- [Qixiang Zhou](https://github.com/JIMsZHOU)
- [Yujun Xie](https://github.com/xieyuju)

### Data Feature
- key - Unique string
- pickup_datetime - timestamp value indicating when the taxi ride started.
- pickup_longitude - float for longitude coordinate of where the taxi ride started.
- pickup_latitude - float for latitude coordinate of where the taxi ride started.
- dropoff_longitude - float for longitude coordinate of where the taxi ride ended.
- dropoff_latitude - float for latitude coordinate of where the taxi ride ended.
- passenger_count - integer indicating the number of passengers in the taxi ride.
- fare_amount - float dollar amount of the cost of the taxi ride. This value is only in the training set.