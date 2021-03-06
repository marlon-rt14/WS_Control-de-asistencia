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
@Table(name = "vista_docente")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "VistaDocente.findAll", query = "SELECT v FROM VistaDocente v"),
	@NamedQuery(name = "VistaDocente.findByIdViewDocente", query = "SELECT v FROM VistaDocente v WHERE v.idViewDocente = :idViewDocente"),
	@NamedQuery(name = "VistaDocente.findByIdEmpleado", query = "SELECT v FROM VistaDocente v WHERE v.idEmpleado = :idEmpleado"),
	@NamedQuery(name = "VistaDocente.findByDescripcionTipo", query = "SELECT v FROM VistaDocente v WHERE v.descripcionTipo = :descripcionTipo"),
	@NamedQuery(name = "VistaDocente.findByCedula", query = "SELECT v FROM VistaDocente v WHERE v.cedula = :cedula"),
	@NamedQuery(name = "VistaDocente.findByNombreEmpleado", query = "SELECT v FROM VistaDocente v WHERE v.nombreEmpleado = :nombreEmpleado"),
	@NamedQuery(name = "VistaDocente.findByDescripcionAula", query = "SELECT v FROM VistaDocente v WHERE v.descripcionAula = :descripcionAula"),
	@NamedQuery(name = "VistaDocente.findByDescripcionMateria", query = "SELECT v FROM VistaDocente v WHERE v.descripcionMateria = :descripcionMateria"),
	@NamedQuery(name = "VistaDocente.findByHoraInicio", query = "SELECT v FROM VistaDocente v WHERE v.horaInicio = :horaInicio"),
	@NamedQuery(name = "VistaDocente.findByHoraFin", query = "SELECT v FROM VistaDocente v WHERE v.horaFin = :horaFin"),
	@NamedQuery(name = "VistaDocente.findByFecha", query = "SELECT v FROM VistaDocente v WHERE v.fecha = :fecha")})
public class VistaDocente implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
        @NotNull
        @Column(name = "id_view_docente")
	private long idViewDocente;
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
	@Size(max = 50)
        @Column(name = "descripcion_aula")
	private String descripcionAula;
	@Size(max = 50)
        @Column(name = "descripcion_materia")
	private String descripcionMateria;
	@Basic(optional = false)
        @NotNull
        @Column(name = "hora_inicio")
        @Temporal(TemporalType.TIME)
	private Date horaInicio;
	@Basic(optional = false)
        @NotNull
        @Column(name = "hora_fin")
        @Temporal(TemporalType.TIME)
	private Date horaFin;
	@Basic(optional = false)
        @NotNull
        @Column(name = "fecha")
        @Temporal(TemporalType.DATE)
	private Date fecha;

	public VistaDocente() {
	}

	public long getIdViewDocente() {
		return idViewDocente;
	}

	public void setIdViewDocente(long idViewDocente) {
		this.idViewDocente = idViewDocente;
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

	public String getDescripcionAula() {
		return descripcionAula;
	}

	public void setDescripcionAula(String descripcionAula) {
		this.descripcionAula = descripcionAula;
	}

	public String getDescripcionMateria() {
		return descripcionMateria;
	}

	public void setDescripcionMateria(String descripcionMateria) {
		this.descripcionMateria = descripcionMateria;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
}
