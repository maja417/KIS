import okhttp3.*;

import java.io.IOException;

public class WebAppCommunication {
    OkHttpClient client;


    public WebAppCommunication() {
        this.client = new OkHttpClient();
    }

    public void sendAndroid(String s){
      this.client.newBuilder()
                .build();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"x\": \""+s+"\"\r\n}");
        Request request = new Request.Builder()
                .url("http://localhost:8080/gps")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            client.newCall(request).execute();
        }  catch (IOException | NoSuchMethodError e) {
            System.out.println("evo me :)");
        }

    }


    public void sendRPI(String s){
       this.client.newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"x\": \""+s+"\"\r\n}");
        Request request = new Request.Builder()
                .url("http://localhost:8080/gps")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            client.newCall(request).execute();
         }  catch (IOException | NoSuchMethodError e) {
            System.out.println("evo me :)");
        }
    }


    public void sendGPS(float x, float y){
        this.client.newBuilder()
                .build();
       MediaType mediaType = MediaType.parse("application/json");

        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"x\": \""+x+"\",\r\n    \"y\": \""+y+"\"\r\n}");
        Request request = new Request.Builder()
                .url("http://localhost:8080/gps")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        }  catch (IOException | NoSuchMethodError e) {
            System.out.println("evo me :)");
        }

    }


    public void sendTemperature(String s){
        this.client.newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"x\": \""+s+"\"\r\n}");
        Request request = new Request.Builder()
                .url("http://localhost:8080/gps")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        }  catch (IOException | NoSuchMethodError e) {
            System.out.println("evo me :)");
        }
    }



}
