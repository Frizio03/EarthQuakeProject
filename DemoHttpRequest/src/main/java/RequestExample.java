import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.net.URL;

public class RequestExample {

    public static void main(String[] args) {

        //Creating a new OkHttpClient class to make requests
        OkHttpClient client = new OkHttpClient();

        //URL data
        String protocol = "https";
        String host = "jsonplaceholder.typicode.com";
        String path = "posts";

        //Building URL
        HttpUrl.Builder urlBuilder = new HttpUrl.Builder();

        urlBuilder.scheme(protocol);
        urlBuilder.host(host);
        urlBuilder.addPathSegment(path);

        URL myurl = urlBuilder.build().url();

        //Printing the generated URL
        System.out.println("Generated URL: " + myurl);

        //Setting the request parameters (defaults to GET method)
        Request request = new Request.Builder()
                .url(myurl)
                .build();

        //Generating a new call to obtain the response
        Call call = client.newCall(request);

        //Trying to execute the call
        try (Response response = call.execute()){

            //Saving body data before OkHttpClient closes the request
            ResponseBody responseBody = response.body();

            //Creating a new ObjcetMapper from JACKSON library
            ObjectMapper mapper = new ObjectMapper();

            //Parsing the body string to JsonNode format (from JACKSON)
            JsonNode bodyNode = mapper.readTree(responseBody.string());

            //Printing 3/100 post data
            for(int i = 0; i < 3; i++) {

                Post post = mapper.readValue(bodyNode.get(i).toString(), Post.class);

                System.out.println("--> Printing post " + (i+1) + ":");
                System.out.println(post.toString());
                System.out.println();
            }
        }
        catch (IOException e) {
            //Catching errors and throwing exceptions
            throw new RuntimeException(e.getMessage());
        }
    }
}
