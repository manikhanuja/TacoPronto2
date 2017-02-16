package edu.csulb.android.tacopronto;

import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    ArrayList<String> gridArray_fillings = new ArrayList<String>();
    ArrayList<String> gridArray_beverage = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        GridView gridView_fillings = (GridView) findViewById(R.id.gridview_fillings);
        GridView gridView_beverage = (GridView) findViewById(R.id.gridview_beverage);

        //Add Items to Fillings Array
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

        //Add Items to Beverage Array
        gridArray_beverage.add("Soda");
        gridArray_beverage.add("Cerveza");
        gridArray_beverage.add("Margarita");
        gridArray_beverage.add("Tequila");

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
    }
}
