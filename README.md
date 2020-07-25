# bitty
Btc price prediction REST API.

- Get current btc value:
  ```sh
  $ curl api.bittie.ru/forecast/currencyValue

- Get current BTC price prediction:
  ```sh
  $ curl api.bittie.ru/forecast/prediction

- Get list of all BTC prices from given time:
  ```sh
  $ curl api.bittie.ru/forecast/listValuesFromTime?fromTime=FROM_TIME #default is 0

- Get list of all predictions from given time:
  ```sh
  $ curl api.bittie.ru/forecast/listPredictionsFromTime?fromTime=FROM_TIME #default is 0
