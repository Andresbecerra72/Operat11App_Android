package apps.paquete.andres.operat11;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;


import apps.paquete.andres.operat11.constantes.Constantes;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    Vuelos RegVuelo = new Vuelos();//codigo para llamar metodos de la clase Vuelos
    TotalesVuelos TotalesVuelos = new TotalesVuelos(); //codigo para llamar metodos de la clase TotalVuelos
    FuelRegistro fuelRegistro = new FuelRegistro();//codigo para llamar metodos de la clase FuelRegistro
    TablaRadio tablaRadio = new TablaRadio();//codigo para llamar metodos de la clase TablaRadio
    TablaContador tablaContador = new TablaContador();//codigo para llamar metodos de la clase TablaCOntador

    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version); //parametros de la base de datos
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        //se crea la tabla
        BaseDeDatos.execSQL(Constantes.CREAR_TABLA_VUELOS); //parametros de la tabla VUELOS se encuentran en el paquete constantes
        BaseDeDatos.execSQL(Constantes.CREAR_TABLA_TOTALES);//parametros de la tabla TOTALES se encuentran en el paquete constantes
        BaseDeDatos.execSQL(Constantes.CREAR_TABLA_FUEL);//parametros de la tabla FUEL se encuentran en el paquete constantes
        BaseDeDatos.execSQL(Constantes.CREAR_TABLA_RADIO);//parametros de la tabla RADIO se encuentran en el paquete constantes
        BaseDeDatos.execSQL(Constantes.CREAR_TABLA_CONTADOR);//parametros de la tabla CONTADOR se encuentran en el paquete constantes

    }

    @Override
    public void onUpgrade(SQLiteDatabase BaseDeDatos, int versionAntigua, int versionNueva) {

        if(versionAntigua < versionNueva)  {
            BaseDeDatos.execSQL("DROP TABLE IF EXISTS bitacora");
            BaseDeDatos.execSQL("DROP TABLE IF EXISTS totales");
            BaseDeDatos.execSQL("DROP TABLE IF EXISTS fuel");
            BaseDeDatos.execSQL("DROP TABLE IF EXISTS radio");
            BaseDeDatos.execSQL("DROP TABLE IF EXISTS contador");
            onCreate(BaseDeDatos);

        }

    }


    ////////////abrir base de datos

    public void abrirBD(){
        this.getWritableDatabase();

    }

    ////////////////cerrar base de datos

    public void cerrarBD(){
        this.close();
    }

