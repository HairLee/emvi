package com.elcom.com.ambitionmvvm.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.elcom.com.ambitionmvvm.R;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

/**
 * Created by Hailpt on 4/4/2018.
 */
public class TestSocketActivity extends AppCompatActivity {

    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://192.168.6.99:3000");
        } catch (URISyntaxException e) {

        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_socket_layout);

        Button btn_show = (Button)findViewById(R.id.btn_show);
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSend();
            }
        });

        mSocket.connect();
//        mSocket.on("connect", onNewMessage);
        mSocket.on("notify everyone", onNewMessage);
    }

    private void attemptSend() {

        JSONObject student1 = new JSONObject();
        try {
            student1.put("comment", "Hailpt sends message");
            student1.put("user", "123");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        mSocket.emit("comment added", student1);
    }

    private Emitter.Listener onNewMessage = new Emitter.Listener() {

        @Override
        public void call(final Object... args) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];



                    String username = "HIHI";
                    String message;
                    try {
                        username = data.getString("comment");
                        message = data.getString("user");
                    } catch (JSONException e) {
                        return;
                    }

                    Log.e("TestSocketActivity", " mSocket "+username);
                }
            });
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSocket.disconnect();
        mSocket.off("notify everyone", onNewMessage);
    }
}
