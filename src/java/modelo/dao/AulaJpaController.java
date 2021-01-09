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
import modelo.entidades.Horario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.dao.exceptions.IllegalOrphanException;
import modelo.dao.exceptions.NonexistentEntityException;
import modelo.entidades.Aula;

/**
 *
 * @author mjavi
 */
public class AulaJpaController implements Serializable {

	public AulaJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}
	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(Aula aula) {
		if (aula.getHorarioList() == null) {
			aula.setHorarioList(new ArrayList<Horario>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			List<Horario> attachedHorarioList = new ArrayList<Horario>();
			for (Horario horarioListHorarioToAttach : aula.getHorarioList()) {
				horarioListHorarioToAttach = em.getReference(horarioListHorarioToAttach.getClass(), horarioListHorarioToAttach.getIdHorario());
				attachedHorarioList.add(horarioListHorarioToAttach);
			}
			aula.setHorarioList(attachedHorarioList);
			em.persist(aula);
			for (Horario horarioListHorario : aula.getHorarioList()) {
				Aula oldIdAulaOfHorarioListHorario = horarioListHorario.getIdAula();
				horarioListHorario.setIdAula(aula);
				horarioListHorario = em.merge(horarioListHorario);
				if (oldIdAulaOfHorarioListHorario != null) {
					oldIdAulaOfHorarioListHorario.getHorarioList().remove(horarioListHorario);
					oldIdAulaOfHorarioListHorario = em.merge(oldIdAulaOfHorarioListHorario);
				}
			}
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(Aula aula) throws IllegalOrphanException, NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Aula persistentAula = em.find(Aula.class, aula.getIdAula());
			List<Horario> horarioListOld = persistentAula.getHorarioList();
			List<Horario> horarioListNew = aula.getHorarioList();
			List<String> illegalOrphanMessages = null;
			for (Horario horarioListOldHorario : horarioListOld) {
				if (!horarioListNew.contains(horarioListOldHorario)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Horario " + horarioListOldHorario + " since its idAula field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			List<Horario> attachedHorarioListNew = new ArrayList<Horario>();
			for (Horario horarioListNewHorarioToAttach : horarioListNew) {
				horarioListNewHorarioToAttach = em.getReference(horarioListNewHorarioToAttach.getClass(), horarioListNewHorarioToAttach.getIdHorario());
				attachedHorarioListNew.add(horarioListNewHorarioToAttach);
			}
			horarioListNew = attachedHorarioListNew;
			aula.setHorarioList(horarioListNew);
			aula = em.merge(aula);
			for (Horario horarioListNewHorario : horarioListNew) {
				if (!horarioListOld.contains(horarioListNewHorario)) {
					Aula oldIdAulaOfHorarioListNewHorario = horarioListNewHorario.getIdAula();
					horarioListNewHorario.setIdAula(aula);
					horarioListNewHorario = em.merge(horarioListNewHorario);
					if (oldIdAulaOfHorarioListNewHorario != null && !oldIdAulaOfHorarioListNewHorario.equals(aula)) {
						oldIdAulaOfHorarioListNewHorario.getHorarioList().remove(horarioListNewHorario);
						oldIdAulaOfHorarioListNewHorario = em.merge(oldIdAulaOfHorarioListNewHorario);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Integer id = aula.getIdAula();
				if (findAula(id) == null) {
					throw new NonexistentEntityException("The aula with id " + id + " no longer exists.");
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
			Aula aula;
			try {
				aula = em.getReference(Aula.class, id);
				aula.getIdAula();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The aula with id " + id + " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Horario> horarioListOrphanCheck = aula.getHorarioList();
			for (Horario horarioListOrphanCheckHorario : horarioListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Aula (" + aula + ") cannot be destroyed since the Horario " + horarioListOrphanCheckHorario + " in its horarioList field has a non-nullable idAula field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			em.remove(aula);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<Aula> findAulaEntities() {
		return findAulaEntities(true, -1, -1);
	}

	public List<Aula> findAulaEntities(int maxResults, int firstResult) {
		return findAulaEntities(false, maxResults, firstResult);
	}

	private List<Aula> findAulaEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Aula.class));
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

	public Aula findAula(Integer id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Aula.class, id);
		} finally {
			em.close();
		}
	}

	public int getAulaCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Aula> rt = cq.from(Aula.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}
	
}
