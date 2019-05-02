package com.example.mini_project1_jordan_murphy;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link overviewOrder.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link overviewOrder#newInstance} factory method to
 * create an instance of this fragment.
 */
public class overviewOrder extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView order1;
    String word1;
    int total;

    Activity context;

    FirebaseDatabase fbdb;
    DatabaseReference dbrf;
    int dbID = 404;
    int i = 0;
    String order_id;

    FirebaseAuth mAuth;
    FirebaseUser user = null;

    private ArrayList<String> items;

    private OnFragmentInteractionListener mListener;

    private NotificationCompat.Builder notification_builder;


    public overviewOrder() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment overviewOrder.
     */
    // TODO: Rename and change types and number of parameters
    public static overviewOrder newInstance(String param1, String param2) {
        overviewOrder fragment = new overviewOrder();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fbdb = FirebaseDatabase.getInstance();
        dbrf = fbdb.getReference();
        mAuth= FirebaseAuth.getInstance();

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        NotificationManager notification_manager = (NotificationManager) getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//This portion of the code handles newer APIs
            String chanel_id = "3000";
            CharSequence name = "Channel Name";
            String description = "Chanel Description";
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel mChannel = new NotificationChannel(chanel_id, name, importance);
            mChannel.setDescription(description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.BLUE);
            notification_manager.createNotificationChannel(mChannel);
            notification_builder = new NotificationCompat.Builder(getActivity(), chanel_id); //maybe not getActivity(), was this
        } else {
            notification_builder = new NotificationCompat.Builder(getActivity()); //this code handles older APIs
        }

    }

    /*@Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        user = mAuth.getCurrentUser();

    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_overview_order, container, false);
        Button submission = (Button) view.findViewById(R.id.submit);
        Button remove = (Button) view.findViewById(R.id.removeItem);
        Button logout = (Button) view.findViewById(R.id.logout);
        order1 = (TextView)getActivity().findViewById(R.id.order1);
        items = new ArrayList<String>();

        Intent intent = getActivity().getIntent();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                user =null;
                Toast.makeText(getActivity(),"Logged Out",Toast.LENGTH_SHORT).show();
                Intent backIntent = new Intent(v.getContext(), MainActivity.class);
                backIntent.putExtra("total", total);
                getActivity().setResult(Activity.RESULT_OK,backIntent);
                getActivity().finish();
            }
        });

        submission.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent backIntent = new Intent(v.getContext(), MainActivity.class);
                //startActivity(backIntent);
                //backIntent.putExtra("answer", "1");
                MediaPlayer mp = MediaPlayer.create(getContext(),R.raw.cashregister);
                Log.v("total", Integer.toString(total));
                backIntent.putExtra("total", total);
                getActivity().setResult(Activity.RESULT_OK,backIntent);
                getActivity().finish();
                mp.start();

                user = mAuth.getCurrentUser();


                if (user != null) {
                    /*EditText courseID = findViewById(R.id.ID);
                    String pass = courseID.getText().toString();
                    courseID.getText().clear();

                    EditText courseName = findViewById(R.id.name);
                    String pass1 = courseName.getText().toString();
                    courseName.getText().clear();

                    EditText courseGrade = findViewById(R.id.grade);
                    String pass2 = courseGrade.getText().toString();
                    courseGrade.getText().clear();*/

                    Random rand = new Random();
                    order_id = String.valueOf(rand.nextInt(1000));

                    Log.d("USER", user.toString().substring(34));
                    /*DatabaseReference passID = dbrf.child("simpsons/grades/" + grade_id + "/course_id");
                    passID.setValue(Integer.parseInt(pass));

                    DatabaseReference passName = dbrf.child("simpsons/grades/" + grade_id + "/course_name");
                    passName.setValue(pass1);

                    DatabaseReference passGrade = dbrf.child("simpsons/grades/" + grade_id + "/grade");
                    passGrade.setValue(pass2);*/

                    for (int i = 0; i < items.size(); ++i) {

                        DatabaseReference passStudentID = dbrf.child("users/" + user.toString().substring(34) + "/orders/" + order_id + "/item" + i);
                        //DatabaseReference passStudentID = dbrf.child("users/" + user.toString().substring(34) + "/orders/item" + i);
                        passStudentID.setValue(items.get(i));

                    }

                }
                else {

                    Toast.makeText(getActivity(),"Something went wrong",Toast.LENGTH_SHORT).show();

                }

                //String mesg = intent.getStringExtra("key1");
                //Toast.makeText(OrderActivity.this, mesg, Toast.LENGTH_SHORT).show();

                //Build a notification with the same information, and show it.
                notification_builder.setSmallIcon(R.drawable.baseline_notification)
                        .setContentTitle("IMPORTANT NOTICE")
                        .setContentText("Your order is on it's way!!!")
                        .setAutoCancel(true);

                Notification notification = notification_builder.build();
                NotificationManager manager = (NotificationManager) getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE);
                manager.notify(1, notification);
            }
        });

        remove.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                items.remove(items.size() - 1);
                String temp = "";

                    Log.v("total before subtract", Integer.toString(total));
                    if (word1.equals("Coffee..................................................................$2")) {
                        total = total - 2;
                    }

                    else if (word1.equals("Coke.....................................................................$3")) {
                        total = total - 3;
                    }

                    else if (word1.equals("Tea.......................................................................$2")) {
                        total = total - 2;
                    }

                    else if (word1.equals("Water....................................................................$1")) {
                        total = total - 1;
                    }
                    else if (word1.equals("Burger.................................................................$10")) {
                        total = total - 10;
                    }

                    else if (word1.equals("Nachos...............................................................$11")) {
                        total = total - 11;
                    }

                    else if (word1.equals("Pasta..................................................................$14")) {
                        total = total - 14;
                    }

                    else if (word1.equals("Steak..................................................................$20")) {
                        total = total - 20;
                    }
                    Log.v("total after subtract", Integer.toString(total));
                    for (int i = 0; i < items.size(); ++i){
                        temp+=items.get(i) + "\n";
                }

                order1.setText(temp);


            }
        });

        return view;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    void setText(String word){

        word1 = word;
        order1 = (TextView)getActivity().findViewById(R.id.order1);

        items.add(word);

        String temp = "";
            //Log.v("size before add", Integer.toString(items.size()));
            //Log.v("total before add", Integer.toString(total));
            //Log.v("total before add", Integer.toString(i));
            if (word.equals("Coffee..................................................................$2")) {
                total = total + 2;
            }

            else if (word.equals("Coke.....................................................................$3")) {
                total = total + 3;
            }

            else if (word.equals("Tea.......................................................................$2")) {
                total = total + 2;
            }

            else if (word.equals("Water....................................................................$1")) {
                total = total + 1;
            }
            else if (word.equals("Burger.................................................................$10")) {
                total = total + 10;
            }

            else if (word.equals("Nachos...............................................................$11")) {
                total = total + 11;
            }

            else if (word.equals("Pasta..................................................................$14")) {
                total = total + 14;
            }

            else if (word.equals("Steak..................................................................$20")) {
                total = total + 20;
            }
            //Log.v("total after add", Integer.toString(i));
            //Log.v("size after add", Integer.toString(items.size()));
            for (int i = 0; i < items.size(); ++i){
                temp+=items.get(i) + "\n";
        }

        order1.setText(temp);

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    /*public void LogoutClick(View view) {
        mAuth.signOut();
        user =null;
        //Toast.makeText(getApplicationContext(),"Logged Out",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(context, MainActivity.class));
        //Intent backIntent = new Intent();
        //startActivity(backIntent);
        //backIntent.putExtra("answer", "1");
        //backIntent.putExtra("total", total);
        //getActivity().setResult(Activity.RESULT_OK,backIntent);
        //getActivity().finish();
    }*/
}
