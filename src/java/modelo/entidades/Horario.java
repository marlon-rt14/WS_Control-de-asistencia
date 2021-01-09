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
@Table(name = "horario")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Horario.findAll", query = "SELECT h FROM Horario h"),
	@NamedQuery(name = "Horario.findByIdHorario", query = "SELECT h FROM Horario h WHERE h.idHorario = :idHorario"),
	@NamedQuery(name = "Horario.findByHoraInicio", query = "SELECT h FROM Horario h WHERE h.horaInicio = :horaInicio"),
	@NamedQuery(name = "Horario.findByHoraFin", query = "SELECT h FROM Horario h WHERE h.horaFin = :horaFin")})
public class Horario implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        @Column(name = "id_horario")
	private Integer idHorario;
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
	@JoinColumn(name = "id_aula", referencedColumnName = "id_aula")
        @ManyToOne(optional = false)
	private Aula idAula;
	@JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
        @ManyToOne
	private Empleado idEmpleado;
	@JoinColumn(name = "id_materia", referencedColumnName = "id_materia")
        @ManyToOne(optional = false)
	private Materia idMateria;

	public Horario() {
	}

	public Horario(Integer idHorario) {
		this.idHorario = idHorario;
	}

	public Horario(Integer idHorario, Date horaInicio, Date horaFin) {
		this.idHorario = idHorario;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}

	public Integer getIdHorario() {
		return idHorario;
	}

	public void setIdHorario(Integer idHorario) {
		this.idHorario = idHorario;
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

	public Aula getIdAula() {
		return idAula;
	}

	public void setIdAula(Aula idAula) {
		this.idAula = idAula;
	}

	public Empleado getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Empleado idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public Materia getIdMateria() {
		return idMateria;
	}

	public void setIdMateria(Materia idMateria) {
		this.idMateria = idMateria;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idHorario != null ? idHorario.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Horario)) {
			return false;
		}
		Horario other = (Horario) object;
		if ((this.idHorario == null && other.idHorario != null) || (this.idHorario != null && !this.idHorario.equals(other.idHorario))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "modelo.entidades.Horario[ idHorario=" + idHorario + " ]";
	}
	
}
