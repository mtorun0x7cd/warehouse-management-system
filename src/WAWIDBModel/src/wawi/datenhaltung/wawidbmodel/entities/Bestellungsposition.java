/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wawi.datenhaltung.wawidbmodel.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mert Torun (mtorun0x7cd), M.Sc. <info@mtorun0x7cd.com>
 */
@Entity
@Table(name = "bestellungsposition")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Bestellungsposition.findAll", query = "SELECT b FROM Bestellungsposition b"),
    @NamedQuery(name = "Bestellungsposition.findByBpid", query = "SELECT b FROM Bestellungsposition b WHERE b.bpid = :bpid"),
    @NamedQuery(name = "Bestellungsposition.findByAnzahl", query = "SELECT b FROM Bestellungsposition b WHERE b.anzahl = :anzahl")
})
public class Bestellungsposition implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "bpid")
    private Integer bpid;
    @Basic(optional = false)
    @Column(name = "anzahl")
    private int anzahl;
    @JoinColumn(name = "bestellung", referencedColumnName = "bid")
    @ManyToOne(optional = false)
    private Bestellung bestellung;
    @JoinColumn(name = "produkt", referencedColumnName = "prodid")
    @ManyToOne(optional = false)
    private Produkt produkt;

    public Bestellungsposition()
    {
    }

    public Bestellungsposition(Integer bpid)
    {
        this.bpid = bpid;
    }

    public Bestellungsposition(Integer bpid, int anzahl)
    {
        this.bpid = bpid;
        this.anzahl = anzahl;
    }

    public Integer getBpid()
    {
        return bpid;
    }

    public void setBpid(Integer bpid)
    {
        this.bpid = bpid;
    }

    public int getAnzahl()
    {
        return anzahl;
    }

    public void setAnzahl(int anzahl)
    {
        this.anzahl = anzahl;
    }

    public Bestellung getBestellung()
    {
        return bestellung;
    }

    public void setBestellung(Bestellung bestellung)
    {
        this.bestellung = bestellung;
    }

    public Produkt getProdukt()
    {
        return produkt;
    }

    public void setProdukt(Produkt produkt)
    {
        this.produkt = produkt;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (bpid != null ? bpid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bestellungsposition))
            return false;
        Bestellungsposition other = (Bestellungsposition) object;
        if ((this.bpid == null && other.bpid != null) || (this.bpid != null && !this.bpid.equals(other.bpid)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "wawi.datenhaltung.wawidbmodel.entities.Bestellungsposition[ bpid=" + bpid + " ]";
    }
    
}
