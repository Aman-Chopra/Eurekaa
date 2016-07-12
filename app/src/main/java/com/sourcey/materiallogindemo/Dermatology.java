package com.sourcey.materiallogindemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Dermatology extends AppCompatActivity {
    private List<Brain> myCars = new ArrayList<Brain>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dermatology);

        populateCarList();
        populateListView();
        registerClickCallback();



    }

    private void populateCarList(){
        myCars.add(new Brain("Dr. Sathish Pai B."));
        myCars.add(new Brain("Dr. Raghavendra Rao"));
        myCars.add(new Brain("Dr. Shricharith Shetty"));
        myCars.add(new Brain("Dr. Shrutakirathi Shenoi"));
        myCars.add(new Brain("Dr. Smitha Prabhu"));
        myCars.add(new Brain("Dr. Sudhir Nayak"));

    }

    private void populateListView(){
        ArrayAdapter<Brain> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(adapter);
    }

    private void registerClickCallback()
    {
        final int pos[] = new int[1];
        ListView list = (ListView)findViewById((R.id.listView));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {

                /*Car clickedCar = myCars.get(position);
                String message = "You clicked position " + position + " which is car make " + clickedCar.getMake();
                Toast.makeText(Listviewdepartments.this, message, Toast.LENGTH_LONG).show();*/
                switch (position) {
                    case 0:
                        Intent myIntent = new Intent(viewClicked.getContext(), der1.class);
                        startActivityForResult(myIntent, 0);
                        break;
                    case 1:
                        Intent myIntent1 = new Intent(viewClicked.getContext(), der2.class);
                        startActivityForResult(myIntent1, 0);
                        break;
                    case 2:
                        Intent myIntent2 = new Intent(viewClicked.getContext(), der3.class);
                        startActivityForResult(myIntent2, 0);
                        break;
                    case 3:
                        Intent myIntent3 = new Intent(viewClicked.getContext(), der4.class);
                        startActivityForResult(myIntent3, 0);
                        break;
                    case 4:
                        Intent myIntent4 = new Intent(viewClicked.getContext(), der5.class);
                        startActivityForResult(myIntent4, 0);
                        break;
                    case 5:
                        Intent myIntent5 = new Intent(viewClicked.getContext(), der6.class);
                        startActivityForResult(myIntent5, 0);
                        break;

                }

            }
        });
        int posi = pos[0];

    }

    private  class MyListAdapter extends ArrayAdapter<Brain>
    {

        public MyListAdapter() {
            super(Dermatology.this, R.layout.neurology, myCars);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if(itemView == null)
            {
                itemView = getLayoutInflater().inflate(R.layout.neurology,parent, false);
            }

            Brain currentCar = myCars.get(position);



            TextView makeText = (TextView) itemView.findViewById(R.id.item_txtMake);
            makeText.setText(currentCar.getMake());






            return itemView;
            //return super.getView(position, convertView, parent);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dermatology, menu);
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
}
