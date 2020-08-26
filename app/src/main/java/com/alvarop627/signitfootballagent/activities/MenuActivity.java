package com.alvarop627.signitfootballagent.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alvarop627.signitfootballagent.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        refresh();

    }

    public void goToClients(View view) {
        Intent intent = new Intent(this, Clientes.class);
        startActivity(intent);
    }



    private void refresh(){
        final TextView txtUsername = this.findViewById(R.id.txtUser);
        final TextView txtMoney = this.findViewById(R.id.txtMoney);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference referenceUsers = db.collection("users");
        Query queryUser = referenceUsers.whereEqualTo("user_id", FirebaseAuth.getInstance().getCurrentUser().getUid());
        queryUser.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d("OK!!", document.getId() + " => " + document.getData());
                                txtUsername.setText(document.get("name").toString());
                                txtMoney.setText(document.get("money").toString()+" €");
                            }
                        } else {
                            Log.d("ERROR!!", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }
}
