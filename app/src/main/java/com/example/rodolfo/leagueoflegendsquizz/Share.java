package com.example.rodolfo.leagueoflegendsquizz;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

/**
 * Created by Rodolfo on 11/13/2015.
 */
public class Share extends Activity {
    CallbackManager callbackManager;
    ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        setContentView(R.layout.share);
        TextView text = (TextView) findViewById(R.id.display);
        Button restart = (Button) findViewById(R.id.restart);
        Button share = (Button) findViewById(R.id.share);
        Singleton s = Singleton.getInstance();
        text.setText("Congratulation you did " + s.points + " points");
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Share.this, Quiz.class);
                startActivity(intent);
                Singleton s = Singleton.getInstance();
                s.points = 0;
                finish();

            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Singleton s = Singleton.getInstance();
                if (ShareDialog.canShow(ShareLinkContent.class)) {
                    ShareLinkContent linkContent = new ShareLinkContent.Builder()
                            .setContentUrl(Uri.parse("https://www.facebook.com/League-of-Legends-Quiz-1479359582372898"))
                            .setContentTitle("League of Legends Quiz")
                            .setContentDescription(
                                    "Wow!! I did " + s.points + " in the league of legends quiz. How many points can you do?")
                            .build();

                    shareDialog.show(linkContent);
                    finish();

                }
            }
        });
        super.onCreate(savedInstanceState);
    }
}
