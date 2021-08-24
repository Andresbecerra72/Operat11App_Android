package apps.paquete.andres.operat11;



import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andres.operat11.R;

import apps.paquete.andres.operat11.constantes.Constantes;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class FullActivity extends AppCompatActivity {

    //Se declaran las variables Graficas de la actividad de Diseño

    //variable para el uso del date  picker
    private static final String TAG = "FullActivity";

    //variable graficas tipo EditText
    private EditText etRegistro;
    private EditText etNumVuelo;
    private EditText etDesde;
    private EditText etHacia;
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
    private EditText etCarga;


    //variable graficas tipo TextView
    private TextView Fecha;
    private DatePickerDialog.OnDateSetListener mDateSetListener; //declarando variable del datePicker
    private TextView Tipo;
    private TextView MatriculaHK;
    private TextView TotalCrew;
    private TextView TotalAircraft;
    private TextView TvVfr;
    private TextView TvIfr;
    private TextView Nocturno;
    private TextView HorasHoy;

    //variable Spinner
    private Spinner AeronaveFac;

    //variables publicas para el desarrollo interno de la logica
    //CREW
    public int hora1crew = 0;
    public int min1crew = 0;
    public int hora2crew = 0;
    public int min2crew = 0;
    //AIRCRAFT
    public int hora1Aircraft = 0;
    public int min1Aircraft = 0;
    public int hora2Aircraft = 0;
    public int min2Aircraft = 0;
    //variables para la BASE DE DATOS Bitacora para el calculo de TotalCrew y TotalAircraft
    public int totalTripHoraBD = 0;
    public int totalTripMinBD = 0;
    public String Total_crewBD = "";
    public int totalAerHoraBD = 0;
    public int totalAerMinBD = 0;
    public String Total_aircraftBD = "";
    public int ciclosBD = 0;
    public int vfrHoraBD = 0;
    public int vfrMinBD = 0;
    public int ifrHoraBD = 0;
    public int ifrMinBD = 0;
    public int noctHoraBD = 0;
    public int noctMinBD = 0;
    public int paxBD = 0;
    public int cargaBD = 0;
    public int fuelBD = 0;


    //////////////////////varibles del documento PDF/////////////////
    public TemplatePDF templatePDF;
    private String[] header = {"Vuelo", "De", "A", "Prend", "Apag", "Vfr", "Ifr", "Noct", "Total Trip", "Decol", "Aterri", "Total Aer", "No Aterri", "Pax", "Carga Kg", "Combus Gls"};
    private String[] header1 = {"Total Trip", "VFR", "IFR", "Noct", "Total Aer", "No Aterri", "Pax", "Carga Kg", "Combus Gls"};
    private String[] header2 = {"Vuelo", "Orig", "Dest", "TOW", "FUEL MIN", "FUEL TAX IN", "FUEL TAX OUT", "TRIP FUEL", "FF ENG 1", "FF ENG 2", "ALT", "MACH/IAS", "FUEL REMAN"};
    private String shortText = "Firma: __________________";
    private String longText = "";

//*******************************************************************************//

    public static String selRegistro = ""; //variable para resgistro de documento PDF
    public static String numHKPdf = ""; //variable para matricula HK de documento PDF
    public static String numFACPdf = ""; //variable para matricula FAC de documento PDF
    public static String TipoAerPdf = ""; //variable para tipo aeronave de documento PDF
    public String date = "";
    public int fechaOper = 0;
    public static String NumeroVuelo = ""; //variable para Registro de combustible
    public static String Origen = ""; //variable para Registro de combustible
    public static String Destino = ""; //variable para Registro de combustible
    public static String numFAC = "";
    public static int flag = 0; //banderola

    //----------VARIABLES PARA CALCULO DE FECHAS------------------------------------
    public static String years = "";
    public static String months = "";
    public static int weeks = 0;
    public static int days = 0;

//---------------------------------------------------------------------------
    //////////////////
    Vuelos RegVuelo = new Vuelos();

    //base de datos
    AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "bitacoraBD", null, 1);//base de datos para el registro
    AdminSQLiteOpenHelper admincount = new AdminSQLiteOpenHelper(this, "countBD", null, 1);//base de datos para el contador de horas

    ///variables publicas para base de datos VUELOS
    public static String numvuelo = "";
    public static String desde = "";
    public static String hacia = "";
    public static String Prendida_hor = "";
    public static String Prendida_min = "";
    public static String Apagada_hor = "";
    public static String Apagada_min = "";
    public static String Vfr = "";
    public static String Ifr = "";
    public static String Noct = "";
    public static String Total_crew = "";
    public static String Decolage_hor = "";
    public static String Decolage_min = "";
    public static String Aterrizaje_hor = "";
    public static String Aterrizaje_min = "";
    public static String Total_aircraft = "";
    public static int Ciclos = 0;
    public static String Pax = "";
    public static String Carga = "";
    public static String Fuel = "0";

    //varibles publicas para base de datos TOTALES
    public static String Totales_tripulacion = "";
    public static int Total_tripulacion_hor = 0;
    public static int Total_tripulacion_min = 0;
    public static int Total_aeronave_hor = 0;
    public static int Total_aeronave_min = 0;
    public static String Totales_aeronave = "";
    public static int Total_Vfr_Hora = 0;
    public static int Total_Vfr_Min = 0;
    public static int Total_Ifr_Hora = 0;
    public static int Total_Ifr_Min = 0;
    public static int Total_Nocturno_Hora = 0;
    public static int Total_Nocturno_Min = 0;
    public static int Total_Carga = 0;
    public static int Total_Ciclos = 0;
    public static int Total_Pax = 0;
    public static int Total_Fuel = 0;

/////////////////


    ///INICIO:****---****---**
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full);
        //esta linea de codigo evita la rotacion de la patanlla
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        //Se conectan las variables logicas con la interface grafica
        //objetos EditText
        etRegistro = (EditText) findViewById(R.id.etRegistro);
        etNumVuelo = (EditText) findViewById(R.id.etNumVuelo);
        etDesde = (EditText) findViewById(R.id.etDesde);
        etHacia = (EditText) findViewById(R.id.etHacia);
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
        etFuel = (EditText) findViewById(R.id.etFuel);
        etPax = (EditText) findViewById(R.id.etPax);
        etCarga = (EditText) findViewById(R.id.etCarga);


        //objeto Text View
        Fecha = (TextView) findViewById(R.id.tvFecha);
        Tipo = (TextView) findViewById(R.id.tvTipoAeronave);
        MatriculaHK = (TextView) findViewById(R.id.tvMatriculaHK);
        Nocturno = (TextView) findViewById(R.id.tvNoc1);
        TvIfr = (TextView) findViewById(R.id.tvIfr1);
        TvVfr = (TextView) findViewById(R.id.tvVfr1);
        TotalAircraft = (TextView) findViewById(R.id.tvTotalAer);
        TotalCrew = (TextView) findViewById(R.id.tvTotalTrip);
        HorasHoy = (TextView) findViewById(R.id.tvDaily);

        //foco
        findViewById(R.id.etRegistro).requestFocus();

        ///////////////////////////

//mantener datos en el activity
        SharedPreferences preferences = getSharedPreferences("full", Context.MODE_PRIVATE);
        etRegistro.setText(preferences.getString("REG", ""));
        etNumVuelo.setText(preferences.getString("FLIGHT", ""));
        etDesde.setText(preferences.getString("FROM", ""));
        etHacia.setText(preferences.getString("TO", ""));
        et1.setText(preferences.getString("STARTH", ""));
        et2.setText(preferences.getString("STARTM", ""));
        et3.setText(preferences.getString("FINH", ""));
        et4.setText(preferences.getString("FINM", ""));
        et5.setText(preferences.getString("DESPH", ""));
        et6.setText(preferences.getString("DESPM", ""));
        et7.setText(preferences.getString("LNDH", ""));
        et8.setText(preferences.getString("LNDM", ""));
        etPax.setText(preferences.getString("PAX", ""));
        etCarga.setText(preferences.getString("CARGA", ""));
        etFuel.setText(preferences.getString("FUEL", ""));
        ///////////////////////////////


/////////////////////////////////////////////////////////////////////////////////////////*****
        /////////////cargar el documento PDF//////////////////

        templatePDF = new TemplatePDF(getApplicationContext());


////////////////////////////////////////////////////////////////////////////////////////******
/////////////////////////////////codigo del DATEPICKER//////////////
        //Fecha.setOnClickListener(new View.OnClickListener() {
         //   @Override
         //   public void onClick(View v) {


                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);



                //DatePickerDialog dialog = new DatePickerDialog(FullActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year, month, day);
               // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
              //  dialog.show();

          //  }
       // });

       // mDateSetListener = new DatePickerDialog.OnDateSetListener() {
         //   @Override
         //   public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
          //      Log.d(TAG, "onDateSet: dd/mm/yyy: " + day + "/" + month + "/" + year);

                date = twoDigits(day) + "/" + twoDigits(month) + "/" + year;

                Fecha.setText(date);
           // }
       // };


///////////////////////////////codigo del SPINNER/////////////////////////


        //objeto SPinner
        AeronaveFac = (Spinner) findViewById(R.id.spAeronave);
        String[] MatriculaFAC = {"Num FAC", "FAC1171", "FAC1172", "FAC1180", "FAC1184", "FAC1187", "FAC1190", "FAC1191", "FAC1192", "FAC1193", "FAC1194"};

        //adaptador que permite vincular el Array MatriculaFAC con el spinner y tambien permite vincular el diseño de los items en el spinner "spinner_item_edit"
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item_edit, MatriculaFAC);
        AeronaveFac.setAdapter(adapter);

        //metodo que permite la seleccion de la aeronave y muestra el TIPO y la matricula HK
        AeronaveFac.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //Tipo.setText(parent.getItemAtPosition(position).toString());
                String seleccion = parent.getItemAtPosition(position).toString();//getSelectedItem().toString();
                if (seleccion.equals("FAC1171")) {

                    Tipo.setText("EMB-145");
                    MatriculaHK.setText("HK4525");

                } else if (seleccion.equals("FAC1172")) {

                    Tipo.setText("EMB-145");
                    MatriculaHK.setText("HK4535");

                } else if (seleccion.equals("FAC1180")) {

                    Tipo.setText("EMB-170");
                    MatriculaHK.setText("HK4528");

                } else if (seleccion.equals("FAC1184")) {

                    Tipo.setText("ATR-42");
                    MatriculaHK.setText("HK4806");

                } else if (seleccion.equals("FAC1187")) {

                    Tipo.setText("ATR-42");
                    MatriculaHK.setText("HK4862");

                } else if (seleccion.equals("FAC1190")) {

                    Tipo.setText("ATR-42");
                    MatriculaHK.setText("HK5104");

                } else if (seleccion.equals("FAC1191")) {

                    Tipo.setText("ATR-42");
                    MatriculaHK.setText("HK4979");

                } else if (seleccion.equals("FAC1192")) {

                    Tipo.setText("ATR-42");
                    MatriculaHK.setText("HK5114");

                } else if (seleccion.equals("FAC1193")) {

                    Tipo.setText("ATR-42");
                    MatriculaHK.setText("HK5129");

                } else if (seleccion.equals("FAC1194")) {

                    Tipo.setText("ATR-42");
                    MatriculaHK.setText("HK5130");

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
////////////////////////////////////////////////////////////////////////////////////////////

        horasTotalesHoy();//carga lashoras totales del dia

    }


