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
    private LiveData<List<Item>> allItemsByPriorityDesc;
    private LiveData<List<Item>> allItemsByCreateDateDesc;
    private LiveData<List<Item>> allItemsBySetDateDesc;
    private LiveData<List<Item>> allItemsInHistory;


    public ItemViewModel(@NonNull Application application) {
        super(application);
        itemRepository = new ItemRepository(application);
        allItemsByPriorityDesc = itemRepository.getAllItemsByPriorityDesc();
        allItemsByCreateDateDesc = itemRepository.getAllItemsByCreateDateDesc();
        allItemsBySetDateDesc = itemRepository.getAllItemsBySetDateDesc();
        allItemsInHistory = itemRepository.getAllItemsInHistory();
    }

    public void insert(Item item) {
        itemRepository.insert(item);
    }

    public void update(Item item) {
        itemRepository.update(item);
    }

    public void updateStatus(int id, String status) {
        itemRepository.updateStatus(id, status);
    }

    public void delete(Item item) {
        itemRepository.delete(item);
    }

    public void deleteAllItems() {
        itemRepository.deleteAllItems();
    }

    public LiveData<List<Item>> getAllItemsInHistory() {
        return allItemsInHistory;
    }

    public LiveData<List<Item>> getAllItemsByPriorityDesc() {
        return allItemsByPriorityDesc;
    }

    public LiveData<List<Item>> getAllItemsByCreateDateDesc() {
        return allItemsByCreateDateDesc;
    }

    public LiveData<List<Item>> getAllItemsBySetDateDesc() {
        return allItemsBySetDateDesc;
    }

}
