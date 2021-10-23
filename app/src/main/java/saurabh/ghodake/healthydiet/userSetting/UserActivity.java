package saurabh.ghodake.healthydiet.userSetting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import saurabh.ghodake.healthydiet.R;
import saurabh.ghodake.healthydiet.addMeal.ManuallyInputActivity;
import saurabh.ghodake.healthydiet.login.LoginActivity;
import saurabh.ghodake.healthydiet.login.User;
import saurabh.ghodake.healthydiet.main.MainActivity;
import saurabh.ghodake.healthydiet.main.ReportActivity;


public class UserActivity extends AppCompatActivity {

    public static String uid;

    public TextView textView_name;
    public ImageView imageView_userImage;
    //Used to get the username information stored in the database
    public String gender,username;

    public DatabaseReference databaseReference;
    public FirebaseStorage storage;
    public StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);


        databaseReference = FirebaseDatabase.getInstance().getReference();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();


        textView_name=(TextView) findViewById(R.id.userSetting_user_name);
        getUsername_fromDatabse();

        //add click listener on LinerLayout
        final LinearLayout llprofile = findViewById(R.id.setting_ll_profile);
        llprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(UserActivity.this, MyProfileActivity.class);
                if (intent != null) {
                    UserActivity.this.startActivity(intent);
                }
            }
        });

        final LinearLayout MyFoodList = findViewById(R.id.MyFoodList);
        MyFoodList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivity.this,MyFoodList.class);
                if(intent!=null){
                    UserActivity.this.startActivity(intent);
                }
            }
        });

        final LinearLayout llreport = findViewById(R.id.setting_ll_report);
        llreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(UserActivity.this, ReportActivity.class);
                if (intent != null) {
                    UserActivity.this.startActivity(intent);
                }
            }
        });

        final LinearLayout llLogout = findViewById(R.id.setting_ll_log_out);
        llLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Log.i("LogOutActivity", "Log out ");

                // Use the Builder class for convenient dialog construction
                AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
                // 2. Chain together various setter methods to set the dialog characteristics
                builder.setTitle(R.string.dialog_delete_title)
                        .setMessage(R.string.dialog_delete_msg)
                        // Add the buttons
                        .setPositiveButton(R.string.delete, new
                                DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent intent = new Intent(UserActivity.this, LoginActivity.class);
                                        if (intent != null) {
                                            UserActivity.this.startActivity(intent);
                                        }
                                    }
                                })
                        .setNegativeButton(R.string.cancel, new
                                DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        // User cancelled the dialog
                                        // Nothing happens
                                    }
                                });
                // Create the AlertDialog object
                builder.create().show();
            }
        });


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(bottomNavigationView.getMenu().getItem(2).getItemId());
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        startActivity(new Intent(UserActivity.this, MainActivity.class));
                        break;
                    case R.id.navigation_add:
                        userChoice();
                        break;
                    case R.id.navigation_user:
                        break;
                }
                return true;
            }
        });
    }
    public void userChoice(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setIcon(getDrawable(R.drawable.icon_alert));
        builder.setTitle("Record Method");
        final String []items=new String[]{"Manual","Photo"};
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    onManualClick();
                    dialog.dismiss(); //When the user selects a value, the dialog box disappears
                }
            }
        });
        builder.show();
    }

    public void onManualClick(){
        Intent intent = new Intent(UserActivity.this, ManuallyInputActivity.class);
        if (intent != null) {
            startActivity(intent);
        }
    }


    public void getUsername_fromDatabse(){
        //Get userID
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        //Get the username of the current user from the database
        databaseReference.child("Users").child(uid)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.getValue(User.class);

                        if(user!=null){

                            for(DataSnapshot d: snapshot.getChildren()){

                                String userInfo_Key = d.getKey();
                                if(userInfo_Key.equals("username")) {
                                    username = d.getValue().toString();
                                    textView_name.setText(username);
                                }
                            }
                        }else{
                            Toast.makeText(UserActivity.this,"display username ERROR!!!",Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}