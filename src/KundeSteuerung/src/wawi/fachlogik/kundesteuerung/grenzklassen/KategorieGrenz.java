package wawi.fachlogik.kundesteuerung.grenzklassen;

public class KategorieGrenz {

    private Integer katid;
    private Integer parentkatid;
    private String name;
    private String beschreibung;

    public KategorieGrenz() {
    }

    public KategorieGrenz(Integer katid, Integer parentkatid, String name, String beschreibung) {
        this.katid = katid;
        this.parentkatid = parentkatid;
        this.name = name;
        this.beschreibung = beschreibung;
    }

    public Integer getKatid() {
        return katid;
    }

    public Integer getParentkatid() {
        return parentkatid;
    }

    public String getName() {
        return name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setKatid(Integer katid) {
        this.katid = katid;
    }

    public void setParentkatid(Integer parentkatid) {
        this.parentkatid = parentkatid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
}
