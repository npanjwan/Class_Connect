/**
 * RegistrationActivity.java
 *
 * @author Niket Panjwani, Jainam Patel, Anirudh V.
 */
package com.example.cnit425_final_project.Register;

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

import com.example.cnit425_final_project.Login.LoginActivity;
import com.example.cnit425_final_project.MainActivity.MainActivity;
import com.example.cnit425_final_project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegistrationActivity extends AppCompatActivity {

    EditText fullname, email, phone, password, confirmPassword;
    Button register;
    TextView login_here;
    FirebaseAuth firebaseAuth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullname = findViewById(R.id.register_name);
        email = findViewById(R.id.register_email);
        phone = findViewById(R.id.register_phone);
        password = findViewById(R.id.register_password);
        confirmPassword = findViewById(R.id.register_confirm_password);
        register = findViewById(R.id.register_button);
        firebaseAuth = FirebaseAuth.getInstance();
        login_here = findViewById(R.id.login_here);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    firebaseAuth.getCurrentUser().sendEmailVerification();
                                    updateName();
                                    reference = FirebaseDatabase.getInstance().getReference("Users")
                                                    .child(firebaseAuth.getCurrentUser().getUid());
                                    HashMap<String,String> map = new HashMap<>();
                                    map.put("id",firebaseAuth.getCurrentUser().getUid());
                                    map.put("email",email.getText().toString());
                                    map.put("name",fullname.getText().toString());
                                    reference.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                                startActivity(intent);
                                            }
                                        }
                                    });
                                } else {
                                    Toast.makeText(RegistrationActivity.this,
                                            "Registration failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        login_here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void updateName(){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        UserProfileChangeRequest profile =
                new UserProfileChangeRequest.Builder().setDisplayName(fullname.getText().toString()).build();
        user.updateProfile(profile);
    }

}
