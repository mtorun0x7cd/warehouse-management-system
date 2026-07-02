/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wawi.datenhaltung.wawidbmodel.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mert Torun (mtorun0x7cd), M.Sc. <info@mtorun0x7cd.com>
 */
@Entity
@Table(name = "lieferant")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Lieferant.findAll", query = "SELECT l FROM Lieferant l"),
    @NamedQuery(name = "Lieferant.findByLfid", query = "SELECT l FROM Lieferant l WHERE l.lfid = :lfid"),
    @NamedQuery(name = "Lieferant.findByTelefon", query = "SELECT l FROM Lieferant l WHERE l.telefon = :telefon"),
    @NamedQuery(name = "Lieferant.findByAktiv", query = "SELECT l FROM Lieferant l WHERE l.aktiv = :aktiv")
})
public class Lieferant implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "lfid")
    private Integer lfid;
    @Basic(optional = false)
    @Lob
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Lob
    @Column(name = "vorname")
    private String vorname;
    @Basic(optional = false)
    @Column(name = "telefon")
    private String telefon;
    @Basic(optional = false)
    @Lob
    @Column(name = "email")
    private String email;
    @Lob
    @Column(name = "firma")
    private String firma;
    @Basic(optional = false)
    @Column(name = "aktiv")
    private boolean aktiv;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lieferant")
    private List<Einlieferung> einlieferungList;

    public Lieferant()
    {
    }

    public Lieferant(Integer lfid)
    {
        this.lfid = lfid;
    }

    public Lieferant(Integer lfid, String name, String vorname, String telefon, String email, boolean aktiv)
    {
        this.lfid = lfid;
        this.name = name;
        this.vorname = vorname;
        this.telefon = telefon;
        this.email = email;
        this.aktiv = aktiv;
    }

    public Integer getLfid()
    {
        return lfid;
    }

    public void setLfid(Integer lfid)
    {
        this.lfid = lfid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getVorname()
    {
        return vorname;
    }

    public void setVorname(String vorname)
    {
        this.vorname = vorname;
    }

    public String getTelefon()
    {
        return telefon;
    }

    public void setTelefon(String telefon)
    {
        this.telefon = telefon;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getFirma()
    {
        return firma;
    }

    public void setFirma(String firma)
    {
        this.firma = firma;
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
    public List<Einlieferung> getEinlieferungList()
    {
        return einlieferungList;
    }

    public void setEinlieferungList(List<Einlieferung> einlieferungList)
    {
        this.einlieferungList = einlieferungList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (lfid != null ? lfid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lieferant))
            return false;
        Lieferant other = (Lieferant) object;
        if ((this.lfid == null && other.lfid != null) || (this.lfid != null && !this.lfid.equals(other.lfid)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "wawi.datenhaltung.wawidbmodel.entities.Lieferant[ lfid=" + lfid + " ]";
    }
    
}
