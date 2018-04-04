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
        mSocket.on("connect", onNewMessage);
        mSocket.on("new message", onNewMessage);
    }

    private void attemptSend() {
        String message = "Le Thanh Hai send message !";
        if (message.isEmpty()) {
            return;
        }

        mSocket.emit("new message", message);
    }

    private Emitter.Listener onNewMessage = new Emitter.Listener() {

        @Override
        public void call(Object... args) {
            Log.e("TestSocketActivity", " mSocket "+args.toString());
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();

        mSocket.disconnect();
        mSocket.off("new message", onNewMessage);
    }
}
