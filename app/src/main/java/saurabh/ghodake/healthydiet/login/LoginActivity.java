package saurabh.ghodake.healthydiet.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import saurabh.ghodake.healthydiet.R;
import saurabh.ghodake.healthydiet.loginFirstTimeUserInfo.InfoActivity_1;
import saurabh.ghodake.healthydiet.main.MainActivity;


public class LoginActivity extends AppCompatActivity{

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private EditText Email, Password;
    private ProgressBar progressBar;

    //define login status
    public final int LOGIN_SUCCESS_CODE = 100;
    public final int LOGIN_FAIL_CODE = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logger_signin);
        mAuth = FirebaseAuth.getInstance();

        //define buttons
        final Button SignInBtn = findViewById(R.id.btn_sign_in);
        final Button SignUpBtn = findViewById(R.id.btn_sign_up);
        final Button ResetBtn = findViewById(R.id.btn_reset_password);
        Email=(EditText) findViewById(R.id.logger_signin_email);
        Password=(EditText) findViewById(R.id.logger_signin_password);
        progressBar=(ProgressBar) findViewById(R.id.sign_in_progress_bar);
        progressBar.setVisibility(View.GONE);

        SignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent register = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(register);
            }
        });

        SignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(v.VISIBLE);
                userlogin(v);
            }
        });

        ResetBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent resetpassword = new Intent(LoginActivity.this, ResetPasswordActivity.class);
                LoginActivity.this.startActivity(resetpassword);
            }
        });
    }

    public void userlogin(final View v){
        String email=Email.getText().toString().trim();
        String password=Password.getText().toString().trim();

        if(email.isEmpty()){
            Email.setError("Email is required");
            Email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Email.setError("Please enter a valid email");
            Email.requestFocus();
            return;
        }
        if(password.isEmpty()){
            Password.setError("Password is required");
            Password.requestFocus();
            return;
        }
        if(password.length()<6){
            Password.setError("Minimum password length should be 6 characters");
            Password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(v.GONE);
                //Verify email address
                if (task.isSuccessful()){

                    if(mAuth.getCurrentUser().isEmailVerified()){
                        databaseReference =  FirebaseDatabase.getInstance().getReference("Users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());

                        //Determine if it is the first time to log in from the database
                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot data: snapshot.getChildren()){

                                    if(data.getKey().equals("notFirstTime")){

                                        if(data.getValue().equals("false")){
                                            //if this is the first time user log in, then enter the User Info Add interface

                                            //Enter the User Info Add interface
                                            Toast.makeText(LoginActivity.this,"Verified successfully, please add your information!",Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(LoginActivity.this, InfoActivity_1.class));
                                            break;
                                        }
                                        else{
                                            // This is an existing user
                                            Toast.makeText(LoginActivity.this,"Sign in successfully,welcome back!",Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                        }
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }else{
                        Toast.makeText(LoginActivity.this, "Please verify your email address",
                                Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(LoginActivity.this,"Sign in failed, incorrect email or password!",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}