package com.example.todolist.RoomDataBase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


// database settings
public abstract class ItemDataBase extends RoomDatabase {
    private static volatile ItemDataBase instance;
    public abstract ItemDao itemDao();
    public static synchronized ItemDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ItemDataBase.class, "item_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsynctask(instance).execute();
        }
    };

    private static class PopulateDbAsynctask extends AsyncTask<Void, Void, Void> {
        private ItemDao itemDao;

        private PopulateDbAsynctask(ItemDataBase dataBase) {
            itemDao = dataBase.itemDao();
        }

        protected Void doInBackground(Void... voids) {
            // initial data
//            itemDao.insert(new Item("1", "2", "3", "4", 5));
            return null;
        }
    }
}
