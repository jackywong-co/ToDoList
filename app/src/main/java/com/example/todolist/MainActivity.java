package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.RoomDataBase.Item;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_ITEM_REQUEST = 1;
    private ItemViewModel itemViewModel;

    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction;
    DetailsFragment detailsFragment = new DetailsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // using toolbar instead of app bar
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.app_name));

        // onClick floating action button
        FloatingActionButton FloatingActionButton = findViewById(R.id.fab);
        FloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivityForResult(intent, ADD_ITEM_REQUEST);
            }
        });

        setUpRecyclerview();
        setUpNavigationView();

    }


    public void setUpRecyclerview() {
        // recyclerview
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        ItemAdapter adapter = new ItemAdapter();
        recyclerView.setAdapter(adapter);

        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        Spinner spinnerSortBy = findViewById(R.id.spinner);
        spinnerSortBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //set spinner choice selected

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String choice = spinnerSortBy.getSelectedItem().toString(); // get selected item to string and put in choice variable
//                Toast.makeText(MainActivity.this, choice, Toast.LENGTH_LONG).show();
                switch (choice) {
                    case "Priority":
                        // sort by priority
                        itemViewModel.getAllItemsByPriorityDesc().observe(MainActivity.this, new Observer<List<Item>>() {
                            @Override
                            public void onChanged(@NonNull List<Item> items) {
                                // update recyclerview
                                adapter.setItems(items);
                            }
                        });

                        break;
                    case "Coming":
                        // sort by set date
                        itemViewModel.getAllItemsBySetDateDesc().observe(MainActivity.this, new Observer<List<Item>>() {
                            @Override
                            public void onChanged(@NonNull List<Item> items) {
                                // update recyclerview
                                adapter.setItems(items);
                            }
                        });
                        break;
                    // sort by created date
                    case "created date":
                        itemViewModel.getAllItemsByCreateDateDesc().observe(MainActivity.this, new Observer<List<Item>>() {
                            @Override
                            public void onChanged(@NonNull List<Item> items) {
                                // update recyclerview
                                adapter.setItems(items);
                            }
                        });
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // ignore
            }
        });
        // create a touch helper
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) { // swipe left and swipe right
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // update item status to 0
                itemViewModel.updateStatus(adapter.getItemAt(viewHolder.getAdapterPosition()).getId(), "0");
                Toast.makeText(MainActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        // get OnItemClickListener from adapter
        adapter.setOnItemClickListener(new ItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Item item) {

                fragmentTransaction = fragmentManager.beginTransaction();
                String TaskName = item.getTaskName();
                String CreatedAt = item.getCreatedAt();
                String CreatedOn = item.getCreatedOn();
                String Date = item.getDate();
                String Priority = String.valueOf(item.getPriority());
                Bundle bundle = new Bundle();
                // store data to bundle
                bundle.putString("TaskName", TaskName);
                bundle.putString("CreatedAt", CreatedAt);
                bundle.putString("CreatedOn", CreatedOn);
                bundle.putString("Date", Date);
                bundle.putString("Priority", Priority);

                detailsFragment.setArguments(bundle); // pass bundle to DetailsFragment
                detailsFragment.show(getSupportFragmentManager(), "Task");
            }
        });

    }

    // get data from add task activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_ITEM_REQUEST && resultCode == RESULT_OK) {
            // get data from add task activity
            String taskname = data.getStringExtra(AddTaskActivity.EXTRA_NAME);
            String createdat = data.getStringExtra(AddTaskActivity.EXTRA_CREATED_AT);
            String createdon = data.getStringExtra(AddTaskActivity.EXTRA_CREATED_ON);
            String date = data.getStringExtra(AddTaskActivity.EXTRA_DATE);
            int priority = data.getIntExtra(AddTaskActivity.EXTRA_PRIORITY, 1);
            String status = data.getStringExtra(AddTaskActivity.EXTRA_STATUS);
            // pack a item
            Item item = new Item(taskname, createdon, createdat, date, priority, status);
            itemViewModel.insert(item); //insert in database
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Not Saved", Toast.LENGTH_SHORT).show();
        }
    }

    // Navigation
    public void setUpNavigationView() {
        // set toolbar
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.app_name));

        // NavigationView
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer);
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        // set listener when selected item
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.EA:
                        Intent intent = new Intent(MainActivity.this, EaActivity.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });
    }

    // menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.history:
                // to History activity
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);

    }
}