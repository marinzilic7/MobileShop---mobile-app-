package com.example.mobileshop.adapters;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.mobileshop.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileshop.models.Mobile;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class MobileAdapter extends RecyclerView.Adapter<MobileAdapter.MobileViewHolder> {
    private int selectedPosition = RecyclerView.NO_POSITION;
    private List<Mobile> mobileList;

    private DatabaseReference menuRef;
    public MobileAdapter(List<Mobile> mobileList,DatabaseReference menuRef) {
        this.mobileList = mobileList;
        this.menuRef = menuRef;
    }
    
    @NonNull
    @Override
    public MobileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mobile_item, parent, false);
        final MobileViewHolder viewHolder = new MobileViewHolder(view);
        return viewHolder;
    }

    public void updateData(List<Mobile> newMobileList) {
        this.mobileList = newMobileList;
        notifyDataSetChanged();
    }



    @Override
    public void onBindViewHolder(@NonNull MobileViewHolder holder, int position) {
        Mobile mobile = mobileList.get(position);
        final int itemPosition = position;
        holder.textViewName.setText(mobile.getImeMobitela());
        holder.textViewPrice.setText(mobile.getCijena());

        if (mobile.getSlika() != null && !mobile.getSlika().isEmpty()) {
            ImageView imageView = holder.itemView.findViewById(R.id.imageView);
            Glide.with(holder.itemView.getContext())
                    .load(mobile.getSlika())
                    .into(imageView);

        }
    }

    @Override
    public int getItemCount() {
        return mobileList.size();

    }


    public static class MobileViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewPrice;
        ImageView openMenuImage;

        public MobileViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);

        }
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Deklaracija ostalih elemenata u ViewHolderu

        ImageView openMenuImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Inicijalizacija ostalih elemenata u ViewHolderu


        }
    }


}
