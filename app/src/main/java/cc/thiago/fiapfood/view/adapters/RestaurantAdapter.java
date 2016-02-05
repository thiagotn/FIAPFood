package cc.thiago.fiapfood.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cc.thiago.fiapfood.R;
import cc.thiago.fiapfood.model.Restaurant;
import io.realm.RealmResults;

/**
 * Created by thiagotn on 05/02/2016.
 */
public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    private OnItemClickListener onItemClickListener;
    private RealmResults<Restaurant> restaurants;

    public RestaurantAdapter(RealmResults<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_restaurant, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        holder.tvRestaurantName.setText(restaurants.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvRestaurantName;

        public RestaurantViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            tvRestaurantName = (TextView) itemView.findViewById(R.id.tv_name_restaurant);
        }

        @Override
        public void onClick(View v) {
            Restaurant restaurant = restaurants.get(getAdapterPosition());
            onItemClickListener.onItemClick(restaurant.getId());
        }
    }

    public interface OnItemClickListener{
        void onItemClick(String id);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
