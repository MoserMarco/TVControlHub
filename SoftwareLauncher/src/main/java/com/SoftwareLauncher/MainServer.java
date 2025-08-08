package com.SoftwareLauncher;

import java.io.IOException;

import static spark.Spark.*;

public class MainServer {
    public static void start() {
        ipAddress("127.0.0.1");
        port(8080);
        staticFiles.location("/public");
        get("/", (req, res) -> {
            res.redirect("/home.html");
            return null;
        });


    }
    public static void stop() {

        spark.Spark.stop();
    }
}
