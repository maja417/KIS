import okhttp3.*;

import java.io.IOException;

public class WebAppCommunication {
    OkHttpClient client = new OkHttpClient();


    public void sendAndroid(String s){
         this.client.newBuilder()
                .build();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"x\": \"3.54\",\r\n    \"y\": \"4.44\"\r\n}");
        Request request = new Request.Builder()
                .url("http://localhost:8080/gps")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void sendRPI(String s){
        this.client.newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"x\": \"3.54\",\r\n    \"y\": \"4.44\"\r\n}");
        Request request = new Request.Builder()
                .url("http://localhost:8080/gps")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sendGPS(String x, String y){
        this.client.newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"x\": \"" +x+"\",\r\n    \"y\": \""+y+"\"\r\n}");
        Request request = new Request.Builder()
                .url("http://localhost:8080/gps")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sendTemperature(String s){
        this.client.newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"x\": \"3.54\",\r\n    \"y\": \"4.44\"\r\n}");
        Request request = new Request.Builder()
                .url("http://localhost:8080/gps")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
