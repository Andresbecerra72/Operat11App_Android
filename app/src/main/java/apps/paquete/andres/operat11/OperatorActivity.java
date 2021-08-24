package apps.paquete.andres.operat11;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andres.operat11.R;

public class OperatorActivity extends AppCompatActivity {
    private Button zero;
    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Button eight;
    private Button nine;
    private Button add;
    private Button sub;
    private Button dosPuntos;
    private Button limpiar;
    private Button equal;
    private Button clear;
    private TextView dia;
    private TextView hora1;
    private TextView hora2;
    private TextView result;
    private TextView signo;
    private final char ADDITION = '+';
    private final char SUBTRACTION = '-';
    private final char MULTIPLICATION = '*';
    private final char DIVISION = '/';
    private final char EQU = 0;
    private int val1 = 0;
    private int val2 = 0;
    private char ACTION;
    private int flag = 0;
    private int bandera = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator);
//esta linea de codigo evita la rotacion de la patanlla
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //poner icono en el action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);


        //VARIABLES
        zero = (Button)findViewById(R.id.button0);
        one = (Button)findViewById(R.id.button1);
        two = (Button)findViewById(R.id.button2);
        three = (Button)findViewById(R.id.button3);
        four = (Button)findViewById(R.id.button4);
        five = (Button)findViewById(R.id.button5);
        six = (Button)findViewById(R.id.button6);
        seven = (Button)findViewById(R.id.button7);
        eight = (Button)findViewById(R.id.button8);
        nine = (Button)findViewById(R.id.button9);
        add = (Button)findViewById(R.id.buttonSuma);
        sub = (Button)findViewById(R.id.buttonResta);
        dosPuntos = (Button)findViewById(R.id.buttonDosPuntos);
        limpiar = (Button)findViewById(R.id.button00);
        equal = (Button)findViewById(R.id.buttonIgual);
        clear = (Button)findViewById(R.id.buttonC);
        hora1 = (TextView)findViewById(R.id.tv1);
        hora2 = (TextView)findViewById(R.id.tv3);
        dia = (TextView)findViewById(R.id.tv2);
        result = (TextView)findViewById(R.id.tvResultado);
        signo = (TextView)findViewById(R.id.tvSigno);



