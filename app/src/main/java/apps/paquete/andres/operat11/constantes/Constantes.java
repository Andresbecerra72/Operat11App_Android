package apps.paquete.andres.operat11.constantes;

//representa los campos de la base de datos

public class Constantes {

    //TABLA VUELOS
    public static final String TABLA_BITACORA = "bitacora";
    public static final String CAMPO_VUELO = "numvuelo";
    public static final String CAMPO_ORIGEN = "origen";
    public static final String CAMPO_DESTINO = "destino";
    public static final String CAMPO_PRENDIDA_HORA = "prendida_hor";
    public static final String CAMPO_PRENDIDA_MIN = "prendida_min";
    public static final String CAMPO_APAGADA_HORA = "apagada_hor";
    public static final String CAMPO_APAGADA_MIN = "apagada_min";
    public static final String CAMPO_VFR = "vfr";
    public static final String CAMPO_IFR = "ifr";
    public static final String CAMPO_NOCTURNO = "nocturno";
    public static final String CAMPO_TOTAL_CREW = "total_crew";
    public static final String CAMPO_DECOLAGE_HORA = "decolage_hor";
    public static final String CAMPO_DECOLAGE_MIN = "decolage_min";
    public static final String CAMPO_ATERRIZAJE_HORA = "aterrizaje_hor";
    public static final String CAMPO_ATERRIZAJE_MIN = "aterrizaje_min";
    public static final String CAMPO_TOTAL_AIRCRAFT = "total_aircraft";
    public static final String CAMPO_CICLOS = "num_landing";
    public static final String CAMPO_PAX = "pax";
    public static final String CAMPO_CARGA = "carga";
    public static final String CAMPO_FUEL = "fuel";


   public static final String CREAR_TABLA_VUELOS = "CREATE TABLE "+TABLA_BITACORA+"("+CAMPO_VUELO+" TEXT primary key, "+CAMPO_ORIGEN+" TEXT, " +
           ""+CAMPO_DESTINO+" TEXT, "+CAMPO_PRENDIDA_HORA+" TEXT, "+CAMPO_PRENDIDA_MIN+" TEXT, "+CAMPO_APAGADA_HORA+" TEXT," +
           " "+CAMPO_APAGADA_MIN+" TEXT, "+CAMPO_VFR+" TEXT, "+CAMPO_IFR+" TEXT, "+CAMPO_NOCTURNO+" TEXT, "+CAMPO_TOTAL_CREW+" TEXT, "+CAMPO_DECOLAGE_HORA+" TEXT, "+CAMPO_DECOLAGE_MIN+" TEXT, "+CAMPO_ATERRIZAJE_HORA+" TEXT, "+CAMPO_ATERRIZAJE_MIN+" TEXT, " +
           ""+CAMPO_TOTAL_AIRCRAFT+" TEXT, "+CAMPO_CICLOS+" INTEGER, "+CAMPO_PAX+" TEXT, "+CAMPO_CARGA+" TEXT, "+CAMPO_FUEL+" TEXT )";

//public static final String CREAR_TABLA_VUELOS = "CREATE TABLE bitacora(numvuelo INTEGER primary key, origen TEXT, destino TEXT, prendida_hor INTEGER, prendida_min INTEGER, apagada_hor INTEGER, apagada_min INTEGER, vfr INTEGER, ifr INTEGER, nocturno INTEGER, total_tripulacion_hor INTEGER, total_tripulacion_min INTEGER, decolage_hor INTEGER, decolage_min INTEGER, aterrizaje_hor INTEGER, aterrizaje_min INTEGER, total_aeronave_hor INTEGER, total_aeronave_min INTEGER, num_landing INTEGER, pax INTEGER, carga INTEGER, fuel INTEGER )";

////TABLA TOTALES
    public static final String TABLA_TOTALES = "totales";
    public static final String CAMPO_TOTAL_TRIP_HORA= "total_tripulacion_hor";
    public static final String CAMPO_TOTAL_TRIP_MIN= "total_tripulacion_min";
    public static final String CAMPO_TOTAL_VFR_HORA = "total_vfr_hora";
    public static final String CAMPO_TOTAL_VFR_MIN = "total_vfr_min";
    public static final String CAMPO_TOTAL_IFR_HORA = "total_ifr_hora";
    public static final String CAMPO_TOTAL_IFR_MIN = "total_ifr_min";
    public static final String CAMPO_TOTAL_NOCTURNO_HORA = "total_nocturno_hora";
    public static final String CAMPO_TOTAL_NOCTURNO_MIN = "total_nocturno_min";
    public static final String CAMPO_TOTAL_AERO_HORA= "total_aeronave_hor";
    public static final String CAMPO_TOTAL_AERO_MIN= "total_aeronave_min";
    public static final String CAMPO_TOTAL_CICLOS = "total_ciclos";
    public static final String CAMPO_TOTAL_PAX = "total_pax";
    public static final String CAMPO_TOTAL_CARGA = "total_carga";
    public static final String CAMPO_TOTAL_FUEL = "total_fuel";


