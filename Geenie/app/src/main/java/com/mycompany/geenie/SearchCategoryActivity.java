package com.mycompany.geenie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.view.View;


public class SearchCategoryActivity extends Activity implements OnItemSelectedListener {

    Spinner categories;
    TextView selCategories;
    Button searchCategories, newCategory;
    private String[] state = { "Travel","Shopping" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_category);

        categories = (Spinner)findViewById(R.id.categories);
        selCategories = (TextView)findViewById(R.id.selcategories);
        searchCategories = (Button)findViewById(R.id.search);
        newCategory = (Button)findViewById(R.id.newCategory);

        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, state);
        adapter_state
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categories.setAdapter(adapter_state);
        categories.setOnItemSelectedListener(this);

        searchCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SearchCategoryActivity.this, SearchResultActivity.class);
                intent.putExtra("category", categories.getSelectedItem().toString());
                startActivity(intent);

            }
        });

        newCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SearchCategoryActivity.this, NewWishActivity.class);
                startActivity(intent);

            }
        });
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        categories.setSelection(position);
        String selState = (String) categories.getSelectedItem();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
}
