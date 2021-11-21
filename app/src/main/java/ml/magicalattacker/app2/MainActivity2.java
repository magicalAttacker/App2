package ml.magicalattacker.app2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    EditText user;
    EditText pass;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initDatabase();
    }

    private void initDatabase() {
        MyHelper helper = new MyHelper(this, "user.db", null, 1);
        db = helper.getWritableDatabase();
    }

    public void signIn(View view) {
        user = findViewById(R.id.editTextTextPersonName3);
        pass = findViewById(R.id.editTextTextPassword3);
        String username = user.getText().toString();
        String password = pass.getText().toString();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        db.insert("user", null, values);
        finish();
    }
}