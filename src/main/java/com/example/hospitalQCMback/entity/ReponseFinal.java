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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "reponse_final")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReponseFinal.findAll", query = "SELECT r FROM ReponseFinal r"),
    @NamedQuery(name = "ReponseFinal.findByIdReponseFinal", query = "SELECT r FROM ReponseFinal r WHERE r.idReponseFinal = :idReponseFinal"),
    @NamedQuery(name = "ReponseFinal.findByIdResultatValeur", query = "SELECT r FROM ReponseFinal r WHERE r.idResultatValeur = :idResultatValeur")})
public class ReponseFinal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_reponse_final")
    private Integer idReponseFinal;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "texte_question")
    private String texteQuestion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_resultat_valeur")
    private int idResultatValeur;
    @JoinColumn(name = "id_medecin", referencedColumnName = "id_medecin")
    @ManyToOne
    private Medecin idMedecin;
    @OneToMany
    private Set<QuestionsAffinée> questionsAffinéeSet;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idReponseFinal")
//    private Set<Contact> contactSet;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idReponseFinal")
//    private Set<ReponsesUser> reponsesUserSet;

    public ReponseFinal() {
    }

    public ReponseFinal(Integer idReponseFinal) {
        this.idReponseFinal = idReponseFinal;
    }

    public ReponseFinal(Integer idReponseFinal, String texteQuestion, int idResultatValeur) {
        this.idReponseFinal = idReponseFinal;
        this.texteQuestion = texteQuestion;
        this.idResultatValeur = idResultatValeur;
    }

    public Integer getIdReponseFinal() {
        return idReponseFinal;
    }

    public void setIdReponseFinal(Integer idReponseFinal) {
        this.idReponseFinal = idReponseFinal;
    }

    public String getTexteQuestion() {
        return texteQuestion;
    }

    public void setTexteQuestion(String texteQuestion) {
        this.texteQuestion = texteQuestion;
    }

    public int getIdResultatValeur() {
        return idResultatValeur;
    }

    public void setIdResultatValeur(int idResultatValeur) {
        this.idResultatValeur = idResultatValeur;
    }

    public Medecin getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(Medecin idMedecin) {
        this.idMedecin = idMedecin;
    }

    @XmlTransient
    public Set<QuestionsAffinée> getQuestionsAffinéeSet() {
        return questionsAffinéeSet;
    }

    public void setQuestionsAffinéeSet(Set<QuestionsAffinée> questionsAffinéeSet) {
        this.questionsAffinéeSet = questionsAffinéeSet;
    }

    @XmlTransient
//    public Set<Contact> getContactSet() {
//        return contactSet;
//    }
//
//    public void setContactSet(Set<Contact> contactSet) {
//        this.contactSet = contactSet;
//    }
//
//    @XmlTransient
//    public Set<ReponsesUser> getReponsesUserSet() {
//        return reponsesUserSet;
//    }
//
//    public void setReponsesUserSet(Set<ReponsesUser> reponsesUserSet) {
//        this.reponsesUserSet = reponsesUserSet;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReponseFinal != null ? idReponseFinal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReponseFinal)) {
            return false;
        }
        ReponseFinal other = (ReponseFinal) object;
        if ((this.idReponseFinal == null && other.idReponseFinal != null) || (this.idReponseFinal != null && !this.idReponseFinal.equals(other.idReponseFinal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.hospitalQCMback.entity.ReponseFinal[ idReponseFinal=" + idReponseFinal + " ]";
    }
    
}
