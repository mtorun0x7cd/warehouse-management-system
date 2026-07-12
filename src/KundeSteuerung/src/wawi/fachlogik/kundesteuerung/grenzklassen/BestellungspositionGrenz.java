package wawi.fachlogik.kundesteuerung.grenzklassen;

public class BestellungspositionGrenz {

    private Integer bpid;
    private Integer anzahl;
    private ProduktGrenz produkt;

    public void setProdukt(ProduktGrenz produkt) {
        this.produkt = produkt;
    }

    public ProduktGrenz getProdukt() {
        return produkt;
    }

    public BestellungspositionGrenz() {
    }

    public BestellungspositionGrenz(Integer bpid, Integer anzahl, ProduktGrenz produkt) {
        this.bpid = bpid;
        this.anzahl = anzahl;
        this.produkt = produkt;
    }

    public Integer getBpid() {
        return bpid;
    }

    public Integer getAnzahl() {
        return anzahl;
    }

    public void setBpid(Integer bpid) {
        this.bpid = bpid;
    }

    public void setAnzahl(Integer anzahl) {
        this.anzahl = anzahl;
    }
}
