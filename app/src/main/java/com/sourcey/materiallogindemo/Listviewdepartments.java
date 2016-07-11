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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Listviewdepartments extends AppCompatActivity {
    private List<Car> myCars = new ArrayList<Car>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listviewdepartments);

        populateCarList();
        populateListView();
        registerClickCallback();



    }

    private void populateCarList(){
        myCars.add(new Car("Neurology", 13200, R.drawable.neurology, "1"));
        myCars.add(new Car("Urology", 13200, R.drawable.urology, "2"));
        myCars.add(new Car("Nephrology", 13200, R.drawable.nephrology, "3"));
        myCars.add(new Car("Audiology", 13200, R.drawable.audiology, "4"));
        myCars.add(new Car("Plastic surgery", 13200, R.drawable.plastic, "5"));
        myCars.add(new Car("Ophthalmology", 13200, R.drawable.ophthal, "6"));
        myCars.add(new Car("Psychiatry", 13200, R.drawable.psych, "7"));
        myCars.add(new Car("Cardiology", 13200, R.drawable.cardiology, "8"));
        myCars.add(new Car("Gastroentorology", 13200, R.drawable.gastro, "9"));
        myCars.add(new Car("Dermatology", 13200, R.drawable.dermatology, "10"));
        myCars.add(new Car("Orthopaedics", 13200, R.drawable.ortho, "11"));
        myCars.add(new Car("Medicine", 13200, R.drawable.medicine, "12"));
        myCars.add(new Car("Dental", 13200, R.drawable.dental, "13"));
        myCars.add(new Car("Women and Child", 13200, R.drawable.women, "14"));
        myCars.add(new Car("Ear, nose and throat", 13200, R.drawable.ent, "15"));
    }

    private void populateListView(){
        ArrayAdapter<Car> adapter = new MyListAdapter();
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
                    Intent myIntent = new Intent(viewClicked.getContext(), About.class);
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

    private  class MyListAdapter extends ArrayAdapter<Car>
    {

        public MyListAdapter() {
            super(Listviewdepartments.this, R.layout.item_view, myCars);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if(itemView == null)
            {
                itemView = getLayoutInflater().inflate(R.layout.item_view,parent, false);
            }

            Car currentCar = myCars.get(position);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.item_icon);
            imageView.setImageResource(currentCar.getIconID());

            TextView makeText = (TextView) itemView.findViewById(R.id.item_txtMake);
            makeText.setText(currentCar.getMake());






            return itemView;
            //return super.getView(position, convertView, parent);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listviewdepartments, menu);
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
