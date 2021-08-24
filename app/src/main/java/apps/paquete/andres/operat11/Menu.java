package apps.paquete.andres.operat11;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.andres.operat11.R;

public class Menu extends AppCompatActivity {

    private static final int STORAGE_CODE = 1000 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //esta linea de codigo evita la rotacion de la patanlla
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //deja el icono en el action Bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        this.deleteDatabase("bitacoraBD");//BORRA LA BASE DE DATOS CREADA
    }

    //metodo boton cambiar a pagina Calculadora
    public void GetCalculator(View view){
        Intent getcalculadora = new Intent(this,CalculatorActivity.class);
    startActivity(getcalculadora);
    }




    public void GetFull(View view){

    //permiso para acceder al SD card
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            //si el sistema operativo del equipo es >= a Marshmallow 6.0 se requiere habilitar el permiso
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                //se requiere el permiso
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, STORAGE_CODE);
            }else{
                //El permiso esta sin problemas
                //se muestra el activity
                Intent getPaginaFull = new Intent(this,FullActivity.class);
                startActivity(getPaginaFull);
            }

        }else{
            //si el sistema operativo del equipo es < a Marshmallow 6.0 se puede acceder a la SD card
            //se muestra el activity
            Intent getPaginaFull = new Intent(this,FullActivity.class);
            startActivity(getPaginaFull);
        }



    }

    public void GetRadioPage(View view){

            Intent getradioactivity = new Intent(this,RadioActivity.class);
            startActivity(getradioactivity);

    }

    public void GetContadorPage(View view){

        Intent getcontadoractivity = new Intent(this,ContadorActivity.class);
        startActivity(getcontadoractivity);

    }

    public void GetOperatorPage(View view){

        Intent getOperatoractivity = new Intent(this,OperatorActivity.class);
        startActivity(getOperatoractivity);

    }

    //administracion del permiso para almacenar el archivo PDF en el SD card


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case STORAGE_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //se muestra el activity
                    Intent getPaginaFull = new Intent(this,FullActivity.class);
                    startActivity(getPaginaFull);
                }else {
                    Toast.makeText(this,"Permiso Negado",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


}
