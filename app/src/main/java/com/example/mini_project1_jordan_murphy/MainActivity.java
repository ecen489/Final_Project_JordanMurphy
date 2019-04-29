package com.example.mini_project1_jordan_murphy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

TextView scoreboard;
    int totalDue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button order = (Button) findViewById(R.id.placeOrder);
        scoreboard = (TextView) findViewById(R.id.scoreboard);




        final Intent intent = new Intent(this, OrderActivity.class);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, topics);

        //order.setAdapter(adapter);
        order.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Here you should add the code you want to execute when the button is clicked
                // In our case we want to open the activity
                //Intent intent = new Intent(v.getContext(), OrderActivity.class);
                //String topic = list.getItemAtPosition(index).toString();
                //intent.putExtra("topic", topic);
                startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                //String result=data.getStringExtra("result");
                totalDue = data.getIntExtra("total", -1);
                Log.v("totalDue", Integer.toString(totalDue));
            }
            if (resultCode == RESULT_CANCELED) {
                //Write your code if there's no result
                Log.v("yes","yes");
            }

            String temp = "$";
            String total = temp + String.valueOf(totalDue);
            scoreboard.setText(total);
            // scoreboard.setText(Float.toString(score));
        }
    }
}
