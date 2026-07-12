/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wawi.datenhaltung.wawidbmodel.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mert Torun (mtorun0x7cd), M.Sc. <info@mtorun0x7cd.com>
 */
@Entity
@Table(name = "nachricht")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Nachricht.findAll", query = "SELECT n FROM Nachricht n"),
    @NamedQuery(name = "Nachricht.findByNid", query = "SELECT n FROM Nachricht n WHERE n.nid = :nid"),
    @NamedQuery(name = "Nachricht.findByCreated", query = "SELECT n FROM Nachricht n WHERE n.created = :created"),
    @NamedQuery(name = "Nachricht.findByStatus", query = "SELECT n FROM Nachricht n WHERE n.status = :status"),
    @NamedQuery(name = "Nachricht.findByTyp", query = "SELECT n FROM Nachricht n WHERE n.typ = :typ")
})
public class Nachricht implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "nid")
    private Integer nid;
    @Basic(optional = false)
    @Lob
    @Column(name = "betreff")
    private String betreff;
    @Basic(optional = false)
    @Lob
    @Column(name = "nachricht")
    private String nachricht;
    @Basic(optional = false)
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @Column(name = "typ")
    private String typ;
    @JoinColumn(name = "kunde", referencedColumnName = "kid")
    @ManyToOne(optional = false)
    private Kunde kunde;

    public Nachricht()
    {
    }

    public Nachricht(Integer nid)
    {
        this.nid = nid;
    }

    public Nachricht(Integer nid, String betreff, String nachricht, Date created, String status, String typ)
    {
        this.nid = nid;
        this.betreff = betreff;
        this.nachricht = nachricht;
        this.created = created;
        this.status = status;
        this.typ = typ;
    }

    public Integer getNid()
    {
        return nid;
    }

    public void setNid(Integer nid)
    {
        this.nid = nid;
    }

    public String getBetreff()
    {
        return betreff;
    }

    public void setBetreff(String betreff)
    {
        this.betreff = betreff;
    }

    public String getNachricht()
    {
        return nachricht;
    }

    public void setNachricht(String nachricht)
    {
        this.nachricht = nachricht;
    }

    public Date getCreated()
    {
        return created;
    }

    public void setCreated(Date created)
    {
        this.created = created;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getTyp()
    {
        return typ;
    }

    public void setTyp(String typ)
    {
        this.typ = typ;
    }

    public Kunde getKunde()
    {
        return kunde;
    }

    public void setKunde(Kunde kunde)
    {
        this.kunde = kunde;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (nid != null ? nid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nachricht))
            return false;
        Nachricht other = (Nachricht) object;
        if ((this.nid == null && other.nid != null) || (this.nid != null && !this.nid.equals(other.nid)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "wawi.datenhaltung.wawidbmodel.entities.Nachricht[ nid=" + nid + " ]";
    }
    
}
