#!/bin/bash


DVD_DEVICE="/dev/sr0"
TIMEOUT=5  # Timeout in seconds


eject "$DVD_DEVICE"


#wait until dvd player is opend
while ! [ -b "$DVD_DEVICE" ]; do
    sleep 0.5
done

start_time=$(date +%s)

while ! blkid "$DVD_DEVICE" &>/dev/null; do
    sleep 1
    current_time=$(date +%s)
    elapsed=$((current_time - start_time))
    if (( elapsed >= TIMEOUT )); then
        
        exit 1
    fi
done

flatpak run tv.kodi.Kodi dvd://

