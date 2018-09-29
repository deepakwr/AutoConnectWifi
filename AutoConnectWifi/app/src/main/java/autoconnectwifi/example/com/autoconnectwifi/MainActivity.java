package autoconnectwifi.example.com.autoconnectwifi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WifiHandler.getInstance(this);

    }

    public void login(final View view)
    {
        String emailAdress  = ((EditText)findViewById(R.id.editEmail)).getText().toString();
        String password  = ((EditText)findViewById(R.id.editPassword)).getText().toString();

        if(emailAdress.contentEquals("abc@xyz.com") && password.contentEquals("password"))
        {
            Toast.makeText(this,"Login Successfull",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Invalid email or password. Please check",Toast.LENGTH_SHORT).show();
        }
    }

    public void signUp(final View view)
    {
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
        Toast.makeText(this,"Opening signup page",Toast.LENGTH_SHORT);

    }
}
