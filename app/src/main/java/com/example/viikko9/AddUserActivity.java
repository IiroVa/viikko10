package com.example.viikko9;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddUserActivity extends AppCompatActivity {

    private EditText firstName, lastName, email;
    private RadioGroup degreePrograms;
    private RadioButton degreeProgram;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void addUser(View view){
        Context context;
        firstName = findViewById(R.id.editFirstName);
        lastName = findViewById(R.id.editLastName);
        email = findViewById(R.id.editEmail);

        degreePrograms = findViewById(R.id.radioDegreeProgram);
        degreeProgram = findViewById(degreePrograms.getCheckedRadioButtonId());

        UserStorage.getInstance().addUser(new User(String.valueOf(firstName.getText()), String.valueOf(lastName.getText()), String.valueOf(email.getText()), String.valueOf(degreeProgram.getText())));
        context = this;
        UserStorage.getInstance().saveUsers(context);
    }
}