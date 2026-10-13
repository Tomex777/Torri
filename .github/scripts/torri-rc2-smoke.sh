#!/usr/bin/env bash
set -euo pipefail

APK="${RUNNER_TEMP}/torri-0.21.2-rc2-universal.apk"
test -s "$APK"
adb install "$APK"
adb logcat -c

ACTIVITY="app.torri.rc/eu.kanade.tachiyomi.ui.main.MainActivity"
adb shell am start -W -n "$ACTIVITY"
sleep 25
PID="$(adb shell pidof app.torri.rc 2>/dev/null | tr -d '\r' || true)"
if [[ -z "$PID" ]]; then
  echo "::error::Torri Preview RC2 exited on first launch"
  adb logcat -d -b crash | tail -150
  exit 1
fi
echo "First launch running: $PID"

adb shell input keyevent KEYCODE_HOME
adb shell am start -W -n "$ACTIVITY"
sleep 8
PID="$(adb shell pidof app.torri.rc 2>/dev/null | tr -d '\r' || true)"
if [[ -z "$PID" ]]; then
  echo "::error::Torri Preview RC2 exited after resume"
  adb logcat -d -b crash | tail -150
  exit 1
fi
if adb logcat -d -b crash | grep -q 'Process: app.torri.rc'; then
  echo "::error::Detected Android runtime crash"
  adb logcat -d -b crash | tail -150
  exit 1
fi
adb exec-out screencap -p > "$RUNNER_TEMP/torri-rc2-android15.png"
test -s "$RUNNER_TEMP/torri-rc2-android15.png"
echo "PASS: signed Torri Preview APK installed, launched and resumed. PID: $PID"
