package com.example.mini_project1_jordan_murphy;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

//NavigationDrawerFragment.NavigationDrawerCallbacks (might need to add at some point. not sure)

public class OrderActivity extends AppCompatActivity
        implements
                    drinkOrder.OnFragmentInteractionListener,
                    foodOrder.OnFragmentInteractionListener,
                    overviewOrder.OnFragmentInteractionListener
                     {


                         Button queryButton;
                         ListView list;
                         ArrayList<String> mylist = new ArrayList<String>();
                         ArrayAdapter<String> arrayAdapter;
                         String item0, item1, item2;




                         FirebaseDatabase fbdb;
                         DatabaseReference dbrf;

                         FirebaseAuth mAuth;
                         FirebaseUser user = null;

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


        fbdb = FirebaseDatabase.getInstance();
        dbrf = fbdb.getReference();
        mAuth= FirebaseAuth.getInstance();

        queryButton=findViewById(R.id.query);
        //myTxt = findViewById(R.id.RecyclerView);


        //mAuth = FirebaseAuth.getInstance();

        //fbdb = FirebaseDatabase.getInstance();
        //dbrf = fbdb.getReference();
        //IDText = findViewById(R.id.userID);
        list = (ListView)findViewById(R.id.list);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,mylist);
        list.setAdapter(arrayAdapter);

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

                         public void queryClick(View view) {


                             Intent myIntent = new Intent(this, ImageRecognition.class);
                             startActivity(myIntent);
                             //studID = Integer.parseInt(IDText.getText().toString());
                             //studentID = ((EditText) findViewById(R.id.userID)).getText().toString();
                             //studID = Integer.parseInt(studentID);

                             //DatabaseReference gradeKey = dbrf.child("simpsons/grades/");

                             //Query query = gradeKey.orderByChild("student_id").equalTo(studID);
                             //query.addListenerForSingleValueEvent(valueEventListener);
                             //query.addValueEventListener(valueEventListener);

                             /*user = mAuth.getCurrentUser();
                             Log.v("Entered: ", "Entered");
                             //(dbrf.child("users/" + user.toString().substring(34) + "/orders")).addValueEventListener(valueEventListener);
                             (dbrf.child("users").child(user.toString().substring(34)).child("orders")).addValueEventListener(valueEventListener);
                             //(dbrf.child("users/")).addValueEventListener(valueEventListener);
                             Log.d("USER", user.toString().substring(34));*/


                         }

                         ValueEventListener valueEventListener = new ValueEventListener() {
                             @Override
                             public void onDataChange(DataSnapshot dataSnapshot) {
                                 if (dataSnapshot.exists()) {
                                     //Toast.makeText(getApplicationContext(),"listening",Toast.LENGTH_SHORT).show();
                                     mylist.clear();
                                     for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                         //order order = snapshot.getValue(order.class);
                                         /*Toast.makeText(getApplicationContext(),Integer.toString(studID) + Integer.toString(grade.student_id),Toast.LENGTH_SHORT).show();
                                         if (grade.student_id >= studID) {
                                             if (grade.student_id == 123) {
                                                 name = "Bart";
                                             } else if (grade.student_id == 404) {

                                                 name = "Ralph";
                                             } else if (grade.student_id == 456) {

                                                 name = "Milhouse";
                                             } else if (grade.student_id == 888) {

                                                 name = "Lisa";
                                             }
                                             mylist.add(name + ", " + grade.course_name + ", " + grade.grade);

                                         }*/


                                         //mylist.add(Integer.toString(i));
                                         //mylist.add(item0);

                                         String item = snapshot.child("users").getValue(String.class);
                                         //Use the dataType you are using and also use the reference of those childs inside arrays\\

                                         // Putting Data into Getter Setter \\
                                         order Order = new order();
                                         Order.setOrder(item);

                                         //Log.d("ITEM", item);

                                         mylist.add(Order.toString());
                                         //mylist.add(Order.item);



                                     }

                                     arrayAdapter.notifyDataSetChanged();
                                 }
                             }

                             @Override
                             public void onCancelled(DatabaseError databaseError) {
                                 //log error

                             }
                         };



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
