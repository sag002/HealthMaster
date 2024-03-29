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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import saurabh.ghodake.healthydiet.R;

public class RegisterActivity extends AppCompatActivity{
    private FirebaseAuth mAuth;
    private EditText Name,Email,Password,Confirm_password;
    private DatabaseReference databaseReference;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logger_signup);
        mAuth = FirebaseAuth.getInstance();
        //define buttons
        final Button RegisterInBtn=findViewById(R.id.btn_register_in);;
        final Button BackToSignUpBtn = findViewById(R.id.btn_back_to_sign_in);

        //get user input text
        Name=(EditText) findViewById(R.id.logger_signup_username);
        Email=(EditText) findViewById(R.id.logger_signup_email);
        Password=(EditText) findViewById(R.id._logger_signup_password);
        Confirm_password=(EditText) findViewById(R.id.logger_signup_password_confirm);
        progressBar=(ProgressBar) findViewById(R.id.sign_up_progress_bar);
        progressBar.setVisibility(View.GONE);

        BackToSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                RegisterActivity.this.startActivity(intent);
            }
        });

        RegisterInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                progressBar.setVisibility(View.VISIBLE);
                registerusers(v);
                //Store Users-username in UsersFood
                initUsersFood_username();

            }
        });
    }

    public void registerusers(final View v){

        final String username=Name.getText().toString().trim();
        final String email=Email.getText().toString().trim();
        final String password=Password.getText().toString().trim();
        final String confirm_password=Confirm_password.getText().toString().trim();


        if(username.isEmpty()){
            Name.setError("User name is required");
            Name.requestFocus();
            return;
        }
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
        if(confirm_password.isEmpty()){
            Confirm_password.setError("Password confirmation required");
            Confirm_password.requestFocus();
            return;
        }
        if(!confirm_password.equals(password)){
            Confirm_password.setError("Password doesn't match");
            Confirm_password.requestFocus();
            return;
        }


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user=new User(username,email,password,confirm_password);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(v.GONE);
                                    if(task.isSuccessful()){
                                        // send verification link
                                        FirebaseUser fuser = mAuth.getCurrentUser();
                                        fuser.sendEmailVerification().
                                                addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if(task.isSuccessful()){
                                                            Toast.makeText(RegisterActivity.this, "Registered Successfully, please verify your email.",
                                                                    Toast.LENGTH_SHORT).show();

                                                        }else{
                                                            Toast.makeText(RegisterActivity.this, task.getException().getMessage(),
                                                                    Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });

                                        Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                                        if ( i != null) {
                                            RegisterActivity.this.startActivity(i);
                                        }
                                    }
                                    else {
                                        Toast.makeText(RegisterActivity.this,"Register Failed",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                        else {
                            Toast.makeText(RegisterActivity.this,"Register Failed",Toast.LENGTH_LONG).show();
                        }
                        // ...
                    }
                });
    }

    public void initUsersFood_username(){

        final String str_usename =Name.getText().toString().trim();

        //Store Users-username in UsersFood
        databaseReference =  FirebaseDatabase.getInstance().getReference("UsersFood");

        String newKey = databaseReference.push().getKey();
        databaseReference.child(newKey).setValue(str_usename);

    }


}
