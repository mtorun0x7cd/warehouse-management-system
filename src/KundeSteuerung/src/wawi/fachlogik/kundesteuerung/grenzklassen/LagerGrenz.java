package wawi.fachlogik.kundesteuerung.grenzklassen;

public class LagerGrenz {

    private Integer lagerid;
    private String name;
    private String adresse;

    public LagerGrenz() {
    }

    public LagerGrenz(Integer lagerid, String name, String adresse) {
        this.lagerid = lagerid;
        this.name = name;
        this.adresse = adresse;
    }

    public Integer getLagerid() {
        return lagerid;
    }

    public String getName() {
        return name;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setLagerid(Integer lagerid) {
        this.lagerid = lagerid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
