#!/usr/bin/env bash
# Smoke-test the release-signed production app before staging ANY GitHub release.
set -euo pipefail

APK="dist/torri-v0.21.2-universal.apk"
test -s "$APK"
adb install "$APK"
adb logcat -c

ACTIVITY="app.torri/eu.kanade.tachiyomi.ui.main.MainActivity"
adb shell am start -W -n "$ACTIVITY"
sleep 25
PID="$(adb shell pidof app.torri 2>/dev/null | tr -d '\r' || true)"
if [[ -z "$PID" ]]; then
  echo "::error::Torri production process exited on first launch."
  adb logcat -d -b crash | tail -150
  exit 1
fi
adb shell input keyevent KEYCODE_HOME
adb shell am start -W -n "$ACTIVITY"
sleep 8
PID="$(adb shell pidof app.torri 2>/dev/null | tr -d '\r' || true)"
if [[ -z "$PID" ]]; then
  echo "::error::Torri production process exited on resume."
  adb logcat -d -b crash | tail -150
  exit 1
fi
if adb logcat -d -b crash | grep -q 'Process: app.torri'; then
  echo "::error::Android runtime crash detected in Torri production."
  adb logcat -d -b crash | tail -150
  exit 1
fi
adb exec-out screencap -p > "$RUNNER_TEMP/torri-production-android15.png"
test -s "$RUNNER_TEMP/torri-production-android15.png"
echo "PASS: PRODUCTION-SIGNED Torri installed, launched, backgrounded, and resumed (PID $PID)."
