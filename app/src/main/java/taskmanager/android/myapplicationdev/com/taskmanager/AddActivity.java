package taskmanager.android.myapplicationdev.com.taskmanager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText etName, etDescription;
    Button addTask,cancel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etName = (EditText)findViewById(R.id.etName);
        etDescription = (EditText)findViewById(R.id.etDescription);
        addTask = (Button)findViewById(R.id.addTask);
        cancel = (Button)findViewById(R.id.cancel);

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                DBHelper db = new DBHelper(AddActivity.this);
                db.insertTask(etName.getText().toString(), etDescription.getText().toString());
                setResult(RESULT_OK, i);
                i.putExtra("name", etName.getText().toString());
                finish();


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                setResult(RESULT_OK, i);
                finish();

            }
        });
    }
}
