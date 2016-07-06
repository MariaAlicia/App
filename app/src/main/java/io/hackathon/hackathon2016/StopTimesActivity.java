package io.hackathon.hackathon2016;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import io.hackathon.hackathon2016.helper.DataController;

/**
 * Created by Sami on 1/05/2016.
 */
public class StopTimesActivity extends AppCompatActivity {

    ListView mainListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final DataController dataController = new DataController();

		String stopId = getIntent().getExtras().getString("stopId");
        AsyncStopTimesLoader loader = new AsyncStopTimesLoader();
        loader.execute(stopId);

		setContentView(R.layout.route_content);
		mainListView = (ListView) findViewById( R.id.mainListView );
	}

    class AsyncStopTimesLoader extends AsyncTask<String, Void, List<String>> {

        @Override
        protected List<String> doInBackground(String... params) {
            DataController dataController = new DataController();

            return dataController.stopRoutesAndTime(params[0]);
        }

        @Override
        protected void onPostExecute(List<String> strings) {
            super.onPostExecute(strings);
            ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(StopTimesActivity.this, android.R.layout.simple_list_item_1, strings);

            mainListView.setAdapter( listAdapter );
        }
    }
}