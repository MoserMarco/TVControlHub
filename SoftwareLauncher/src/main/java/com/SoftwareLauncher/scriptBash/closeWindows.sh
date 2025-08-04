#!/bin/bash
echo "chiudo tutto"
# Prende tutte le finestre aperte
windows=$(wmctrl -l | awk '{print $1}')

for win in $windows; do
    # Prova a chiudere la finestra
    wmctrl -ic "$win"
done

