# Receipt Processor Challenge

## Overview

This README.md file provides a comprehensive guide for building, running, and using your application, including API endpoints, rules for calculating points, and example requests.

* /receipts/process: Processes a receipt and returns the receipt ID.
* /receipts/{id}/points: Returns the points for a given receipt ID.

## Build

bash
docker build -t receipt-processor-challenge .


## Run

bash
docker run -p 8080:8080 receipt-processor-challenge


## Endpoint: Process Receipts
#### URL http://localhost:8080/receipts/process

#### Method POST

#### Payload Receipt JSON

#### Response JSON containing an id for the receipt

#### Sample Request
json
{
  "retailer": "Target",
  "purchaseDate": "2022-01-01",
  "purchaseTime": "13:01",
  "items": [
    {
      "shortDescription": "Mountain Dew 12PK",
      "price": "6.49"
    },{
      "shortDescription": "Emils Cheese Pizza",
      "price": "12.25"
    },{
      "shortDescription": "Knorr Creamy Chicken",
      "price": "1.26"
    },{
      "shortDescription": "Doritos Nacho Cheese",
      "price": "3.35"
    },{
      "shortDescription": "  Klarbrunn 12-PK 12 FL OZ  ",
      "price": "12.00"
    }
  ],
  "total": "35.35"
}



## Endpoint: Get Points
#### URL http://localhost:8080/receipts/{id}/points

#### Method GET

#### Response A JSON object containing the number of points awarded

#### Sample Response
json
{
  "points": 28
}
