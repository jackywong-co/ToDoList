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

    // clear all items
    @Query("DELETE FROM ItemTable")
    void deleteAllItem();

    // get all items
    @Query("SELECT * FROM ItemTable ORDER BY date DESC")
    // if item_table have any change, display automatically
    LiveData<List<Item>> getAllItemsbyDate();
}
