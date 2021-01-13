/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entidades;

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
 * @author mjavi
 */
@Entity
@Table(name = "fechas_habiles")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "FechaHabil.findAll", query = "SELECT f FROM FechaHabil f"),
	@NamedQuery(name = "FechaHabil.findByIdFechaHabil", query = "SELECT f FROM FechaHabil f WHERE f.idFechaHabil = :idFechaHabil"),
	@NamedQuery(name = "FechaHabil.findByFecha", query = "SELECT f FROM FechaHabil f WHERE f.fecha = :fecha")})
public class FechaHabil implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        @Column(name = "id_fecha_habil")
	private Integer idFechaHabil;
	@Basic(optional = false)
        @NotNull
        @Column(name = "fecha")
        @Temporal(TemporalType.DATE)
	private Date fecha;
	@JoinColumn(name = "id_tipo", referencedColumnName = "id_tipo")
        @ManyToOne(optional = false)
	private Tipo idTipo;

	public FechaHabil() {
	}

	public FechaHabil(Integer idFechaHabil) {
		this.idFechaHabil = idFechaHabil;
	}

	public FechaHabil(Integer idFechaHabil, Date fecha) {
		this.idFechaHabil = idFechaHabil;
		this.fecha = fecha;
	}

	public Integer getIdFechaHabil() {
		return idFechaHabil;
	}

	public void setIdFechaHabil(Integer idFechaHabil) {
		this.idFechaHabil = idFechaHabil;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Tipo getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Tipo idTipo) {
		this.idTipo = idTipo;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idFechaHabil != null ? idFechaHabil.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof FechaHabil)) {
			return false;
		}
		FechaHabil other = (FechaHabil) object;
		if ((this.idFechaHabil == null && other.idFechaHabil != null) || (this.idFechaHabil != null && !this.idFechaHabil.equals(other.idFechaHabil))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "modelo.entidades.FechaHabil[ idFechaHabil=" + idFechaHabil + " ]";
	}
	
}
