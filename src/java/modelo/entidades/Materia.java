/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mjavi
 */
@Entity
@Table(name = "materias")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Materia.findAll", query = "SELECT m FROM Materia m"),
	@NamedQuery(name = "Materia.findByIdMateria", query = "SELECT m FROM Materia m WHERE m.idMateria = :idMateria"),
	@NamedQuery(name = "Materia.findByDescripcionMateria", query = "SELECT m FROM Materia m WHERE m.descripcionMateria = :descripcionMateria")})
public class Materia implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        @Column(name = "id_materia")
	private Integer idMateria;
	@Size(max = 50)
        @Column(name = "descripcion_materia")
	private String descripcionMateria;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idMateria")
	private List<Horario> horarioList;

	public Materia() {
	}

	public Materia(Integer idMateria) {
		this.idMateria = idMateria;
	}

	public Integer getIdMateria() {
		return idMateria;
	}

	public void setIdMateria(Integer idMateria) {
		this.idMateria = idMateria;
	}

	public String getDescripcionMateria() {
		return descripcionMateria;
	}

	public void setDescripcionMateria(String descripcionMateria) {
		this.descripcionMateria = descripcionMateria;
	}

	@XmlTransient
	public List<Horario> getHorarioList() {
		return horarioList;
	}

	public void setHorarioList(List<Horario> horarioList) {
		this.horarioList = horarioList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idMateria != null ? idMateria.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Materia)) {
			return false;
		}
		Materia other = (Materia) object;
		if ((this.idMateria == null && other.idMateria != null) || (this.idMateria != null && !this.idMateria.equals(other.idMateria))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "modelo.entidades.Materia[ idMateria=" + idMateria + " ]";
	}
	
}
