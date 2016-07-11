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
        myCars.add(new Car("Neurology", R.drawable.neurology));
        myCars.add(new Car("Urology",R.drawable.urology));
        myCars.add(new Car("Nephrology",R.drawable.nephrology));
        myCars.add(new Car("Audiology",R.drawable.audiology));
        myCars.add(new Car("Plastic surgery",R.drawable.plastic));
        myCars.add(new Car("Ophthalmology", R.drawable.ophthal));
        myCars.add(new Car("Psychiatry", R.drawable.psych));
        myCars.add(new Car("Cardiology", R.drawable.cardiology));
        myCars.add(new Car("Gastroentorology", R.drawable.gastro));
        myCars.add(new Car("Dermatology", R.drawable.dermatology));
        myCars.add(new Car("Orthopaedics", R.drawable.ortho));
        myCars.add(new Car("Medicine", R.drawable.medicine));
        myCars.add(new Car("Dental", R.drawable.dental));
        myCars.add(new Car("Woman and Child", R.drawable.women));
        myCars.add(new Car("Ear, nose and throat", R.drawable.ent));
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
                    Intent myIntent = new Intent(viewClicked.getContext(), Neuro.class);
                    startActivityForResult(myIntent, 0);
                        break;
                    case 1:
                        Intent myIntent1 = new Intent(viewClicked.getContext(), Urology.class);
                        startActivityForResult(myIntent1, 0);
                        break;
                    case 2:
                        Intent myIntent2 = new Intent(viewClicked.getContext(), Nephrology.class);
                        startActivityForResult(myIntent2, 0);
                        break;
                    case 3:
                        Intent myIntent3 = new Intent(viewClicked.getContext(), Audiology.class);
                        startActivityForResult(myIntent3, 0);
                        break;
                    case 4:
                        Intent myIntent4 = new Intent(viewClicked.getContext(), Plastic.class);
                        startActivityForResult(myIntent4, 0);
                        break;
                    case 5:
                        Intent myIntent5 = new Intent(viewClicked.getContext(), Ophthalmology.class);
                        startActivityForResult(myIntent5, 0);
                        break;
                    case 6:
                        Intent myIntent6 = new Intent(viewClicked.getContext(), Psychaitry.class);
                        startActivityForResult(myIntent6, 0);
                        break;
                    case 7:
                        Intent myIntent7 = new Intent(viewClicked.getContext(), Cardiology.class);
                        startActivityForResult(myIntent7, 0);
                        break;
                    case 8:
                        Intent myIntent8 = new Intent(viewClicked.getContext(), Gastroenterology.class);
                        startActivityForResult(myIntent8, 0);
                        break;
                    case 9:
                        Intent myIntent9 = new Intent(viewClicked.getContext(), Dermatology.class);
                        startActivityForResult(myIntent9, 0);
                        break;
                    case 10:
                        Intent myIntent10 = new Intent(viewClicked.getContext(), Orthopaedics.class);
                        startActivityForResult(myIntent10, 0);
                        break;
                    case 11:
                        Intent myIntent11 = new Intent(viewClicked.getContext(), Medicine.class);
                        startActivityForResult(myIntent11, 0);
                        break;
                    case 12:
                        Intent myIntent12 = new Intent(viewClicked.getContext(), Dental.class);
                        startActivityForResult(myIntent12, 0);
                        break;
                    case 13:
                        Intent myIntent13 = new Intent(viewClicked.getContext(), Wc.class);
                        startActivityForResult(myIntent13, 0);
                        break;
                    case 14:
                        Intent myIntent14 = new Intent(viewClicked.getContext(), Ent.class);
                        startActivityForResult(myIntent14, 0);
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
