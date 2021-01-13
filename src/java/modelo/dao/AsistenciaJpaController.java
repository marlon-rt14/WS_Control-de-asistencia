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
import modelo.entidades.Asistencia;
import modelo.entidades.Empleado;
import modelo.entidades.Estado;
import modelo.entidades.TipoEmpleado;

/**
 *
 * @author mjavi
 */
public class AsistenciaJpaController implements Serializable {

	public AsistenciaJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}
	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(Asistencia asistencia) {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Empleado idEmpleado = asistencia.getIdEmpleado();
			if (idEmpleado != null) {
				idEmpleado = em.getReference(idEmpleado.getClass(), idEmpleado.getIdEmpleado());
				asistencia.setIdEmpleado(idEmpleado);
			}
			Estado idEstado = asistencia.getIdEstado();
			if (idEstado != null) {
				idEstado = em.getReference(idEstado.getClass(), idEstado.getIdEstado());
				asistencia.setIdEstado(idEstado);
			}
			TipoEmpleado idTipoEmpleado = asistencia.getIdTipoEmpleado();
			if (idTipoEmpleado != null) {
				idTipoEmpleado = em.getReference(idTipoEmpleado.getClass(), idTipoEmpleado.getIdTipoEmpleado());
				asistencia.setIdTipoEmpleado(idTipoEmpleado);
			}
			em.persist(asistencia);
			if (idEmpleado != null) {
				idEmpleado.getAsistenciaList().add(asistencia);
				idEmpleado = em.merge(idEmpleado);
			}
			if (idEstado != null) {
				idEstado.getAsistenciaList().add(asistencia);
				idEstado = em.merge(idEstado);
			}
			if (idTipoEmpleado != null) {
				idTipoEmpleado.getAsistenciaList().add(asistencia);
				idTipoEmpleado = em.merge(idTipoEmpleado);
			}
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(Asistencia asistencia) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Asistencia persistentAsistencia = em.find(Asistencia.class, asistencia.getIdAsistencia());
			Empleado idEmpleadoOld = persistentAsistencia.getIdEmpleado();
			Empleado idEmpleadoNew = asistencia.getIdEmpleado();
			Estado idEstadoOld = persistentAsistencia.getIdEstado();
			Estado idEstadoNew = asistencia.getIdEstado();
			TipoEmpleado idTipoEmpleadoOld = persistentAsistencia.getIdTipoEmpleado();
			TipoEmpleado idTipoEmpleadoNew = asistencia.getIdTipoEmpleado();
			if (idEmpleadoNew != null) {
				idEmpleadoNew = em.getReference(idEmpleadoNew.getClass(), idEmpleadoNew.getIdEmpleado());
				asistencia.setIdEmpleado(idEmpleadoNew);
			}
			if (idEstadoNew != null) {
				idEstadoNew = em.getReference(idEstadoNew.getClass(), idEstadoNew.getIdEstado());
				asistencia.setIdEstado(idEstadoNew);
			}
			if (idTipoEmpleadoNew != null) {
				idTipoEmpleadoNew = em.getReference(idTipoEmpleadoNew.getClass(), idTipoEmpleadoNew.getIdTipoEmpleado());
				asistencia.setIdTipoEmpleado(idTipoEmpleadoNew);
			}
			asistencia = em.merge(asistencia);
			if (idEmpleadoOld != null && !idEmpleadoOld.equals(idEmpleadoNew)) {
				idEmpleadoOld.getAsistenciaList().remove(asistencia);
				idEmpleadoOld = em.merge(idEmpleadoOld);
			}
			if (idEmpleadoNew != null && !idEmpleadoNew.equals(idEmpleadoOld)) {
				idEmpleadoNew.getAsistenciaList().add(asistencia);
				idEmpleadoNew = em.merge(idEmpleadoNew);
			}
			if (idEstadoOld != null && !idEstadoOld.equals(idEstadoNew)) {
				idEstadoOld.getAsistenciaList().remove(asistencia);
				idEstadoOld = em.merge(idEstadoOld);
			}
			if (idEstadoNew != null && !idEstadoNew.equals(idEstadoOld)) {
				idEstadoNew.getAsistenciaList().add(asistencia);
				idEstadoNew = em.merge(idEstadoNew);
			}
			if (idTipoEmpleadoOld != null && !idTipoEmpleadoOld.equals(idTipoEmpleadoNew)) {
				idTipoEmpleadoOld.getAsistenciaList().remove(asistencia);
				idTipoEmpleadoOld = em.merge(idTipoEmpleadoOld);
			}
			if (idTipoEmpleadoNew != null && !idTipoEmpleadoNew.equals(idTipoEmpleadoOld)) {
				idTipoEmpleadoNew.getAsistenciaList().add(asistencia);
				idTipoEmpleadoNew = em.merge(idTipoEmpleadoNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Integer id = asistencia.getIdAsistencia();
				if (findAsistencia(id) == null) {
					throw new NonexistentEntityException("The asistencia with id " + id + " no longer exists.");
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
			Asistencia asistencia;
			try {
				asistencia = em.getReference(Asistencia.class, id);
				asistencia.getIdAsistencia();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The asistencia with id " + id + " no longer exists.", enfe);
			}
			Empleado idEmpleado = asistencia.getIdEmpleado();
			if (idEmpleado != null) {
				idEmpleado.getAsistenciaList().remove(asistencia);
				idEmpleado = em.merge(idEmpleado);
			}
			Estado idEstado = asistencia.getIdEstado();
			if (idEstado != null) {
				idEstado.getAsistenciaList().remove(asistencia);
				idEstado = em.merge(idEstado);
			}
			TipoEmpleado idTipoEmpleado = asistencia.getIdTipoEmpleado();
			if (idTipoEmpleado != null) {
				idTipoEmpleado.getAsistenciaList().remove(asistencia);
				idTipoEmpleado = em.merge(idTipoEmpleado);
			}
			em.remove(asistencia);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<Asistencia> findAsistenciaEntities() {
		return findAsistenciaEntities(true, -1, -1);
	}

	public List<Asistencia> findAsistenciaEntities(int maxResults, int firstResult) {
		return findAsistenciaEntities(false, maxResults, firstResult);
	}

	private List<Asistencia> findAsistenciaEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Asistencia.class));
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

	public Asistencia findAsistencia(Integer id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Asistencia.class, id);
		} finally {
			em.close();
		}
	}

	public int getAsistenciaCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Asistencia> rt = cq.from(Asistencia.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}
	
}
