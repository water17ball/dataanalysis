package cn.wp.bigdata.dataanalysis.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.*;

public class HttpUtil {

    public JSONObject doPost2(String urlstr, String data) {
        String resp = null;
        JSONObject jsonObject = null;
        try {
            URL url = new URL(urlstr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(15 * 1000);
            connection.connect();

/*            int code = connection.getResponseCode();
            if(code == 400){
                //todo retry
            }*/
            try (OutputStream os = connection.getOutputStream()) {
                os.write(data.getBytes("UTF-8"));
            }

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()))) {
                String lines;
                StringBuffer sbf = new StringBuffer();
                while ((lines = reader.readLine()) != null) {
                    lines = new String(lines.getBytes(), "utf-8");
                    sbf.append(lines);
                }
                resp = sbf.toString();

            }
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jsonObject = JSONObject.parseObject(resp);
        }
        return jsonObject;
    }

    public JSONArray doPost(String urlstr, String data) {
        String resp = null;
        JSONArray jsonarr = new JSONArray();
        try {
            URL url = new URL(urlstr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(15 * 1000);
            connection.connect();

            try (OutputStream os = connection.getOutputStream()) {
                os.write(data.getBytes("UTF-8"));
            }

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()))) {
                String lines;
                StringBuffer sbf = new StringBuffer();
                while ((lines = reader.readLine()) != null) {
                    lines = new String(lines.getBytes(), "utf-8");
                    sbf.append(lines);
                }
                resp = sbf.toString();

            }
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resp != null && resp.startsWith("{")) {
                jsonarr.add(JSONObject.parseObject(resp));
            } else {
                jsonarr = JSONArray.parseArray(resp);
            }
        }
        return jsonarr;
    }


    public String doGet(String url) throws Exception {

        URL localURL = new URL(url);

        URLConnection connection = this.openConnection(localURL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
        httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;
        //响应失败
        if (httpURLConnection.getResponseCode() >= 300) {
            throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
        }

        try {
            inputStream = httpURLConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);

            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }

        } finally {

            if (reader != null) {
                reader.close();
            }

            if (inputStreamReader != null) {
                inputStreamReader.close();
            }

            if (inputStream != null) {
                inputStream.close();
            }

        }
        return resultBuffer.toString();
    }

    private Integer proxyPort = null;
    private String proxyHost = null;

    private URLConnection openConnection(URL localURL) throws IOException {
        URLConnection connection;
        if (proxyHost != null && proxyPort != null) {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
            connection = localURL.openConnection(proxy);
        } else {
            connection = localURL.openConnection();
        }
        return connection;
    }
}
