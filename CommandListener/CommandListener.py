import socket
from pynput.keyboard import Controller, Key

keyboard = Controller()

UDP_IP = "0.0.0.0"
UDP_PORT = 5000

# Mappa comandi API → funzione che preme tasti
def press_key(command):
    if command == "up":
        keyboard.press(Key.up)
        keyboard.release(Key.up)
    elif command == "down":
        keyboard.press(Key.down)
        keyboard.release(Key.down)
    elif command == "left":
        keyboard.press(Key.left)
        keyboard.release(Key.left)
    elif command == "right":
        keyboard.press(Key.right)
        keyboard.release(Key.right)
    elif command == "enter":
        keyboard.press(Key.enter)
        keyboard.release(Key.enter)
    elif command == "back":  # Alt + F4
        keyboard.press(Key.alt_l)
        keyboard.press(Key.f4)
        keyboard.release(Key.f4)
        keyboard.release(Key.alt_l)

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
sock.bind((UDP_IP, UDP_PORT))

print(f"In ascolto su porta {UDP_PORT}...")

while True:
    data, addr = sock.recvfrom(1024)
    command = data.decode().strip()
    press_key(command)
    print(f"Tasto {command} premuto")
