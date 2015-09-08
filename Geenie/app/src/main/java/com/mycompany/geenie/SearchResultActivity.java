package com.mycompany.geenie;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class SearchResultActivity extends Activity {

    private ListView lv;
    Button chatNow;

    // Create Array's of titles, descriptions and thumbs resource id's:
    private String title[] = { "Travel", "Shopping", "Food" };

    private String desc[] = { "I want to go to Delhi", "I want to buy comics", "I want to eat Bouillabaisse" };

    private int thumb[] = {R.drawable.travel, R.drawable.shopping, R.drawable.food};

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        final  Context context = this;
        TextView category = (TextView)findViewById(R.id.selectedCategory);
        category.setText(getIntent().getExtras().getString("category"));

        chatNow = (Button)findViewById(R.id.chat);

        lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(new VersionAdapter(this));
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                int pos = arg2;
                LayoutInflater layoutInflator = getLayoutInflater();

                View layout = layoutInflator.inflate(R.layout.custom_toast,
                        (ViewGroup) findViewById(R.id.toast_layout_root));

                ImageView iv = (ImageView) layout.findViewById(R.id.toast_iv);
                TextView tv = (TextView) layout.findViewById(R.id.toast_tv);

                iv.setBackgroundResource(thumb[pos]);
                tv.setText(title[pos]);

                Toast toast = new Toast(getApplicationContext());
                toast.setView(layout);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });

        chatNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ChatActivity.class);
                startActivity(intent);
            }
        });

    }

    class VersionAdapter extends BaseAdapter {

        private LayoutInflater layoutInflater;

        public VersionAdapter(SearchResultActivity activity) {
            layoutInflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return title.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View listItem = convertView;
            int pos = position;
            if (listItem == null) {
                listItem = layoutInflater.inflate(R.layout.list_item, null);
            }

            ImageView iv = (ImageView) listItem.findViewById(R.id.thumb);
            TextView tvTitle = (TextView) listItem.findViewById(R.id.title);
            TextView tvDesc = (TextView) listItem.findViewById(R.id.desc);

            iv.setBackgroundResource(thumb[pos]);
            tvTitle.setText(title[pos]);
            tvDesc.setText(desc[pos]);

            return listItem;
        }
    }
}

