package com.example.todolist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todolist.RoomDataBase.Item;

import java.util.List;



// get database data through ViewModel
public class ItemViewModel extends AndroidViewModel {
    private ItemRepository itemRepository;
    private LiveData<List<Item>> allItems;

    public ItemViewModel(@NonNull Application application) {
        super(application);
        itemRepository = new ItemRepository(application);
        allItems = itemRepository.getAllItems();
    }

    public void insert(Item item) {
        itemRepository.insert(item);
    }

    public void update(Item item) {
        itemRepository.update(item);
    }

    public void delete(Item item) {
        itemRepository.delete(item);
    }

    public void deleteAllItems() {
        itemRepository.deleteAllItems();
    }

    public LiveData<List<Item>> getAllItems() {
        return allItems;
    }

}
