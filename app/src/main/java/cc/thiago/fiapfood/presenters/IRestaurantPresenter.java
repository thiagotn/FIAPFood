package cc.thiago.fiapfood.presenters;

/**
 * Created by thiagotn on 05/02/2016.
 */
public interface IRestaurantPresenter extends IBasePresenter {

    void addRestaurant(String universityName);

    void deleteRestaurant(int position);

    void deleteRestaurantById(String Id);

    void getRestaurantById(String id);

    void getAllRestaurants();
}
