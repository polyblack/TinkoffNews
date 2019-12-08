package com.polyblack.tinkoffnews.recview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.polyblack.tinkoffnews.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.RecViewHolder> {
    private List<String> titleList = new ArrayList<>();

    public RecAdapter(List<String> titleList) {
        this.titleList=titleList;
    }

    public void setItems(Collection<String> titles) {
        titleList.addAll(titles);
        notifyDataSetChanged();
    }

    public void clearItems() {
        titleList.clear();
        notifyDataSetChanged();
    }

    @Override
    public RecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_item, parent, false);
        return new RecViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecViewHolder holder, int position) {
        holder.bind(titleList.get(position));
    }

    @Override
    public int getItemCount() {
        return titleList.size();
    }

    class RecViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;

        public RecViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.title_item);
        }

        public void bind(String title) {
            nameTextView.setText(title);
        }
    }
}
