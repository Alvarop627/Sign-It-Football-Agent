package com.alvarop627.signitfootballagent.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.alvarop627.signitfootballagent.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Clientes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference reference = db.collection("users").document("92ZNdZziYDLmsj1VCoUe");
        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("USUARIO 1", "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d("NO EXISTE EL USUARIO", "No such document");
                    }
                } else {
                    Log.d("ERROR", "get failed with ", task.getException());
                }
            }
        });

        System.out.println("/////////////////////////////////////////////////////////////////////////////");
        System.out.println("------------------------------------------------------------------------------");
    }
}
