package com.example.viikko9;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class AddUserActivity extends AppCompatActivity {

    private EditText firstName, lastName, email;
    private RadioGroup degreePrograms;
    private RadioButton degreeProgram;
    private CheckBox mSc, bSc, lIc, pHd;


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
        mSc = findViewById(R.id.msCheckBox);
        bSc = findViewById(R.id.bcCheckBox);
        lIc = findViewById(R.id.lciCheckBox);
        pHd = findViewById(R.id.phdCheckBox);
        ArrayList<String> degree = new ArrayList<>();
        if(pHd.isChecked() == true){
            degree.add("Doctoral degree");
        }
        if(lIc.isChecked() == true){
            degree.add("Licenciate");
        }
        if(mSc.isChecked() == true){
            degree.add("M.Sc. degree");
        }
        if(bSc.isChecked() == true){
            degree.add("B.Sc. degree");
        }
        StringBuilder str = new StringBuilder("");

        for (int i = 0; i < degree.size(); i++) {
            str.append(degree.get(i));
            if (i < degree.size() - 1) {
                str.append(", ");
            }
        }
        //Log.d("OMA",str.toString());
        degreePrograms = findViewById(R.id.radioDegreeProgram);
        degreeProgram = findViewById(degreePrograms.getCheckedRadioButtonId());

        UserStorage.getInstance().addUser(new User(String.valueOf(firstName.getText()), String.valueOf(lastName.getText()), String.valueOf(email.getText()), String.valueOf(degreeProgram.getText()), String.valueOf(str)));
        context = this;
        UserStorage.getInstance().saveUsers(context);
    }
}