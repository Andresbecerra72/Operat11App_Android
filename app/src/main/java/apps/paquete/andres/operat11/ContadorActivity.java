package apps.paquete.andres.operat11;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.andres.operat11.R;
import java.util.ArrayList;
import java.util.Calendar;


import apps.paquete.andres.operat11.constantes.Constantes;

public class ContadorActivity extends AppCompatActivity {

    //variables de los objetos
    private EditText etHorasTotales;
    private EditText etMinTotales;
    private EditText etHorasObjetivo;
    private TextView tvActuales;
    private TextView tvFaltantes;
    private TextView tvHorasHoy;
    private TextView tvHorasSemana;
    private TextView tvHorasMes;
    private TextView tvHorasAno;
    private TextView tvHorasQuince;
    private Button btnSet;
    private Button btnConsulta;
    //variables para el calculo de las horas
    private int HorasTotales = 0;
    private int MinTotales = 0;


    //variable Spinner
    private Spinner spSemanas;
    private Spinner spMeses;
    private Spinner spAnual;
    private int Code = 0;
    public static int CodeSemana = 0;
    public static  int CodeMes = 0;
    public static  int CodeAno = 0;
    private String SelectAno = "";
    private String SelectMes = "";

    //CONSULTA DEL SPINNER
    ArrayList<String> listaVuelosAnos;
    ArrayList<TablaContador> vuelosListAnos;
    ArrayList<String> listaVuelosMes;
    ArrayList<TablaContador> vuelosListMes;

    FullActivity fullActivity = new FullActivity();


