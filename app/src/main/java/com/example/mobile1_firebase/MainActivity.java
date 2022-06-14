package com.example.mobile1_firebase;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile1_firebase.databinding.ActivityMainBinding;
import com.example.mobile1_firebase.model.News;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    FirebaseFirestore db;
    CollectionReference collectionReference;
    List<News> newsList;
    NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        newsList = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        newsAdapter = new NewsAdapter(newsList);

        binding.recycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        binding.recycler.setAdapter(newsAdapter);
        binding.recycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        binding.recycler.setHasFixedSize(true);

        binding.fab.setOnClickListener(v -> {

        });

        collectionReference = db.collection("news");
        collectionReference.orderBy("title", Query.Direction.ASCENDING).addSnapshotListener((value, error) -> {
            if (error != null) {
                Log.e("Firestore", error.getMessage());
                return;
            }
            ArrayList<News> newsSnapshots = new ArrayList<>();
            for (QueryDocumentSnapshot snapshot : value) {
                News news = snapshot.toObject(News.class);
                newsSnapshots.add(news);
            }
            Log.d("Firestore","item!!");
            newsList = newsSnapshots;
            for (News news: newsList) {
                Log.d("Activity",news.getTitle());
            }
            newsAdapter.setNewsList(newsList);
            newsAdapter.notifyDataSetChanged();
            for (News news: newsAdapter.getNewsList()) {
                Log.d("Adapter",news.getTitle());
            }
//            newsAdapter.onAttachedToRecyclerView(binding.recycler);
//            binding.recycler.setAdapter(newsAdapter);

        });


    }
}