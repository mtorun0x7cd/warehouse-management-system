package wawi.fachlogik.kundesteuerung.grenzklassen;

import java.util.Date;

public class KundeGrenz {

    private Integer kid;
    private String name;
    private String vorname;
    private String adresse;
    private Date angelegt;

    public KundeGrenz() {
    }

    public KundeGrenz(Integer kid, String name, String vorname, String adresse, Date erstellt) {
        this.kid = kid;
        this.name = name;
        this.vorname = vorname;
        this.adresse = adresse;
        this.angelegt = erstellt;
    }

    public Integer getKid() {
        return kid;
    }

    public String getName() {
        return name;
    }

    public String getVorname() {
        return vorname;
    }

    public String getAdresse() {
        return adresse;
    }

    public Date getAngelegt() {
        return angelegt;
    }

    public void setKid(Integer kid) {
        this.kid = kid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setAngelegt(Date angelegt) {
        this.angelegt = angelegt;
    }
}
