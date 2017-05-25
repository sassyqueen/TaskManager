package taskmanager.android.myapplicationdev.com.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<Task> taskAL;
    Button btnAddTask;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.lv);
        btnAddTask = (Button) findViewById(R.id.btnAddTask);
        taskAL = new ArrayList<Task>();

        DBHelper db = new DBHelper(MainActivity.this);

        taskAL = db.getAllTasks();


        ArrayAdapter<Task> adapter = new ArrayAdapter<Task>(this, android.R.layout.simple_list_item_2, android.R.id.text1, taskAL){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);
                text1.setText((position+1)+" "+taskAL.get(position).getDescription());
                text2.setText(taskAL.get(position).getName());
                return view;
            }
        };
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        db.close();

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(i, 1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Only handle when 2nd activity closed normally
        //  and data contains something
        if (resultCode == RESULT_OK) {

            // Get data passed back from 2nd activity
            DBHelper db = new DBHelper(MainActivity.this);

            taskAL.clear();
            taskAL = db.getAllTasks();
            ArrayAdapter<Task> adapter = new ArrayAdapter<Task>(this, android.R.layout.simple_list_item_2, android.R.id.text1, taskAL){
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);
                    text1.setText((position+1)+" "+taskAL.get(position).getName());
                    text2.setText(taskAL.get(position).getDescription());
                    return view;
                }
            };
            lv.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            db.close();



        }
    }

}
