package apps.paquete.andres.operat11;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.andres.operat11.R;

import java.util.ArrayList;
import java.util.Calendar;
import static apps.paquete.andres.operat11.ContadorActivity.CodeSemana;
import static apps.paquete.andres.operat11.ContadorActivity.CodeMes;
import static apps.paquete.andres.operat11.ContadorActivity.CodeAno;


import apps.paquete.andres.operat11.constantes.Constantes;

public class DisplayActivity extends AppCompatActivity {

    private TableLayout tableLayout;
    private EditText etOrdeVuelo;
    private Button btnEliminar;

    private String[] header = {"ID","Fecha","VUELO","TOTAL TRIP"};
    // private String[] header = {" "," "," "," "," "};

    ContadorActivity mainActivity = new ContadorActivity();
    DatosTabla datosTabla;

    //para conectar la base de datos
    AdminSQLiteOpenHelper admincount = new AdminSQLiteOpenHelper(this, "countBD", null, 1);//base de datos para el contador de horas




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        etOrdeVuelo = (EditText) findViewById(R.id.etOrdenVuelo);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);

        datosTabla = new DatosTabla(tableLayout, getApplicationContext());

        //foco
        findViewById(R.id.btnEliminar).requestFocusFromTouch();
        //datosTabla.addHeader(header1);
        //datosTabla.addDatos(getDatos());
        // datosTabla.backgroundHeader(R.color.colorPrimaryDark);
        datosTabla.createTable(header, getDatos());
        datosTabla.backgroundDatos(Color.LTGRAY, Color.GRAY, getDatos());
        setCeroCodes();




