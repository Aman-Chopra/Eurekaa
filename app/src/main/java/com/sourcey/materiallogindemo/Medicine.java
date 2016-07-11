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

public class Medicine extends AppCompatActivity {
    private List<Brain> myCars = new ArrayList<Brain>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        populateCarList();
        populateListView();
        registerClickCallback();



    }

    private void populateCarList(){
        myCars.add(new Brain("Dr. Manjunatha Hande"));
        myCars.add(new Brain("Dr. Joseph Thomas"));
        myCars.add(new Brain("Dr. Shivashankar K.N."));
        myCars.add(new Brain("Dr. Kavitha Saravu"));
        myCars.add(new Brain("Dr. Raviraja Acharaya"));
        myCars.add(new Brain("Dr. Ragini Bekur"));
        myCars.add(new Brain("Dr. B.A. Shashtry"));
        myCars.add(new Brain("Dr. Vasudeva Acharaya"));
        myCars.add(new Brain("Dr. Mukhyaprana Prabhu"));
        myCars.add(new Brain("Dr. Weena Stanley"));


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
                        Intent myIntent = new Intent(viewClicked.getContext(), Doctor.class);
                        startActivityForResult(myIntent, 0);
                        break;
                    case 1:
                        Intent myIntent1 = new Intent(viewClicked.getContext(), SignupActivity.class);
                        startActivityForResult(myIntent1, 0);
                        break;

                }

            }
        });
        int posi = pos[0];

    }

    private  class MyListAdapter extends ArrayAdapter<Brain>
    {

        public MyListAdapter() {
            super(Medicine.this, R.layout.neurology, myCars);
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
        getMenuInflater().inflate(R.menu.menu_medicine, menu);
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
