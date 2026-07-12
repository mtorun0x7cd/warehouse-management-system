package wawi.fachlogik.kundesteuerung.grenzklassen;

import java.math.BigDecimal;
import java.util.Date;

public class ProduktGrenz {

    private Integer prodid;
    private String name;
    private String beschreibung;
    private Date angelegt;
    private Integer stueckzahl;
    private BigDecimal nettopreis;
    private Integer mwstsatz;
    private boolean aktiv;
    private KategorieGrenz kategorie;
    private LagerortGrenz lagerort;

    public ProduktGrenz() {
    }

    public ProduktGrenz(Integer prodid, String name, String beschreibung, Date angelegt, Integer stueckzahl, BigDecimal nettopreis, Integer mwstsatz, boolean aktiv) {
        this.prodid = prodid;
        this.name = name;
        this.beschreibung = beschreibung;
        this.angelegt = angelegt;
        this.stueckzahl = stueckzahl;
        this.nettopreis = nettopreis;
        this.mwstsatz = mwstsatz;
        this.aktiv = aktiv;
    }

    public Integer getProdid() {
        return prodid;
    }

    public String getName() {
        return name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public Date getAngelegt() {
        return angelegt;
    }

    public Integer getStueckzahl() {
        return stueckzahl;
    }

    public BigDecimal getNettopreis() {
        return nettopreis;
    }

    public Integer getMwstsatz() {
        return mwstsatz;
    }

    public boolean isAktiv() {
        return aktiv;
    }

    public KategorieGrenz getKategorie() {
        return kategorie;
    }

    public LagerortGrenz getLagerort() {
        return lagerort;
    }

    public void setProdid(Integer prodid) {
        this.prodid = prodid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public void setAngelegt(Date angelegt) {
        this.angelegt = angelegt;
    }

    public void setStueckzahl(Integer stueckzahl) {
        this.stueckzahl = stueckzahl;
    }

    public void setNettopreis(BigDecimal nettopreis) {
        this.nettopreis = nettopreis;
    }

    public void setMwstsatz(Integer mwstsatz) {
        this.mwstsatz = mwstsatz;
    }

    public void setAktiv(boolean aktiv) {
        this.aktiv = aktiv;
    }

    public void setKategorie(KategorieGrenz kategorie) {
        this.kategorie = kategorie;
    }

    public void setLagerort(LagerortGrenz lagerort) {
        this.lagerort = lagerort;
    }
}
