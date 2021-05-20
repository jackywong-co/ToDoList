package com.example.todolist;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.RoomDataBase.Item;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private ItemViewModel itemViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // link layout
        setContentView(R.layout.activity_history);
        setUpRecyclerview();
        // set title
        setTitle("History");
    }

    public void setUpRecyclerview() {
        // recyclerview
        RecyclerView recyclerView = findViewById(R.id.recycler_view); // link recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        com.example.todolist.HistoryAdaptper adapter = new com.example.todolist.HistoryAdaptper(); // create a history adapter object
        recyclerView.setAdapter(adapter); // set adapter

        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        itemViewModel.getAllItemsInHistory().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(@NonNull List<Item> items) {
                // update recyclerview
                adapter.setItems(items);
            }
        });

    }

    // menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.history_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_items:
                // delete all items
                itemViewModel.deleteAllItems();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }
}