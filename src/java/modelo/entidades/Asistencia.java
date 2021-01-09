/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entidades;

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
 * @author mjavi
 */
@Entity
@Table(name = "asistencia")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Asistencia.findAll", query = "SELECT a FROM Asistencia a"),
	@NamedQuery(name = "Asistencia.findByIdAsistencia", query = "SELECT a FROM Asistencia a WHERE a.idAsistencia = :idAsistencia"),
	@NamedQuery(name = "Asistencia.findByMensajeAsistencia", query = "SELECT a FROM Asistencia a WHERE a.mensajeAsistencia = :mensajeAsistencia"),
	@NamedQuery(name = "Asistencia.findByComentarios", query = "SELECT a FROM Asistencia a WHERE a.comentarios = :comentarios"),
	@NamedQuery(name = "Asistencia.findByObservaciones", query = "SELECT a FROM Asistencia a WHERE a.observaciones = :observaciones")})
public class Asistencia implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        @Column(name = "id_asistencia")
	private Integer idAsistencia;
	@Basic(optional = false)
        @NotNull
        @Size(min = 1, max = 150)
        @Column(name = "mensaje_asistencia")
	private String mensajeAsistencia;
	@Size(max = 150)
        @Column(name = "comentarios")
	private String comentarios;
	@Size(max = 12)
        @Column(name = "observaciones")
	private String observaciones;
	@JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
        @ManyToOne
	private Empleado idEmpleado;
	@JoinColumn(name = "id_estado", referencedColumnName = "id_estado")
        @ManyToOne
	private Estado idEstado;
	@JoinColumn(name = "id_fecha_habil", referencedColumnName = "id_fecha_habil")
        @ManyToOne
	private FechaHabil idFechaHabil;
	@JoinColumn(name = "id_tipo_empleado", referencedColumnName = "id_tipo_empleado")
        @ManyToOne
	private TipoEmpleado idTipoEmpleado;

	public Asistencia() {
	}

	public Asistencia(Integer idAsistencia) {
		this.idAsistencia = idAsistencia;
	}

	public Asistencia(Integer idAsistencia, String mensajeAsistencia) {
		this.idAsistencia = idAsistencia;
		this.mensajeAsistencia = mensajeAsistencia;
	}

	public Integer getIdAsistencia() {
		return idAsistencia;
	}

	public void setIdAsistencia(Integer idAsistencia) {
		this.idAsistencia = idAsistencia;
	}

	public String getMensajeAsistencia() {
		return mensajeAsistencia;
	}

	public void setMensajeAsistencia(String mensajeAsistencia) {
		this.mensajeAsistencia = mensajeAsistencia;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Empleado getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Empleado idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public Estado getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Estado idEstado) {
		this.idEstado = idEstado;
	}

	public FechaHabil getIdFechaHabil() {
		return idFechaHabil;
	}

	public void setIdFechaHabil(FechaHabil idFechaHabil) {
		this.idFechaHabil = idFechaHabil;
	}

	public TipoEmpleado getIdTipoEmpleado() {
		return idTipoEmpleado;
	}

	public void setIdTipoEmpleado(TipoEmpleado idTipoEmpleado) {
		this.idTipoEmpleado = idTipoEmpleado;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idAsistencia != null ? idAsistencia.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Asistencia)) {
			return false;
		}
		Asistencia other = (Asistencia) object;
		if ((this.idAsistencia == null && other.idAsistencia != null) || (this.idAsistencia != null && !this.idAsistencia.equals(other.idAsistencia))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "modelo.entidades.Asistencia[ idAsistencia=" + idAsistencia + " ]";
	}
	
}
