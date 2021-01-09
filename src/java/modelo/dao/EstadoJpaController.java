/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.entidades.Asistencia;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.dao.exceptions.NonexistentEntityException;
import modelo.entidades.Estado;

/**
 *
 * @author mjavi
 */
public class EstadoJpaController implements Serializable {

	public EstadoJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}
	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(Estado estado) {
		if (estado.getAsistenciaList() == null) {
			estado.setAsistenciaList(new ArrayList<Asistencia>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			List<Asistencia> attachedAsistenciaList = new ArrayList<Asistencia>();
			for (Asistencia asistenciaListAsistenciaToAttach : estado.getAsistenciaList()) {
				asistenciaListAsistenciaToAttach = em.getReference(asistenciaListAsistenciaToAttach.getClass(), asistenciaListAsistenciaToAttach.getIdAsistencia());
				attachedAsistenciaList.add(asistenciaListAsistenciaToAttach);
			}
			estado.setAsistenciaList(attachedAsistenciaList);
			em.persist(estado);
			for (Asistencia asistenciaListAsistencia : estado.getAsistenciaList()) {
				Estado oldIdEstadoOfAsistenciaListAsistencia = asistenciaListAsistencia.getIdEstado();
				asistenciaListAsistencia.setIdEstado(estado);
				asistenciaListAsistencia = em.merge(asistenciaListAsistencia);
				if (oldIdEstadoOfAsistenciaListAsistencia != null) {
					oldIdEstadoOfAsistenciaListAsistencia.getAsistenciaList().remove(asistenciaListAsistencia);
					oldIdEstadoOfAsistenciaListAsistencia = em.merge(oldIdEstadoOfAsistenciaListAsistencia);
				}
			}
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(Estado estado) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Estado persistentEstado = em.find(Estado.class, estado.getIdEstado());
			List<Asistencia> asistenciaListOld = persistentEstado.getAsistenciaList();
			List<Asistencia> asistenciaListNew = estado.getAsistenciaList();
			List<Asistencia> attachedAsistenciaListNew = new ArrayList<Asistencia>();
			for (Asistencia asistenciaListNewAsistenciaToAttach : asistenciaListNew) {
				asistenciaListNewAsistenciaToAttach = em.getReference(asistenciaListNewAsistenciaToAttach.getClass(), asistenciaListNewAsistenciaToAttach.getIdAsistencia());
				attachedAsistenciaListNew.add(asistenciaListNewAsistenciaToAttach);
			}
			asistenciaListNew = attachedAsistenciaListNew;
			estado.setAsistenciaList(asistenciaListNew);
			estado = em.merge(estado);
			for (Asistencia asistenciaListOldAsistencia : asistenciaListOld) {
				if (!asistenciaListNew.contains(asistenciaListOldAsistencia)) {
					asistenciaListOldAsistencia.setIdEstado(null);
					asistenciaListOldAsistencia = em.merge(asistenciaListOldAsistencia);
				}
			}
			for (Asistencia asistenciaListNewAsistencia : asistenciaListNew) {
				if (!asistenciaListOld.contains(asistenciaListNewAsistencia)) {
					Estado oldIdEstadoOfAsistenciaListNewAsistencia = asistenciaListNewAsistencia.getIdEstado();
					asistenciaListNewAsistencia.setIdEstado(estado);
					asistenciaListNewAsistencia = em.merge(asistenciaListNewAsistencia);
					if (oldIdEstadoOfAsistenciaListNewAsistencia != null && !oldIdEstadoOfAsistenciaListNewAsistencia.equals(estado)) {
						oldIdEstadoOfAsistenciaListNewAsistencia.getAsistenciaList().remove(asistenciaListNewAsistencia);
						oldIdEstadoOfAsistenciaListNewAsistencia = em.merge(oldIdEstadoOfAsistenciaListNewAsistencia);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Integer id = estado.getIdEstado();
				if (findEstado(id) == null) {
					throw new NonexistentEntityException("The estado with id " + id + " no longer exists.");
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
			Estado estado;
			try {
				estado = em.getReference(Estado.class, id);
				estado.getIdEstado();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The estado with id " + id + " no longer exists.", enfe);
			}
			List<Asistencia> asistenciaList = estado.getAsistenciaList();
			for (Asistencia asistenciaListAsistencia : asistenciaList) {
				asistenciaListAsistencia.setIdEstado(null);
				asistenciaListAsistencia = em.merge(asistenciaListAsistencia);
			}
			em.remove(estado);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<Estado> findEstadoEntities() {
		return findEstadoEntities(true, -1, -1);
	}

	public List<Estado> findEstadoEntities(int maxResults, int firstResult) {
		return findEstadoEntities(false, maxResults, firstResult);
	}

	private List<Estado> findEstadoEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Estado.class));
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

	public Estado findEstado(Integer id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Estado.class, id);
		} finally {
			em.close();
		}
	}

	public int getEstadoCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Estado> rt = cq.from(Estado.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}
	
}
