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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author y.goudmant
 */
@Entity
@Table(name = "contact")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contact.findAll", query = "SELECT c FROM Contact c"),
    @NamedQuery(name = "Contact.findByIdContact", query = "SELECT c FROM Contact c WHERE c.idContact = :idContact"),
    @NamedQuery(name = "Contact.findByDateContact", query = "SELECT c FROM Contact c WHERE c.dateContact = :dateContact"),
    @NamedQuery(name = "Contact.findByTexteContact", query = "SELECT c FROM Contact c WHERE c.texteContact = :texteContact")})
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_contact")
    private Integer idContact;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_contact")
    @Temporal(TemporalType.DATE)
    private Date dateContact;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "texte_contact")
    private String texteContact;
    @JoinColumn(name = "id_medecin", referencedColumnName = "id_medecin")
    @ManyToOne(optional = false)
    private Medecin idMedecin;
    @JoinColumn(name = "id_reponse_final", referencedColumnName = "id_reponse_final")
    @ManyToOne(optional = false)
    private ReponseFinal idReponseFinal;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne(optional = false)
    private UserConnect idUser;

    public Contact() {
    }

    public Contact(Integer idContact) {
        this.idContact = idContact;
    }

    public Contact(Integer idContact, Date dateContact, String texteContact) {
        this.idContact = idContact;
        this.dateContact = dateContact;
        this.texteContact = texteContact;
    }

    public Integer getIdContact() {
        return idContact;
    }

    public void setIdContact(Integer idContact) {
        this.idContact = idContact;
    }

    public Date getDateContact() {
        return dateContact;
    }

    public void setDateContact(Date dateContact) {
        this.dateContact = dateContact;
    }

    public String getTexteContact() {
        return texteContact;
    }

    public void setTexteContact(String texteContact) {
        this.texteContact = texteContact;
    }

    public Medecin getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(Medecin idMedecin) {
        this.idMedecin = idMedecin;
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
        hash += (idContact != null ? idContact.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contact)) {
            return false;
        }
        Contact other = (Contact) object;
        if ((this.idContact == null && other.idContact != null) || (this.idContact != null && !this.idContact.equals(other.idContact))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.hospitalQCMback.entity.Contact[ idContact=" + idContact + " ]";
    }
    
}
