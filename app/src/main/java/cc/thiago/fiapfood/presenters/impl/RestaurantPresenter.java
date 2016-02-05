package cc.thiago.fiapfood.presenters.impl;

import cc.thiago.fiapfood.model.Restaurant;
import cc.thiago.fiapfood.presenters.IRestaurantPresenter;
import cc.thiago.fiapfood.realm.repository.IRestaurantRepository;
import cc.thiago.fiapfood.realm.repository.impl.RestaurantRepository;
import cc.thiago.fiapfood.view.activity.RestaurantActivity;
import io.realm.RealmResults;

/**
 * Created by thiagotn on 05/02/2016.
 */
public class RestaurantPresenter implements IRestaurantPresenter {

    private RestaurantActivity view;

    private IRestaurantRepository repository;

    private IRestaurantRepository.OnGetAllRestaurantCallback getAllRestaurantCallback;
    private IRestaurantRepository.OnAddRestaurantCallback addRestaurantCallback;
    private IRestaurantRepository.OnGetRestaurantByIdCallback getSpecialRestaurantCallback;
    private IRestaurantRepository.OnDeleteRestaurantCallback deleteRestaurantCallback;

    public RestaurantPresenter(RestaurantActivity view) {
        this.view = view;
        repository = new RestaurantRepository();
    }

    @Override
    public void addRestaurant(String restaurantName) {
        Restaurant restaurant = new Restaurant(restaurantName);
        // add more fields
        repository.addRestaurant(restaurant, addRestaurantCallback);
    }

    @Override
    public void deleteRestaurant(int position) {
        repository.deleteRestaurantByPosition(position, deleteRestaurantCallback);
    }

    @Override
    public void deleteRestaurantById(String Id) {
        repository.deleteRestaurantById(Id, deleteRestaurantCallback);
    }

    @Override
    public void getRestaurantById(String id) {
        repository.getRestaurantById(id, getSpecialRestaurantCallback);
    }

    @Override
    public void getAllRestaurants() {
        repository.getAllRestaurants(getAllRestaurantCallback);
    }

    @Override
    public void subscribeCallbacks() {
        getAllRestaurantCallback = new IRestaurantRepository.OnGetAllRestaurantCallback() {
            @Override
            public void onSuccess(RealmResults<Restaurant> restaurants) {
                view.showRestaurants(restaurants);
            }

            @Override
            public void onError(String message) {
                view.showMessage(message);
            }
        };
        addRestaurantCallback = new IRestaurantRepository.OnAddRestaurantCallback() {
            @Override
            public void onSuccess() {
                view.showMessage("Added");
            }

            @Override
            public void onError(String message) {
                view.showMessage(message);
            }
        };
        getSpecialRestaurantCallback = new IRestaurantRepository.OnGetRestaurantByIdCallback() {
            @Override
            public void onSuccess(Restaurant restaurant) {

            }

            @Override
            public void onError(String message) {
                view.showMessage(message);
            }
        };
        deleteRestaurantCallback = new IRestaurantRepository.OnDeleteRestaurantCallback() {
            @Override
            public void onSuccess() {
                view.showMessage("Deleted");
            }

            @Override
            public void onError(String message) {
                view.showMessage(message);
            }
        };

    }

    @Override
    public void unSubscribeCallbacks() {
        getAllRestaurantCallback = null;
        addRestaurantCallback = null;
        getSpecialRestaurantCallback = null;
        deleteRestaurantCallback = null;
    }
}
