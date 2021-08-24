package apps.paquete.andres.operat11;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.andres.operat11.R;

public class DetailActivity extends AppCompatActivity {

    //datos de cabecera
    private EditText etTOW;
    private EditText etAltura;
    private EditText etVelocidad;
    private EditText etMotor1;
    private EditText etMotor2;

    private TextView tvRegistro;
    private TextView tvNumVuelo;
    private TextView tvDesde;
    private TextView tvHacia;

    //toggle Button Fuel
    private ToggleButton tgConverUnidades;
    private ToggleButton tgResulFuel;

    //FUEL
    private EditText et9;
    private EditText et10;
    private EditText et11;
    private EditText et12;

    //textView FUEL
    private TextView FuelIn;
    private TextView FuelOut;
    private TextView FuelTrip;
    private TextView Remain;
    private TextView tvConsumo;

    //datos totales FUEL
    public int valortvFuelin = 0;
    public int valortvFuelout = 0;
    public int valortvFuelTrip = 0;
    public int valorRemain = 0;
    public int valorConsumo = 0;
    public int flag = 0;

    //variables publicas para la base de datos fuel
    public static String Tow = "";
    public static String Alt = "";
    public static String Vel = "";
    public static String Engi1 = "";
    public static String Engi2 = "";
    public static String FuelMin = "";
    public static String FuelIN = "";
    public static String FuelOUT = "";
    public static String FuelTRIP = "";
    public static String FuelREMAN = "";
    public static String Consumo = "";

    //base de datos
    AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "bitacoraBD", null, 1);

    //para llamar datos del Fullactivity
    FullActivity fullActivity = new FullActivity();
    Vuelos vuelos = new Vuelos();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //esta linea de codigo evita la rotacion de la patanlla
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        //objetos de la cabecera
        etTOW = (EditText) findViewById(R.id.etTow);
        etAltura = (EditText) findViewById(R.id.etAltura);
        etVelocidad = (EditText) findViewById(R.id.etVelocidad);
        etMotor1 = (EditText) findViewById(R.id.etEng1);
        etMotor2 = (EditText) findViewById(R.id.etEng2);

        tvRegistro = (TextView) findViewById(R.id.tvRegistro);
        tvNumVuelo = (TextView) findViewById(R.id.tvNumVuelo);
        tvDesde = (TextView) findViewById(R.id.tvOrigen);
        tvHacia = (TextView) findViewById(R.id.tvDestino);

        //objetos TextView para el calculo FUEL
        FuelIn = (TextView) findViewById(R.id.tvFuelin);
        FuelOut = (TextView) findViewById(R.id.tvFuelout);
        FuelTrip = (TextView) findViewById(R.id.tvFuelTrip);
        Remain = (TextView) findViewById(R.id.tvRemain);
        tvConsumo = (TextView) findViewById(R.id.tvConsumo);

        //objetos EditText FUEL
        et9 = (EditText) findViewById(R.id.etfull);
        et10 = (EditText) findViewById(R.id.etTakeoffFuel);
        et11 = (EditText) findViewById(R.id.etLandingFuel);
        et12 = (EditText) findViewById(R.id.etShutdownFuel);
        //toggle Button Fuel
        tgConverUnidades = (ToggleButton) findViewById(R.id.tgConverUnidades);
        tgResulFuel = (ToggleButton) findViewById(R.id.tgResulFuel);

        //foco
        findViewById(R.id.etTow).requestFocus();

        //mantener datos en el activity
        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        etTOW.setText(preferences.getString("TOW",""));
        etAltura.setText(preferences.getString("ALT",""));
        etVelocidad.setText(preferences.getString("VEL",""));
        etMotor1.setText(preferences.getString("FF1",""));
        etMotor2.setText(preferences.getString("FF2",""));
        et9.setText(preferences.getString("FMIN",""));
        et10.setText(preferences.getString("FTO",""));
        et11.setText(preferences.getString("FLND",""));
        et12.setText(preferences.getString("FSD",""));



        //presenta los datos de la cabecera

        tvRegistro.setText(fullActivity.selRegistro);
        tvNumVuelo.setText(fullActivity.NumeroVuelo);
        tvDesde.setText(fullActivity.Origen);
        tvHacia.setText(fullActivity.Destino);



    }
    //METODOS DEL ACTIVITY

    //Codigo para mantener los datos en el DetailActiviti




    ////////////////BASE DE DATOS/////////////////////////

    public void GuardarDatos(View view) {
        //codigo para mostarlos datos y se mantengan en el activity
        SharedPreferences preferencia = getSharedPreferences("datos",Context.MODE_PRIVATE);
        SharedPreferences.Editor Obj_editor = preferencia.edit();
        Obj_editor.putString("TOW", etTOW.getText().toString());
        Obj_editor.putString("ALT", etAltura.getText().toString());
        Obj_editor.putString("VEL", etVelocidad.getText().toString());
        Obj_editor.putString("FF1", etMotor1.getText().toString());
        Obj_editor.putString("FF2", etMotor2.getText().toString());
        Obj_editor.putString("FMIN", et9.getText().toString());
        Obj_editor.putString("FTO", et10.getText().toString());
        Obj_editor.putString("FLND", et11.getText().toString());
        Obj_editor.putString("FSD", et12.getText().toString());
        Obj_editor.commit();


        //se cargan las variables Publicas con los datos del ingresados por el usuario
        Tow = etTOW.getText().toString();
        Alt = etAltura.getText().toString();
        Vel = etVelocidad.getText().toString();
        Engi1 = etMotor1.getText().toString();
        Engi2 = etMotor2.getText().toString();
        FuelMin = et9.getText().toString();
        FuelIN = FuelIn.getText().toString();
        FuelOUT = FuelOut.getText().toString();
        FuelTRIP = FuelTrip.getText().toString();
        FuelREMAN = Remain.getText().toString();
        Consumo = tvConsumo.getText().toString();


        finish();

    }


