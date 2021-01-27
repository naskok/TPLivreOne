package com.example.tplivre;

import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RendreLivre extends AppCompatActivity {


    /** Android Views **/
    Spinner spinClient;
    Spinner spinLivre;
    String IDClient;
     LivreAdapter adapter;
    ArrayList<String> listeKey;
    RecyclerView monRecyle;
    ArrayList<Livre> malistelivre;

    DatabaseReference mesHalls,mesLivres;

    RecyclerView.LayoutManager mLayoutManager;
    android.support.v7.widget.AppCompatButton btnLoc;
    /** Android Views **/

    /**
     * Binds XML views
     * Call this function after setContentView() in onCreate().
     **/
    private void bindViews(){
        spinClient =  findViewById(R.id.spinClient);

        btnLoc =  findViewById(R.id.btnLoc);
        monRecyle=findViewById(R.id.monRecycler);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rendre_livre);
        bindViews();
        malistelivre=new ArrayList<>();
        listeKey=new ArrayList<>();
        DatabaseReference mesClients= FirebaseDatabase.getInstance().getReference("Clients");
        final ArrayList<String> listeClients=new ArrayList<>();
        final ArrayAdapter<String> adapterClient=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,listeClients);
        spinClient.setAdapter(adapterClient);
    mesHalls= FirebaseDatabase.getInstance().getReference("Halls");

      mesLivres= FirebaseDatabase.getInstance().getReference("Livres");






        mesClients.orderByChild("nom").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren())
                {
                    Client tempClient=data.getValue(Client.class);

                    listeClients.add(tempClient.getNom().toUpperCase()
                            +" "+tempClient.getPrenom());
                    listeKey.add(tempClient.getId());

                }
                adapterClient.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        adapter=new LivreAdapter(this,malistelivre);
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){

            mLayoutManager = new GridLayoutManager(this, 3);
        }
        else{
            mLayoutManager = new GridLayoutManager(this, 5);
        }


        monRecyle.setLayoutManager(mLayoutManager);
        monRecyle.setAdapter(adapter);

        spinClient.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                IDClient=listeKey.get(position);
                ShowLivre();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Livre> listesloues = adapter.getSelectedItems();
                for (Livre livre : listesloues) {
                    mesHalls.child(livre.getHall()).child(livre.getId()).setValue(true);
                    mesLivres.child(livre.getId()).child("loc").setValue(false).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            finish();
                        }
                    });


                }


            }
        });
    }
public void ShowLivre()
{
    mesLivres.orderByChild("loc").equalTo(IDClient).addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for (DataSnapshot data : dataSnapshot.getChildren())
            {
                malistelivre.add(data.getValue(Livre.class));

            }
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });

}
}

