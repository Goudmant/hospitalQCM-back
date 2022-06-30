/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.hospitalQCMback.entity;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author y.goudmant
 */
@Entity
@Table(name = "reponse_questions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReponseQuestions.findAll", query = "SELECT r FROM ReponseQuestions r"),
    @NamedQuery(name = "ReponseQuestions.findByIdReponsesQuestions", query = "SELECT r FROM ReponseQuestions r WHERE r.idReponsesQuestions = :idReponsesQuestions"),
    @NamedQuery(name = "ReponseQuestions.findByTexteQuestion", query = "SELECT r FROM ReponseQuestions r WHERE r.texteQuestion = :texteQuestion"),
    @NamedQuery(name = "ReponseQuestions.findByValeur", query = "SELECT r FROM ReponseQuestions r WHERE r.valeur = :valeur")})
public class ReponseQuestions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_reponses_questions")
    private Integer idReponsesQuestions;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "texte_question")
    private String texteQuestion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valeur")
    private int valeur;
//    @JoinColumn(name = "id_questions", referencedColumnName = "id_questions")
//    @ManyToOne(optional = false)
//    private Questions idQuestions;

    public ReponseQuestions() {
    }

    public ReponseQuestions(Integer idReponsesQuestions) {
        this.idReponsesQuestions = idReponsesQuestions;
    }

    public ReponseQuestions(Integer idReponsesQuestions, String texteQuestion, int valeur) {
        this.idReponsesQuestions = idReponsesQuestions;
        this.texteQuestion = texteQuestion;
        this.valeur = valeur;
    }

    public Integer getIdReponsesQuestions() {
        return idReponsesQuestions;
    }

    public void setIdReponsesQuestions(Integer idReponsesQuestions) {
        this.idReponsesQuestions = idReponsesQuestions;
    }

    public String getTexteQuestion() {
        return texteQuestion;
    }

    public void setTexteQuestion(String texteQuestion) {
        this.texteQuestion = texteQuestion;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

//    public Questions getIdQuestions() {
//        return idQuestions;
//    }
//
//    public void setIdQuestions(Questions idQuestions) {
//        this.idQuestions = idQuestions;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReponsesQuestions != null ? idReponsesQuestions.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReponseQuestions)) {
            return false;
        }
        ReponseQuestions other = (ReponseQuestions) object;
        if ((this.idReponsesQuestions == null && other.idReponsesQuestions != null) || (this.idReponsesQuestions != null && !this.idReponsesQuestions.equals(other.idReponsesQuestions))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.hospitalQCMback.entity.ReponseQuestions[ idReponsesQuestions=" + idReponsesQuestions + " ]";
    }
    
}
