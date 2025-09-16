#!/bin/bash


sleep 1


EXCLUDE=(
    "systemd" "gnome-session" "gnome-shell" "systemd-logind" "polkitd" "dbus-daemon"
    "Xorg" "wayland" "pipewire" "pulseaudio" "login" "gdm" "NetworkManager" "gsd-"
    "at-spi" "tracker-miner-f" "xdg-desktop-por" "gvfsd" "evolution-" "goa-daemon"
    "dconf-service" "mutter-x11-fram" "gnome-text-edit" "gnome-keyring-d" "ibus-"
    "gnome-remote-de" "gvfs" "tracker" "update-notifier" "gnome-terminal-" "bash"
    "gjs" "xdg-document-po" "xdg-permission-" "gsd-power" "gsd-screensaver" "gsd-print-notif"
    "kill_processes."
    "pipewire" "pipewire-pulse" "wireplumber"   
    "sleep" "ps" "grep"                         
    "dbus" "runSoftwareLaun" "wake-monitor.sh"                                      
)



file="/home/user/SoftwareLauncher/SoftwareLauncher/scriptBash/log"



echo "script kill process--------------------------------------">> $file
ps -eo pid,user,comm --sort=user | grep -v root | while read pid user comm; do
    skip=0
    for exc in "${EXCLUDE[@]}"; do
        if [[ "$comm" == *"$exc"* ]]; then
            skip=1
	    echo "dontkill $comm" >> $file
            break
        fi
    done

    if [ $skip -eq 0 ]; then
        echo "kill $comm" >> $file 
        kill $pid
    fi
done
echo "Eseguo La Sospensione" >> $file
systemctl suspend


