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
import modelo.entidades.FechaHabil;
import modelo.entidades.Tipo;

/**
 *
 * @author mjavi
 */
public class FechaHabilJpaController implements Serializable {

	public FechaHabilJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}
	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(FechaHabil fechaHabil) {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Tipo idTipo = fechaHabil.getIdTipo();
			if (idTipo != null) {
				idTipo = em.getReference(idTipo.getClass(), idTipo.getIdTipo());
				fechaHabil.setIdTipo(idTipo);
			}
			em.persist(fechaHabil);
			if (idTipo != null) {
				idTipo.getFechaHabilList().add(fechaHabil);
				idTipo = em.merge(idTipo);
			}
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(FechaHabil fechaHabil) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			FechaHabil persistentFechaHabil = em.find(FechaHabil.class, fechaHabil.getIdFechaHabil());
			Tipo idTipoOld = persistentFechaHabil.getIdTipo();
			Tipo idTipoNew = fechaHabil.getIdTipo();
			if (idTipoNew != null) {
				idTipoNew = em.getReference(idTipoNew.getClass(), idTipoNew.getIdTipo());
				fechaHabil.setIdTipo(idTipoNew);
			}
			fechaHabil = em.merge(fechaHabil);
			if (idTipoOld != null && !idTipoOld.equals(idTipoNew)) {
				idTipoOld.getFechaHabilList().remove(fechaHabil);
				idTipoOld = em.merge(idTipoOld);
			}
			if (idTipoNew != null && !idTipoNew.equals(idTipoOld)) {
				idTipoNew.getFechaHabilList().add(fechaHabil);
				idTipoNew = em.merge(idTipoNew);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Integer id = fechaHabil.getIdFechaHabil();
				if (findFechaHabil(id) == null) {
					throw new NonexistentEntityException("The fechaHabil with id " + id + " no longer exists.");
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
			FechaHabil fechaHabil;
			try {
				fechaHabil = em.getReference(FechaHabil.class, id);
				fechaHabil.getIdFechaHabil();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The fechaHabil with id " + id + " no longer exists.", enfe);
			}
			Tipo idTipo = fechaHabil.getIdTipo();
			if (idTipo != null) {
				idTipo.getFechaHabilList().remove(fechaHabil);
				idTipo = em.merge(idTipo);
			}
			em.remove(fechaHabil);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<FechaHabil> findFechaHabilEntities() {
		return findFechaHabilEntities(true, -1, -1);
	}

	public List<FechaHabil> findFechaHabilEntities(int maxResults, int firstResult) {
		return findFechaHabilEntities(false, maxResults, firstResult);
	}

	private List<FechaHabil> findFechaHabilEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(FechaHabil.class));
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

	public FechaHabil findFechaHabil(Integer id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(FechaHabil.class, id);
		} finally {
			em.close();
		}
	}

	public int getFechaHabilCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<FechaHabil> rt = cq.from(FechaHabil.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}
	
}
