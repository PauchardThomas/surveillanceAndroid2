package iia.com.surveillanceproject;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;
import iia.com.surveillanceproject.com.UserWSAdapter;
import iia.com.surveillanceproject.com.asymetric.Hash;

public class MainActivity extends AppCompatActivity {

    EditText edLogin;
    EditText edPassword;
    String login = "";
    String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * Set view
         */
        setContentView(R.layout.activity_main);

        /**
         * Get View items
         */
        edLogin = (EditText) this.findViewById(R.id.login);
        edPassword = (EditText) this.findViewById(R.id.password);
        Button btCnx = (Button) this.findViewById(R.id.Cnx);

        /**
         * Click on connexion
         */
        btCnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login = edLogin.getText().toString();
                password = edPassword.getText().toString();
                /**
                 * Hash password SHA256
                 */
                password = Hash.hashContent(password);

                String jsonEncrypted = null;

                try {
                    /**
                     * Encrypt JSON
                     */
                    jsonEncrypted = Test.sendData(login, password);
                } catch (Exception e) {
                    e.printStackTrace();
                }


                /**
                 * Send encrypted JSON to server
                 */
                try {
                    UserWSAdapter.post(MainActivity.this, jsonEncrypted, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }
        });


    }


}
