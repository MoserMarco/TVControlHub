#!/bin/bash
rm -R ../SoftwareLauncher/
rm -R ../README.md
rm -R ../CommandListener/
python3 -m venv venv
source venv/bin/activate
pip install -r requirements.txt
sudo mv TVControlHub.service /etc/systemd/system
sudo systemctl daemon-reload
sudo systemctl enable TVControlHub
sudo systemctl start TVControlHub
sudo systemctl status TVControlHub
