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
import modelo.entidades.Materia;

/**
 *
 * @author mjavi
 */
public class MateriaJpaController implements Serializable {

	public MateriaJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}
	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(Materia materia) {
		if (materia.getHorarioList() == null) {
			materia.setHorarioList(new ArrayList<Horario>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			List<Horario> attachedHorarioList = new ArrayList<Horario>();
			for (Horario horarioListHorarioToAttach : materia.getHorarioList()) {
				horarioListHorarioToAttach = em.getReference(horarioListHorarioToAttach.getClass(), horarioListHorarioToAttach.getIdHorario());
				attachedHorarioList.add(horarioListHorarioToAttach);
			}
			materia.setHorarioList(attachedHorarioList);
			em.persist(materia);
			for (Horario horarioListHorario : materia.getHorarioList()) {
				Materia oldIdMateriaOfHorarioListHorario = horarioListHorario.getIdMateria();
				horarioListHorario.setIdMateria(materia);
				horarioListHorario = em.merge(horarioListHorario);
				if (oldIdMateriaOfHorarioListHorario != null) {
					oldIdMateriaOfHorarioListHorario.getHorarioList().remove(horarioListHorario);
					oldIdMateriaOfHorarioListHorario = em.merge(oldIdMateriaOfHorarioListHorario);
				}
			}
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(Materia materia) throws IllegalOrphanException, NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Materia persistentMateria = em.find(Materia.class, materia.getIdMateria());
			List<Horario> horarioListOld = persistentMateria.getHorarioList();
			List<Horario> horarioListNew = materia.getHorarioList();
			List<String> illegalOrphanMessages = null;
			for (Horario horarioListOldHorario : horarioListOld) {
				if (!horarioListNew.contains(horarioListOldHorario)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain Horario " + horarioListOldHorario + " since its idMateria field is not nullable.");
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
			materia.setHorarioList(horarioListNew);
			materia = em.merge(materia);
			for (Horario horarioListNewHorario : horarioListNew) {
				if (!horarioListOld.contains(horarioListNewHorario)) {
					Materia oldIdMateriaOfHorarioListNewHorario = horarioListNewHorario.getIdMateria();
					horarioListNewHorario.setIdMateria(materia);
					horarioListNewHorario = em.merge(horarioListNewHorario);
					if (oldIdMateriaOfHorarioListNewHorario != null && !oldIdMateriaOfHorarioListNewHorario.equals(materia)) {
						oldIdMateriaOfHorarioListNewHorario.getHorarioList().remove(horarioListNewHorario);
						oldIdMateriaOfHorarioListNewHorario = em.merge(oldIdMateriaOfHorarioListNewHorario);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Integer id = materia.getIdMateria();
				if (findMateria(id) == null) {
					throw new NonexistentEntityException("The materia with id " + id + " no longer exists.");
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
			Materia materia;
			try {
				materia = em.getReference(Materia.class, id);
				materia.getIdMateria();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The materia with id " + id + " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			List<Horario> horarioListOrphanCheck = materia.getHorarioList();
			for (Horario horarioListOrphanCheckHorario : horarioListOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This Materia (" + materia + ") cannot be destroyed since the Horario " + horarioListOrphanCheckHorario + " in its horarioList field has a non-nullable idMateria field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			em.remove(materia);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<Materia> findMateriaEntities() {
		return findMateriaEntities(true, -1, -1);
	}

	public List<Materia> findMateriaEntities(int maxResults, int firstResult) {
		return findMateriaEntities(false, maxResults, firstResult);
	}

	private List<Materia> findMateriaEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Materia.class));
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

	public Materia findMateria(Integer id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Materia.class, id);
		} finally {
			em.close();
		}
	}

	public int getMateriaCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Materia> rt = cq.from(Materia.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}
	
}
