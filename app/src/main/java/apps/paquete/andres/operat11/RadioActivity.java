package apps.paquete.andres.operat11;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.PermissionRequest;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.Toast;
import com.example.andres.operat11.R;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import apps.paquete.andres.operat11.constantes.Constantes;

public class RadioActivity extends AppCompatActivity {

    //variable graficas tipo EditText
    private EditText etNumFac;
    private EditText etNumVuelo;
    private EditText etDesde;
    private EditText etHacia;
    private EditText etDoorHora;
    private EditText etDoorMin;
    //CREW
    private EditText et1;
    private EditText et2;
    private EditText et3;
    private EditText et4;
    //ARICRAFT
    private EditText et5;
    private EditText et6;
    private EditText et7;
    private EditText et8;
    //miscelaneo
    private EditText etFuel;
    private EditText etPax;
    private EditText etPaxHombres;
    private EditText etPaxMujeres;
    private EditText etPaxNinos;
    private EditText etPaxNibra;
    private EditText etCarga;

    private TableLayout tableLayout;
    //variable Spinner
    private Spinner spinner;

    //CONSULTA DEL SPINNER
    ArrayList<String> listaVuelos;
    ArrayList<TablaRadio> vuelosList;

    //para llamar datos del Fullactivity
    FullActivity fullActivity = new FullActivity();



    TablaRadio tablaRadio;

    //base de datos
    AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "radioBD", null, 2);

    ///variables publicas para base de datos
    public static String numfac = "";
    public static String numvuelo = "";
    public static String desde = "";
    public static String hacia = "";
    public static String Puertas_hor = "";
    public static String Puertas_min = "";
    public static String Prendida_hor = "";
    public static String Prendida_min = "";
    public static String Apagada_hor = "";
    public static String Apagada_min = "";
    public static String Decolage_hor = "";
    public static String Decolage_min = "";
    public static String Aterrizaje_hor = "";
    public static String Aterrizaje_min = "";
    public static String Pax = "";
    public static String Pax_h = "";
    public static String Pax_m = "";
    public static String Pax_n = "";
    public static String Pax_nibra = "";
    public static String Carga = "";
    public static String Fuel = "";



//INICIO CODIGO
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);
        //esta linea de codigo evita la rotacion de la patanlla
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Se conectan las variables logicas con la interface grafica
        //objetos EditText
        etNumFac = (EditText) findViewById(R.id.etMatricula);
        etNumVuelo = (EditText) findViewById(R.id.etnumVuelo);
        etDesde = (EditText) findViewById(R.id.etDesde);
        etHacia = (EditText) findViewById(R.id.etHacia);
        etDoorHora = (EditText) findViewById(R.id.etDoorHora);
        etDoorMin = (EditText) findViewById(R.id.etDoorMin);
        //objetos EditText CREW
        et1 = (EditText) findViewById(R.id.etHoraStartTrip);
        et2 = (EditText) findViewById(R.id.etMinStartTrip);
        et3 = (EditText) findViewById(R.id.etHoraFinishTrip);
        et4 = (EditText) findViewById(R.id.etMinFinishTrip);
        //objetos EditText AIRCRAFT
        et5 = (EditText) findViewById(R.id.etHoraStartAer);
        et6 = (EditText) findViewById(R.id.etMinStartAer);
        et7 = (EditText) findViewById(R.id.etHoraFinishAer);
        et8 = (EditText) findViewById(R.id.etMinFinishAer);
        //objetos miscelaneos
        etFuel = (EditText) findViewById(R.id.etRemanente);
        etPax = (EditText) findViewById(R.id.etPaxTotal);
        etPaxHombres = (EditText) findViewById(R.id.etHombres);
        etPaxMujeres = (EditText) findViewById(R.id.etMujeres);
        etPaxNinos = (EditText) findViewById(R.id.etNinos);
        etPaxNibra = (EditText) findViewById(R.id.etNibra);
        etCarga = (EditText) findViewById(R.id.etCarga);

        tableLayout=(TableLayout)findViewById(R.id.tableDatos);
        spinner=(Spinner) findViewById(R.id.spSeleccion);


