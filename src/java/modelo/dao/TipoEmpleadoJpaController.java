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
import modelo.entidades.Empleado;
import modelo.entidades.Jornada;
import modelo.entidades.Tipo;
import modelo.entidades.Asistencia;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.dao.exceptions.NonexistentEntityException;
import modelo.entidades.TipoEmpleado;

/**
 *
 * @author mjavi
 */
public class TipoEmpleadoJpaController implements Serializable {

	public TipoEmpleadoJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}
	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(TipoEmpleado tipoEmpleado) {
		if (tipoEmpleado.getAsistenciaList() == null) {
			tipoEmpleado.setAsistenciaList(new ArrayList<Asistencia>());
		}
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Empleado idEmpleado = tipoEmpleado.getIdEmpleado();
			if (idEmpleado != null) {
				idEmpleado = em.getReference(idEmpleado.getClass(), idEmpleado.getIdEmpleado());
				tipoEmpleado.setIdEmpleado(idEmpleado);
			}
			Jornada idJornada = tipoEmpleado.getIdJornada();
			if (idJornada != null) {
				idJornada = em.getReference(idJornada.getClass(), idJornada.getIdJornada());
				tipoEmpleado.setIdJornada(idJornada);
			}
			Tipo idTipo = tipoEmpleado.getIdTipo();
			if (idTipo != null) {
				idTipo = em.getReference(idTipo.getClass(), idTipo.getIdTipo());
				tipoEmpleado.setIdTipo(idTipo);
			}
			List<Asistencia> attachedAsistenciaList = new ArrayList<Asistencia>();
			for (Asistencia asistenciaListAsistenciaToAttach : tipoEmpleado.getAsistenciaList()) {
				asistenciaListAsistenciaToAttach = em.getReference(asistenciaListAsistenciaToAttach.getClass(), asistenciaListAsistenciaToAttach.getIdAsistencia());
				attachedAsistenciaList.add(asistenciaListAsistenciaToAttach);
			}
			tipoEmpleado.setAsistenciaList(attachedAsistenciaList);
			em.persist(tipoEmpleado);
			if (idEmpleado != null) {
				idEmpleado.getTipoEmpleadoList().add(tipoEmpleado);
				idEmpleado = em.merge(idEmpleado);
			}
			if (idJornada != null) {
				idJornada.getTipoEmpleadoList().add(tipoEmpleado);
				idJornada = em.merge(idJornada);
			}
			if (idTipo != null) {
				idTipo.getTipoEmpleadoList().add(tipoEmpleado);
				idTipo = em.merge(idTipo);
			}
			for (Asistencia asistenciaListAsistencia : tipoEmpleado.getAsistenciaList()) {
				TipoEmpleado oldIdTipoEmpleadoOfAsistenciaListAsistencia = asistenciaListAsistencia.getIdTipoEmpleado();
				asistenciaListAsistencia.setIdTipoEmpleado(tipoEmpleado);
				asistenciaListAsistencia = em.merge(asistenciaListAsistencia);
				if (oldIdTipoEmpleadoOfAsistenciaListAsistencia != null) {
					oldIdTipoEmpleadoOfAsistenciaListAsistencia.getAsistenciaList().remove(asistenciaListAsistencia);
					oldIdTipoEmpleadoOfAsistenciaListAsistencia = em.merge(oldIdTipoEmpleadoOfAsistenciaListAsistencia);
				}
			}
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(TipoEmpleado tipoEmpleado) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			TipoEmpleado persistentTipoEmpleado = em.find(TipoEmpleado.class, tipoEmpleado.getIdTipoEmpleado());
			Empleado idEmpleadoOld = persistentTipoEmpleado.getIdEmpleado();
			Empleado idEmpleadoNew = tipoEmpleado.getIdEmpleado();
			Jornada idJornadaOld = persistentTipoEmpleado.getIdJornada();
			Jornada idJornadaNew = tipoEmpleado.getIdJornada();
			Tipo idTipoOld = persistentTipoEmpleado.getIdTipo();
			Tipo idTipoNew = tipoEmpleado.getIdTipo();
			List<Asistencia> asistenciaListOld = persistentTipoEmpleado.getAsistenciaList();
			List<Asistencia> asistenciaListNew = tipoEmpleado.getAsistenciaList();
			if (idEmpleadoNew != null) {
				idEmpleadoNew = em.getReference(idEmpleadoNew.getClass(), idEmpleadoNew.getIdEmpleado());
				tipoEmpleado.setIdEmpleado(idEmpleadoNew);
			}
			if (idJornadaNew != null) {
				idJornadaNew = em.getReference(idJornadaNew.getClass(), idJornadaNew.getIdJornada());
				tipoEmpleado.setIdJornada(idJornadaNew);
			}
			if (idTipoNew != null) {
				idTipoNew = em.getReference(idTipoNew.getClass(), idTipoNew.getIdTipo());
				tipoEmpleado.setIdTipo(idTipoNew);
			}
			List<Asistencia> attachedAsistenciaListNew = new ArrayList<Asistencia>();
			for (Asistencia asistenciaListNewAsistenciaToAttach : asistenciaListNew) {
				asistenciaListNewAsistenciaToAttach = em.getReference(asistenciaListNewAsistenciaToAttach.getClass(), asistenciaListNewAsistenciaToAttach.getIdAsistencia());
				attachedAsistenciaListNew.add(asistenciaListNewAsistenciaToAttach);
			}
			asistenciaListNew = attachedAsistenciaListNew;
			tipoEmpleado.setAsistenciaList(asistenciaListNew);
			tipoEmpleado = em.merge(tipoEmpleado);
			if (idEmpleadoOld != null && !idEmpleadoOld.equals(idEmpleadoNew)) {
				idEmpleadoOld.getTipoEmpleadoList().remove(tipoEmpleado);
				idEmpleadoOld = em.merge(idEmpleadoOld);
			}
			if (idEmpleadoNew != null && !idEmpleadoNew.equals(idEmpleadoOld)) {
				idEmpleadoNew.getTipoEmpleadoList().add(tipoEmpleado);
				idEmpleadoNew = em.merge(idEmpleadoNew);
			}
			if (idJornadaOld != null && !idJornadaOld.equals(idJornadaNew)) {
				idJornadaOld.getTipoEmpleadoList().remove(tipoEmpleado);
				idJornadaOld = em.merge(idJornadaOld);
			}
			if (idJornadaNew != null && !idJornadaNew.equals(idJornadaOld)) {
				idJornadaNew.getTipoEmpleadoList().add(tipoEmpleado);
				idJornadaNew = em.merge(idJornadaNew);
			}
			if (idTipoOld != null && !idTipoOld.equals(idTipoNew)) {
				idTipoOld.getTipoEmpleadoList().remove(tipoEmpleado);
				idTipoOld = em.merge(idTipoOld);
			}
			if (idTipoNew != null && !idTipoNew.equals(idTipoOld)) {
				idTipoNew.getTipoEmpleadoList().add(tipoEmpleado);
				idTipoNew = em.merge(idTipoNew);
			}
			for (Asistencia asistenciaListOldAsistencia : asistenciaListOld) {
				if (!asistenciaListNew.contains(asistenciaListOldAsistencia)) {
					asistenciaListOldAsistencia.setIdTipoEmpleado(null);
					asistenciaListOldAsistencia = em.merge(asistenciaListOldAsistencia);
				}
			}
			for (Asistencia asistenciaListNewAsistencia : asistenciaListNew) {
				if (!asistenciaListOld.contains(asistenciaListNewAsistencia)) {
					TipoEmpleado oldIdTipoEmpleadoOfAsistenciaListNewAsistencia = asistenciaListNewAsistencia.getIdTipoEmpleado();
					asistenciaListNewAsistencia.setIdTipoEmpleado(tipoEmpleado);
					asistenciaListNewAsistencia = em.merge(asistenciaListNewAsistencia);
					if (oldIdTipoEmpleadoOfAsistenciaListNewAsistencia != null && !oldIdTipoEmpleadoOfAsistenciaListNewAsistencia.equals(tipoEmpleado)) {
						oldIdTipoEmpleadoOfAsistenciaListNewAsistencia.getAsistenciaList().remove(asistenciaListNewAsistencia);
						oldIdTipoEmpleadoOfAsistenciaListNewAsistencia = em.merge(oldIdTipoEmpleadoOfAsistenciaListNewAsistencia);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Integer id = tipoEmpleado.getIdTipoEmpleado();
				if (findTipoEmpleado(id) == null) {
					throw new NonexistentEntityException("The tipoEmpleado with id " + id + " no longer exists.");
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
			TipoEmpleado tipoEmpleado;
			try {
				tipoEmpleado = em.getReference(TipoEmpleado.class, id);
				tipoEmpleado.getIdTipoEmpleado();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The tipoEmpleado with id " + id + " no longer exists.", enfe);
			}
			Empleado idEmpleado = tipoEmpleado.getIdEmpleado();
			if (idEmpleado != null) {
				idEmpleado.getTipoEmpleadoList().remove(tipoEmpleado);
				idEmpleado = em.merge(idEmpleado);
			}
			Jornada idJornada = tipoEmpleado.getIdJornada();
			if (idJornada != null) {
				idJornada.getTipoEmpleadoList().remove(tipoEmpleado);
				idJornada = em.merge(idJornada);
			}
			Tipo idTipo = tipoEmpleado.getIdTipo();
			if (idTipo != null) {
				idTipo.getTipoEmpleadoList().remove(tipoEmpleado);
				idTipo = em.merge(idTipo);
			}
			List<Asistencia> asistenciaList = tipoEmpleado.getAsistenciaList();
			for (Asistencia asistenciaListAsistencia : asistenciaList) {
				asistenciaListAsistencia.setIdTipoEmpleado(null);
				asistenciaListAsistencia = em.merge(asistenciaListAsistencia);
			}
			em.remove(tipoEmpleado);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<TipoEmpleado> findTipoEmpleadoEntities() {
		return findTipoEmpleadoEntities(true, -1, -1);
	}

	public List<TipoEmpleado> findTipoEmpleadoEntities(int maxResults, int firstResult) {
		return findTipoEmpleadoEntities(false, maxResults, firstResult);
	}

	private List<TipoEmpleado> findTipoEmpleadoEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(TipoEmpleado.class));
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

	public TipoEmpleado findTipoEmpleado(Integer id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(TipoEmpleado.class, id);
		} finally {
			em.close();
		}
	}

	public int getTipoEmpleadoCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<TipoEmpleado> rt = cq.from(TipoEmpleado.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}
	
}
