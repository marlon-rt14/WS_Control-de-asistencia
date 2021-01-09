/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.dao.exceptions.NonexistentEntityException;
import modelo.entidades.Aula;
import modelo.entidades.Empleado;
import modelo.entidades.Horario;
import modelo.entidades.Materia;

/**
 *
 * @author mjavi
 */
public class HorarioJpaController implements Serializable {

	public HorarioJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}
	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(Horario horario) {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Aula idAula = horario.getIdAula();
			if (idAula != null) {
				idAula = em.getReference(idAula.getClass(), idAula.getIdAula());
				horario.setIdAula(idAula);
			}
			Empleado idEmpleado = horario.getIdEmpleado();
			if (idEmpleado != null) {
				idEmpleado = em.getReference(idEmpleado.getClass(), idEmpleado.getIdEmpleado());
				horario.setIdEmpleado(idEmpleado);
			}
			Materia idMateria = horario.getIdMateria();
			if (idMateria != null) {
				idMateria = em.getReference(idMateria.getClass(), idMateria.getIdMateria());
				horario.setIdMateria(idMateria);
			}
			em.persist(horario);
			if (idAula != null) {
				idAula.getHorarioList().add(horario);
				idAula = em.merge(idAula);
			}
			if (idEmpleado != null) {
				idEmpleado.getHorarioList().add(horario);
				idEmpleado = em.merge(idEmpleado);
			}
			if (idMateria != null) {
				idMateria.getHorarioList().add(horario);
				idMateria = em.merge(idMateria);
			}
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(Horario horario) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Horario persistentHorario = em.find(Horario.class, horario.getIdHorario());
			Aula idAulaOld = persistentHorario.getIdAula();
			Aula idAulaNew = horario.getIdAula();
			Empleado idEmpleadoOld = persistentHorario.getIdEmpleado();
			Empleado idEmpleadoNew = horario.getIdEmpleado();
			Materia idMateriaOld = persistentHorario.getIdMateria();
			Materia idMateriaNew = horario.getIdMateria();
			if (idAulaNew != null) {
				idAulaNew = em.getReference(idAulaNew.getClass(), idAulaNew.getIdAula());
				horario.setIdAula(idAulaNew);
			}
			if (idEmpleadoNew != null) {
				idEmpleadoNew = em.getReference(idEmpleadoNew.getClass(), idEmpleadoNew.getIdEmpleado());
				horario.setIdEmpleado(idEmpleadoNew);
			}
			if (idMateriaNew != null) {
				idMateriaNew = em.getReference(idMateriaNew.getClass(), idMateriaNew.getIdMateria());
				horario.setIdMateria(idMateriaNew);
			}
			horario = em.merge(horario);
			if (idAulaOld != null && !idAulaOld.equals(idAulaNew)) {
				idAulaOld.getHorarioList().remove(horario);
				idAulaOld = em.merge(idAulaOld);
			}
			if (idAulaNew != null && !idAulaNew.equals(idAulaOld)) {
				idAulaNew.getHorarioList().add(horario);
				idAulaNew = em.merge(idAulaNew);
			}
			if (idEmpleadoOld != null && !idEmpleadoOld.equals(idEmpleadoNew)) {
				idEmpleadoOld.getHorarioList().remove(horario);
				idEmpleadoOld = em.merge(idEmpleadoOld);
			}
			if (idEmpleadoNew != null && !idEmpleadoNew.equals(idEmpleadoOld)) {
				idEmpleadoNew.getHorarioList().add(horario);
				idEmpleadoNew = em.merge(idEmpleadoNew);
			}
			if (idMateriaOld != null && !idMateriaOld.equals(idMateriaNew)) {
				idMateriaOld.getHorarioList().remove(horario);
				idMateriaOld = em.merge(idMateriaOld);
			}
			if (idMateriaNew != null && !idMateriaNew.equals(idMateriaOld)) {
				idMateriaNew.getHorarioList().add(horario);
				idMateriaNew = em.merge(idMateriaNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Integer id = horario.getIdHorario();
				if (findHorario(id) == null) {
					throw new NonexistentEntityException("The horario with id " + id + " no longer exists.");
				}
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void destroy(Integer id) throws NonexistentEntityException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Horario horario;
			try {
				horario = em.getReference(Horario.class, id);
				horario.getIdHorario();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The horario with id " + id + " no longer exists.", enfe);
			}
			Aula idAula = horario.getIdAula();
			if (idAula != null) {
				idAula.getHorarioList().remove(horario);
				idAula = em.merge(idAula);
			}
			Empleado idEmpleado = horario.getIdEmpleado();
			if (idEmpleado != null) {
				idEmpleado.getHorarioList().remove(horario);
				idEmpleado = em.merge(idEmpleado);
			}
			Materia idMateria = horario.getIdMateria();
			if (idMateria != null) {
				idMateria.getHorarioList().remove(horario);
				idMateria = em.merge(idMateria);
			}
			em.remove(horario);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<Horario> findHorarioEntities() {
		return findHorarioEntities(true, -1, -1);
	}

	public List<Horario> findHorarioEntities(int maxResults, int firstResult) {
		return findHorarioEntities(false, maxResults, firstResult);
	}

	private List<Horario> findHorarioEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Horario.class));
			Query q = em.createQuery(cq);
			if (!all) {
				q.setMaxResults(maxResults);
				q.setFirstResult(firstResult);
			}
			return q.getResultList();
		} finally {
			em.close();
		}
	}

	public Horario findHorario(Integer id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Horario.class, id);
		} finally {
			em.close();
		}
	}

	public int getHorarioCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Horario> rt = cq.from(Horario.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}
	
}
