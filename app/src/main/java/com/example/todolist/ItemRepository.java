package com.example.todolist;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.todolist.RoomDataBase.Item;
import com.example.todolist.RoomDataBase.ItemDao;
import com.example.todolist.RoomDataBase.ItemDataBase;

import java.util.List;

// doing AsyncTask
public class ItemRepository {
    private ItemDao itemDao;

    public ItemRepository(Application application) {
        ItemDataBase dataBase = ItemDataBase.getInstance(application);
        itemDao = dataBase.itemDao();
        allItemsByPriorityDesc = itemDao.getAllItemsByPriorityDesc();
        allItemsByCreateDateDesc = itemDao.getAllItemsByCreateDateDesc();
        allItemsBySetDateDesc = itemDao.getAllItemsBySetDateDesc();
        allItemsInHistory = itemDao.getAllItemsInHistory();
    }

    // live data
    private LiveData<List<Item>> allItemsInHistory;

    public LiveData<List<Item>> getAllItemsInHistory() {
        return allItemsInHistory;
    }

    private LiveData<List<Item>> allItemsByPriorityDesc;

    public LiveData<List<Item>> getAllItemsByPriorityDesc() {
        return allItemsByPriorityDesc;
    }

    private LiveData<List<Item>> allItemsByCreateDateDesc;

    public LiveData<List<Item>> getAllItemsByCreateDateDesc() {
        return allItemsByCreateDateDesc;
    }

    private LiveData<List<Item>> allItemsBySetDateDesc;

    public LiveData<List<Item>> getAllItemsBySetDateDesc() {
        return allItemsBySetDateDesc;
    }


    // insert item
    public void insert(Item item) {
        new InsertItemAsyncTask(itemDao).execute(item);
    }


    private static class InsertItemAsyncTask extends AsyncTask<Item, Void, Void> {

        private ItemDao itemDao;

        private InsertItemAsyncTask(ItemDao itemUao) {
            this.itemDao = itemUao;
        }

        @Override
        protected Void doInBackground(Item... items) {
            itemDao.insert(items[0]);
            return null;
        }
    }


    // update
    public void update(Item item) {
        new UpdateItemAsyncTask(itemDao).execute(item);
    }

    private static class UpdateItemAsyncTask extends AsyncTask<Item, Void, Void> {

        private ItemDao itemDao;

        private UpdateItemAsyncTask(ItemDao itemUao) {
            this.itemDao = itemUao;
        }

        @Override
        protected Void doInBackground(Item... items) {
            itemDao.update(items[0]);
            return null;
        }
    }


    // delete item
    public void delete(Item item) {
        new DeleteItemAsyncTask(itemDao).execute(item);
    }

    private static class DeleteItemAsyncTask extends AsyncTask<Item, Void, Void> {

        private ItemDao itemDao;

        private DeleteItemAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected Void doInBackground(Item... items) {
            itemDao.delete(items[0]);
            return null;
        }
    }

    // delete all items
    public void deleteAllItems() {
        new DeleteAllItemAsyncTask(itemDao).execute();
    }

    private static class DeleteAllItemAsyncTask extends AsyncTask<Void, Void, Void> {

        private ItemDao itemDao;

        private DeleteAllItemAsyncTask(ItemDao itemDao) {
            this.itemDao = itemDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            itemDao.deleteAllItem();
            return null;
        }
    }


    // update status
    private static class UpdateStatus {
        int id;
        String status;

        UpdateStatus(int id, String status) {
            this.id = id;
            this.status = status;
        }
    }

    public void updateStatus(int id, String status) {
        UpdateStatus updateStatus = new UpdateStatus(id, status);
        new UpdateStatusAsyncTask(itemDao).execute(updateStatus);
    }

    private static class UpdateStatusAsyncTask extends AsyncTask<UpdateStatus, Void, Void> {

        private ItemDao itemDao;

        private UpdateStatusAsyncTask(ItemDao itemUao) {
            this.itemDao = itemUao;
        }

        @Override
        protected Void doInBackground(UpdateStatus... updateStatuses) {
            int id = updateStatuses[0].id;
            String status = updateStatuses[0].status;
            itemDao.updateStatus(id, status);
            return null;
        }
    }
}