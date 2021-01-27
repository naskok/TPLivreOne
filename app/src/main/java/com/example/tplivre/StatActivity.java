package com.example.tplivre;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class StatActivity extends AppCompatActivity {
DatabaseReference mesHalls;
    PieChartView p1,p2,p3,pALL;
    int cptA=0,cptB=0,cptC=0, locA=0,locB=0,locC=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);

        mesHalls= FirebaseDatabase.getInstance().getReference("Halls");
        p1=findViewById(R.id.pieChartA);
        p2=findViewById(R.id.pieChartB);
        p3=findViewById(R.id.pieChartC);
        pALL=findViewById(R.id.pieChartALL);
        mesHalls.child("Hall A").orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data:dataSnapshot.getChildren())
                {
                    cptA=cptA+1;
                    if (!(data.getValue().toString().equals("true"))) locA=locA+1;
                    Log.i("cmpA"," "+cptA+" "+locA);

                }
                showDataA();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mesHalls.child("Hall B").orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data:dataSnapshot.getChildren())
                {
                    cptB=cptB+1;
                    if (!(data.getValue().toString().equals("true"))) locB=locB+1;
                    Log.i("cmpB"," "+cptB+" "+locB);

                }
                showDataB();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mesHalls.child("Hall C").orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data:dataSnapshot.getChildren())
                {
                    cptC=cptC+1;
                    if (!(data.getValue().toString().equals("true"))) locC=locC+1;
                    Log.i("cmpC"," "+cptC+" "+locC);

                }
                showDataC();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });








    }

    public void showDataA()
    {
        ArrayList<SliceValue> pieDataA = new ArrayList<>();
        if (locA!=0)
        pieDataA.add(new SliceValue(locA, Color.RED).setLabel("Loués"));
        if ((cptA-locA)!=0)
        pieDataA.add(new SliceValue(cptA-locA, Color.BLUE).setLabel("Disponible"));
        PieChartData pieChartDataA = new PieChartData(pieDataA);
        pieChartDataA.setHasLabels(true);
        p1.setPieChartData(pieChartDataA);
    }

    public void showDataB()
    {
        ArrayList<SliceValue> pieDataB = new ArrayList<>();



        if (locB!=0)
        pieDataB.add(new SliceValue(locB, Color.RED).setLabel("Loués"));
        if ((cptB-locB)!=0)
        pieDataB.add(new SliceValue(cptB-locB, Color.BLUE).setLabel("Disponible"));
        PieChartData pieChartDataB = new PieChartData(pieDataB);
        pieChartDataB.setHasLabels(true);
        p2.setPieChartData(pieChartDataB);
    }
    public void showDataC()
    {
        ArrayList<SliceValue> pieDataC = new ArrayList<>();
        if (locC!=0)
        pieDataC.add(new SliceValue(locC, Color.RED).setLabel("Loués"));
        if ((cptC-locC)!=0)
        pieDataC.add(new SliceValue(cptC-locC, Color.BLUE).setLabel("Disponible"));
        PieChartData pieChartDataC = new PieChartData(pieDataC);
        pieChartDataC.setHasLabels(true);
        p3.setPieChartData(pieChartDataC);

        ArrayList<SliceValue> pieDataALL = new ArrayList<>();
        if ((locC+locA+locB)!=0)
        pieDataALL.add(new SliceValue(locC+locA+locB, Color.RED).setLabel("Loués"));
        if ((cptC+cptA+cptB-locC-locA-locB)!=0)
        pieDataALL.add(new SliceValue(cptC+cptA+cptB-locC-locA-locB, Color.BLUE).setLabel("Disponible"));
        PieChartData pieChartDataALL = new PieChartData(pieDataALL);
        pieChartDataALL.setHasLabels(true);
        pALL.setPieChartData(pieChartDataALL);




    }

}
