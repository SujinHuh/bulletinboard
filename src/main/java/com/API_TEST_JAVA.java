package com;

import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class API_TEST_JAVA {

    public static void main(String[] args) throws Exception {

        String result = "";
        HttpURLConnection con = null;
        OutputStream os = null;
        BufferedReader in = null;

        if( true ) {
            try {
                String API_KEY = "37850b0c9b708891aff390ad3413691d";
                String param = "";
                String urlString = "https://api.kakaobrain.com/v1/inference/kogpt/generation";

                URL url = new URL(urlString);
                con = (HttpURLConnection) url.openConnection();
                con.setDoInput(true);
                con.setDoOutput(true);
                con.setRequestMethod("POST");
                con.setRequestProperty("Accept-Charset", "UTF-8");
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestProperty("Authorization", "KakaoAK " + API_KEY);

                con.setConnectTimeout(20000);
                con.setReadTimeout(20000);

                os = con.getOutputStream();

                JsonObject json = new JsonObject();

                json.addProperty("prompt", "오늘 점심은 뭘먹을까");
                json.addProperty("max_tokens", 300);
                json.addProperty("n", 1);

                param = json.toString();

                System.out.println(">>> " + param);

                os.write(param.getBytes("utf-8"));

                String buffer;

                in = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));

                while ((buffer = in.readLine()) != null) {
                    result += buffer;
                }
                System.out.println("--------------------------------------------------------");
                System.out.println(result);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (con != null) {
                    con.disconnect();
                }
                if (os != null) {
                    os.flush();
                    os.close();
                }
                if (in != null) {
                    in.close();
                }
            }
        }
    }
}