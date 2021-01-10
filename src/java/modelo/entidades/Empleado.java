/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entidades;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mjavi
 */
@Entity
@Table(name = "empleados")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e"),
	@NamedQuery(name = "Empleado.findByIdEmpleado", query = "SELECT e FROM Empleado e WHERE e.idEmpleado = :idEmpleado"),
	@NamedQuery(name = "Empleado.findByCedula", query = "SELECT e FROM Empleado e WHERE e.cedula = :cedula"),
	@NamedQuery(name = "Empleado.findByNombres", query = "SELECT e FROM Empleado e WHERE e.nombres = :nombres"),
	@NamedQuery(name = "Empleado.findByApellidos", query = "SELECT e FROM Empleado e WHERE e.apellidos = :apellidos"),
	@NamedQuery(name = "Empleado.findByUsuario", query = "SELECT e FROM Empleado e WHERE e.usuario = :usuario"),
	@NamedQuery(name = "Empleado.findByFuncion", query = "SELECT e FROM Empleado e WHERE e.funcion = :funcion"),
	@NamedQuery(name = "Empleado.findByClave", query = "SELECT e FROM Empleado e WHERE e.clave = :clave")})
public class Empleado implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        @Column(name = "id_empleado")
	private Integer idEmpleado;
	@Basic(optional = false)
        @NotNull
        @Size(min = 1, max = 10)
        @Column(name = "cedula")
	private String cedula;
	@Basic(optional = false)
        @NotNull
        @Size(min = 1, max = 50)
        @Column(name = "nombres")
	private String nombres;
	@Basic(optional = false)
        @NotNull
        @Size(min = 1, max = 50)
        @Column(name = "apellidos")
	private String apellidos;
	@Basic(optional = false)
        @NotNull
        @Size(min = 1, max = 20)
        @Column(name = "usuario")
	private String usuario;
	@Size(max = 50)
        @Column(name = "funcion")
	private String funcion;
	@Basic(optional = false)
        @NotNull
        @Size(min = 1, max = 50)
        @Column(name = "clave")
	private String clave;
	@OneToMany(mappedBy = "idEmpleado")
	private List<Horario> horarioList;
	@OneToMany(mappedBy = "idEmpleado")
	private List<TipoEmpleado> tipoEmpleadoList;
	@OneToMany(mappedBy = "idEmpleado")
	private List<Asistencia> asistenciaList;

	public Empleado() {
	}

	public Empleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public Empleado(Integer idEmpleado, String cedula, String nombres, String apellidos, String usuario, String clave) {
		this.idEmpleado = idEmpleado;
		this.cedula = cedula;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.usuario = usuario;
		this.clave = clave;
	}

	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getFuncion() {
		return funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@XmlTransient
	public List<Horario> getHorarioList() {
		return horarioList;
	}

	public void setHorarioList(List<Horario> horarioList) {
		this.horarioList = horarioList;
	}

	@XmlTransient
	public List<TipoEmpleado> getTipoEmpleadoList() {
		return tipoEmpleadoList;
	}

	public void setTipoEmpleadoList(List<TipoEmpleado> tipoEmpleadoList) {
		this.tipoEmpleadoList = tipoEmpleadoList;
	}

	@XmlTransient
	public List<Asistencia> getAsistenciaList() {
		return asistenciaList;
	}

	public void setAsistenciaList(List<Asistencia> asistenciaList) {
		this.asistenciaList = asistenciaList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idEmpleado != null ? idEmpleado.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Empleado)) {
			return false;
		}
		Empleado other = (Empleado) object;
		if ((this.idEmpleado == null && other.idEmpleado != null) || (this.idEmpleado != null && !this.idEmpleado.equals(other.idEmpleado))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "modelo.entidades.Empleado[ idEmpleado=" + idEmpleado + " ]";
	}
	
}
