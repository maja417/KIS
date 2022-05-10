import okhttp3.*;

import java.io.IOException;

public class WebAppCommunication {
    OkHttpClient client = new OkHttpClient();
    public void prijemPoruke(Poruka poruka){
        if(poruka.getId()==0x02){

            sendAndroid(poruka.getPoruka().toString());


        }
        else{



        }


    }
public void sendAndroid(String s){
        //TODO
    MediaType mediaType=MediaType.parse("application.json")
    RequestBody body = RequestBody.create();
    Request request = new Request.Builder()
            .url(url)
            .post(body)
            .build();
    try (Response response = client.newCall(request).execute()) {

    } catch (IOException e) {
        e.printStackTrace();
    }


}

}
