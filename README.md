# TVControlHub

## Overview
I purchased a small laptop and decided to connect it to my living room TV, turning my 20-year-old television into a smart TV.  
The setup was simple: the laptop was connected to the TV via HDMI.

My initial goals for the system were:
- Watch DVDs
- Play retro games using emulators
- Browse the internet

To keep things neat and aesthetically pleasing, I mounted the laptop behind the TV so it would remain invisible.  
For powering it on, I planned to use **Wake-on-LAN**, and for sending commands, a USB keyboard.  
The laptop runs **Ubuntu 24**.

Everything worked perfectly, but since other family members (with limited technical skills) would also be using it,  
I decided to create a program that would make it easier to run applications and use Ubuntu.

---

## Motivation
Using only Wake-on-LAN and a USB keyboard meant always having both the phone and keyboard nearby.  
Given that the control interface required just a few buttons, I also decided to create a **web-based remote control**.

---

## Project Structure
The project is made of two main components:

1. **Software Launcher** – Installed on the laptop
2. **Remote Control** – A web page accessible within the home network

---

## Technologies Used

### Software Launcher
- **JavaFX** – For the graphical user interface and keyboard command handling
- **Spark** – To create a local server, opened inside a JavaFX-managed window (hidden browser)
- **HTML, CSS, JS** – Front-end of the application
- **HP Laptop** – The device running the launcher

### Remote Control
- **Python Flask** – Backend for the web page
- **HTML, CSS, JS** – Front-end of the remote control
- **Raspberry Pi 3** – Server hosting the web application

---

## How It Works
1. **Startup** – Power on the laptop via Wake-on-LAN  
2. **Launch Applications** – Use the Software Launcher interface to quickly access media players, emulators, and browsers  
3. **Remote Control** – Access the control page from any device in the home network to send commands

---