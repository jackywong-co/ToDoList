package com.example.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.RoomDataBase.Item;

import java.util.ArrayList;
import java.util.List;


public class HistoryAdaptper extends RecyclerView.Adapter<HistoryAdaptper.HistoryHolder> {
    private List<Item> items = new ArrayList<>();

    public class HistoryHolder extends RecyclerView.ViewHolder {
        private TextView textViewTaskName, textViewDate;
        private RatingBar ratingBarPriority;

        public HistoryHolder(@NonNull View itemView) {
            super(itemView);
            textViewTaskName = itemView.findViewById(R.id.textViewTaskName);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            ratingBarPriority = itemView.findViewById(R.id.ratingBarPriority);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }

    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // link the item.xml
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new HistoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder holder, int position) {
        // set item show乜
        Item currentItem = items.get(position);
        holder.textViewTaskName.setText(currentItem.getTaskName());
        holder.textViewDate.setText(currentItem.getDate());
        holder.ratingBarPriority.setNumStars(currentItem.getPriority());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}
