package facci.sanchezarteagajose.a2kg.Login;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import facci.sanchezarteagajose.a2kg.Consejo;
import facci.sanchezarteagajose.a2kg.Contacto;
import facci.sanchezarteagajose.a2kg.R;
import facci.sanchezarteagajose.a2kg.UserAreaActivity;

public class HORARIO extends AppCompatActivity implements View.OnClickListener {
        EditText ethora, etdescripcion, etfecha, etnombre;//CREACION DE VARIABLES

        Button btn_reservar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario);

                etfecha = findViewById(R.id.EditT_fecha);  //AQUI ESTOY DEFINIENDO LOS VALORES DE LAS VARIABLES DEL ACTIVITY REGISTRO
                ethora = findViewById(R.id.EditT_hora);
                etdescripcion = findViewById(R.id.EditT_descripcion);
                etnombre = findViewById(R.id.EditT_actividad);
                btn_reservar = findViewById(R.id.Btn_reserva);     //AQUI HAGO CLICK EN EL BOTON Y HACE LO SIGUIENTE
                btn_reservar.setOnClickListener(this);

            }

            @Override
            public void onClick(View view) {
                 //AL HACER CLIC TOMA LOS VALORES FINALES Y LOS PASA A STRING
                final String nombre = etnombre.getText().toString();
                final String hora = ethora.getText().toString();
                final String fecha = etfecha.getText().toString();
                final String descripcion = etdescripcion.getText().toString();



                final Response.Listener<String> respoLister = new Response.Listener<String>() {  //LLAMA AL ESCUCHADOR
                    @Override
                    public void onResponse(String response) {


                        if (etnombre.getText().toString().isEmpty()) {  //SE APLICA UNA CONDICIÓN SI LOS CAMPOS ESTÁN VACÍOS

                            Toast.makeText(HORARIO.this, "ingrese nombre", Toast.LENGTH_SHORT).show();
                            //SE MANDA MENSAJE

                        } else if (etfecha.getText().toString().isEmpty()) {

                            Toast.makeText(HORARIO.this, "Debe ingresar la fecha", Toast.LENGTH_SHORT).show();

                        } else if (ethora.getText().toString().isEmpty()) {


                            Toast.makeText(HORARIO.this, "Debe ingresar la hora", Toast.LENGTH_SHORT).show();

                        } else if (etdescripcion.getText().toString().isEmpty()) {

                            Toast.makeText(HORARIO.this, "Debe ingresar  descripcio", Toast.LENGTH_SHORT).show();

                        }
                        try {
                            JSONObject jsonReponse = new JSONObject(response);
                            boolean success = jsonReponse.getBoolean("success");
                            //YA SI NO HAY NINGUN PROBLEMA HACE LO SIGUIENTE....
                            if (success) {
                                //AQUÍ DIGO SI SE REGISTRÓ CORRECTAMENTE MANDO UN MENSAJE
                                Toast.makeText(HORARIO.this, "reservacion guardada", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(HORARIO.this, UserAreaActivity.class);
                                HORARIO.this.startActivity(intent);

                            } else {
                                //DE CASO CONTRARIO DIGO QUE EL ALUMNO YA ESTÁ REGISTRADO

                                AlertDialog.Builder builder = new AlertDialog.Builder(HORARIO.this);
                                builder.setMessage("reservacion  guardada ").setNegativeButton("Volver", null).create().show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                HorarioRequest horarioRequestt = new HorarioRequest(nombre, fecha,hora,descripcion,respoLister);
                RequestQueue queue = Volley.newRequestQueue(HORARIO.this);
                queue.add(horarioRequestt);  //AQUI TOMO LAS VARIABLES Y LAS EJECUTO

            }
    public boolean onOptionsItemSelected(MenuItem item){

        Intent intent;
        switch (item.getItemId()){
            case R.id.contacto:
                intent =new Intent (HORARIO.this, Contacto.class);
                startActivity(intent);
                break;
            case R.id.consejo:
                intent =new Intent (HORARIO.this,Consejo.class);
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