////////////////////////
//METODO PARA INSERTAR DATOS EN LA BASE DE DATOS------------------------------------------
public void IngresarDatos(){

    FullActivity fullActivity = new FullActivity();
    DetailActivity detailActivity = new DetailActivity();
    RadioActivity radioActivity = new RadioActivity();

        //este codigo ingresa el valor del ususario del formulario Fullactivity en la clase Vuelos ***TABLA VUELOS
    RegVuelo.setNumvuelo(fullActivity.numvuelo);
    RegVuelo.setDesde(fullActivity.desde);
    RegVuelo.setHacia(fullActivity.hacia);
    RegVuelo.setPrendida_hor(fullActivity.Prendida_hor);
    RegVuelo.setPrendida_min(fullActivity.Prendida_min);
    RegVuelo.setApagada_hor(fullActivity.Apagada_hor);
    RegVuelo.setApagada_min(fullActivity.Apagada_min);
    RegVuelo.setVfr(fullActivity.Vfr);
    RegVuelo.setIfr(fullActivity.Ifr);
    RegVuelo.setNocturno(fullActivity.Noct);
    RegVuelo.setTotal_crew(fullActivity.Total_crew);
    RegVuelo.setDecolage_hor(fullActivity.Decolage_hor);
    RegVuelo.setDecolage_min(fullActivity.Decolage_min);
    RegVuelo.setAterrizaje_hor(fullActivity.Aterrizaje_hor);
    RegVuelo.setAterrizaje_min(fullActivity.Aterrizaje_min);
    RegVuelo.setTotal_aircraft(fullActivity.Total_aircraft);
    RegVuelo.setNum_landing(fullActivity.Ciclos);
    RegVuelo.setPax(fullActivity.Pax);
    RegVuelo.setCarga(fullActivity.Carga);
    RegVuelo.setFuel(fullActivity.Fuel);

    //este codigo ingresa el valor del ususario del formulario Fullactivity en la clase TotalesVuelos ***TABLA TOTALES
    TotalesVuelos.setNumvuelo(fullActivity.numvuelo);
    TotalesVuelos.setTotal_tripulacion_hor(fullActivity.Total_tripulacion_hor);
    TotalesVuelos.setTotal_tripulacion_min(fullActivity.Total_tripulacion_min);
    TotalesVuelos.setTotal_aeronave_hor(fullActivity.Total_aeronave_hor);
    TotalesVuelos.setTotal_aeronave_min(fullActivity.Total_aeronave_min);
    TotalesVuelos.setVfrHora(fullActivity.Total_Vfr_Hora);
    TotalesVuelos.setVfrMin(fullActivity.Total_Vfr_Min);
    TotalesVuelos.setIfrHora(fullActivity.Total_Ifr_Hora);
    TotalesVuelos.setIfrMin(fullActivity.Total_Ifr_Min);
    TotalesVuelos.setNocturnoHora(fullActivity.Total_Nocturno_Hora);
    TotalesVuelos.setNocturnoMin(fullActivity.Total_Nocturno_Min);
    TotalesVuelos.setPax(fullActivity.Total_Pax);
    TotalesVuelos.setNum_landing(fullActivity.Total_Ciclos);
    TotalesVuelos.setFuel(fullActivity.Total_Fuel);
    TotalesVuelos.setCarga(fullActivity.Total_Carga);



    ///este codigo ingresa el valor del ususario del formulario DetailActivity en la clase FuelRegistro ***TABLA FUEL
    fuelRegistro.setTow(detailActivity.Tow);
    fuelRegistro.setAltura(detailActivity.Alt);
    fuelRegistro.setVelocidad(detailActivity.Vel);
    fuelRegistro.setFf_eng1(detailActivity.Engi1);
    fuelRegistro.setFf_eng2(detailActivity.Engi2);
    fuelRegistro.setFuel_min(detailActivity.FuelMin);
    fuelRegistro.setFuel_tax_out(detailActivity.FuelOUT);
    fuelRegistro.setFuel_tax_in(detailActivity.FuelIN);
    fuelRegistro.setTrip_fuel(detailActivity.FuelTRIP);
    fuelRegistro.setFuel_reman(detailActivity.FuelREMAN);

    //este codigo ingresa el valor del ususario del formulario RadioActivity en la clase TablaRadio ***TABLA RADIO
    //tablaRadio.setNumvuelo(fullActivity.numvuelo);
    tablaRadio.setNumFAC(radioActivity.numfac);
    tablaRadio.setPuertas_hor(radioActivity.Puertas_hor);
    tablaRadio.setPuertas_min(radioActivity.Puertas_min);
    tablaRadio.setPrendida_hor(radioActivity.Prendida_hor);
    tablaRadio.setPrendida_min(radioActivity.Prendida_min);
    tablaRadio.setApagada_hor(radioActivity.Apagada_hor);
    tablaRadio.setApagada_min(radioActivity.Apagada_min);
    tablaRadio.setDecolage_hor(radioActivity.Decolage_hor);
    tablaRadio.setDecolage_min(radioActivity.Decolage_min);
    tablaRadio.setAterrizaje_hor(radioActivity.Aterrizaje_hor);
    tablaRadio.setAterrizaje_min(radioActivity.Aterrizaje_min);
    tablaRadio.setPax(radioActivity.Pax);
    tablaRadio.setPax_h(radioActivity.Pax_h);
    tablaRadio.setPax_m(radioActivity.Pax_m);
    tablaRadio.setPax_n(radioActivity.Pax_n);
    tablaRadio.setPax_nibra(radioActivity.Pax_nibra);
    tablaRadio.setCarga(radioActivity.Carga);
    tablaRadio.setFuel(radioActivity.Fuel);



    //se obtienen o captura los datos de las variables ingresados en la actividad FullActvity desde la clase Vuelos ***TABLA VUELOS
    String numvuelo = RegVuelo.getNumvuelo();
    String desde = RegVuelo.getDesde();
    String hacia = RegVuelo.getHacia();
    String Prendida_hor = RegVuelo.getPrendida_hor();
    String Prendida_min = RegVuelo.getPrendida_min();
    String Apagada_hor = RegVuelo.getApagada_hor();
    String Apagada_min = RegVuelo.getApagada_min();
    String Vfr = RegVuelo.getVfr();
    String Ifr = RegVuelo.getIfr();
    String Noct = RegVuelo.getNocturno();
    String Total_Crew = RegVuelo.getTotal_crew();
    String Decolage_hor = RegVuelo.getDecolage_hor();
    String Decolage_min = RegVuelo.getDecolage_min();
    String Aterrizaje_hor = RegVuelo.getAterrizaje_hor();
    String Aterrizaje_min = RegVuelo.getAterrizaje_min();
    String Total_Aircraft = RegVuelo.getTotal_aircraft();
    String Ciclos = String.valueOf(RegVuelo.getNum_landing());
    String Pax = RegVuelo.getPax();
    String Carga = RegVuelo.getCarga();
    String Fuel = RegVuelo.getFuel();

    //se obtienen o captura los datos de las variables ingresados en la actividad FullActvity desde la clase TotalVuelos ***TABLA TOTALES
    int Totales_Trip_Hora = TotalesVuelos.getTotal_tripulacion_hor();
    int Totales_Trip_Min = TotalesVuelos.getTotal_tripulacion_min();
    int Totales_Aer_Hora = TotalesVuelos.getTotal_aeronave_hor();
    int Totales_Aer_Min = TotalesVuelos.getTotal_aeronave_min();
    int Totales_Vfr_Hora = TotalesVuelos.getVfrHora();
    int Totales_Vfr_Min = TotalesVuelos.getVfrMin();
    int Totales_Ifr_Hora = TotalesVuelos.getIfrHora();
    int Totales_Ifr_Min = TotalesVuelos.getIfrMin();
    int Totales_Noct_Hora = TotalesVuelos.getNocturnoHora();
    int Totales_Noct_Min = TotalesVuelos.getNocturnoMin();
    int Totales_Pax = TotalesVuelos.getPax();
    int Totales_Ciclos = TotalesVuelos.getNum_landing();
    int Totales_Carga = TotalesVuelos.getCarga();
    int Totales_Fuel = TotalesVuelos.getFuel();


    //se obtienen o captura los datos de las variables ingresados en la actividad DetailActivity desde la clase FuelRegistro ***TABLA FUEL
    String Tow = fuelRegistro.getTow();
    String Alt = fuelRegistro.getAltura();
    String Vel = fuelRegistro.getVelocidad();
    String Engi1 = fuelRegistro.getFf_eng1();
    String Engi2 = fuelRegistro.getFf_eng2();
    String FuelMin = fuelRegistro.getFuel_min();
    String FuelIN = fuelRegistro.getFuel_tax_in();
    String FuelOUT = fuelRegistro.getFuel_tax_out();
    String FuelTRIP = fuelRegistro.getTrip_fuel();
    String FuelREMAN = fuelRegistro.getFuel_reman();

    //System.out.println(numvuelo);




        ContentValues Insert = new ContentValues();//BASE DE DATOS BITACORA

        //se insertan los datos en la base de datos para la TABLA VUELOS
        Insert.put(Constantes.CAMPO_VUELO, numvuelo);
        Insert.put(Constantes.CAMPO_ORIGEN, desde);
        Insert.put(Constantes.CAMPO_DESTINO, hacia);
        Insert.put(Constantes.CAMPO_PRENDIDA_HORA, Prendida_hor);
        Insert.put(Constantes.CAMPO_PRENDIDA_MIN, Prendida_min);
        Insert.put(Constantes.CAMPO_APAGADA_HORA, Apagada_hor);
        Insert.put(Constantes.CAMPO_APAGADA_MIN, Apagada_min);
        Insert.put(Constantes.CAMPO_VFR, Vfr); //condicion de vuelo
        Insert.put(Constantes.CAMPO_IFR, Ifr); //condicion de vuelo
        Insert.put(Constantes.CAMPO_NOCTURNO, Noct); //condicion de vuelo
        Insert.put(Constantes.CAMPO_TOTAL_CREW, Total_Crew);
        Insert.put(Constantes.CAMPO_DECOLAGE_HORA, Decolage_hor);
        Insert.put(Constantes.CAMPO_DECOLAGE_MIN, Decolage_min);
        Insert.put(Constantes.CAMPO_ATERRIZAJE_HORA, Aterrizaje_hor);
        Insert.put(Constantes.CAMPO_ATERRIZAJE_MIN, Aterrizaje_min);
        Insert.put(Constantes.CAMPO_TOTAL_AIRCRAFT, Total_Aircraft);
        Insert.put(Constantes.CAMPO_CICLOS, Ciclos);
        Insert.put(Constantes.CAMPO_PAX, Pax);
        Insert.put(Constantes.CAMPO_CARGA, Carga);
        Insert.put(Constantes.CAMPO_FUEL, Fuel);




        //se almacenan los datos en la BASE de datos TABLA bitacora
        this.getWritableDatabase().insert(Constantes.TABLA_BITACORA, null, Insert);




        ContentValues Insertar = new ContentValues();//BASE DE DATOS TOTALES

      //se insertan los datos en la base de datos para la TABLA TOTALES
        Insertar.put(Constantes.CAMPO_VUELO, numvuelo);
        Insertar.put(Constantes.CAMPO_TOTAL_TRIP_HORA, Totales_Trip_Hora);
        Insertar.put(Constantes.CAMPO_TOTAL_TRIP_MIN, Totales_Trip_Min);
        Insertar.put(Constantes.CAMPO_TOTAL_AERO_HORA, Totales_Aer_Hora);
        Insertar.put(Constantes.CAMPO_TOTAL_AERO_MIN, Totales_Aer_Min);
        Insertar.put(Constantes.CAMPO_TOTAL_VFR_HORA, Totales_Vfr_Hora);
        Insertar.put(Constantes.CAMPO_TOTAL_VFR_MIN, Totales_Vfr_Min);
        Insertar.put(Constantes.CAMPO_TOTAL_IFR_HORA, Totales_Ifr_Hora);
        Insertar.put(Constantes.CAMPO_TOTAL_IFR_MIN, Totales_Ifr_Min);
        Insertar.put(Constantes.CAMPO_TOTAL_NOCTURNO_HORA, Totales_Noct_Hora);
        Insertar.put(Constantes.CAMPO_TOTAL_NOCTURNO_MIN, Totales_Noct_Min);
        Insertar.put(Constantes.CAMPO_TOTAL_CICLOS, Totales_Ciclos);
        Insertar.put(Constantes.CAMPO_TOTAL_PAX, Totales_Pax);
        Insertar.put(Constantes.CAMPO_TOTAL_CARGA, Totales_Carga);
        Insertar.put(Constantes.CAMPO_TOTAL_FUEL, Totales_Fuel);


    //se almacenan los datos en la BASE de datos TABLA TOTALES
        this.getWritableDatabase().insert(Constantes.TABLA_TOTALES, null, Insertar);




    ContentValues Inser = new ContentValues();//BASE DE DATOS FUEL

    //se insertan los datos en la base de datos para la TABLA FUEL
    Inser.put(Constantes.CAMPO_VUELO, numvuelo);
    Inser.put(Constantes.CAMPO_ORIGEN, desde);
    Inser.put(Constantes.CAMPO_DESTINO, hacia);
    Inser.put(Constantes.CAMPO_TOW, Tow);
    Inser.put(Constantes.CAMPO_FUEL_MIN, FuelMin);
    Inser.put(Constantes.CAMPO_FUEL_TAX_IN, FuelIN);
    Inser.put(Constantes.CAMPO_FUEL_TAX_OUT, FuelOUT);
    Inser.put(Constantes.CAMPO_TRIP_FUEL, FuelTRIP);
    Inser.put(Constantes.CAMPO_FF_ENG1, Engi1);
    Inser.put(Constantes.CAMPO_FF_ENG2, Engi2);
    Inser.put(Constantes.CAMPO_ALT, Alt);
    Inser.put(Constantes.CAMPO_MACH_IAS, Vel);
    Inser.put(Constantes.CAMPO_FUEL_REMAN, FuelREMAN);

    //se almacenan los datos en la BASE de datos TABLA TOTALES
    this.getWritableDatabase().insert(Constantes.TABLA_FUEL, null, Inser);

}


   ////////////////////////


