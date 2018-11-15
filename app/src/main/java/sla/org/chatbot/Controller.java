package sla.org.chatbot;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.view.View.*;
import android.view.KeyEvent;
import android.widget.ScrollView;
import android.content.Context;
import java.io.*;

public class Controller {
    public ListView listView;
    public EditText chatText;
    public Button button;
    public ArrayAdapter<String> arrayAdapter;
    public ScrollView scroll;

    public String name;
    public String age;
    public boolean newUser;
    public int waitingFor = 0;
    private String chat;

    public Context context;

    private Bot bot = new Bot();

    Controller(ListView getListView, EditText getChatText, Button getButton, ScrollView getScroll, Context cntxt) {
        listView = getListView;
        chatText = getChatText;
        button = getButton;
        scroll = getScroll;

        context = cntxt;

        arrayAdapter = (ArrayAdapter<String>)listView.getAdapter();

        read();

        scroll.postDelayed(new Runnable() {
            @Override
            public void run() {
                scroll.fullScroll(View.FOCUS_DOWN);
            }
        },1000);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!chatText.getText().toString().trim().equals("")) {
                    arrayAdapter.add("User: " + chatText.getText().toString());
                    chat = chatText.getText().toString();
                    runChat();
                    chatText.setText("");

                    chatText.requestFocus();

                    scroll.fullScroll(View.FOCUS_DOWN);
                }
            }
        });

        chatText.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    if (!chatText.getText().toString().trim().equals("")) {
                        arrayAdapter.add("User: " + chatText.getText().toString());

                        chat = chatText.getText().toString();

                        runChat();
                        chatText.setText("");

                        chatText.requestFocus();

                        scroll.fullScroll(View.FOCUS_DOWN);
                    }
                    return true;
                }
                return false;
            }
        });
    }

    public void runChat() {
        if (waitingFor == 0) {
            arrayAdapter.add("Bot: " + bot.respond(chat));
        } else if (waitingFor == 1) {
            name = chat;
            arrayAdapter.add("Bot: " + bot.newUser(1, name));
            waitingFor = 2;
        } else if (waitingFor == 2) {
            age = chat;
            arrayAdapter.add("Bot: " + bot.newUser(2, age));
            bot.transferValues(name, age);
            waitingFor = 0;
        } else {
            arrayAdapter.add("Bot: Error waiting for is either < 0 or > 2");
        }

        listView.smoothScrollToPosition(arrayAdapter.getCount());
    }

    private void welcomeBack() {
        arrayAdapter.add("Bot: Welcome back " + name + "!");
    }

    private void newUser() {
        arrayAdapter.add("Bot: " + bot.newUser(0, ""));
        waitingFor = 1;
    }

    public void read() {
        try {
            // Open file to read saved text
            File savedText = new File(context.getFilesDir(), "SavedText.txt");

            if (!savedText.exists()) {
                newUser = true;
                newUser();
            } else {
                BufferedReader input = new BufferedReader(new FileReader(savedText));

                name = input.readLine();
                age = input.readLine();
                input.close();

                input = new BufferedReader(new FileReader(savedText));

                String firstLine = input.readLine();

                System.out.println("first " + firstLine);

                if (firstLine == null) {
                    newUser = true;
                    newUser();
                } else {
                    input.close();
                    input = new BufferedReader(new FileReader(savedText));
                    newUser = false;
                    name = input.readLine();
                    age = input.readLine();

                    bot.transferValues(name, age);

                    welcomeBack();
                }
            }
        } catch (Exception e) {
            System.out.println("Model() threw exception");
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            File savedText = new File(context.getFilesDir(), "SavedText.txt");

            // Write the final model to a saved file
            BufferedWriter writer = new BufferedWriter(new FileWriter(savedText));
            if (writer != null) {
                writer.write(name);
                writer.newLine();
                writer.write(age);
                writer.newLine();
                writer.close();
            }
        } catch (Exception e) {
            System.out.println("Model.save() threw exception!!!");
            e.printStackTrace();
        }
    }
}
