package com.SoftwareLauncher;

import java.io.IOException;

import static spark.Spark.*;

public class MainServer {
    public static void start() {
        System.out.println("Avvio server Spark...");
        port(8080);
        staticFiles.location("/public");  // assicurati di avere src/main/resources/public

        get("/start/:app", (req, res) -> {
            String app = req.params(":app");
            try {
                Runtime.getRuntime().exec(app);
                return "Avviato: " + app;
            } catch (Exception e) {
                res.status(500);
                return "Errore: " + e.getMessage();
            }
        });


    }
    public static void stop() {

        spark.Spark.stop(); // <- IMPORTANTE
    }
}