    //base de datos
    AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "bitacoraBD", null, 1);//base de datos para el registro
    AdminSQLiteOpenHelper admincount = new AdminSQLiteOpenHelper(this, "countBD", null, 1);//base de datos para el contador de horas



    //inicio

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contador);

        //esta linea de codigo evita la rotacion de la patanlla
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //objetos
        etHorasTotales = (EditText) findViewById(R.id.etHorasTotales);
        etMinTotales = (EditText) findViewById(R.id.etMinTotales);
        etHorasObjetivo = (EditText) findViewById(R.id.etHorasObjetivo);
        tvActuales = (TextView) findViewById(R.id.tvActuales);
        tvFaltantes = (TextView) findViewById(R.id.tvFaltantes);
        tvHorasHoy = (TextView) findViewById(R.id.tvHorasHoy);
        tvHorasSemana = (TextView) findViewById(R.id.tvHorasSemana);
        tvHorasMes = (TextView) findViewById(R.id.tvHorasMes);
        tvHorasAno = (TextView) findViewById(R.id.tvHorasAno);
        tvHorasQuince = (TextView) findViewById(R.id.tvHorasQuince);

        spSemanas = (Spinner) findViewById(R.id.spSemana);
        spMeses = (Spinner) findViewById(R.id.spMeses);
        spAnual = (Spinner) findViewById(R.id.spAnual);
        btnSet = (Button) findViewById(R.id.btCargar);
        btnConsulta = (Button) findViewById(R.id.btnConsulta);

        //mantener datos en el activity
        SharedPreferences preferences = getSharedPreferences("count", Context.MODE_PRIVATE);
        etHorasTotales.setText(preferences.getString("HT",""));
        etMinTotales.setText(preferences.getString("MT",""));
        tvActuales.setText(preferences.getString("HA",""));
        tvFaltantes.setText(preferences.getString("HF",""));
        tvHorasHoy.setText(preferences.getString("HH",""));
        etHorasObjetivo.setText(preferences.getString("HO",""));

        //codigo para cargar el activity con los datos
        spSemanas.setEnabled(false);
        spMeses.setEnabled(false);
        btnConsulta.setEnabled(false);
        horasTotalesSemana();//CARGA LAS HORAS DE LA QUINCENA
        ConsultarListaVuelosAnos();
        cargarSpinnerAnos();
        ConsultarListaVuelosMes();
        cargarSpinnerMeses();
        cargarSpinnerSemana();

     /////////////////////////////////////////

        //Deteccion de ERRORES antes de cargar los datos del activity
        if ((etHorasTotales.getText().toString().length() != 0) && (etMinTotales.getText().toString().length() != 0)){
            //codigo para mantener desabilitado algunos objetos
            btnSet.setEnabled(false);
            etHorasTotales.setEnabled(false);
            etMinTotales.setEnabled(false);
            etHorasObjetivo.setEnabled(false);
            sethorasTotales(null);
            operacion();
            horasTotalesHoy();
            horasFaltantes();
                    }
    }

    //INICIO
    //metodos del Activity

    /////////////////////////////////////////////////////////////CODIGO PARA LA SELECCION DE LAS HORAS TOTALES DEL AÑO------------------------------------------------------

    /////////////////////-------------
    private void cargarSpinnerAnos(){
        ///////////////////////////////codigo del SPINNER AÑOS/////////////////////////



        //adaptador que permite vincular el Array MatriculaFAC con el spinner y tambien permite vincular el diseño de los items en el spinner "spinner_item_edit"
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, R.layout.spinner_item_edit, listaVuelosAnos);
        spAnual.setAdapter(adapter);

        //metodo que permite la seleccion de la aeronave y muestra el TIPO y la matricula HK
        spAnual.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position != 0) {

                    SelectAno = vuelosListAnos.get(position - 1).getYear();
                    CodeAno = Integer.parseInt(SelectAno);
                    horasTotalesAnos();
                    spMeses.setEnabled(true);
                    btnConsulta.setEnabled(true);


                }else {
                    tvHorasAno.setText("Y00:00");

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    //////////////////////codigo para llamar la seleccion de HORAS EN EL AÑO
    private void horasTotalesAnos(){

        TablaContador numVueloTotal = null;
        ArrayList<TablaContador> Totales = new ArrayList<TablaContador>();

        //Codigo para efectuar la consulta a la base de datos
        Cursor cursor = admincount.getReadableDatabase().rawQuery("SELECT total_tripulacion_hor, total_tripulacion_min FROM " + Constantes.TABLA_CONTADOR + " WHERE year=" + CodeAno, null);
        //Cursor cursor = admincount.getReadableDatabase().rawQuery("SELECT * FROM "+ Constantes.TABLA_CONTADOR,null);
        //variables para los calculos y sumar los totales de la base de dato
        int TotalTripHora = 0;
        int TotalTripMin = 0;
        int HorasTotales = 0;
        int MinTotales = 0;




        while(cursor.moveToNext())

        {

            numVueloTotal = new TablaContador();

            numVueloTotal.setTotal_tripulacion_hor(cursor.getInt(0));
            numVueloTotal.setTotal_tripulacion_min(cursor.getInt(1));


            Totales.add(numVueloTotal);
        }

        for( int i = 0; i<Totales.size();i++)

        {

            TotalTripHora = TotalTripHora + Totales.get(i).getTotal_tripulacion_hor();
            TotalTripMin = TotalTripMin + Totales.get(i).getTotal_tripulacion_min();

        }

        //variables para mostrar datos en archivo PDF
        String Total_Tripulacion = "";


        HorasTotales = HorasTotales + TotalTripHora;
        MinTotales = MinTotales + TotalTripMin;

        //codigo cuando los minutos son mayores a 60
        if (MinTotales > 59) { //condicion para Minutos tripulacion

            while (MinTotales > 59) {

                MinTotales = MinTotales - 60;
                HorasTotales = HorasTotales + 1;
            }
            if (MinTotales <= 9) {//codigo para mejorar la presentacion de los minutos menores a 10
                Total_Tripulacion = String.valueOf(HorasTotales) + ":0" + String.valueOf(MinTotales);
                tvHorasAno.setText("►" + Total_Tripulacion + "◄");

            } else {
                Total_Tripulacion = String.valueOf(HorasTotales) + ":" + String.valueOf(MinTotales);
                tvHorasAno.setText("►" + Total_Tripulacion + "◄");
            }
        } else {
            if (MinTotales <= 9) {//codigo para mejorar la presentacion de los minutos menores a 10
                Total_Tripulacion = String.valueOf(HorasTotales) + ":0" + String.valueOf(MinTotales);
                tvHorasAno.setText("►" + Total_Tripulacion + "◄");
            } else {
                Total_Tripulacion = String.valueOf(HorasTotales) + ":" + String.valueOf(MinTotales);
                tvHorasAno.setText("►" + Total_Tripulacion + "◄");
            }

        }



    }
    ///////////////////////////
    //******
    //para mostrar los vuelos en el SPINNER AÑOS////////////////////////

    private void ConsultarListaVuelosAnos(){

        TablaContador numeroVuelo = null;
        vuelosListAnos = new ArrayList<TablaContador>();

        //Codigo para efectuar la consulta a la base de datos
        Cursor cursor = admincount.getReadableDatabase().rawQuery("SELECT DISTINCT year FROM " + Constantes.TABLA_CONTADOR , null);
        //Cursor cursor = admincount.getReadableDatabase().rawQuery("SELECT * FROM "+ Constantes.TABLA_CONTADOR,null);
        //codigo para con
        while (cursor.moveToNext()){
            numeroVuelo = new TablaContador();


            numeroVuelo.setYear(cursor.getString(0));


            vuelosListAnos.add(numeroVuelo);

        }

        obtenerListaAnos();

    }
    //////////////////////////////////////////////////////////

    /////////////////////////////////////
    //obtiene la lista de los vuelos ingresados
    private void obtenerListaAnos(){

        listaVuelosAnos = new ArrayList<String>();
        listaVuelosAnos.add("Years");
        //llama todos los registros almacenados en la base de datos y lo spresenta en la tabla del Archivo PDF
        for(int i = 0; i < vuelosListAnos.size(); i++){

            listaVuelosAnos.add(vuelosListAnos.get(i).getYear());



        }
    }
/////////////////////////////////////




    //////////////////////-----------

    /////////////////////////////////////////////////////////////CODIGO PARA LA SELECCION DE LAS HORAS TOTALES DEL MES------------------------------------------------------

    private void cargarSpinnerMeses(){
        ///////////////////////////////codigo del SPINNER MESES/////////////////////////



        //adaptador que permite vincular el Array MatriculaFAC con el spinner y tambien permite vincular el diseño de los items en el spinner "spinner_item_edit"
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, R.layout.spinner_item_edit, listaVuelosMes);
        spMeses.setAdapter(adapter);

        //metodo que permite la seleccion de la aeronave y muestra el TIPO y la matricula HK
        spMeses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position != 0) {

                    CodeAno = Integer.parseInt(SelectAno);
                    SelectMes = vuelosListMes.get(position - 1).getMonth();
                    CodeMes = Integer.parseInt(SelectMes);
                    spSemanas.setEnabled(true);

                    horasTotalesMeses();



                }else {
                    tvHorasMes.setText("M00:00");

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    ///////////////////////////
    //////////////////////codigo para llla mar la seleccion de HORAS EN EL AÑO
    private void horasTotalesMeses(){

        TablaContador numVueloTotal = null;
        ArrayList<TablaContador> Totales = new ArrayList<TablaContador>();

        //Codigo para efectuar la consulta a la base de datos
        Cursor cursor = admincount.getReadableDatabase().rawQuery("SELECT total_tripulacion_hor, total_tripulacion_min FROM " + Constantes.TABLA_CONTADOR + " WHERE month=" + CodeMes + " AND year=" + CodeAno, null);

        //variables para los calculos y sumar los totales de la base de dato
        int TotalTripHora = 0;
        int TotalTripMin = 0;
        int HorasTotales = 0;
        int MinTotales = 0;




        while(cursor.moveToNext())

        {

            numVueloTotal = new TablaContador();

            numVueloTotal.setTotal_tripulacion_hor(cursor.getInt(0));
            numVueloTotal.setTotal_tripulacion_min(cursor.getInt(1));


            Totales.add(numVueloTotal);
        }

        for( int i = 0; i<Totales.size();i++)

        {

            TotalTripHora = TotalTripHora + Totales.get(i).getTotal_tripulacion_hor();
            TotalTripMin = TotalTripMin + Totales.get(i).getTotal_tripulacion_min();

        }

        //variables para mostrar datos en archivo PDF
        String Total_Tripulacion = "";


        HorasTotales = HorasTotales + TotalTripHora;
        MinTotales = MinTotales + TotalTripMin;

        //codigo cuando los minutos son mayores a 60
        if (MinTotales > 59) { //condicion para Minutos tripulacion

            while (MinTotales > 59) {

                MinTotales = MinTotales - 60;
                HorasTotales = HorasTotales + 1;
            }
            if (MinTotales <= 9) {//codigo para mejorar la presentacion de los minutos menores a 10
                Total_Tripulacion = String.valueOf(HorasTotales) + ":0" + String.valueOf(MinTotales);
                tvHorasMes.setText("►" + Total_Tripulacion + "◄");

            } else {
                Total_Tripulacion = String.valueOf(HorasTotales) + ":" + String.valueOf(MinTotales);
                tvHorasMes.setText("►" + Total_Tripulacion + "◄");
            }
        } else {
            if (MinTotales <= 9) {//codigo para mejorar la presentacion de los minutos menores a 10
                Total_Tripulacion = String.valueOf(HorasTotales) + ":0" + String.valueOf(MinTotales);
                tvHorasMes.setText("►" + Total_Tripulacion + "◄");
            } else {
                Total_Tripulacion = String.valueOf(HorasTotales) + ":" + String.valueOf(MinTotales);
                tvHorasMes.setText("►" + Total_Tripulacion + "◄");
            }

        }



    }
    ///////////////////////////
    //******
    //para mostrar los vuelos en el SPINNER MES////////////////////////

    private void ConsultarListaVuelosMes(){

        TablaContador numeroVuelo = null;
        vuelosListMes = new ArrayList<TablaContador>();

        //Codigo para efectuar la consulta a la base de datos
        //Cursor cursor = admincount.getReadableDatabase().rawQuery("SELECT total_tripulacion_hor, total_tripulacion_min FROM " + Constantes.TABLA_CONTADOR + " WHERE month=" + CodeMes, null);
        Cursor cursor = admincount.getReadableDatabase().rawQuery("SELECT DISTINCT month FROM "+ Constantes.TABLA_CONTADOR,null);
        //codigo para con
        while (cursor.moveToNext()){
            numeroVuelo = new TablaContador();


           numeroVuelo.setMonth(cursor.getString(0));



            vuelosListMes.add(numeroVuelo);

        }

        obtenerListaMeses();

    }
    //////////////////////////////////////////////////////////

    /////////////////////////////////////
    //obtiene la lista de los vuelos ingresados
    private void obtenerListaMeses(){

        listaVuelosMes = new ArrayList<String>();
        listaVuelosMes.add("Months");
        //llama todos los registros almacenados en la base de datos y lo spresenta en la tabla del Archivo PDF
       for(int i = 0; i < vuelosListMes.size(); i++){

            listaVuelosMes.add(vuelosListMes.get(i).getMonth());



        }
    }
/////////////////////////////////////
    /////////////////////////////////////////////////////////////CODIGO PARA LA SELECCION DE LAS HORAS TOTALES DE LA SEMANA------------------------------------------------------
    //////////////////////////////////////////
    ///////////////////////////////codigo del SPINNER/////////////////////////


    //objeto SPinner SEMANAS

    private void cargarSpinnerSemana() {

        String[] weeks = {"Fornight", "First", "Second"};

        //adaptador que permite vincular el Array MatriculaFAC con el spinner y tambien permite vincular el diseño de los items en el spinner "spinner_item_edit"
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item_edit, weeks);
        spSemanas.setAdapter(adapter);

        //metodo que permite la seleccion de la aeronave y muestra el TIPO y la matricula HK
        spSemanas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //Tipo.setText(parent.getItemAtPosition(position).toString());
                String seleccion = parent.getItemAtPosition(position).toString();//getSelectedItem().toString();
                if (seleccion.equals("First")) {//****************************************


                    CodeAno = Integer.parseInt(SelectAno);
                    CodeMes = Integer.parseInt(SelectMes);
                    Code = 1;
                    CodeSemana = 1;

                    consultaSemana();

                } else if (seleccion.equals("Second")) {//****************************************

                    CodeAno = Integer.parseInt(SelectAno);
                    CodeMes = Integer.parseInt(SelectMes);
                    Code = 2;
                    CodeSemana = 2;
                    consultaSemana();

                } else {
                    tvHorasSemana.setText("F00:00");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
////////////////////////////////////////////////////////////////////////////////////////////





    /////////////////////////////////////////////////////////////CODIGO PARA LA CONSULTA SEMANA------------------------------------------------------
//******

    //////////////////////////metodo para consulta por semanas
    private void consultaSemana(){
        TablaContador numVueloTotal = null;
        ArrayList<TablaContador> Totales = new ArrayList<TablaContador>();


        //Codigo para efectuar la consulta a la base de datos
        Cursor cursor = admincount.getReadableDatabase().rawQuery("SELECT total_tripulacion_hor, total_tripulacion_min FROM " + Constantes.TABLA_CONTADOR + " WHERE week=" + Code +" AND month=" + CodeMes + " AND year=" + CodeAno, null);
        //variables para los calculos y sumar los totales de la base de dato
        int TotalTripHora = 0;
        int TotalTripMin = 0;
        int HorasTotales = 0;
        int MinTotales = 0;




        while(cursor.moveToNext())

        {

            numVueloTotal = new TablaContador();

            numVueloTotal.setTotal_tripulacion_hor(cursor.getInt(0));
            numVueloTotal.setTotal_tripulacion_min(cursor.getInt(1));


            Totales.add(numVueloTotal);
        }

        for( int i = 0; i<Totales.size();i++)

        {

            TotalTripHora = TotalTripHora + Totales.get(i).getTotal_tripulacion_hor();
            TotalTripMin = TotalTripMin + Totales.get(i).getTotal_tripulacion_min();


        }



        //variables para mostrar datos en archivo PDF
        String Total_Tripulacion = "";


        HorasTotales = HorasTotales + TotalTripHora;
        MinTotales = MinTotales + TotalTripMin;

        //codigo cuando los minutos son mayores a 60
        if (MinTotales > 59) { //condicion para Minutos tripulacion

            while (MinTotales > 59) {

                MinTotales = MinTotales - 60;
                HorasTotales = HorasTotales + 1;
            }
            if (MinTotales <= 9) {//codigo para mejorar la presentacion de los minutos menores a 10
                Total_Tripulacion = String.valueOf(HorasTotales) + ":0" + String.valueOf(MinTotales);
                tvHorasSemana.setText("►" + Total_Tripulacion + "◄");
                Code=0;

            } else {
                Total_Tripulacion = String.valueOf(HorasTotales) + ":" + String.valueOf(MinTotales);
                tvHorasSemana.setText("►" + Total_Tripulacion + "◄");
                Code=0;
            }
        } else {
            if (MinTotales <= 9) {//codigo para mejorar la presentacion de los minutos menores a 10
                Total_Tripulacion = String.valueOf(HorasTotales) + ":0" + String.valueOf(MinTotales);
                tvHorasSemana.setText("►" + Total_Tripulacion + "◄");
                Code=0;
            } else {
                Total_Tripulacion = String.valueOf(HorasTotales) + ":" + String.valueOf(MinTotales);
                tvHorasSemana.setText("►" + Total_Tripulacion + "◄");
                Code=0;
            }

        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////------------------------------------------------------------------------------
    //Este metodo captura las horas totales
    public void sethorasTotales(View view) {

        dataActivity();


        //variables para captura de datos
        String dataH1 = etHorasTotales.getText().toString();
        String dataH2 = etMinTotales.getText().toString();

        if( !dataH1.isEmpty() && !dataH2.isEmpty() ) {

            HorasTotales = Integer.parseInt(dataH1);
            MinTotales = Integer.parseInt(dataH2);


            btnSet.setEnabled(false);
            btnSet.setVisibility(View.INVISIBLE);
            etHorasTotales.setEnabled(false);
            etMinTotales.setEnabled(false);
            etHorasObjetivo.setEnabled(false);
        }else{
            Toast.makeText(this, "Ingresa Horas Totales", Toast.LENGTH_SHORT).show();
        }

        horasFaltantes();

    }

    //codigo para habilitar los objetos
    public void abilitaObjets(View view) {

        btnSet.setEnabled(true);
        etHorasTotales.setEnabled(true);
        etMinTotales.setEnabled(true);
        etHorasObjetivo.setEnabled(true);
        btnSet.setVisibility(View.VISIBLE);

    }

//////metodo que muestra las horas ACTUALES
    private void operacion (){


            TablaContador numVueloTotal = null;
            ArrayList<TablaContador> Totales = new ArrayList<TablaContador>();

            //Codigo para efectuar la consulta a la base de datos
            Cursor cursor = admincount.getReadableDatabase().rawQuery("SELECT * FROM "+ Constantes.TABLA_CONTADOR,null);
            //variables para los calculos y sumar los totales de la base de dato
            int TotalTripHora = 0;
            int TotalTripMin = 0;


            while (cursor.moveToNext()){

                numVueloTotal = new TablaContador();

                numVueloTotal.setTotal_tripulacion_hor(cursor.getInt(1));
                numVueloTotal.setTotal_tripulacion_min(cursor.getInt(2));


                Totales.add(numVueloTotal);
            }

            for(int i = 0; i < Totales.size(); i++) {

                TotalTripHora = TotalTripHora + Totales.get(i).getTotal_tripulacion_hor();
                TotalTripMin = TotalTripMin + Totales.get(i).getTotal_tripulacion_min();


            }

            //variables para mostrar datos en archivo PDF
             String Total_Tripulacion = "";



        HorasTotales = HorasTotales + TotalTripHora;
        MinTotales = MinTotales + TotalTripMin;

        //codigo cuando los minutos son mayores a 60
        if(MinTotales > 59){ //condicion para Minutos tripulacion

            while (MinTotales > 59){

                MinTotales = MinTotales - 60;
                HorasTotales = HorasTotales + 1;
            }
            if(MinTotales <= 9){//codigo para mejorar la presentacion de los minutos menores a 10
                Total_Tripulacion = String.valueOf(HorasTotales)+":0"+String.valueOf(MinTotales);
                tvActuales.setText(Total_Tripulacion);
                dataActivity();
            }else{
                Total_Tripulacion = String.valueOf(HorasTotales)+":"+String.valueOf(MinTotales);
                tvActuales.setText(Total_Tripulacion);
                dataActivity();
            }
        }else{
            if(MinTotales <= 9){//codigo para mejorar la presentacion de los minutos menores a 10
                Total_Tripulacion = String.valueOf(HorasTotales)+":0"+String.valueOf(MinTotales);
                tvActuales.setText(Total_Tripulacion);
                dataActivity();
            }else{
                Total_Tripulacion = String.valueOf(HorasTotales)+":"+String.valueOf(MinTotales);
                tvActuales.setText(Total_Tripulacion);
                dataActivity();
            }

        }


    }
    ////////////////////////////////////////////////
    //este metodo muestra el total de horas voladas en un solo dia ** DAILY
    public void horasTotalesHoy (){
        TablaContador numVueloTotal = null;
        ArrayList<TablaContador> Totales = new ArrayList<TablaContador>();

        //Codigo para efectuar la consulta a la base de datos
        Cursor cursor = admin.getReadableDatabase().rawQuery("SELECT * FROM " + Constantes.TABLA_TOTALES, null);
        //variables para los calculos y sumar los totales de la base de dato
        int TotalTripHora = 0;
        int TotalTripMin = 0;
        int HorasTotales = 0;
        int MinTotales = 0;



        while(cursor.moveToNext())

        {

            numVueloTotal = new TablaContador();

            numVueloTotal.setTotal_tripulacion_hor(cursor.getInt(1));
            numVueloTotal.setTotal_tripulacion_min(cursor.getInt(2));


            Totales.add(numVueloTotal);
        }

        for(
                int i = 0; i<Totales.size();i++)

        {

            TotalTripHora = TotalTripHora + Totales.get(i).getTotal_tripulacion_hor();
            TotalTripMin = TotalTripMin + Totales.get(i).getTotal_tripulacion_min();


        }

        //variables para mostrar datos en archivo PDF
        String Total_Tripulacion = "";


        HorasTotales =HorasTotales +TotalTripHora;
        MinTotales =MinTotales +TotalTripMin;

        //codigo cuando los minutos son mayores a 60
        if(MinTotales >59)

        { //condicion para Minutos tripulacion

            while (MinTotales > 59) {

                MinTotales = MinTotales - 60;
                HorasTotales = HorasTotales + 1;
            }
            if (MinTotales <= 9) {//codigo para mejorar la presentacion de los minutos menores a 10
                Total_Tripulacion = String.valueOf(HorasTotales) + ":0" + String.valueOf(MinTotales);
                tvHorasHoy.setText("►" +Total_Tripulacion+"◄");

            } else {
                Total_Tripulacion = String.valueOf(HorasTotales) + ":" + String.valueOf(MinTotales);
                tvHorasHoy.setText("►" +Total_Tripulacion+"◄");
            }
        }else

        {
            if (MinTotales <= 9) {//codigo para mejorar la presentacion de los minutos menores a 10
                Total_Tripulacion = String.valueOf(HorasTotales) + ":0" + String.valueOf(MinTotales);
                tvHorasHoy.setText("►" +Total_Tripulacion+"◄");
            } else {
                Total_Tripulacion = String.valueOf(HorasTotales) + ":" + String.valueOf(MinTotales);
                tvHorasHoy.setText("►" + Total_Tripulacion +"◄");
            }

        }

    }
//////////////////////////////////////////////////////
    //////////////////////////////////////////////////////METODO PARA CARGAR AUTOMATICAMENTE LAS HORAS DE LA SEMANA
//este metodo muestra el total de horas voladas en un solo dia ** WEEKLY
public void horasTotalesSemana (){

   //codigo para capturar los parametros de la fecha y calcular la semana
    Calendar calendar = Calendar.getInstance();
    int semana = calendar.get(Calendar.WEEK_OF_MONTH);
    int mes = calendar.get(Calendar.MONTH)+ 1;
    int dia = calendar.get(Calendar.DAY_OF_MONTH);



    if (dia <= 15)
    {
        primeraQuicena();

    }else{
        segundaQuincena();

    }


}

    ////////////////////metodo calcula horas totales SEGUNDA quincena------------------------------

    private void segundaQuincena() {

        //codigo para capturar los parametros de la fecha y calcular la semana
        Calendar calendar = Calendar.getInstance();
        int ano = calendar.get(Calendar.YEAR);
        int semana = calendar.get(Calendar.WEEK_OF_MONTH);
        int mes = calendar.get(Calendar.MONTH)+ 1;
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        //variables para los calculos y sumar los totales de la base de dato
        int TotalTripHora = 0;
        int TotalTripMin = 0;
        int HorasTotales = 0;
        int MinTotales = 0;
        int weekly = 2;
        int day = 0;



        TablaContador numVueloTotal = null;
        ArrayList<TablaContador> Totales = new ArrayList<TablaContador>();

        //Codigo para efectuar la consulta a la base de datos
        //Cursor cursor = admincount.getReadableDatabase().rawQuery("SELECT total_tripulacion_hor, total_tripulacion_min,day,week FROM " + Constantes.TABLA_CONTADOR + " WHERE month=" + mes, null);
        Cursor cursor = admincount.getReadableDatabase().rawQuery("SELECT SUM(total_tripulacion_hor), SUM(total_tripulacion_min) FROM " + Constantes.TABLA_CONTADOR +" WHERE week=" + weekly + " AND month=" + mes + " AND year=" + ano, null);



        while(cursor.moveToNext())

        {

            numVueloTotal = new TablaContador();

            numVueloTotal.setTotal_tripulacion_hor(cursor.getInt(0));
            numVueloTotal.setTotal_tripulacion_min(cursor.getInt(1));
            //numVueloTotal.setDay(cursor.getInt(2));
            //numVueloTotal.setWeek(cursor.getInt(3));

            Totales.add(numVueloTotal);
        }

        for( int i = 0; i<Totales.size();i++)

        {

            TotalTripHora = Totales.get(i).getTotal_tripulacion_hor();
            TotalTripMin = Totales.get(i).getTotal_tripulacion_min();



        }


            //variables para mostrar datos en archivo PDF
            String Total_Tripulacion = "";


            HorasTotales = HorasTotales + TotalTripHora;
            MinTotales = MinTotales + TotalTripMin;

            //codigo cuando los minutos son mayores a 60
            if (MinTotales > 59) { //condicion para Minutos tripulacion

                while (MinTotales > 59) {

                    MinTotales = MinTotales - 60;
                    HorasTotales = HorasTotales + 1;
                }
                if (MinTotales <= 9) {//codigo para mejorar la presentacion de los minutos menores a 10
                    Total_Tripulacion = String.valueOf(HorasTotales) + ":0" + String.valueOf(MinTotales);
                    tvHorasQuince.setText("2►" + Total_Tripulacion + "◄");

                } else {
                    Total_Tripulacion = String.valueOf(HorasTotales) + ":" + String.valueOf(MinTotales);
                    tvHorasQuince.setText("2►" + Total_Tripulacion + "◄");
                }
            } else {
                if (MinTotales <= 9) {//codigo para mejorar la presentacion de los minutos menores a 10
                    Total_Tripulacion = String.valueOf(HorasTotales) + ":0" + String.valueOf(MinTotales);
                    tvHorasQuince.setText("2►" + Total_Tripulacion + "◄");
                } else {
                    Total_Tripulacion = String.valueOf(HorasTotales) + ":" + String.valueOf(MinTotales);
                    tvHorasQuince.setText("2►" + Total_Tripulacion + "◄");
                }

            }


    }

    ////////////////////metodo calcula horas totales primera quincena------------------------------------------
    private void primeraQuicena() {

        //codigo para capturar los parametros de la fecha y calcular la semana
        Calendar calendar = Calendar.getInstance();
        int ano = calendar.get(Calendar.YEAR);
        int semana = calendar.get(Calendar.WEEK_OF_MONTH);
        int mes = calendar.get(Calendar.MONTH)+ 1;
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        //variables para los calculos y sumar los totales de la base de dato
        int TotalTripHora = 0;
        int TotalTripMin = 0;
        int HorasTotales = 0;
        int MinTotales = 0;
        int weekly = 1;
        int day = 0;

        TablaContador numVueloTotal = null;
        ArrayList<TablaContador> Totales = new ArrayList<TablaContador>();
        //Codigo para efectuar la consulta a la base de datos
        Cursor cursor = admincount.getReadableDatabase().rawQuery("SELECT SUM(total_tripulacion_hor), SUM(total_tripulacion_min) FROM " + Constantes.TABLA_CONTADOR + " WHERE week=" + weekly + " AND month=" + mes + " AND year=" + ano, null);


        while (cursor.moveToNext()) {

            numVueloTotal = new TablaContador();

            numVueloTotal.setTotal_tripulacion_hor(cursor.getInt(0));
            numVueloTotal.setTotal_tripulacion_min(cursor.getInt(1));


            Totales.add(numVueloTotal);
        }

        for (int i = 0; i < Totales.size(); i++) {

            TotalTripHora = Totales.get(i).getTotal_tripulacion_hor();
            TotalTripMin = Totales.get(i).getTotal_tripulacion_min();


        }



            //variables para mostrar datos en archivo PDF
            String Total_Tripulacion = "";


            HorasTotales = HorasTotales + TotalTripHora;
            MinTotales = MinTotales + TotalTripMin;

            //codigo cuando los minutos son mayores a 60
            if (MinTotales > 59) { //condicion para Minutos tripulacion

                while (MinTotales > 59) {

                    MinTotales = MinTotales - 60;
                    HorasTotales = HorasTotales + 1;
                }
                if (MinTotales <= 9) {//codigo para mejorar la presentacion de los minutos menores a 10
                    Total_Tripulacion = String.valueOf(HorasTotales) + ":0" + String.valueOf(MinTotales);
                    tvHorasQuince.setText("1►" + Total_Tripulacion + "◄");

                } else {
                    Total_Tripulacion = String.valueOf(HorasTotales) + ":" + String.valueOf(MinTotales);
                    tvHorasQuince.setText("1►" + Total_Tripulacion + "◄");
                }
            } else {
                if (MinTotales <= 9) {//codigo para mejorar la presentacion de los minutos menores a 10
                    Total_Tripulacion = String.valueOf(HorasTotales) + ":0" + String.valueOf(MinTotales);
                    tvHorasQuince.setText("1►" + Total_Tripulacion + "◄");
                } else {
                    Total_Tripulacion = String.valueOf(HorasTotales) + ":" + String.valueOf(MinTotales);
                    tvHorasQuince.setText("1►" + Total_Tripulacion + "◄");
                }

            }

    }

    /////////////////////////////////calculo de las horas faltantes para cumplir el objetivo//////////////////
    private void horasFaltantes (){

        int objetivo = 0;
        int horasHoy = 0;
        int minHoy = 0;
        int resultHora = 0;
        int resultMin = 0;
        int result = 0;


        if ((etHorasObjetivo.getText().toString().length() != 0)&& MinTotales > 0 && MinTotales < 60){

             objetivo=  Integer.parseInt(etHorasObjetivo.getText().toString());
            horasHoy = HorasTotales;
            minHoy = MinTotales;

            resultHora = objetivo - horasHoy -1;
            resultMin = 60 - minHoy;


            tvFaltantes.setText(String.valueOf(resultHora)+":"+String.valueOf(resultMin));


        }else if ((etHorasObjetivo.getText().toString().length() != 0)&& MinTotales == 0){

            objetivo=  Integer.parseInt(etHorasObjetivo.getText().toString());
            horasHoy = HorasTotales;
            minHoy = MinTotales;

            resultHora = objetivo - horasHoy ;



            tvFaltantes.setText(String.valueOf(resultHora)+":00");

        }else if(etHorasObjetivo.getText().toString().length() == 0 && MinTotales > 0 && MinTotales < 60){
            horasHoy = HorasTotales;
            minHoy = MinTotales;

            resultHora = 3000 - horasHoy -1;
            resultMin = 60 - minHoy;

            tvFaltantes.setText(String.valueOf(resultHora)+":"+String.valueOf(resultMin));

        }else if(etHorasObjetivo.getText().toString().length() == 0 && MinTotales ==0) {
            horasHoy = HorasTotales;
            minHoy = MinTotales;

            resultHora = 3000 - horasHoy ;


            tvFaltantes.setText(String.valueOf(resultHora) + ":00");
        }else{
            tvFaltantes.setText("00:00");
        }

    }


    //////////////////////////////////////////////////////////

    //metodo que carga los datos delactivity para mantenerlos
    private void dataActivity (){
        //codigo para mostarlos datos y se mantengan en el activity
        SharedPreferences preferencia = getSharedPreferences("count",Context.MODE_PRIVATE);
        SharedPreferences.Editor Obj_editor = preferencia.edit();
        Obj_editor.putString("HT", etHorasTotales.getText().toString());
        Obj_editor.putString("MT", etMinTotales.getText().toString());
        Obj_editor.putString("HA", tvActuales.getText().toString());
        Obj_editor.putString("HF", tvFaltantes.getText().toString());
        Obj_editor.putString("HH", tvHorasHoy.getText().toString());
        Obj_editor.putString("HO", etHorasObjetivo.getText().toString());
        Obj_editor.commit();
    }

    public void GetDisplayPage(View view){

        //codigo para cargar el activity con los datos
        spSemanas.setEnabled(false);
        spMeses.setEnabled(false);
        btnConsulta.setEnabled(false);
        ConsultarListaVuelosAnos();
        cargarSpinnerAnos();
        ConsultarListaVuelosMes();
        cargarSpinnerMeses();
        //ConsultarListaVuelo();
        cargarSpinnerSemana();

        Intent getDisplayactivity = new Intent(this,DisplayActivity.class);
        startActivity(getDisplayactivity);

        //finish();


    }



}
