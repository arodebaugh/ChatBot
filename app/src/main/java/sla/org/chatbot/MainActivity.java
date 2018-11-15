package sla.org.chatbot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.EditText;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
        listView.setAdapter(arrayAdapter);

        EditText chatText = findViewById(R.id.chatText);
        Button button = findViewById(R.id.button);

        ScrollView scroll = findViewById(R.id.scroll);

        chatText.setFocusableInTouchMode(true);
        chatText.setFocusable(true);
        chatText.requestFocus();

        controller = new Controller(listView, chatText, button, scroll, getApplicationContext());
    }

    @Override
    protected void onStop() {
        controller.save();
        super.onStop();
    }
}
