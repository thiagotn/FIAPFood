package cc.thiago.fiapfood.view.activity;

import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import cc.thiago.fiapfood.R;
import cc.thiago.fiapfood.model.Restaurant;
import cc.thiago.fiapfood.presenters.IRestaurantPresenter;
import cc.thiago.fiapfood.presenters.impl.RestaurantPresenter;
import cc.thiago.fiapfood.view.activity.base.BaseActivity;
import cc.thiago.fiapfood.view.adapters.RestaurantAdapter;
import cc.thiago.fiapfood.view.dialogs.AddRestaurantDialog;
import io.realm.RealmResults;

public class RestaurantActivity extends BaseActivity implements View.OnClickListener {

    private FloatingActionButton fbAdd;
    private RecyclerView rvRestaurants;
    private RestaurantAdapter adapter;

    private IRestaurantPresenter presenter;

    private RealmResults<Restaurant> restaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        presenter = new RestaurantPresenter(this);

        initComponents();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.subscribeCallbacks();
        presenter.getAllRestaurants();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unSubscribeCallbacks();
    }

    @Override
    protected void initComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.restaurants);
        setSupportActionBar(toolbar);
        fbAdd = (FloatingActionButton) findViewById(R.id.fab_add_restaurant);
        fbAdd.setOnClickListener(this);
        initRecyclerListener();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_add_restaurant: {
                showAddRestaurantDialog();
                break;
            }
        }
    }

    private void initRecyclerListener() {
        rvRestaurants = (RecyclerView) findViewById(R.id.rv_restaurants);
        rvRestaurants.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvRestaurants.setItemAnimator(new DefaultItemAnimator());

        ItemTouchHelper swipeToDismissTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                presenter.deleteRestaurantById(restaurants.get(viewHolder.getAdapterPosition()).getId());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        swipeToDismissTouchHelper.attachToRecyclerView(rvRestaurants);
    }

    public void showRestaurants(RealmResults<Restaurant> restaurants) {
        this.restaurants = restaurants;
        adapter = new RestaurantAdapter(restaurants);
        adapter.setOnItemClickListener(new RestaurantAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String id) {
                // TODO: Ir para Activity de Detalhes de Restaurante!
                /*
                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                intent.putExtra(RealmTable.ID, id);
                startActivity(intent);
                */
                Log.i("[showRestaurants]", "Show Restaurants!");
            }
        });
        rvRestaurants.setAdapter(adapter);
    }

    private void showAddRestaurantDialog() {
        final AddRestaurantDialog dialog = new AddRestaurantDialog();
        dialog.show(getSupportFragmentManager(), dialog.getClass().getName());
        dialog.setListener(new AddRestaurantDialog.OnAddRestaurantClickListener() {
            @Override
            public void onAddRestaurantClickListener(String restaurantName) {
                dialog.dismiss();
                presenter.addRestaurant(restaurantName);
            }
        });
    }

}