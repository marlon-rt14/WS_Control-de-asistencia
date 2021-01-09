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
import modelo.entidades.Tipo;
import modelo.entidades.Asistencia;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.dao.exceptions.NonexistentEntityException;
import modelo.entidades.FechaHabil;

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
		if (fechaHabil.getAsistenciaList() == null) {
			fechaHabil.setAsistenciaList(new ArrayList<Asistencia>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Tipo idTipo = fechaHabil.getIdTipo();
			if (idTipo != null) {
				idTipo = em.getReference(idTipo.getClass(), idTipo.getIdTipo());
				fechaHabil.setIdTipo(idTipo);
			}
			List<Asistencia> attachedAsistenciaList = new ArrayList<Asistencia>();
			for (Asistencia asistenciaListAsistenciaToAttach : fechaHabil.getAsistenciaList()) {
				asistenciaListAsistenciaToAttach = em.getReference(asistenciaListAsistenciaToAttach.getClass(), asistenciaListAsistenciaToAttach.getIdAsistencia());
				attachedAsistenciaList.add(asistenciaListAsistenciaToAttach);
			}
			fechaHabil.setAsistenciaList(attachedAsistenciaList);
			em.persist(fechaHabil);
			if (idTipo != null) {
				idTipo.getFechaHabilList().add(fechaHabil);
				idTipo = em.merge(idTipo);
			}
			for (Asistencia asistenciaListAsistencia : fechaHabil.getAsistenciaList()) {
				FechaHabil oldIdFechaHabilOfAsistenciaListAsistencia = asistenciaListAsistencia.getIdFechaHabil();
				asistenciaListAsistencia.setIdFechaHabil(fechaHabil);
				asistenciaListAsistencia = em.merge(asistenciaListAsistencia);
				if (oldIdFechaHabilOfAsistenciaListAsistencia != null) {
					oldIdFechaHabilOfAsistenciaListAsistencia.getAsistenciaList().remove(asistenciaListAsistencia);
					oldIdFechaHabilOfAsistenciaListAsistencia = em.merge(oldIdFechaHabilOfAsistenciaListAsistencia);
				}
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
			List<Asistencia> asistenciaListOld = persistentFechaHabil.getAsistenciaList();
			List<Asistencia> asistenciaListNew = fechaHabil.getAsistenciaList();
			if (idTipoNew != null) {
				idTipoNew = em.getReference(idTipoNew.getClass(), idTipoNew.getIdTipo());
				fechaHabil.setIdTipo(idTipoNew);
			}
			List<Asistencia> attachedAsistenciaListNew = new ArrayList<Asistencia>();
			for (Asistencia asistenciaListNewAsistenciaToAttach : asistenciaListNew) {
				asistenciaListNewAsistenciaToAttach = em.getReference(asistenciaListNewAsistenciaToAttach.getClass(), asistenciaListNewAsistenciaToAttach.getIdAsistencia());
				attachedAsistenciaListNew.add(asistenciaListNewAsistenciaToAttach);
			}
			asistenciaListNew = attachedAsistenciaListNew;
			fechaHabil.setAsistenciaList(asistenciaListNew);
			fechaHabil = em.merge(fechaHabil);
			if (idTipoOld != null && !idTipoOld.equals(idTipoNew)) {
				idTipoOld.getFechaHabilList().remove(fechaHabil);
				idTipoOld = em.merge(idTipoOld);
			}
			if (idTipoNew != null && !idTipoNew.equals(idTipoOld)) {
				idTipoNew.getFechaHabilList().add(fechaHabil);
				idTipoNew = em.merge(idTipoNew);
			}
			for (Asistencia asistenciaListOldAsistencia : asistenciaListOld) {
				if (!asistenciaListNew.contains(asistenciaListOldAsistencia)) {
					asistenciaListOldAsistencia.setIdFechaHabil(null);
					asistenciaListOldAsistencia = em.merge(asistenciaListOldAsistencia);
				}
			}
			for (Asistencia asistenciaListNewAsistencia : asistenciaListNew) {
				if (!asistenciaListOld.contains(asistenciaListNewAsistencia)) {
					FechaHabil oldIdFechaHabilOfAsistenciaListNewAsistencia = asistenciaListNewAsistencia.getIdFechaHabil();
					asistenciaListNewAsistencia.setIdFechaHabil(fechaHabil);
					asistenciaListNewAsistencia = em.merge(asistenciaListNewAsistencia);
					if (oldIdFechaHabilOfAsistenciaListNewAsistencia != null && !oldIdFechaHabilOfAsistenciaListNewAsistencia.equals(fechaHabil)) {
						oldIdFechaHabilOfAsistenciaListNewAsistencia.getAsistenciaList().remove(asistenciaListNewAsistencia);
						oldIdFechaHabilOfAsistenciaListNewAsistencia = em.merge(oldIdFechaHabilOfAsistenciaListNewAsistencia);
					}
				}
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
			List<Asistencia> asistenciaList = fechaHabil.getAsistenciaList();
			for (Asistencia asistenciaListAsistencia : asistenciaList) {
				asistenciaListAsistencia.setIdFechaHabil(null);
				asistenciaListAsistencia = em.merge(asistenciaListAsistencia);
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
