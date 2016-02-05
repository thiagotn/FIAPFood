package cc.thiago.fiapfood.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cc.thiago.fiapfood.R;
import cc.thiago.fiapfood.view.activity.base.BaseActivity;

public class RestaurantActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
    }

    @Override
    protected void initComponents() {

    }

    @Override
    public void onClick(View v) {

    }
}
