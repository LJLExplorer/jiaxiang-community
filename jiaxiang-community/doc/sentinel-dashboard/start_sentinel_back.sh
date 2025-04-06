#!/bin/bash

# ======================== 配置参数 ========================
JAR_NAME="sentinel-dashboard-1.8.8.jar"
PORT=9001
APP_NAME="sentinel-dashboard"
LOG_DIR="./log"
DATE=$(date +%F)   # 当前日期：2025-04-04
LOG_FILE="$LOG_DIR/${DATE}_${APP_NAME}.log"
# ==========================================================

# 创建日志目录（如果不存在）
mkdir -p $LOG_DIR

# 检查是否已经在运行
PID=$(ps -ef | grep $JAR_NAME | grep -v grep | awk '{print $2}')
if [ -n "$PID" ]; then
  echo "$APP_NAME is already running (PID: $PID)"
  exit 1
fi

# 启动服务
echo "Starting $APP_NAME on port $PORT ..."
nohup java -Dserver.port=$PORT \
           -Dcsp.sentinel.dashboard.server=localhost:$PORT \
           -Dproject.name=$APP_NAME \
           -jar $JAR_NAME > "$LOG_FILE" 2>&1 &

sleep 1
NEW_PID=$(ps -ef | grep $JAR_NAME | grep -v grep | awk '{print $2}')
echo "$APP_NAME started successfully (PID: $NEW_PID)"
echo "Logs: tail -f $LOG_FILE"
