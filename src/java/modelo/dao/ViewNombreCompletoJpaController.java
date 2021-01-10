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
import modelo.entidades.ViewNombreCompleto;

/**
 *
 * @author mjavi
 */
public class ViewNombreCompletoJpaController implements Serializable {

	public ViewNombreCompletoJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}
	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(ViewNombreCompleto viewNombreCompleto) {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(viewNombreCompleto);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(ViewNombreCompleto viewNombreCompleto) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			viewNombreCompleto = em.merge(viewNombreCompleto);
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				int id = viewNombreCompleto.getIdEmpleado();
				if (findViewNombreCompleto(id) == null) {
					throw new NonexistentEntityException("The viewNombreCompleto with id " + id + " no longer exists.");
				}
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void destroy(int id) throws NonexistentEntityException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			ViewNombreCompleto viewNombreCompleto;
			try {
				viewNombreCompleto = em.getReference(ViewNombreCompleto.class, id);
				viewNombreCompleto.getIdEmpleado();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The viewNombreCompleto with id " + id + " no longer exists.", enfe);
			}
			em.remove(viewNombreCompleto);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<ViewNombreCompleto> findViewNombreCompletoEntities() {
		return findViewNombreCompletoEntities(true, -1, -1);
	}

	public List<ViewNombreCompleto> findViewNombreCompletoEntities(int maxResults, int firstResult) {
		return findViewNombreCompletoEntities(false, maxResults, firstResult);
	}

	private List<ViewNombreCompleto> findViewNombreCompletoEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(ViewNombreCompleto.class));
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

	public ViewNombreCompleto findViewNombreCompleto(int id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(ViewNombreCompleto.class, id);
		} finally {
			em.close();
		}
	}

	public int getViewNombreCompletoCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<ViewNombreCompleto> rt = cq.from(ViewNombreCompleto.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}
	
}
