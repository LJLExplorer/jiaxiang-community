#!/bin/bash

JAR_NAME="sentinel-dashboard-1.8.8.jar"
APP_NAME="sentinel-dashboard"

PID=$(ps -ef | grep $JAR_NAME | grep -v grep | awk '{print $2}')
if [ -z "$PID" ]; then
  echo "$APP_NAME is not running."
else
  echo "Stopping $APP_NAME (PID: $PID)..."
  kill -9 $PID
  echo "Stopped."
fi
