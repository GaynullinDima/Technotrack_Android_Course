package ru.mail.track.hw2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ru.mail.track.hw2.ui.OnItemClickListener;

public class TechItemAdapter extends RecyclerView.Adapter<TechItemAdapter.ItemViewHolder> {
    private ImageLoader mImageLoader;
    OnItemClickListener mItemClickListener;
    List<TechItem> techItemList;

    public TechItemAdapter(Context context, List<TechItem> techItemList) {
        this.techItemList = techItemList;
        mImageLoader = new MultipleImageLoader(context, R.drawable.ic_settings_black_48dp);
        int previewSize = (int) context.getResources().getDimension(R.dimen.preview_image_size);
        mImageLoader.setRequiredSize(previewSize, previewSize);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView preview;
        public TextView title;

        public ItemViewHolder(View itemView) {
            super(itemView);

            preview = (ImageView) itemView.findViewById(R.id.preview);
            title = (TextView) itemView.findViewById(R.id.title);

            itemView.setOnClickListener(this);
            preview.setOnClickListener(this);
            title.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mItemClickListener.onItemClick(v, getAdapterPosition());
        }


    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tech, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.title.setText(techItemList.get(position).title);
        mImageLoader.loadImage(techItemList.get(position).getPictureUrl(), holder.preview);
    }

    @Override
    public int getItemCount() {
        return techItemList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}
