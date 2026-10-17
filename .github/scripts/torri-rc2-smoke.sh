#!/usr/bin/env bash
set -euo pipefail

APK="dist/torri-v1.1.0-theme-resume-rc2-universal.apk"
PACKAGE="app.torri.rc"
ACTIVITY="$PACKAGE/eu.kanade.tachiyomi.ui.main.MainActivity"

test -s "$APK"
adb install -r "$APK"
adb logcat -c
adb shell am start -W -n "$ACTIVITY"
sleep 25
pid="$(adb shell pidof "$PACKAGE" 2>/dev/null | tr -d '\r' || true)"
if [[ -z "$pid" ]]; then
  echo "::error::Torri RC failed first launch"
  adb logcat -d -b crash | tail -120
  exit 1
fi
adb exec-out screencap -p > "$RUNNER_TEMP/torri-rc2-android15.png"
test -s "$RUNNER_TEMP/torri-rc2-android15.png"
adb shell input keyevent KEYCODE_HOME
adb shell am start -W -n "$ACTIVITY"
sleep 8
pid="$(adb shell pidof "$PACKAGE" 2>/dev/null | tr -d '\r' || true)"
test -n "$pid" || { echo "::error::Torri RC failed warm resume"; adb logcat -d -b crash | tail -120; exit 1; }
if adb logcat -d -b crash | grep -q "Process: $PACKAGE"; then
  echo "::error::Torri RC crashed in Android 15 emulator"
  adb logcat -d -b crash | tail -120
  exit 1
fi
echo "PASS: signed test RC installed and survived cold launch, home and resume on Android 15"
