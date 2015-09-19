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

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;


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

                String topic        = "Topic from phone";
                String content      = "Message from MqttPublishSample";
                int qos             = 2;
                String broker       = "tcp://192.168.1.108:61613";
                String clientId     = "JavaSample";
                MemoryPersistence persistence = new MemoryPersistence();

                try {
                    MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
                    MqttConnectOptions connOpts = new MqttConnectOptions();
                    connOpts.setUserName("admin");
                    connOpts.setPassword("password".toCharArray());
                    connOpts.setCleanSession(true);
                    System.out.println("Connecting to broker: "+broker);
                    System.out.println("username is "+connOpts.getUserName());
                    sampleClient.connect(connOpts);
                    System.out.println("Connected");
                    System.out.println("Publishing message: "+content);
                    MqttMessage message = new MqttMessage(content.getBytes());
                    message.setQos(qos);
                    sampleClient.publish(topic, message);
                    System.out.println("Message published");
            /*sampleClient.disconnect();
            System.out.println("Disconnected");
            System.exit(0);
            */
                } catch(MqttException me) {
                    System.out.println("reason "+me.getReasonCode());
                    System.out.println("msg "+me.getMessage());
                    System.out.println("loc "+me.getLocalizedMessage());
                    System.out.println("cause "+me.getCause());
                    System.out.println("excep "+me);
                    me.printStackTrace();
                }

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
