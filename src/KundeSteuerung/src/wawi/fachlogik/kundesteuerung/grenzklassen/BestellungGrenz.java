package wawi.fachlogik.kundesteuerung.grenzklassen;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class BestellungGrenz {

    private Integer bid;
    private String lieferadresse;
    private String rechnungsadresse;
    private Date angelegt;
    private EnumBestellStatus status;
    private BigDecimal gesamtNetto;
    private BigDecimal gesamtBrutto;
    private List<BestellungspositionGrenz> bestellungspositionsList;
    private KundeGrenz kunde;

    public BestellungGrenz() {

    }

    public BestellungGrenz(Integer bid, String lieferadresse, String rechnungsadresse, Date created, EnumBestellStatus status, BigDecimal gesamtNetto, BigDecimal gesamtBrutto, KundeGrenz kunde) {
        this.bid = bid;
        this.lieferadresse = lieferadresse;
        this.rechnungsadresse = rechnungsadresse;
        this.angelegt = created;
        this.status = status;
        this.gesamtNetto = gesamtNetto;
        this.gesamtBrutto = gesamtBrutto;
        this.kunde = kunde;
    }

    public BestellungGrenz(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Integer getBid() {
        return bid;
    }

    public String getLieferadresse() {
        return lieferadresse;
    }

    public String getRechnungsadresse() {
        return rechnungsadresse;
    }

    public Date getAngelegt() {
        return angelegt;
    }

    public EnumBestellStatus getStatus() {
        return status;
    }

    public BigDecimal getGesamtNetto() {
        return gesamtNetto;
    }

    public BigDecimal getGesamtBrutto() {
        return gesamtBrutto;
    }

    public List<BestellungspositionGrenz> getBestellungspositionsList() {
        return bestellungspositionsList;
    }

    public KundeGrenz getKunde() {
        return kunde;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public void setLieferadresse(String lieferadresse) {
        this.lieferadresse = lieferadresse;
    }

    public void setRechnungsadresse(String rechnungsadresse) {
        this.rechnungsadresse = rechnungsadresse;
    }

    public void setAngelegt(Date angelegt) {
        this.angelegt = angelegt;
    }

    public void setStatus(EnumBestellStatus status) {
        this.status = status;
    }

    public void setGesamtNetto(BigDecimal gesamtNetto) {
        this.gesamtNetto = gesamtNetto;
    }

    public void setGesamtBrutto(BigDecimal gesamtBrutto) {
        this.gesamtBrutto = gesamtBrutto;
    }

    public void setBestellungspositionsList(List<BestellungspositionGrenz> bestellungspositionsList) {
        this.bestellungspositionsList = bestellungspositionsList;
    }

    public void setKunde(KundeGrenz kunde) {
        this.kunde = kunde;
    }
}