////////////////////////////////////////////////////////////////

    //**********************************************************************************************************************************************************************************
    //**********************************************************************************************************************************************************************************
    //.......................................................CALCULO DATOS COMBUSTIBLE..............................................................


    //metodo del boton resultado Fuel
    public void CalculoFuel(View view) {


        if (tgResulFuel.isChecked()) {//condicion para calculo de datos combustible
            OperacionFuel();
        } else {
            //datos totales
            valortvFuelin = 0;
            valortvFuelout = 0;
            valortvFuelTrip = 0;
            valorRemain = 0;
            valorConsumo = 0;
            //Limpia los datos
            etTOW.setText("");
            etAltura.setText("");
            etVelocidad.setText("");
            etMotor1.setText("");
            etMotor2.setText("");

            et9.setText("");
            et10.setText("");
            et11.setText("");
            et12.setText("");
            FuelOut.setText("00");
            FuelIn.setText("00");
            FuelTrip.setText("00");
            Remain.setText("00");
            tvConsumo.setText("-----");
        }


    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //metodo para hacer la conversion de las unidades
    public void ConversionUnidades(View view) {
        //este codigo muestra el cambio de unidades al presionar el toggle buttom
        if (tgConverUnidades.isChecked()) {
            Toast.makeText(DetailActivity.this, "Kilogram", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(DetailActivity.this, "Pounds", Toast.LENGTH_SHORT).show();
        }

///////conversion cuando inicia en Kilogramos
        if (flag == 1 && tgResulFuel.isChecked()) { //inicia con Kilogramos y Conversion de kilogramos - Libras

            //System.out.println("ESTAMOS EN K");
            //operaciones
            double fuelOut = valortvFuelout * 2.204;
            double fuelIn = valortvFuelin * 2.204;
            double fuelTrip = valortvFuelTrip * 2.204;
            double remain = valorRemain * 2.204;
            //mostrar resultados
            FuelOut.setText(String.valueOf(fuelOut) + " LB");
            FuelIn.setText(String.valueOf(fuelIn) + " LB");
            FuelTrip.setText(String.valueOf(fuelTrip) + " LB");
            Remain.setText(String.valueOf(remain) + " LB");

            flag = 2;


        } else if (flag == 2 && tgResulFuel.isChecked()) {//Conversion de Libras - kilogramos

            //System.out.println("ESTAMOS EN K2");

            //operaciones
            double fuelOut = valortvFuelout;
            double fuelIn = valortvFuelin;
            double fuelTrip = valortvFuelTrip;
            double remain = valorRemain;
            //mostrar resultados
            FuelOut.setText(String.valueOf(fuelOut) + " KG");
            FuelIn.setText(String.valueOf(fuelIn) + " KG");
            FuelTrip.setText(String.valueOf(fuelTrip) + " KG");
            Remain.setText(String.valueOf(remain) + " KG");


            flag = 1;

        } else if (flag == 3 && tgResulFuel.isChecked()) {//Inicia con Libras y Conversion de Libras - Kilogramos
            //////conversion cuando inicia en libras

            //System.out.println("ESTAMOS EN L");
            //operaciones
            double fuelOut = valortvFuelout * 0.453;
            double fuelIn = valortvFuelin * 0.453;
            double fuelTrip = valortvFuelTrip * 0.453;
            double remain = valorRemain * 0.453;
            //mostrar resultados
            FuelOut.setText(String.valueOf(fuelOut) + " KG");
            FuelIn.setText(String.valueOf(fuelIn) + " KG");
            FuelTrip.setText(String.valueOf(fuelTrip) + " KG");
            Remain.setText(String.valueOf(remain) + " KG");

            flag = 4;


        } else if(flag == 4 && tgResulFuel.isChecked()) {//conversion de Kilogramos - Libras
            //System.out.println("ESTAMOS EN L2");
            //operaciones
            double fuelOut = valortvFuelout;
            double fuelIn = valortvFuelin;
            double fuelTrip = valortvFuelTrip;
            double remain = valorRemain;
            //mostrar resultados
            FuelOut.setText(String.valueOf(fuelOut) + " LB");
            FuelIn.setText(String.valueOf(fuelIn) + " LB");
            FuelTrip.setText(String.valueOf(fuelTrip) + " LB");
            Remain.setText(String.valueOf(remain) + " LB");

            flag = 3;

        }



    }








    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //metodo para realizar todas las operaciones de calculo de combustibles
    public void OperacionFuel(){
        //variables para captura de datos
        String dataF1 = et9.getText().toString();
        String dataF2 = et10.getText().toString();
        String dataF3 = et11.getText().toString();
        String dataF4 = et12.getText().toString();

        if(tgConverUnidades.isChecked()) {//condicion para Kilogramos
            flag = 1; //indica que estamos trabajando en KILOGRAMOS
            //calculo cuando tengo todos los datos en los Edit Text 1 1 1 1
            if ((et9.getText().toString().length() != 0) && (et10.getText().toString().length() != 0) && (et11.getText().toString().length() != 0) && (et12.getText().toString().length() != 0)) {
                //System.out.println("1 1 1 1");
                //convierte los datos de texto String en numeros enteros
                int cantidad = Integer.parseInt(dataF1);
                int fuelTO = Integer.parseInt(dataF2);
                int fuelLND = Integer.parseInt(dataF3);
                int engineOff = Integer.parseInt(dataF4);

                if ((cantidad > fuelTO) && (fuelLND > engineOff) && (fuelTO > fuelLND)) { //condicion cuando los datos ingresados son correctos

                    //operaciones
                    int fuelOut = cantidad - fuelTO;
                    int fuelIn = fuelLND - engineOff;
                    int fuelTrip = fuelTO - fuelLND;
                    int fuelConsumo = cantidad - engineOff;
                    //mostrar resultados
                    FuelOut.setText(String.valueOf(fuelOut));
                    FuelIn.setText(String.valueOf(fuelIn));
                    FuelTrip.setText(String.valueOf(fuelTrip));
                    Remain.setText(dataF4 );
                    tvConsumo.setText(String.valueOf(fuelConsumo));

                    //pasa los datos para la coversion de unidades
                    valortvFuelout = fuelOut;
                    valortvFuelin = fuelIn;
                    valortvFuelTrip = fuelTrip;
                    valorRemain = engineOff;


                } else {
                    Toast.makeText(DetailActivity.this, "Error: Data", Toast.LENGTH_SHORT).show();
                    FuelOut.setText("---");
                    FuelIn.setText("---");
                    FuelTrip.setText("---");
                    Remain.setText("---");
                    tvConsumo.setText("****");
                }

                //condicion solo cuando esta los datos de cantidad, fuel TO y fuel LDN 1 1 1 0
            } else if ((et9.getText().toString().length() != 0) && (et10.getText().toString().length() != 0) && (et11.getText().toString().length() != 0) && (et12.getText().toString().isEmpty())) {
                //System.out.println("1 1 1 0");
                //convierte los datos de texto String en numeros enteros
                int cantidad = Integer.parseInt(dataF1);
                int fuelTO = Integer.parseInt(dataF2);
                int fuelLND = Integer.parseInt(dataF3);


                if ((cantidad > fuelTO) && (fuelTO > fuelLND)) { //condicion cuando los datos ingresados son correctos

                    //operaciones
                    int fuelOut = cantidad - fuelTO;
                    int fuelTrip = fuelTO - fuelLND;

                    //mostrar resultados
                    FuelOut.setText(String.valueOf(fuelOut));
                    FuelIn.setText("00");
                    FuelTrip.setText(String.valueOf(fuelTrip));
                    Remain.setText("00");

                    //pasa los datos para la coversion de unidades
                    valortvFuelout = fuelOut;
                    valortvFuelTrip = fuelTrip;



                } else {
                    Toast.makeText(DetailActivity.this, "Error: Data", Toast.LENGTH_SHORT).show();
                    FuelOut.setText("---");
                    FuelIn.setText("---");
                    FuelTrip.setText("---");
                    Remain.setText("---");
                    tvConsumo.setText("****");
                }


                //condicion solo uando esta los datos de cantidad y fuel TO 1 1 0 0
            } else if ((et9.getText().toString().length() != 0) && (et10.getText().toString().length() != 0) && (et11.getText().toString().isEmpty()) && (et12.getText().toString().isEmpty())) {
                //System.out.println("1 1 0 0");
                //convierte los datos de texto String en numeros enteros
                int cantidad = Integer.parseInt(dataF1);
                int fuelTO = Integer.parseInt(dataF2);


                if (cantidad > fuelTO) { //condicion cuando los datos ingresados son correctos

                    //operaciones
                    int fuelOut = cantidad - fuelTO;

                    //mostrar resultados
                    FuelOut.setText(String.valueOf(fuelOut));
                    FuelIn.setText("00");
                    FuelTrip.setText("00");
                    Remain.setText("00");

                    //pasa los datos para la coversion de unidades
                    valortvFuelout = fuelOut;



                } else {
                    Toast.makeText(DetailActivity.this, "Error: Data", Toast.LENGTH_SHORT).show();
                    FuelOut.setText("---");
                    FuelIn.setText("---");
                    FuelTrip.setText("---");
                    Remain.setText("---");
                    tvConsumo.setText("****");
                }

                //condicion solo uando esta los datos de fuel LND y Engien SD 0 0 1 1
            } else if ((et9.getText().toString().isEmpty()) && (et10.getText().toString().isEmpty()) && (et11.getText().toString().length() != 0) && (et12.getText().toString().length() != 0)) {
                //System.out.println("0 0 1 1");
                //convierte los datos de texto String en numeros enteros
                int fuelLND = Integer.parseInt(dataF3);
                int engineOff = Integer.parseInt(dataF4);


                if (fuelLND > engineOff) { //condicion cuando los datos ingresados son correctos

                    //operaciones
                    int fuelIn = fuelLND - engineOff;

                    //mostrar resultados
                    FuelOut.setText("00");
                    FuelIn.setText(String.valueOf(fuelIn));
                    FuelTrip.setText("00");
                    Remain.setText(dataF4);
                    tvConsumo.setText("****");

                    //pasa los datos para la coversion de unidades
                    valortvFuelin = fuelIn;
                    valorRemain = engineOff;
                }

                //condicion solo cuando esta los datos de fuel TO, FuelLND y Engine SD 0 1 1 1
            } else if ((et9.getText().toString().isEmpty()) && (et10.getText().toString().length() != 0) && (et11.getText().toString().length() != 0) && (et12.getText().toString().length() != 0)) {
                //System.out.println("0 1 1 1");
                //convierte los datos de texto String en numeros enteros
                int fuelTO = Integer.parseInt(dataF2);
                int fuelLND = Integer.parseInt(dataF3);
                int engineOff = Integer.parseInt(dataF4);


                if ((fuelTO > fuelLND) && (fuelLND > engineOff)) { //condicion cuando los datos ingresados son correctos

                    //operaciones
                    int fuelIn = fuelLND - engineOff;
                    int fuelTrip = fuelTO - fuelLND;

                    //mostrar resultados
                    FuelOut.setText("00");
                    FuelIn.setText(String.valueOf(fuelIn));
                    FuelTrip.setText(String.valueOf(fuelTrip));
                    Remain.setText(dataF4);
                    tvConsumo.setText("****");

                    //pasa los datos para la coversion de unidades
                    valortvFuelin = fuelIn;
                    valortvFuelTrip = fuelTrip;
                    valorRemain = engineOff;


                } else {
                    Toast.makeText(DetailActivity.this, "Error: Data", Toast.LENGTH_SHORT).show();
                    FuelOut.setText("---");
                    FuelIn.setText("---");
                    FuelTrip.setText("---");
                    Remain.setText("---");
                    tvConsumo.setText("****");
                }


                //condicion solo uando esta los datos de cantidad y fuel TO 1 0 1 1
            } else if ((et9.getText().toString().length() != 0) && (et10.getText().toString().isEmpty()) && (et11.getText().toString().length() != 0) && (et12.getText().toString().length() != 0)) {
                //System.out.println("1 0 1 1");
                //convierte los datos de texto String en numeros enteros
                int cantidad = Integer.parseInt(dataF1);
                int fuelLND = Integer.parseInt(dataF3);
                int engineOff = Integer.parseInt(dataF4);


                if (fuelLND > engineOff) { //condicion cuando los datos ingresados son correctos

                    //operaciones
                    int fuelIn = fuelLND - engineOff;
                    int fuelConsumo = cantidad - engineOff;


                    //mostrar resultados
                    FuelOut.setText("00");
                    FuelIn.setText(String.valueOf(fuelIn));
                    FuelTrip.setText("00");
                    Remain.setText(dataF4);
                    tvConsumo.setText(String.valueOf(fuelConsumo));

                    //pasa los datos para la coversion de unidades
                    valortvFuelin = fuelIn;
                    valorRemain = engineOff;


                } else {
                    Toast.makeText(DetailActivity.this, "Error: Data", Toast.LENGTH_SHORT).show();
                    FuelOut.setText("---");
                    FuelIn.setText("---");
                    FuelTrip.setText("---");
                    Remain.setText("---");
                    tvConsumo.setText("****");
                }


                //condicion solo uando esta los datos de cantidad y fuel TO 1 1 0 1
            } else if ((et9.getText().toString().length() != 0) && (et10.getText().toString().length() != 0) && (et11.getText().toString().isEmpty()) && (et12.getText().toString().length() != 0)) {
                //System.out.println("1 1 0 1");
                //convierte los datos de texto String en numeros enteros

                int cantidad = Integer.parseInt(dataF1);
                int fuelTO = Integer.parseInt(dataF2);
                int engineOff = Integer.parseInt(dataF4);


                if (cantidad > fuelTO) { //condicion cuando los datos ingresados son correctos

                    //operaciones
                    int fuelOut = cantidad - fuelTO;
                    int fuelConsumo = cantidad - engineOff;


                    //mostrar resultados
                    FuelOut.setText(String.valueOf(fuelOut));
                    FuelIn.setText("00");
                    FuelTrip.setText("00");
                    Remain.setText(dataF4);
                    tvConsumo.setText(String.valueOf(fuelConsumo));

                    //pasa los datos para la coversion de unidades
                    valortvFuelout = fuelOut;


                } else {
                    Toast.makeText(DetailActivity.this, "Error: Data", Toast.LENGTH_SHORT).show();
                    FuelOut.setText("---");
                    FuelIn.setText("---");
                    FuelTrip.setText("---");
                    Remain.setText("---");
                    tvConsumo.setText("****");
                }


                //condicion solo uando esta los datos de fuel TO y fuel LND 0 1 1 0
            } else if ((et9.getText().toString().length() != 0) && (et10.getText().toString().isEmpty()) && (et11.getText().toString().length() != 0) && (et12.getText().toString().length() != 0)) {
                //System.out.println("0 1 1 0 ");
                //convierte los datos de texto String en numeros enteros

                int fuelTO = Integer.parseInt(dataF2);
                int fuelLND = Integer.parseInt(dataF3);


                if (fuelTO > fuelLND) { //condicion cuando los datos ingresados son correctos

                    //operaciones
                    int fuelTrip = fuelTO - fuelLND;


                    //mostrar resultados
                    FuelOut.setText("00");
                    FuelIn.setText("00");
                    FuelTrip.setText(String.valueOf(fuelTrip));
                    Remain.setText("00");
                    tvConsumo.setText("****");

                    //pasa los datos para la coversion de unidades
                    valortvFuelTrip = fuelTrip;



                } else {
                    Toast.makeText(DetailActivity.this, "Error: Data", Toast.LENGTH_SHORT).show();
                    FuelOut.setText("---");
                    FuelIn.setText("---");
                    FuelTrip.setText("---");
                    Remain.setText("---");
                    tvConsumo.setText("****");
                }


            } else {
                Toast.makeText(DetailActivity.this, "Error: Missing Data", Toast.LENGTH_SHORT).show();
                FuelOut.setText("---");
                FuelIn.setText("---");
                FuelTrip.setText("---");
                Remain.setText("---");
                tvConsumo.setText("****");
            }


        }else {//////////***********************************CAMBIO DEL BOTON DE CONVERSION DE UNIDADES ******************************

            flag = 3; //indica que estamos trabajando en LIBRAS
            //System.out.println("no check");
            //calculo cuando tengo todos los datos en los Edit Text 1 1 1 1
            if ((et9.getText().toString().length() != 0) && (et10.getText().toString().length() != 0) && (et11.getText().toString().length() != 0) && (et12.getText().toString().length() != 0)) {
                //System.out.println("1 1 1 1");
                //convierte los datos de texto String en numeros enteros
                int cantidad = Integer.parseInt(dataF1);
                int fuelTO = Integer.parseInt(dataF2);
                int fuelLND = Integer.parseInt(dataF3);
                int engineOff = Integer.parseInt(dataF4);

                if ((cantidad > fuelTO) && (fuelLND > engineOff) && (fuelTO > fuelLND)) { //condicion cuando los datos ingresados son correctos

                    //operaciones
                    int fuelOut = cantidad - fuelTO;
                    int fuelIn = fuelLND - engineOff;
                    int fuelTrip = fuelTO - fuelLND;
                    int fuelConsumo = cantidad - engineOff;
                    //mostrar resultados
                    FuelOut.setText(String.valueOf(fuelOut));
                    FuelIn.setText(String.valueOf(fuelIn));
                    FuelTrip.setText(String.valueOf(fuelTrip));
                    Remain.setText(dataF4 );
                    tvConsumo.setText(String.valueOf(fuelConsumo));

                    //pasa los datos para la conversion de unidades
                    valortvFuelout = fuelOut;
                    valortvFuelin = fuelIn;
                    valortvFuelTrip = fuelTrip;
                    valorRemain = engineOff;

                } else {
                    Toast.makeText(DetailActivity.this, "Error: Data", Toast.LENGTH_SHORT).show();
                    FuelOut.setText("---");
                    FuelIn.setText("---");
                    FuelTrip.setText("---");
                    Remain.setText("---");
                    tvConsumo.setText("****");
                }

                //condicion solo uando esta los datos de cantidad, fuel TO y fuel LDN 1 1 1 0
            } else if ((et9.getText().toString().length() != 0) && (et10.getText().toString().length() != 0) && (et11.getText().toString().length() != 0) && (et12.getText().toString().isEmpty())) {
                //System.out.println("1 1 1 0");
                //convierte los datos de texto String en numeros enteros
                int cantidad = Integer.parseInt(dataF1);
                int fuelTO = Integer.parseInt(dataF2);
                int fuelLND = Integer.parseInt(dataF3);


                if ((cantidad > fuelTO) && (fuelTO > fuelLND)) { //condicion cuando los datos ingresados son correctos

                    //operaciones
                    int fuelOut = cantidad - fuelTO;
                    int fuelTrip = fuelTO - fuelLND;

                    //mostrar resultados
                    FuelOut.setText(String.valueOf(fuelOut));
                    FuelIn.setText("00");
                    FuelTrip.setText(String.valueOf(fuelTrip));
                    Remain.setText("00");
                    tvConsumo.setText("****");

                    //pasa los datos para la conversion de unidades
                    valortvFuelout = fuelOut;
                    valortvFuelTrip = fuelTrip;



                } else {
                    Toast.makeText(DetailActivity.this, "Error: Data", Toast.LENGTH_SHORT).show();
                    FuelOut.setText("---");
                    FuelIn.setText("---");
                    FuelTrip.setText("---");
                    Remain.setText("---");
                    tvConsumo.setText("****");
                }


                //condicion solo uando esta los datos de cantidad y fuel TO 1 1 0 0
            } else if ((et9.getText().toString().length() != 0) && (et10.getText().toString().length() != 0) && (et11.getText().toString().isEmpty()) && (et12.getText().toString().isEmpty())) {
                //System.out.println("1 1 0 0");
                //convierte los datos de texto String en numeros enteros
                int cantidad = Integer.parseInt(dataF1);
                int fuelTO = Integer.parseInt(dataF2);


                if (cantidad > fuelTO) { //condicion cuando los datos ingresados son correctos

                    //operaciones
                    int fuelOut = cantidad - fuelTO;

                    //mostrar resultados
                    FuelOut.setText(String.valueOf(fuelOut));
                    FuelIn.setText("00");
                    FuelTrip.setText("00");
                    Remain.setText("00");
                    tvConsumo.setText("****");

                    //pasa los datos para la conversion de unidades
                    valortvFuelout = fuelOut;



                } else {
                    Toast.makeText(DetailActivity.this, "Error: Data", Toast.LENGTH_SHORT).show();
                    FuelOut.setText("---");
                    FuelIn.setText("---");
                    FuelTrip.setText("---");
                    Remain.setText("---");
                    tvConsumo.setText("****");
                }

                //condicion solo uando esta los datos de fuel LND y Engien SD 0 0 1 1
            } else if ((et9.getText().toString().isEmpty()) && (et10.getText().toString().isEmpty()) && (et11.getText().toString().length() != 0) && (et12.getText().toString().length() != 0)) {
                //System.out.println("0 0 1 1");
                //convierte los datos de texto String en numeros enteros
                int fuelLND = Integer.parseInt(dataF3);
                int engineOff = Integer.parseInt(dataF4);


                if (fuelLND > engineOff) { //condicion cuando los datos ingresados son correctos

                    //operaciones
                    int fuelIn = fuelLND - engineOff;

                    //mostrar resultados
                    FuelOut.setText("00");
                    FuelIn.setText(String.valueOf(fuelIn));
                    FuelTrip.setText("00");
                    Remain.setText(dataF4);
                    tvConsumo.setText("****");

                    //pasa los datos para la conversion de unidades
                    valortvFuelin = fuelIn;
                    valorRemain = engineOff;
                }

                //condicion solo cuando esta los datos de fuel TO, FuelLND y Engine SD 0 1 1 1
            } else if ((et9.getText().toString().isEmpty()) && (et10.getText().toString().length() != 0) && (et11.getText().toString().length() != 0) && (et12.getText().toString().length() != 0)) {
                //System.out.println("0 1 1 1");
                //convierte los datos de texto String en numeros enteros
                int fuelTO = Integer.parseInt(dataF2);
                int fuelLND = Integer.parseInt(dataF3);
                int engineOff = Integer.parseInt(dataF4);


                if ((fuelTO > fuelLND) && (fuelLND > engineOff)) { //condicion cuando los datos ingresados son correctos

                    //operaciones
                    int fuelIn = fuelLND - engineOff;
                    int fuelTrip = fuelTO - fuelLND;

                    //mostrar resultados
                    FuelOut.setText("00");
                    FuelIn.setText(String.valueOf(fuelIn));
                    FuelTrip.setText(String.valueOf(fuelTrip));
                    Remain.setText(dataF4);
                    tvConsumo.setText("****");

                    //pasa los datos para la coversion de unidades
                    valortvFuelin = fuelIn;
                    valortvFuelTrip = fuelTrip;
                    valorRemain = engineOff;


                } else {
                    Toast.makeText(DetailActivity.this, "Error: Data", Toast.LENGTH_SHORT).show();
                    FuelOut.setText("---");
                    FuelIn.setText("---");
                    FuelTrip.setText("---");
                    Remain.setText("---");
                    tvConsumo.setText("****");
                }


                //condicion solo uando esta los datos de cantidad y fuel TO 1 0 1 1
            } else if ((et9.getText().toString().length() != 0) && (et10.getText().toString().isEmpty()) && (et11.getText().toString().length() != 0) && (et12.getText().toString().length() != 0)) {
                //System.out.println("1 0 1 1");
                //convierte los datos de texto String en numeros enteros
                int cantidad = Integer.parseInt(dataF1);
                int fuelLND = Integer.parseInt(dataF3);
                int engineOff = Integer.parseInt(dataF4);


                if (fuelLND > engineOff) { //condicion cuando los datos ingresados son correctos

                    //operaciones
                    int fuelIn = fuelLND - engineOff;
                    int fuelConsumo = cantidad - engineOff;

                    //mostrar resultados
                    FuelOut.setText("00");
                    FuelIn.setText(String.valueOf(fuelIn));
                    FuelTrip.setText("00");
                    Remain.setText(dataF4);
                    tvConsumo.setText(String.valueOf(fuelConsumo));

                    //pasa los datos para la coversion de unidades
                    valortvFuelin = fuelIn;
                    valorRemain = engineOff;


                } else {
                    Toast.makeText(DetailActivity.this, "Error: Data", Toast.LENGTH_SHORT).show();
                    FuelOut.setText("---");
                    FuelIn.setText("---");
                    FuelTrip.setText("---");
                    Remain.setText("---");
                    tvConsumo.setText("****");
                }


                //condicion solo uando esta los datos de cantidad y fuel TO 1 1 0 1
            } else if ((et9.getText().toString().length() != 0) && (et10.getText().toString().length() != 0) && (et11.getText().toString().isEmpty()) && (et12.getText().toString().length() != 0)) {
                //System.out.println("1 1 0 1");
                //convierte los datos de texto String en numeros enteros

                int cantidad = Integer.parseInt(dataF1);
                int fuelTO = Integer.parseInt(dataF2);
                int engineOff = Integer.parseInt(dataF4);


                if (cantidad > fuelTO) { //condicion cuando los datos ingresados son correctos

                    //operaciones
                    int fuelOut = cantidad - fuelTO;
                    int fuelConsumo = cantidad - engineOff;


                    //mostrar resultados
                    FuelOut.setText(String.valueOf(fuelOut));
                    FuelIn.setText("00");
                    FuelTrip.setText("00");
                    Remain.setText(dataF4);
                    tvConsumo.setText(String.valueOf(fuelConsumo));

                    //pasa los datos para la coversion de unidades
                    valortvFuelout = fuelOut;



                } else {
                    Toast.makeText(DetailActivity.this, "Error: Data", Toast.LENGTH_SHORT).show();
                    FuelOut.setText("---");
                    FuelIn.setText("---");
                    FuelTrip.setText("---");
                    Remain.setText("---");
                    tvConsumo.setText("****");
                }


                //condicion solo uando esta los datos de fuel TO y fuel LND 0 1 1 0
            } else if ((et9.getText().toString().length() != 0) && (et10.getText().toString().isEmpty()) && (et11.getText().toString().length() != 0) && (et12.getText().toString().length() != 0)) {
                //System.out.println("0 1 1 0 ");
                //convierte los datos de texto String en numeros enteros

                int fuelTO = Integer.parseInt(dataF2);
                int fuelLND = Integer.parseInt(dataF3);


                if (fuelTO > fuelLND) { //condicion cuando los datos ingresados son correctos

                    //operaciones
                    int fuelTrip = fuelTO - fuelLND;


                    //mostrar resultados
                    FuelOut.setText("00");
                    FuelIn.setText("00");
                    FuelTrip.setText(String.valueOf(fuelTrip));
                    Remain.setText("00");
                    tvConsumo.setText("****");

                    //pasa los datos para la coversion de unidades
                    valortvFuelTrip = fuelTrip;



                } else {
                    Toast.makeText(DetailActivity.this, "Error: Data", Toast.LENGTH_SHORT).show();
                    FuelOut.setText("---");
                    FuelIn.setText("---");
                    FuelTrip.setText("---");
                    Remain.setText("---");
                    tvConsumo.setText("****");
                }


            } else {
                Toast.makeText(DetailActivity.this, "Error: Missing Data", Toast.LENGTH_SHORT).show();
                FuelOut.setText("---");
                FuelIn.setText("---");
                FuelTrip.setText("---");
                Remain.setText("---");
                tvConsumo.setText("****");
            }

        }

    }






}
