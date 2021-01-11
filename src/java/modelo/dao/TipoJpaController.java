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
import modelo.entidades.FechaHabil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.dao.exceptions.IllegalOrphanException;
import modelo.dao.exceptions.NonexistentEntityException;
import modelo.entidades.Jornada;
import modelo.entidades.Tipo;
import modelo.entidades.TipoEmpleado;

/**
 *
 * @author mjavi
 */
public class TipoJpaController implements Serializable {

	public TipoJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}
	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(Tipo tipo) {
		if (tipo.getFechaHabilList() == null) {
			tipo.setFechaHabilList(new ArrayList<FechaHabil>());
		}
		if (tipo.getJornadaList() == null) {
			tipo.setJornadaList(new ArrayList<Jornada>());
		}
		if (tipo.getTipoEmpleadoList() == null) {
			tipo.setTipoEmpleadoList(new ArrayList<TipoEmpleado>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			List<FechaHabil> attachedFechaHabilList = new ArrayList<FechaHabil>();
			for (FechaHabil fechaHabilListFechaHabilToAttach : tipo.getFechaHabilList()) {
				fechaHabilListFechaHabilToAttach = em.getReference(fechaHabilListFechaHabilToAttach.getClass(), fechaHabilListFechaHabilToAttach.getIdFechaHabil());
				attachedFechaHabilList.add(fechaHabilListFechaHabilToAttach);
			}
			tipo.setFechaHabilList(attachedFechaHabilList);
			List<Jornada> attachedJornadaList = new ArrayList<Jornada>();
			for (Jornada jornadaListJornadaToAttach : tipo.getJornadaList()) {
				jornadaListJornadaToAttach = em.getReference(jornadaListJornadaToAttach.getClass(), jornadaListJornadaToAttach.getIdJornada());
				attachedJornadaList.add(jornadaListJornadaToAttach);
			}
			tipo.setJornadaList(attachedJornadaList);
			List<TipoEmpleado> attachedTipoEmpleadoList = new ArrayList<TipoEmpleado>();
			for (TipoEmpleado tipoEmpleadoListTipoEmpleadoToAttach : tipo.getTipoEmpleadoList()) {
				tipoEmpleadoListTipoEmpleadoToAttach = em.getReference(tipoEmpleadoListTipoEmpleadoToAttach.getClass(), tipoEmpleadoListTipoEmpleadoToAttach.getIdTipoEmpleado());
				attachedTipoEmpleadoList.add(tipoEmpleadoListTipoEmpleadoToAttach);
			}
			tipo.setTipoEmpleadoList(attachedTipoEmpleadoList);
			em.persist(tipo);
			for (FechaHabil fechaHabilListFechaHabil : tipo.getFechaHabilList()) {
				Tipo oldIdTipoOfFechaHabilListFechaHabil = fechaHabilListFechaHabil.getIdTipo();
				fechaHabilListFechaHabil.setIdTipo(tipo);
				fechaHabilListFechaHabil = em.merge(fechaHabilListFechaHabil);
				if (oldIdTipoOfFechaHabilListFechaHabil != null) {
					oldIdTipoOfFechaHabilListFechaHabil.getFechaHabilList().remove(fechaHabilListFechaHabil);
					oldIdTipoOfFechaHabilListFechaHabil = em.merge(oldIdTipoOfFechaHabilListFechaHabil);
				}
			}
			for (Jornada jornadaListJornada : tipo.getJornadaList()) {
				Tipo oldIdTipoOfJornadaListJornada = jornadaListJornada.getIdTipo();
				jornadaListJornada.setIdTipo(tipo);
				jornadaListJornada = em.merge(jornadaListJornada);
				if (oldIdTipoOfJornadaListJornada != null) {
					oldIdTipoOfJornadaListJornada.getJornadaList().remove(jornadaListJornada);
					oldIdTipoOfJornadaListJornada = em.merge(oldIdTipoOfJornadaListJornada);
				}
			}
			for (TipoEmpleado tipoEmpleadoListTipoEmpleado : tipo.getTipoEmpleadoList()) {
				Tipo oldIdTipoOfTipoEmpleadoListTipoEmpleado = tipoEmpleadoListTipoEmpleado.getIdTipo();
				tipoEmpleadoListTipoEmpleado.setIdTipo(tipo);
				tipoEmpleadoListTipoEmpleado = em.merge(tipoEmpleadoListTipoEmpleado);
				if (oldIdTipoOfTipoEmpleadoListTipoEmpleado != null) {
					oldIdTipoOfTipoEmpleadoListTipoEmpleado.getTipoEmpleadoList().remove(tipoEmpleadoListTipoEmpleado);
					oldIdTipoOfTipoEmpleadoListTipoEmpleado = em.merge(oldIdTipoOfTipoEmpleadoListTipoEmpleado);
				}
			}
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(Tipo tipo) throws IllegalOrphanException, NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Tipo persistentTipo = em.find(Tipo.class, tipo.getIdTipo());
			List<FechaHabil> fechaHabilListOld = persistentTipo.getFechaHabilList();
			List<FechaHabil> fechaHabilListNew = tipo.getFechaHabilList();
			List<Jornada> jornadaListOld = persistentTipo.getJornadaList();
			List<Jornada> jornadaListNew = tipo.getJornadaList();
			List<TipoEmpleado> tipoEmpleadoListOld = persistentTipo.getTipoEmpleadoList();
			List<TipoEmpleado> tipoEmpleadoListNew = tipo.getTipoEmpleadoList();
			List<String> illegalOrphanMessages = null;
			for (FechaHabil fechaHabilListOldFechaHabil : fechaHabilListOld) {
				if (!fechaHabilListNew.contains(fechaHabilListOldFechaHabil)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain FechaHabil " + fechaHabilListOldFechaHabil + " since its idTipo field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			List<FechaHabil> attachedFechaHabilListNew = new ArrayList<FechaHabil>();
			for (FechaHabil fechaHabilListNewFechaHabilToAttach : fechaHabilListNew) {
				fechaHabilListNewFechaHabilToAttach = em.getReference(fechaHabilListNewFechaHabilToAttach.getClass(), fechaHabilListNewFechaHabilToAttach.getIdFechaHabil());
				attachedFechaHabilListNew.add(fechaHabilListNewFechaHabilToAttach);
			}
			fechaHabilListNew = attachedFechaHabilListNew;
			tipo.setFechaHabilList(fechaHabilListNew);
			List<Jornada> attachedJornadaListNew = new ArrayList<Jornada>();
			for (Jornada jornadaListNewJornadaToAttach : jornadaListNew) {
				jornadaListNewJornadaToAttach = em.getReference(jornadaListNewJornadaToAttach.getClass(), jornadaListNewJornadaToAttach.getIdJornada());
				attachedJornadaListNew.add(jornadaListNewJornadaToAttach);
			}
			jornadaListNew = attachedJornadaListNew;
			tipo.setJornadaList(jornadaListNew);
			List<TipoEmpleado> attachedTipoEmpleadoListNew = new ArrayList<TipoEmpleado>();
			for (TipoEmpleado tipoEmpleadoListNewTipoEmpleadoToAttach : tipoEmpleadoListNew) {
				tipoEmpleadoListNewTipoEmpleadoToAttach = em.getReference(tipoEmpleadoListNewTipoEmpleadoToAttach.getClass(), tipoEmpleadoListNewTipoEmpleadoToAttach.getIdTipoEmpleado());
				attachedTipoEmpleadoListNew.add(tipoEmpleadoListNewTipoEmpleadoToAttach);
			}
			tipoEmpleadoListNew = attachedTipoEmpleadoListNew;
			tipo.setTipoEmpleadoList(tipoEmpleadoListNew);
			tipo = em.merge(tipo);
			for (FechaHabil fechaHabilListNewFechaHabil : fechaHabilListNew) {
				if (!fechaHabilListOld.contains(fechaHabilListNewFechaHabil)) {
					Tipo oldIdTipoOfFechaHabilListNewFechaHabil = fechaHabilListNewFechaHabil.getIdTipo();
					fechaHabilListNewFechaHabil.setIdTipo(tipo);
					fechaHabilListNewFechaHabil = em.merge(fechaHabilListNewFechaHabil);
					if (oldIdTipoOfFechaHabilListNewFechaHabil != null && !oldIdTipoOfFechaHabilListNewFechaHabil.equals(tipo)) {
						oldIdTipoOfFechaHabilListNewFechaHabil.getFechaHabilList().remove(fechaHabilListNewFechaHabil);
						oldIdTipoOfFechaHabilListNewFechaHabil = em.merge(oldIdTipoOfFechaHabilListNewFechaHabil);
					}
				}
			}
			for (Jornada jornadaListOldJornada : jornadaListOld) {
				if (!jornadaListNew.contains(jornadaListOldJornada)) {
					jornadaListOldJornada.setIdTipo(null);
					jornadaListOldJornada = em.merge(jornadaListOldJornada);
				}
			}
			for (Jornada jornadaListNewJornada : jornadaListNew) {
				if (!jornadaListOld.contains(jornadaListNewJornada)) {
					Tipo oldIdTipoOfJornadaListNewJornada = jornadaListNewJornada.getIdTipo();
					jornadaListNewJornada.setIdTipo(tipo);
					jornadaListNewJornada = em.merge(jornadaListNewJornada);
					if (oldIdTipoOfJornadaListNewJornada != null && !oldIdTipoOfJornadaListNewJornada.equals(tipo)) {
						oldIdTipoOfJornadaListNewJornada.getJornadaList().remove(jornadaListNewJornada);
						oldIdTipoOfJornadaListNewJornada = em.merge(oldIdTipoOfJornadaListNewJornada);
					}
				}
			}
			for (TipoEmpleado tipoEmpleadoListOldTipoEmpleado : tipoEmpleadoListOld) {
				if (!tipoEmpleadoListNew.contains(tipoEmpleadoListOldTipoEmpleado)) {
					tipoEmpleadoListOldTipoEmpleado.setIdTipo(null);
					tipoEmpleadoListOldTipoEmpleado = em.merge(tipoEmpleadoListOldTipoEmpleado);
				}
			}
			for (TipoEmpleado tipoEmpleadoListNewTipoEmpleado : tipoEmpleadoListNew) {
				if (!tipoEmpleadoListOld.contains(tipoEmpleadoListNewTipoEmpleado)) {
					Tipo oldIdTipoOfTipoEmpleadoListNewTipoEmpleado = tipoEmpleadoListNewTipoEmpleado.getIdTipo();
					tipoEmpleadoListNewTipoEmpleado.setIdTipo(tipo);
					tipoEmpleadoListNewTipoEmpleado = em.merge(tipoEmpleadoListNewTipoEmpleado);
					if (oldIdTipoOfTipoEmpleadoListNewTipoEmpleado != null && !oldIdTipoOfTipoEmpleadoListNewTipoEmpleado.equals(tipo)) {
						oldIdTipoOfTipoEmpleadoListNewTipoEmpleado.getTipoEmpleadoList().remove(tipoEmpleadoListNewTipoEmpleado);
						oldIdTipoOfTipoEmpleadoListNewTipoEmpleado = em.merge(oldIdTipoOfTipoEmpleadoListNewTipoEmpleado);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Integer id = tipo.getIdTipo();
				if (findTipo(id) == null) {
					throw new NonexistentEntityException("The tipo with id " + id + " no longer exists.");
				}
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Tipo tipo;
			try {
				tipo = em.getReference(Tipo.class, id);
				tipo.getIdTipo();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The tipo with id " + id + " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<FechaHabil> fechaHabilListOrphanCheck = tipo.getFechaHabilList();
			for (FechaHabil fechaHabilListOrphanCheckFechaHabil : fechaHabilListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Tipo (" + tipo + ") cannot be destroyed since the FechaHabil " + fechaHabilListOrphanCheckFechaHabil + " in its fechaHabilList field has a non-nullable idTipo field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			List<Jornada> jornadaList = tipo.getJornadaList();
			for (Jornada jornadaListJornada : jornadaList) {
				jornadaListJornada.setIdTipo(null);
				jornadaListJornada = em.merge(jornadaListJornada);
			}
			List<TipoEmpleado> tipoEmpleadoList = tipo.getTipoEmpleadoList();
			for (TipoEmpleado tipoEmpleadoListTipoEmpleado : tipoEmpleadoList) {
				tipoEmpleadoListTipoEmpleado.setIdTipo(null);
				tipoEmpleadoListTipoEmpleado = em.merge(tipoEmpleadoListTipoEmpleado);
			}
			em.remove(tipo);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<Tipo> findTipoEntities() {
		return findTipoEntities(true, -1, -1);
	}

	public List<Tipo> findTipoEntities(int maxResults, int firstResult) {
		return findTipoEntities(false, maxResults, firstResult);
	}

	private List<Tipo> findTipoEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Tipo.class));
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

	public Tipo findTipo(Integer id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Tipo.class, id);
		} finally {
			em.close();
		}
	}

	public int getTipoCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Tipo> rt = cq.from(Tipo.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}
	
}
