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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 *
 * @author mjavi
 */
@Entity
@Table(name = "vista_nombre_completo")
@Immutable
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "VistaNombreCompleto.findAll", query = "SELECT v FROM VistaNombreCompleto v"),
	@NamedQuery(name = "VistaNombreCompleto.findByIdEmpleado", query = "SELECT v FROM VistaNombreCompleto v WHERE v.idEmpleado = :idEmpleado"),
	@NamedQuery(name = "VistaNombreCompleto.findByNombreEmpleado", query = "SELECT v FROM VistaNombreCompleto v WHERE v.nombreEmpleado = :nombreEmpleado")})
public class VistaNombreCompleto implements Serializable {

	@Id
	@GeneratedValue
	
        @NotNull
        @Column(name = "id_empleado")
	private int idEmpleado;
	@Size(max = 101)
        @Column(name = "nombre_empleado")
	private String nombreEmpleado;

	public VistaNombreCompleto() {
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	
	
}
