/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.hospitalQCMback.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author y.goudmant
 */
@Entity
@Table(name = "medecin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medecin.findAll", query = "SELECT m FROM Medecin m"),
    @NamedQuery(name = "Medecin.findByIdMedecin", query = "SELECT m FROM Medecin m WHERE m.idMedecin = :idMedecin"),
    @NamedQuery(name = "Medecin.findByIdTheme", query = "SELECT m FROM Medecin m WHERE m.idTheme = :idTheme"),
    @NamedQuery(name = "Medecin.findByNumeroInami", query = "SELECT m FROM Medecin m WHERE m.numeroInami = :numeroInami")})
public class Medecin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_medecin")
    private Integer idMedecin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_theme")
    private int idTheme;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_inami")
    private int numeroInami;
//    @OneToMany(mappedBy = "idMedecin")
//    private Set<ReponseFinal> reponseFinalSet;

    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @OneToOne(optional = false)
    private UserConnect idUser;

    public Medecin() {
    }

    public Medecin(Integer idMedecin) {
        this.idMedecin = idMedecin;
    }

    public Medecin(Integer idMedecin, int idTheme, int numeroInami) {
        this.idMedecin = idMedecin;
        this.idTheme = idTheme;
        this.numeroInami = numeroInami;
    }

    public Integer getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(Integer idMedecin) {
        this.idMedecin = idMedecin;
    }

    public int getIdTheme() {
        return idTheme;
    }

    public void setIdTheme(int idTheme) {
        this.idTheme = idTheme;
    }

    public int getNumeroInami() {
        return numeroInami;
    }

    public void setNumeroInami(int numeroInami) {
        this.numeroInami = numeroInami;
    }

//    @XmlTransient
//    public Set<ReponseFinal> getReponseFinalSet() {
//        return reponseFinalSet;
//    }
//
//    public void setReponseFinalSet(Set<ReponseFinal> reponseFinalSet) {
//        this.reponseFinalSet = reponseFinalSet;
//    }

//    @XmlTransient
//    public Set<Contact> getContactSet() {
//        return contactSet;
//    }
//
//    public void setContactSet(Set<Contact> contactSet) {
//        this.contactSet = contactSet;
//    }

    public UserConnect getIdUser() {
        return idUser;
    }

    public void setIdUser(UserConnect idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMedecin != null ? idMedecin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medecin)) {
            return false;
        }
        Medecin other = (Medecin) object;
        if ((this.idMedecin == null && other.idMedecin != null) || (this.idMedecin != null && !this.idMedecin.equals(other.idMedecin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.hospitalQCMback.entity.Medecin[ idMedecin=" + idMedecin + " ]";
    }
    
}
