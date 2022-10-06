package com;

import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class API_TEST_JAVA {

    public static void main(String[] args) throws Exception {

        boolean test = false;

        String str = "";
        HttpURLConnection con = null;
        OutputStream os = null;
        BufferedReader in = null;

        if( test ) {
            try {
                String param = "a=a&b=b";
                String urlString = "";

                URL url = new URL(urlString);
                con = (HttpURLConnection) url.openConnection();
                con.setDoInput(true);
                con.setDoOutput(true);
                con.setRequestMethod("POST");
                con.setRequestProperty("Accept-Charset", "UTF-8");
                con.setRequestProperty("Content-Type", "application/json");

                con.setConnectTimeout(20000);
                con.setReadTimeout(20000);
                os = con.getOutputStream();
                os.write(param.getBytes("UTF-8"));
                String buffer;
                System.out.println(con.getResponseCode());

                in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

                while ((buffer = in.readLine()) != null) {
                    str += buffer;
                }

                System.out.println(str);
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