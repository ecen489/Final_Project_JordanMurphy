package com.example.mini_project1_jordan_murphy;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

//NavigationDrawerFragment.NavigationDrawerCallbacks (might need to add at some point. not sure)

public class OrderActivity extends AppCompatActivity
        implements
                    drinkOrder.OnFragmentInteractionListener,
                    foodOrder.OnFragmentInteractionListener,
                    overviewOrder.OnFragmentInteractionListener
                     {

    @Override
    public void onFragmentInteraction(Uri uri){

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        Log.v("reached","on create");

        /*Button submission = (Button) findViewById(R.id.submit);

        Intent intent = getIntent();

        submission.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent backIntent = new Intent();
                //startActivity(backIntent);
                //backIntent.putExtra("answer", "1");
                setResult(Activity.RESULT_CANCELED,backIntent);
                finish();
            }
        });*/

        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }



    /*public void onClick(View view) {
    Log.v("reached","on click in activity");
        ImageView image = (ImageView) findViewById(R.id.imageView);
        boolean checked = ((RadioButton) view).isChecked();
        Log.v("reached","on click");

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.coffee:
                if (checked)
                    Log.v("coffee image","is set");
                image.setImageResource(R.drawable.coffee);

                break;
            case R.id.coke:
                if (checked)
                    Log.v("coke image","is set");
                image.setImageResource(R.drawable.coke);

                break;
            case R.id.tea:
                if (checked)

                    image.setImageResource(R.drawable.tea);


                break;
            case R.id.water:
                if (checked)

                    image.setImageResource(R.drawable.water);


                break;
        }
    }*/

}
