package io.hackathon.hackathon2016;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Sami on 1/05/2016.
 */
public class RouteStopsActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.route_content);
		ListView mainListView = (ListView) findViewById( R.id.mainListView );
		String[] planets = new String[] { "Mercury", "Venus", "Earth", "Mars",
				"Jupiter", "Saturn", "Uranus", "Neptune"};
		ArrayList<String> planetList = new ArrayList<String>();
		planetList.addAll( Arrays.asList(planets) );
		ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(RouteStopsActivity.this, android.R.layout.simple_list_item_1, planetList);

		mainListView.setAdapter( listAdapter );
	}
}
