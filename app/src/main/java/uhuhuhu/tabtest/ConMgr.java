package uhuhuhu.tabtest;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

public class ConMgr {

    static public String connect(String uri, String data) throws IOException {
        CookieManager cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);

        URL url = new URL(uri);
        Proxy proxy = new Proxy(Proxy.Type.HTTP,
                new InetSocketAddress("cache.fors.ru",
                        3128));
        HttpURLConnection conn =
                (HttpURLConnection)url.openConnection(proxy);

        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded; charset: UTF-8");
        conn.setRequestProperty("Connection",
                "close");
        conn.setRequestProperty("Cookie", "XML_JSON=true");

        OutputStreamWriter wr =
                new OutputStreamWriter(conn.getOutputStream());
        wr.write(data);
        wr.flush();
        wr.close();

        InputStream is = conn.getInputStream();
        BufferedReader bufferedReader =
                new BufferedReader(
                new InputStreamReader(is));
        StringBuilder stringBuilder = new StringBuilder(
                is.available());
        String line;
        while((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }

        String result = stringBuilder.toString();
        //String result = "test";
                Log.d("", result);
        return result;
    }
}
