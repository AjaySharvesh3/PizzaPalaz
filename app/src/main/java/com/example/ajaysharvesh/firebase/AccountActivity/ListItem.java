package com.example.ajaysharvesh.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ajaysharvesh.firebase.AccountActivity.Veggies;

import java.util.List;

public class ListItem extends AppCompatActivity {

    private Button b1, b2, b3, b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);

        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent button1 = new Intent(ListItem.this, Veggies.class);
                startActivity(button1);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent button2 = new Intent(ListItem.this, Veggies.class);
                startActivity(button2);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent button3 = new Intent(ListItem.this, Sides.class);
                startActivity(button3);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent button4 = new Intent(ListItem.this, Drinks.class);
                startActivity(button4);
            }
        });
    }
}
