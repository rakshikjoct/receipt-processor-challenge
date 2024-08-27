# Receipt Processor Challenge

## Overview

This application processes receipts and calculates points for each receipt. It exposes two endpoints:

* `/receipts/process`: Processes a receipt and returns the receipt ID.
* `/receipts/{id}/points`: Returns the points for a given receipt ID.

## Run

I've shared the Docker image, so you can simply run it using:

```bash
docker run -p 8080:8080 receipt-processor-challenge