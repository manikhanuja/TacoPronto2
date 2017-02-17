package edu.csulb.android.tacopronto;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.SharedPreferences;
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

public class OrderActivity extends Activity {
    ArrayList<String> gridArray_fillings = new ArrayList<String>();
    ArrayList<String> gridArray_beverage = new ArrayList<String>();
    SharedPreferences sharedPreferences =  getPreferences(Context.MODE_PRIVATE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        GridView gridView_fillings = (GridView) findViewById(R.id.gridview_fillings);
        GridView gridView_beverage = (GridView) findViewById(R.id.gridview_beverage);

        setGridArray_fillings();
        setGridArray_beverage();
        setSharedPreferences(gridArray_fillings,gridArray_beverage);

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

    public void setGridArray_fillings(){
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

    }

    public void setGridArray_beverage(){
        //Add Items to Beverage Array
        gridArray_beverage.add("Soda");
        gridArray_beverage.add("Cerveza");
        gridArray_beverage.add("Margarita");
        gridArray_beverage.add("Tequila");

    }
    public void setSharedPreferences(ArrayList<String> gridArray_fillings, ArrayList<String> gridArray_beverage){

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
        editor.putFloat(getString(R.string.corn_radio),(float) 2.0);
        editor.putFloat(getString(R.string.flour_radio),(float) 2.0);
        editor.apply();
    }

}
