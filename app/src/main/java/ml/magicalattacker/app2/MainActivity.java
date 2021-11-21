package ml.magicalattacker.app2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatabase();
    }

    private void initDatabase() {
        MyHelper helper = new MyHelper(this, "user.db", null, 1);
        db = helper.getReadableDatabase();
    }

    public void login(View view) {
        EditText user = findViewById(R.id.editTextTextPersonName);
        EditText pass = findViewById(R.id.editTextTextPassword);
        String username = user.getText().toString();
        String password = pass.getText().toString();
        boolean found = false;
        StringBuilder all = new StringBuilder();
        ArrayList<UserEntry> arrayListOfToken = new ArrayList<>();
        Cursor cursor = db.query("user", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String correctUsername = cursor.getString(cursor.getColumnIndexOrThrow("username"));
            String correctPassword = cursor.getString(cursor.getColumnIndexOrThrow("password"));
            arrayListOfToken.add(new UserEntry(correctUsername, correctPassword));
        }
        for (UserEntry entry:arrayListOfToken
             ) {
            if (username.equals(entry.getUsername()) && password.equals(entry.getPassword())) {
                found = true;
            }
            all.append(entry.getUsername()).append(" ").append(entry.getPassword()).append("\n");
        }
        if (found) {
            Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
        } else if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "账号或密码未输入", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "账号或密码错误", Toast.LENGTH_LONG).show();
        }
        Toast.makeText(this, "用户信息列表：\n" + all, Toast.LENGTH_LONG).show();
    }

    public void signUp(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}