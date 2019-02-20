package com.ncku_tainan.co2_detection;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class Switch extends AppCompatActivity {
    private ImageButton collectionbtn;
    private ImageButton mediumbtn;
    TextView text2;
    //@Override
    int collectionstatus;
    int mediumstatus;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);
        collectionbtn = (ImageButton) findViewById(R.id.collectionButton);
        mediumbtn = (ImageButton) findViewById(R.id.mediumButton);
        text2=(TextView)findViewById(R.id.text);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference DB_collection = database.getReference();
        DatabaseReference DB_medium = database.getReference();

        DB_collection.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if(dataSnapshot.getKey().equals("collection")) {
                    String m = (String) dataSnapshot.getValue();
                    if(m.equals("Y"))
                    {
                        collectionstatus=1;
                        collectionbtn.setImageResource(R.drawable.on);
                    }

                    else
                    {
                        collectionstatus=0;
                        collectionbtn.setImageResource(R.drawable.off);

                    }
                }
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                if(dataSnapshot.getKey().equals("collection")) {
                    String m = (String) dataSnapshot.getValue();
                    if(m.equals("Y"))
                    {
                        collectionstatus=1;
                        collectionbtn.setImageResource(R.drawable.on);
                    }
                    else
                    {
                        collectionstatus=0;
                        collectionbtn.setImageResource(R.drawable.off);
                    }
                };
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        DB_medium.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if(dataSnapshot.getKey().equals("medium")) {
                    String m = (String) dataSnapshot.getValue();
                    if(m.equals("Y"))
                    {
                        mediumstatus=1;
                        mediumbtn.setImageResource(R.drawable.on);

                    }
                    else
                    {
                        mediumstatus=0;
                        mediumbtn.setImageResource(R.drawable.off);

                    }
                };
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                if(dataSnapshot.getKey().equals("medium")) {
                    String m = (String) dataSnapshot.getValue();
                    if(m.equals("Y"))
                    {
                        mediumstatus=1;
                        mediumbtn.setImageResource(R.drawable.on);
                    }
                    else
                    {
                        mediumstatus=0;

                        mediumbtn.setImageResource(R.drawable.off);
                    }

                };
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
        collectionbtn.setOnClickListener(new View.OnClickListener()
        {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference DB_collection= database.getReference();
            public void onClick(View v)
            {

                if(collectionstatus==0) {
                    Map<String, Object> switchmedium = new HashMap<>();
                    switchmedium.put("collection", "Y");
                    DB_collection.updateChildren(switchmedium);
                }
                else
                {
                    Map<String, Object> switchmedium = new HashMap<>();
                    switchmedium.put("collection", "N");
                    DB_collection.updateChildren(switchmedium);

                }

            }
        });
        mediumbtn.setOnClickListener(new View.OnClickListener()
        {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference DB_medium = database.getReference();


            public void onClick(View v)
            {

                if(mediumstatus==0) {
                    Map<String, Object> switchmedium = new HashMap<>();
                    switchmedium.put("medium", "Y");
                    DB_medium.updateChildren(switchmedium);


                }
                else
                {
                    Map<String, Object> switchmedium = new HashMap<>();
                    switchmedium.put("medium", "N");
                    DB_medium.updateChildren(switchmedium);
                }

            }
        });
    }
}







