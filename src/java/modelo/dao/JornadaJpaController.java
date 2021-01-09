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
import modelo.entidades.TipoEmpleado;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.dao.exceptions.NonexistentEntityException;
import modelo.entidades.Jornada;

/**
 *
 * @author mjavi
 */
public class JornadaJpaController implements Serializable {

	public JornadaJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}
	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(Jornada jornada) {
		if (jornada.getTipoEmpleadoList() == null) {
			jornada.setTipoEmpleadoList(new ArrayList<TipoEmpleado>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			List<TipoEmpleado> attachedTipoEmpleadoList = new ArrayList<TipoEmpleado>();
			for (TipoEmpleado tipoEmpleadoListTipoEmpleadoToAttach : jornada.getTipoEmpleadoList()) {
				tipoEmpleadoListTipoEmpleadoToAttach = em.getReference(tipoEmpleadoListTipoEmpleadoToAttach.getClass(), tipoEmpleadoListTipoEmpleadoToAttach.getIdTipoEmpleado());
				attachedTipoEmpleadoList.add(tipoEmpleadoListTipoEmpleadoToAttach);
			}
			jornada.setTipoEmpleadoList(attachedTipoEmpleadoList);
			em.persist(jornada);
			for (TipoEmpleado tipoEmpleadoListTipoEmpleado : jornada.getTipoEmpleadoList()) {
				Jornada oldIdJornadaOfTipoEmpleadoListTipoEmpleado = tipoEmpleadoListTipoEmpleado.getIdJornada();
				tipoEmpleadoListTipoEmpleado.setIdJornada(jornada);
				tipoEmpleadoListTipoEmpleado = em.merge(tipoEmpleadoListTipoEmpleado);
				if (oldIdJornadaOfTipoEmpleadoListTipoEmpleado != null) {
					oldIdJornadaOfTipoEmpleadoListTipoEmpleado.getTipoEmpleadoList().remove(tipoEmpleadoListTipoEmpleado);
					oldIdJornadaOfTipoEmpleadoListTipoEmpleado = em.merge(oldIdJornadaOfTipoEmpleadoListTipoEmpleado);
				}
			}
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(Jornada jornada) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Jornada persistentJornada = em.find(Jornada.class, jornada.getIdJornada());
			List<TipoEmpleado> tipoEmpleadoListOld = persistentJornada.getTipoEmpleadoList();
			List<TipoEmpleado> tipoEmpleadoListNew = jornada.getTipoEmpleadoList();
			List<TipoEmpleado> attachedTipoEmpleadoListNew = new ArrayList<TipoEmpleado>();
			for (TipoEmpleado tipoEmpleadoListNewTipoEmpleadoToAttach : tipoEmpleadoListNew) {
				tipoEmpleadoListNewTipoEmpleadoToAttach = em.getReference(tipoEmpleadoListNewTipoEmpleadoToAttach.getClass(), tipoEmpleadoListNewTipoEmpleadoToAttach.getIdTipoEmpleado());
				attachedTipoEmpleadoListNew.add(tipoEmpleadoListNewTipoEmpleadoToAttach);
			}
			tipoEmpleadoListNew = attachedTipoEmpleadoListNew;
			jornada.setTipoEmpleadoList(tipoEmpleadoListNew);
			jornada = em.merge(jornada);
			for (TipoEmpleado tipoEmpleadoListOldTipoEmpleado : tipoEmpleadoListOld) {
				if (!tipoEmpleadoListNew.contains(tipoEmpleadoListOldTipoEmpleado)) {
					tipoEmpleadoListOldTipoEmpleado.setIdJornada(null);
					tipoEmpleadoListOldTipoEmpleado = em.merge(tipoEmpleadoListOldTipoEmpleado);
				}
			}
			for (TipoEmpleado tipoEmpleadoListNewTipoEmpleado : tipoEmpleadoListNew) {
				if (!tipoEmpleadoListOld.contains(tipoEmpleadoListNewTipoEmpleado)) {
					Jornada oldIdJornadaOfTipoEmpleadoListNewTipoEmpleado = tipoEmpleadoListNewTipoEmpleado.getIdJornada();
					tipoEmpleadoListNewTipoEmpleado.setIdJornada(jornada);
					tipoEmpleadoListNewTipoEmpleado = em.merge(tipoEmpleadoListNewTipoEmpleado);
					if (oldIdJornadaOfTipoEmpleadoListNewTipoEmpleado != null && !oldIdJornadaOfTipoEmpleadoListNewTipoEmpleado.equals(jornada)) {
						oldIdJornadaOfTipoEmpleadoListNewTipoEmpleado.getTipoEmpleadoList().remove(tipoEmpleadoListNewTipoEmpleado);
						oldIdJornadaOfTipoEmpleadoListNewTipoEmpleado = em.merge(oldIdJornadaOfTipoEmpleadoListNewTipoEmpleado);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Integer id = jornada.getIdJornada();
				if (findJornada(id) == null) {
					throw new NonexistentEntityException("The jornada with id " + id + " no longer exists.");
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
			Jornada jornada;
			try {
				jornada = em.getReference(Jornada.class, id);
				jornada.getIdJornada();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The jornada with id " + id + " no longer exists.", enfe);
			}
			List<TipoEmpleado> tipoEmpleadoList = jornada.getTipoEmpleadoList();
			for (TipoEmpleado tipoEmpleadoListTipoEmpleado : tipoEmpleadoList) {
				tipoEmpleadoListTipoEmpleado.setIdJornada(null);
				tipoEmpleadoListTipoEmpleado = em.merge(tipoEmpleadoListTipoEmpleado);
			}
			em.remove(jornada);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<Jornada> findJornadaEntities() {
		return findJornadaEntities(true, -1, -1);
	}

	public List<Jornada> findJornadaEntities(int maxResults, int firstResult) {
		return findJornadaEntities(false, maxResults, firstResult);
	}

	private List<Jornada> findJornadaEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Jornada.class));
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

	public Jornada findJornada(Integer id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Jornada.class, id);
		} finally {
			em.close();
		}
	}

	public int getJornadaCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Jornada> rt = cq.from(Jornada.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}
	
}
