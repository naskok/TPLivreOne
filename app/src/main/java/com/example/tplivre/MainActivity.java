package com.example.tplivre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button btnRajouter, btnClient, btnLoc, btnRendre, btnStat, btnInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        btnRajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),RajoutLivre.class);
                startActivity(intent);            }
        });

    btnClient.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(getApplicationContext(),newClient.class);
            startActivity(intent);

        }
    });


        btnLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Location.class);
                startActivity(intent);

            }
        });
        btnRendre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),RendreLivre.class);
                startActivity(intent);

            }
        });
        btnStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),StatActivity.class);
                startActivity(intent);

            }
        });
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent=new Intent(MainActivity.this, InfoActivity.class);
                    startActivity(intent);
            }
        });


    }
    

    public void Init()
    {
        btnStat=findViewById(R.id.btnStat);
        btnInfo=findViewById(R.id.btnInfo);
        btnRendre=findViewById(R.id.btnRendre);
        btnRajouter=findViewById(R.id.btnRaj);
        btnClient=findViewById(R.id.btnClient);
        btnLoc=findViewById(R.id.btnLouer);
    }
}