    public static final String CREAR_TABLA_TOTALES = "CREATE TABLE "+TABLA_TOTALES+"("+CAMPO_VUELO+" TEXT primary key, "+CAMPO_TOTAL_TRIP_HORA+" INTEGER, "+CAMPO_TOTAL_TRIP_MIN+" INTEGER, "+CAMPO_TOTAL_VFR_HORA+" INTEGER, "+CAMPO_TOTAL_VFR_MIN+" INTEGER, "+CAMPO_TOTAL_IFR_HORA+" INTEGER, "+CAMPO_TOTAL_IFR_MIN+" INTEGER, "+CAMPO_TOTAL_NOCTURNO_HORA+" INTEGER, "+CAMPO_TOTAL_NOCTURNO_MIN+" INTEGER, "+CAMPO_TOTAL_AERO_HORA+" INTEGER, "+CAMPO_TOTAL_AERO_MIN+" INTEGER, "+CAMPO_TOTAL_CICLOS+" INTEGER, "+CAMPO_TOTAL_PAX+" INTEGER, "+CAMPO_TOTAL_CARGA+" INTEGER, "+CAMPO_TOTAL_FUEL+" INTEGER )";

    ////TABLA FUEL

    public static final String TABLA_FUEL = "fuel";
    public static final String CAMPO_TOW= "tow";
    public static final String CAMPO_FUEL_MIN= "fuel_min";
    public static final String CAMPO_FUEL_TAX_IN = "tax_in";
    public static final String CAMPO_FUEL_TAX_OUT = "tax_out";
    public static final String CAMPO_TRIP_FUEL = "trip_fuel";
    public static final String CAMPO_FF_ENG1 = "ff_eng1";
    public static final String CAMPO_FF_ENG2 = "ff_eng2";
    public static final String CAMPO_ALT = "altura";
    public static final String CAMPO_MACH_IAS= "velocidad";
    public static final String CAMPO_FUEL_REMAN= "fuel_reman";



    public static final String CREAR_TABLA_FUEL = "CREATE TABLE "+TABLA_FUEL+"("+CAMPO_VUELO+" TEXT primary key, "+CAMPO_ORIGEN+" TEXT, " +CAMPO_DESTINO+" TEXT, "+CAMPO_TOW+" INTEGER, "+CAMPO_FUEL_MIN+" INTEGER, "+CAMPO_FUEL_TAX_IN+" INTEGER, "+CAMPO_FUEL_TAX_OUT+" INTEGER, "+CAMPO_TRIP_FUEL+" INTEGER, "+CAMPO_FF_ENG1+" INTEGER, "+CAMPO_FF_ENG2+" INTEGER, "+CAMPO_ALT+" INTEGER, "+CAMPO_MACH_IAS+" INTEGER, "+CAMPO_FUEL_REMAN+" INTEGER )";

    ////TABLA RADIO

    public static final String TABLA_RADIO = "radio";
    public static final String CAMPO_FAC= "fac";
    public static final String CAMPO_PUERTAS_HORA = "puertas_hora";
    public static final String CAMPO_PUERTAS_MIN = "puertas_min";
    public static final String CAMPO_RADIO_PRENDIDA_HORA = "prendida_hor_radio";
    public static final String CAMPO_RADIO_PRENDIDA_MIN = "prendida_min_radio";
    public static final String CAMPO_RADIO_DECOLAGE_HORA = "decolage_hor_radio";
    public static final String CAMPO_RADIO_DECOLAGE_MIN = "decolage_min_radio";
    public static final String CAMPO_RADIO_ATERRIZAJE_HORA = "aterrizaje_hor_radio";
    public static final String CAMPO_RADIO_ATERRIZAJE_MIN = "aterrizaje_min_radio";
    public static final String CAMPO_RADIO_APAGADA_HORA = "apagada_hor_radio";
    public static final String CAMPO_RADIO_APAGADA_MIN = "apagada_min_radio";
    public static final String CAMPO_RADIO_PAX = "pax_radio";
    public static final String CAMPO_PAX_H = "pax_hombres";
    public static final String CAMPO_PAX_M = "pax_mujeres";
    public static final String CAMPO_PAX_N = "pax_ninos";
    public static final String CAMPO_PAX_NIBRA = "pax_nibra";
    public static final String CAMPO_CARGA_RADIO = "carga_radio";
    public static final String CAMPO_REMANENTE_RADIO = "remanente_radio";




