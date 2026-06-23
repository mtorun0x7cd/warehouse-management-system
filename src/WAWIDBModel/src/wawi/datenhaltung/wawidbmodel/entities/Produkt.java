/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wawi.datenhaltung.wawidbmodel.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mert Torun (mtorun0x7cd), M.Sc. <info@mtorun0x7cd.com>
 */
@Entity
@Table(name = "produkt")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Produkt.findAll", query = "SELECT p FROM Produkt p"),
    @NamedQuery(name = "Produkt.findByProdid", query = "SELECT p FROM Produkt p WHERE p.prodid = :prodid"),
    @NamedQuery(name = "Produkt.findByAngelegt", query = "SELECT p FROM Produkt p WHERE p.angelegt = :angelegt"),
    @NamedQuery(name = "Produkt.findByStueckzahl", query = "SELECT p FROM Produkt p WHERE p.stueckzahl = :stueckzahl"),
    @NamedQuery(name = "Produkt.findByNettopreis", query = "SELECT p FROM Produkt p WHERE p.nettopreis = :nettopreis"),
    @NamedQuery(name = "Produkt.findByMwstsatz", query = "SELECT p FROM Produkt p WHERE p.mwstsatz = :mwstsatz"),
    @NamedQuery(name = "Produkt.findByAktiv", query = "SELECT p FROM Produkt p WHERE p.aktiv = :aktiv")
})
public class Produkt implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prodid")
    private Integer prodid;
    @Basic(optional = false)
    @Lob
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Lob
    @Column(name = "beschreibung")
    private String beschreibung;
    @Basic(optional = false)
    @Column(name = "angelegt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date angelegt;
    @Basic(optional = false)
    @Column(name = "stueckzahl")
    private int stueckzahl;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "nettopreis")
    private BigDecimal nettopreis;
    @Basic(optional = false)
    @Column(name = "mwstsatz")
    private int mwstsatz;
    @Basic(optional = false)
    @Column(name = "aktiv")
    private boolean aktiv;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produkt")
    private List<Bestellungsposition> bestellungspositionList;
    @JoinColumn(name = "kategorie", referencedColumnName = "katid")
    @ManyToOne(optional = false)
    private Kategorie kategorie;
    @JoinColumn(name = "lagerort", referencedColumnName = "lgortid")
    @ManyToOne(optional = false)
    private Lagerort lagerort;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produkt")
    private List<Lieferposition> lieferpositionList;

    public Produkt()
    {
    }

    public Produkt(Integer prodid)
    {
        this.prodid = prodid;
    }

    public Produkt(Integer prodid, String name, String beschreibung, Date angelegt, int stueckzahl, BigDecimal nettopreis, int mwstsatz, boolean aktiv)
    {
        this.prodid = prodid;
        this.name = name;
        this.beschreibung = beschreibung;
        this.angelegt = angelegt;
        this.stueckzahl = stueckzahl;
        this.nettopreis = nettopreis;
        this.mwstsatz = mwstsatz;
        this.aktiv = aktiv;
    }

    public Integer getProdid()
    {
        return prodid;
    }

    public void setProdid(Integer prodid)
    {
        this.prodid = prodid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getBeschreibung()
    {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung)
    {
        this.beschreibung = beschreibung;
    }

    public Date getAngelegt()
    {
        return angelegt;
    }

    public void setAngelegt(Date angelegt)
    {
        this.angelegt = angelegt;
    }

    public int getStueckzahl()
    {
        return stueckzahl;
    }

    public void setStueckzahl(int stueckzahl)
    {
        this.stueckzahl = stueckzahl;
    }

    public BigDecimal getNettopreis()
    {
        return nettopreis;
    }

    public void setNettopreis(BigDecimal nettopreis)
    {
        this.nettopreis = nettopreis;
    }

    public int getMwstsatz()
    {
        return mwstsatz;
    }

    public void setMwstsatz(int mwstsatz)
    {
        this.mwstsatz = mwstsatz;
    }

    public boolean getAktiv()
    {
        return aktiv;
    }

    public void setAktiv(boolean aktiv)
    {
        this.aktiv = aktiv;
    }

    @XmlTransient
    public List<Bestellungsposition> getBestellungspositionList()
    {
        return bestellungspositionList;
    }

    public void setBestellungspositionList(List<Bestellungsposition> bestellungspositionList)
    {
        this.bestellungspositionList = bestellungspositionList;
    }

    public Kategorie getKategorie()
    {
        return kategorie;
    }

    public void setKategorie(Kategorie kategorie)
    {
        this.kategorie = kategorie;
    }

    public Lagerort getLagerort()
    {
        return lagerort;
    }

    public void setLagerort(Lagerort lagerort)
    {
        this.lagerort = lagerort;
    }

    @XmlTransient
    public List<Lieferposition> getLieferpositionList()
    {
        return lieferpositionList;
    }

    public void setLieferpositionList(List<Lieferposition> lieferpositionList)
    {
        this.lieferpositionList = lieferpositionList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (prodid != null ? prodid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produkt))
            return false;
        Produkt other = (Produkt) object;
        if ((this.prodid == null && other.prodid != null) || (this.prodid != null && !this.prodid.equals(other.prodid)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "wawi.datenhaltung.wawidbmodel.entities.Produkt[ prodid=" + prodid + " ]";
    }
    
}
