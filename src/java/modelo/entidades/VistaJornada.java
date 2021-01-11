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
import javax.persistence.Id;
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
 * @author mjavi
 */
@Entity
@Table(name = "vista_jornada")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "VistaJornada.findAll", query = "SELECT v FROM VistaJornada v"),
	@NamedQuery(name = "VistaJornada.findByIdEmpleado", query = "SELECT v FROM VistaJornada v WHERE v.idEmpleado = :idEmpleado"),
	@NamedQuery(name = "VistaJornada.findByDescripcionTipo", query = "SELECT v FROM VistaJornada v WHERE v.descripcionTipo = :descripcionTipo"),
	@NamedQuery(name = "VistaJornada.findByCedula", query = "SELECT v FROM VistaJornada v WHERE v.cedula = :cedula"),
	@NamedQuery(name = "VistaJornada.findByNombreEmpleado", query = "SELECT v FROM VistaJornada v WHERE v.nombreEmpleado = :nombreEmpleado"),
	@NamedQuery(name = "VistaJornada.findByFecha", query = "SELECT v FROM VistaJornada v WHERE v.fecha = :fecha"),
	@NamedQuery(name = "VistaJornada.findByEntraPrimerPeriodo", query = "SELECT v FROM VistaJornada v WHERE v.entraPrimerPeriodo = :entraPrimerPeriodo"),
	@NamedQuery(name = "VistaJornada.findBySalePrimerPeriodo", query = "SELECT v FROM VistaJornada v WHERE v.salePrimerPeriodo = :salePrimerPeriodo"),
	@NamedQuery(name = "VistaJornada.findByEntraSegundoPeriodo", query = "SELECT v FROM VistaJornada v WHERE v.entraSegundoPeriodo = :entraSegundoPeriodo"),
	@NamedQuery(name = "VistaJornada.findBySaleSegundoPeriodo", query = "SELECT v FROM VistaJornada v WHERE v.saleSegundoPeriodo = :saleSegundoPeriodo")})
public class VistaJornada implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
        @NotNull
        @Column(name = "id_empleado")
	private int idEmpleado;
	@Basic(optional = false)
        @NotNull
        @Size(min = 1, max = 50)
        @Column(name = "descripcion_tipo")
	private String descripcionTipo;
	@Basic(optional = false)
        @NotNull
        @Size(min = 1, max = 10)
        @Column(name = "cedula")
	private String cedula;
	@Size(max = 101)
        @Column(name = "nombre_empleado")
	private String nombreEmpleado;
	@Basic(optional = false)
        @NotNull
        @Column(name = "fecha")
        @Temporal(TemporalType.DATE)
	private Date fecha;
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

	public VistaJornada() {
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getDescripcionTipo() {
		return descripcionTipo;
	}

	public void setDescripcionTipo(String descripcionTipo) {
		this.descripcionTipo = descripcionTipo;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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
	
}
