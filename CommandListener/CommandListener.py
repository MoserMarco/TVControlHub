import socket
import subprocess
from evdev import UInput, ecodes as e

# Define real keyboard
capabilities = {
    e.EV_KEY: list(range(e.KEY_ESC, e.KEY_MAX))
}


ui = UInput(capabilities,
            name="AT Translated Set 2 keyboard",
            bustype=0x11,
            vendor=0x1,
            product=0x1,
            version=1)

UDP_IP = "0.0.0.0"
UDP_PORT = 5000

KEY_MAP = {
    "up": e.KEY_UP,
    "down": e.KEY_DOWN,
    "left": e.KEY_LEFT,
    "right": e.KEY_RIGHT,
    "enter": e.KEY_ENTER,
    "esc": e.KEY_ESC,
    "space": e.KEY_SPACE,
    "mouseRight": e.BTN_RIGHT,
    "mouseLeft": e.BTN_LEFT,
}

def press_key(command):
    if command in KEY_MAP:
        key = KEY_MAP[command]
        ui.write(e.EV_KEY, key, 1) 
        ui.write(e.EV_KEY, key, 0) 
        ui.syn()
    elif command == "back":  # Alt+F4
        ui.write(e.EV_KEY, e.KEY_LEFTALT, 1)
        ui.write(e.EV_KEY, e.KEY_F4, 1)
        ui.write(e.EV_KEY, e.KEY_F4, 0)
        ui.write(e.EV_KEY, e.KEY_LEFTALT, 0)
        ui.syn()
    elif command == "home":
        subprocess.run(["/home/user/SoftwareLauncher/SoftwareLauncher/runSoftwareLauncherScript.sh", "/home/user/SoftwareLauncher/SoftwareLauncher/"], capture_output=True, text=True)
        
sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
sock.bind((UDP_IP, UDP_PORT))

print(f"Listening port {UDP_PORT}...")

while True:
    data, addr = sock.recvfrom(1024)
    command = data.decode().strip()
    press_key(command)
    print(f"Button {command} pressed")
