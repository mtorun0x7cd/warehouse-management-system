package wawi.fachlogik.kundesteuerung.grenzklassen;

import java.util.Date;

public class NachrichtGrenz {

    private Integer nid;
    private String betreff;
    private String nachricht;
    private Date angelegt;
    private EnumNachrichtStatus status;
    private EnumNachrichtTyp typ;
    private KundeGrenz kunde;

    public NachrichtGrenz() {
    }

    public NachrichtGrenz(Integer nid, String betreff, String nachricht, Date angelegt, EnumNachrichtStatus status, EnumNachrichtTyp typ, KundeGrenz kunde) {
        this.nid = nid;
        this.betreff = betreff;
        this.nachricht = nachricht;
        this.angelegt = angelegt;
        this.status = status;
        this.typ = typ;
        this.kunde = kunde;
    }

    public Integer getNid() {
        return nid;
    }

    public String getBetreff() {
        return betreff;
    }

    public String getNachricht() {
        return nachricht;
    }

    public Date getAngelegt() {
        return angelegt;
    }

    public EnumNachrichtStatus getStatus() {
        return status;
    }

    public EnumNachrichtTyp getTyp() {
        return typ;
    }

    public KundeGrenz getKunde() {
        return kunde;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public void setBetreff(String betreff) {
        this.betreff = betreff;
    }

    public void setNachricht(String nachricht) {
        this.nachricht = nachricht;
    }

    public void setAngelegt(Date angelegt) {
        this.angelegt = angelegt;
    }

    public void setStatus(EnumNachrichtStatus status) {
        this.status = status;
    }

    public void setTyp(EnumNachrichtTyp typ) {
        this.typ = typ;
    }

    public void setKunde(KundeGrenz kunde) {
        this.kunde = kunde;
    }
}
