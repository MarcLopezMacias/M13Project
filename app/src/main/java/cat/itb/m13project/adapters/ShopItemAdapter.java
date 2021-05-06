package cat.itb.m13project.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cat.itb.m13project.R;
import cat.itb.m13project.pojo.Item;

public class ShopItemAdapter extends RecyclerView.Adapter<ShopItemAdapter.ShopItemViewHolder> implements View.OnClickListener {

    private View.OnClickListener shopItemListener;

    @Override
    public void onClick(View v) {
        if (shopItemListener != null) {
            shopItemListener.onClick(v);
        }
    }

    @NonNull
    @Override
    public ShopItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_shop_item_view, parent, false);
        v.setOnClickListener(this);
        return new ShopItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopItemViewHolder holder, int position) {
        //holder.bind();
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ShopItemViewHolder extends RecyclerView.ViewHolder {

        TextView itemNameTextView;
        TextView itemPriceTextView;
        ImageView itemImageView;

        public ShopItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.shop_item_name_text_view);
            itemPriceTextView = itemView.findViewById(R.id.shop_item_price_text_view);
            itemImageView = itemView.findViewById(R.id.shop_item_image_view);
        }

        public void bind(final Item item) {
            itemNameTextView.setText(item.getName());
            itemPriceTextView.setText(String.valueOf(item.getPrice()));
        }
    }
}
