package com.SoftwareLauncher;

import javafx.application.Platform;

public class SoftwareLauncher {

    int programId;

    public SoftwareLauncher(int programId) {
        this.programId = programId;
    }

    public void run() {
        switch (programId) {
            case 0:
                Kodi();
                break;
            case 1:
                DVDPlayer();
                break;
            case 2:
                Bluetooth();
                break;
            case 3:
                Dolphin();
                break;
            case 4:
                Mame();
                break;
            case 5:
                Extra1();
                break;
            case 6:
                Extra2();
                break;
            case 7:
                Exit();
                break;
            case 8:
                PowerOff();
                break;
            default:
                System.out.println("Programma non riconosciuto: " + programId);
        }
    }
    private void Kodi() {
        System.out.println("Kodi: " + programId);
        executeCommandWhitSonProces("flatpak", "run" ,"tv.kodi.Kodi" );
    }

    private void DVDPlayer() {
        System.out.println("DVDPlayer: " + programId);
    }

    private void Bluetooth() {
        System.out.println("Bluetooth: " + programId);
        executeCommandWhitSonProces("gnome-control-center", "bluetooth");

    }

    private void Dolphin() {
        System.out.println("Dolphin: " + programId);
        executeCommandWhitSonProces("flatpak", "run" ,"org.DolphinEmu.dolphin-emu" );
    }

    private void Mame() {

        System.out.println("Mame: " + programId);
        executeCommandWhitSonProces( "mame" );

    }

    private void Extra1() {
        System.out.println("Extra1: " + programId);
    }

    private void Extra2() {
        System.out.println("Extra2: " + programId);
    }

    private void Exit() {
        System.out.println("Exit: " + programId);
        MainServer.stop();
        Platform.exit();
        System.exit(0);
    }

    private void PowerOff() {
        System.out.println("PowerOff: " + programId);

        executeCommandAndKillFather("systemctl" , "suspend");

    }

    private void executeCommandAndKillFather(String... command) {
        try {
            // Comando da eseguire (es. un'applicazione Flatpak o qualsiasi comando Ubuntu)
            // Avvia il processo figlio (in background, indipendente dal padre)

            ProcessBuilder builder = new ProcessBuilder(command);
            builder.start();

            // Termina il processo padre
            System.exit(0);

        } catch (Exception e) {

        }
    }
    private void executeCommandWhitSonProces(String... command ) {
        try {
            // Comando da eseguire (es. un'applicazione Flatpak o qualsiasi comando Ubuntu)
            // Avvia il processo figlio (in background, indipendente dal padre)

            ProcessBuilder builder = new ProcessBuilder(command);

            builder.start();


        } catch (Exception e) {

        }
    }
}
