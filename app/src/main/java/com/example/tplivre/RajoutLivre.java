package com.example.tplivre;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RajoutLivre extends AppCompatActivity {
    TextView textTitre;
    EditText editTitre;
    TextView textAuteur;
    EditText editAuteur;
    TextView textPages;
    EditText editPages;
    TextView textHall;
    Spinner spinHall;
    FirebaseDatabase database;
    Button Save;
    String monHall;
    android.support.v7.widget.AppCompatRatingBar rating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rajout_livre);
        bindViews();
        final ArrayList<String> listeHalls=new ArrayList<>();
        listeHalls.add("Hall A");
        listeHalls.add("Hall B");
        listeHalls.add("Hall C");
        ArrayAdapter<String> spinneradapter=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,listeHalls);
        spinHall.setAdapter(spinneradapter);
        spinHall.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                               @Override
                                               public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                  monHall=listeHalls.get(position);
                                               }

                                               @Override
                                               public void onNothingSelected(AdapterView<?> parent) {

                                               }
                                           });


// Write a message to the database
                database = FirebaseDatabase.getInstance();
        final DatabaseReference mesLivres = database.getReference("Livres");

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titre=editTitre.getText().toString();
                String auteur=editAuteur.getText().toString();
                int nbrating=(int) rating.getRating();
                int nbpages=Integer.parseInt(editPages.getText().toString());

                String key=mesLivres.push().getKey();
                Livre newLivres=new Livre(titre,auteur,nbpages,monHall,nbrating,key);

                Map<String,Object> mapLivre=new HashMap<>();


                mapLivre.put("/Livres/"+key,newLivres);
                mapLivre.put("/Halls/"+monHall+"/"+key,true);
                mapLivre.put("/LivresDispo/"+key,true);

                DatabaseReference mabase=database.getReference();


                DatabaseReference mesHall = database.getReference("Halls").child(monHall);

                mabase.updateChildren(mapLivre).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        finish();
                    }
                });

            }
        });




    }

    private void bindViews(){
        textTitre =  findViewById(R.id.textTitre);
        editTitre =  findViewById(R.id.editTitre);
        textAuteur =  findViewById(R.id.textAuteur);
        editAuteur =  findViewById(R.id.editAuteur);
        textPages =  findViewById(R.id.textPages);
        editPages =  findViewById(R.id.editPages);
        textHall =  findViewById(R.id.textHall);
        spinHall =  findViewById(R.id.spinHall);
        rating =  findViewById(R.id.rating);
        Save =  findViewById(R.id.Save);
    }
}
