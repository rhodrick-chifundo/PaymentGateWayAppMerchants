package com.showupdate.merchants;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

;import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MalawiHousingCorperation extends AppCompatActivity {
    EditText name1, reference, bil;
    TextView submit , newBill;
     FirebaseDatabase database;
    DatabaseReference add;
    ProgressBar prgba;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_malawi_housing_corperation);

        name1 = (EditText)findViewById(R.id.name);
        reference = (EditText)findViewById(R.id.refNum);
        bil = (EditText)findViewById(R.id.bill);
        submit = (TextView)findViewById(R.id.sub1);
        prgba = (ProgressBar)findViewById(R.id.pBar);
        newBill = (TextView)findViewById(R.id.setbil);
        database = FirebaseDatabase.getInstance();
       prgba.setVisibility(View.INVISIBLE);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                prgba.setVisibility(View.VISIBLE);
                String name = name1.getText().toString();
                String referenceNumber = reference.getText().toString();
                String bill = bil.getText().toString();


                if (TextUtils.isEmpty(referenceNumber)) {
                    prgba.setVisibility(View.GONE);
                    reference.setError("enter reference number");
                    reference.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(name)) {
                    prgba.setVisibility(View.GONE);
                    name1.setError("enter customer name");
                    name1.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(bill)) {
                    prgba.setVisibility(View.GONE);
                    bil.setError("enter customer bill");
                    bil.requestFocus();
                    return;
                }

                add = database.getReference("MALAWI HOUSING CORPERATION");
                customerz customerz1 = new customerz(bill, name, referenceNumber);
                add.child(referenceNumber).setValue(customerz1);
                 add.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                     @Override
                     public void onComplete(@NonNull Task<DataSnapshot> task) {
                         if(task.isSuccessful()){
                             Toast.makeText(MalawiHousingCorperation.this,"operation successful! you can add more customers or leave", Toast.LENGTH_LONG).show();
                             prgba.setVisibility(View.GONE);
                             name1.setText("");
                             bil.setText("");
                             reference.setText("");
                         }
                     }
                 });
            }
        });

        newBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MalawiHousingCorperation.this, setBill.class);
                startActivity(intent);
            }
        });



    }
}