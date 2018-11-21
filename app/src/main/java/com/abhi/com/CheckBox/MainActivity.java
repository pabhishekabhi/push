package com.abhi.com.CheckBox;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import com.abhi.com.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


public class MainActivity extends Activity {
    LinearLayout linearMain;
    CheckBox checkBox;

    LinkedHashMap<Integer, String> alphabet = new LinkedHashMap<Integer, String>();
    LinkedHashMap<Integer, String> alphabetSelected = new LinkedHashMap<Integer, String>();

    ArrayList<String> array = new ArrayList<>();

    StringBuilder build = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box);
        linearMain = (LinearLayout) findViewById(R.id.linearMain);

        if(array.size()==0) {
            array.add("one");
            array.add("two");
            array.add("three");
            array.add("four");
            array.add("five");
            array.add("six");
        }

        for(int i = 0; i<array.size(); i++) {

            alphabet.put(i, array.get(i));

        }

        Set<?> set = alphabet.entrySet();
        // Get an iterator
        Iterator<?> i = set.iterator();
        // Display elements
        while (i.hasNext()) {
            @SuppressWarnings("rawtypes")
            Map.Entry me = (Map.Entry) i.next();
            System.out.print(me.getKey() + ": ");
            System.out.println(me.getValue());

            checkBox = new CheckBox(this);
            checkBox.setId(Integer.parseInt(me.getKey().toString()));
            checkBox.setText(me.getValue().toString());
            checkBox.setOnClickListener(getOnClickDoSomething(checkBox));
            linearMain.addView(checkBox);
        }

    }

    View.OnClickListener getOnClickDoSomething(final Button button) {
        return new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("Button Id" + button.getId());

                if(!alphabetSelected.containsKey(button.getId()))
                alphabetSelected.put(button.getId(),button.getText().toString());
                else
                    alphabetSelected.remove(button.getId());

                Log.d("Selected Size",""+alphabetSelected.size());

                build = new StringBuilder();

                for (Map.Entry<Integer,String> entry : alphabetSelected.entrySet()) {
                    int key = entry.getKey();
                    String value = entry.getValue();
                    Log.d("Selected Size Added",""+key +"   "+ value);
                    if (build.length()>0)
                        build.append(","+value);
                    else
                        build.append(value);
                }

                Log.d("Selected List Printed",""+build.toString());

            }
        };
    }

}


