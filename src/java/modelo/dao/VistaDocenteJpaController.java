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
import modelo.entidades.VistaDocente;

/**
 *
 * @author mjavi
 */
public class VistaDocenteJpaController implements Serializable {

	public VistaDocenteJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}
	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(VistaDocente vistaDocente) throws PreexistingEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(vistaDocente);
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (findVistaDocente(vistaDocente.getIdViewDocente()) != null) {
				throw new PreexistingEntityException("VistaDocente " + vistaDocente + " already exists.", ex);
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(VistaDocente vistaDocente) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			vistaDocente = em.merge(vistaDocente);
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				long id = vistaDocente.getIdViewDocente();
				if (findVistaDocente(id) == null) {
					throw new NonexistentEntityException("The vistaDocente with id " + id + " no longer exists.");
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
			VistaDocente vistaDocente;
			try {
				vistaDocente = em.getReference(VistaDocente.class, id);
				vistaDocente.getIdViewDocente();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The vistaDocente with id " + id + " no longer exists.", enfe);
			}
			em.remove(vistaDocente);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<VistaDocente> findVistaDocenteEntities() {
		return findVistaDocenteEntities(true, -1, -1);
	}

	public List<VistaDocente> findVistaDocenteEntities(int maxResults, int firstResult) {
		return findVistaDocenteEntities(false, maxResults, firstResult);
	}

	private List<VistaDocente> findVistaDocenteEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(VistaDocente.class));
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

	public VistaDocente findVistaDocente(long id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(VistaDocente.class, id);
		} finally {
			em.close();
		}
	}

	public int getVistaDocenteCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<VistaDocente> rt = cq.from(VistaDocente.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}
	
}
