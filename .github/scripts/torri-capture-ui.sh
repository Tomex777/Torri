#!/usr/bin/env bash
set -euo pipefail

APK="$(find app/build/outputs/apk/debug -type f -name '*universal*debug.apk' -print -quit)"
test -s "$APK"
adb install -r "$APK"
adb shell settings put system screen_off_timeout 600000 || true
adb shell svc power stayon true || true
mkdir -p docs/screenshots

ACTIVITY="app.torri.dev/eu.kanade.tachiyomi.ui.preview.TorriUiPreviewActivity"

capture() {
  local mode="$1"
  local file="$2"
  adb shell am force-stop app.torri.dev || true
  adb logcat -c
  adb shell am start -W -n "$ACTIVITY" --es screen "$mode"
  sleep 12
  local pid
  pid="$(adb shell pidof app.torri.dev | tr -d '\r' || true)"
  test -n "$pid" || { adb logcat -d -b crash | tail -120; echo "::error::$mode capture activity did not stay running"; exit 1; }
  adb exec-out screencap -p > "$file"
  test -s "$file"
  echo "Captured $file"
  adb logcat -d -b crash | tail -35
}

capture studio docs/screenshots/torri-actual-theme-studio.png
adb shell input swipe 520 1940 520 590 620
sleep 3
adb exec-out screencap -p > docs/screenshots/torri-actual-theme-studio-picker.png
test -s docs/screenshots/torri-actual-theme-studio-picker.png
capture continue docs/screenshots/torri-continue-component-preview.png
