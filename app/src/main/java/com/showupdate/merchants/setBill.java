package com.showupdate.merchants;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class setBill extends AppCompatActivity {
    Double add;
    String s1;
Button submit2;
EditText refnum2, bill2;
TextView result1, comfirm, result3, next;
    FirebaseDatabase database;
    DatabaseReference update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_bill);
        result1 = (TextView)findViewById(R.id.rez2);
        result3 = (TextView)findViewById(R.id.rez3);
        refnum2 = (EditText)findViewById(R.id.refffnum2);
        bill2 = (EditText)findViewById(R.id.bil2);
        submit2 = (Button)findViewById(R.id.sub2);
        comfirm = (TextView)findViewById(R.id.confi2);
        next = (TextView)findViewById(R.id.nxt);




        database = FirebaseDatabase.getInstance();


          submit2.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                next.setVisibility(View.VISIBLE);
                  Toast.makeText(setBill.this," click next button to proceed",Toast.LENGTH_LONG ).show();
                  String referenci = refnum2.getText().toString();
                  String bill = bill2.getText().toString();

                  if(TextUtils.isEmpty(bill)){
                      bill2.setError("fill reference number");
                      bill2.requestFocus();
                      return;
                  }
                  if(TextUtils.isEmpty(referenci)){
                      refnum2.setError("fill in bill");
                      refnum2.requestFocus();
                      return;
                  }


                  s1 = refnum2.getText().toString();
                  update = database.getReference("MALAWI HOUSING CORPERATION");
                  Query chech = update.orderByChild("referenceNumber").equalTo(s1);
                  chech.addListenerForSingleValueEvent(new ValueEventListener() {
                      @Override
                      public void onDataChange(@NonNull DataSnapshot snapshot) {
                          if(snapshot.exists()){
                                String s2 = refnum2.getText().toString();
                                update.child(s2).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        String bill = snapshot.child("bill").getValue().toString();
                                        result1.setText(bill);


                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                          }
                          else {
                              refnum2.setError("invalid customer");
                              refnum2.requestFocus();
                              return;
                          }

                      }


                      @Override
                      public void onCancelled(@NonNull DatabaseError error) {

                      }
                  });
              }
          });
             next.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Toast.makeText(setBill.this," the bill of entered customer will increase, click confirm button to complete the process",Toast.LENGTH_LONG ).show();
                     comfirm.setVisibility(View.VISIBLE);
                     next.setVisibility(View.INVISIBLE);
                     Double bil1 = Double.parseDouble(bill2.getText().toString());
                     Double add1 = Double.parseDouble(result1.getText().toString());
                     Double add = add1 + bil1;
                     result3.setText(String.valueOf(add));

                 }
             });
          comfirm.setOnClickListener(new View.OnClickListener() {

              @Override
              public void onClick(View v) {
                  comfirm.setVisibility(View.INVISIBLE);
                  String s3 = refnum2.getText().toString();
                  update = database.getReference("MALAWI HOUSING CORPERATION");
                  update.child(s3).addValueEventListener(new ValueEventListener() {

                      @Override
                      public void onDataChange(@NonNull DataSnapshot snapshot) {
                          Double add2 = Double.parseDouble(result3.getText().toString());
                          Map<String, Object> hashMap = new HashMap<>();
                          hashMap.put("bill", add2);
                          snapshot.getRef().updateChildren(hashMap);
                      }

                      @Override
                      public void onCancelled(@NonNull DatabaseError error) {

                      }
                  });
                  refnum2.setText("");
                  bill2.setText("");

              }
          });


    }
}