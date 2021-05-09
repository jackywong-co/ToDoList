package com.example.todolist.RoomDataBase;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


// ORM create database
@Entity(tableName = "ItemTable")
public class Item {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int Id;
    @ColumnInfo(name = "TaskName")
    private String TaskName;
    @ColumnInfo(name = "CreatedAt")
    private String CreateAt;
    @ColumnInfo(name = "Date")
    private String Date;
    @ColumnInfo(name = "Status")
    private String Status;
    @ColumnInfo(name = "Priority")
    private int Priority;


    public Item(String taskName, String createAt, String date, String status, int priority) {
        this.TaskName = taskName;
        this.CreateAt = createAt;
        this.Date = date;
        this.Status = status;
        this.Priority = priority;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    public String getCreateAt() {
        return CreateAt;
    }

    public void setCreateAt(String createAt) {
        CreateAt = createAt;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public int getPriority() {
        return Priority;
    }

    public void setPriority(int priority) {
        Priority = priority;
    }
}
