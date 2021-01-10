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
		if (tipo.getFechaHabileList() == null) {
			tipo.setFechaHabileList(new ArrayList<FechaHabil>());
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
			List<FechaHabil> attachedFechaHabileList = new ArrayList<FechaHabil>();
			for (FechaHabil fechaHabileListFechaHabilToAttach : tipo.getFechaHabileList()) {
				fechaHabileListFechaHabilToAttach = em.getReference(fechaHabileListFechaHabilToAttach.getClass(), fechaHabileListFechaHabilToAttach.getIdFechaHabil());
				attachedFechaHabileList.add(fechaHabileListFechaHabilToAttach);
			}
			tipo.setFechaHabileList(attachedFechaHabileList);
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
			for (FechaHabil fechaHabileListFechaHabil : tipo.getFechaHabileList()) {
				Tipo oldIdTipoOfFechaHabileListFechaHabil = fechaHabileListFechaHabil.getIdTipo();
				fechaHabileListFechaHabil.setIdTipo(tipo);
				fechaHabileListFechaHabil = em.merge(fechaHabileListFechaHabil);
				if (oldIdTipoOfFechaHabileListFechaHabil != null) {
					oldIdTipoOfFechaHabileListFechaHabil.getFechaHabileList().remove(fechaHabileListFechaHabil);
					oldIdTipoOfFechaHabileListFechaHabil = em.merge(oldIdTipoOfFechaHabileListFechaHabil);
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
			List<FechaHabil> fechaHabileListOld = persistentTipo.getFechaHabileList();
			List<FechaHabil> fechaHabileListNew = tipo.getFechaHabileList();
			List<Jornada> jornadaListOld = persistentTipo.getJornadaList();
			List<Jornada> jornadaListNew = tipo.getJornadaList();
			List<TipoEmpleado> tipoEmpleadoListOld = persistentTipo.getTipoEmpleadoList();
			List<TipoEmpleado> tipoEmpleadoListNew = tipo.getTipoEmpleadoList();
			List<String> illegalOrphanMessages = null;
			for (FechaHabil fechaHabileListOldFechaHabil : fechaHabileListOld) {
				if (!fechaHabileListNew.contains(fechaHabileListOldFechaHabil)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain FechaHabil " + fechaHabileListOldFechaHabil + " since its idTipo field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			List<FechaHabil> attachedFechaHabileListNew = new ArrayList<FechaHabil>();
			for (FechaHabil fechaHabileListNewFechaHabilToAttach : fechaHabileListNew) {
				fechaHabileListNewFechaHabilToAttach = em.getReference(fechaHabileListNewFechaHabilToAttach.getClass(), fechaHabileListNewFechaHabilToAttach.getIdFechaHabil());
				attachedFechaHabileListNew.add(fechaHabileListNewFechaHabilToAttach);
			}
			fechaHabileListNew = attachedFechaHabileListNew;
			tipo.setFechaHabileList(fechaHabileListNew);
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
			for (FechaHabil fechaHabileListNewFechaHabil : fechaHabileListNew) {
				if (!fechaHabileListOld.contains(fechaHabileListNewFechaHabil)) {
					Tipo oldIdTipoOfFechaHabileListNewFechaHabil = fechaHabileListNewFechaHabil.getIdTipo();
					fechaHabileListNewFechaHabil.setIdTipo(tipo);
					fechaHabileListNewFechaHabil = em.merge(fechaHabileListNewFechaHabil);
					if (oldIdTipoOfFechaHabileListNewFechaHabil != null && !oldIdTipoOfFechaHabileListNewFechaHabil.equals(tipo)) {
						oldIdTipoOfFechaHabileListNewFechaHabil.getFechaHabileList().remove(fechaHabileListNewFechaHabil);
						oldIdTipoOfFechaHabileListNewFechaHabil = em.merge(oldIdTipoOfFechaHabileListNewFechaHabil);
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
			List<FechaHabil> fechaHabileListOrphanCheck = tipo.getFechaHabileList();
			for (FechaHabil fechaHabileListOrphanCheckFechaHabil : fechaHabileListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Tipo (" + tipo + ") cannot be destroyed since the FechaHabil " + fechaHabileListOrphanCheckFechaHabil + " in its fechaHabileList field has a non-nullable idTipo field.");
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
