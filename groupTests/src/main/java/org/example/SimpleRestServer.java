package org.example;

import static spark.Spark.*;

public class SimpleRestServer {
    public static void main(String[] args) {

        // Set the port to listen on
        port(8080);

        // Handle GET requests to /hello
        get("/hello", (req, res) -> "Hello from test server of group 124M_22_1");

        // Handle POST requests to /hello
        post("/hello", (req, res) -> {
            String name = req.queryParams("name");
            System.out.println("POST" + name);
            return "Hello " + name;
        });

        // Handle PUT requests to /hello
        put("/hello", (req, res) -> {
            String name = req.queryParams("name");
            return "Hello " + name;
        });

        // Handle DELETE requests to /hello
        delete("/hello", (req, res) -> "Goodbye from test server of group 124M_22_1");
    }
}
