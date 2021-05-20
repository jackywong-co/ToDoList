package com.example.todolist.RoomDataBase;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


// ORM create database
@Entity(tableName = "item_table")
public class Item {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "TaskName")
    private String taskName;
    @ColumnInfo(name = "CreatedAt")
    private String createdAt;
    @ColumnInfo(name = "CreatedOn")
    private String createdOn;
    @ColumnInfo(name = "Date")
    private String date;
    @ColumnInfo(name = "Priority")
    private int priority;
    @ColumnInfo(name = "Status")
    private String status;

    public Item(String taskName, String createdAt, String createdOn, String date, int priority, String status) {
        this.taskName = taskName;
        this.createdAt = createdAt;
        this.createdOn = createdOn;
        this.date = date;
        this.priority = priority;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
