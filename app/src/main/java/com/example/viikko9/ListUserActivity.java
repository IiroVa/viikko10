package com.example.viikko9;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListUserActivity extends AppCompatActivity {

    private UserStorage userStorage;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        userStorage = UserStorage.getInstance();

        recyclerView = findViewById(R.id.rvUserView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<User> users = new ArrayList<>();
        users = UserStorage.getInstance().getUsers();
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getLastName().compareToIgnoreCase(o2.getLastName());
            }
        });
        recyclerView.setAdapter(new UserListAdapter(getApplicationContext(), users));
    }


}