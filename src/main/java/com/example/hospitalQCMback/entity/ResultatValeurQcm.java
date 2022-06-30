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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author y.goudmant
 */
@Entity
@Table(name = "resultat_valeur_qcm")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResultatValeurQcm.findAll", query = "SELECT r FROM ResultatValeurQcm r"),
    @NamedQuery(name = "ResultatValeurQcm.findByIdResultatValeur", query = "SELECT r FROM ResultatValeurQcm r WHERE r.idResultatValeur = :idResultatValeur"),
    @NamedQuery(name = "ResultatValeurQcm.findByIdTheme", query = "SELECT r FROM ResultatValeurQcm r WHERE r.idTheme = :idTheme"),
    @NamedQuery(name = "ResultatValeurQcm.findByValeurMin", query = "SELECT r FROM ResultatValeurQcm r WHERE r.valeurMin = :valeurMin"),
    @NamedQuery(name = "ResultatValeurQcm.findByValeurMax", query = "SELECT r FROM ResultatValeurQcm r WHERE r.valeurMax = :valeurMax")})
public class ResultatValeurQcm implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_resultat_valeur")
    private Integer idResultatValeur;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_theme")
    private int idTheme;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valeur_min")
    private int valeurMin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valeur_max")
    private int valeurMax;

    public ResultatValeurQcm() {
    }

    public ResultatValeurQcm(Integer idResultatValeur) {
        this.idResultatValeur = idResultatValeur;
    }

    public ResultatValeurQcm(Integer idResultatValeur, int idTheme, int valeurMin, int valeurMax) {
        this.idResultatValeur = idResultatValeur;
        this.idTheme = idTheme;
        this.valeurMin = valeurMin;
        this.valeurMax = valeurMax;
    }

    public Integer getIdResultatValeur() {
        return idResultatValeur;
    }

    public void setIdResultatValeur(Integer idResultatValeur) {
        this.idResultatValeur = idResultatValeur;
    }

    public int getIdTheme() {
        return idTheme;
    }

    public void setIdTheme(int idTheme) {
        this.idTheme = idTheme;
    }

    public int getValeurMin() {
        return valeurMin;
    }

    public void setValeurMin(int valeurMin) {
        this.valeurMin = valeurMin;
    }

    public int getValeurMax() {
        return valeurMax;
    }

    public void setValeurMax(int valeurMax) {
        this.valeurMax = valeurMax;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idResultatValeur != null ? idResultatValeur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResultatValeurQcm)) {
            return false;
        }
        ResultatValeurQcm other = (ResultatValeurQcm) object;
        if ((this.idResultatValeur == null && other.idResultatValeur != null) || (this.idResultatValeur != null && !this.idResultatValeur.equals(other.idResultatValeur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.hospitalQCMback.entity.ResultatValeurQcm[ idResultatValeur=" + idResultatValeur + " ]";
    }
    
}
