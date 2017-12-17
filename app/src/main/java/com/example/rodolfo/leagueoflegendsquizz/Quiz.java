package com.example.rodolfo.leagueoflegendsquizz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Rodolfo on 11/9/2015.
 */


public class Quiz extends Activity {

    CallbackManager callbackManager;
    ShareDialog shareDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizz);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        final  TextView head =  (TextView) findViewById(R.id.head);
        final TextView q1 = (TextView) findViewById(R.id.q1);
        final TextView q2 = (TextView) findViewById(R.id.q2);
        final TextView q3 = (TextView) findViewById(R.id.q3);
        final TextView q4 = (TextView) findViewById(R.id.q4);


        ArrayList<String> strings = fill();
        head.setText(strings.get(0));
        final String[] comp = new String[]{fillScreen(q1, q2, q3, q4, strings)};

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView temp = (TextView) v;
                String tempString = temp.getText() + "";
                if (tempString.equals(comp[0])) {
                    Intent intent = new Intent(Quiz.this,Quiz.class);
                    startActivity(intent);
                    Singleton s = Singleton.getInstance();
                    s.points++;
                    finish();
                }else{
                    Intent intent = new Intent(Quiz.this,Share.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

        q1.setOnClickListener(onClickListener);
        q2.setOnClickListener(onClickListener);
        q3.setOnClickListener(onClickListener);
        q4.setOnClickListener(onClickListener);

    }

    private ArrayList<String> fill(){
        ArrayList<String> rtn = new ArrayList<>();
        Singleton s = Singleton.getInstance();
        ArrayList<Champions> array = s.arrayList;
        Random random = new Random();
        int randomInt = random.nextInt(4);
        int randomChamp = random.nextInt(array.size());

        switch (randomInt){
            case 0:
                rtn.add("Which skills is the " + array.get(randomChamp).getName() + "'s Q?" );
                rtn.add(array.get(randomChamp).getSkills().get(0));
                rtn.add(array.get(random.nextInt(array.size())).getSkills().get(random.nextInt(4)));
                rtn.add(array.get(random.nextInt(array.size())).getSkills().get(random.nextInt(4)));
                rtn.add(array.get(random.nextInt(array.size())).getSkills().get(random.nextInt(4)));
                break;
            case 1:
                rtn.add("Which skills is the " + array.get(randomChamp).getName() + "'s W?" );
                rtn.add(array.get(randomChamp).getSkills().get(1));
                rtn.add(array.get(random.nextInt(array.size())).getSkills().get(random.nextInt(4)));
                rtn.add(array.get(random.nextInt(array.size())).getSkills().get(random.nextInt(4)));
                rtn.add(array.get(random.nextInt(array.size())).getSkills().get(random.nextInt(4)));
                break;
            case 2:
                rtn.add("Which skills is the " + array.get(randomChamp).getName() + "'s E?" );
                rtn.add(array.get(randomChamp).getSkills().get(2));
                rtn.add(array.get(random.nextInt(array.size())).getSkills().get(random.nextInt(4)));
                rtn.add(array.get(random.nextInt(array.size())).getSkills().get(random.nextInt(4)));
                rtn.add(array.get(random.nextInt(array.size())).getSkills().get(random.nextInt(4)));
                break;
            case 3:
                rtn.add("Which skills is the " + array.get(randomChamp).getName() + "'s R?" );
                rtn.add(array.get(randomChamp).getSkills().get(3));
                rtn.add(array.get(random.nextInt(array.size())).getSkills().get(random.nextInt(4)));
                rtn.add(array.get(random.nextInt(array.size())).getSkills().get(random.nextInt(4)));
                rtn.add(array.get(random.nextInt(array.size())).getSkills().get(random.nextInt(4)));
                break;
        }
        return rtn;
    }

    private String fillScreen(TextView q1,TextView q2, TextView q3, TextView q4, ArrayList<String> strings){
        Random random = new Random();
        switch (random.nextInt(4)){
            case 0:
                q1.setText(strings.get(1));
                q2.setText(strings.get(2));
                q3.setText(strings.get(3));
                q4.setText(strings.get(4));
                break;
            case 1:
                q1.setText(strings.get(2));
                q2.setText(strings.get(1));
                q3.setText(strings.get(3));
                q4.setText(strings.get(4));
                break;
            case 2:
                q1.setText(strings.get(3));
                q2.setText(strings.get(2));
                q3.setText(strings.get(1));
                q4.setText(strings.get(4));
                break;
            case 3:
                q1.setText(strings.get(4));
                q2.setText(strings.get(2));
                q3.setText(strings.get(3));
                q4.setText(strings.get(1));
                break;
        }
        return strings.get(1);
    }
}
