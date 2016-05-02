package iia.com.surveillanceproject.com;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by Thom' on 01/05/2016.
 */
public class UserWSAdapter {

    private static final String URL = "http://ip/adresse";

    private static AsyncHttpClient client = new AsyncHttpClient();


    public static void post(Context context,String json ,AsyncHttpResponseHandler responseHandler)
            throws JSONException, UnsupportedEncodingException {

        StringEntity entity = new StringEntity(json);
        client.post(context,URL,entity,"application/json",responseHandler);
    }
}
