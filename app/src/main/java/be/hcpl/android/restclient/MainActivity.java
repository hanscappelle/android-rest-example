package be.hcpl.android.restclient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends Activity {

    private MyListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list = (ListView) findViewById(R.id.list);

        // create some dummy data here
        List<Item> objects = new ArrayList<Item>();
        // and put it into an adapter for the list
        mAdapter = new MyListAdapter(this, objects);
        list.setAdapter(mAdapter);

        // mAdapter is available in the helper methods below and the data will be updated based on
        // action menu interactions

        // you could also keep the reference to the android ListView object instead and use the
        // {@link ListView#getAdapter()} method instead. However you would have to cast that
        // adapter to your own instance every time

        refreshData();
    }

    private void refreshData(){
        // retrieve all data to populate list
        ApiClient.getItemApiClient().getItems(new Callback<List<Item>>() {
            @Override
            public void success(List<Item> items, Response response) {
                reloadAllData(items);
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.e(this.getClass().getSimpleName(), "Received error on data fetch", retrofitError);
                // TODO handle error here
            }
        });
    }

    /**
     * helper to show what happens when all data is new
     */
    private void reloadAllData(List<Item> items){
        // update data in our adapter
        mAdapter.getData().clear();
        mAdapter.getData().addAll(items);
        // fire the event
        mAdapter.notifyDataSetChanged();
    }

    /**
     * helper to show how only changing properties of data elements also works
     */
    private void scrambleChecked(){
        Random random = new Random();
        // update data in our adapter, iterate all objects and resetting the checked option
        for( Item mo : mAdapter.getData()) {
            mo.setChecked(random.nextBoolean());
        }
        // fire the event
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_scramble) {
            scrambleChecked();
            return true;
        } else if (id == R.id.action_reload) {
            refreshData();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
