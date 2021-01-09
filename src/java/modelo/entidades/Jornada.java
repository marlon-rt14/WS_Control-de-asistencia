/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mjavi
 */
@Entity
@Table(name = "jornada")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Jornada.findAll", query = "SELECT j FROM Jornada j"),
	@NamedQuery(name = "Jornada.findByIdJornada", query = "SELECT j FROM Jornada j WHERE j.idJornada = :idJornada"),
	@NamedQuery(name = "Jornada.findByEntraPrimerPeriodo", query = "SELECT j FROM Jornada j WHERE j.entraPrimerPeriodo = :entraPrimerPeriodo"),
	@NamedQuery(name = "Jornada.findBySalePrimerPeriodo", query = "SELECT j FROM Jornada j WHERE j.salePrimerPeriodo = :salePrimerPeriodo"),
	@NamedQuery(name = "Jornada.findByEntraSegundoPeriodo", query = "SELECT j FROM Jornada j WHERE j.entraSegundoPeriodo = :entraSegundoPeriodo"),
	@NamedQuery(name = "Jornada.findBySaleSegundoPeriodo", query = "SELECT j FROM Jornada j WHERE j.saleSegundoPeriodo = :saleSegundoPeriodo")})
public class Jornada implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        @Column(name = "id_jornada")
	private Integer idJornada;
	@Basic(optional = false)
        @NotNull
        @Column(name = "entra_primer_periodo")
        @Temporal(TemporalType.TIME)
	private Date entraPrimerPeriodo;
	@Basic(optional = false)
        @NotNull
        @Column(name = "sale_primer_periodo")
        @Temporal(TemporalType.TIME)
	private Date salePrimerPeriodo;
	@Basic(optional = false)
        @NotNull
        @Column(name = "entra_segundo_periodo")
        @Temporal(TemporalType.TIME)
	private Date entraSegundoPeriodo;
	@Basic(optional = false)
        @NotNull
        @Column(name = "sale_segundo_periodo")
        @Temporal(TemporalType.TIME)
	private Date saleSegundoPeriodo;
	@OneToMany(mappedBy = "idJornada")
	private List<TipoEmpleado> tipoEmpleadoList;

	public Jornada() {
	}

	public Jornada(Integer idJornada) {
		this.idJornada = idJornada;
	}

	public Jornada(Integer idJornada, Date entraPrimerPeriodo, Date salePrimerPeriodo, Date entraSegundoPeriodo, Date saleSegundoPeriodo) {
		this.idJornada = idJornada;
		this.entraPrimerPeriodo = entraPrimerPeriodo;
		this.salePrimerPeriodo = salePrimerPeriodo;
		this.entraSegundoPeriodo = entraSegundoPeriodo;
		this.saleSegundoPeriodo = saleSegundoPeriodo;
	}

	public Integer getIdJornada() {
		return idJornada;
	}

	public void setIdJornada(Integer idJornada) {
		this.idJornada = idJornada;
	}

	public Date getEntraPrimerPeriodo() {
		return entraPrimerPeriodo;
	}

	public void setEntraPrimerPeriodo(Date entraPrimerPeriodo) {
		this.entraPrimerPeriodo = entraPrimerPeriodo;
	}

	public Date getSalePrimerPeriodo() {
		return salePrimerPeriodo;
	}

	public void setSalePrimerPeriodo(Date salePrimerPeriodo) {
		this.salePrimerPeriodo = salePrimerPeriodo;
	}

	public Date getEntraSegundoPeriodo() {
		return entraSegundoPeriodo;
	}

	public void setEntraSegundoPeriodo(Date entraSegundoPeriodo) {
		this.entraSegundoPeriodo = entraSegundoPeriodo;
	}

	public Date getSaleSegundoPeriodo() {
		return saleSegundoPeriodo;
	}

	public void setSaleSegundoPeriodo(Date saleSegundoPeriodo) {
		this.saleSegundoPeriodo = saleSegundoPeriodo;
	}

	@XmlTransient
	public List<TipoEmpleado> getTipoEmpleadoList() {
		return tipoEmpleadoList;
	}

	public void setTipoEmpleadoList(List<TipoEmpleado> tipoEmpleadoList) {
		this.tipoEmpleadoList = tipoEmpleadoList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idJornada != null ? idJornada.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Jornada)) {
			return false;
		}
		Jornada other = (Jornada) object;
		if ((this.idJornada == null && other.idJornada != null) || (this.idJornada != null && !this.idJornada.equals(other.idJornada))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "modelo.entidades.Jornada[ idJornada=" + idJornada + " ]";
	}
	
}