//METODO PARA CONSULTAR DATOS EN LA BASE DE DATOS------------------------------------------
    public Cursor BuscarDatos (String numvuelo) throws SQLException {


        String[] parametros = {numvuelo};//parametro de busqueda es el numero de vuelo
        //los campos de seleccion que se consultan
        String[] campos = {Constantes.CAMPO_ORIGEN,Constantes.CAMPO_DESTINO, Constantes.CAMPO_PRENDIDA_HORA, Constantes.CAMPO_PRENDIDA_MIN, Constantes.CAMPO_APAGADA_HORA, Constantes.CAMPO_APAGADA_MIN,
                Constantes.CAMPO_VFR, Constantes.CAMPO_IFR, Constantes.CAMPO_NOCTURNO, Constantes.CAMPO_TOTAL_CREW, Constantes.CAMPO_DECOLAGE_HORA, Constantes.CAMPO_DECOLAGE_MIN,
                Constantes.CAMPO_ATERRIZAJE_HORA, Constantes.CAMPO_ATERRIZAJE_MIN, Constantes.CAMPO_TOTAL_AIRCRAFT, Constantes.CAMPO_CICLOS, Constantes.CAMPO_PAX, Constantes.CAMPO_CARGA, Constantes.CAMPO_FUEL};

        //este cursor trea los datos consultados
        Cursor fila = this.getWritableDatabase().query(Constantes.TABLA_BITACORA,campos,Constantes.CAMPO_VUELO+"=?",parametros,null,null,null);

return fila;

    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //    //METODO PARA ELIMINAR DATOS EN LA BASE DE DATOS------------------------------------------

    public int Eliminar(String numvuelo){


        String[] parametros = {numvuelo};//parametro para eliminar es el numero de vuelo
             //codigo para eliminar Vuelo en tabla VUELOS
        int cantidad= this.getWritableDatabase().delete(Constantes.TABLA_BITACORA, Constantes.CAMPO_VUELO+"=?",parametros);


        //codigo para eliminar Vuelo en Tabla TOTALES
        this.getWritableDatabase().delete(Constantes.TABLA_TOTALES, Constantes.CAMPO_VUELO+"=?",parametros);


        //codigo para eliminar Vuelo en Tabla FUEL
         this.getWritableDatabase().delete(Constantes.TABLA_FUEL, Constantes.CAMPO_VUELO+"=?",parametros);

return cantidad;

    }
    /////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////
    //    //METODO PARA MODIFICAR DATOS EN LA BASE DE DATOS------------------------------------------

    public int ModificarDatos(String numvuelo){




        FullActivity fullActivity = new FullActivity();

        //este codigo ingresa el valor del ususario del formulario Fullactivity en la clase Vuelos ***TABLA VUELOS
        RegVuelo.setNumvuelo(fullActivity.numvuelo);
        RegVuelo.setDesde(fullActivity.desde);
        RegVuelo.setHacia(fullActivity.hacia);
        RegVuelo.setPrendida_hor(fullActivity.Prendida_hor);
        RegVuelo.setPrendida_min(fullActivity.Prendida_min);
        RegVuelo.setApagada_hor(fullActivity.Apagada_hor);
        RegVuelo.setApagada_min(fullActivity.Apagada_min);
        RegVuelo.setVfr(fullActivity.Vfr);
        RegVuelo.setIfr(fullActivity.Ifr);
        RegVuelo.setNocturno(fullActivity.Noct);
        RegVuelo.setTotal_crew(fullActivity.Total_crew);
        RegVuelo.setDecolage_hor(fullActivity.Decolage_hor);
        RegVuelo.setDecolage_min(fullActivity.Decolage_min);
        RegVuelo.setAterrizaje_hor(fullActivity.Aterrizaje_hor);
        RegVuelo.setAterrizaje_min(fullActivity.Aterrizaje_min);
        RegVuelo.setTotal_aircraft(fullActivity.Total_aircraft);
        RegVuelo.setNum_landing(fullActivity.Ciclos);
        RegVuelo.setPax(fullActivity.Pax);
        RegVuelo.setCarga(fullActivity.Carga);
        RegVuelo.setFuel(fullActivity.Fuel);

        //este codigo ingresa el valor del ususario del formulario Fullactivity en la clase TotalesVuelos ***TABLA TOTALES
        TotalesVuelos.setNumvuelo(fullActivity.numvuelo);
        TotalesVuelos.setTotal_tripulacion_hor(fullActivity.Total_tripulacion_hor);
        TotalesVuelos.setTotal_tripulacion_min(fullActivity.Total_tripulacion_min);
        TotalesVuelos.setTotal_aeronave_hor(fullActivity.Total_aeronave_hor);
        TotalesVuelos.setTotal_aeronave_min(fullActivity.Total_aeronave_min);
        TotalesVuelos.setVfrHora(fullActivity.Total_Vfr_Hora);
        TotalesVuelos.setVfrMin(fullActivity.Total_Vfr_Min);
        TotalesVuelos.setIfrHora(fullActivity.Total_Ifr_Hora);
        TotalesVuelos.setIfrMin(fullActivity.Total_Ifr_Min);
        TotalesVuelos.setNocturnoHora(fullActivity.Total_Nocturno_Hora);
        TotalesVuelos.setNocturnoMin(fullActivity.Total_Nocturno_Min);
        TotalesVuelos.setPax(fullActivity.Total_Pax);
        TotalesVuelos.setNum_landing(fullActivity.Total_Ciclos);
        TotalesVuelos.setFuel(fullActivity.Total_Fuel);
        TotalesVuelos.setCarga(fullActivity.Total_Carga);



        //se obtienen o captura los datos de las variables ingresados en la actividad FullActvity desde la clase Vuelos ***TABLA VUELOS
        //String numvuelo = RegVuelo.getNumvuelo();
        String desde = RegVuelo.getDesde();
        String hacia = RegVuelo.getHacia();
        String Prendida_hor = RegVuelo.getPrendida_hor();
        String Prendida_min = RegVuelo.getPrendida_min();
        String Apagada_hor = RegVuelo.getApagada_hor();
        String Apagada_min = RegVuelo.getApagada_min();
        String Vfr = RegVuelo.getVfr();
        String Ifr = RegVuelo.getIfr();
        String Noct = RegVuelo.getNocturno();
        String Total_crew = RegVuelo.getTotal_crew();
        String Decolage_hor = RegVuelo.getDecolage_hor();
        String Decolage_min = RegVuelo.getDecolage_min();
        String Aterrizaje_hor = RegVuelo.getAterrizaje_hor();
        String Aterrizaje_min = RegVuelo.getAterrizaje_min();
        String Total_aircraft = RegVuelo.getTotal_aircraft();
        String Ciclos = String.valueOf(RegVuelo.getNum_landing());
        String Pax = RegVuelo.getPax();
        String Carga = RegVuelo.getCarga();
        String Fuel = RegVuelo.getFuel();

        //se obtienen o captura los datos de las variables ingresados en la actividad FullActvity desde la clase TotalVuelos ***TABLA TOTALES
        int Totales_Trip_Hora = TotalesVuelos.getTotal_tripulacion_hor();
        int Totales_Trip_Min = TotalesVuelos.getTotal_tripulacion_min();
        int Totales_Aer_Hora = TotalesVuelos.getTotal_aeronave_hor();
        int Totales_Aer_Min = TotalesVuelos.getTotal_aeronave_min();
        int Totales_Vfr_Hora = TotalesVuelos.getVfrHora();
        int Totales_Vfr_Min = TotalesVuelos.getVfrMin();
        int Totales_Ifr_Hora = TotalesVuelos.getIfrHora();
        int Totales_Ifr_Min = TotalesVuelos.getIfrMin();
        int Totales_Noct_Hora = TotalesVuelos.getNocturnoHora();
        int Totales_Noct_Min = TotalesVuelos.getNocturnoMin();
        int Totales_Pax = TotalesVuelos.getPax();
        int Totales_Ciclos = TotalesVuelos.getNum_landing();
        int Totales_Carga = TotalesVuelos.getCarga();
        int Totales_Fuel = TotalesVuelos.getFuel();



        //codicion para verificar que los datos esten ingresados


            ContentValues Insert = new ContentValues();/// TABLA VUELOS

            //Insert.put(Constantes.CAMPO_VUELO, numvuelo);
            Insert.put(Constantes.CAMPO_ORIGEN, desde);
            Insert.put(Constantes.CAMPO_DESTINO, hacia);
            Insert.put(Constantes.CAMPO_PRENDIDA_HORA, Prendida_hor);
            Insert.put(Constantes.CAMPO_PRENDIDA_MIN, Prendida_min);
            Insert.put(Constantes.CAMPO_APAGADA_HORA, Apagada_hor);
            Insert.put(Constantes.CAMPO_APAGADA_MIN, Apagada_min);
            Insert.put(Constantes.CAMPO_VFR, Vfr); //condicion de vuelo
            Insert.put(Constantes.CAMPO_IFR, Ifr); //condicion de vuelo
            Insert.put(Constantes.CAMPO_NOCTURNO, Noct); //condicion de vuelo
            Insert.put(Constantes.CAMPO_TOTAL_CREW, Total_crew);
            Insert.put(Constantes.CAMPO_DECOLAGE_HORA, Decolage_hor);
            Insert.put(Constantes.CAMPO_DECOLAGE_MIN, Decolage_min);
            Insert.put(Constantes.CAMPO_ATERRIZAJE_HORA, Aterrizaje_hor);
            Insert.put(Constantes.CAMPO_ATERRIZAJE_MIN, Aterrizaje_min);
            Insert.put(Constantes.CAMPO_TOTAL_AIRCRAFT, Total_aircraft);
            Insert.put(Constantes.CAMPO_CICLOS, Ciclos);
            Insert.put(Constantes.CAMPO_PAX, Pax);
            Insert.put(Constantes.CAMPO_CARGA, Carga);
            Insert.put(Constantes.CAMPO_FUEL, Fuel);




        ContentValues Insertar = new ContentValues();//BASE DE DATOS TOTALES

        //se insertan los datos en la base de datos para la TABLA TOTALES
        Insertar.put(Constantes.CAMPO_VUELO, numvuelo);
        Insertar.put(Constantes.CAMPO_TOTAL_TRIP_HORA, Totales_Trip_Hora);
        Insertar.put(Constantes.CAMPO_TOTAL_TRIP_MIN, Totales_Trip_Min);
        Insertar.put(Constantes.CAMPO_TOTAL_AERO_HORA, Totales_Aer_Hora);
        Insertar.put(Constantes.CAMPO_TOTAL_AERO_MIN, Totales_Aer_Min);
        Insertar.put(Constantes.CAMPO_TOTAL_VFR_HORA, Totales_Vfr_Hora);
        Insertar.put(Constantes.CAMPO_TOTAL_VFR_MIN, Totales_Vfr_Min);
        Insertar.put(Constantes.CAMPO_TOTAL_IFR_HORA, Totales_Ifr_Hora);
        Insertar.put(Constantes.CAMPO_TOTAL_IFR_MIN, Totales_Ifr_Min);
        Insertar.put(Constantes.CAMPO_TOTAL_NOCTURNO_HORA, Totales_Noct_Hora);
        Insertar.put(Constantes.CAMPO_TOTAL_NOCTURNO_MIN, Totales_Noct_Min);
        Insertar.put(Constantes.CAMPO_TOTAL_CICLOS, Totales_Ciclos);
        Insertar.put(Constantes.CAMPO_TOTAL_PAX, Totales_Pax);
        Insertar.put(Constantes.CAMPO_TOTAL_CARGA, Totales_Carga);
        Insertar.put(Constantes.CAMPO_TOTAL_FUEL, Totales_Fuel);


        String[] parametros = {numvuelo};//parametro para modificar los datos del numero de vuelo

        //se MODIFICAN los datos en la BASE de datos TABLA VUELOS
        int cantidad = this.getWritableDatabase().update(Constantes.TABLA_BITACORA, Insert, Constantes.CAMPO_VUELO+"=?", parametros);
        //se MODIFICAN los datos en la BASE de datos TABLA TOTALES
        this.getWritableDatabase().update(Constantes.TABLA_TOTALES, Insertar, Constantes.CAMPO_VUELO+"=?", parametros);





        return cantidad;
    }
    ////////////////////////////

    //////////*****************************************************************************************************/////////////////////
    ///////////////////  ---------ADMINISTRACION BASE DE DATOS RADIO--------  ////////////

    ////////////////////////////////////////////////
    //METODO PARA INSERTAR DATOS EN LA BASE DE DATOS RADIO------------------------------------------
    public void IngresarDatosRadio(){
        RadioActivity radioActivity = new RadioActivity();

        //este codigo ingresa el valor del ususario del formulario RadioActivity en la clase TablaRadio ***TABLA RADIO

        tablaRadio.setNumFAC(radioActivity.numfac);
        tablaRadio.setNumvuelo(radioActivity.numvuelo);
        tablaRadio.setDesde(radioActivity.desde);
        tablaRadio.setHacia(radioActivity.hacia);
        tablaRadio.setPuertas_hor(radioActivity.Puertas_hor);
        tablaRadio.setPuertas_min(radioActivity.Puertas_min);
        tablaRadio.setPrendida_hor(radioActivity.Prendida_hor);
        tablaRadio.setPrendida_min(radioActivity.Prendida_min);
        tablaRadio.setApagada_hor(radioActivity.Apagada_hor);
        tablaRadio.setApagada_min(radioActivity.Apagada_min);
        tablaRadio.setDecolage_hor(radioActivity.Decolage_hor);
        tablaRadio.setDecolage_min(radioActivity.Decolage_min);
        tablaRadio.setAterrizaje_hor(radioActivity.Aterrizaje_hor);
        tablaRadio.setAterrizaje_min(radioActivity.Aterrizaje_min);
        tablaRadio.setPax(radioActivity.Pax);
        tablaRadio.setPax_h(radioActivity.Pax_h);
        tablaRadio.setPax_m(radioActivity.Pax_m);
        tablaRadio.setPax_n(radioActivity.Pax_n);
        tablaRadio.setPax_nibra(radioActivity.Pax_nibra);
        tablaRadio.setCarga(radioActivity.Carga);
        tablaRadio.setFuel(radioActivity.Fuel);



        //se obtienen o captura los datos de las variables ingresados en la actividad RadioActivity desde la clase TablaRadio ***TABLA RADIO
        String numFACRadio = tablaRadio.getNumFAC();
        String numvuelo = tablaRadio.getNumvuelo();
        String desde = tablaRadio.getDesde();
        String hacia = tablaRadio.getHacia();
        String Puertas_hor = tablaRadio.getPuertas_hor();
        String Puertas_min = tablaRadio.getPuertas_min();
        String Prendida_hor_radio = tablaRadio.getPrendida_hor();
        String Prendida_min_radio = tablaRadio.getPrendida_min();
        String Apagada_hor_radio = tablaRadio.getApagada_hor();
        String Apagada_min_radio = tablaRadio.getApagada_min();
        String Decolage_hor_radio = tablaRadio.getDecolage_hor();
        String Decolage_min_radio = tablaRadio.getDecolage_min();
        String Aterrizaje_hor_radio = tablaRadio.getAterrizaje_hor();
        String Aterrizaje_min_radio = tablaRadio.getAterrizaje_min();
        String Pax_radio = tablaRadio.getPax();
        String Pax_h_radio = tablaRadio.getPax_h();
        String Pax_m_radio = tablaRadio.getPax_m();
        String Pax_n_radio = tablaRadio.getPax_n();
        String Pax_nibra_radio = tablaRadio.getPax_nibra();
        String Carga_radio = tablaRadio.getCarga();
        String Fuel_radio = tablaRadio.getFuel();



        ContentValues Add = new ContentValues();//BASE DE DATOS RADIO

        //se insertan los datos en la base de datos para la TABLA RADIO
        Add.put(Constantes.CAMPO_VUELO, numvuelo);
        Add.put(Constantes.CAMPO_FAC, numFACRadio);
        Add.put(Constantes.CAMPO_ORIGEN,desde);
        Add.put(Constantes.CAMPO_DESTINO, hacia);
        Add.put(Constantes.CAMPO_PUERTAS_HORA, Puertas_hor);
        Add.put(Constantes.CAMPO_PUERTAS_MIN, Puertas_min);
        Add.put(Constantes.CAMPO_RADIO_PRENDIDA_HORA, Prendida_hor_radio);
        Add.put(Constantes.CAMPO_RADIO_PRENDIDA_MIN, Prendida_min_radio);
        Add.put(Constantes.CAMPO_RADIO_APAGADA_HORA, Apagada_hor_radio);
        Add.put(Constantes.CAMPO_RADIO_APAGADA_MIN, Apagada_min_radio);
        Add.put(Constantes.CAMPO_RADIO_DECOLAGE_HORA, Decolage_hor_radio);
        Add.put(Constantes.CAMPO_RADIO_DECOLAGE_MIN, Decolage_min_radio);
        Add.put(Constantes.CAMPO_RADIO_ATERRIZAJE_HORA, Aterrizaje_hor_radio);
        Add.put(Constantes.CAMPO_RADIO_ATERRIZAJE_MIN, Aterrizaje_min_radio);
        Add.put(Constantes.CAMPO_RADIO_PAX, Pax_radio);
        Add.put(Constantes.CAMPO_PAX_H, Pax_h_radio);
        Add.put(Constantes.CAMPO_PAX_M, Pax_m_radio);
        Add.put(Constantes.CAMPO_PAX_N, Pax_n_radio);
        Add.put(Constantes.CAMPO_PAX_NIBRA, Pax_nibra_radio);
        Add.put(Constantes.CAMPO_CARGA_RADIO, Carga_radio);
        Add.put(Constantes.CAMPO_REMANENTE_RADIO, Fuel_radio);




        //se almacenan los datos en la BASE de datos TABLA bitacora
        this.getWritableDatabase().insert(Constantes.TABLA_RADIO, null, Add);

    }
    /////////////////////////////////////////////////


    //////////////////////
    //METODO PARA CONSULTAR DATOS EN LA BASE DE DATOS RADIO------------------------------------------
    public Cursor BuscarDatosRadio (String numvuelo) throws SQLException {


        String[] parametros = {numvuelo};//parametro de busqueda es el numero de vuelo
        //los campos de seleccion que se consultan
        String[] campos = {Constantes.CAMPO_FAC,Constantes.CAMPO_VUELO, Constantes.CAMPO_ORIGEN, Constantes.CAMPO_DESTINO, Constantes.CAMPO_PUERTAS_HORA, Constantes.CAMPO_PUERTAS_MIN, Constantes.CAMPO_RADIO_PRENDIDA_HORA, Constantes.CAMPO_RADIO_PRENDIDA_MIN, Constantes.CAMPO_RADIO_APAGADA_HORA, Constantes.CAMPO_RADIO_APAGADA_MIN,
                Constantes.CAMPO_RADIO_DECOLAGE_HORA, Constantes.CAMPO_RADIO_DECOLAGE_MIN, Constantes.CAMPO_RADIO_ATERRIZAJE_HORA, Constantes.CAMPO_RADIO_ATERRIZAJE_MIN, Constantes.CAMPO_RADIO_PAX, Constantes.CAMPO_PAX_H, Constantes.CAMPO_PAX_M, Constantes.CAMPO_PAX_N, Constantes.CAMPO_PAX_NIBRA, Constantes.CAMPO_CARGA_RADIO, Constantes.CAMPO_REMANENTE_RADIO};

        //este cursor trea los datos consultados
        Cursor fila = this.getWritableDatabase().query(Constantes.TABLA_RADIO,campos,Constantes.CAMPO_VUELO+"=?",parametros,null,null,null);

        return fila;

    }
    /////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////////
    //    //METODO PARA ELIMINAR DATOS EN LA BASE DE DATOS------------------------------------------

    public int EliminarDatosRadio(String numvuelo){


        String[] parametros = {numvuelo};//parametro para eliminar es el numero de vuelo
        //codigo para eliminar Vuelo en tabla VUELOS
        int cantidad= this.getWritableDatabase().delete(Constantes.TABLA_RADIO, Constantes.CAMPO_VUELO+"=?",parametros);


        return cantidad;

    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //    //METODO PARA MODIFICAR DATOS EN LA BASE DE DATOS RADIO------------------------------------------

    public int ModificarDatosRadio(String numvuelo){

        RadioActivity radioActivity = new RadioActivity();

        //este codigo ingresa el valor del ususario del formulario RadioActivity en la clase TablaRadio ***TABLA RADIO

        tablaRadio.setNumFAC(radioActivity.numfac);
        tablaRadio.setNumvuelo(radioActivity.numvuelo);
        tablaRadio.setDesde(radioActivity.desde);
        tablaRadio.setHacia(radioActivity.hacia);
        tablaRadio.setPuertas_hor(radioActivity.Puertas_hor);
        tablaRadio.setPuertas_min(radioActivity.Puertas_min);
        tablaRadio.setPrendida_hor(radioActivity.Prendida_hor);
        tablaRadio.setPrendida_min(radioActivity.Prendida_min);
        tablaRadio.setApagada_hor(radioActivity.Apagada_hor);
        tablaRadio.setApagada_min(radioActivity.Apagada_min);
        tablaRadio.setDecolage_hor(radioActivity.Decolage_hor);
        tablaRadio.setDecolage_min(radioActivity.Decolage_min);
        tablaRadio.setAterrizaje_hor(radioActivity.Aterrizaje_hor);
        tablaRadio.setAterrizaje_min(radioActivity.Aterrizaje_min);
        tablaRadio.setPax(radioActivity.Pax);
        tablaRadio.setPax_h(radioActivity.Pax_h);
        tablaRadio.setPax_m(radioActivity.Pax_m);
        tablaRadio.setPax_n(radioActivity.Pax_n);
        tablaRadio.setPax_nibra(radioActivity.Pax_nibra);
        tablaRadio.setCarga(radioActivity.Carga);
        tablaRadio.setFuel(radioActivity.Fuel);

        //se obtienen o captura los datos de las variables ingresados en la actividad RadioActivity desde la clase TablaRadio ***TABLA RADIO
        String numFACRadio = tablaRadio.getNumFAC();
        String desde = tablaRadio.getDesde();
        String hacia = tablaRadio.getHacia();
        String Puertas_hor = tablaRadio.getPuertas_hor();
        String Puertas_min = tablaRadio.getPuertas_min();
        String Prendida_hor_radio = tablaRadio.getPrendida_hor();
        String Prendida_min_radio = tablaRadio.getPrendida_min();
        String Apagada_hor_radio = tablaRadio.getApagada_hor();
        String Apagada_min_radio = tablaRadio.getApagada_min();
        String Decolage_hor_radio = tablaRadio.getDecolage_hor();
        String Decolage_min_radio = tablaRadio.getDecolage_min();
        String Aterrizaje_hor_radio = tablaRadio.getAterrizaje_hor();
        String Aterrizaje_min_radio = tablaRadio.getAterrizaje_min();
        String Pax_radio = tablaRadio.getPax();
        String Pax_h_radio = tablaRadio.getPax_h();
        String Pax_m_radio = tablaRadio.getPax_m();
        String Pax_n_radio = tablaRadio.getPax_n();
        String Pax_nibra_radio = tablaRadio.getPax_nibra();
        String Carga_radio = tablaRadio.getCarga();
        String Fuel_radio = tablaRadio.getFuel();



        ContentValues Add = new ContentValues();//BASE DE DATOS RADIO

        //se insertan los datos en la base de datos para la TABLA RADIO
        Add.put(Constantes.CAMPO_VUELO, numvuelo);
        Add.put(Constantes.CAMPO_FAC, numFACRadio);
        Add.put(Constantes.CAMPO_ORIGEN,desde);
        Add.put(Constantes.CAMPO_DESTINO, hacia);
        Add.put(Constantes.CAMPO_PUERTAS_HORA, Puertas_hor);
        Add.put(Constantes.CAMPO_PUERTAS_MIN, Puertas_min);
        Add.put(Constantes.CAMPO_RADIO_PRENDIDA_HORA, Prendida_hor_radio);
        Add.put(Constantes.CAMPO_RADIO_PRENDIDA_MIN, Prendida_min_radio);
        Add.put(Constantes.CAMPO_RADIO_APAGADA_HORA, Apagada_hor_radio);
        Add.put(Constantes.CAMPO_RADIO_APAGADA_MIN, Apagada_min_radio);
        Add.put(Constantes.CAMPO_RADIO_DECOLAGE_HORA, Decolage_hor_radio);
        Add.put(Constantes.CAMPO_RADIO_DECOLAGE_MIN, Decolage_min_radio);
        Add.put(Constantes.CAMPO_RADIO_ATERRIZAJE_HORA, Aterrizaje_hor_radio);
        Add.put(Constantes.CAMPO_RADIO_ATERRIZAJE_MIN, Aterrizaje_min_radio);
        Add.put(Constantes.CAMPO_RADIO_PAX, Pax_radio);
        Add.put(Constantes.CAMPO_PAX_H, Pax_h_radio);
        Add.put(Constantes.CAMPO_PAX_M, Pax_m_radio);
        Add.put(Constantes.CAMPO_PAX_N, Pax_n_radio);
        Add.put(Constantes.CAMPO_PAX_NIBRA, Pax_nibra_radio);
        Add.put(Constantes.CAMPO_CARGA_RADIO, Carga_radio);
        Add.put(Constantes.CAMPO_REMANENTE_RADIO, Fuel_radio);




        String[] parametros = {numvuelo};//parametro para modificar los datos del numero de vuelo

        //se MODIFICAN los datos en la BASE de datos TABLA RADIO
        int cantidad = this.getWritableDatabase().update(Constantes.TABLA_RADIO, Add, Constantes.CAMPO_VUELO+"=?", parametros);



        return cantidad;
    }
    ////////////////////////////
    ////////////////////////////////////////**
    ///////////////////////
//METODO PARA INSERTAR DATOS EN LA BASE DE DATOS CONTADOR------------------------------------------
    public void IngresarDatosContador(){

        FullActivity fullActivity = new FullActivity();


        //este codigo ingresa el valor del ususario del formulario Fullactivity en la clase TotalesVuelos ***TABLA CONTADOR
        tablaContador.setNumvuelo(fullActivity.numvuelo);
        tablaContador.setTotal_tripulacion_hor(fullActivity.Total_tripulacion_hor);
        tablaContador.setTotal_tripulacion_min(fullActivity.Total_tripulacion_min);
        tablaContador.setDay(fullActivity.days);
        tablaContador.setWeek(fullActivity.weeks);
        tablaContador.setMonth(fullActivity.months);
        tablaContador.setYear(fullActivity.years);



        //se obtienen o captura los datos de las variables ingresados en la actividad FullActvity desde la clase TablaContador ***TABLA CONTADOR
        String numvuelo = tablaContador.getNumvuelo();
        int Contador_Trip_Hora = tablaContador.getTotal_tripulacion_hor();
        int Contador_Trip_Min = tablaContador.getTotal_tripulacion_min();
        int day = tablaContador.getDay();
        int week = tablaContador.getWeek();
        String month = tablaContador.getMonth();
        String year = tablaContador.getYear();



        //////////////////////////////////
        ContentValues poner = new ContentValues();//BASE DE DATOS CONTADOR
        //se insertan los datos en la base de datos para la TABLA CONTADOR
        poner.put(Constantes.CAMPO_IDVUELO, numvuelo);
        poner.put(Constantes.CAMPO_TOTAL_TRIP_HORA, Contador_Trip_Hora);
        poner.put(Constantes.CAMPO_TOTAL_TRIP_MIN, Contador_Trip_Min);
        poner.put(Constantes.CAMPO_DAY, day);
        poner.put(Constantes.CAMPO_WEEK, week);
        poner.put(Constantes.CAMPO_MONTH, month);
        poner.put(Constantes.CAMPO_YEAR, year);

        this.getWritableDatabase().insert(Constantes.TABLA_CONTADOR, null, poner);
///////////////////////////////




    }

    ///////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////
    //    //METODO PARA ELIMINAR DATOS EN LA BASE DE DATOS CONTADOR--------------------------------------

    public int EliminarDatosContador(String idCode){


        String[] parametros = {idCode};//parametro para eliminar es el numero de vuelo
        //codigo para eliminar Vuelo en tabla CONTADOR
        int cantidad= this.getWritableDatabase().delete(Constantes.TABLA_CONTADOR, Constantes.ID_CAMPO+"=?",parametros);



        return cantidad;

    }


    ////////////////////////
    /////////////////////////////////////////////**

    //////////////////////////////////////////////////////////////////////////////////////////////
    //    //METODO PARA MODIFICAR DATOS EN LA BASE DE DATOS RADIO------------------------------------------

    public int ModificarDatosContador(String numvuelo){

        FullActivity fullActivity = new FullActivity();


        //este codigo ingresa el valor del ususario del formulario Fullactivity en la clase TotalesVuelos ***TABLA CONTADOR
        tablaContador.setNumvuelo(fullActivity.numvuelo);
        tablaContador.setTotal_tripulacion_hor(fullActivity.Total_tripulacion_hor);
        tablaContador.setTotal_tripulacion_min(fullActivity.Total_tripulacion_min);
        tablaContador.setDay(fullActivity.days);
        tablaContador.setWeek(fullActivity.weeks);
        tablaContador.setMonth(fullActivity.months);
        tablaContador.setYear(fullActivity.years);



        //se obtienen o captura los datos de las variables ingresados en la actividad FullActvity desde la clase TablaContador ***TABLA CONTADOR
        int Contador_Trip_Hora = tablaContador.getTotal_tripulacion_hor();
        int Contador_Trip_Min = tablaContador.getTotal_tripulacion_min();
        int day = tablaContador.getDay();
        int week = tablaContador.getWeek();
        String month = tablaContador.getMonth();
        String year = tablaContador.getYear();



        //////////////////////////////////
        ContentValues poner = new ContentValues();//BASE DE DATOS CONTADOR
        //se insertan los datos en la base de datos para la TABLA CONTADOR
        poner.put(Constantes.CAMPO_IDVUELO, numvuelo);
        poner.put(Constantes.CAMPO_TOTAL_TRIP_HORA, Contador_Trip_Hora);
        poner.put(Constantes.CAMPO_TOTAL_TRIP_MIN, Contador_Trip_Min);
        poner.put(Constantes.CAMPO_DAY, day);
        poner.put(Constantes.CAMPO_WEEK, week);
        poner.put(Constantes.CAMPO_MONTH, month);
        poner.put(Constantes.CAMPO_YEAR, year);


        String[] parametros = {numvuelo};//parametro para modificar los datos del numero de vuelo

        //se MODIFICAN los datos en la BASE de datos TABLA RADIO
        int cantidad = this.getWritableDatabase().update(Constantes.TABLA_CONTADOR, poner, Constantes.CAMPO_IDVUELO+"=?", parametros);



        return cantidad;
    }
    ////////////////////////////


}

