package apps.paquete.andres.operat11;

public class Vuelos {
    //estructura de la tabla 19 registros
    private String numvuelo;
    private String desde;
    private String hacia;
    private String prendida_hor;
    private String prendida_min;
    private String apagada_hor;
    private String apagada_min;
    private String vfr;
    private String ifr;
    private String nocturno;
    private String total_crew;
    private String decolage_hor;
    private String decolage_min;
    private String aterrizaje_hor;
    private String aterrizaje_min;
    private String total_aircraft;
    private Integer num_landing;
    private String pax;
    private String carga;
    private String fuel;

    public Vuelos() {
        this.numvuelo = numvuelo;
        this.desde = desde;
        this.hacia = hacia;
        this.prendida_hor = prendida_hor;
        this.prendida_min = prendida_min;
        this.apagada_hor = apagada_hor;
        this.apagada_min = apagada_min;
        this.vfr = vfr;
        this.ifr = ifr;
        this.nocturno = nocturno;
        this.total_crew = total_crew;
        this.decolage_hor = decolage_hor;
        this.decolage_min = decolage_min;
        this.aterrizaje_hor = aterrizaje_hor;
        this.aterrizaje_min = aterrizaje_min;
        this.num_landing = num_landing;
        this.pax = pax;
        this.carga = carga;
        this.fuel = fuel;
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

    public String getPrendida_hor() {
        return prendida_hor;
    }

    public void setPrendida_hor(String prendida_hor) {
        this.prendida_hor = prendida_hor;
    }

    public String getPrendida_min() {
        return prendida_min;
    }

    public void setPrendida_min(String prendida_min) {
        this.prendida_min = prendida_min;
    }

    public String getApagada_hor() {
        return apagada_hor;
    }

    public void setApagada_hor(String apagada_hor) {
        this.apagada_hor = apagada_hor;
    }

    public String getApagada_min() {
        return apagada_min;
    }

    public void setApagada_min(String apagada_min) {
        this.apagada_min = apagada_min;
    }

    public String getVfr() {
        return vfr;
    }

    public void setVfr(String vfr) {
        this.vfr = vfr;
    }

    public String getIfr() {
        return ifr;
    }

    public void setIfr(String ifr) {
        this.ifr = ifr;
    }

    public String getNocturno() {
        return nocturno;
    }

    public void setNocturno(String nocturno) {
        this.nocturno = nocturno;
    }


    public String getTotal_crew() {
        return total_crew;
    }

    public void setTotal_crew(String total_crew) {
        this.total_crew = total_crew;
    }


    public String getDecolage_hor() {
        return decolage_hor;
    }

    public void setDecolage_hor(String decolage_hor) {
        this.decolage_hor = decolage_hor;
    }

    public String getDecolage_min() {
        return decolage_min;
    }

    public void setDecolage_min(String decolage_min) {
        this.decolage_min = decolage_min;
    }

    public String getAterrizaje_hor() {
        return aterrizaje_hor;
    }

    public void setAterrizaje_hor(String aterrizaje_hor) {
        this.aterrizaje_hor = aterrizaje_hor;
    }

    public String getAterrizaje_min() {
        return aterrizaje_min;
    }

    public void setAterrizaje_min(String aterrizaje_min) {
        this.aterrizaje_min = aterrizaje_min;
    }

    public String getTotal_aircraft() {
        return total_aircraft;
    }

    public void setTotal_aircraft(String total_aircraft) {
        this.total_aircraft = total_aircraft;
    }

    public Integer getNum_landing() {
        return num_landing;
    }

    public void setNum_landing(Integer num_landing) {
        this.num_landing = num_landing;
    }

    public String getPax() {
        return pax;
    }

    public void setPax(String pax) {
        this.pax = pax;
    }

    public String getCarga() {
        return carga;
    }

    public void setCarga(String carga) {
        this.carga = carga;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }
}