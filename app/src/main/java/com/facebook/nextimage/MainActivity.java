package com.facebook.nextimage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Context context = MainActivity.this;
    private RecyclerViewAdapter adapter;

    private FirebaseFirestore mDb;
    private RecyclerView rv;
    private ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");

        mDb = FirebaseFirestore.getInstance();

        Log.d(TAG, "onCreate: gettingInstence");

        rv = findViewById(R.id.recyclerView);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(context));
        adapter = new RecyclerViewAdapter(context, list);
        rv.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        list.clear();
        mDb.collection("images")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            Log.d(TAG, "onComplete: ImageTask: success" + task.getResult());

                            for (QueryDocumentSnapshot snapshot: task.getResult()) {

                                Log.d(TAG, "onComplete: " + snapshot.getString("image"));

                                list.add(snapshot.getString("image"));
                                Log.d(TAG, "onComplete: notifying adapter" +
                                        "");
                                adapter.notifyDataSetChanged();

                            }

                        }
                    }
                });

    }
}
