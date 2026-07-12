package wawi.fachlogik.kundesteuerung.grenzklassen;

public class LagerortGrenz {

    private Integer lgortid;
    private String bezeichnung;
    private Integer kapazitaet;
    private LagerGrenz lager;

    public LagerortGrenz() {
    }

    public LagerortGrenz(Integer lgortid, String bezeichnung, Integer kapazitaet, LagerGrenz lager) {
        this.lgortid = lgortid;
        this.bezeichnung = bezeichnung;
        this.kapazitaet = kapazitaet;
        this.lager = lager;
    }

    public Integer getLgortid() {
        return lgortid;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public Integer getKapazitaet() {
        return kapazitaet;
    }

    public LagerGrenz getLager() {
        return lager;
    }

    public void setLgortid(Integer lgortid) {
        this.lgortid = lgortid;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public void setKapazitaet(Integer kapazitaet) {
        this.kapazitaet = kapazitaet;
    }

    public void setLager(LagerGrenz lager) {
        this.lager = lager;
    }
}
