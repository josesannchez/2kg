package facci.sanchezarteagajose.a2kg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        Intent intent = getIntent();
        String nombres= intent.getStringExtra("nombres");
        String correo = intent.getStringExtra("correo");


        TextView tvWelcomeMsg = (TextView) findViewById(R.id.tvWelcomeMsg);
        EditText etnombres = (EditText) findViewById(R.id.etnombress);
        EditText etcorreo = (EditText) findViewById(R.id.etcorreo);

        // Display user details
        String message = nombres+ " welcome to your user area";
        tvWelcomeMsg.setText(message);
        etcorreo.setText(correo
        );
        etnombres.setText(nombres);
    }
    public boolean onOptionsItemSelected(MenuItem item){

        Intent intent;
        switch (item.getItemId()){
            case R.id.contacto:
                intent =new Intent (UserAreaActivity.this,Contacto.class);
                startActivity(intent);
                break;
            case R.id.consejo:
                intent =new Intent (UserAreaActivity.this,Consejo.class);
                startActivity(intent);
                break;
        }
        return  true;
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menus,menu);
        return true;



    }
}
