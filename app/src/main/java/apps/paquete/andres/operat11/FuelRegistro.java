package apps.paquete.andres.operat11;

public class FuelRegistro {
    //estructura de la tabla *****13 registros


    private String numvuelo;
    private String desde;
    private String hacia;
    private String tow;
    private String fuel_min;
    private String fuel_tax_in;
    private String fuel_tax_out;
    private String trip_fuel;
    private String ff_eng1;
    private String ff_eng2;
    private String altura;
    private String velocidad;
    private String fuel_reman;

    public FuelRegistro() {
        this.numvuelo = numvuelo;
        this.desde = desde;
        this.hacia = hacia;
        this.tow = tow;
        this.fuel_min = fuel_min;
        this.fuel_tax_in = fuel_tax_in;
        this.fuel_tax_out = fuel_tax_out;
        this.trip_fuel = trip_fuel;
        this.ff_eng1 = ff_eng1;
        this.ff_eng2 = ff_eng2;
        this.altura = altura;
        this.velocidad = velocidad;
        this.fuel_reman = fuel_reman;
    }

    public String getNumvuelo() {
        return numvuelo;
    }

    public void setNumvuelo(String numvuelo) {
        this.numvuelo = numvuelo;
    }

    public String getDesde() {
        return desde;
    }

    public void setDesde(String desde) {
        this.desde = desde;
    }

    public String getHacia() {
        return hacia;
    }

    public void setHacia(String hacia) {
        this.hacia = hacia;
    }

    public String getTow() {
        return tow;
    }

    public void setTow(String tow) {
        this.tow = tow;
    }

    public String getFuel_min() {
        return fuel_min;
    }

    public void setFuel_min(String fuel_min) {
        this.fuel_min = fuel_min;
    }

    public String getFuel_tax_in() {
        return fuel_tax_in;
    }

    public void setFuel_tax_in(String fuel_tax_in) {
        this.fuel_tax_in = fuel_tax_in;
    }

    public String getFuel_tax_out() {
        return fuel_tax_out;
    }

    public void setFuel_tax_out(String fuel_tax_out) {
        this.fuel_tax_out = fuel_tax_out;
    }

    public String getTrip_fuel() {
        return trip_fuel;
    }

    public void setTrip_fuel(String trip_fuel) {
        this.trip_fuel = trip_fuel;
    }

    public String getFf_eng1() {
        return ff_eng1;
    }

    public void setFf_eng1(String ff_eng1) {
        this.ff_eng1 = ff_eng1;
    }

    public String getFf_eng2() {
        return ff_eng2;
    }

    public void setFf_eng2(String ff_eng2) {
        this.ff_eng2 = ff_eng2;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(String velocidad) {
        this.velocidad = velocidad;
    }

    public String getFuel_reman() {
        return fuel_reman;
    }

    public void setFuel_reman(String fuel_reman) {
        this.fuel_reman = fuel_reman;
    }
}
