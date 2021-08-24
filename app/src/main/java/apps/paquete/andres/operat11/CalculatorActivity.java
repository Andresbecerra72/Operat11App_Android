package apps.paquete.andres.operat11;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.andres.operat11.R;

import java.util.Date;

public class CalculatorActivity extends AppCompatActivity {
    //Se declaran las variables Graficas de la actividad de Diseño

    //variable graficas tipo TexView
    private TextView TotalCrew;
    private TextView TotalAircraft;
    private TextView TvVfr;
    private TextView TvIfr;
    private TextView Nocturno;
    //textView FUEL
    private TextView FuelIn;
    private TextView FuelOut;
    private TextView FuelTrip;
    private TextView Remain;

    //toggle Button Fuel
    private ToggleButton tgConverUnidades;
    private ToggleButton tgResulFuel;

    //variable graficas tipo EditText
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
    //FUEL
    private EditText et9;
    private EditText et10;
    private EditText et11;
    private EditText et12;
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
    //datos totales FUEL
    public int valortvFuelin = 0;
    public int valortvFuelout = 0;
    public int valortvFuelTrip = 0;
    public int valorRemain = 0;
    public int flag = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        //esta linea de codigo evita la rotacion de la patanlla
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Se conectan las variables logicas con la interface grafica
        //objetos TexView
        Nocturno = (TextView) findViewById(R.id.tvNocturno);
        TvIfr = (TextView) findViewById(R.id.tvIfr);
        TvVfr = (TextView) findViewById(R.id.tvVfr);
        TotalAircraft = (TextView) findViewById(R.id.tvTotalAircraft);
        TotalCrew = (TextView) findViewById(R.id.tvTotalCrew);
        //objetos TextView FUEL
        FuelIn = (TextView) findViewById(R.id.tvFuelin);
        FuelOut = (TextView) findViewById(R.id.tvFuelout);
        FuelTrip = (TextView) findViewById(R.id.tvFuelTrip);
        Remain = (TextView) findViewById(R.id.tvRemain);
        //objetos EditText CREW
        et1 = (EditText) findViewById(R.id.etTimeStartHour);
        et2 = (EditText) findViewById(R.id.etTimeStartMinutes);
        et3 = (EditText) findViewById(R.id.etTimeFinishHour);
        et4 = (EditText) findViewById(R.id.etTimeFinishMinutes);
        //objetos EditText AIRCRAFT
        et5 = (EditText) findViewById(R.id.etTimeStartHour2);
        et6 = (EditText) findViewById(R.id.etTimeStartMinutes2);
        et7 = (EditText) findViewById(R.id.etTimeFinishHour2);
        et8 = (EditText) findViewById(R.id.etTimeFinishMinutes2);
        //objetos EditText FUEL
        et9 = (EditText) findViewById(R.id.etfull);
        et10 = (EditText) findViewById(R.id.etTakeoffFuel);
        et11 = (EditText) findViewById(R.id.etLandingFuel);
        et12 = (EditText) findViewById(R.id.etShutdownFuel);
        //toggle Button Fuel
        tgConverUnidades = (ToggleButton) findViewById(R.id.tgConverUnidades);
        tgResulFuel = (ToggleButton) findViewById(R.id.tgResulFuel);

        //foco
        findViewById(R.id.etTimeStartHour).requestFocus();
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
        Button btn1crew = (Button) findViewById(R.id.startCrewButton);
        btn1crew.setEnabled(false);


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
            Button btn2crew = (Button) findViewById(R.id.finishCrewButton);
            btn2crew.setEnabled(false);


