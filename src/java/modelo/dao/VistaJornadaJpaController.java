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
import modelo.dao.exceptions.PreexistingEntityException;
import modelo.entidades.VistaJornada;

/**
 *
 * @author mjavi
 */
public class VistaJornadaJpaController implements Serializable {

	public VistaJornadaJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}
	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(VistaJornada vistaJornada) throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(vistaJornada);
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findVistaJornada(vistaJornada.getIdViewJornada()) != null) {
				throw new PreexistingEntityException("VistaJornada " + vistaJornada + " already exists.", ex);
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(VistaJornada vistaJornada) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			vistaJornada = em.merge(vistaJornada);
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				long id = vistaJornada.getIdViewJornada();
				if (findVistaJornada(id) == null) {
					throw new NonexistentEntityException("The vistaJornada with id " + id + " no longer exists.");
				}
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void destroy(long id) throws NonexistentEntityException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			VistaJornada vistaJornada;
			try {
				vistaJornada = em.getReference(VistaJornada.class, id);
				vistaJornada.getIdViewJornada();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The vistaJornada with id " + id + " no longer exists.", enfe);
			}
			em.remove(vistaJornada);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<VistaJornada> findVistaJornadaEntities() {
		return findVistaJornadaEntities(true, -1, -1);
	}

	public List<VistaJornada> findVistaJornadaEntities(int maxResults, int firstResult) {
		return findVistaJornadaEntities(false, maxResults, firstResult);
	}

	private List<VistaJornada> findVistaJornadaEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(VistaJornada.class));
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

	public VistaJornada findVistaJornada(long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(VistaJornada.class, id);
		} finally {
			em.close();
		}
	}

	public int getVistaJornadaCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<VistaJornada> rt = cq.from(VistaJornada.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}
	
}