//mantener datos DE PASAJEROS EN EL ACTIVITY

        SharedPreferences preferences = getSharedPreferences("full", Context.MODE_PRIVATE);
        etDoorHora.setText(preferences.getString("DH",""));
        etDoorMin.setText(preferences.getString("DM",""));
       etPaxHombres.setText(preferences.getString("H", ""));
       etPaxMujeres.setText(preferences.getString("M",""));
       etPaxNinos.setText(preferences.getString("N", ""));
       etPaxNibra.setText(preferences.getString("NB", ""));

//metodos para cargar el spinner con los registros
        ConsultarListaVuelos();
        cargarSpinner();


        //foco
        findViewById(R.id.etMatricula).requestFocus();



    }//fin OnCreate


///METODOS DEL ACTIVITY

    ////////CODIGO PARA MANTENER DATOS EN LOS EDIT TEXT/////////////////
    public void cargarActivity() {
        //codigo para mostarlos datos y se mantengan en el activity
        SharedPreferences preferencia = getSharedPreferences("full", Context.MODE_PRIVATE);
        SharedPreferences.Editor Obj_editor = preferencia.edit();
        Obj_editor.putString("DH", etDoorHora.getText().toString());
        Obj_editor.putString("DM", etDoorMin.getText().toString());
        Obj_editor.putString("H", etPaxHombres.getText().toString());
        Obj_editor.putString("M", etPaxMujeres.getText().toString());
        Obj_editor.putString("N", etPaxNinos.getText().toString());
        Obj_editor.putString("NB", etPaxNibra.getText().toString());
        Obj_editor.commit();
    }




    ///////////////////////////////codigo del SPINNER/////////////////////////

    private void cargarSpinner(){



        //adaptador que permite vincular el Array MatriculaFAC con el spinner y tambien permite vincular el diseño de los items en el spinner "spinner_item_edit"
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, R.layout.spinner_item_edit, listaVuelos);
        spinner.setAdapter(adapter);

        //metodo que permite la seleccion de la aeronave y muestra el TIPO y la matricula HK
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position != 0) {
                    etNumVuelo.setText(vuelosList.get(position - 1).getNumvuelo().toString());
                    etNumFac.setText(vuelosList.get(position - 1).getNumFAC().toString());
                    etDesde.setText(vuelosList.get(position - 1).getDesde().toString());
                    etHacia.setText(vuelosList.get(position - 1).getHacia().toString());
                    etDoorHora.setText(vuelosList.get(position - 1).getPuertas_hor().toString());
                    etDoorMin.setText(vuelosList.get(position - 1).getPuertas_min().toString());
                    et1.setText(vuelosList.get(position - 1).getPrendida_hor().toString());
                    et2.setText(vuelosList.get(position - 1).getPrendida_min().toString());
                    et3.setText(vuelosList.get(position - 1).getApagada_hor().toString());
                    et4.setText(vuelosList.get(position - 1).getApagada_min().toString());
                    et5.setText(vuelosList.get(position - 1).getDecolage_hor().toString());
                    et6.setText(vuelosList.get(position - 1).getDecolage_min().toString());
                    et7.setText(vuelosList.get(position - 1).getAterrizaje_hor().toString());
                    et8.setText(vuelosList.get(position - 1).getAterrizaje_min().toString());
                    etPax.setText(vuelosList.get(position - 1).getPax().toString());
                    etPaxHombres.setText(vuelosList.get(position - 1).getPax_h().toString());
                    etPaxMujeres.setText(vuelosList.get(position - 1).getPax_m().toString());
                    etPaxNinos.setText(vuelosList.get(position - 1).getPax_n().toString());
                    etPaxNibra.setText(vuelosList.get(position - 1).getPax_nibra().toString());
                    etCarga.setText(vuelosList.get(position - 1).getCarga().toString());
                    etFuel.setText(vuelosList.get(position - 1).getFuel().toString());

                }else {
                    if(fullActivity.flag ==1){
                        //presenta los datos de la cabecera

                        etNumFac.setText(fullActivity.numFAC);
                        etNumVuelo.setText(fullActivity.NumeroVuelo);
                        etDesde.setText(fullActivity.desde);
                        etHacia.setText(fullActivity.hacia);
                        et1.setText(fullActivity.Prendida_hor);
                        et2.setText(fullActivity.Prendida_min);
                        et3.setText(fullActivity.Apagada_hor);
                        et4.setText(fullActivity.Apagada_min);
                        et5.setText(fullActivity.Decolage_hor);
                        et6.setText(fullActivity.Decolage_min);
                        et7.setText(fullActivity.Aterrizaje_hor);
                        et8.setText(fullActivity.Aterrizaje_min);
                        etPax.setText(fullActivity.Pax);
                        etCarga.setText(fullActivity.Carga);



                        fullActivity.flag = 0;
                    }else {
                        Limpiar();
                    }



                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
////////////////////////////////////////////////////////////////////////////////////////////


    ////////////******************//////////// BASE DE DATOS//////////////***************///////

    //METODO PARA INSERTAR DATOS EN LA BASE DE DATOS RADIO------------------------------------------
    public void Insertar(View view){

        cargarActivity();

       //se cargan las variables Publicas con los datos del ingresados por el usuario
        numfac = etNumFac.getText().toString();
        numvuelo = etNumVuelo.getText().toString();
        desde = etDesde.getText().toString();
        hacia = etHacia.getText().toString();
        Puertas_hor = etDoorHora.getText().toString();
        Puertas_min = etDoorMin.getText().toString();
        Prendida_hor = et1.getText().toString();
        Prendida_min = et2.getText().toString();
        Apagada_hor = et3.getText().toString();
        Apagada_min = et4.getText().toString();
        Decolage_hor = et5.getText().toString();
        Decolage_min = et6.getText().toString();
        Aterrizaje_hor = et7.getText().toString();
        Aterrizaje_min = et8.getText().toString();
        Pax = etPax.getText().toString();
        Pax_h = etPaxHombres.getText().toString();
        Pax_m = etPaxMujeres.getText().toString();
        Pax_n = etPaxNinos.getText().toString();
        Pax_nibra = etPaxNibra.getText().toString();
        Carga = etCarga.getText().toString();
        Fuel = etFuel.getText().toString();



        //verifica que los campos tengan datos para ingresar a la base de datos
        if( !numfac.isEmpty() &&!numvuelo.isEmpty()){



            admin.abrirBD();
            admin.IngresarDatosRadio();
            admin.cerrarBD();
            ConsultarListaVuelos();
            cargarSpinner();

            //etNumVuelo.setText("");

            Toast.makeText(this,"Registro Exitoso", Toast.LENGTH_SHORT).show();

        } else{
            Toast.makeText(this, "Ingresa Matricula y Vuelo", Toast.LENGTH_SHORT).show();
        }


    }
    //////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////
    //    //METODO PARA ELIMINAR DATOS EN LA BASE DE DATOS------------------------------------------

    public void Eliminar(View view){

        admin.abrirBD();

        String numvuelo = etNumVuelo.getText().toString();

        if(!numvuelo.isEmpty()){


            int cantidad = admin.EliminarDatosRadio(numvuelo);//llama el medoto eliminar que devuelve un numero entero
            admin.cerrarBD();
            ConsultarListaVuelos();
            cargarSpinner();
            etNumVuelo.setText("");
            Limpiar(); //limpia las entradas del usuario
            cargarActivity();

            if(cantidad == 1){

                Toast.makeText(this, "Vuelo Eliminado", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "El Vuelo no existe", Toast.LENGTH_SHORT).show();
                Limpiar();
            }

        } else {
            Toast.makeText(this, "Debes de introducir el Num Vuelo", Toast.LENGTH_SHORT).show();

        }
    }



    //////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////////
    //    //METODO PARA MODIFICAR DATOS EN LA BASE DE DATOS------------------------------------------

    public void Modificar(View view){
        admin.abrirBD();


        //variables para capturar los datos ingresados
        //se cargan las variables Publicas con los datos del ingresados por el usuario
        numfac = etNumFac.getText().toString();
        numvuelo = etNumVuelo.getText().toString();
        desde = etDesde.getText().toString();
        hacia = etHacia.getText().toString();
        Puertas_hor = etDoorHora.getText().toString();
        Puertas_min = etDoorMin.getText().toString();
        Prendida_hor = et1.getText().toString();
        Prendida_min = et2.getText().toString();
        Apagada_hor = et3.getText().toString();
        Apagada_min = et4.getText().toString();
        Decolage_hor = et5.getText().toString();
        Decolage_min = et6.getText().toString();
        Aterrizaje_hor = et7.getText().toString();
        Aterrizaje_min = et8.getText().toString();
        Pax = etPax.getText().toString();
        Pax_h = etPaxHombres.getText().toString();
        Pax_m = etPaxMujeres.getText().toString();
        Pax_n = etPaxNinos.getText().toString();
        Pax_nibra = etPaxNibra.getText().toString();
        Carga = etCarga.getText().toString();
        Fuel = etFuel.getText().toString();


        //codicion para verificar que los datos esten ingresados
        if( !numfac.isEmpty() &&!numvuelo.isEmpty()){


            int cantidad = admin.ModificarDatosRadio(numvuelo);
            admin.cerrarBD();
            ConsultarListaVuelos();
            cargarSpinner();
            cargarActivity();

            if(cantidad == 1){
                Toast.makeText(this, "Vuelo modificado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El Registro no existe", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Selecciona un Vuelo", Toast.LENGTH_SHORT).show();
        }
    }

    //////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
    //METODO PARA CONSULTAR DATOS EN LA BASE DE DATOS------------------------------------------

    public void Buscar(View view){


        numvuelo = etNumVuelo.getText().toString();


        if(!numvuelo.isEmpty()){
            admin.abrirBD();//abre la base de datos

            Cursor fila = admin.BuscarDatosRadio(numvuelo);//llama el cursor de la clase AdminSQLiteOpenHelper

            if(fila.moveToFirst()){

                //muestra la consulta de la base de datos en la actividad

                etNumFac.setText(fila.getString(0));
                etNumVuelo.setText(fila.getString(1));
                etDesde.setText(fila.getString(2));
                etHacia.setText(fila.getString(3));
                etDoorHora.setText(fila.getString(4));
                etDoorMin.setText(fila.getString(5));
                et1.setText(fila.getString(6));
                et2.setText(fila.getString(7));
                et5.setText(fila.getString(8));
                et6.setText(fila.getString(9));
                et7.setText(fila.getString(10));
                et8.setText(fila.getString(11));
                et3.setText(fila.getString(12));
                et4.setText(fila.getString(13));
                etPax.setText(fila.getString(14));
                etPaxHombres.setText(fila.getString(15));
                etPaxMujeres.setText(fila.getString(16));
                etPaxNinos.setText(fila.getString(17));
                etPaxNibra.setText(fila.getString(18));
                etCarga.setText(fila.getString(19));
                etFuel.setText(fila.getString(20));


                //cierra la Base de Datos
                admin.cerrarBD();

            } else {
                Toast.makeText(this,"No existe el Vuelo", Toast.LENGTH_SHORT).show();
                admin.cerrarBD();

            }

        } else {
            Toast.makeText(this, "Debes introducir el Num Vuelo", Toast.LENGTH_SHORT).show();
        }
    }


    //////////////////////////////////////////////////////////////////////////////////////////////


    //para mostrar los vuelos en el SPINNER////////////////////////

    private void ConsultarListaVuelos(){

        TablaRadio numeroVuelo = null;
        vuelosList = new ArrayList<TablaRadio>();

        //Codigo para efectuar la consulta a la base de datos
        Cursor cursor = admin.getReadableDatabase().rawQuery("SELECT * FROM "+ Constantes.TABLA_RADIO,null);
        //codigo para con
        while (cursor.moveToNext()){
            numeroVuelo = new TablaRadio();

            numeroVuelo.setNumvuelo(cursor.getString(0));
            numeroVuelo.setNumFAC(cursor.getString(1));
            numeroVuelo.setDesde(cursor.getString(2));
            numeroVuelo.setHacia(cursor.getString(3));
            numeroVuelo.setPuertas_hor(cursor.getString(4));
            numeroVuelo.setPuertas_min(cursor.getString(5));
            numeroVuelo.setPrendida_hor(cursor.getString(6));
            numeroVuelo.setPrendida_min(cursor.getString(7));
            numeroVuelo.setDecolage_hor(cursor.getString(8));
            numeroVuelo.setDecolage_min(cursor.getString(9));
            numeroVuelo.setAterrizaje_hor(cursor.getString(10));
            numeroVuelo.setAterrizaje_min(cursor.getString(11));
            numeroVuelo.setApagada_hor(cursor.getString(12));
            numeroVuelo.setApagada_min(cursor.getString(13));
            numeroVuelo.setPax(cursor.getString(14));
            numeroVuelo.setPax_h(cursor.getString(15));
            numeroVuelo.setPax_m(cursor.getString(16));
            numeroVuelo.setPax_n(cursor.getString(17));
            numeroVuelo.setPax_nibra(cursor.getString(18));
            numeroVuelo.setCarga(cursor.getString(19));
            numeroVuelo.setFuel(cursor.getString(20));

            vuelosList.add(numeroVuelo);

        }

            obtenerLista();
    }
    //////////////////////////////////////////////////////////

    /////////////////////////////////////
    //obtiene la lista de los vuelos ingresados
    private void obtenerLista(){

        listaVuelos = new ArrayList<String>();
        listaVuelos.add("Seleccione Numero Vuelo");
        //llama todos los registros almacenados en la base de datos y lo spresenta en la tabla del Archivo PDF
        for(int i = 0; i < vuelosList.size(); i++){

            listaVuelos.add(vuelosList.get(i).getNumvuelo()+ " - "+ vuelosList.get(i).getNumFAC() + "-" +vuelosList.get(i).getDesde() + "/" + vuelosList.get(i).getHacia());



        }
    }
    /////////////////////////////////////

    ////////////metodo para limpiar el formulario

    public void Limpiar() {

        //limpia las entradas del usuario
        etNumFac.setText("");
        etDesde.setText("");
        etHacia.setText("");
        etDoorHora.setText("");
        etDoorMin.setText("");
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        et5.setText("");
        et6.setText("");
        et7.setText("");
        et8.setText("");
        etPax.setText("");
        etPaxHombres.setText("");
        etPaxMujeres.setText("");
        etPaxNinos.setText("");
        etPaxNibra.setText("");
        etCarga.setText("");
        etFuel.setText("");
    }
    /////////////////////////////ADMINISTRA LA CAPTURA DE HORAS Y MINUTOS//////////////////////////////

    //Este metodo captura la hora actual para el cierre de puertas
    public void SetHoraPuertas(View view) {


         //este codigo convierte los valores de capHora y capMinutos en texto para imprimirlo en el editText
        if (capHora() < 10) {
            String hora = "0" + String.valueOf(capHora());
            etDoorHora.setText(hora);
        } else {
            etDoorHora.setText(String.valueOf(capHora()));
        }
        if (capMin() < 10) {
            String min = "0" + String.valueOf(capMin());
            etDoorMin.setText(min);
        } else {
            etDoorMin.setText(String.valueOf(capMin()));
        }


    }

    //Este metodo captura la hora actual para la prendida
    public void SetHoraPrendida(View view) {


        //este codigo convierte los valores de capHora y capMinutos en texto para imprimirlo en el editText
        if (capHora() < 10) {
            String hora = "0" + String.valueOf(capHora());
            et1.setText(hora);
        } else {
            et1.setText(String.valueOf(capHora()));
        }
        if (capMin() < 10) {
            String min = "0" + String.valueOf(capMin());
            et2.setText(min);
        } else {
            et2.setText(String.valueOf(capMin()));
        }


    }

    //Este metodo captura la hora actual para la apagada
    public void SetHoraApagada(View view) {


        //este codigo convierte los valores de capHora y capMinutos en texto para imprimirlo en el editText
        if (capHora() < 10) {
            String hora = "0" + String.valueOf(capHora());
            et3.setText(hora);
        } else {
            et3.setText(String.valueOf(capHora()));
        }
        if (capMin() < 10) {
            String min = "0" + String.valueOf(capMin());
            et4.setText(min);
        } else {
            et4.setText(String.valueOf(capMin()));
        }


    }

    //Este metodo captura la hora actual para la despegue
    public void SetHoraDecolaje(View view) {


        //este codigo convierte los valores de capHora y capMinutos en texto para imprimirlo en el editText
        if (capHora() < 10) {
            String hora = "0" + String.valueOf(capHora());
            et5.setText(hora);
        } else {
            et5.setText(String.valueOf(capHora()));
        }
        if (capMin() < 10) {
            String min = "0" + String.valueOf(capMin());
            et6.setText(min);
        } else {
            et6.setText(String.valueOf(capMin()));
        }


    }

    //Este metodo captura la hora actual para la despegue
    public void SetHoraAterrizaje(View view) {


        //este codigo convierte los valores de capHora y capMinutos en texto para imprimirlo en el editText
        if (capHora() < 10) {
            String hora = "0" + String.valueOf(capHora());
            et7.setText(hora);
        } else {
            et7.setText(String.valueOf(capHora()));
        }
        if (capMin() < 10) {
            String min = "0" + String.valueOf(capMin());
            et8.setText(min);
        } else {
            et8.setText(String.valueOf(capMin()));
        }


    }

    //************************************************* CAPTURA DE HORAS Y MINUTOS *************************************************
    //este metodo captura la Hora actual del celular (devuelve un entero)
    public int capHora() {

        Date now = new Date();
        int hour = now.getHours();

        return hour;


    }

    //este metodo captura los minutos actuales del celular (devuelve un entero)
    public int capMin() {

        Date now = new Date();
        int minutes = now.getMinutes();

        return minutes;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////METODO PARA TOMAR LA FOTO DE PANTALLA
    //Take an ScreenShot
    public void screenShot(View view){

        Date date = new Date();
        CharSequence now = android.text.format.DateFormat.format("yyyy-mm-dd_hh:mm:ss",date);
        String filename = Environment.getExternalStorageDirectory() + "/ScreenShot_OpraT/" + now + ".jpg";


        View root = getWindow().getDecorView();
        root.setDrawingCacheEnabled(true);
        //Bitmap bitmap = Bitmap.createBitmap(root.getDrawingCache());
        Bitmap bitmap = takeScreenshotForView(tableLayout); // Take ScreenshotUtil for any view
        root.setDrawingCacheEnabled(true);

        File file =new File(filename);
        file.getParentFile().mkdirs();

        try{
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();

            Uri uri = Uri.fromFile(file);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(uri, "image/*");
            startActivity(intent);

        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    ////////////////////////////
    public Bitmap takeScreenshotForView(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(view.getWidth(), View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(view.getHeight(), View.MeasureSpec.EXACTLY));
        view.layout((int) view.getX(), (int) view.getY(), (int) view.getX() + view.getMeasuredWidth(), (int) view.getY() + view.getMeasuredHeight());

        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(true);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);

        return bitmap;
    }
    ///////////////////////////




    //////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////

}
