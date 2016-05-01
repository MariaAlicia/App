package io.hackathon.hackathon2016;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Sami on 1/05/2016.
 */
public class StopTimesActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final DataController dataController = new DataController();

		String stopId = getIntent().getExtras().getString("stopId");
		ArrayList<String> busTimes = new ArrayList<>();

		setContentView(R.layout.route_content);
		ListView mainListView = (ListView) findViewById( R.id.mainListView );
		ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(StopTimesActivity.this, android.R.layout.simple_list_item_1, busTimes);

		mainListView.setAdapter( listAdapter );

		new AsyncStopLoader(busTimes).execute(stopId);
	}
}