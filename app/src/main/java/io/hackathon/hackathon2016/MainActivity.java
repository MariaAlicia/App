package io.hackathon.hackathon2016;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button b1, b2;
	final private DataController dataController = new DataController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
	    initializeMain();
    }

	private void initializeMain(){
        //update database
        dataController.updateDatabase(getAssets());
        
        b1=(Button)findViewById(R.id.go);
        b2=(Button)findViewById(R.id.go2);
        // Spinner element
        final Spinner s = (Spinner) findViewById(R.id.spinner);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText stopIdEditText = ((EditText)findViewById(R.id.stopId));
                String stopId = stopIdEditText.getText().toString();

                ArrayList<String> busTimes = (ArrayList<String>)dataController.stopRoutesAndTime(stopId);

	            if(busTimes.isEmpty())
	            {
		            Toast.makeText(MainActivity.this, "Invalid stop", Toast.LENGTH_SHORT).show();
		            return;
	            }

	            Intent i = new Intent(getApplicationContext(), StopTimesActivity.class);
	            i.putExtra("busTimes", busTimes);
	            startActivity(i);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
	            Spinner sp = (Spinner)findViewById(R.id.spinner);
	            String routeName = sp.getSelectedItem().toString();
	            ArrayList<String> stops = (ArrayList<String>)dataController.getStops(routeName);

	            Intent i = new Intent(getApplicationContext(), RouteStopsActivity.class);
	            i.putExtra("stops", stops);
	            startActivity(i);
            }
        });

        // Spinner click listener
        assert s != null;
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {

                String items=s.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }

        });

        // Spinner Drop down elements
        List<String> route = dataController.getAllRoutes();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, route);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        s.setAdapter(dataAdapter);

	    setupUI(findViewById(R.id.parentLayout));
    }

	public static void hideSoftKeyboard(Activity activity) {
		InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
	}

	public void setupUI(View view) {

		//Set up touch listener for non-text box views to hide keyboard.
		if(!(view instanceof EditText)) {

			view.setOnTouchListener(new View.OnTouchListener() {

				public boolean onTouch(View v, MotionEvent event) {
					hideSoftKeyboard(MainActivity.this);
					return false;
				}

			});
		}

		//If a layout container, iterate over children and seed recursion.
		if (view instanceof ViewGroup) {

			for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

				View innerView = ((ViewGroup) view).getChildAt(i);

				setupUI(innerView);
			}
		}
	}

    public ArrayList getRoutes(){
        return new ArrayList(8);
    }
}
