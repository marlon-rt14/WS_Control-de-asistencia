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
import javax.persistence.Id;
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
@Table(name = "vista_nombre_completo")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "VistaNombreCompleto.findAll", query = "SELECT v FROM VistaNombreCompleto v"),
	@NamedQuery(name = "VistaNombreCompleto.findByIdViewNombre", query = "SELECT v FROM VistaNombreCompleto v WHERE v.idViewNombre = :idViewNombre"),
	@NamedQuery(name = "VistaNombreCompleto.findByIdEmpleado", query = "SELECT v FROM VistaNombreCompleto v WHERE v.idEmpleado = :idEmpleado"),
	@NamedQuery(name = "VistaNombreCompleto.findByNombreEmpleado", query = "SELECT v FROM VistaNombreCompleto v WHERE v.nombreEmpleado = :nombreEmpleado")})
public class VistaNombreCompleto implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
        @NotNull
        @Column(name = "id_view_nombre")
	private long idViewNombre;
	@Basic(optional = false)
        @NotNull
        @Column(name = "id_empleado")
	private int idEmpleado;
	@Size(max = 101)
        @Column(name = "nombre_empleado")
	private String nombreEmpleado;

	public VistaNombreCompleto() {
	}

	public long getIdViewNombre() {
		return idViewNombre;
	}

	public void setIdViewNombre(long idViewNombre) {
		this.idViewNombre = idViewNombre;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}
	
}
