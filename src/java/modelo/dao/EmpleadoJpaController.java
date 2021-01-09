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
import modelo.entidades.Asistencia;
import modelo.entidades.Empleado;
import modelo.entidades.Horario;

/**
 *
 * @author mjavi
 */
public class EmpleadoJpaController implements Serializable {

	public EmpleadoJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}
	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(Empleado empleado) {
		if (empleado.getTipoEmpleadoList() == null) {
			empleado.setTipoEmpleadoList(new ArrayList<TipoEmpleado>());
		}
		if (empleado.getAsistenciaList() == null) {
			empleado.setAsistenciaList(new ArrayList<Asistencia>());
		}
		if (empleado.getHorarioList() == null) {
			empleado.setHorarioList(new ArrayList<Horario>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			List<TipoEmpleado> attachedTipoEmpleadoList = new ArrayList<TipoEmpleado>();
			for (TipoEmpleado tipoEmpleadoListTipoEmpleadoToAttach : empleado.getTipoEmpleadoList()) {
				tipoEmpleadoListTipoEmpleadoToAttach = em.getReference(tipoEmpleadoListTipoEmpleadoToAttach.getClass(), tipoEmpleadoListTipoEmpleadoToAttach.getIdTipoEmpleado());
				attachedTipoEmpleadoList.add(tipoEmpleadoListTipoEmpleadoToAttach);
			}
			empleado.setTipoEmpleadoList(attachedTipoEmpleadoList);
			List<Asistencia> attachedAsistenciaList = new ArrayList<Asistencia>();
			for (Asistencia asistenciaListAsistenciaToAttach : empleado.getAsistenciaList()) {
				asistenciaListAsistenciaToAttach = em.getReference(asistenciaListAsistenciaToAttach.getClass(), asistenciaListAsistenciaToAttach.getIdAsistencia());
				attachedAsistenciaList.add(asistenciaListAsistenciaToAttach);
			}
			empleado.setAsistenciaList(attachedAsistenciaList);
			List<Horario> attachedHorarioList = new ArrayList<Horario>();
			for (Horario horarioListHorarioToAttach : empleado.getHorarioList()) {
				horarioListHorarioToAttach = em.getReference(horarioListHorarioToAttach.getClass(), horarioListHorarioToAttach.getIdHorario());
				attachedHorarioList.add(horarioListHorarioToAttach);
			}
			empleado.setHorarioList(attachedHorarioList);
			em.persist(empleado);
			for (TipoEmpleado tipoEmpleadoListTipoEmpleado : empleado.getTipoEmpleadoList()) {
				Empleado oldIdEmpleadoOfTipoEmpleadoListTipoEmpleado = tipoEmpleadoListTipoEmpleado.getIdEmpleado();
				tipoEmpleadoListTipoEmpleado.setIdEmpleado(empleado);
				tipoEmpleadoListTipoEmpleado = em.merge(tipoEmpleadoListTipoEmpleado);
				if (oldIdEmpleadoOfTipoEmpleadoListTipoEmpleado != null) {
					oldIdEmpleadoOfTipoEmpleadoListTipoEmpleado.getTipoEmpleadoList().remove(tipoEmpleadoListTipoEmpleado);
					oldIdEmpleadoOfTipoEmpleadoListTipoEmpleado = em.merge(oldIdEmpleadoOfTipoEmpleadoListTipoEmpleado);
				}
			}
			for (Asistencia asistenciaListAsistencia : empleado.getAsistenciaList()) {
				Empleado oldIdEmpleadoOfAsistenciaListAsistencia = asistenciaListAsistencia.getIdEmpleado();
				asistenciaListAsistencia.setIdEmpleado(empleado);
				asistenciaListAsistencia = em.merge(asistenciaListAsistencia);
				if (oldIdEmpleadoOfAsistenciaListAsistencia != null) {
					oldIdEmpleadoOfAsistenciaListAsistencia.getAsistenciaList().remove(asistenciaListAsistencia);
					oldIdEmpleadoOfAsistenciaListAsistencia = em.merge(oldIdEmpleadoOfAsistenciaListAsistencia);
				}
			}
			for (Horario horarioListHorario : empleado.getHorarioList()) {
				Empleado oldIdEmpleadoOfHorarioListHorario = horarioListHorario.getIdEmpleado();
				horarioListHorario.setIdEmpleado(empleado);
				horarioListHorario = em.merge(horarioListHorario);
				if (oldIdEmpleadoOfHorarioListHorario != null) {
					oldIdEmpleadoOfHorarioListHorario.getHorarioList().remove(horarioListHorario);
					oldIdEmpleadoOfHorarioListHorario = em.merge(oldIdEmpleadoOfHorarioListHorario);
				}
			}
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(Empleado empleado) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Empleado persistentEmpleado = em.find(Empleado.class, empleado.getIdEmpleado());
			List<TipoEmpleado> tipoEmpleadoListOld = persistentEmpleado.getTipoEmpleadoList();
			List<TipoEmpleado> tipoEmpleadoListNew = empleado.getTipoEmpleadoList();
			List<Asistencia> asistenciaListOld = persistentEmpleado.getAsistenciaList();
			List<Asistencia> asistenciaListNew = empleado.getAsistenciaList();
			List<Horario> horarioListOld = persistentEmpleado.getHorarioList();
			List<Horario> horarioListNew = empleado.getHorarioList();
			List<TipoEmpleado> attachedTipoEmpleadoListNew = new ArrayList<TipoEmpleado>();
			for (TipoEmpleado tipoEmpleadoListNewTipoEmpleadoToAttach : tipoEmpleadoListNew) {
				tipoEmpleadoListNewTipoEmpleadoToAttach = em.getReference(tipoEmpleadoListNewTipoEmpleadoToAttach.getClass(), tipoEmpleadoListNewTipoEmpleadoToAttach.getIdTipoEmpleado());
				attachedTipoEmpleadoListNew.add(tipoEmpleadoListNewTipoEmpleadoToAttach);
			}
			tipoEmpleadoListNew = attachedTipoEmpleadoListNew;
			empleado.setTipoEmpleadoList(tipoEmpleadoListNew);
			List<Asistencia> attachedAsistenciaListNew = new ArrayList<Asistencia>();
			for (Asistencia asistenciaListNewAsistenciaToAttach : asistenciaListNew) {
				asistenciaListNewAsistenciaToAttach = em.getReference(asistenciaListNewAsistenciaToAttach.getClass(), asistenciaListNewAsistenciaToAttach.getIdAsistencia());
				attachedAsistenciaListNew.add(asistenciaListNewAsistenciaToAttach);
			}
			asistenciaListNew = attachedAsistenciaListNew;
			empleado.setAsistenciaList(asistenciaListNew);
			List<Horario> attachedHorarioListNew = new ArrayList<Horario>();
			for (Horario horarioListNewHorarioToAttach : horarioListNew) {
				horarioListNewHorarioToAttach = em.getReference(horarioListNewHorarioToAttach.getClass(), horarioListNewHorarioToAttach.getIdHorario());
				attachedHorarioListNew.add(horarioListNewHorarioToAttach);
			}
			horarioListNew = attachedHorarioListNew;
			empleado.setHorarioList(horarioListNew);
			empleado = em.merge(empleado);
			for (TipoEmpleado tipoEmpleadoListOldTipoEmpleado : tipoEmpleadoListOld) {
				if (!tipoEmpleadoListNew.contains(tipoEmpleadoListOldTipoEmpleado)) {
					tipoEmpleadoListOldTipoEmpleado.setIdEmpleado(null);
					tipoEmpleadoListOldTipoEmpleado = em.merge(tipoEmpleadoListOldTipoEmpleado);
				}
			}
			for (TipoEmpleado tipoEmpleadoListNewTipoEmpleado : tipoEmpleadoListNew) {
				if (!tipoEmpleadoListOld.contains(tipoEmpleadoListNewTipoEmpleado)) {
					Empleado oldIdEmpleadoOfTipoEmpleadoListNewTipoEmpleado = tipoEmpleadoListNewTipoEmpleado.getIdEmpleado();
					tipoEmpleadoListNewTipoEmpleado.setIdEmpleado(empleado);
					tipoEmpleadoListNewTipoEmpleado = em.merge(tipoEmpleadoListNewTipoEmpleado);
					if (oldIdEmpleadoOfTipoEmpleadoListNewTipoEmpleado != null && !oldIdEmpleadoOfTipoEmpleadoListNewTipoEmpleado.equals(empleado)) {
						oldIdEmpleadoOfTipoEmpleadoListNewTipoEmpleado.getTipoEmpleadoList().remove(tipoEmpleadoListNewTipoEmpleado);
						oldIdEmpleadoOfTipoEmpleadoListNewTipoEmpleado = em.merge(oldIdEmpleadoOfTipoEmpleadoListNewTipoEmpleado);
					}
				}
			}
			for (Asistencia asistenciaListOldAsistencia : asistenciaListOld) {
				if (!asistenciaListNew.contains(asistenciaListOldAsistencia)) {
					asistenciaListOldAsistencia.setIdEmpleado(null);
					asistenciaListOldAsistencia = em.merge(asistenciaListOldAsistencia);
				}
			}
			for (Asistencia asistenciaListNewAsistencia : asistenciaListNew) {
				if (!asistenciaListOld.contains(asistenciaListNewAsistencia)) {
					Empleado oldIdEmpleadoOfAsistenciaListNewAsistencia = asistenciaListNewAsistencia.getIdEmpleado();
					asistenciaListNewAsistencia.setIdEmpleado(empleado);
					asistenciaListNewAsistencia = em.merge(asistenciaListNewAsistencia);
					if (oldIdEmpleadoOfAsistenciaListNewAsistencia != null && !oldIdEmpleadoOfAsistenciaListNewAsistencia.equals(empleado)) {
						oldIdEmpleadoOfAsistenciaListNewAsistencia.getAsistenciaList().remove(asistenciaListNewAsistencia);
						oldIdEmpleadoOfAsistenciaListNewAsistencia = em.merge(oldIdEmpleadoOfAsistenciaListNewAsistencia);
					}
				}
			}
			for (Horario horarioListOldHorario : horarioListOld) {
				if (!horarioListNew.contains(horarioListOldHorario)) {
					horarioListOldHorario.setIdEmpleado(null);
					horarioListOldHorario = em.merge(horarioListOldHorario);
				}
			}
			for (Horario horarioListNewHorario : horarioListNew) {
				if (!horarioListOld.contains(horarioListNewHorario)) {
					Empleado oldIdEmpleadoOfHorarioListNewHorario = horarioListNewHorario.getIdEmpleado();
					horarioListNewHorario.setIdEmpleado(empleado);
					horarioListNewHorario = em.merge(horarioListNewHorario);
					if (oldIdEmpleadoOfHorarioListNewHorario != null && !oldIdEmpleadoOfHorarioListNewHorario.equals(empleado)) {
						oldIdEmpleadoOfHorarioListNewHorario.getHorarioList().remove(horarioListNewHorario);
						oldIdEmpleadoOfHorarioListNewHorario = em.merge(oldIdEmpleadoOfHorarioListNewHorario);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Integer id = empleado.getIdEmpleado();
				if (findEmpleado(id) == null) {
					throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.");
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
			Empleado empleado;
			try {
				empleado = em.getReference(Empleado.class, id);
				empleado.getIdEmpleado();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.", enfe);
			}
			List<TipoEmpleado> tipoEmpleadoList = empleado.getTipoEmpleadoList();
			for (TipoEmpleado tipoEmpleadoListTipoEmpleado : tipoEmpleadoList) {
				tipoEmpleadoListTipoEmpleado.setIdEmpleado(null);
				tipoEmpleadoListTipoEmpleado = em.merge(tipoEmpleadoListTipoEmpleado);
			}
			List<Asistencia> asistenciaList = empleado.getAsistenciaList();
			for (Asistencia asistenciaListAsistencia : asistenciaList) {
				asistenciaListAsistencia.setIdEmpleado(null);
				asistenciaListAsistencia = em.merge(asistenciaListAsistencia);
			}
			List<Horario> horarioList = empleado.getHorarioList();
			for (Horario horarioListHorario : horarioList) {
				horarioListHorario.setIdEmpleado(null);
				horarioListHorario = em.merge(horarioListHorario);
			}
			em.remove(empleado);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<Empleado> findEmpleadoEntities() {
		return findEmpleadoEntities(true, -1, -1);
	}

	public List<Empleado> findEmpleadoEntities(int maxResults, int firstResult) {
		return findEmpleadoEntities(false, maxResults, firstResult);
	}

	private List<Empleado> findEmpleadoEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Empleado.class));
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

	public Empleado findEmpleado(Integer id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Empleado.class, id);
		} finally {
			em.close();
		}
	}

	public int getEmpleadoCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Empleado> rt = cq.from(Empleado.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}
	
}
