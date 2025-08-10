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
                System.out.println("Program not recognized" + programId);
        }
    }

    private void Kodi() {
        System.out.println("Kodi: " + programId);
        executeCommandWhitSonProces("flatpak", "run" ,"tv.kodi.Kodi" );
    }

    private void DVDPlayer() {

        System.out.println("DVDPlayer: " + programId);

        String scriptPathDvd = "scriptBash/dvd_Kodi.sh";
        executeCommandWhitSonProces("/bin/bash", scriptPathDvd);


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

        String scriptPathCloseWindows = "scriptBash/closeWindows.sh";
        String scriptPathSuspend = "scriptBash/suspend.sh";
        executeCommandWhitSonProces("/bin/bash", scriptPathCloseWindows);
        executeCommandWhitSonProces("/bin/bash", scriptPathSuspend);


    }

    private void executeCommandAndKillFather(String... command) {
        try {

            ProcessBuilder builder = new ProcessBuilder(command);
            builder.start();


            System.exit(0);

        } catch (Exception e) {

        }
    }

    private void executeCommandWhitSonProces(String... command ) {
        try {

            ProcessBuilder builder = new ProcessBuilder(command);

            Process process = builder.start();
            process.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
