/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.hospitalQCMback.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author y.goudmant
 */
@Entity
@Table(name = "reponses_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReponsesUser.findAll", query = "SELECT r FROM ReponsesUser r"),
    @NamedQuery(name = "ReponsesUser.findByIdReponseUser", query = "SELECT r FROM ReponsesUser r WHERE r.idReponseUser = :idReponseUser"),
    @NamedQuery(name = "ReponsesUser.findByDate", query = "SELECT r FROM ReponsesUser r WHERE r.date = :date"),
    @NamedQuery(name = "ReponsesUser.findByValeurFinal", query = "SELECT r FROM ReponsesUser r WHERE r.valeurFinal = :valeurFinal")})
public class ReponsesUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_reponse_user")
    private Integer idReponseUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valeur_final")
    private int valeurFinal;
    @JoinColumn(name = "id_reponse_final", referencedColumnName = "id_reponse_final")
    @ManyToOne(optional = false)
    private ReponseFinal idReponseFinal;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne(optional = false)
    private UserConnect idUser;

    public ReponsesUser() {
    }

    public ReponsesUser(Integer idReponseUser) {
        this.idReponseUser = idReponseUser;
    }

    public ReponsesUser(Integer idReponseUser, Date date, int valeurFinal) {
        this.idReponseUser = idReponseUser;
        this.date = date;
        this.valeurFinal = valeurFinal;
    }

    public Integer getIdReponseUser() {
        return idReponseUser;
    }

    public void setIdReponseUser(Integer idReponseUser) {
        this.idReponseUser = idReponseUser;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getValeurFinal() {
        return valeurFinal;
    }

    public void setValeurFinal(int valeurFinal) {
        this.valeurFinal = valeurFinal;
    }

    public ReponseFinal getIdReponseFinal() {
        return idReponseFinal;
    }

    public void setIdReponseFinal(ReponseFinal idReponseFinal) {
        this.idReponseFinal = idReponseFinal;
    }

    public UserConnect getIdUser() {
        return idUser;
    }

    public void setIdUser(UserConnect idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReponseUser != null ? idReponseUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReponsesUser)) {
            return false;
        }
        ReponsesUser other = (ReponsesUser) object;
        if ((this.idReponseUser == null && other.idReponseUser != null) || (this.idReponseUser != null && !this.idReponseUser.equals(other.idReponseUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.hospitalQCMback.entity.ReponsesUser[ idReponseUser=" + idReponseUser + " ]";
    }
    
}