    public static final String CREAR_TABLA_RADIO = "CREATE TABLE "+TABLA_RADIO+"("+CAMPO_VUELO+" INTEGER primary key, "+CAMPO_FAC+" TEXT, "+CAMPO_ORIGEN+" TEXT, "+CAMPO_DESTINO+" TEXT, " +CAMPO_PUERTAS_HORA+" TEXT, "+CAMPO_PUERTAS_MIN+" TEXT, "+CAMPO_RADIO_PRENDIDA_HORA+" TEXT, "+CAMPO_RADIO_PRENDIDA_MIN+" TEXT, "+CAMPO_RADIO_DECOLAGE_HORA+" TEXT, "+CAMPO_RADIO_DECOLAGE_MIN+" TEXT, "+CAMPO_RADIO_ATERRIZAJE_HORA+" TEXT, "+CAMPO_RADIO_ATERRIZAJE_MIN+" TEXT, "+CAMPO_RADIO_APAGADA_HORA+" TEXT, "+CAMPO_RADIO_APAGADA_MIN+" TEXT, "+CAMPO_RADIO_PAX+" TEXT, "+CAMPO_PAX_H+" TEXT,"+CAMPO_PAX_M +" TEXT, "+CAMPO_PAX_N+" TEXT, "+CAMPO_PAX_NIBRA+" TEXT, "+CAMPO_CARGA_RADIO+" TEXT, "+CAMPO_REMANENTE_RADIO+" TEXT)";

//TABLA CONTADOR

    public static final String TABLA_CONTADOR = "contador";
    public static final String ID_CAMPO = "id";
    public static final String CAMPO_IDVUELO = "idvuelo";
    public static final String CAMPO_DAY= "day";
    public static final String CAMPO_WEEK= "week";
    public static final String CAMPO_MONTH= "month";
    public static final String CAMPO_YEAR= "year";

    public static final String CREAR_TABLA_CONTADOR = "CREATE TABLE "+TABLA_CONTADOR+"("+ID_CAMPO+" INTEGER primary key AUTOINCREMENT, "+CAMPO_TOTAL_TRIP_HORA+" INTEGER, "+CAMPO_TOTAL_TRIP_MIN+" INTEGER, "+CAMPO_DAY+" INTEGER, "+CAMPO_WEEK+" INTEGER, "+CAMPO_MONTH+" INTEGER, "+CAMPO_YEAR+" INTEGER, "+CAMPO_IDVUELO+" TEXT, FOREIGN KEY ("+CAMPO_IDVUELO+") REFERENCES "+TABLA_BITACORA+"("+CAMPO_VUELO+") );";

    //TABLA AÑOS

    public static final String TABLA_YEAR = "year";

    public static final String CREAR_TABLA_YEAR = "CREATE TABLE "+TABLA_YEAR+"("+CAMPO_YEAR+" INTEGER primary key, "+CAMPO_DAY+" INTEGER, "+CAMPO_WEEK+" INTEGER, "+CAMPO_MONTH+" INTEGER, "+CAMPO_TOTAL_TRIP_HORA+" INTEGER, "+CAMPO_TOTAL_TRIP_MIN+" INTEGER,)";

    //TABLA MESES

    public static final String TABLA_MONTH = "month";

    public static final String CREAR_TABLA_MONTH = "CREATE TABLE "+TABLA_MONTH+"("+CAMPO_MONTH+" INTEGER primary key, "+CAMPO_DAY+" INTEGER, "+CAMPO_WEEK+" INTEGER, "+CAMPO_YEAR+" INTEGER, "+CAMPO_TOTAL_TRIP_HORA+" INTEGER, "+CAMPO_TOTAL_TRIP_MIN+" INTEGER,)";

    //TABLA SEMANAS

    public static final String TABLA_WEEK = "week";

    public static final String CREAR_TABLA_WEEK = "CREATE TABLE "+TABLA_WEEK+"("+CAMPO_WEEK+" INTEGER primary key, "+CAMPO_DAY+" INTEGER, "+CAMPO_MONTH+" INTEGER, "+CAMPO_YEAR+" INTEGER, "+CAMPO_TOTAL_TRIP_HORA+" INTEGER, "+CAMPO_TOTAL_TRIP_MIN+" INTEGER,)";

    //TABLA DIAS

    public static final String TABLA_DAY = "week";

    public static final String CREAR_TABLA_DAY = "CREATE TABLE "+TABLA_DAY+"("+CAMPO_DAY+" INTEGER primary key, "+CAMPO_WEEK+" INTEGER, "+CAMPO_MONTH+" INTEGER, "+CAMPO_YEAR+" INTEGER, "+CAMPO_TOTAL_TRIP_HORA+" INTEGER, "+CAMPO_TOTAL_TRIP_MIN+" INTEGER,)";


}
