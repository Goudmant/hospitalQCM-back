/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.hospitalQCMback.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author y.goudmant
 */
@Entity
@Table(name = "user_connect")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserConnect.findAll", query = "SELECT u FROM UserConnect u"),
    @NamedQuery(name = "UserConnect.findByIdUser", query = "SELECT u FROM UserConnect u WHERE u.idUser = :idUser"),
    @NamedQuery(name = "UserConnect.findByNomUser", query = "SELECT u FROM UserConnect u WHERE u.nomUser = :nomUser"),
    @NamedQuery(name = "UserConnect.findByPhoneUser", query = "SELECT u FROM UserConnect u WHERE u.phoneUser = :phoneUser"),
    @NamedQuery(name = "UserConnect.findByPrenomUser", query = "SELECT u FROM UserConnect u WHERE u.prenomUser = :prenomUser"),
    @NamedQuery(name = "UserConnect.findByPassword", query = "SELECT u FROM UserConnect u WHERE u.password = :password"),
    @NamedQuery(name = "UserConnect.findByMailUser", query = "SELECT u FROM UserConnect u WHERE u.mailUser = :mailUser")})
public class UserConnect implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_user")
    private Integer idUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "nom_user")
    private String nomUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "phone_user")
    private int phoneUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "prenom_user")
    private String prenomUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "mail_user")
    private String mailUser;
    @JoinColumn(name = "id_roles", referencedColumnName = "id_roles")
    @ManyToOne(optional = false)
    private Roles idRoles;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private Set<Contact> contactSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private Set<ReponsesUser> reponsesUserSet;
//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idUser")
//    @JsonIgnore
//    private Medecin medecin;

    public UserConnect() {
    }

    public UserConnect(Integer idUser) {
        this.idUser = idUser;
    }

    public UserConnect(Integer idUser, String nomUser, int phoneUser, String prenomUser, String password, String mailUser) {
        this.idUser = idUser;
        this.nomUser = nomUser;
        this.phoneUser = phoneUser;
        this.prenomUser = prenomUser;
        this.password = password;
        this.mailUser = mailUser;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public int getPhoneUser() {
        return phoneUser;
    }

    public void setPhoneUser(int phoneUser) {
        this.phoneUser = phoneUser;
    }

    public String getPrenomUser() {
        return prenomUser;
    }

    public void setPrenomUser(String prenomUser) {
        this.prenomUser = prenomUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMailUser() {
        return mailUser;
    }

    public void setMailUser(String mailUser) {
        this.mailUser = mailUser;
    }

    public Roles getIdRoles() {
        return idRoles;
    }

    public void setIdRoles(Roles idRoles) {
        this.idRoles = idRoles;
    }

    @XmlTransient
    public Set<Contact> getContactSet() {
        return contactSet;
    }

    public void setContactSet(Set<Contact> contactSet) {
        this.contactSet = contactSet;
    }

    @XmlTransient
    public Set<ReponsesUser> getReponsesUserSet() {
        return reponsesUserSet;
    }

    public void setReponsesUserSet(Set<ReponsesUser> reponsesUserSet) {
        this.reponsesUserSet = reponsesUserSet;
    }

//    public Medecin getMedecin() {
//        return medecin;
//    }
//
//    public void setMedecin(Medecin medecin) {
//        this.medecin = medecin;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserConnect)) {
            return false;
        }
        UserConnect other = (UserConnect) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.hospitalQCMback.entity.UserConnect[ idUser=" + idUser + " ]";
    }
    
}
