package com.alvarop627.signitfootballagent.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alvarop627.signitfootballagent.R;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.HttpsCallableReference;
import com.google.firebase.functions.HttpsCallableResult;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

public class NewGameActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);

    }// fin onCreate


    private void deleteAtPath(String path) {
        Map<String, Object> data = new HashMap<>();
        data.put("path", path);

        HttpsCallableReference deleteFn =
                FirebaseFunctions.getInstance().getHttpsCallable("recursiveDelete");
        deleteFn.call(data)
                .addOnSuccessListener(new OnSuccessListener<HttpsCallableResult>() {
                    @Override
                    public void onSuccess(HttpsCallableResult httpsCallableResult) {
                        // Delete Success
                        // ...
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Delete failed
                        // ...
                    }
                });
    }


    public void playNewGame(View view) {
        final EditText txtUsername = this.findViewById(R.id.txtNewUser);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final String newUser = txtUsername.getText().toString();
        CollectionReference refUsers = db.collection("users");
        Query queryUser = refUsers.whereEqualTo("user_id", FirebaseAuth.getInstance().getCurrentUser().getUid());
        queryUser.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> data = new HashMap<>();
                                data.put("name", newUser);
                                data.put("money", 10000);
                                document.getReference().set(data, SetOptions.merge());
                                String path = "users/" + document.getId() + "/players";
                                deleteAtPath(path);

                            }
                        } else {
                            Log.d("ERROR!!", "Error getting documents: ", task.getException());
                        }
                    }
                });
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }


}

