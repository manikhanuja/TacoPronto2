package edu.csulb.android.tacopronto;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.telephony.SmsManager;

import java.util.ArrayList;

public class OrderActivity extends Activity {
    ArrayList<String> gridArray_fillings = new ArrayList<String>();
    ArrayList<String> gridArray_beverage = new ArrayList<String>();
    GridView gridView_fillings;
    GridView gridView_beverage;

    RadioGroup size_group, tortilla_group;
    RadioButton large_radio, medium_radio;
    Button submit_Button;
    float orderCost= (float) 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        gridView_fillings = (GridView) findViewById(R.id.gridview_fillings);
        gridView_beverage = (GridView) findViewById(R.id.gridview_beverage);
        size_group = (RadioGroup)findViewById(R.id.size_radioGroup);
        tortilla_group =(RadioGroup)findViewById(R.id.flour_radiogroup);
        submit_Button = (Button)findViewById(R.id.placeOrder_button);
        large_radio = (RadioButton) findViewById(R.id.large_radio);
        medium_radio = (RadioButton) findViewById(R.id.medium_radio);


        //Add Fillings
        gridArray_fillings.add("Beef");
        gridArray_fillings.add("Chicken");
        gridArray_fillings.add("White Fish");
        gridArray_fillings.add("Cheese");
        gridArray_fillings.add("Sea Food");
        gridArray_fillings.add("Rice");
        gridArray_fillings.add("Beans");
        gridArray_fillings.add("Pico de Gallo");
        gridArray_fillings.add("Guacamole");
        gridArray_fillings.add("LBT");

        // Add Beverages
        gridArray_beverage.add("Soda");
        gridArray_beverage.add("Cerveza");
        gridArray_beverage.add("Margarita");
        gridArray_beverage.add("Tequila");

       // setSharedPreferences(gridArray_fillings,gridArray_beverage);
        final SharedPreferences sharedPreferences =  getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //Add price for fillings
        editor.putFloat(gridArray_fillings.get(0),(float) 1.5);
        editor.putFloat(gridArray_fillings.get(1),(float) 1.5);
        editor.putFloat(gridArray_fillings.get(2),(float) 2.0);
        editor.putFloat(gridArray_fillings.get(3),(float) 1.5);
        editor.putFloat(gridArray_fillings.get(4),(float) 1.0);
        editor.putFloat(gridArray_fillings.get(5),(float) 1.0);
        editor.putFloat(gridArray_fillings.get(6),(float) 1.0);
        editor.putFloat(gridArray_fillings.get(7),(float) 2.5);
        editor.putFloat(gridArray_fillings.get(8),(float) 3.0);
        editor.putFloat(gridArray_fillings.get(9),(float) 2.5);

        //Add Price for Beverage
        editor.putFloat(gridArray_beverage.get(0),(float) 1.0);
        editor.putFloat(gridArray_beverage.get(1),(float) 1.5);
        editor.putFloat(gridArray_beverage.get(2),(float) 1.5);
        editor.putFloat(gridArray_beverage.get(3),(float) 2.0);

        //Add Price for Tortilla
        editor.putFloat(getString(R.string.corn_radio),(float) 3.0);
        editor.putFloat(getString(R.string.flour_radio),(float) 2.0);
        editor.putFloat(getString(R.string.large_radio),(float) 4.0);
        editor.putFloat(getString(R.string.medium_radio),(float) 2.0);
        editor.apply();

        //Set Array Adapter for Fillings
        ArrayAdapter<String> adapter_fillings = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, gridArray_fillings);
        gridView_fillings.setAdapter(adapter_fillings);
        gridView_fillings.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        //Set Array Adapter for Beverages
        ArrayAdapter<String> adapter_beverages = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, gridArray_beverage);
        gridView_beverage.setAdapter(adapter_beverages);
        gridView_beverage.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


       submit_Button.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
                String size_selection = null;
                String tortilla_selection = null;
                try {
                    if(size_group.getCheckedRadioButtonId() != -1) {
                         size_selection = ((RadioButton) findViewById(size_group.getCheckedRadioButtonId())).getText().toString();
                    }
                    if(tortilla_group.getCheckedRadioButtonId() != -1){
                         tortilla_selection = ((RadioButton) findViewById(tortilla_group.getCheckedRadioButtonId())).getText().toString();

                    }

                        orderCost = orderCost + sharedPreferences.getFloat(size_selection, (float) 0.0);
                        orderCost = orderCost + sharedPreferences.getFloat(tortilla_selection, (float) 0.0);

                    SparseBooleanArray sparseBool_fillings = gridView_fillings.getCheckedItemPositions();

                    for (int fill_index = 0; fill_index < gridView_fillings.getCount(); fill_index++
                            ) {
                        if (sparseBool_fillings.get(fill_index)) {

                            float price = sharedPreferences.getFloat(gridArray_fillings.get(fill_index), (float) 0.0);
                            orderCost = orderCost + price;
                        }

                    }

                    SparseBooleanArray sparseBool_beverage = gridView_beverage.getCheckedItemPositions();

                    for (int bev_index = 0; bev_index < gridView_beverage.getCount(); bev_index++
                            ) {
                        if (sparseBool_beverage.get(bev_index)) {

                            float price = sharedPreferences.getFloat(gridArray_beverage.get(bev_index), (float) 0.0);
                            orderCost = orderCost + price;
                        }

                    }
                }
                catch (Exception e){
                    String errorMessage = e.getMessage();
                    String stackTraceError = e.getStackTrace().toString();
                }
                TextView message_textView = (TextView) findViewById(R.id.msg_textView);
                message_textView.setText("Price for fillings: " + orderCost);
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage("5556", null,
                        getString(R.string.textMessage) + orderCost + getString(R.string.currencyUnit) , null, null);

                //Reset the orderCost Value
                orderCost = (float) 0.0;

            }
        });


    }



}
