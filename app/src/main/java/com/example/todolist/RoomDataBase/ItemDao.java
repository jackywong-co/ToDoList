package com.example.todolist.RoomDataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

// sql
@Dao
public interface ItemDao {
    @Insert
    void insert(Item item);

    @Update
    void update(Item item);


    @Delete
    void delete(Item item);

    @Query("UPDATE item_table SET Status = :status WHERE id = :id")
    void updateStatus(int id, String status);

    // clear all items
    @Query("DELETE FROM item_table")
    void deleteAllItem();


    // get all items

    @Query("SELECT * FROM item_table ORDER BY Priority DESC,Date DESC, CreatedOn DESC")
        // livedata is used to display automatically, if table have any change.
    LiveData<List<Item>> getAllItemsInHistory();
    @Query("SELECT * FROM item_table WHERE status = '1' ORDER BY Priority DESC,Date DESC, CreatedOn DESC")
        // livedata is used to display automatically, if table have any change.
    LiveData<List<Item>> getAllItemsByPriorityDesc();

    @Query("SELECT * FROM item_table   WHERE status = '1'  ORDER BY CreatedOn DESC, CreatedAt DESC")
    LiveData<List<Item>> getAllItemsByCreateDateDesc();

    @Query("SELECT * FROM item_table   WHERE status = '1'  ORDER BY Date DESC, Priority DESC")
    LiveData<List<Item>> getAllItemsBySetDateDesc();

}