//variables de accion para los botones de los numeros
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //System.out.println(valH1);

                if(flag == 0){
                    hora1.setText(hora1.getText().toString() + "0");
                }else {
                    hora2.setText(hora2.getText().toString() + "0");
                    // flag = 0;
                }
            }
        });

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //info.setText(info.getText().toString() + "1");
                if(flag == 0){
                    hora1.setText(hora1.getText().toString() + "1");
                }else {
                    hora2.setText(hora2.getText().toString() + "1");
                    //flag = 0;
                }


            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //info.setText(info.getText().toString() + "2");
                if(flag == 0){
                    hora1.setText(hora1.getText().toString() + "2");
                }else {
                    hora2.setText(hora2.getText().toString() + "2");
                    //flag = 0;
                }
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // info.setText(info.getText().toString() + "3");
                if(flag == 0){
                    hora1.setText(hora1.getText().toString() + "3");
                }else {
                    hora2.setText(hora2.getText().toString() + "3");
                    //flag = 0;
                }
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //info.setText(info.getText().toString() + "4");
                if(flag == 0){
                    hora1.setText(hora1.getText().toString() + "4");
                }else {
                    hora2.setText(hora2.getText().toString() + "4");
                    // flag = 0;
                }
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //info.setText(info.getText().toString() + "5");
                if(flag == 0){
                    hora1.setText(hora1.getText().toString() + "5");
                }else {
                    hora2.setText(hora2.getText().toString() + "5");
                    //flag = 0;
                }
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //info.setText(info.getText().toString() + "6");
                if(flag == 0){
                    hora1.setText(hora1.getText().toString() + "6");
                }else {
                    hora2.setText(hora2.getText().toString() + "6");
                    //flag = 0;
                }
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //info.setText(info.getText().toString() + "7");
                if(flag == 0){
                    hora1.setText(hora1.getText().toString() + "7");
                }else {
                    hora2.setText(hora2.getText().toString() + "7");
                    // flag = 0;
                }
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //info.setText(info.getText().toString() + "8");
                if(flag == 0){
                    hora1.setText(hora1.getText().toString() + "8");
                }else {
                    hora2.setText(hora2.getText().toString() + "8");
                    //flag = 0;
                }
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //info.setText(info.getText().toString() + "9");
                if(flag == 0){
                    hora1.setText(hora1.getText().toString() + "9");
                }else {
                    hora2.setText(hora2.getText().toString() + "9");
                    //flag = 0;
                }
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //accion del boton restar

                if (hora1.getText().length() > 0) {
                    flag = 1;
                    add.setEnabled(false);
                    sub.setEnabled(false);

                    if(bandera == 1){//codigo para organizar los minutos
                        if(hora1.getText().length() == 4){
                            String valorHora1 = hora1.getText().toString();
                            String valH0 = String.valueOf(valorHora1.charAt(0));
                            String valH1 = String.valueOf(valorHora1.charAt(1));
                            String valH2 = String.valueOf(valorHora1.charAt(2));
                            int valH3 = Integer.parseInt(String.valueOf(valorHora1.charAt(3)));
                            if(valH3 <= 9){

                                hora1.setText(valH0 + valH1 + valH2 + "0" + valH3);
                                //compute();
                                ACTION = SUBTRACTION;
                                signo.setText("-");
                                dosPuntos.setEnabled(true);
                                //bandera = 0;

                            }

                        }else if (hora1.getText().length() == 3) {
                            hora1.setText(hora1.getText().toString() + "00");
                            //compute();
                            ACTION = SUBTRACTION;
                            signo.setText("-");
                            dosPuntos.setEnabled(true);
                            //bandera = 0;


                        } else {
                            // compute();
                            ACTION = SUBTRACTION;
                            signo.setText("-");
                            dosPuntos.setEnabled(true);
                            //bandera = 0;

                        }


                    }else{
                        //compute();
                        ACTION = SUBTRACTION;
                        signo.setText("-");
                        dosPuntos.setEnabled(true);

                    }


                }else{
                    signo.setText("Err");

                }


            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //accion de boton sumar

                if (hora1.getText().length() > 0) {

                    flag = 1;
                    add.setEnabled(false);
                    sub.setEnabled(false);

                    if (bandera == 1) {//codigo para organizar los minutos "se presiona boton dos punto"
                        if (hora1.getText().length() == 4) {
                            String valorHora1 = hora1.getText().toString();
                            String valH0 = String.valueOf(valorHora1.charAt(0));
                            String valH1 = String.valueOf(valorHora1.charAt(1));
                            String valH2 = String.valueOf(valorHora1.charAt(2));
                            int valH3 = Integer.parseInt(String.valueOf(valorHora1.charAt(3)));
                            if (valH3 <= 9) {

                                hora1.setText(valH0 + valH1 + valH2 + "0" + valH3);
                                //compute();
                                ACTION = ADDITION;
                                signo.setText("+");
                                dosPuntos.setEnabled(true);
                                //bandera = 0;

                            }

                        }else if (hora1.getText().length() == 3) {
                            hora1.setText(hora1.getText().toString() + "00");
                            //compute();
                            ACTION = ADDITION;
                            signo.setText("+");
                            dosPuntos.setEnabled(true);
                            //bandera = 0;


                        } else {
                            //compute();
                            ACTION = ADDITION;
                            signo.setText("+");
                            dosPuntos.setEnabled(true);
                            //bandera = 0;

                        }


                    } else {
                        // compute();
                        ACTION = ADDITION;
                        signo.setText("+");
                        dosPuntos.setEnabled(true);

                    }

                }else {
                    signo.setText("Err");

                }



            }
        });


        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //accion del boton igual



                if(bandera == 1){//codigo para organizar los minutos
                    if(hora2.getText().length() == 4){
                        String valorHora2 = hora2.getText().toString();
                        String valH0 = String.valueOf(valorHora2.charAt(0));
                        String valH1 = String.valueOf(valorHora2.charAt(1));
                        String valH2 = String.valueOf(valorHora2.charAt(2));
                        int valH3 = Integer.parseInt(String.valueOf(valorHora2.charAt(3)));
                        if(valH3 <= 9){

                            hora2.setText(valH0 + valH1 + valH2 + "0" + valH3);
                            compute();
                            ACTION = EQU;
                            // result.setText(result.getText().toString() + String.valueOf(val2) + "=" + String.valueOf(val1));
                            // 5 + 4 = 9
                            //info.setText(null);
                            bandera = 0;
                            //flag = 0;

                        }

                    }else if(hora2.getText().length() == 3){

                        hora2.setText(hora2.getText().toString() + "00");
                        compute();
                        ACTION = EQU;
                        // result.setText(result.getText().toString() + String.valueOf(val2) + "=" + String.valueOf(val1));
                        // 5 + 4 = 9
                        //info.setText(null);
                        bandera = 0;
                        //flag = 0;


                    } else{
                        compute();
                        ACTION = EQU;
                        // result.setText(result.getText().toString() + String.valueOf(val2) + "=" + String.valueOf(val1));
                        // 5 + 4 = 9
                        //info.setText(null);
                        bandera = 0;
                        //flag = 0;

                    }


                }else{
                    compute();
                    ACTION = EQU;
                    //  flag = 0;
                    // result.setText(result.getText().toString() + String.valueOf(val2) + "=" + String.valueOf(val1));
                    // 5 + 4 = 9
                    //info.setText(null);

                }




            }
        });

        clear.setOnClickListener(new View.OnClickListener() {//borra todos los datos
            @Override
            public void onClick(View v) { //accion del boton C

                if((bandera == 1) || (flag == 1)){
                    hora1.setText(null);
                    hora2.setText(null);
                    result.setText(null);
                    hora1.setHint("00:00");
                    hora2.setHint("00:00");
                    result.setHint("00:00");
                    signo.setText("C");
                    dosPuntos.setEnabled(true);
                    hora1.setVisibility(View.VISIBLE);
                    hora2.setVisibility(View.VISIBLE);
                    result.setVisibility(View.VISIBLE);
                    dia.setVisibility(View.INVISIBLE);
                    add.setEnabled(true);
                    sub.setEnabled(true);
                    flag = 0;
                    bandera = 0;

                }else {

                    hora1.setText(null);
                    hora2.setText(null);
                    result.setText(null);
                    hora1.setHint("00:00");
                    hora2.setHint("00:00");
                    result.setHint("00:00");
                    signo.setText("C");
                    dosPuntos.setEnabled(true);
                    add.setEnabled(true);
                    sub.setEnabled(true);
                    dia.setVisibility(View.INVISIBLE);
                    flag = 0;

                }



            }
        });


        dosPuntos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //accion del boton dos puntos
                //info.setText(info.getText().toString() + ":");

                bandera = 1;

                if(flag == 0 && hora1.getText().length() == 1){
                    String valorHora1 = hora1.getText().toString();
                    int valH1 = Integer.parseInt(valorHora1);

                    if(valH1 < 10){
                        hora1.setText("0" + hora1.getText().toString() + ":");
                        dosPuntos.setEnabled(false);
                    }else {
                        hora1.setText(hora1.getText().toString() + ":");
                        dosPuntos.setEnabled(false);
                    }
                }else if(flag == 1 && hora2.getText().length() == 1) {

                    String valorHora2 = hora2.getText().toString();
                    int valH2 = Integer.parseInt(valorHora2);

                    if (valH2 < 10) {
                        hora2.setText("0" + hora2.getText().toString() + ":");
                        dosPuntos.setEnabled(false);
                    } else {
                        hora2.setText(hora2.getText().toString() + ":");
                        dosPuntos.setEnabled(false);
                    }


                }else if(flag == 0 && hora1.getText().length() == 2) {

                    hora1.setText(hora1.getText().toString() + ":");
                    dosPuntos.setEnabled(false);


                } else if(flag == 1 && hora2.getText().length() == 2) {

                    hora2.setText(hora2.getText().toString() + ":");
                    dosPuntos.setEnabled(false);


                }else{

                    signo.setText("Error");
                    hora1.setVisibility(View.INVISIBLE);
                    hora2.setVisibility(View.INVISIBLE);
                    result.setVisibility(View.INVISIBLE);
                }



            }
        });

        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // accion del boton CR

                if(flag == 0){
                    if(hora1.getText().length() > 0){
                        CharSequence name = hora1.getText().toString();
                        hora1.setText(name.subSequence(0, name.length()-1));
                    }
                    else{
                        //  val1 = Double.NaN;
                        //  val2 = Double.NaN;
                        hora1.setText(null);
                        result.setText(null);
                    }
                }else {
                    if(hora2.getText().length() > 0){
                        CharSequence name = hora2.getText().toString();
                        hora2.setText(name.subSequence(0, name.length()-1));
                    }
                    else{
                        //  val1 = Double.NaN;
                        //  val2 = Double.NaN;
                        hora2.setText(null);
                        result.setText(null);
                    }

                }
            }
        });

    }

    //metodos:


    private void compute(){// operaciones de la calculadora


        if (bandera == 1) {//condicion para hacer las operaciones de las horas

            if(hora1.length() == 5 && hora2.length() == 5) {

                //se capturan los valores de las horas ingresadas
                String valorHora1 = hora1.getText().toString();
                int hora1Valor0 = Integer.parseInt(String.valueOf(valorHora1.charAt(0)));
                int hora1Valor1 = Integer.parseInt(String.valueOf(valorHora1.charAt(1)));
                int min1Valor0 = Integer.parseInt(String.valueOf(valorHora1.charAt(3)));
                int min1Valor1 = Integer.parseInt(String.valueOf(valorHora1.charAt(4)));
                String valorHora2 = hora2.getText().toString();
                int hora2Valor0 = Integer.parseInt(String.valueOf(valorHora2.charAt(0)));
                int hora2Valor1 = Integer.parseInt(String.valueOf(valorHora2.charAt(1)));
                int min2Valor0 = Integer.parseInt(String.valueOf(valorHora2.charAt(3)));
                int min2Valor1 = Integer.parseInt(String.valueOf(valorHora2.charAt(4)));

                int horaInicial = (hora1Valor0 * 10) + hora1Valor1;
                int minInicial = (min1Valor0 * 10) + min1Valor1;

                int horaFinal = (hora2Valor0 * 10) + hora2Valor1;
                int minFinal = (min2Valor0 * 10) + min2Valor1;

                switch (ACTION) {
                    case ADDITION://***********SUMA DE HORAS*************

                        val1 = horaInicial + horaFinal;
                        val2 = minInicial + minFinal;
                        int d = 0;



                        if (val2 >= 59 || val1 >= 24) {

                            while (val2 > 59) {

                                val2 = val2 - 60;
                                val1 = val1 + 1;
                            }

                            while (val1 > 23){
                                val1 = val1 - 24;
                                d = d + 1;
                            }

                            if (d > 0){
                                dia.setVisibility(View.VISIBLE);
                                dia.setText("Days: " + d);
                            }
                            //mejora la presentacion con datos menores a 10
                            if (val1 <= 9 && val2 <= 9) {
                                result.setText("= 0" + val1 + ":0" + val2);
                            } else if (val1 <= 9 && val2 > 9) {
                                result.setText("= 0" + val1 + ":" + val2);
                            } else if (val1 > 9 && val2 <= 9) {
                                result.setText("= " + val1 + ":0" + val2);
                            } else {
                                result.setText("= " + val1 + ":" + val2);
                            }


                        } else {

                            //mejora la presentacion con datos menores a 10
                            if (val1 <= 9 && val2 <= 9) {
                                result.setText("= 0" + val1 + ":0" + val2);
                            } else if (val1 <= 9 && val2 > 9) {
                                result.setText("= 0" + val1 + ":" + val2);
                            } else if (val1 > 9 && val2 <= 9) {
                                result.setText("= " + val1 + ":0" + val2);
                            } else {
                                result.setText("= " + val1 + ":" + val2);
                            }
                        }


                        break;
                    case SUBTRACTION://**********RESTA DE HORAS***********

                        val1 = horaInicial - horaFinal;
                        val2 = minInicial - minFinal;
                        d = 0;

                        if (val2 >= 59 || val1 < 0 || val2 < 0) {

                            while (val2 > 59) {
                                //System.out.println("while 1");
                                val2 = val2 - 60;
                                val1 = val1 - 1;

                            }
                            while (val1 < 0){
                                //System.out.println("while 2");
                                val1 = val1 + 24;
                                d = d - 1;
                            }

                            while (val2 < 0){
                                //System.out.println("while 3");
                                val2 = val2 + 60;
                                val1 = val1 - 1;
                            }


                            //System.out.println("Aqui es");
                            //System.out.println(val1+ " : "+val2);

                            //mejora la presentacion con datos menores a 10
                            if (val1 <= 9 && val1 >= 0 && val2 <= 9 && val2 >= 0) {//hora 0-9 minutos 0-9
                                //System.out.println("Aqui 0");

                                result.setText("= 0" + val1 + ":0" + val2);
                            } else if (val1 <= 9 && val1 >= 0 && val2 > 9) { //hora 0-9 minutos mayor 9
                                //System.out.println("Aqui 1");

                                result.setText("= 0" + val1 + ":" + val2);
                            } else if (val1 > 9 && val2 <= 9 && val2 >= 0) { //hora mayor 9 minutos 0-9
                                //System.out.println("Aqui 2");

                                result.setText("= " + val1 + ":0" + val2);
                            }  else if (val1 < 0 && val2 <= 9 && val2 >= 0) { //hora menor -0 minutos 0-9
                                //System.out.println("Aqui 3");

                                //val1 = val1 + 24;
                                dia.setVisibility(View.VISIBLE);
                                dia.setText("Days: " + d);
                                result.setText("= " + val1 + ":0" + val2);
                            }  else if (val1 <= 9 && val1 >= 0&& val2 < 0) { //hora 0-9 minutos menor -0
                                System.out.println("Aqui 4");

                                //val2= val2 + 60;
                                result.setText("= 0" + val1 + ":" + val2);
                            } else if (val1 < 0 && val2 > 9) {               //hora menor -0 minutos mayor 9
                                //System.out.println("Aqui 5");

                                // val1 = val1 + 24;
                                dia.setVisibility(View.VISIBLE);
                                dia.setText("Days: " + d);
                                result.setText("= " + val1 + ":" + val2);
                            }else if (val1 > 9 && val2 < 0) {               //hora mayor 9 minutos menor -0
                                //System.out.println("Aqui 6");

                                //val2= val2 + 60;
                                result.setText("= " + val1 + ":" + val2);
                            }else{                                        // hora menor -0 minutos menor -0
                                //System.out.println("Aqui 7");

                                //val1 = val1 + 24;
                                //val2= val2 + 60;
                                dia.setVisibility(View.VISIBLE);
                                dia.setText("Days: " + d);
                                result.setText("= " + val1 + ":" + val2);
                            }


                        } else {

                            //mejora la presentacion con datos menores a 10
                            if (val1 <= 9 && val1 >= 0 && val2 <= 9 && val2 >= 0) {
                                result.setText("= 0" + val1 + ":0" + val2);
                            } else if (val1 <= 9 && val1 >= 0 && val2 > 9) {
                                result.setText("= 0" + val1 + ":" + val2);
                            } else if (val1 > 9 && val2 <= 9 && val2 >= 0) {
                                result.setText("= " + val1 + ":0" + val2);
                            }  else if (val1 < 0 && val2 <= 9 && val2 >= 0) {
                                result.setText("= " + val1 + ":0" + val2);
                            }  else if (val1 <= 9 && val1 >= 0&& val2 < 0) {
                                result.setText("= 0" + val1 + ":" + val2);
                            } else {
                                result.setText("= " + val1 + ":" + val2);
                            }
                        }


                        break;
                    case MULTIPLICATION:
                        // val1 = val1 * val2;
                        break;
                    case DIVISION:
                        //  val1 = val1 / val2;
                        break;
                    case EQU:
                        break;
                }
            }else{

                Toast.makeText(this, "Error: Missing Data", Toast.LENGTH_SHORT).show();

            }
        }else if (bandera == 0) {// condicion cuando no se usan las horas

            switch(ACTION){
                case ADDITION://********SUMA DE NUMEROS*********

                    val1 = Integer.parseInt(hora1.getText().toString());
                    val2 = Integer.parseInt(hora2.getText().toString());
                    val1 = val1 + val2;
                    result.setText("= " + val1);

                    break;

                case SUBTRACTION://*****RESTA DE NUMEROS**********
                    val1 = Integer.parseInt(hora1.getText().toString());
                    val2 = Integer.parseInt(hora2.getText().toString());
                    val1 = val1 - val2;
                    result.setText("= " + val1);

                    break;

                case MULTIPLICATION:
                    // val1 = val1 * val2;
                    break;
                case DIVISION:
                    //  val1 = val1 / val2;
                    break;
                case EQU:
                    break;
            }

        }else {
            Toast.makeText(this, "Error: Missing Data", Toast.LENGTH_SHORT).show();

        }
    }


}
