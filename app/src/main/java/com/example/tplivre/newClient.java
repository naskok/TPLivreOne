package com.example.tplivre;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class newClient extends AppCompatActivity {

    TextView textView2;
    EditText editNom;
    TextView textView3;
    EditText editPrenom;
    android.support.v7.widget.AppCompatButton Rajouter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_client);
        bindViews();
        final DatabaseReference mesClients= FirebaseDatabase.getInstance().getReference("Clients");
        Rajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom=editNom.getText().toString();
                String prenom=editPrenom.getText().toString();
                String key=mesClients.push().getKey();
                Client newClient=new Client(nom,prenom,key);
                mesClients.child(key).setValue(newClient).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        finish();
                    }
                });

            }
        });



    }



    private void bindViews(){
        textView2 =  findViewById(R.id.textView2);
        editNom =  findViewById(R.id.editNom);
        textView3 =  findViewById(R.id.textView3);
        editPrenom =  findViewById(R.id.editPrenom);
        Rajouter =  findViewById(R.id.Rajouter);
    }
}
