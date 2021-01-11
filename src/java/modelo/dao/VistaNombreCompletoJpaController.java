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
import modelo.entidades.VistaNombreCompleto;

/**
 *
 * @author mjavi
 */
public class VistaNombreCompletoJpaController implements Serializable {

	public VistaNombreCompletoJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}
	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(VistaNombreCompleto vistaNombreCompleto) {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(vistaNombreCompleto);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(VistaNombreCompleto vistaNombreCompleto) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			vistaNombreCompleto = em.merge(vistaNombreCompleto);
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				int id = vistaNombreCompleto.getIdEmpleado();
				if (findVistaNombreCompleto(id) == null) {
					throw new NonexistentEntityException("The vistaNombreCompleto with id " + id + " no longer exists.");
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
			VistaNombreCompleto vistaNombreCompleto;
			try {
				vistaNombreCompleto = em.getReference(VistaNombreCompleto.class, id);
				vistaNombreCompleto.getIdEmpleado();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The vistaNombreCompleto with id " + id + " no longer exists.", enfe);
			}
			em.remove(vistaNombreCompleto);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<VistaNombreCompleto> findVistaNombreCompletoEntities() {
		return findVistaNombreCompletoEntities(true, -1, -1);
	}

	public List<VistaNombreCompleto> findVistaNombreCompletoEntities(int maxResults, int firstResult) {
		return findVistaNombreCompletoEntities(false, maxResults, firstResult);
	}

	private List<VistaNombreCompleto> findVistaNombreCompletoEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(VistaNombreCompleto.class));
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

	public VistaNombreCompleto findVistaNombreCompleto(int id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(VistaNombreCompleto.class, id);
		} finally {
			em.close();
		}
	}

	public int getVistaNombreCompletoCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<VistaNombreCompleto> rt = cq.from(VistaNombreCompleto.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}
	
}
