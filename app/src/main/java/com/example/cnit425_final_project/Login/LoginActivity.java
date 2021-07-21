/**
 * LoginActivity.java
 *
 * @author Niket Panjwani, Jainam Patel, Anirudh V.
 */
package com.example.cnit425_final_project.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cnit425_final_project.MainActivity.MainActivity;
import com.example.cnit425_final_project.R;
import com.example.cnit425_final_project.Register.RegistrationActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    //Activity Object variable declaration
    Button login;
    EditText email;
    EditText password;
    TextView forgotPassword;
    TextView registerHere;

    //Firebase Auth variable declaration
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //activity object initialization
        login = findViewById(R.id.login_button);
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);

        mAuth = FirebaseAuth.getInstance();

        //Checking if user is already logged in
        if(mAuth.getCurrentUser() != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }


                //login button on click listener
                login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //logging in user
                        mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        //checking if login was successful
                                        if (task.isSuccessful()) {
                                            Toast.makeText(LoginActivity.this,
                                                    "Login Successful!",
                                                    Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                            startActivity(intent);

                                            //Tosting message for unsuccessful login
                                        } else {
                                            Toast.makeText(LoginActivity.this,
                                                    "Email or Password is incorrect!",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                });

        
    }

    //Hide keyboard if clicked on empty objects
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
