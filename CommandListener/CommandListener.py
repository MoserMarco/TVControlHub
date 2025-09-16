#!/usr/bin/env python3
import socket
import subprocess
import time
import sys
from evdev import UInput, ecodes as e, list_devices, InputDevice


keyboard = UInput(
    {
        e.EV_KEY: list(range(e.KEY_ESC, e.KEY_MAX)),
    },
    name="Virtual Keyboard",
    bustype=0x03,   # BUS_USB (0x03)
    vendor=0x1234,
    product=0x5678,
    version=1,
)


mouse = UInput(
    {
        # bottoni mouse
        e.EV_KEY: [
            e.BTN_LEFT, e.BTN_RIGHT, e.BTN_MIDDLE,
            e.BTN_SIDE, e.BTN_EXTRA, e.BTN_FORWARD, e.BTN_BACK
        ],
        # assi relativi (pointer)
        e.EV_REL: [e.REL_X, e.REL_Y, e.REL_WHEEL],
    },
    name="Virtual Mouse",
    bustype=0x03,
    vendor=0x8765,
    product=0x4321,
    version=1,
)

def find_device_by_name(name):
    for path in list_devices():
        try:
            d = InputDevice(path)
            if d.name == name:
                return path, d
        except Exception:
            pass
    return None, None


kbd_path, kbd_dev = find_device_by_name("Virtual Keyboard")
mouse_path, mouse_dev = find_device_by_name("Virtual Mouse")
print("Virtual keyboard device:", kbd_path or "(non trovato)")
print("Virtual mouse device   :", mouse_path or "(non trovato)")

# --- Config rete ---
UDP_IP = "0.0.0.0"
UDP_PORT = 5000

KEY_MAP = {
    "up": ("keyboard", e.KEY_UP),
    "down": ("keyboard", e.KEY_DOWN),
    "left": ("keyboard", e.KEY_LEFT),
    "right": ("keyboard", e.KEY_RIGHT),
    "enter": ("keyboard", e.KEY_ENTER),
    "esc": ("keyboard", e.KEY_ESC),
    "space": ("keyboard", e.KEY_SPACE),
    "mouseRight": ("mouse", e.BTN_RIGHT),
    "mouseLeft": ("mouse", e.BTN_LEFT),
}

def press_key(command):
    if command in KEY_MAP:
        devname, key = KEY_MAP[command]
        target = keyboard if devname == "keyboard" else mouse

        # Press
        target.write(e.EV_KEY, key, 1)
        target.syn()

        time.sleep(0.02)
        # Release
        target.write(e.EV_KEY, key, 0)
        target.syn()
        return True

    elif command == "back":  # Alt+F4
        keyboard.write(e.EV_KEY, e.KEY_LEFTALT, 1)
        keyboard.write(e.EV_KEY, e.KEY_F4, 1)
        keyboard.syn()
        time.sleep(0.02)
        keyboard.write(e.EV_KEY, e.KEY_F4, 0)
        keyboard.write(e.EV_KEY, e.KEY_LEFTALT, 0)
        keyboard.syn()
        return True

    elif command == "home":
        subprocess.run(
            [
                "/home/user/SoftwareLauncher/SoftwareLauncher/runSoftwareLauncherScript.sh",
                "/home/user/SoftwareLauncher/SoftwareLauncher/",
            ],
            capture_output=True,
            text=True,
        )
        return True

    return False

# --- Loop UDP ---
sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
sock.bind((UDP_IP, UDP_PORT))

print(f"Listening port {UDP_PORT}... (run as root if necessary)")
while True:
    data, addr = sock.recvfrom(1024)
    command = data.decode().strip()
    ok = press_key(command)
    print(f"Button {command} pressed -> {'OK' if ok else 'UNKNOWN'}")

