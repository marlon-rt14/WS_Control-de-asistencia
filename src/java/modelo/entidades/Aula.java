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
@Table(name = "aulas")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Aula.findAll", query = "SELECT a FROM Aula a"),
	@NamedQuery(name = "Aula.findByIdAula", query = "SELECT a FROM Aula a WHERE a.idAula = :idAula"),
	@NamedQuery(name = "Aula.findByDescripcionAula", query = "SELECT a FROM Aula a WHERE a.descripcionAula = :descripcionAula")})
public class Aula implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Basic(optional = false)
        @Column(name = "id_aula")
	private Integer idAula;
	@Size(max = 50)
        @Column(name = "descripcion_aula")
	private String descripcionAula;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idAula")
	private List<Horario> horarioList;

	public Aula() {
	}

	public Aula(Integer idAula) {
		this.idAula = idAula;
	}

	public Integer getIdAula() {
		return idAula;
	}

	public void setIdAula(Integer idAula) {
		this.idAula = idAula;
	}

	public String getDescripcionAula() {
		return descripcionAula;
	}

	public void setDescripcionAula(String descripcionAula) {
		this.descripcionAula = descripcionAula;
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
		hash += (idAula != null ? idAula.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Aula)) {
			return false;
		}
		Aula other = (Aula) object;
		if ((this.idAula == null && other.idAula != null) || (this.idAula != null && !this.idAula.equals(other.idAula))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "modelo.entidades.Aula[ idAula=" + idAula + " ]";
	}
	
}