            //ingreso AUTOMATICO de parametros
            InsertCrew(null); //llama al metodo InsertCrew(null) con parametros null para que el boton ADD funciones correctamente
            calcOpera();

        } else { //si no hay datos en los edit text presenta este error

            Toast.makeText(CalculatorActivity.this, "Error: Missing Data", Toast.LENGTH_SHORT).show();
            TotalCrew.setText("null");
            TotalAircraft.setText("null");
            TvVfr.setText("null");
            TvIfr.setText("null");
            Nocturno.setText("null");
        }


    }


    //codigo para ingresar MANUALMENTE y AUTOMATICAMENTE los datos de vuelo de la tripulacion boton ADD
    public void InsertCrew(View view) {

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
                Toast.makeText(CalculatorActivity.this, "Error: Hour/Minutes set", Toast.LENGTH_SHORT).show();
                TotalCrew.setText("null");
                TotalAircraft.setText("null");
                TvVfr.setText("null");
                TvIfr.setText("null");
                Nocturno.setText("null");
            } else if (nroH2 == nroH1 && nroM2 < nroM1) {
                Toast.makeText(CalculatorActivity.this, "Error: Minutes set", Toast.LENGTH_SHORT).show();
                TotalCrew.setText("null");
            } else {//logica para el calculo de la diferencia hora inicial vs hora final
                if (ophours < 0 && opminut < 0) {//cuando el dato de Hora y minutos son NEGATIVOS -;-
                    //System.out.println("negativo - negativo");
                    totalHor = (23 - nroH1) + nroH2;
                    totalMin = (60 - nroM1) + nroM2;
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
                    //System.out.println("positivo = 0 - negativo");
                    ophours = ophours - 1;
                    totalMin = (60 - nroM1) + nroM2;
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
            Toast.makeText(CalculatorActivity.this, "Error: Missing Data", Toast.LENGTH_SHORT).show();
            TotalCrew.setText("null");
            TotalAircraft.setText("null");
            TvVfr.setText("null");
            TvIfr.setText("null");
            Nocturno.setText("null");
        }
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
        ImageButton btn1Aircraft = (ImageButton) findViewById(R.id.startAircraftButton);
        btn1Aircraft.setEnabled(false);

    }

    public void horaFinishAircraft(View view) {

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
            ImageButton btn2Aircraft = (ImageButton) findViewById(R.id.finishAircraftButton);
            btn2Aircraft.setEnabled(false);
            //ingreso AUTOMATICO de parametros
            InsertAircraft(null); //llama al metodo InsertAircraft(null) con parametros null para que el boton ADD funciones correctamente

        } else { //si no hay datos en los edit text presenta este error
            Toast.makeText(CalculatorActivity.this, "Error: Missing Data", Toast.LENGTH_SHORT).show();
            TotalCrew.setText("null");
            TotalAircraft.setText("null");
            TvVfr.setText("null");
            TvIfr.setText("null");
            Nocturno.setText("null");
        }


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
                Toast.makeText(CalculatorActivity.this, "Error: Hour/Minutes set", Toast.LENGTH_SHORT).show();
                TotalCrew.setText("null");
                TotalAircraft.setText("null");
                TvVfr.setText("null");
                TvIfr.setText("null");
                Nocturno.setText("null");
            } else if (nroHra2 == nroHra1 && nroMin2 < nroMin1) {
                Toast.makeText(CalculatorActivity.this, "Error: Minutes set", Toast.LENGTH_SHORT).show();
                TotalAircraft.setText("null");
            } else {//logica para el calculo de la diferencia hora inicial vs hora final
                if (ophours2 < 0 && opminut2 < 0) {//cuando el dato de Hora y minutos son NEGATIVOS -;-
                    //System.out.println("negativo - negativo");
                    totalHor2 = (23 - nroHra1) + nroHra2;
                    totalMin2 = (60 - nroMin1) + nroMin2;
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
            Toast.makeText(CalculatorActivity.this, "Error: Missing Data", Toast.LENGTH_SHORT).show();
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
//**************************************************************************************************************
    //metodos para hacer visibles los botones de ADD Manualmente los datos de Tripulacion y Aeronave

    public void setvisible1(View view) {
        Button btn1 = (Button) findViewById(R.id.btinsertCrew);
        btn1.setVisibility(View.VISIBLE);
    }


    //habilita nuevamente los botone CREW START - FINISH A/C START - FINISH
    public void enableButtons1(View view) {
        Button btn1Crew = (Button) findViewById(R.id.startCrewButton);
        btn1Crew.setEnabled(true);
        Button btn2Crew = (Button) findViewById(R.id.finishCrewButton);
        btn2Crew.setEnabled(true);
        ImageButton btn1Aircraft = (ImageButton) findViewById(R.id.startAircraftButton);
        btn1Aircraft.setEnabled(true);
        ImageButton btn2Aircraft = (ImageButton) findViewById(R.id.finishAircraftButton);
        btn2Aircraft.setEnabled(true);

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
                    totalNoc = "0" + String.valueOf(difhours) + ":" + String.valueOf(totalMin2);
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
            TvIfr.setText("--:--");


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
            //System.out.println("DATA3 RH");
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
            TvIfr.setText("--:--");


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
            TvVfr.setText("--:--");
            TvIfr.setText("--:--");
            // condicion solo diurno datos entre las 06:00 y las 18:00*************************SOLO DIURNO************************************************************************************
        } else if (((hora1crew >= 6) && (hora1crew < 18)) && ((hora2crew >= 6) && (hora2crew < 18)) && ((hora1Aircraft >= 6) && (hora1Aircraft < 18)) && ((hora2Aircraft >= 6) && (hora2Aircraft < 18))) {
            //calculo Nocturno//////////////////////////////////////////////////////////////
            //System.out.println("SOLO DIURNO");
            Nocturno.setText("--:--");
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
                if (vfr <= 9){
                    totalVfr = "00:0" + String.valueOf(vfr);
                    TvVfr.setText(totalVfr);
                }else{
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

            et9.setText("");
            et10.setText("");
            et11.setText("");
            et12.setText("");
            FuelOut.setText("00");
            FuelIn.setText("00");
            FuelTrip.setText("00");
            Remain.setText("00");
        }


    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //metodo para hacer la conversion de las unidades
    public void ConversionUnidades(View view) {
        //este codigo muestra el cambio de unidades al presionar el toggle buttom
        if (tgConverUnidades.isChecked()) {
            Toast.makeText(CalculatorActivity.this, "Kilogram", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(CalculatorActivity.this, "Pounds", Toast.LENGTH_SHORT).show();
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
                //mostrar resultados
                FuelOut.setText(String.valueOf(fuelOut) + " KG");
                FuelIn.setText(String.valueOf(fuelIn) + " KG");
                FuelTrip.setText(String.valueOf(fuelTrip) + " KG");
                Remain.setText(dataF4 + " KG");

                //pasa los datos para la coversion de unidades
                valortvFuelout = fuelOut;
                valortvFuelin = fuelIn;
                valortvFuelTrip = fuelTrip;
                valorRemain = engineOff;


            } else {
                Toast.makeText(CalculatorActivity.this, "Error: Data", Toast.LENGTH_SHORT).show();
                FuelOut.setText("---");
                FuelIn.setText("---");
                FuelTrip.setText("---");
                Remain.setText("---");
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

                //pasa los datos para la coversion de unidades
                valortvFuelout = fuelOut;
                valortvFuelTrip = fuelTrip;



            } else {
                Toast.makeText(CalculatorActivity.this, "Error: Data", Toast.LENGTH_SHORT).show();
                FuelOut.setText("---");
                FuelIn.setText("---");
                FuelTrip.setText("---");
                Remain.setText("---");
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
                Toast.makeText(CalculatorActivity.this, "Error: Data", Toast.LENGTH_SHORT).show();
                FuelOut.setText("---");
                FuelIn.setText("---");
                FuelTrip.setText("---");
                Remain.setText("---");
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

                //pasa los datos para la coversion de unidades
                valortvFuelin = fuelIn;
                valortvFuelTrip = fuelTrip;
                valorRemain = engineOff;


            } else {
                Toast.makeText(CalculatorActivity.this, "Error: Data", Toast.LENGTH_SHORT).show();
                FuelOut.setText("---");
                FuelIn.setText("---");
                FuelTrip.setText("---");
                Remain.setText("---");
            }


            //condicion solo uando esta los datos de cantidad y fuel TO 1 0 1 1
        } else if ((et9.getText().toString().length() != 0) && (et10.getText().toString().isEmpty()) && (et11.getText().toString().length() != 0) && (et12.getText().toString().length() != 0)) {
            //System.out.println("1 0 1 1");
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

                //pasa los datos para la coversion de unidades
                valortvFuelin = fuelIn;
                valorRemain = engineOff;


            } else {
                Toast.makeText(CalculatorActivity.this, "Error: Data", Toast.LENGTH_SHORT).show();
                FuelOut.setText("---");
                FuelIn.setText("---");
                FuelTrip.setText("---");
                Remain.setText("---");
            }


            //condicion solo uando esta los datos de cantidad y fuel TO 1 1 0 1
        } else if ((et9.getText().toString().length() != 0) && (et10.getText().toString().length() != 0) && (et11.getText().toString().isEmpty()) && (et12.getText().toString().length() != 0)) {
            //System.out.println("1 1 0 1");
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
                Remain.setText(dataF4);

                //pasa los datos para la coversion de unidades
                valortvFuelout = fuelOut;


            } else {
                Toast.makeText(CalculatorActivity.this, "Error: Data", Toast.LENGTH_SHORT).show();
                FuelOut.setText("---");
                FuelIn.setText("---");
                FuelTrip.setText("---");
                Remain.setText("---");
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

                //pasa los datos para la coversion de unidades
                valortvFuelTrip = fuelTrip;



            } else {
                Toast.makeText(CalculatorActivity.this, "Error: Data", Toast.LENGTH_SHORT).show();
                FuelOut.setText("---");
                FuelIn.setText("---");
                FuelTrip.setText("---");
                Remain.setText("---");
            }


        } else {
            Toast.makeText(CalculatorActivity.this, "Error: Missing Data", Toast.LENGTH_SHORT).show();
            FuelOut.setText("---");
            FuelIn.setText("---");
            FuelTrip.setText("---");
            Remain.setText("---");
        }


    }else {//////////***********************************CAMBIO DEL BOTON DE CONVERSION DE UNIDADES ******************************

        flag = 3; //indica que estamos trabajando en LIBRAS
        System.out.println("no check");
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
                //mostrar resultados
                FuelOut.setText(String.valueOf(fuelOut) + " LBS");
                FuelIn.setText(String.valueOf(fuelIn) + " LBS");
                FuelTrip.setText(String.valueOf(fuelTrip) + " LBS");
                Remain.setText(dataF4 + " LBS");

                //pasa los datos para la conversion de unidades
                valortvFuelout = fuelOut;
                valortvFuelin = fuelIn;
                valortvFuelTrip = fuelTrip;
                valorRemain = engineOff;

            } else {
                Toast.makeText(CalculatorActivity.this, "Error: Data", Toast.LENGTH_SHORT).show();
                FuelOut.setText("---");
                FuelIn.setText("---");
                FuelTrip.setText("---");
                Remain.setText("---");
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

                //pasa los datos para la conversion de unidades
                valortvFuelout = fuelOut;
                valortvFuelTrip = fuelTrip;



            } else {
                Toast.makeText(CalculatorActivity.this, "Error: Data", Toast.LENGTH_SHORT).show();
                FuelOut.setText("---");
                FuelIn.setText("---");
                FuelTrip.setText("---");
                Remain.setText("---");
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

                //pasa los datos para la conversion de unidades
                valortvFuelout = fuelOut;



            } else {
                Toast.makeText(CalculatorActivity.this, "Error: Data", Toast.LENGTH_SHORT).show();
                FuelOut.setText("---");
                FuelIn.setText("---");
                FuelTrip.setText("---");
                Remain.setText("---");
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

                //pasa los datos para la coversion de unidades
                valortvFuelin = fuelIn;
                valortvFuelTrip = fuelTrip;
                valorRemain = engineOff;


            } else {
                Toast.makeText(CalculatorActivity.this, "Error: Data", Toast.LENGTH_SHORT).show();
                FuelOut.setText("---");
                FuelIn.setText("---");
                FuelTrip.setText("---");
                Remain.setText("---");
            }


            //condicion solo uando esta los datos de cantidad y fuel TO 1 0 1 1
        } else if ((et9.getText().toString().length() != 0) && (et10.getText().toString().isEmpty()) && (et11.getText().toString().length() != 0) && (et12.getText().toString().length() != 0)) {
            //System.out.println("1 0 1 1");
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

                //pasa los datos para la coversion de unidades
                valortvFuelin = fuelIn;
                valorRemain = engineOff;


            } else {
                Toast.makeText(CalculatorActivity.this, "Error: Data", Toast.LENGTH_SHORT).show();
                FuelOut.setText("---");
                FuelIn.setText("---");
                FuelTrip.setText("---");
                Remain.setText("---");
            }


            //condicion solo uando esta los datos de cantidad y fuel TO 1 1 0 1
        } else if ((et9.getText().toString().length() != 0) && (et10.getText().toString().length() != 0) && (et11.getText().toString().isEmpty()) && (et12.getText().toString().length() != 0)) {
            //System.out.println("1 1 0 1");
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
                Remain.setText(dataF4);

                //pasa los datos para la coversion de unidades
                valortvFuelout = fuelOut;



            } else {
                Toast.makeText(CalculatorActivity.this, "Error: Data", Toast.LENGTH_SHORT).show();
                FuelOut.setText("---");
                FuelIn.setText("---");
                FuelTrip.setText("---");
                Remain.setText("---");
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

                //pasa los datos para la coversion de unidades
                valortvFuelTrip = fuelTrip;



            } else {
                Toast.makeText(CalculatorActivity.this, "Error: Data", Toast.LENGTH_SHORT).show();
                FuelOut.setText("---");
                FuelIn.setText("---");
                FuelTrip.setText("---");
                Remain.setText("---");
            }


        } else {
            Toast.makeText(CalculatorActivity.this, "Error: Missing Data", Toast.LENGTH_SHORT).show();
            FuelOut.setText("---");
            FuelIn.setText("---");
            FuelTrip.setText("---");
            Remain.setText("---");
        }

    }

}






}