//METODOS DEL CODIGO:


    ////////CODIGO PARA MANTENER DATOS EN LOS EDIT TEXT/////////////////
    public void cargarActivity() {
        //codigo para mostarlos datos y se mantengan en el activity
        SharedPreferences preferencia = getSharedPreferences("full", Context.MODE_PRIVATE);
        SharedPreferences.Editor Obj_editor = preferencia.edit();
        Obj_editor.putString("REG", etRegistro.getText().toString());
        Obj_editor.putString("FLIGHT", etNumVuelo.getText().toString());
        Obj_editor.putString("FROM", etDesde.getText().toString());
        Obj_editor.putString("TO", etHacia.getText().toString());
        Obj_editor.putString("STARTH", et1.getText().toString());
        Obj_editor.putString("STARTM", et2.getText().toString());
        Obj_editor.putString("FINH", et3.getText().toString());
        Obj_editor.putString("FINM", et4.getText().toString());
        Obj_editor.putString("DESPH", et5.getText().toString());
        Obj_editor.putString("DESPM", et6.getText().toString());
        Obj_editor.putString("LNDH", et7.getText().toString());
        Obj_editor.putString("LNDM", et8.getText().toString());
        Obj_editor.putString("PAX", etPax.getText().toString());
        Obj_editor.putString("CARGA", etCarga.getText().toString());
        Obj_editor.putString("FUEL", etFuel.getText().toString());
        Obj_editor.commit();
    }
    //************************************************* TRIPULACION *************************************************************

    //Este metodo captura la hora actual para la Tripulacion (EL CELULAR DEBE TENER FORMATO 24HORAS)

    public void horaStartCrew(View view) {

        hora1crew = capHora();
        min1crew = capMin();

        //este codigo convierte los valores de capHora y capMinutos en texto para imprimirlo en el editText "CREW STAR"
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

        //este codigo deshabilita el Boton
        Button btn1crew = (Button) findViewById(R.id.Prendida);
        btn1crew.setEnabled(false);

        cargarActivity();
    }

    public void horaFinishCrew(View view) {


        //Deteccion de ERRORES
        if ((et1.getText().toString().length() != 0) && (et2.getText().toString().length() != 0) && (et5.getText().toString().length() != 0) && (et6.getText().toString().length() != 0) && (et7.getText().toString().length() != 0) && (et8.getText().toString().length() != 0)) {

            hora2crew = capHora();
            min2crew = capMin();


            //este codigo convierte los valores de capHora y capMinutos en texto para imprimirlo en el editText "CREW FINISH"
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
            //este codigo deshabilita el Boton
            Button btn2crew = (Button) findViewById(R.id.Apagada);
            btn2crew.setEnabled(false);


            //ingreso AUTOMATICO de parametros
            InsertCrew(null); //llama al metodo InsertCrew(null) con parametros null para que el boton ADD funciones correctamente
            calcOpera();

        } else { //si no hay datos en los edit text presenta este error

            Toast.makeText(FullActivity.this, "Error: Missing Data", Toast.LENGTH_SHORT).show();
            TotalCrew.setText("null");
            TotalAircraft.setText("null");
            TvVfr.setText("null");
            TvIfr.setText("null");
            Nocturno.setText("null");
        }

        cargarActivity();
    }

    //codigo para ingresar MANUALMENTE y AUTOMATICAMENTE los datos de vuelo de la tripulacion boton ADD
    public void InsertCrew(View view) {

        ciclosBD = 1;

        InsertAircraft(null); //llama al metodo InsertAircraft(null) con parametros null para que el boton ADD funciones correctamente

//Deteccion de ERRORES cuando no hay datos en los edit text
        if ((et1.getText().toString().length() != 0) && (et2.getText().toString().length() != 0) && (et3.getText().toString().length() != 0) && (et4.getText().toString().length() != 0) && (et5.getText().toString().length() != 0) && (et6.getText().toString().length() != 0) && (et7.getText().toString().length() != 0) && (et8.getText().toString().length() != 0)) {

            //variables para captura de datos
            String dataH1 = et1.getText().toString();
            String dataH2 = et3.getText().toString();
            String dataM1 = et2.getText().toString();
            String dataM2 = et4.getText().toString();
            int nroH1 = Integer.parseInt(dataH1);
            int nroH2 = Integer.parseInt(dataH2);
            int nroM1 = Integer.parseInt(dataM1);
            int nroM2 = Integer.parseInt(dataM2);
            //variables publicas
            hora1crew = nroH1;
            min1crew = nroM1;
            hora2crew = nroH2;
            min2crew = nroM2;
            //variables de operacion
            String totalCrew1 = "";
            int ophours = nroH2 - nroH1;
            int opminut = nroM2 - nroM1;
            int totalMin = 0;
            int totalHor = 0;


            //logica para informar los ERRORES en el ingreso de los datos manuales
            if (hora1crew >= 24 || hora1Aircraft >= 24 || hora2crew >= 24 || hora2Aircraft >= 24 || min1crew >= 60 || min1Aircraft >= 60 || min2crew >= 60 || min2Aircraft >= 60) {
                Toast.makeText(FullActivity.this, "Error: Hour/Minutes set", Toast.LENGTH_SHORT).show();
                TotalCrew.setText("null");
                TotalAircraft.setText("null");
                TvVfr.setText("null");
                TvIfr.setText("null");
                Nocturno.setText("null");
            } else if (nroH2 == nroH1 && nroM2 < nroM1) {
                Toast.makeText(FullActivity.this, "Error: Minutes set", Toast.LENGTH_SHORT).show();
                TotalCrew.setText("null");
            } else {//logica para el calculo de la diferencia hora inicial vs hora final
                if (ophours < 0 && opminut < 0) {//cuando el dato de Hora y minutos son NEGATIVOS -;-
                    //System.out.println("negativo - negativo");
                    totalHor = (23 - nroH1) + nroH2;
                    totalMin = (60 - nroM1) + nroM2;

                    totalTripHoraBD = totalHor;//datos para BD calcular totalCrew
                    totalTripMinBD = totalMin; //datos para BD calcular totalCrew

                    if (totalHor < 10 && totalMin < 10) {
                        totalCrew1 = "0" + String.valueOf(totalHor) + ":0" + String.valueOf(totalMin);
                        TotalCrew.setText(totalCrew1);
                    } else if (totalHor < 10) {
                        totalCrew1 = "0" + String.valueOf(totalHor) + ":" + String.valueOf(totalMin);
                        TotalCrew.setText(totalCrew1);
                    } else if (totalMin < 10) {
                        totalCrew1 = String.valueOf(totalHor) + ":0" + String.valueOf(totalMin);
                        TotalCrew.setText(totalCrew1);
                    } else {
                        totalCrew1 = String.valueOf(totalHor) + ":" + String.valueOf(totalMin);
                        TotalCrew.setText(totalCrew1);
                    }

                } else if (ophours < 0 && opminut >= 0) { //condicion cuando el resultado hora es NEGATIVO y resultado minutos es POSITIVO O IGUAL A CERO -;+
                    //System.out.println("negativo - positivo = 0");
                    totalHor = (24 - nroH1) + nroH2;

                    totalTripHoraBD = totalHor;//datos para BD calcular totalCrew
                    totalTripMinBD = opminut; //datos para BD calcular totalCrew

                    if (totalHor < 10 && opminut < 10) {
                        totalCrew1 = "0" + String.valueOf(totalHor) + ":0" + String.valueOf(opminut);
                        TotalCrew.setText(totalCrew1);
                    } else if (totalHor < 10) {
                        totalCrew1 = "0" + String.valueOf(totalHor) + ":" + String.valueOf(opminut);
                        TotalCrew.setText(totalCrew1);
                    } else if (opminut < 10) {
                        totalCrew1 = String.valueOf(totalHor) + ":0" + String.valueOf(opminut);
                        TotalCrew.setText(totalCrew1);
                    } else {
                        totalCrew1 = String.valueOf(totalHor) + ":" + String.valueOf(opminut);
                        TotalCrew.setText(totalCrew1);
                    }


                } else if (ophours >= 0 && opminut < 0) { //condicion cuando el resultado horas es POSITIVO IGUAL A CERO  y el resultado minutos es NEGATIVO +;-
                    // System.out.println("positivo = 0 - negativo");
                    ophours = ophours - 1;
                    totalMin = (60 - nroM1) + nroM2;

                    totalTripHoraBD = ophours;//datos para BD calcular totalCrew
                    totalTripMinBD = totalMin; //datos para BD calcular totalCrew

                    if (ophours < 10 && totalMin > 10) {
                        totalCrew1 = "0" + String.valueOf(ophours) + ":" + String.valueOf(totalMin);
                        TotalCrew.setText(totalCrew1);
                    } else if (ophours < 10 && totalMin < 10) {
                        totalCrew1 = "0" + String.valueOf(ophours) + ":0" + String.valueOf(totalMin);
                        TotalCrew.setText(totalCrew1);
                    } else {
                        totalMin = (60 - nroM1) + nroM2;
                        totalCrew1 = String.valueOf(ophours) + ":" + String.valueOf(totalMin);
                        TotalCrew.setText(totalCrew1);
                    }
                } else { //condiciones de resultado horas y minutos mayores a uno y POSITIVOS +;+
                    //System.out.println("positivo - positivo");

                    totalTripHoraBD = ophours;//datos para BD calcular totalCrew
                    totalTripMinBD = opminut; //datos para BD calcular totalCrew

                    if (ophours < 10 && opminut < 10) {
                        totalCrew1 = "0" + String.valueOf(ophours) + ":0" + String.valueOf(opminut);
                        TotalCrew.setText(totalCrew1);
                    } else if (ophours < 10 && opminut > 10) {
                        totalCrew1 = "0" + String.valueOf(ophours) + ":" + String.valueOf(opminut);
                        TotalCrew.setText(totalCrew1);
                    } else if (ophours > 10 && opminut < 10) {
                        totalCrew1 = String.valueOf(ophours) + ":0" + String.valueOf(opminut);
                        TotalCrew.setText(totalCrew1);
                    } else if (ophours == 10 && opminut < 10) {
                        totalCrew1 = String.valueOf(ophours) + ":0" + String.valueOf(opminut);
                        TotalCrew.setText(totalCrew1);
                    } else if (ophours < 10 && opminut == 10) {
                        totalCrew1 = "0" + String.valueOf(ophours) + ":" + String.valueOf(opminut);
                        TotalCrew.setText(totalCrew1);
                    } else {
                        //System.out.println("crudos");
                        totalCrew1 = String.valueOf(ophours) + ":" + String.valueOf(opminut);
                        TotalCrew.setText(totalCrew1);
                    }
                }
            }
            calcOpera();
        } else { //si no hay datos en los edit text presenta este error
            Toast.makeText(FullActivity.this, "Error: Missing Data", Toast.LENGTH_SHORT).show();
            TotalCrew.setText("null");
            TotalAircraft.setText("null");
            TvVfr.setText("null");
            TvIfr.setText("null");
            Nocturno.setText("null");
        }
        cargarActivity();
    }


    //*********************************************************** AERONAVE ****************************************************
    //Este metodo captura la hora actual para la Aeronave

    public void horaStartAircraft(View view) {


        hora1Aircraft = capHora();
        min1Aircraft = capMin();

        //este codigo convierte los valores de capHora y capMinutos en texto para imprimirlo en el editText "CREW STAR"
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
        //este codigo deshabilita el Boton
        Button btn1Aircraft = (Button) findViewById(R.id.Decolage);
        btn1Aircraft.setEnabled(false);
        cargarActivity();
    }

    public void horaFinishAircraft(View view) {

        //ciclos = ciclos + 1;

        //Deteccion de ERRORES
        if ((et5.getText().toString().length() != 0) && (et6.getText().toString().length() != 0)) {


            hora2Aircraft = capHora();
            min2Aircraft = capMin();


            //este codigo convierte los valores de capHora y capMinutos en texto para imprimirlo en el editText "CREW FINISH"
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

            //este codigo deshabilita el Boton
            Button btn2Aircraft = (Button) findViewById(R.id.Aterrizaje);
            btn2Aircraft.setEnabled(false);
            //ingreso AUTOMATICO de parametros
            InsertAircraft(null); //llama al metodo InsertAircraft(null) con parametros null para que el boton ADD funciones correctamente

        } else { //si no hay datos en los edit text presenta este error
            Toast.makeText(FullActivity.this, "Error: Missing Data", Toast.LENGTH_SHORT).show();
            TotalCrew.setText("null");
            TotalAircraft.setText("null");
            TvVfr.setText("null");
            TvIfr.setText("null");
            Nocturno.setText("null");
        }

        cargarActivity();
    }

    //codigo para ingresar MANUALMENTE Y AUTOMATICAMENTE los datos de vuelo de la Aeronave boton ADD
    public void InsertAircraft(View view) {

        //Deteccion de ERRORES cuando no hay datos en los edit text

        if ((et5.getText().toString().length() != 0) && (et6.getText().toString().length() != 0) && (et7.getText().toString().length() != 0) && (et8.getText().toString().length() != 0)) {

            String dataHra1 = et5.getText().toString();
            String dataHra2 = et7.getText().toString();
            String dataMin1 = et6.getText().toString();
            String dataMin2 = et8.getText().toString();
            int nroHra1 = Integer.parseInt(dataHra1);
            int nroHra2 = Integer.parseInt(dataHra2);
            int nroMin1 = Integer.parseInt(dataMin1);
            int nroMin2 = Integer.parseInt(dataMin2);
            //variables publicas
            hora1Aircraft = nroHra1;
            min1Aircraft = nroMin1;
            hora2Aircraft = nroHra2;
            min2Aircraft = nroMin2;

            //variables de operacion
            String totalAircraft = "";
            int ophours2 = nroHra2 - nroHra1;
            int opminut2 = nroMin2 - nroMin1;
            int totalMin2 = 0;
            int totalHor2 = 0;

            //logica para informar los ERRORES en el ingreso de los datos manuales
            if (hora1crew >= 24 || hora1Aircraft >= 24 || hora2crew >= 24 || hora2Aircraft >= 24 || min1crew >= 60 || min1Aircraft >= 60 || min2crew >= 60 || min2Aircraft >= 60) {
                Toast.makeText(FullActivity.this, "Error: Hour/Minutes set", Toast.LENGTH_SHORT).show();
                TotalCrew.setText("null");
                TotalAircraft.setText("null");
                TvVfr.setText("null");
                TvIfr.setText("null");
                Nocturno.setText("null");
            } else if (nroHra2 == nroHra1 && nroMin2 < nroMin1) {
                Toast.makeText(FullActivity.this, "Error: Minutes set", Toast.LENGTH_SHORT).show();
                TotalAircraft.setText("null");
            } else {//logica para el calculo de la diferencia hora inicial vs hora final
                if (ophours2 < 0 && opminut2 < 0) {//cuando el dato de Hora y minutos son NEGATIVOS -;-
                    //System.out.println("negativo - negativo");
                    totalHor2 = (23 - nroHra1) + nroHra2;
                    totalMin2 = (60 - nroMin1) + nroMin2;

                    totalAerHoraBD = totalHor2;//datos para BD calcular totalAircraft
                    totalAerMinBD = totalMin2; //datos para BD calcular totalAircraft


                    if (totalHor2 < 10 && totalMin2 < 10) {
                        totalAircraft = "0" + String.valueOf(totalHor2) + ":0" + String.valueOf(totalMin2);
                        TotalAircraft.setText(totalAircraft);
                    } else if (totalHor2 < 10) {
                        totalAircraft = "0" + String.valueOf(totalHor2) + ":" + String.valueOf(totalMin2);
                        TotalAircraft.setText(totalAircraft);
                    } else if (totalMin2 < 10) {
                        totalAircraft = String.valueOf(totalHor2) + ":0" + String.valueOf(totalMin2);
                        TotalAircraft.setText(totalAircraft);
                    } else {
                        totalAircraft = String.valueOf(totalHor2) + ":" + String.valueOf(totalMin2);
                        TotalAircraft.setText(totalAircraft);
                    }

                } else if (ophours2 < 0 && opminut2 >= 0) { //condicion cuando el resultado hora es NEGATIVO y resultado minutos es POSITIVO O IGUAL A CERO -;+
                    //System.out.println("negativo - positivo = 0");
                    totalHor2 = (24 - nroHra1) + nroHra2;

                    totalAerHoraBD = totalHor2;//datos para BD calcular totalAircraft
                    totalAerMinBD = opminut2; //datos para BD calcular totalAircraft

                    if (totalHor2 < 10 && opminut2 < 10) {
                        totalAircraft = "0" + String.valueOf(totalHor2) + ":0" + String.valueOf(opminut2);
                        TotalAircraft.setText(totalAircraft);
                    } else if (totalHor2 < 10) {
                        totalAircraft = "0" + String.valueOf(totalHor2) + ":" + String.valueOf(opminut2);
                        TotalAircraft.setText(totalAircraft);
                    } else if (opminut2 < 10) {
                        totalAircraft = String.valueOf(totalHor2) + ":0" + String.valueOf(opminut2);
                        TotalAircraft.setText(totalAircraft);
                    } else {
                        totalAircraft = String.valueOf(totalHor2) + ":" + String.valueOf(opminut2);
                        TotalAircraft.setText(totalAircraft);
                    }

                } else if (ophours2 >= 0 && opminut2 < 0) { //condicion cuando el resultado horas es POSITIVO IGUAL A CERO  y el resultado minutos es NEGATIVO +;-
                    //System.out.println("positivo = 0 - negativo");
                    ophours2 = ophours2 - 1;
                    totalMin2 = (60 - nroMin1) + nroMin2;

                    totalAerHoraBD = ophours2;//datos para BD calcular totalAircraft
                    totalAerMinBD = totalMin2; //datos para BD calcular totalAircraft

                    if (ophours2 < 10 && totalMin2 > 10) {
                        totalAircraft = "0" + String.valueOf(ophours2) + ":" + String.valueOf(totalMin2);
                        TotalAircraft.setText(totalAircraft);
                    } else if (ophours2 < 10 && totalMin2 < 10) {
                        totalAircraft = "0" + String.valueOf(ophours2) + ":0" + String.valueOf(totalMin2);
                        TotalAircraft.setText(totalAircraft);
                    } else {
                        totalMin2 = (60 - nroMin1) + nroMin2;
                        totalAircraft = String.valueOf(ophours2) + ":" + String.valueOf(totalMin2);
                        TotalAircraft.setText(totalAircraft);
                    }
                } else { //condiciones de resultado horas y minutos mayores a uno y POSITIVOS +;+
                    //System.out.println("positivo - positivo");

                    totalAerHoraBD = ophours2;//datos para BD calcular totalAircraft
                    totalAerMinBD = opminut2; //datos para BD calcular totalAircraft

                    if (ophours2 < 10 && opminut2 < 10) {
                        totalAircraft = "0" + String.valueOf(ophours2) + ":0" + String.valueOf(opminut2);
                        TotalAircraft.setText(totalAircraft);
                    } else if (ophours2 < 10 && opminut2 > 10) {
                        totalAircraft = "0" + String.valueOf(ophours2) + ":" + String.valueOf(opminut2);
                        TotalAircraft.setText(totalAircraft);
                    } else if (ophours2 > 10 && opminut2 < 10) {
                        totalAircraft = String.valueOf(ophours2) + ":0" + String.valueOf(opminut2);
                        TotalAircraft.setText(totalAircraft);
                    } else if (ophours2 == 10 && opminut2 < 10) {
                        totalAircraft = String.valueOf(ophours2) + ":0" + String.valueOf(opminut2);
                        TotalAircraft.setText(totalAircraft);
                    } else if (ophours2 < 10 && opminut2 == 10) {
                        totalAircraft = "0" + String.valueOf(ophours2) + ":" + String.valueOf(opminut2);
                        TotalAircraft.setText(totalAircraft);
                    } else {
                        totalAircraft = String.valueOf(ophours2) + ":" + String.valueOf(opminut2);
                        TotalAircraft.setText(totalAircraft);
                    }
                }
            }
        } else { //si no hay datos en los edit text presenta este error
            Toast.makeText(FullActivity.this, "Error: Missing Data", Toast.LENGTH_SHORT).show();
            TotalCrew.setText("null");
            TotalAircraft.setText("null");
            TvVfr.setText("null");
            TvIfr.setText("null");
            Nocturno.setText("null");
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
    //**************************************************************************************************************
    //metodos para hacer visibles los botones de ADD Manualmente los datos de Tripulacion y Aeronave

    public void setvisible1(View view) {
        Button btn1 = (Button) findViewById(R.id.btSet);
        btn1.setVisibility(View.VISIBLE);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //**********************************************************DATOS****************************************************
    //calculo de los datos VFR IFR y Nocturno
    private void calcOpera() {


        String totalNoc = "";
        String totalVfr = "";
        String totalIfr = "";
        int noc = 0;
        int vfr = 0;
        int ifr = 0;

        //variables para operaciones calculo nocturno
        int nocHra1 = 0;
        int nocMin1 = 0;
        int nocHra2 = 0;
        int nocMin2 = 0;
        int difhours = 0;
        int difminut = 0;
        int totalMin1 = 0;
        int totalHor1 = 0;
        int totalMin2 = 0;
        int totalHor2 = 0;


        //condicion para calcular NOCTURNO cuando afecta solo una parte de la hora ******H2C esta despues de las 18:00 y el resto es diurno*******************************************************************
        if (((hora1crew >= 6) && (hora1crew < 18)) && ((hora1Aircraft >= 6) && (hora1Aircraft < 18)) && ((hora2Aircraft >= 6) && (hora2Aircraft < 18)) && (hora2crew >= 18)) {
            //System.out.println("DATA1 LH");
            //calculo del dato nocturno//////////////////////////////////////
            //variables publicas
            nocHra1 = 18;
            nocMin1 = 0;
            nocHra2 = hora2crew;
            nocMin2 = min2crew;
            //variables de operacion
            difhours = nocHra2 - nocHra1;
            difminut = nocMin2 - nocMin1;
            totalMin2 = 0;
            totalHor2 = 0;
            //logica para el calculo de la diferencia hora inicial vs hora final
            if (difhours < 0 && difminut < 0) {//cuando el dato de Hora y minutos son NEGATIVOS -;-
                //System.out.println("negativo - negativo");
                totalHor2 = (23 - nocHra1) + nocHra2;
                totalMin2 = (60 - nocMin1) + nocMin2;
                if (totalHor2 < 10 && totalMin2 < 10) {
                    totalNoc = "0" + String.valueOf(totalHor2) + ":0" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);

                } else if (totalHor2 < 10) {
                    totalNoc = "0" + String.valueOf(totalHor2) + ":" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                } else if (totalMin2 < 10) {
                    totalNoc = String.valueOf(totalHor2) + ":0" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                } else {
                    totalNoc = String.valueOf(totalHor2) + ":" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                }

            } else if (difhours < 0 && difminut >= 0) { //condicion cuando el resultado hora es NEGATIVO y resultado minutos es POSITIVO O IGUAL A CERO -;+
                //System.out.println("negativo - positivo = 0");
                totalHor2 = (24 - nocHra1) + nocHra2;
                if (totalHor2 < 10 && difminut < 10) {
                    totalNoc = "0" + String.valueOf(totalHor2) + ":0" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else if (totalHor2 < 10) {
                    totalNoc = "0" + String.valueOf(totalHor2) + ":" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else if (difminut < 10) {
                    totalNoc = String.valueOf(totalHor2) + ":0" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else {
                    totalNoc = String.valueOf(totalHor2) + ":" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                }

            } else if (difhours >= 0 && difminut < 0) { //condicion cuando el resultado horas es POSITIVO IGUAL A CERO  y el resultado minutos es NEGATIVO +;-
                //System.out.println("positivo = 0 - negativo");
                difhours = difhours - 1;
                totalMin2 = (60 - nocMin1) + nocMin2;
                if (difhours < 10 && totalMin2 > 10) {
                    totalNoc = "0" + String.valueOf(difhours) + ":" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                } else if (difhours < 10 && totalMin2 < 10) {
                    totalNoc = "0" + String.valueOf(difhours) + ":0" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                } else {
                    totalMin2 = (60 - nocMin1) + nocMin2;
                    totalNoc = String.valueOf(difhours) + ":" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                }
            } else { //condiciones de resultado horas y minutos mayores a uno y POSITIVOS +;+
                //System.out.println("positivo - positivo");
                if (difhours < 10 && difminut < 10) {
                    totalNoc = "0" + String.valueOf(difhours) + ":0" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else if (difhours < 10 && difminut > 10) {
                    totalNoc = "0" + String.valueOf(difhours) + ":" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else if (difhours > 10 && difminut < 10) {
                    totalNoc = String.valueOf(difhours) + ":0" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else if (difhours == 10 && difminut < 10) {
                    totalNoc = String.valueOf(difhours) + ":0" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else if (difhours < 10 && difminut == 10) {
                    totalNoc = "0" + String.valueOf(difhours) + ":" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else {
                    totalNoc = String.valueOf(difhours) + ":" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                }
            }


            //calculo ifr //////////////////////////////////////////////////////////////
            totalIfr = TotalAircraft.getText().toString();
            TvIfr.setText(totalIfr);

            //calculo vfr//////////////////////////////////////////////////////////////
            //condicion cuando los valores de vfr son en diferentes horas HORAS EXTENDIDAS

            //Esta parte del codigo halla la diferencia entre H1A - 06:00
            nocHra1 = hora1crew;
            nocMin1 = min1crew;
            nocHra2 = hora1Aircraft;
            nocMin2 = min1Aircraft;
            //variables de operacion
            int difhours1 = nocHra2 - nocHra1;
            int difminut1 = nocMin2 - nocMin1;
            totalMin1 = 0;
            totalHor1 = 0;

            vfr = 0;
            int vfr1Horas = 0;
            int vfr1Min = 0;
            int vfr2Horas = 0;
            int vfr2Min = 0;

            //logica para el calculo de la diferencia hora inicial vs hora final
            if (difhours1 < 0 && difminut1 < 0) {//cuando el dato de Hora y minutos son NEGATIVOS -;-
                //System.out.println("vfr 1 negativo - negativo");
                totalHor1 = (23 - nocHra1) + nocHra2;
                totalMin1 = (60 - nocMin1) + nocMin2;
                vfr1Horas = totalHor1;
                vfr1Min = totalMin1;

            } else if (difhours1 < 0 && difminut1 >= 0) { //condicion cuando el resultado hora es NEGATIVO y resultado minutos es POSITIVO O IGUAL A CERO -;+
                //System.out.println("vfr 1 negativo - positivo = 0");
                totalHor1 = (24 - nocHra1) + nocHra2;
                vfr1Horas = totalHor1;
                vfr1Min = difminut1;


            } else if (difhours1 >= 0 && difminut1 < 0) { //condicion cuando el resultado horas es POSITIVO IGUAL A CERO  y el resultado minutos es NEGATIVO +;-
                //System.out.println("vfr 1 positivo = 0 - negativo");
                difhours1 = difhours1 - 1;
                totalMin1 = (60 - nocMin1) + nocMin2;
                vfr1Horas = difhours1;
                vfr1Min = totalMin1;

            } else { //condiciones de resultado horas y minutos mayores a uno y POSITIVOS +;+
                //System.out.println("vfr 1 positivo - positivo");
                vfr1Horas = difhours1;
                vfr1Min = difminut1;

            }

            //Esta parte del codigo halla la diferencia entre H2C - H2A para el calculo de vfr

            nocHra1 = hora2Aircraft;
            nocMin1 = min2Aircraft;
            nocHra2 = 18;
            nocMin2 = 0;
            //variables de operacion
            int difhours2 = nocHra2 - nocHra1;
            int difminut2 = nocMin2 - nocMin1;
            totalMin2 = 0;
            totalHor2 = 0;
            //logica para el calculo de la diferencia hora inicial vs hora final
            if (difhours2 < 0 && difminut2 < 0) {//cuando el dato de Hora y minutos son NEGATIVOS -;-
                //System.out.println("vfr 2 negativo - negativo");
                totalHor2 = (23 - nocHra1) + nocHra2;
                totalMin2 = (60 - nocMin1) + nocMin2;
                vfr2Horas = totalHor2;
                vfr2Min = totalMin2;


            } else if (difhours2 < 0 && difminut2 >= 0) { //condicion cuando el resultado hora es NEGATIVO y resultado minutos es POSITIVO O IGUAL A CERO -;+
                //System.out.println("vfr 2 negativo - positivo = 0");
                totalHor2 = (24 - nocHra1) + nocHra2;
                vfr2Horas = totalHor2;
                vfr2Min = difminut2;


            } else if (difhours2 >= 0 && difminut2 < 0) { //condicion cuando el resultado horas es POSITIVO IGUAL A CERO  y el resultado minutos es NEGATIVO +;-
                //System.out.println("vfr 2 positivo = 0 - negativo");
                difhours2 = difhours2 - 1;
                totalMin2 = (60 - nocMin1) + nocMin2;
                vfr2Horas = difhours2;
                vfr2Min = totalMin2;

            } else { //condiciones de resultado horas y minutos mayores a uno y POSITIVOS +;+
                //System.out.println("vfr 2 positivo - positivo");
                vfr2Horas = difhours2;
                vfr2Min = difminut2;

            }

            //Esta parte del codigo suma los datos de las diferencias entre (H1A - H1C)+(H2C-H2A) Y MUESTRA EL RESULTADO
            int vfrHoras = vfr1Horas + vfr2Horas;
            int vfrMin = vfr1Min + vfr2Min;
            if (vfrHoras < 10 && vfrMin < 10) {
                totalVfr = "0" + String.valueOf(vfrHoras) + ":0" + String.valueOf(vfrMin);
                TvVfr.setText(totalVfr);
            } else if (vfrHoras < 10 && vfrMin > 10) {
                totalVfr = "0" + String.valueOf(vfrHoras) + ":" + String.valueOf(vfrMin);
                TvVfr.setText(totalVfr);
            } else if (vfrHoras > 10 && vfrMin < 10) {
                totalVfr = String.valueOf(vfrHoras) + ":0" + String.valueOf(vfrMin);
                TvVfr.setText(totalVfr);
            } else if (vfrHoras == 10 && vfrMin < 10) {
                totalVfr = String.valueOf(vfrHoras) + ":0" + String.valueOf(vfrMin);
                TvVfr.setText(totalVfr);
            } else if (vfrHoras < 10 && vfrMin == 10) {
                totalVfr = "0" + String.valueOf(vfrHoras) + ":" + String.valueOf(vfrMin);
                TvVfr.setText(totalVfr);
            } else {
                totalVfr = String.valueOf(vfrHoras) + ":" + String.valueOf(vfrMin);
                TvVfr.setText(totalVfr);
            }


        }


        //condicion para calcular NOCTURNO cuando afecta solo una parte de la hora ******H2A y H2C estan despues de las 18:00*******************************************************************
        else if (((hora1crew >= 6) && (hora1crew < 18)) && ((hora1Aircraft >= 6) && (hora1Aircraft < 18)) && ((hora2crew >= 18) && (hora2Aircraft >= 18))) {
            //System.out.println("DATA2 LH");
            //calculo Nocturno //////////////////////////////////////////////////////////////
            //variables publicas
            nocHra1 = 18;
            nocMin1 = 0;
            nocHra2 = hora2crew;
            nocMin2 = min2crew;
            //variables de operacion
            difhours = nocHra2 - nocHra1;
            difminut = nocMin2 - nocMin1;
            totalMin2 = 0;
            totalHor2 = 0;
            //logica para el calculo de la diferencia hora inicial vs hora final
            if (difhours < 0 && difminut < 0) {//cuando el dato de Hora y minutos son NEGATIVOS -;-
                //System.out.println("negativo - negativo");
                totalHor2 = (23 - nocHra1) + nocHra2;
                totalMin2 = (60 - nocMin1) + nocMin2;
                if (totalHor2 < 10 && totalMin2 < 10) {
                    totalNoc = "0" + String.valueOf(totalHor2) + ":0" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                } else if (totalHor2 < 10) {
                    totalNoc = "0" + String.valueOf(totalHor2) + ":" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                } else if (totalMin2 < 10) {
                    totalNoc = String.valueOf(totalHor2) + ":0" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                } else {
                    totalNoc = String.valueOf(totalHor2) + ":" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                }

            } else if (difhours < 0 && difminut >= 0) { //condicion cuando el resultado hora es NEGATIVO y resultado minutos es POSITIVO O IGUAL A CERO -;+
                //System.out.println("negativo - positivo = 0");
                totalHor2 = (24 - nocHra1) + nocHra2;
                if (totalHor2 < 10 && difminut < 10) {
                    totalNoc = "0" + String.valueOf(totalHor2) + ":0" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else if (totalHor2 < 10) {
                    totalNoc = "0" + String.valueOf(totalHor2) + ":" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else if (difminut < 10) {
                    totalNoc = String.valueOf(totalHor2) + ":0" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else {
                    totalNoc = String.valueOf(totalHor2) + ":" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                }

            } else if (difhours >= 0 && difminut < 0) { //condicion cuando el resultado horas es POSITIVO IGUAL A CERO  y el resultado minutos es NEGATIVO +;-
                //System.out.println("positivo = 0 - negativo");
                difhours = difhours - 1;
                totalMin2 = (60 - nocMin1) + nocMin2;
                if (difhours < 10 && totalMin2 > 10) {
                    totalNoc = "0" + String.valueOf(difhours) + ":" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                } else if (difhours < 10 && totalMin2 < 10) {
                    totalNoc = "0" + String.valueOf(difhours) + ":0" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                } else {
                    totalMin2 = (60 - nocMin1) + nocMin2;
                    totalNoc = String.valueOf(difhours) + ":" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                }
            } else { //condiciones de resultado horas y minutos mayores a uno y POSITIVOS +;+
                //System.out.println("positivo - positivo");
                if (difhours < 10 && difminut < 10) {
                    totalNoc = "0" + String.valueOf(difhours) + ":0" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else if (difhours < 10 && difminut > 10) {
                    totalNoc = "0" + String.valueOf(difhours) + ":" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else if (difhours > 10 && difminut < 10) {
                    totalNoc = String.valueOf(difhours) + ":0" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else if (difhours == 10 && difminut < 10) {
                    totalNoc = String.valueOf(difhours) + ":0" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else if (difhours < 10 && difminut == 10) {
                    totalNoc = "0" + String.valueOf(difhours) + ":" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else {
                    totalNoc = String.valueOf(difhours) + ":" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                }
            }


            //calculo vfr //////////////////////////////////////////////////////////////
            //logica para el calculo de la diferencia hora inicial vs hora final
            //variables publicas
            int vfrHra1 = hora1crew;
            int vfrMin1 = min1crew;
            int vfrHra2 = hora1Aircraft;
            int vfrMin2 = min1Aircraft;
            //variables de operacion
            int ophours = vfrHra2 - vfrHra1;
            int opminut = vfrMin2 - vfrMin1;
            int totalMin = 0;
            int totalHor = 0;

            if (ophours < 0 && opminut < 0) {//cuando el dato de Hora y minutos son NEGATIVOS -;-
                //System.out.println("negativo - negativo");
                totalHor = (23 - vfrHra1) + vfrHra2;
                totalMin = (60 - vfrMin1) + vfrMin2;
                if (totalHor < 10 && totalMin < 10) {
                    totalVfr = "0" + String.valueOf(totalHor) + ":0" + String.valueOf(totalMin);
                    TvVfr.setText(totalVfr);
                } else if (totalHor < 10) {
                    totalVfr = "0" + String.valueOf(totalHor) + ":" + String.valueOf(totalMin);
                    TvVfr.setText(totalVfr);
                } else if (totalMin < 10) {
                    totalVfr = String.valueOf(totalHor) + ":0" + String.valueOf(totalMin);
                    TvVfr.setText(totalVfr);
                } else {
                    totalVfr = String.valueOf(totalHor) + ":" + String.valueOf(totalMin);
                    TvVfr.setText(totalVfr);
                }

            } else if (ophours < 0 && opminut >= 0) { //condicion cuando el resultado hora es NEGATIVO y resultado minutos es POSITIVO O IGUAL A CERO -;+
                //System.out.println("negativo - positivo = 0");
                totalHor = (24 - vfrHra1) + vfrHra2;
                if (totalHor < 10 && opminut < 10) {
                    totalVfr = "0" + String.valueOf(totalHor) + ":0" + String.valueOf(opminut);
                    TvVfr.setText(totalVfr);
                } else if (totalHor < 10) {
                    totalVfr = "0" + String.valueOf(totalHor) + ":" + String.valueOf(opminut);
                    TvVfr.setText(totalVfr);
                } else if (opminut < 10) {
                    totalVfr = String.valueOf(totalHor) + ":0" + String.valueOf(opminut);
                    TvVfr.setText(totalVfr);
                } else {
                    totalVfr = String.valueOf(totalHor) + ":" + String.valueOf(opminut);
                    TvVfr.setText(totalVfr);
                }


            } else if (ophours >= 0 && opminut < 0) { //condicion cuando el resultado horas es POSITIVO IGUAL A CERO  y el resultado minutos es NEGATIVO +;-
                //System.out.println("positivo = 0 - negativo");
                ophours = ophours - 1;
                totalMin = (60 - vfrMin1) + vfrMin2;
                if (ophours < 10 && totalMin > 10) {
                    totalVfr = "0" + String.valueOf(ophours) + ":" + String.valueOf(totalMin);
                    TvVfr.setText(totalVfr);
                } else if (ophours < 10 && totalMin < 10) {
                    totalVfr = "0" + String.valueOf(ophours) + ":0" + String.valueOf(totalMin);
                    TvVfr.setText(totalVfr);
                } else {
                    totalMin = (60 - vfrMin1) + vfrMin2;
                    totalVfr = String.valueOf(ophours) + ":" + String.valueOf(totalMin);
                    TvVfr.setText(totalVfr);
                }
            } else { //condiciones de resultado horas y minutos mayores a uno y POSITIVOS +;+
                //System.out.println("positivo - positivo");
                if (ophours < 10 && opminut < 10) {
                    totalVfr = "0" + String.valueOf(ophours) + ":0" + String.valueOf(opminut);
                    TvVfr.setText(totalVfr);
                } else if (ophours < 10 && opminut > 10) {
                    totalVfr = "0" + String.valueOf(ophours) + ":" + String.valueOf(opminut);
                    TvVfr.setText(totalVfr);
                } else if (ophours > 10 && opminut < 10) {
                    totalVfr = String.valueOf(ophours) + ":0" + String.valueOf(opminut);
                    TvVfr.setText(totalVfr);
                } else if (ophours == 10 && opminut < 10) {
                    totalVfr = String.valueOf(ophours) + ":0" + String.valueOf(opminut);
                    TvVfr.setText(totalVfr);
                } else if (ophours < 10 && opminut == 10) {
                    totalVfr = "0" + String.valueOf(ophours) + ":" + String.valueOf(opminut);
                    TvVfr.setText(totalVfr);
                } else {
                    //System.out.println("crudos");
                    totalVfr = String.valueOf(ophours) + ":" + String.valueOf(opminut);
                    TvVfr.setText(totalVfr);
                }
            }


            //calculo ifr /////////////////////////////////////////////////////////////////
            int horaIfr = 0;
            int minIfr = 0;
            if (hora1Aircraft < 17) {//condicion para el calculo de IFR
                horaIfr = 17 - hora1Aircraft;
                minIfr = 60 - min1Aircraft;
                if (horaIfr < 10 && minIfr < 10) {
                    totalIfr = "0" + String.valueOf(horaIfr) + ":0" + String.valueOf(minIfr);
                    TvIfr.setText(totalIfr);
                } else if (horaIfr < 10 && minIfr > 10) {
                    totalIfr = "0" + String.valueOf(horaIfr) + ":" + String.valueOf(minIfr);
                    TvIfr.setText(totalIfr);
                } else if (horaIfr > 10 && minIfr < 10) {
                    totalIfr = String.valueOf(horaIfr) + ":0" + String.valueOf(minIfr);
                    TvIfr.setText(totalIfr);
                } else if (horaIfr == 10 && minIfr < 10) {
                    totalIfr = String.valueOf(horaIfr) + ":0" + String.valueOf(minIfr);
                    TvIfr.setText(totalIfr);
                } else if (horaIfr < 10 && minIfr == 10) {
                    totalIfr = "0" + String.valueOf(horaIfr) + ":" + String.valueOf(minIfr);
                    TvIfr.setText(totalIfr);
                } else {
                    //System.out.println("crudos");
                    totalIfr = String.valueOf(horaIfr) + ":" + String.valueOf(minIfr);
                    TvIfr.setText(totalIfr);
                }

            } else {
                minIfr = 60 - min1Aircraft;
                if (minIfr < 10) {
                    totalIfr = "00:0" + String.valueOf(minIfr);
                    TvIfr.setText(totalIfr);
                } else {
                    totalIfr = "00:" + String.valueOf(minIfr);
                    TvIfr.setText(totalIfr);
                }
            }


        }
        //condicion para calcular nocturno cuando afecta solo una parte de la hora ******H2C y H2A y H1A estan despues de las 18:00***************************************************************
        else if (((hora1crew >= 6) && (hora1crew < 18)) && (hora2crew >= 18) && (hora1Aircraft >= 18) && (hora2Aircraft >= 18)) {
            //System.out.println("DATA3 LH");
            //calculo del dato nocturno//////////////////////////////////////////////////////////////
            //variables publicas
            nocHra1 = 18;
            nocMin1 = 0;
            nocHra2 = hora2crew;
            nocMin2 = min2crew;
            //variables de operacion
            difhours = nocHra2 - nocHra1;
            difminut = nocMin2 - nocMin1;
            totalMin2 = 0;
            totalHor2 = 0;
            //logica para el calculo de la diferencia hora inicial vs hora final
            if (difhours < 0 && difminut < 0) {//cuando el dato de Hora y minutos son NEGATIVOS -;-
                //System.out.println("negativo - negativo");
                totalHor2 = (23 - nocHra1) + nocHra2;
                totalMin2 = (60 - nocMin1) + nocMin2;
                if (totalHor2 < 10 && totalMin2 < 10) {
                    totalNoc = "0" + String.valueOf(totalHor2) + ":0" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                } else if (totalHor2 < 10) {
                    totalNoc = "0" + String.valueOf(totalHor2) + ":" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                } else if (totalMin2 < 10) {
                    totalNoc = String.valueOf(totalHor2) + ":0" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                } else {
                    totalNoc = String.valueOf(totalHor2) + ":" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                }

            } else if (difhours < 0 && difminut >= 0) { //condicion cuando el resultado hora es NEGATIVO y resultado minutos es POSITIVO O IGUAL A CERO -;+
                //System.out.println("negativo - positivo = 0");
                totalHor2 = (24 - nocHra1) + nocHra2;
                if (totalHor2 < 10 && difminut < 10) {
                    totalNoc = "0" + String.valueOf(totalHor2) + ":0" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else if (totalHor2 < 10) {
                    totalNoc = "0" + String.valueOf(totalHor2) + ":" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else if (difminut < 10) {
                    totalNoc = String.valueOf(totalHor2) + ":0" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else {
                    totalNoc = String.valueOf(totalHor2) + ":" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                }

            } else if (difhours >= 0 && difminut < 0) { //condicion cuando el resultado horas es POSITIVO IGUAL A CERO  y el resultado minutos es NEGATIVO +;-
                //System.out.println("positivo = 0 - negativo");
                difhours = difhours - 1;
                totalMin2 = (60 - nocMin1) + nocMin2;
                if (difhours < 10 && totalMin2 > 10) {
                    totalNoc = "0" + String.valueOf(difhours) + ":" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                } else if (difhours < 10 && totalMin2 < 10) {
                    totalNoc = "0" + String.valueOf(difhours) + ":0" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                } else {
                    totalMin2 = (60 - nocMin1) + nocMin2;
                    totalNoc = String.valueOf(difhours) + ":" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                }
            } else { //condiciones de resultado horas y minutos mayores a uno y POSITIVOS +;+
                //System.out.println("positivo - positivo");
                if (difhours < 10 && difminut < 10) {
                    totalNoc = "0" + String.valueOf(difhours) + ":0" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else if (difhours < 10 && difminut > 10) {
                    totalNoc = "0" + String.valueOf(difhours) + ":" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else if (difhours > 10 && difminut < 10) {
                    totalNoc = String.valueOf(difhours) + ":0" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else if (difhours == 10 && difminut < 10) {
                    totalNoc = String.valueOf(difhours) + ":0" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else if (difhours < 10 && difminut == 10) {
                    totalNoc = "0" + String.valueOf(difhours) + ":" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else {
                    totalNoc = String.valueOf(difhours) + ":" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                }
            }


            //calculo ifr//////////////////////////////////////////////////////////////
            //no aplica el calculo IFR:
            //porque solo la H1C esta antes de las 18:00
            TvIfr.setText("00:00");


            //calculo vfr//////////////////////////////////////////////////////////////

            nocHra1 = hora1crew;
            nocMin1 = min1crew;
            nocHra2 = 18;
            nocMin2 = 0;
            //variables de operacion
            difhours = nocHra2 - nocHra1;
            difminut = nocMin2 - nocMin1;
            totalMin2 = 0;
            totalHor2 = 0;
            //logica para el calculo de la diferencia hora inicial vs hora final
            if (difhours < 0 && difminut < 0) {//cuando el dato de Hora y minutos son NEGATIVOS -;-
                //System.out.println("negativo - negativo");
                totalHor2 = (23 - nocHra1) + nocHra2;
                totalMin2 = (60 - nocMin1) + nocMin2;
                if (totalHor2 < 10 && totalMin2 < 10) {
                    totalNoc = "0" + String.valueOf(totalHor2) + ":0" + String.valueOf(totalMin2);
                    TvVfr.setText(totalNoc);
                } else if (totalHor2 < 10) {
                    totalNoc = "0" + String.valueOf(totalHor2) + ":" + String.valueOf(totalMin2);
                    TvVfr.setText(totalNoc);
                } else if (totalMin2 < 10) {
                    totalNoc = String.valueOf(totalHor2) + ":0" + String.valueOf(totalMin2);
                    TvVfr.setText(totalNoc);
                } else {
                    totalNoc = String.valueOf(totalHor2) + ":" + String.valueOf(totalMin2);
                    TvVfr.setText(totalNoc);
                }

            } else if (difhours < 0 && difminut >= 0) { //condicion cuando el resultado hora es NEGATIVO y resultado minutos es POSITIVO O IGUAL A CERO -;+
                //System.out.println("negativo - positivo = 0");
                totalHor2 = (24 - nocHra1) + nocHra2;
                if (totalHor2 < 10 && difminut < 10) {
                    totalNoc = "0" + String.valueOf(totalHor2) + ":0" + String.valueOf(difminut);
                    TvVfr.setText(totalNoc);
                } else if (totalHor2 < 10) {
                    totalNoc = "0" + String.valueOf(totalHor2) + ":" + String.valueOf(difminut);
                    TvVfr.setText(totalNoc);
                } else if (difminut < 10) {
                    totalNoc = String.valueOf(totalHor2) + ":0" + String.valueOf(difminut);
                    TvVfr.setText(totalNoc);
                } else {
                    totalNoc = String.valueOf(totalHor2) + ":" + String.valueOf(difminut);
                    TvVfr.setText(totalNoc);
                }

            } else if (difhours >= 0 && difminut < 0) { //condicion cuando el resultado horas es POSITIVO IGUAL A CERO  y el resultado minutos es NEGATIVO +;-
                //System.out.println("positivo = 0 - negativo");
                difhours = difhours - 1;
                totalMin2 = (60 - nocMin1) + nocMin2;
                if (difhours < 10 && totalMin2 > 10) {
                    totalNoc = "0" + String.valueOf(difhours) + ":" + String.valueOf(totalMin2);
                    TvVfr.setText(totalNoc);
                } else if (difhours < 10 && totalMin2 < 10) {
                    totalNoc = "0" + String.valueOf(difhours) + ":0" + String.valueOf(totalMin2);
                    TvVfr.setText(totalNoc);
                } else {
                    totalMin2 = (60 - nocMin1) + nocMin2;
                    totalNoc = "0" + String.valueOf(difhours) + ":" + String.valueOf(totalMin2);
                    TvVfr.setText(totalNoc);

                }
            } else { //condiciones de resultado horas y minutos mayores a uno y POSITIVOS +;+
                //System.out.println("positivo - positivo");
                if (difhours < 10 && difminut < 10) {
                    totalNoc = "0" + String.valueOf(difhours) + ":0" + String.valueOf(difminut);
                    TvVfr.setText(totalNoc);
                } else if (difhours < 10 && difminut > 10) {
                    totalNoc = "0" + String.valueOf(difhours) + ":" + String.valueOf(difminut);
                    TvVfr.setText(totalNoc);
                } else if (difhours > 10 && difminut < 10) {
                    totalNoc = String.valueOf(difhours) + ":0" + String.valueOf(difminut);
                    TvVfr.setText(totalNoc);
                } else if (difhours == 10 && difminut < 10) {
                    totalNoc = String.valueOf(difhours) + ":0" + String.valueOf(difminut);
                    TvVfr.setText(totalNoc);
                } else if (difhours < 10 && difminut == 10) {
                    totalNoc = "0" + String.valueOf(difhours) + ":" + String.valueOf(difminut);
                    TvVfr.setText(totalNoc);
                } else {
                    totalNoc = String.valueOf(difhours) + ":" + String.valueOf(difminut);
                    TvVfr.setText(totalNoc);
                }
            }


        }

        //condicion para calcular NOCTURNO cuando afecta solo una parte de la hora ******H1C  estan antes de las 06:00 el resto menores de 18:00*******************************************************************
        else if ((hora1crew < 6) && ((hora1Aircraft >= 6) && (hora1Aircraft < 18)) && ((hora2crew >= 6) && (hora2crew < 18)) && ((hora2Aircraft >= 6) && (hora2Aircraft < 18))) {
            //System.out.println("DATA1 RH");
            //calculo del dato nocturno//////////////////////////////////////
            //variables publicas
            nocHra1 = hora1crew;
            nocMin1 = min1crew;
            nocHra2 = 6;
            nocMin2 = 0;
            //variables de operacion
            difhours = nocHra2 - nocHra1;
            difminut = nocMin2 - nocMin1;
            totalMin2 = 0;
            totalHor2 = 0;
            //logica para el calculo de la diferencia hora inicial vs hora final
            if (difhours < 0 && difminut < 0) {//cuando el dato de Hora y minutos son NEGATIVOS -;-
                //System.out.println("negativo - negativo");
                totalHor2 = (23 - nocHra1) + nocHra2;
                totalMin2 = (60 - nocMin1) + nocMin2;
                if (totalHor2 < 10 && totalMin2 < 10) {
                    totalNoc = "0" + String.valueOf(totalHor2) + ":0" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                } else if (totalHor2 < 10) {
                    totalNoc = "0" + String.valueOf(totalHor2) + ":" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                } else if (totalMin2 < 10) {
                    totalNoc = String.valueOf(totalHor2) + ":0" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                } else {
                    totalNoc = String.valueOf(totalHor2) + ":" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                }

            } else if (difhours < 0 && difminut >= 0) { //condicion cuando el resultado hora es NEGATIVO y resultado minutos es POSITIVO O IGUAL A CERO -;+
                //System.out.println("negativo - positivo = 0");
                totalHor2 = (24 - nocHra1) + nocHra2;
                if (totalHor2 < 10 && difminut < 10) {
                    totalNoc = "0" + String.valueOf(totalHor2) + ":0" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else if (totalHor2 < 10) {
                    totalNoc = "0" + String.valueOf(totalHor2) + ":" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else if (difminut < 10) {
                    totalNoc = String.valueOf(totalHor2) + ":0" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else {
                    totalNoc = String.valueOf(totalHor2) + ":" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                }

            } else if (difhours >= 0 && difminut < 0) { //condicion cuando el resultado horas es POSITIVO IGUAL A CERO  y el resultado minutos es NEGATIVO +;-
               // System.out.println("positivo = 0 - negativo");
                difhours = difhours - 1;
                totalMin2 = (60 - nocMin1) + nocMin2;
                if (difhours < 10 && totalMin2 > 9) {
                    totalNoc = "0" + String.valueOf(difhours) + ":" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                } else if (difhours < 10 && totalMin2 <= 9) {
                    totalNoc = "0" + String.valueOf(difhours) + ":0" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                } else {
                    totalMin2 = (60 - nocMin1) + nocMin2;
                    totalNoc = String.valueOf(difhours) + ":" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                }
            } else { //condiciones de resultado horas y minutos mayores a uno y POSITIVOS +;+
                //System.out.println("positivo - positivo");
                if (difhours < 10 && difminut < 10) {
                    totalNoc = "0" + String.valueOf(difhours) + ":0" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else if (difhours < 10 && difminut > 10) {
                    totalNoc = "0" + String.valueOf(difhours) + ":" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else if (difhours > 10 && difminut < 10) {
                    totalNoc = String.valueOf(difhours) + ":0" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else if (difhours == 10 && difminut < 10) {
                    totalNoc = String.valueOf(difhours) + ":0" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else if (difhours < 10 && difminut == 10) {
                    totalNoc = "0" + String.valueOf(difhours) + ":" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else {
                    totalNoc = String.valueOf(difhours) + ":" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                }
            }


            //calculo ifr //////////////////////////////////////////////////////////////
            totalIfr = TotalAircraft.getText().toString();
            TvIfr.setText(totalIfr);

            //calculo vfr//////////////////////////////////////////////////////////////
            //condicion cuando los valores de vfr son en diferentes horas HORAS EXTENDIDAS

            //Esta parte del codigo halla la diferencia entre H1A - 06:00
            nocHra1 = 6;
            nocMin1 = 0;
            nocHra2 = hora1Aircraft;
            nocMin2 = min1Aircraft;
            //variables de operacion
            int difhours1 = nocHra2 - nocHra1;
            int difminut1 = nocMin2 - nocMin1;
            totalMin1 = 0;
            totalHor1 = 0;

            vfr = 0;
            int vfr1Horas = 0;
            int vfr1Min = 0;
            int vfr2Horas = 0;
            int vfr2Min = 0;

            //logica para el calculo de la diferencia hora inicial vs hora final
            if (difhours1 < 0 && difminut1 < 0) {//cuando el dato de Hora y minutos son NEGATIVOS -;-
                // System.out.println("vfr 1 negativo - negativo");
                totalHor1 = (23 - nocHra1) + nocHra2;
                totalMin1 = (60 - nocMin1) + nocMin2;
                vfr1Horas = totalHor1;
                vfr1Min = totalMin1;

            } else if (difhours1 < 0 && difminut1 >= 0) { //condicion cuando el resultado hora es NEGATIVO y resultado minutos es POSITIVO O IGUAL A CERO -;+
                // System.out.println("vfr 1 negativo - positivo = 0");
                totalHor1 = (24 - nocHra1) + nocHra2;
                vfr1Horas = totalHor1;
                vfr1Min = difminut1;


            } else if (difhours1 >= 0 && difminut1 < 0) { //condicion cuando el resultado horas es POSITIVO IGUAL A CERO  y el resultado minutos es NEGATIVO +;-
                // System.out.println("vfr 1 positivo = 0 - negativo");
                difhours1 = difhours1 - 1;
                totalMin1 = (60 - nocMin1) + nocMin2;
                vfr1Horas = difhours1;
                vfr1Min = totalMin1;

            } else { //condiciones de resultado horas y minutos mayores a uno y POSITIVOS +;+
                //System.out.println("vfr 1 positivo - positivo");
                vfr1Horas = difhours1;
                vfr1Min = difminut1;

            }

            //Esta parte del codigo halla la diferencia entre H2C - H2A para el calculo de vfr

            nocHra1 = hora2Aircraft;
            nocMin1 = min2Aircraft;
            nocHra2 = hora2crew;
            nocMin2 = min2crew;
            //variables de operacion
            int difhours2 = nocHra2 - nocHra1;
            int difminut2 = nocMin2 - nocMin1;
            totalMin2 = 0;
            totalHor2 = 0;
            //logica para el calculo de la diferencia hora inicial vs hora final
            if (difhours2 < 0 && difminut2 < 0) {//cuando el dato de Hora y minutos son NEGATIVOS -;-
                //System.out.println("vfr 2 negativo - negativo");
                totalHor2 = (23 - nocHra1) + nocHra2;
                totalMin2 = (60 - nocMin1) + nocMin2;
                vfr2Horas = totalHor2;
                vfr2Min = totalMin2;


            } else if (difhours2 < 0 && difminut2 >= 0) { //condicion cuando el resultado hora es NEGATIVO y resultado minutos es POSITIVO O IGUAL A CERO -;+
                //System.out.println("vfr 2 negativo - positivo = 0");
                totalHor2 = (24 - nocHra1) + nocHra2;
                vfr2Horas = totalHor2;
                vfr2Min = difminut2;


            } else if (difhours2 >= 0 && difminut2 < 0) { //condicion cuando el resultado horas es POSITIVO IGUAL A CERO  y el resultado minutos es NEGATIVO +;-
                //System.out.println("vfr 2 positivo = 0 - negativo");
                difhours2 = difhours2 - 1;
                totalMin2 = (60 - nocMin1) + nocMin2;
                vfr2Horas = difhours2;
                vfr2Min = totalMin2;

            } else { //condiciones de resultado horas y minutos mayores a uno y POSITIVOS +;+
                //System.out.println("vfr 2 positivo - positivo");
                vfr2Horas = difhours2;
                vfr2Min = difminut2;

            }

            //Esta parte del codigo suma los datos de las diferencias entre (H1A - H1C)+(H2C-H2A) Y MUESTRA EL RESULTADO
            int vfrHoras = vfr1Horas + vfr2Horas;
            int vfrMin = vfr1Min + vfr2Min;
            if (vfrHoras < 10 && vfrMin < 10) {
                totalVfr = "0" + String.valueOf(vfrHoras) + ":0" + String.valueOf(vfrMin);
                TvVfr.setText(totalVfr);
            } else if (vfrHoras < 10 && vfrMin > 10) {
                totalVfr = "0" + String.valueOf(vfrHoras) + ":" + String.valueOf(vfrMin);
                TvVfr.setText(totalVfr);
            } else if (vfrHoras > 10 && vfrMin < 10) {
                totalVfr = String.valueOf(vfrHoras) + ":0" + String.valueOf(vfrMin);
                TvVfr.setText(totalVfr);
            } else if (vfrHoras == 10 && vfrMin < 10) {
                totalVfr = String.valueOf(vfrHoras) + ":0" + String.valueOf(vfrMin);
                TvVfr.setText(totalVfr);
            } else if (vfrHoras < 10 && vfrMin == 10) {
                totalVfr = "0" + String.valueOf(vfrHoras) + ":" + String.valueOf(vfrMin);
                TvVfr.setText(totalVfr);
            } else {
                totalVfr = String.valueOf(vfrHoras) + ":" + String.valueOf(vfrMin);
                TvVfr.setText(totalVfr);
            }


        } //condicion para calcular NOCTURNO cuando afecta solo una parte de la hora ******H1C y H1A estan antes de las 06:00*******************************************************************
        else if (((hora1crew < 6) && (hora1Aircraft < 6)) && ((hora2crew >= 6) && (hora2crew < 18)) && ((hora2Aircraft >= 6) && (hora2Aircraft < 18))) {
            //System.out.println("DATA2 RH");
            //calculo Nocturno //////////////////////////////////////////////////////////////
            //variables publicas
            nocHra1 = hora1crew;
            nocMin1 = min1crew;
            nocHra2 = 6;
            nocMin2 = 0;
            //variables de operacion
            difhours = nocHra2 - nocHra1;
            difminut = nocMin2 - nocMin1;
            totalMin2 = 0;
            totalHor2 = 0;
            //logica para el calculo de la diferencia hora inicial vs hora final
            if (difhours < 0 && difminut < 0) {//cuando el dato de Hora y minutos son NEGATIVOS -;-
                //System.out.println("negativo - negativo");
                totalHor2 = (23 - nocHra1) + nocHra2;
                totalMin2 = (60 - nocMin1) + nocMin2;
                if (totalHor2 < 10 && totalMin2 < 10) {
                    totalNoc = "0" + String.valueOf(totalHor2) + ":0" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                } else if (totalHor2 < 10) {
                    totalNoc = "0" + String.valueOf(totalHor2) + ":" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                } else if (totalMin2 < 10) {
                    totalNoc = String.valueOf(totalHor2) + ":0" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                } else {
                    totalNoc = String.valueOf(totalHor2) + ":" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                }

            } else if (difhours < 0 && difminut >= 0) { //condicion cuando el resultado hora es NEGATIVO y resultado minutos es POSITIVO O IGUAL A CERO -;+
                //System.out.println("negativo - positivo = 0");
                totalHor2 = (24 - nocHra1) + nocHra2;
                if (totalHor2 < 10 && difminut < 10) {
                    totalNoc = "0" + String.valueOf(totalHor2) + ":0" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else if (totalHor2 < 10) {
                    totalNoc = "0" + String.valueOf(totalHor2) + ":" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else if (difminut < 10) {
                    totalNoc = String.valueOf(totalHor2) + ":0" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else {
                    totalNoc = String.valueOf(totalHor2) + ":" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                }

            } else if (difhours >= 0 && difminut < 0) { //condicion cuando el resultado horas es POSITIVO IGUAL A CERO  y el resultado minutos es NEGATIVO +;-
                //System.out.println("positivo = 0 - negativo");
                difhours = difhours - 1;
                totalMin2 = (60 - nocMin1) + nocMin2;
                if (difhours < 10 && totalMin2 > 10) {
                    totalNoc = "0" + String.valueOf(difhours) + ":" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                } else if (difhours < 10 && totalMin2 < 10) {
                    totalNoc = "0" + String.valueOf(difhours) + ":0" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                } else {
                    totalMin2 = (60 - nocMin1) + nocMin2;
                    totalNoc = String.valueOf(difhours) + ":" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                }
            } else { //condiciones de resultado horas y minutos mayores a uno y POSITIVOS +;+
                //System.out.println("positivo - positivo");
                if (difhours < 10 && difminut < 10) {
                    totalNoc = "0" + String.valueOf(difhours) + ":0" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else if (difhours < 10 && difminut > 10) {
                    totalNoc = "0" + String.valueOf(difhours) + ":" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else if (difhours > 10 && difminut < 10) {
                    totalNoc = String.valueOf(difhours) + ":0" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else if (difhours == 10 && difminut < 10) {
                    totalNoc = String.valueOf(difhours) + ":0" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else if (difhours < 10 && difminut == 10) {
                    totalNoc = "0" + String.valueOf(difhours) + ":" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else {
                    totalNoc = String.valueOf(difhours) + ":" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                }
            }


            //calculo vfr //////////////////////////////////////////////////////////////
            //logica para el calculo de la diferencia hora inicial vs hora final
            //variables publicas
            int vfrHra1 = hora2Aircraft;
            int vfrMin1 = min2Aircraft;
            int vfrHra2 = hora2crew;
            int vfrMin2 = min2crew;
            //variables de operacion
            int ophours = vfrHra2 - vfrHra1;
            int opminut = vfrMin2 - vfrMin1;
            int totalMin = 0;
            int totalHor = 0;

            if (ophours < 0 && opminut < 0) {//cuando el dato de Hora y minutos son NEGATIVOS -;-
                //System.out.println("negativo - negativo");
                totalHor = (23 - vfrHra1) + vfrHra2;
                totalMin = (60 - vfrMin1) + vfrMin2;
                if (totalHor < 10 && totalMin < 10) {
                    totalVfr = "0" + String.valueOf(totalHor) + ":0" + String.valueOf(totalMin);
                    TvVfr.setText(totalVfr);
                } else if (totalHor < 10) {
                    totalVfr = "0" + String.valueOf(totalHor) + ":" + String.valueOf(totalMin);
                    TvVfr.setText(totalVfr);
                } else if (totalMin < 10) {
                    totalVfr = String.valueOf(totalHor) + ":0" + String.valueOf(totalMin);
                    TvVfr.setText(totalVfr);
                } else {
                    totalVfr = String.valueOf(totalHor) + ":" + String.valueOf(totalMin);
                    TvVfr.setText(totalVfr);
                }

            } else if (ophours < 0 && opminut >= 0) { //condicion cuando el resultado hora es NEGATIVO y resultado minutos es POSITIVO O IGUAL A CERO -;+
                // System.out.println("negativo - positivo = 0");
                totalHor = (24 - vfrHra1) + vfrHra2;
                if (totalHor < 10 && opminut < 10) {
                    totalVfr = "0" + String.valueOf(totalHor) + ":0" + String.valueOf(opminut);
                    TvVfr.setText(totalVfr);
                } else if (totalHor < 10) {
                    totalVfr = "0" + String.valueOf(totalHor) + ":" + String.valueOf(opminut);
                    TvVfr.setText(totalVfr);
                } else if (opminut < 10) {
                    totalVfr = String.valueOf(totalHor) + ":0" + String.valueOf(opminut);
                    TvVfr.setText(totalVfr);
                } else {
                    totalVfr = String.valueOf(totalHor) + ":" + String.valueOf(opminut);
                    TvVfr.setText(totalVfr);
                }


            } else if (ophours >= 0 && opminut < 0) { //condicion cuando el resultado horas es POSITIVO IGUAL A CERO  y el resultado minutos es NEGATIVO +;-
                //System.out.println("positivo = 0 - negativo");
                ophours = ophours - 1;
                totalMin = (60 - vfrMin1) + vfrMin2;
                if (ophours < 10 && totalMin > 10) {
                    totalVfr = "0" + String.valueOf(ophours) + ":" + String.valueOf(totalMin);
                    TvVfr.setText(totalVfr);
                } else if (ophours < 10 && totalMin < 10) {
                    totalVfr = "0" + String.valueOf(ophours) + ":0" + String.valueOf(totalMin);
                    TvVfr.setText(totalVfr);
                } else if (ophours < 10 && totalMin == 10) {
                    totalVfr = "0" + String.valueOf(ophours) + ":" + String.valueOf(totalMin);
                    TvVfr.setText(totalVfr);
                } else {
                    totalMin = (60 - vfrMin1) + vfrMin2;
                    totalVfr = String.valueOf(ophours) + ":" + String.valueOf(totalMin);
                    TvVfr.setText(totalVfr);
                }
            } else { //condiciones de resultado horas y minutos mayores a uno y POSITIVOS +;+
                //System.out.println("positivo - positivo");
                if (ophours < 10 && opminut < 10) {
                    totalVfr = "0" + String.valueOf(ophours) + ":0" + String.valueOf(opminut);
                    TvVfr.setText(totalVfr);
                } else if (ophours < 10 && opminut > 10) {
                    totalVfr = "0" + String.valueOf(ophours) + ":" + String.valueOf(opminut);
                    TvVfr.setText(totalVfr);
                } else if (ophours > 10 && opminut < 10) {
                    totalVfr = String.valueOf(ophours) + ":0" + String.valueOf(opminut);
                    TvVfr.setText(totalVfr);
                } else if (ophours == 10 && opminut < 10) {
                    totalVfr = String.valueOf(ophours) + ":0" + String.valueOf(opminut);
                    TvVfr.setText(totalVfr);
                } else if (ophours < 10 && opminut == 10) {
                    totalVfr = "0" + String.valueOf(ophours) + ":" + String.valueOf(opminut);
                    TvVfr.setText(totalVfr);
                } else {
                    //System.out.println("crudos");
                    totalVfr = String.valueOf(ophours) + ":" + String.valueOf(opminut);
                    TvVfr.setText(totalVfr);
                }
            }


            //calculo ifr /////////////////////////////////////////////////////////////////
            int horaIfr = 0;
            int minIfr = 0;
            if (hora2Aircraft > 6) {//condicion para el calculo de IFR
                horaIfr = hora2Aircraft - 6;
                minIfr = min2Aircraft;
                if (horaIfr < 10 && minIfr < 10) {
                    totalIfr = "0" + String.valueOf(horaIfr) + ":0" + String.valueOf(minIfr);
                    TvIfr.setText(totalIfr);
                } else if (horaIfr < 10 && minIfr > 10) {
                    totalIfr = "0" + String.valueOf(horaIfr) + ":" + String.valueOf(minIfr);
                    TvIfr.setText(totalIfr);
                } else if (horaIfr > 10 && minIfr < 10) {
                    totalIfr = String.valueOf(horaIfr) + ":0" + String.valueOf(minIfr);
                    TvIfr.setText(totalIfr);
                } else if (horaIfr == 10 && minIfr < 10) {
                    totalIfr = String.valueOf(horaIfr) + ":0" + String.valueOf(minIfr);
                    TvIfr.setText(totalIfr);
                } else if (horaIfr < 10 && minIfr == 10) {
                    totalIfr = "0" + String.valueOf(horaIfr) + ":" + String.valueOf(minIfr);
                    TvIfr.setText(totalIfr);
                } else {
                    //System.out.println("crudos");
                    totalIfr = String.valueOf(horaIfr) + ":" + String.valueOf(minIfr);
                    TvIfr.setText(totalIfr);
                }

            } else {
                minIfr = min2Aircraft;
                if (minIfr < 10) {
                    totalIfr = "00:0" + String.valueOf(minIfr);
                    TvIfr.setText(totalIfr);
                } else {
                    totalIfr = "00:" + String.valueOf(minIfr);
                    TvIfr.setText(totalIfr);
                }
            }


            //condicion para calcular nocturno cuando afecta solo una parte de la hora ******H1C y H1A y H2A estan antes de las 06:00*********************************************************
        } else if ((hora1crew < 6) && (hora1Aircraft < 6) && (hora2Aircraft < 6) && ((hora2crew >= 6) && (hora2crew < 18))) {
           // System.out.println("DATA3 RH");
            //calculo del dato nocturno//////////////////////////////////////////////////////////////
            //variables publicas
            nocHra1 = hora1crew;
            nocMin1 = min1crew;
            nocHra2 = 6;
            nocMin2 = 0;
            //variables de operacion
            difhours = nocHra2 - nocHra1;
            difminut = nocMin2 - nocMin1;
            totalMin2 = 0;
            totalHor2 = 0;
            //logica para el calculo de la diferencia hora inicial vs hora final
            if (difhours < 0 && difminut < 0) {//cuando el dato de Hora y minutos son NEGATIVOS -;-
                //System.out.println("negativo - negativo");
                totalHor2 = (23 - nocHra1) + nocHra2;
                totalMin2 = (60 - nocMin1) + nocMin2;
                if (totalHor2 < 10 && totalMin2 < 10) {
                    totalNoc = "0" + String.valueOf(totalHor2) + ":0" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                } else if (totalHor2 < 10) {
                    totalNoc = "0" + String.valueOf(totalHor2) + ":" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                } else if (totalMin2 < 10) {
                    totalNoc = String.valueOf(totalHor2) + ":0" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                } else {
                    totalNoc = String.valueOf(totalHor2) + ":" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                }

            } else if (difhours < 0 && difminut >= 0) { //condicion cuando el resultado hora es NEGATIVO y resultado minutos es POSITIVO O IGUAL A CERO -;+
                //System.out.println("negativo - positivo = 0");
                totalHor2 = (24 - nocHra1) + nocHra2;
                if (totalHor2 < 10 && difminut < 10) {
                    totalNoc = "0" + String.valueOf(totalHor2) + ":0" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else if (totalHor2 < 10) {
                    totalNoc = "0" + String.valueOf(totalHor2) + ":" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else if (difminut < 10) {
                    totalNoc = String.valueOf(totalHor2) + ":0" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else {
                    totalNoc = String.valueOf(totalHor2) + ":" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                }

            } else if (difhours >= 0 && difminut < 0) { //condicion cuando el resultado horas es POSITIVO IGUAL A CERO  y el resultado minutos es NEGATIVO +;-
                //System.out.println("positivo = 0 - negativo");
                difhours = difhours - 1;
                totalMin2 = (60 - nocMin1) + nocMin2;
                if (difhours < 10 && totalMin2 > 10) {
                    totalNoc = "0" + String.valueOf(difhours) + ":" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                } else if (difhours < 10 && totalMin2 < 10) {
                    totalNoc = "0" + String.valueOf(difhours) + ":0" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                } else if (difhours < 10 && totalMin2 == 10) {
                    totalNoc = "0" + String.valueOf(difhours) + ":" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                }else {
                    totalMin2 = (60 - nocMin1) + nocMin2;
                    totalNoc = String.valueOf(difhours) + ":" + String.valueOf(totalMin2);
                    Nocturno.setText(totalNoc);
                }
            } else { //condiciones de resultado horas y minutos mayores a uno y POSITIVOS +;+
                //System.out.println("positivo - positivo");
                if (difhours < 10 && difminut < 10) {
                    totalNoc = "0" + String.valueOf(difhours) + ":0" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else if (difhours < 10 && difminut > 10) {
                    totalNoc = "0" + String.valueOf(difhours) + ":" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else if (difhours > 10 && difminut < 10) {
                    totalNoc = String.valueOf(difhours) + ":0" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else if (difhours == 10 && difminut < 10) {
                    totalNoc = String.valueOf(difhours) + ":0" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else if (difhours < 10 && difminut == 10) {
                    totalNoc = "0" + String.valueOf(difhours) + ":" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                } else {
                    totalNoc = String.valueOf(difhours) + ":" + String.valueOf(difminut);
                    Nocturno.setText(totalNoc);
                }
            }


            //calculo ifr//////////////////////////////////////////////////////////////
            TvIfr.setText("00:00");


            //calculo vfr//////////////////////////////////////////////////////////////
            if (hora2crew == 6) {
               // System.out.println("AQUI");
                if (min2crew < 10){
                    TvVfr.setText("00:0" + String.valueOf(min2crew));
                }else{
                    TvVfr.setText("00:" + String.valueOf(min2crew));
                }

            } else {
                nocHra1 = 6;
                nocMin1 = 0;
                nocHra2 = hora2crew;
                nocMin2 = min2crew;
                //variables de operacion
                difhours = nocHra2 - nocHra1;
                difminut = nocMin2 - nocMin1;
                totalMin2 = 0;
                totalHor2 = 0;
                //logica para el calculo de la diferencia hora inicial vs hora final
                if (difhours < 0 && difminut < 0) {//cuando el dato de Hora y minutos son NEGATIVOS -;-
                  //System.out.println("negativo - negativo");
                    totalHor2 = (23 - nocHra1) + nocHra2;
                    totalMin2 = (60 - nocMin1) + nocMin2;
                    if (totalHor2 < 10 && totalMin2 < 10) {
                        totalNoc = "0" + String.valueOf(totalHor2) + ":0" + String.valueOf(totalMin2);
                        TvVfr.setText(totalNoc);
                    } else if (totalHor2 < 10) {
                        totalNoc = "0" + String.valueOf(totalHor2) + ":" + String.valueOf(totalMin2);
                        TvVfr.setText(totalNoc);
                    } else if (totalMin2 < 10) {
                        totalNoc = String.valueOf(totalHor2) + ":0" + String.valueOf(totalMin2);
                        TvVfr.setText(totalNoc);
                    } else {
                        totalNoc = String.valueOf(totalHor2) + ":" + String.valueOf(totalMin2);
                        TvVfr.setText(totalNoc);
                    }

                } else if (difhours < 0 && difminut >= 0) { //condicion cuando el resultado hora es NEGATIVO y resultado minutos es POSITIVO O IGUAL A CERO -;+
                  //System.out.println("negativo - positivo = 0");
                    totalHor2 = (24 - nocHra1) + nocHra2;
                    if (totalHor2 < 10 && difminut < 10) {
                        totalNoc = "0" + String.valueOf(totalHor2) + ":0" + String.valueOf(difminut);
                        TvVfr.setText(totalNoc);
                    } else if (totalHor2 < 10) {
                        totalNoc = "0" + String.valueOf(totalHor2) + ":" + String.valueOf(difminut);
                        TvVfr.setText(totalNoc);
                    } else if (difminut < 10) {
                        totalNoc = String.valueOf(totalHor2) + ":0" + String.valueOf(difminut);
                        TvVfr.setText(totalNoc);
                    } else {
                        totalNoc = String.valueOf(totalHor2) + ":" + String.valueOf(difminut);
                        TvVfr.setText(totalNoc);
                    }

                } else if (difhours >= 0 && difminut < 0) { //condicion cuando el resultado horas es POSITIVO IGUAL A CERO  y el resultado minutos es NEGATIVO +;-
                  //System.out.println("positivo = 0 - negativo");
                    difhours = difhours - 1;
                    totalMin2 = (60 - nocMin1) + nocMin2;
                    if (difhours < 10 && totalMin2 > 10) {
                        totalNoc = "0" + String.valueOf(difhours) + ":" + String.valueOf(totalMin2);
                        TvVfr.setText(totalNoc);
                    } else if (difhours < 10 && totalMin2 < 10) {
                        totalNoc = "0" + String.valueOf(difhours) + ":0" + String.valueOf(totalMin2);
                        TvVfr.setText(totalNoc);
                    } else {
                        totalMin2 = (60 - nocMin1) + nocMin2;
                        totalNoc = String.valueOf(difhours) + ":" + String.valueOf(totalMin2);
                        TvVfr.setText(totalNoc);
                    }
                } else { //condiciones de resultado horas y minutos mayores a uno y POSITIVOS +;+
                  //System.out.println("positivo - positivo");
                    if (difhours < 10 && difminut < 10) {
                        totalNoc = "0" + String.valueOf(difhours) + ":0" + String.valueOf(difminut);
                        TvVfr.setText(totalNoc);
                    } else if (difhours < 10 && difminut > 10) {
                        totalNoc = "0" + String.valueOf(difhours) + ":" + String.valueOf(difminut);
                        TvVfr.setText(totalNoc);
                    } else if (difhours > 10 && difminut < 10) {
                        totalNoc = String.valueOf(difhours) + ":0" + String.valueOf(difminut);
                        TvVfr.setText(totalNoc);
                    } else if (difhours == 10 && difminut < 10) {
                        totalNoc = String.valueOf(difhours) + ":0" + String.valueOf(difminut);
                        TvVfr.setText(totalNoc);
                    } else if (difhours < 10 && difminut == 10) {
                        totalNoc = "0" + String.valueOf(difhours) + ":" + String.valueOf(difminut);
                        TvVfr.setText(totalNoc);
                    } else {
                        totalNoc = String.valueOf(difhours) + ":" + String.valueOf(difminut);
                        TvVfr.setText(totalNoc);
                    }
                }
            }


            //condicion para calcular nocturno antes de las 06:00 y despues de las 18:00 *********SOLO NOCTURNO********************************************************************************
        } else if (((hora1crew < 6) || (hora1crew >= 18)) && ((hora2crew < 6) || (hora2crew >= 18)) && ((min1crew <= 59) || (min1crew >= 0)) && ((min2crew <= 59) || (min2crew >= 0)) && ((hora1Aircraft <= 6) || (hora1Aircraft >= 18)) && ((hora2Aircraft <= 6) || (hora2Aircraft >= 18)) && ((min1Aircraft <= 59) || (min1Aircraft >= 0)) && ((min2Aircraft <= 59) || (min2Aircraft >= 0))) {
            //System.out.println("SOLO NOCTURNO");
            totalNoc = TotalCrew.getText().toString();
            Nocturno.setText(totalNoc);
            TvVfr.setText("00:00");
            TvIfr.setText("00:00");
            // condicion solo diurno datos entre las 06:00 y las 18:00*************************SOLO DIURNO************************************************************************************
        } else if (((hora1crew >= 6) && (hora1crew < 18)) && ((hora2crew >= 6) && (hora2crew < 18)) && ((hora1Aircraft >= 6) && (hora1Aircraft < 18)) && ((hora2Aircraft >= 6) && (hora2Aircraft < 18))) {
            //calculo Nocturno//////////////////////////////////////////////////////////////
            //System.out.println("SOLO DIURNO");
            Nocturno.setText("00:00");
            totalIfr = TotalAircraft.getText().toString();

            //calculo Ifr//////////////////////////////////////////////////////////////
            TvIfr.setText(totalIfr);

            //calculo vfr//////////////////////////////////////////////////////////////
            int vfr1 = 0;
            int vfr2 = 0;
            if ((hora1crew == hora1Aircraft) && (hora2crew == hora2Aircraft)) {//condicion cuando las horas son iguales
                int dif1vfr = min1Aircraft - min1crew;

                if ((dif1vfr < 0) && (hora1crew == hora1Aircraft)) { //condicion para el calculo de VFR
                    vfr1 = (60 - min1crew) + min1Aircraft;

                } else if ((dif1vfr < 0) && (hora1crew < hora1Aircraft)) {
                    vfr1 = (60 - min1Aircraft) + min1crew;

                } else {
                    vfr1 = dif1vfr;
                }

                int dif2vfr = min2crew - min2Aircraft;

                if ((dif2vfr < 0) && (hora2crew == hora2Aircraft)) { //condicion para el calculo de VFR
                    vfr2 = (60 - min2crew) + min2Aircraft;

                } else if ((dif2vfr < 0) && (hora2crew > hora2Aircraft)) {
                    vfr2 = (60 - min2Aircraft) + min2crew;

                } else {
                    vfr2 = dif2vfr;
                }

                vfr = vfr1 + vfr2;
                if (vfr <= 9) {
                    totalVfr = "00:0" + String.valueOf(vfr);
                    TvVfr.setText(totalVfr);
                } else {
                    totalVfr = "00:" + String.valueOf(vfr);
                    TvVfr.setText(totalVfr);
                }


            } else {//condicion cuando los valores de vfr son en diferentes horas HORAS EXTENDIDAS

                //Esta parte del codigo halla la diferencia entre H1A - H1C
                nocHra1 = hora1crew;
                nocMin1 = min1crew;
                nocHra2 = hora1Aircraft;
                nocMin2 = min1Aircraft;
                //variables de operacion
                int difhours1 = nocHra2 - nocHra1;
                int difminut1 = nocMin2 - nocMin1;
                totalMin1 = 0;
                totalHor1 = 0;
                vfr1 = 0;
                vfr2 = 0;
                vfr = 0;
                int vfr1Horas = 0;
                int vfr1Min = 0;
                int vfr2Horas = 0;
                int vfr2Min = 0;

                //logica para el calculo de la diferencia hora inicial vs hora final
                if (difhours1 < 0 && difminut1 < 0) {//cuando el dato de Hora y minutos son NEGATIVOS -;-
                    //System.out.println("vfr 1 negativo - negativo");
                    totalHor1 = (23 - nocHra1) + nocHra2;
                    totalMin1 = (60 - nocMin1) + nocMin2;
                    vfr1Horas = totalHor1;
                    vfr1Min = totalMin1;

                } else if (difhours1 < 0 && difminut1 >= 0) { //condicion cuando el resultado hora es NEGATIVO y resultado minutos es POSITIVO O IGUAL A CERO -;+
                    //System.out.println("vfr 1 negativo - positivo = 0");
                    totalHor1 = (24 - nocHra1) + nocHra2;
                    vfr1Horas = totalHor1;
                    vfr1Min = difminut1;


                } else if (difhours1 >= 0 && difminut1 < 0) { //condicion cuando el resultado horas es POSITIVO IGUAL A CERO  y el resultado minutos es NEGATIVO +;-
                    //System.out.println("vfr 1 positivo = 0 - negativo");
                    difhours1 = difhours1 - 1;
                    totalMin1 = (60 - nocMin1) + nocMin2;
                    vfr1Horas = difhours1;
                    vfr1Min = totalMin1;

                } else { //condiciones de resultado horas y minutos mayores a uno y POSITIVOS +;+
                    //System.out.println("vfr 1 positivo - positivo");
                    vfr1Horas = difhours1;
                    vfr1Min = difminut1;

                }

                //Esta parte del codigo halla la diferencia entre H2C - H2A para el calculo de vfr

                nocHra1 = hora2Aircraft;
                nocMin1 = min2Aircraft;
                nocHra2 = hora2crew;
                nocMin2 = min2crew;
                //variables de operacion
                int difhours2 = nocHra2 - nocHra1;
                int difminut2 = nocMin2 - nocMin1;
                totalMin2 = 0;
                totalHor2 = 0;
                //logica para el calculo de la diferencia hora inicial vs hora final
                if (difhours2 < 0 && difminut2 < 0) {//cuando el dato de Hora y minutos son NEGATIVOS -;-
                    //System.out.println("vfr 2 negativo - negativo");
                    totalHor2 = (23 - nocHra1) + nocHra2;
                    totalMin2 = (60 - nocMin1) + nocMin2;
                    vfr2Horas = totalHor2;
                    vfr2Min = totalMin2;


                } else if (difhours2 < 0 && difminut2 >= 0) { //condicion cuando el resultado hora es NEGATIVO y resultado minutos es POSITIVO O IGUAL A CERO -;+
                    //System.out.println("vfr 2 negativo - positivo = 0");
                    totalHor2 = (24 - nocHra1) + nocHra2;
                    vfr2Horas = totalHor2;
                    vfr2Min = difminut2;


                } else if (difhours2 >= 0 && difminut2 < 0) { //condicion cuando el resultado horas es POSITIVO IGUAL A CERO  y el resultado minutos es NEGATIVO +;-
                    //System.out.println("vfr 2 positivo = 0 - negativo");
                    difhours2 = difhours2 - 1;
                    totalMin2 = (60 - nocMin1) + nocMin2;
                    vfr2Horas = difhours2;
                    vfr2Min = totalMin2;

                } else { //condiciones de resultado horas y minutos mayores a uno y POSITIVOS +;+
                    //System.out.println("vfr 2 positivo - positivo");
                    vfr2Horas = difhours2;
                    vfr2Min = difminut2;

                }

                //Esta parte del codigo suma los datos de las diferencias entre (H1A - H1C)+(H2C-H2A) Y MUESTRA EL RESULTADO
                int vfrHoras = vfr1Horas + vfr2Horas;
                int vfrMin = vfr1Min + vfr2Min;
                if (vfrHoras < 10 && vfrMin < 10) {
                    totalVfr = "0" + String.valueOf(vfrHoras) + ":0" + String.valueOf(vfrMin);
                    TvVfr.setText(totalVfr);
                } else if (vfrHoras < 10 && vfrMin > 10) {
                    totalVfr = "0" + String.valueOf(vfrHoras) + ":" + String.valueOf(vfrMin);
                    TvVfr.setText(totalVfr);
                } else if (vfrHoras > 10 && vfrMin < 10) {
                    totalVfr = String.valueOf(vfrHoras) + ":0" + String.valueOf(vfrMin);
                    TvVfr.setText(totalVfr);
                } else if (vfrHoras == 10 && vfrMin < 10) {
                    totalVfr = String.valueOf(vfrHoras) + ":0" + String.valueOf(vfrMin);
                    TvVfr.setText(totalVfr);
                } else if (vfrHoras < 10 && vfrMin == 10) {
                    totalVfr = "0" + String.valueOf(vfrHoras) + ":" + String.valueOf(vfrMin);
                    TvVfr.setText(totalVfr);
                } else {
                    totalVfr = String.valueOf(vfrHoras) + ":" + String.valueOf(vfrMin);
                    TvVfr.setText(totalVfr);
                }

            }


        } else {
            System.out.println("null-null");
        }


    }////////////////////////////////////////////FIN CALCULAR OPERACIONES////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////******************//////////// BASE DE DATOS//////////////***************///////

    //METODO PARA INSERTAR DATOS EN LA BASE DE DATOS------------------------------------------
    public void Insertar(View view) {
        //HABILITA LOS BOTONES
        Button btn1crew = (Button) findViewById(R.id.Prendida);
        btn1crew.setEnabled(true);
        Button btn2crew = (Button) findViewById(R.id.Apagada);
        btn2crew.setEnabled(true);
        Button btn1Aircraft = (Button) findViewById(R.id.Decolage);
        btn1Aircraft.setEnabled(true);
        Button btn2Aircraft = (Button) findViewById(R.id.Aterrizaje);
        btn2Aircraft.setEnabled(true);

        //codigo para cargar el documento PDF
        selRegistro = etRegistro.getText().toString();//aqui se almacena el numero del requistro como nombre del documento PDF
        TipoAerPdf = Tipo.getText().toString();
        numHKPdf = MatriculaHK.getText().toString();
        numFACPdf = AeronaveFac.getSelectedItem().toString();
        //codogo para mostrar los datos en el registro de combustible DetailActivity
        NumeroVuelo = etNumVuelo.getText().toString();
        Origen = etDesde.getText().toString();
        Destino = etHacia.getText().toString();


        //se cargan las variables Publicas con los datos del ingresados por el usuario
        numvuelo = etNumVuelo.getText().toString();
        desde = etDesde.getText().toString();
        hacia = etHacia.getText().toString();
        Prendida_hor = et1.getText().toString();
        Prendida_min = et2.getText().toString();
        Apagada_hor = et3.getText().toString();
        Apagada_min = et4.getText().toString();
        Vfr = TvVfr.getText().toString();
        Ifr = TvIfr.getText().toString();
        Noct = Nocturno.getText().toString();
        Total_crew = TotalCrew.getText().toString();
        Decolage_hor = et5.getText().toString();
        Decolage_min = et6.getText().toString();
        Aterrizaje_hor = et7.getText().toString();
        Aterrizaje_min = et8.getText().toString();
        Total_aircraft = TotalAircraft.getText().toString();
        Ciclos = ciclosBD;
        Pax = etPax.getText().toString();
        Carga = etCarga.getText().toString();
        Fuel = etFuel.getText().toString();


        //verifica que los campos tengan datos para ingresar a la base de datos
        if (!numvuelo.isEmpty() && !desde.isEmpty() && !hacia.isEmpty() && !Pax.isEmpty() && !Carga.isEmpty() && !Fuel.isEmpty()) {

            //se cargan los datos en la tabla TOTALES
            Totales_tripulacion = "";
            Total_tripulacion_hor = totalTripHoraBD;
            Total_tripulacion_min = totalTripMinBD;
            //System.out.println(Total_tripulacion_min);
            Totales_aeronave = "";
            Total_aeronave_hor = totalAerHoraBD;
            Total_aeronave_min = totalAerMinBD;
            //CODIGO PARA LAS CONDICIONES DEL VUELO:
            vfrHoraBD = Integer.parseInt(String.valueOf(Vfr.charAt(1)));//el dato de hora vfr no es mayor a 10, siempre es un solo digito
            Total_Vfr_Hora = vfrHoraBD;//HORA VFR
            //codigo para capturar los valores enteros y almacenarlos en la base de datos TOTALES
            vfrMinBD = Integer.parseInt(String.valueOf(Vfr.charAt(3)));
            if (vfrMinBD == 0) {
                vfrMinBD = Integer.parseInt(String.valueOf(Vfr.charAt(4)));
                Total_Vfr_Min = vfrMinBD;//MINUTOS VFR
            } else {
                vfrMinBD = Integer.parseInt(String.valueOf(Vfr.charAt(3)));
                vfrMinBD = (vfrMinBD * 10) + Integer.parseInt(String.valueOf(Vfr.charAt(4)));
                Total_Vfr_Min = vfrMinBD;//MINUTOS VFR
            }

            ifrHoraBD = Integer.parseInt(String.valueOf(Ifr.charAt(1)));//el dato de hora ifr no es mayor a 10, siempre es un solo digito
            Total_Ifr_Hora = ifrHoraBD;//HORA IFR
            ifrMinBD = Integer.parseInt(String.valueOf(Ifr.charAt(3)));
            if (ifrMinBD == 0) {
                ifrMinBD = Integer.parseInt(String.valueOf(Ifr.charAt(4)));
                Total_Ifr_Min = ifrMinBD;//MINUTOS IFR
            } else {
                ifrMinBD = Integer.parseInt(String.valueOf(Ifr.charAt(3)));
                ifrMinBD = (ifrMinBD * 10) + Integer.parseInt(String.valueOf(Ifr.charAt(4)));
                Total_Ifr_Min = ifrMinBD;//MINUTOS IFR
            }

            noctHoraBD = Integer.parseInt(String.valueOf(Noct.charAt(1)));//el dato de hora Nocturno no es mayor a 10, siempre es un solo digito
            Total_Nocturno_Hora = noctHoraBD;//HORA NOCTURNO
            noctMinBD = Integer.parseInt(String.valueOf(Noct.charAt(3)));
            if (noctMinBD == 0) {
                noctMinBD = Integer.parseInt(String.valueOf(Noct.charAt(4)));
                Total_Nocturno_Min = noctMinBD;//MINUTOS NOCTURNO

            } else {
                noctMinBD = Integer.parseInt(String.valueOf(Noct.charAt(3)));
                noctMinBD = (noctMinBD * 10) + Integer.parseInt(String.valueOf(Noct.charAt(4)));
                Total_Nocturno_Min = noctMinBD;//MINUTOS NOCTURNO
            }

            Total_Ciclos = ciclosBD;
            cargaBD = Integer.parseInt(String.valueOf(Carga));
            Total_Carga = cargaBD;
            paxBD = Integer.parseInt(String.valueOf(Pax));
            Total_Pax = paxBD;
            fuelBD = Integer.parseInt(String.valueOf(Fuel));
            Total_Fuel = fuelBD;


            admin.abrirBD();
            admin.IngresarDatos();
            InsertarContador();//inserta los datos en contador de horas
            admin.cerrarBD();
            Limpiar();
            etNumVuelo.setText("");

            Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }


    }

    //inserta datos en la TABLA CONTADOR------BASE DE DATOS CONTADOR***************************
    public void InsertarContador() {

        //days = Integer.parseInt(String.valueOf(date.charAt(4)));

        Calendar calendar = Calendar.getInstance();
         int year = calendar.get(Calendar.YEAR);
         years = String.valueOf(year);
        int month = calendar.get(Calendar.MONTH)+ 1;
        months = String.valueOf(month);
         weeks = 0;
         days = calendar.get(Calendar.DAY_OF_MONTH);

        numvuelo = etNumVuelo.getText().toString();
        Totales_tripulacion = "";
        Total_tripulacion_hor = totalTripHoraBD;
        Total_tripulacion_min = totalTripMinBD;
        if(days <= 15){
            weeks = 1;
            admincount.abrirBD();
            admincount.IngresarDatosContador();
            admincount.cerrarBD();

            horasTotalesHoy();
        }else {
            weeks=2;
            admincount.abrirBD();
            admincount.IngresarDatosContador();
            admincount.cerrarBD();

            horasTotalesHoy();
        }
    }

    //este metodo muestra el total de horas voladas en un solo dia
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
        if(MinTotales >59) { //condicion para Minutos tripulacion

        while (MinTotales > 59) {

            MinTotales = MinTotales - 60;
            HorasTotales = HorasTotales + 1;
        }
        if (MinTotales <= 9) {//codigo para mejorar la presentacion de los minutos menores a 10
            Total_Tripulacion = String.valueOf(HorasTotales) + ":0" + String.valueOf(MinTotales);
            HorasHoy.setText("►" +Total_Tripulacion+"◄");

        } else {
            Total_Tripulacion = String.valueOf(HorasTotales) + ":" + String.valueOf(MinTotales);
            HorasHoy.setText("►" +Total_Tripulacion+"◄");
        }
    }else {
        if (MinTotales <= 9) {//codigo para mejorar la presentacion de los minutos menores a 10
            Total_Tripulacion = String.valueOf(HorasTotales) + ":0" + String.valueOf(MinTotales);
            HorasHoy.setText("►" +Total_Tripulacion+"◄");
        } else {
            Total_Tripulacion = String.valueOf(HorasTotales) + ":" + String.valueOf(MinTotales);
            HorasHoy.setText("►" + Total_Tripulacion +"◄");
        }
    }

}

    ////////////////////////////////////////////////////////////////////////////////////////////
    //METODO PARA CONSULTAR DATOS EN LA BASE DE DATOS------------------------------------------

    public void Buscar(View view){


        numvuelo = etNumVuelo.getText().toString();


        if(!numvuelo.isEmpty()){
            admin.abrirBD();//abre la base de datos

            Cursor fila = admin.BuscarDatos(numvuelo);//llama el cursor de la clase AdminSQLiteOpenHelper

            if(fila.moveToFirst()){

                //muestra la consulta de la base de datos en la actividad

                etDesde.setText(fila.getString(0));
                etHacia.setText(fila.getString(1));
                et1.setText(fila.getString(2));
                et2.setText(fila.getString(3));
                et3.setText(fila.getString(4));
                et4.setText(fila.getString(5));
                TvVfr.setText(fila.getString(6));
                TvIfr.setText(fila.getString(7));
                Nocturno.setText(fila.getString(8));
                TotalCrew.setText(fila.getString(9));
                et5.setText(fila.getString(10));
                et6.setText(fila.getString(11));
                et7.setText(fila.getString(12));
                et8.setText(fila.getString(13));
                TotalAircraft.setText(fila.getString(14));
                //ciclos 15
                etPax.setText(fila.getString(16));
                etCarga.setText(fila.getString(17));
                etFuel.setText(fila.getString(18));


                //cierra la Base de Datos
                admin.cerrarBD();

            } else {
                Toast.makeText(this,"No existe el Vuelo", Toast.LENGTH_SHORT).show();
                admin.cerrarBD();
                Limpiar();
            }

        } else {
            Toast.makeText(this, "Debes introducir el Num Vuelo", Toast.LENGTH_SHORT).show();
        }
    }


    //////////////////////////////////////////////////////////////////////////////////////////////
    //    //METODO PARA ELIMINAR DATOS EN LA BASE DE DATOS------------------------------------------

    public void Eliminar(View view){

        admin.abrirBD();
        admincount.abrirBD();


        String numvuelo = etNumVuelo.getText().toString();

        if(!numvuelo.isEmpty()){


            int cantidad = admin.Eliminar(numvuelo);//llama el medoto eliminar que devuelve un numero entero
            admin.cerrarBD();

            Limpiar(); //limpia las entradas del usuario
            cargarActivity();

            if(cantidad == 1){
               // EliminarDatosContador();
                int cantidad1 = admincount.EliminarDatosContador(numvuelo);//llama el medoto eliminar que devuelve un numero entero**elimina datos tabla CONTADOR
                admincount.cerrarBD();

                if(cantidad1 == 1){
                    Toast.makeText(this, "Vuelo Eliminado", Toast.LENGTH_SHORT).show();
                    Limpiar();
                    horasTotalesHoy();
                }else {
                    Toast.makeText(this, "Vuelo no existe", Toast.LENGTH_SHORT).show();

                }


            } else {
                Toast.makeText(this, "El Vuelo no existe", Toast.LENGTH_SHORT).show();

            }

        } else {
            Toast.makeText(this, "Debes de introducir el Num Vuelo", Toast.LENGTH_SHORT).show();

        }
    }
///////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////
    //    //METODO PARA ELIMINAR DATOS EN LA BASE DE DATOS CONTADOR**------------------------------------------

    public void EliminarDatosContador(){

        admincount.abrirBD();

        String numvuelo = etNumVuelo.getText().toString();


            int cantidad = admincount.EliminarDatosContador(numvuelo);//llama el medoto eliminar que devuelve un numero entero
            admincount.cerrarBD();

           horasTotalesHoy();


    }



    //////////////////////////////////////////////////////////////////////////////////////////////
    //    //METODO PARA MODIFICAR DATOS EN LA BASE DE DATOS------------------------------------------

    public void Modificar(View view){
       admin.abrirBD();
       admincount.abrirBD();


        //variables para capturar los datos ingresados
        //se cargan las variables Publicas con los datos del ingresados por el usuario
        numvuelo = etNumVuelo.getText().toString();
        desde = etDesde.getText().toString();
        hacia = etHacia.getText().toString();
        Prendida_hor = et1.getText().toString();
        Prendida_min = et2.getText().toString();
        Apagada_hor = et3.getText().toString();
        Apagada_min = et4.getText().toString();
        Vfr = TvVfr.getText().toString();
        Ifr = TvIfr.getText().toString();
        Noct = Nocturno.getText().toString();
        Total_crew = TotalCrew.getText().toString();
        Decolage_hor = et5.getText().toString();
        Decolage_min = et6.getText().toString();
        Aterrizaje_hor = et7.getText().toString();
        Aterrizaje_min = et8.getText().toString();
        Total_aircraft = TotalAircraft.getText().toString();
        Ciclos = ciclosBD;
        Pax = etPax.getText().toString();
        Carga = etCarga.getText().toString();
        Fuel = etFuel.getText().toString();


        //codicion para verificar que los datos esten ingresados
        if( !numvuelo.isEmpty() && !desde.isEmpty() && !hacia.isEmpty()){

            ////////////////////////////////
            //se cargan los datos en la tabla TOTALES
            Totales_tripulacion = "";
            Total_tripulacion_hor = totalTripHoraBD;
            Total_tripulacion_min = totalTripMinBD;
            Totales_aeronave = "";
            Total_aeronave_hor = totalAerHoraBD;
            Total_aeronave_min = totalAerMinBD;
            //CODIGO PARA LAS CONDICIONES DEL VUELO:
            vfrHoraBD = Integer.parseInt(String.valueOf(Vfr.charAt(1)));//el dato de hora vfr no es mayor a 10, siempre es un solo digito
            Total_Vfr_Hora = vfrHoraBD;//HORA VFR
            //codigo para capturar los valores enteros y almacenarlos en la base de datos TOTALES
            vfrMinBD = Integer.parseInt(String.valueOf(Vfr.charAt(3)));
            if (vfrMinBD == 0){
                vfrMinBD = Integer.parseInt(String.valueOf(Vfr.charAt(4)));
                Total_Vfr_Min = vfrMinBD;//MINUTOS VFR
            }else {
                vfrMinBD = Integer.parseInt(String.valueOf(Vfr.charAt(3)));
                vfrMinBD = (vfrMinBD * 10) + Integer.parseInt(String.valueOf(Vfr.charAt(4)));
                Total_Vfr_Min = vfrMinBD;//MINUTOS VFR
            }

            ifrHoraBD = Integer.parseInt(String.valueOf(Ifr.charAt(1)));//el dato de hora ifr no es mayor a 10, siempre es un solo digito
            Total_Ifr_Hora = ifrHoraBD;//HORA IFR
            ifrMinBD = Integer.parseInt(String.valueOf(Ifr.charAt(3)));
            if (ifrMinBD == 0){
                ifrMinBD = Integer.parseInt(String.valueOf(Ifr.charAt(4)));
                Total_Ifr_Min = ifrMinBD;//MINUTOS IFR
            }else {
                ifrMinBD = Integer.parseInt(String.valueOf(Ifr.charAt(3)));
                ifrMinBD = (ifrMinBD * 10) + Integer.parseInt(String.valueOf(Ifr.charAt(4)));
                Total_Ifr_Min = ifrMinBD;//MINUTOS IFR
            }

            noctHoraBD = Integer.parseInt(String.valueOf(Noct.charAt(1)));//el dato de hora Nocturno no es mayor a 10, siempre es un solo digito
            Total_Nocturno_Hora = noctHoraBD;//HORA NOCTURNO
            noctMinBD = Integer.parseInt(String.valueOf(Noct.charAt(3)));
            if (noctMinBD == 0){
                noctMinBD = Integer.parseInt(String.valueOf(Noct.charAt(4)));
                Total_Nocturno_Min = noctMinBD;//MINUTOS NOCTURNO

            }else {
                noctMinBD = Integer.parseInt(String.valueOf(Noct.charAt(3)));
                noctMinBD = (noctMinBD * 10) + Integer.parseInt(String.valueOf(Noct.charAt(4)));
                Total_Nocturno_Min = noctMinBD;//MINUTOS NOCTURNO
            }

            Total_Ciclos = ciclosBD;
            cargaBD = Integer.parseInt(String.valueOf(Carga));
            Total_Carga = cargaBD;
            paxBD = Integer.parseInt(String.valueOf(Pax));
            Total_Pax = paxBD;
            fuelBD = Integer.parseInt(String.valueOf(Fuel));
            Total_Fuel = fuelBD;


            ///////////////////////////////////

                /////////////////////////////////////////
            //inserta datos en la TABLA CONTADOR------BASE DE DATOS CONTADOR***************************




                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                years = String.valueOf(year);
                int month = calendar.get(Calendar.MONTH)+ 1;
                months = String.valueOf(month);
                weeks = 0;
                days = calendar.get(Calendar.DAY_OF_MONTH);



            /////////////////////////////////


            int cantidad = admin.ModificarDatos(numvuelo);
            admin.cerrarBD();


            if(cantidad == 1){// condicion para modificar la Tabla Bitacora



                if(days <= 15){
                    weeks = 1;
                    int cantidad1 = admincount.ModificarDatosContador(numvuelo);
                    admincount.cerrarBD();
                    if(cantidad1 == 1){ // condicion para modificar la Tabla CONTADOR
                        Toast.makeText(this, "Vuelo modificado correctamente", Toast.LENGTH_SHORT).show();
                        horasTotalesHoy();
                    }else{
                        Toast.makeText(this, "Registro no existe", Toast.LENGTH_SHORT).show();
                    }


                }else {
                    weeks=2;
                    int cantidad2 = admincount.ModificarDatosContador(numvuelo);
                    admincount.cerrarBD();

                    if(cantidad2 == 1){// condicion para modificar la Tabla CONTADOR
                        Toast.makeText(this, "Vuelo modificado correctamente", Toast.LENGTH_SHORT).show();
                        horasTotalesHoy();
                    }else{
                        Toast.makeText(this, "Registro no existe", Toast.LENGTH_SHORT).show();
                    }
                }

            } else {
                Toast.makeText(this, "El Registro no existe", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }

        horasTotalesHoy ();
    }

 //////////////////////////////////////
    ////////////metodo para limpiar el formulario

    public void Limpiar() {

        //limpia las entradas del usuario
        //etRegistro.setText("");
        //etNumVuelo.setText("");
        etDesde.setText("");
        etHacia.setText("");
        //objetos EditText CREW
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        //objetos EditText AIRCRAFT
        et5.setText("");
        et6.setText("");
        et7.setText("");
        et8.setText("");
        //objetos miscelaneos
        etFuel.setText("");
        etPax.setText("");
        etCarga.setText("");
        //objeto Text View
        //Fecha.setText("Ingresar Fecha");
        //Tipo.setText("");
        //MatriculaHK.setText("");
        Nocturno.setText("");
        TvIfr.setText("");
        TvVfr.setText("");
        TotalAircraft.setText("");
        TotalCrew.setText("");

    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////CREAR DOCUMENTO PDF//////////////////////////////////////////
    //***************metodos del documento PDF*************************
    public void cargarDocPDF(){
        templatePDF.openDocument();
        templatePDF.addMetadata("Reporte","Bitacora","Andres Becerra");
        templatePDF.addTitles("Bitacora de Vuelo",selRegistro,date);
        templatePDF.addDatos(numFACPdf,numHKPdf,TipoAerPdf);
        //templatePDF.addParagraph(shortText);
        //templatePDF.addParagraph(longText);
        templatePDF.createTable(header,getClients());
        templatePDF.addParagraph("Totales:");
        templatePDF.createTableTotales(header1,getClients1());
        templatePDF.addParagraph1("Registro de combustibles en Vuelo: ");
        templatePDF.createTableFuel(header2,getClients2());
        templatePDF.addParagraph("Oprat 1.1");
        templatePDF.addParagraph(shortText);
        templatePDF.closeDocument();

    }



    //metodo para EL boton PDF VIEW para visualizar el documento pdf***********************************
    public void pdfView (View view){
        cargarActivity();
        cargarDocPDF();

        templatePDF.viewPDF(); //llama  templatePDF.java y busca el metodo viewPDF()
    }

    //metodo para EL boton PDF VIEW para visualizar el documento pdf de manera externa en una aplicacion***********************************
    public void pdfApp (View view){
        templatePDF.appviewPDF(this); //llama templatePDF.java y busca el metodo viewPDF()

    }

    //para llenar la tabla del documento TABLA REGISTRO DE VUELOS
    private ArrayList<String[]>getClients(){

        Vuelos numeroVuelo = null;
        ArrayList<String[]> rows = new ArrayList<>();
        ArrayList<Vuelos> registroVuelos = new ArrayList<Vuelos>();

        //Codigo para efectuar la consulta a la base de datos
        Cursor cursor = admin.getReadableDatabase().rawQuery("SELECT * FROM "+ Constantes.TABLA_BITACORA,null);
        //codigo para llenar las celdas en la table del Archivo PDF
        while (cursor.moveToNext()){
            numeroVuelo = new Vuelos();
            numeroVuelo.setNumvuelo(cursor.getString(0));
            numeroVuelo.setDesde(cursor.getString(1));
            numeroVuelo.setHacia(cursor.getString(2));
            numeroVuelo.setPrendida_hor(cursor.getString(3));
            numeroVuelo.setPrendida_min(cursor.getString(4));
            numeroVuelo.setApagada_hor(cursor.getString(5));
            numeroVuelo.setApagada_min(cursor.getString(6));
            numeroVuelo.setVfr(cursor.getString(7));
            numeroVuelo.setIfr(cursor.getString(8));
            numeroVuelo.setNocturno(cursor.getString(9));
            numeroVuelo.setTotal_crew(cursor.getString(10));
            numeroVuelo.setDecolage_hor(cursor.getString(11));
            numeroVuelo.setDecolage_min(cursor.getString(12));
            numeroVuelo.setAterrizaje_hor(cursor.getString(13));
            numeroVuelo.setAterrizaje_min(cursor.getString(14));
            numeroVuelo.setTotal_aircraft(cursor.getString(15));
            //numeroVuelo.setNum_landing(1);//numero de ciclos*** solo para mostrar en la tabla
            numeroVuelo.setNum_landing(cursor.getInt(16));
            numeroVuelo.setPax(cursor.getString(17));
            numeroVuelo.setCarga(cursor.getString(18));
            numeroVuelo.setFuel(cursor.getString(19));

            registroVuelos.add(numeroVuelo);

         }



        //llama todos los registros almacenados en la base de datos y lo spresenta en la tabla del Archivo PDF
        for(int i = 0; i < registroVuelos.size(); i++){


                String horaPrendida1 = registroVuelos.get(i).getPrendida_hor() + ":" + registroVuelos.get(i).getPrendida_min();
                String horaApagada1 = registroVuelos.get(i).getApagada_hor() + ":" + registroVuelos.get(i).getApagada_min();

                String horaDecolage1 = registroVuelos.get(i).getDecolage_hor() + ":" + registroVuelos.get(i).getDecolage_min();
                String horaAterrizaje1 = registroVuelos.get(i).getAterrizaje_hor() + ":" + registroVuelos.get(i).getAterrizaje_min();


                rows.add(new String[]{registroVuelos.get(i).getNumvuelo(), registroVuelos.get(i).getDesde(), registroVuelos.get(i).getHacia(), horaPrendida1, horaApagada1, registroVuelos.get(i).getVfr(), registroVuelos.get(i).getIfr(), registroVuelos.get(i).getNocturno(), registroVuelos.get(i).getTotal_crew(),
                        horaDecolage1, horaAterrizaje1, registroVuelos.get(i).getTotal_aircraft(), String.valueOf(registroVuelos.get(i).getNum_landing()), registroVuelos.get(i).getPax(), registroVuelos.get(i).getCarga(), registroVuelos.get(i).getFuel()});

            }


        return rows;

    }


    //crea la tabla en el archivo PDF  --llenar la tabla de TOTALES
    private ArrayList<String[]>getClients1(){

        ArrayList<String[]> rows = new ArrayList<>();
        TotalesVuelos numVueloTotal = null;
        ArrayList<TotalesVuelos> Totales = new ArrayList<TotalesVuelos>();

        //Codigo para efectuar la consulta a la base de datos
        Cursor cursor = admin.getReadableDatabase().rawQuery("SELECT * FROM "+Constantes.TABLA_TOTALES,null);
        //variables para los calculos y sumar los totales de la base de dato
        int TotalTripHora = 0;
        int TotalTripMin = 0;
        int TotalVfrHora = 0;
        int TotalVfrMin = 0;
        int TotalIfrHora = 0;
        int TotalIfrMin = 0;
        int TotalNocHora = 0;
        int TotalNocMin = 0;
        int TotalAerHora = 0;
        int TotalAerMin = 0;
        int TotalCiclos = 0;
        int TotalPax = 0;
        int TotalCarga = 0;
        int TotalFuel = 0;

        while (cursor.moveToNext()){

            numVueloTotal = new TotalesVuelos();

            numVueloTotal.setTotal_tripulacion_hor(cursor.getInt(1));
            numVueloTotal.setTotal_tripulacion_min(cursor.getInt(2));
            numVueloTotal.setVfrHora(cursor.getInt(3));
            numVueloTotal.setVfrMin(cursor.getInt(4));
            numVueloTotal.setIfrHora(cursor.getInt(5));
            numVueloTotal.setIfrMin(cursor.getInt(6));
            numVueloTotal.setNocturnoHora(cursor.getInt(7));
            numVueloTotal.setNocturnoMin(cursor.getInt(8));
            numVueloTotal.setTotal_aeronave_hor(cursor.getInt(9));
            numVueloTotal.setTotal_aeronave_min(cursor.getInt(10));
            numVueloTotal.setNum_landing(cursor.getInt(11));
            numVueloTotal.setPax(cursor.getInt(12));
            numVueloTotal.setCarga(cursor.getInt(13));
            numVueloTotal.setFuel(cursor.getInt(14));

            Totales.add(numVueloTotal);
        }

        for(int i = 0; i < Totales.size(); i++) {

            TotalTripHora = TotalTripHora + Totales.get(i).getTotal_tripulacion_hor();
            TotalTripMin = TotalTripMin + Totales.get(i).getTotal_tripulacion_min();
            TotalVfrHora = TotalVfrHora + Totales.get(i).getVfrHora();
            TotalVfrMin = TotalVfrMin + Totales.get(i).getVfrMin();
            TotalIfrHora = TotalIfrHora + Totales.get(i).getIfrHora();
            TotalIfrMin = TotalIfrMin + Totales.get(i).getIfrMin();
            TotalNocHora = TotalNocHora + Totales.get(i).getNocturnoHora();
            TotalNocMin = TotalNocMin + Totales.get(i).getNocturnoMin();
            TotalAerHora = TotalAerHora + Totales.get(i).getTotal_aeronave_hor();
            TotalAerMin = TotalAerMin + Totales.get(i).getTotal_aeronave_min();
            TotalCiclos = TotalCiclos + Totales.get(i).getNum_landing();
            TotalPax = TotalPax + Totales.get(i).getPax();
            TotalCarga = TotalCarga + Totales.get(i).getCarga();
            TotalFuel = TotalFuel + Totales.get(i).getFuel();


        }

        //variables para mostrar datos en archivo PDF
        String Total_Tripulacion = "";
        String Total_Vfr = "";
        String Total_Ifr = "";
        String Total_Noc = "";
        String Total_Aeronave = "";
        String Total_Pasajeros = String.valueOf(TotalPax);
        String Total_Carga = String.valueOf(TotalCarga);
        String Total_Landings = String.valueOf(TotalCiclos);
        String Total_Combustible = String.valueOf(TotalFuel);


        //codigo cuando los minutos son mayores a 60
        if(TotalTripMin > 59){ //condicion para Minutos tripulacion

            while (TotalTripMin > 59){

                TotalTripMin = TotalTripMin - 60;
                TotalTripHora = TotalTripHora + 1;
            }
            if(TotalTripMin <= 9){//codigo para mejorar la presentacion de los minutos menores a 10
                Total_Tripulacion = String.valueOf(TotalTripHora)+":0"+String.valueOf(TotalTripMin);
            }else{
                Total_Tripulacion = String.valueOf(TotalTripHora)+":"+String.valueOf(TotalTripMin);
            }
        }else{
            if(TotalTripMin <= 9){//codigo para mejorar la presentacion de los minutos menores a 10
                Total_Tripulacion = String.valueOf(TotalTripHora)+":0"+String.valueOf(TotalTripMin);
            }else{
                Total_Tripulacion = String.valueOf(TotalTripHora)+":"+String.valueOf(TotalTripMin);
            }

        }
//-------------------------------------------------------------------------
        if(TotalVfrMin > 59){ //condicion para Minutos VFR

            while (TotalVfrMin > 59){

                TotalVfrMin = TotalVfrMin - 60;
                TotalVfrHora = TotalVfrHora + 1;
            }
            if(TotalVfrMin <= 9){//codigo para mejorar la presentacion de los minutos menores a 10
                Total_Vfr = String.valueOf(TotalVfrHora)+":0"+String.valueOf(TotalVfrMin);
            }else{
                Total_Vfr = String.valueOf(TotalVfrHora)+":"+String.valueOf(TotalVfrMin);
            }
        }else{
            if(TotalVfrMin <= 9){//codigo para mejorar la presentacion de los minutos menores a 10
                Total_Vfr = String.valueOf(TotalVfrHora)+":0"+String.valueOf(TotalVfrMin);
            }else{
                Total_Vfr = String.valueOf(TotalVfrHora)+":"+String.valueOf(TotalVfrMin);
            }
        }
//---------------------------------------------------------------------------
        if(TotalIfrMin > 59){ //condicion para Minutos IFR

            while (TotalIfrMin > 59){

                TotalIfrMin = TotalIfrMin - 60;
                TotalIfrHora = TotalIfrHora + 1;
            }
            if(TotalIfrMin <= 9){//codigo para mejorar la presentacion de los minutos menores a 10
                Total_Ifr = String.valueOf(TotalIfrHora)+":0"+String.valueOf(TotalIfrMin);
            }else{
                Total_Ifr = String.valueOf(TotalIfrHora)+":"+String.valueOf(TotalIfrMin);
            }
        }else{
            if(TotalIfrMin <= 9){//codigo para mejorar la presentacion de los minutos menores a 10
                Total_Ifr = String.valueOf(TotalIfrHora)+":0"+String.valueOf(TotalIfrMin);
            }else{
                Total_Ifr = String.valueOf(TotalIfrHora)+":"+String.valueOf(TotalIfrMin);
            }
        }
//-------------------------------------------------------------------------------

        if(TotalNocMin > 59){ //condicion para Minutos NOCTURNO

            while (TotalNocMin > 59){

                TotalNocMin = TotalNocMin - 60;
                TotalNocHora = TotalNocHora + 1;
            }
            if(TotalNocMin <= 9){//codigo para mejorar la presentacion de los minutos menores a 10
                Total_Noc = String.valueOf(TotalNocHora)+":0"+String.valueOf(TotalNocMin);
            }else{
                Total_Noc = String.valueOf(TotalNocHora)+":"+String.valueOf(TotalNocMin);
            }
        }else{
            if(TotalNocMin <= 9){//codigo para mejorar la presentacion de los minutos menores a 10
                Total_Noc = String.valueOf(TotalNocHora)+":0"+String.valueOf(TotalNocMin);
            }else{
                Total_Noc = String.valueOf(TotalNocHora)+":"+String.valueOf(TotalNocMin);
            }
        }
//---------------------------------------------------------------------------------------
        if(TotalAerMin > 59){ //condicion para Minutos AERONAVE

            while (TotalAerMin > 59){

                TotalAerMin = TotalAerMin - 60;
                TotalAerHora = TotalAerHora + 1;
            }
            if(TotalAerMin <= 9){//codigo para mejorar la presentacion de los minutos menores a 10
                Total_Aeronave = String.valueOf(TotalAerHora)+":0"+String.valueOf(TotalAerMin);
            }else{
                Total_Aeronave = String.valueOf(TotalAerHora)+":"+String.valueOf(TotalAerMin);
            }
        }else{
            if(TotalAerMin <= 9){//codigo para mejorar la presentacion de los minutos menores a 10
                Total_Aeronave = String.valueOf(TotalAerHora)+":0"+String.valueOf(TotalAerMin);
            }else{
                Total_Aeronave = String.valueOf(TotalAerHora)+":"+String.valueOf(TotalAerMin);
            }
        }

        //codigo que muestra los totales en la tabla del archivo PDF
        rows.add(new String[]{Total_Tripulacion, Total_Vfr, Total_Ifr, Total_Noc, Total_Aeronave, Total_Landings, Total_Pasajeros, Total_Carga, Total_Combustible});


        return rows;

    }
    //crea la tabla en el archivo PDF  --tabla combustibles
    private ArrayList<String[]> getClients2() {


        FuelRegistro numRegistroFuel = null;
        ArrayList<String[]> rows = new ArrayList<>();
        ArrayList<FuelRegistro> registroFuel = new ArrayList<FuelRegistro>();

        //Codigo para efectuar la consulta a la base de datos
        Cursor cursor = admin.getReadableDatabase().rawQuery("SELECT * FROM "+Constantes.TABLA_FUEL,null);
        //codigo para llenar las celdas en la table del Archivo PDF
        while (cursor.moveToNext()){
            numRegistroFuel = new FuelRegistro();
            numRegistroFuel.setNumvuelo(cursor.getString(0));
            numRegistroFuel.setDesde(cursor.getString(1));
            numRegistroFuel.setHacia(cursor.getString(2));
            numRegistroFuel.setTow(cursor.getString(3));
            numRegistroFuel.setFuel_min(cursor.getString(4));
            numRegistroFuel.setFuel_tax_in(cursor.getString(5));
            numRegistroFuel.setFuel_tax_out(cursor.getString(6));
            numRegistroFuel.setTrip_fuel(cursor.getString(7));
            numRegistroFuel.setFf_eng1(cursor.getString(8));
            numRegistroFuel.setFf_eng2(cursor.getString(9));
            numRegistroFuel.setAltura(cursor.getString(10));
            numRegistroFuel.setVelocidad(cursor.getString(11));
            numRegistroFuel.setFuel_reman(cursor.getString(12));

            registroFuel.add(numRegistroFuel);

        }


        //llama todos los registros almacenados en la base de datos y los presenta en la tabla del Archivo PDF
        for(int i = 0; i < registroFuel.size(); i++){

            rows.add(new String[]{registroFuel.get(i).getNumvuelo(), registroFuel.get(i).getDesde(), registroFuel.get(i).getHacia(),
                    registroFuel.get(i).getTow(),registroFuel.get(i).getFuel_min(), registroFuel.get(i).getFuel_tax_in(),registroFuel.get(i).getFuel_tax_out(),
                    registroFuel.get(i).getTrip_fuel(), registroFuel.get(i).getFf_eng1(), registroFuel.get(i).getFf_eng2(), registroFuel.get(i).getAltura(), registroFuel.get(i).getVelocidad(), registroFuel.get(i).getFuel_reman()});

        }


        return rows;

    }


//llama la pagina fuel page, detailactivity
    public void GetDetailPage(View view){
        cargarActivity();
        //codogo para mostrar los datos en el registro de combustible DetailActivity
        selRegistro = etRegistro.getText().toString();
        NumeroVuelo = etNumVuelo.getText().toString();
        Origen = etDesde.getText().toString();
        Destino = etHacia.getText().toString();

        //verifica que los campos tengan datos para ingresar a la base de datos
        if( !selRegistro.isEmpty() && !NumeroVuelo.isEmpty() && !Origen.isEmpty()&& !Destino.isEmpty()) {


            Intent getdetailactivity = new Intent(this,DetailActivity.class);
            startActivity(getdetailactivity);

        }else{
            Toast.makeText(this, "Ingresa Registro -Vuelo -Origen -Destino", Toast.LENGTH_SHORT).show();
        }


    }

    //llama la pagina RadioActivity
    public void GetRadioPage(View view){
        cargarActivity();
        //codogo para mostrar los datos en el registro de combustible DetailActivity

        //codogo para mostrar los datos en el registro de combustible DetailActivity
        selRegistro = etRegistro.getText().toString();
        NumeroVuelo = etNumVuelo.getText().toString();
        desde = etDesde.getText().toString();
        hacia = etHacia.getText().toString();
        Origen = etDesde.getText().toString();
        Destino = etHacia.getText().toString();
        numFAC = AeronaveFac.getSelectedItem().toString();
        Prendida_hor = et1.getText().toString();
        Prendida_min= et2.getText().toString();
        Apagada_hor = et3.getText().toString();
        Apagada_min = et4.getText().toString();
        Decolage_hor = et5.getText().toString();
        Decolage_min = et6.getText().toString();
        Aterrizaje_hor = et7.getText().toString();
        Aterrizaje_min = et8.getText().toString();
        Pax = etPax.getText().toString();
        Carga = etCarga.getText().toString();
        Fuel = etFuel.getText().toString();



    //verifica que los campos tengan datos para ingresar a la base de datos
        if( !selRegistro.isEmpty() && !NumeroVuelo.isEmpty() && !Origen.isEmpty()&& !Destino.isEmpty() && AeronaveFac.getSelectedItem().toString()!="Num FAC") {
            flag = 1;

            Intent getRadioActivity = new Intent(this, RadioActivity.class);
            startActivity(getRadioActivity);


        }else{
            Toast.makeText(this, "Ingresa Registro-FAC-Vuelo-Orig-Dest", Toast.LENGTH_SHORT).show();
        }



    }

   /////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //este metodo permite ver la fecha en dos digitos 01/01/2018
    private String twoDigits(int n) {
        return (n<=9) ? ("0"+n) : String.valueOf(n);
    }
//end
}
