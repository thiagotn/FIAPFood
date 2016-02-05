package cc.thiago.fiapfood.view.activity.base;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by thiagotn on 05/02/2016.
 */
public abstract class BaseActivity extends AppCompatActivity {

    abstract protected void initComponents();

    public void showMessage(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
