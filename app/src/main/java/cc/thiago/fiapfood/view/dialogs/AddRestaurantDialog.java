package cc.thiago.fiapfood.view.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import cc.thiago.fiapfood.R;

/**
 * Created by thiagotn on 05/02/2016.
 */
public class AddRestaurantDialog extends DialogFragment implements View.OnClickListener {

    private EditText etRestaurantName;
    private Button btAddRestaurant;

    private OnAddRestaurantClickListener listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setStyle(DialogFragment.STYLE_NORMAL, R.style.AlertDialogStyle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_restaurant, container);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        etRestaurantName = (EditText) view.findViewById(R.id.et_restaurant_name);
        etRestaurantName.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        btAddRestaurant = (Button) view.findViewById(R.id.bt_add_restaurant);
        btAddRestaurant.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add_restaurant: {
                if (isRestaurantInfoValid())
                    listener.onAddRestaurantClickListener(etRestaurantName.getText().toString());
                break;
            }
        }
    }

    private boolean isRestaurantInfoValid() {
        return !etRestaurantName.getText().toString().isEmpty();
    }

    public void setListener(OnAddRestaurantClickListener listener) {
        this.listener = listener;
    }

    public interface OnAddRestaurantClickListener {
        void onAddRestaurantClickListener(String universityName);
    }
}
