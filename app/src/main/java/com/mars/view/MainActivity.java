package com.mars.view;

import android.os.Bundle;

import com.mars.R;
import com.mars.customview.ParentAdapter;

import com.mars.model.Item;
import com.mars.presenter.ItemInitializer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

    //===============================================Variable=======================================
    //-------------------------------------------------View-----------------------------------------
    RecyclerView listView;
    RecyclerView.LayoutManager manager;
    //----------------------------------------------------------------------------------------------
    //-----------------------------------------------Instance---------------------------------------
    ItemInitializer itemInitializer;
    ArrayList<Item> data;
    //----------------------------------------------------------------------------------------------
    //==============================================================================================
    //==============================================LifeCycle=======================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.manager = new LinearLayoutManager(this);
        this.listView = findViewById(R.id.exp_lv_item);
        this.itemInitializer = new ItemInitializer();
        this.data = this.itemInitializer.generateData(3);
        this.listView.setLayoutManager(manager);
        this.listView.setAdapter(new ParentAdapter(this,this.data));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //==============================================================================================

}
