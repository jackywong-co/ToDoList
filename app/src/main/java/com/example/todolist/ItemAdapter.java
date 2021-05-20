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

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {
    private List<Item> items = new ArrayList<>();
    private OnItemClickListener listener;

    public class ItemHolder extends RecyclerView.ViewHolder {
        private TextView textViewTaskName, textViewDate;
        private RatingBar ratingBarPriority;


        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            // link view
            textViewTaskName = itemView.findViewById(R.id.textViewTaskName);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            ratingBarPriority = itemView.findViewById(R.id.ratingBarPriority);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // on item clicked set position
                    int position = getAdapterPosition();
                    // check the listener is not null and not chick in no item place
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(items.get(position));
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // link the item.xml
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
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

    public Item getItemAt(int position) {
        return items.get(position);
    }
    // create a on item click listener
    // pass the item
    public interface OnItemClickListener {
        void onItemClick(Item item);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
