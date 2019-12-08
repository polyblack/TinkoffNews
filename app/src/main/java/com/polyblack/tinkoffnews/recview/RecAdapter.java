package com.polyblack.tinkoffnews.recview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.polyblack.tinkoffnews.R;
import com.polyblack.tinkoffnews.data.TNews;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.RecViewHolder> {
    private List<TNews> titleList = new ArrayList<>();

    public RecAdapter(List<TNews> titleList) {
        this.titleList=titleList;
    }
    public void setItems(Collection<TNews> titles) {
        titleList.addAll(titles);
        notifyDataSetChanged();
    }

    public void clearItems() {
        titleList.clear();
        notifyDataSetChanged();
    }

    public interface NewsAdapterOnClickHandler {
        void onClick(int newsId);
    }



    @Override
    public RecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_item, parent, false);
        return new RecViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecViewHolder holder, int position) {
        TNews tNews =titleList.get(position);
        holder.bind(titleList.get(position));
    }

    @Override
    public int getItemCount() {
        return titleList.size();
    }

    class RecViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nameTextView;

        @Override
        public void onClick(View v) {
           int adapterPosition = getAdapterPosition();
           int itemId = titleList.get(adapterPosition).getId();
        }

        public RecViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.title_item);
        }

        public void bind(TNews tNews) {
            nameTextView.setText(tNews.getTitle());
        }
    }
}
