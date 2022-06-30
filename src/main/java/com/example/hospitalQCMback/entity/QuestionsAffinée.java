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
@Table(name = "questions_affin\u00e9e")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuestionsAffin\u00e9e.findAll", query = "SELECT q FROM QuestionsAffin\u00e9e q"),
    @NamedQuery(name = "QuestionsAffin\u00e9e.findByIdQuestionAffin\u00e9e", query = "SELECT q FROM QuestionsAffin\u00e9e q WHERE q.idQuestionAffin\u00e9e = :idQuestionAffin\u00e9e"),
    @NamedQuery(name = "QuestionsAffin\u00e9e.findByTexteQuestionAffin\u00e9e", query = "SELECT q FROM QuestionsAffin\u00e9e q WHERE q.texteQuestionAffin\u00e9e = :texteQuestionAffin\u00e9e")})
public class QuestionsAffinée implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_question_affin\u00e9e")
    private Integer idQuestionAffinée;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "texte_question_affin\u00e9e")
    private String texteQuestionAffinée;
//    @JoinColumn(name = "id_reponse_final", referencedColumnName = "id_reponse_final")
//    @ManyToOne(optional = false)
//    private ReponseFinal idReponseFinal;

    public QuestionsAffinée() {
    }

    public QuestionsAffinée(Integer idQuestionAffinée) {
        this.idQuestionAffinée = idQuestionAffinée;
    }

    public QuestionsAffinée(Integer idQuestionAffinée, String texteQuestionAffinée) {
        this.idQuestionAffinée = idQuestionAffinée;
        this.texteQuestionAffinée = texteQuestionAffinée;
    }

    public Integer getIdQuestionAffinée() {
        return idQuestionAffinée;
    }

    public void setIdQuestionAffinée(Integer idQuestionAffinée) {
        this.idQuestionAffinée = idQuestionAffinée;
    }

    public String getTexteQuestionAffinée() {
        return texteQuestionAffinée;
    }

    public void setTexteQuestionAffinée(String texteQuestionAffinée) {
        this.texteQuestionAffinée = texteQuestionAffinée;
    }

//    public ReponseFinal getIdReponseFinal() {
//        return idReponseFinal;
//    }
//
//    public void setIdReponseFinal(ReponseFinal idReponseFinal) {
//        this.idReponseFinal = idReponseFinal;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idQuestionAffinée != null ? idQuestionAffinée.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuestionsAffinée)) {
            return false;
        }
        QuestionsAffinée other = (QuestionsAffinée) object;
        if ((this.idQuestionAffinée == null && other.idQuestionAffinée != null) || (this.idQuestionAffinée != null && !this.idQuestionAffinée.equals(other.idQuestionAffinée))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.hospitalQCMback.entity.QuestionsAffin\u00e9e[ idQuestionAffin\u00e9e=" + idQuestionAffinée + " ]";
    }
    
}