/////////////////////////////////
    }

    private void setCeroCodes() {
        CodeAno = 0;
        CodeMes = 0;
        CodeSemana = 0;
    }

    ////inicio de los metodos:
    private ArrayList<String[]> getDatos(){

        // rows.add(new String[]{"0","0","0","0","0"});

        int minTrip = 0;
        String minutos="";
        TablaContador numeroVuelo = null;
        ArrayList<String[]> rows = new ArrayList<>();
        ArrayList<TablaContador> registroVuelos = new ArrayList<TablaContador>();


        if (CodeAno !=0 && CodeMes == 0 && CodeSemana == 0){ ///condicion para consultar el año

            //System.out.println("A "+CodeAno + "/" + CodeMes + "/" + Code);

            //Codigo para efectuar la consulta a la base de datos
            Cursor cursor = admincount.getReadableDatabase().rawQuery("SELECT id, idvuelo, total_tripulacion_hor, total_tripulacion_min, day, month, year FROM " + Constantes.TABLA_CONTADOR + " WHERE year=" + CodeAno, null);
            //codigo para llenar las celdas en la tabla
            while (cursor.moveToNext()){
                numeroVuelo = new TablaContador();

                numeroVuelo.setIdCode(cursor.getString(0));
                numeroVuelo.setNumvuelo(cursor.getString(1));
                numeroVuelo.setTotal_tripulacion_hor(cursor.getInt(2));
                numeroVuelo.setTotal_tripulacion_min(cursor.getInt(3));
                numeroVuelo.setDay(cursor.getInt(4));
                numeroVuelo.setMonth(cursor.getString(5));
                numeroVuelo.setYear(cursor.getString(6));

                registroVuelos.add(numeroVuelo);

            }


            //llama todos los registros almacenados en la base de datos y lo spresenta en la tabla
            for(int i = 0; i < registroVuelos.size(); i++){

                minTrip = registroVuelos.get(i).getTotal_tripulacion_min();

                if (minTrip < 10) {
                    minutos = "0" + minTrip;

                }else{
                    minutos = String.valueOf(minTrip);
                }

                String hora = registroVuelos.get(i).getTotal_tripulacion_hor()+ ":" + minutos;
                String fecha = registroVuelos.get(i).getDay() + "/" + registroVuelos.get(i).getMonth() + "/" + registroVuelos.get(i).getYear();


                rows.add(new String[]{registroVuelos.get(i).getIdCode(), fecha, registroVuelos.get(i).getNumvuelo(), hora});

            }




        }else  if(CodeAno !=0 && CodeMes != 0 && CodeSemana == 0){ //condicion para consultar año y mes
            //System.out.println("M "+CodeAno + "/" + CodeMes + "/" + Code);

            //Codigo para efectuar la consulta a la base de datos
            Cursor cursor = admincount.getReadableDatabase().rawQuery("SELECT id, idvuelo, total_tripulacion_hor, total_tripulacion_min, day, month, year FROM " + Constantes.TABLA_CONTADOR + " WHERE month=" + CodeMes + " AND year=" +CodeAno, null);
            //codigo para llenar las celdas en la tabla
            while (cursor.moveToNext()){
                numeroVuelo = new TablaContador();

                numeroVuelo.setIdCode(cursor.getString(0));
                numeroVuelo.setNumvuelo(cursor.getString(1));
                numeroVuelo.setTotal_tripulacion_hor(cursor.getInt(2));
                numeroVuelo.setTotal_tripulacion_min(cursor.getInt(3));
                numeroVuelo.setDay(cursor.getInt(4));
                numeroVuelo.setMonth(cursor.getString(5));
                numeroVuelo.setYear(cursor.getString(6));

                registroVuelos.add(numeroVuelo);

            }



            //llama todos los registros almacenados en la base de datos y lo spresenta en la tabla
            for(int i = 0; i < registroVuelos.size(); i++){

                minTrip = registroVuelos.get(i).getTotal_tripulacion_min();

                if (minTrip < 10) {
                    minutos = "0" + minTrip;

                }else{
                    minutos = String.valueOf(minTrip);
                }

                String hora = registroVuelos.get(i).getTotal_tripulacion_hor()+ ":" + minutos;

                String fecha = registroVuelos.get(i).getDay() + "/" + registroVuelos.get(i).getMonth() + "/" + registroVuelos.get(i).getYear();



                rows.add(new String[]{registroVuelos.get(i).getIdCode(), fecha, registroVuelos.get(i).getNumvuelo(), hora});

            }



        }else if(CodeAno !=0 && CodeMes != 0 && CodeSemana > 0){//condicion para consultar año/mes/dia
            //System.out.println("D "+CodeAno + "/" + CodeMes + "/" + Code);


            //Codigo para efectuar la consulta a la base de datos
            Cursor cursor = admincount.getReadableDatabase().rawQuery("SELECT id, idvuelo, total_tripulacion_hor, total_tripulacion_min, day, month, year FROM " + Constantes.TABLA_CONTADOR + " WHERE week=" + CodeSemana + " AND month=" + CodeMes + " AND year=" + CodeAno, null);
            //codigo para llenar las celdas en la tabla
            while (cursor.moveToNext()){
                numeroVuelo = new TablaContador();

                numeroVuelo.setIdCode(cursor.getString(0));
                numeroVuelo.setNumvuelo(cursor.getString(1));
                numeroVuelo.setTotal_tripulacion_hor(cursor.getInt(2));
                numeroVuelo.setTotal_tripulacion_min(cursor.getInt(3));
                numeroVuelo.setDay(cursor.getInt(4));
                numeroVuelo.setMonth(cursor.getString(5));
                numeroVuelo.setYear(cursor.getString(6));

                registroVuelos.add(numeroVuelo);

            }



            //llama todos los registros almacenados en la base de datos y lo spresenta en la tabla
            for(int i = 0; i < registroVuelos.size(); i++){


                minTrip = registroVuelos.get(i).getTotal_tripulacion_min();

                if (minTrip < 10) {
                    minutos = "0" + minTrip;

                }else{
                    minutos = String.valueOf(minTrip);
                }

                String hora = registroVuelos.get(i).getTotal_tripulacion_hor()+ ":" + minutos;

                String fecha = registroVuelos.get(i).getDay() + "/" + registroVuelos.get(i).getMonth() + "/" + registroVuelos.get(i).getYear();



                rows.add(new String[]{registroVuelos.get(i).getIdCode(), fecha, registroVuelos.get(i).getNumvuelo(), hora});

            }



        }else {
            rows.add(new String[]{"0","0","0","0"});
            //System.out.println("aqui");
        }


        return rows;
    }


    ///////////////////////////////metodo para eliminar vuelo de la Base de datos
    public void Eliminar(View view){

//tableLayout.setVisibility(View.INVISIBLE);

        admincount.abrirBD();


        String idCode = etOrdeVuelo.getText().toString();

        if(!idCode.isEmpty()){


            int cantidad = admincount.EliminarDatosContador(idCode);//llama el medoto eliminar que devuelve un numero entero
            admincount.cerrarBD();



            if(cantidad == 1){

                Toast.makeText(this, "Vuelo Eliminado", Toast.LENGTH_SHORT).show();

                horasTotalesHoy();

            } else {
                Toast.makeText(this, "El Vuelo no existe", Toast.LENGTH_SHORT).show();

            }

        } else {
            Toast.makeText(this, "Debes de introducir el Num Vuelo", Toast.LENGTH_SHORT).show();

        }


    }

    private void horasTotalesHoy() {

        Intent intent = new Intent(this, ContadorActivity.class);

        //codigo para llamar los datos de la fecha
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+ 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);


        TablaContador numVueloTotal = null;
        ArrayList<TablaContador> Totales = new ArrayList<TablaContador>();

        //Codigo para efectuar la consulta a la base de datos
        Cursor cursor = admincount.getReadableDatabase().rawQuery("SELECT SUM(total_tripulacion_hor), SUM(total_tripulacion_min) FROM " + Constantes.TABLA_CONTADOR + " WHERE day=" + day + " AND month=" + month + " AND year=" + year, null);
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

        for(
                int i = 0; i<Totales.size();i++)

        {

            TotalTripHora = Totales.get(i).getTotal_tripulacion_hor();
            TotalTripMin = Totales.get(i).getTotal_tripulacion_min();


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

                intent.putExtra("dato", Total_Tripulacion);
                startActivity(intent);
                //mainActivity.tvHorasHoy.setText("►" +Total_Tripulacion+"◄");

            } else {
                Total_Tripulacion = String.valueOf(HorasTotales) + ":" + String.valueOf(MinTotales);
                intent.putExtra("dato", Total_Tripulacion);
                startActivity(intent);
                // mainActivity.tvHorasHoy.setText("►" +Total_Tripulacion+"◄");
            }
        }else

        {
            if (MinTotales <= 9) {//codigo para mejorar la presentacion de los minutos menores a 10
                Total_Tripulacion = String.valueOf(HorasTotales) + ":0" + String.valueOf(MinTotales);
                intent.putExtra("dato", Total_Tripulacion);
                startActivity(intent);
                // mainActivity.tvHorasHoy.setText("►" +Total_Tripulacion+"◄");
            } else {
                Total_Tripulacion = String.valueOf(HorasTotales) + ":" + String.valueOf(MinTotales);
                intent.putExtra("dato", Total_Tripulacion);
                startActivity(intent);
                // mainActivity.tvHorasHoy.setText("►" + Total_Tripulacion +"◄");
            }

        }
    }


}
