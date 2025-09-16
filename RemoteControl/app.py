import socket

from flask import Flask, render_template, jsonify
from wakeonlan import send_magic_packet

app = Flask(__name__)

# Configurazione PC da accendere (dummy dati per ora)
PC_MAC = "60:45:bd:fb:cf:8b"
PC_IP= "192.168.1.110"
PC_PORT = 5000
@app.route("/")
def index():
    return render_template("remote.html")
@app.route("/mouse")
def mouse():
    return render_template("mouse.html")

@app.route("/wake", methods=["POST"])
def wake_pc():
    try:
        send_magic_packet(PC_MAC)
        return jsonify({"status": "ok", "message": "Wake-on-LAN sent!"})
    except Exception as e:
            return jsonify({"status": "error", "message": str(e)}), 500

def send_key_to_pc(command):
    sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    sock.sendto(command.encode(), (PC_IP, PC_PORT))
@app.route("/key/<name>", methods=["POST"])
def press_key(name):
    allowed_keys = ["up", "down", "left", "right", "enter", "back", "home", "esc", "space"]
    if name in allowed_keys:
        send_key_to_pc(name)
        return jsonify({"status": "ok", "message": f"Button {name} sent"})
    else:
        return jsonify({"status": "error", "message": "Button non valido"})

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=6969, debug=False)
