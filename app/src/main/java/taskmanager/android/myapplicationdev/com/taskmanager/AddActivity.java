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

    EditText etName, etDescription, etTime;
    Button addTask,cancel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etName = (EditText)findViewById(R.id.etName);
        etDescription = (EditText)findViewById(R.id.etDescription);
        addTask = (Button)findViewById(R.id.addTask);
        cancel = (Button)findViewById(R.id.cancel);
        etTime = (EditText)findViewById(R.id.etTime);

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                DBHelper db = new DBHelper(AddActivity.this);
                db.insertTask(etName.getText().toString(), etDescription.getText().toString());
                setResult(RESULT_OK, i);

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, Integer.parseInt(etTime.getText().toString()));


                //create new PendingIntent and add to AlarmManager
                Intent intent = new Intent(AddActivity.this, TaskBroadcastReceiver.class);
                intent.putExtra("name", etName.getText().toString());
                intent.putExtra("desc", etDescription.getText().toString());
                int reqCode = 12345;
                PendingIntent pendingIntent = PendingIntent.getBroadcast(AddActivity.this, reqCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);

                //Get AlarmManager instance

                AlarmManager am = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);

                // set alarm
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);

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
