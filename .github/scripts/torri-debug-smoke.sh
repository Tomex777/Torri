#!/usr/bin/env bash
set -euo pipefail

APK="$(find app/build/outputs/apk/debug -type f -name '*universal*debug.apk' -print -quit)"
if [[ -z "$APK" || ! -s "$APK" ]]; then
  echo "::error::Universal Torri debug APK not found"
  find app/build/outputs/apk/debug -type f || true
  exit 1
fi

adb install -r "$APK"
adb logcat -c

ACTIVITY="app.torri.dev/eu.kanade.tachiyomi.ui.main.MainActivity"
adb shell am start -W -n "$ACTIVITY"
sleep 20
PID="$(adb shell pidof app.torri.dev 2>/dev/null | tr -d '\r' || true)"
if [[ -z "$PID" ]]; then
  echo "::error::Torri process exited after first launch"
  adb logcat -d -b crash | tail -150
  exit 1
fi
echo "First launch running: $PID"

adb shell input keyevent KEYCODE_HOME
adb shell am start -W -n "$ACTIVITY"
sleep 8
PID="$(adb shell pidof app.torri.dev 2>/dev/null | tr -d '\r' || true)"
if [[ -z "$PID" ]]; then
  echo "::error::Torri process exited after resume"
  adb logcat -d -b crash | tail -150
  exit 1
fi
if adb logcat -d -b crash | grep -q 'Process: app.torri.dev'; then
  echo "::error::Torri Android runtime crash detected"
  adb logcat -d -b crash | tail -150
  exit 1
fi
adb exec-out screencap -p > "$RUNNER_TEMP/torri-debug-android15.png"
test -s "$RUNNER_TEMP/torri-debug-android15.png"
echo "PASS: Torri installed, launched, backgrounded and resumed. PID: $PID"
