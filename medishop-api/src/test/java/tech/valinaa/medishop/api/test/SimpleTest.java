package tech.valinaa.medishop.api.test;

import java.net.URI;
import java.net.http.HttpRequest;

public class SimpleTest {
    public static void main(String[] args) {
        String s = null;
        var url = URI.create(s + "/api/v1/medishop/medicines");
        var yarnReq = HttpRequest
                .newBuilder()
                .uri(url)
                .header("Content-Type", "application/json")
                .build();
        System.out.println(url);
    }
}
