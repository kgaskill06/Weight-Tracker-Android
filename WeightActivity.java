package com.example.weighttracker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class WeightActivity extends AppCompatActivity {

    private User mUser;
    private WeightDatabaseActivity mWeightDb;
    TextView mTargetGoalWeight;
    WeightEntity mNewWeightEntry;
    TableLayout mTableLayout;
    TableRow mRecordsRow;

    private static final int PERMISSION_REQUEST_SMS = 1;
    private static final int PERMISSION_READ = 1;
    private final int ADD_RECORD_ACTIVITY =1;
    private final int UPDATE_RECORD_ACTIVITY = 1;
    private final int DELETE_RECORD_ACTIVITY = 1;


@Override
    protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_weights_history);

    // assigns table layout to XML id weightHistory and target goal to goal_weight
    mTableLayout = (TableLayout)findViewById(R.id.weightHistory);
    mTargetGoalWeight = (TextView)findViewById(R.id.goal_weight);

    // singleton database instance launch
    mWeightDb = WeightDatabaseActivity.getInstance(getApplicationContext());

    // User login loads object and gets data
    Intent intent = getIntent();
    mUser = (User) getIntent().getSerializableExtra("user");


}

public void addRecordOnClick(View view) {
    Intent intent = new Intent(this, AddRecordActivity.class);
    startActivity(intent);

}

// checks for SMS permissions, if permission not accepted, sends request, else does nothing
private void checkSMSPermission(){
    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) !=
    PackageManager.PERMISSION_GRANTED) {
        System.out.println("Did not accept permission");

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},
                PERMISSION_REQUEST_SMS);
    } else {
        System.out.print("Permission Privilege Accepted");
    }
}


}
