package io.hackathon.hackathon2016;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import io.hackathon.hackathon2016.helper.DataController;

/**
 * Created by Sami on 1/05/2016.
 */
public class RouteStopsActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final DataController dataController = new DataController();

		String routeName = getIntent().getExtras().getString("routeName");
		ArrayList<String> stops = (ArrayList<String>)dataController.getStops(routeName);

		setContentView(R.layout.route_content);
		ListView mainListView = (ListView) findViewById( R.id.mainListView );
		ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(RouteStopsActivity.this, android.R.layout.simple_list_item_1, stops);

		mainListView.setAdapter( listAdapter );
	}
}
