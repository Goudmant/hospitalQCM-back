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
@Table(name = "questions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Questions.findAll", query = "SELECT q FROM Questions q"),
    @NamedQuery(name = "Questions.findByIdQuestions", query = "SELECT q FROM Questions q WHERE q.idQuestions = :idQuestions"),
    @NamedQuery(name = "Questions.findByTexteQuestions", query = "SELECT q FROM Questions q WHERE q.texteQuestions = :texteQuestions")})
public class Questions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_questions")
    private Integer idQuestions;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "texte_questions")
    private String texteQuestions;
//    @JoinColumn(name = "id_theme", referencedColumnName = "id_theme")
//    @ManyToOne(optional = false)
//    private Theme idTheme;
    @OneToMany
    private Set<ReponseQuestions> reponseQuestionsSet;

    public Questions() {
    }

    public Questions(Integer idQuestions) {
        this.idQuestions = idQuestions;
    }

    public Questions(Integer idQuestions, String texteQuestions) {
        this.idQuestions = idQuestions;
        this.texteQuestions = texteQuestions;
    }

    public Integer getIdQuestions() {
        return idQuestions;
    }

    public void setIdQuestions(Integer idQuestions) {
        this.idQuestions = idQuestions;
    }

    public String getTexteQuestions() {
        return texteQuestions;
    }

    public void setTexteQuestions(String texteQuestions) {
        this.texteQuestions = texteQuestions;
    }

//    public Theme getIdTheme() {
//        return idTheme;
//    }
//
//    public void setIdTheme(Theme idTheme) {
//        this.idTheme = idTheme;
//    }

    @XmlTransient
    public Set<ReponseQuestions> getReponseQuestionsSet() {
        return reponseQuestionsSet;
    }

    public void setReponseQuestionsSet(Set<ReponseQuestions> reponseQuestionsSet) {
        this.reponseQuestionsSet = reponseQuestionsSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idQuestions != null ? idQuestions.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Questions)) {
            return false;
        }
        Questions other = (Questions) object;
        if ((this.idQuestions == null && other.idQuestions != null) || (this.idQuestions != null && !this.idQuestions.equals(other.idQuestions))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.hospitalQCMback.entity.Questions[ idQuestions=" + idQuestions + " ]";
    }
    
}
