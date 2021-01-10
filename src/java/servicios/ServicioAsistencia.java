/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.util.Date;
import java.util.List;
import javax.jws.Oneway;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import modelo.entidades.Asistencia;
import modelo.entidades.Aula;
import modelo.entidades.Empleado;
import modelo.entidades.Estado;
import modelo.entidades.FechaHabil;
import modelo.entidades.Horario;
import modelo.entidades.Jornada;
import modelo.entidades.Materia;
import modelo.entidades.Tipo;
import modelo.entidades.TipoEmpleado;
import modelo.entidades.ViewNombreCompleto;
import modelo.entidades.VistaDocente;
import modelo.entidades.VistaJornada;
import modelo.facadeAsistencia;
import modelo.facadeAulas;
import modelo.facadeEmpleados;
import modelo.facadeEstados;
import modelo.facadeFechasHabiles;
import modelo.facadeHorario;
import modelo.facadeJornada;
import modelo.facadeMateria;
import modelo.facadeTipoEmpleado;
import modelo.facadeTipos;
import modelo.facadeViewDocente;
import modelo.facadeViewJornada;
import modelo.facadeViewNombreCompleto;
import vista.interfaces.IAsistencia;
import vista.interfaces.IAulas;
import vista.interfaces.IEmpleados;
import vista.interfaces.IEstados;
import vista.interfaces.IFechasHabiles;
import vista.interfaces.IHorario;
import vista.interfaces.IJornada;
import vista.interfaces.IMateria;
import vista.interfaces.ITipoEmpleado;
import vista.interfaces.ITipos;
import vista.interfaces.IViewDocente;
import vista.interfaces.IViewJornada;
import vista.interfaces.IViewNombreCompleto;

/**
 *
 * @author mjavi
 */
@WebService(serviceName = "ServicioAsistencia")
public class ServicioAsistencia implements IAsistencia, IAulas, IEmpleados, IEstados, IFechasHabiles, IHorario, IJornada, IMateria, ITipoEmpleado, ITipos , IViewNombreCompleto, IViewDocente, IViewJornada{

//############################################# ASISTENCIA ##################################
	@Override
	@WebMethod(operationName = "getListAsistencia")
	public List<Asistencia> getListAsistencia() {
		facadeAsistencia fac = new facadeAsistencia();
		return fac.getListAsistencia();
	}

	@Override
	@WebMethod(operationName = "getAsistencia")
	public Asistencia getAsistencia(@WebParam(name = "id") int id) {
		facadeAsistencia fac = new facadeAsistencia();
		return fac.getAsistencia(id);
	}

	@Override
	@WebMethod(operationName = "deleteAsistencia")
	public boolean deleteAsistencia(@WebParam(name = "id") int id) {
		facadeAsistencia fac = new facadeAsistencia();
		return fac.deleteAsistencia(id);
	}

	@Override
	@WebMethod(operationName = "saveAsistencia")
	@Oneway
	public void saveAsistencia(
		@WebParam(name = "tipoEmpleado") TipoEmpleado tipoEmpleado,
		@WebParam(name = "empleado") Empleado empleado,
		@WebParam(name = "mensajeAsistencia") String mensajeAsistencia,
		@WebParam(name = "fechaHabil") FechaHabil fechaHabil,
		@WebParam(name = "estado") Estado estado,
		@WebParam(name = "comentarios") String comentarios,
		@WebParam(name = "observaciones") String observaciones) {
		facadeAsistencia fac = new facadeAsistencia();
		fac.saveAsistencia(tipoEmpleado, empleado, mensajeAsistencia, fechaHabil, estado, comentarios, observaciones);
	}

	@Override
	@WebMethod(operationName = "updateAsistencia")
	public boolean updateAsistencia(
		@WebParam(name = "id") int id,
		@WebParam(name = "tipoEmpleado") TipoEmpleado tipoEmpleado,
		@WebParam(name = "empleado") Empleado empleado,
		@WebParam(name = "mensajeAsistencia") String mensajeAsistencia,
		@WebParam(name = "fechaHabil") FechaHabil fechaHabil,
		@WebParam(name = "estado") Estado estado,
		@WebParam(name = "comentarios") String comentarios,
		@WebParam(name = "observaciones") String observaciones) {
		facadeAsistencia fac = new facadeAsistencia();
		return fac.updateAsistencia(id, tipoEmpleado, empleado, mensajeAsistencia, fechaHabil, estado, comentarios, observaciones);
	}

//##################################################################### AULAS ###########################################################
	@Override
	@WebMethod(operationName = "getListAulas")
	public List<Aula> getListAulas() {
		facadeAulas fac = new facadeAulas();
		return fac.getListAulas();
	}

	@Override
	@WebMethod(operationName = "getAula")
	public Aula getAula(@WebParam(name = "id") int id) {
		facadeAulas fac = new facadeAulas();
		return fac.getAula(id);
	}

	@Override
	@WebMethod(operationName = "deleteAula")
	public boolean deleteAula(@WebParam(name = "id") int id) {
		facadeAulas fac = new facadeAulas();
		return fac.deleteAula(id);
	}

	@Override
	@WebMethod(operationName = "saveAula")
	@Oneway
	public void saveAula(@WebParam(name = "descripcionAula") String descripcionAula) {
		facadeAulas fac = new facadeAulas();
		fac.saveAula(descripcionAula);
	}

	@Override
	@WebMethod(operationName = "updateAula")
	public boolean updateAula(
		@WebParam(name = "id") int id,
		@WebParam(name = "descripcionAula") String descripcionAula) {
		facadeAulas fac = new facadeAulas();
		return fac.updateAula(id, descripcionAula);
	}

//################################################################## EMPLEADOS ################################################
	@Override
	@WebMethod(operationName = "getListEmpleados")
	public List<Empleado> getListEmpleados() {
		facadeEmpleados fac = new facadeEmpleados();
		return fac.getListEmpleados();
	}

	@Override
	@WebMethod(operationName = "getEmpleado")
	public Empleado getEmpleado(@WebParam(name = "id") int id) {
		facadeEmpleados fac = new facadeEmpleados();
		return fac.getEmpleado(id);
	}

	@Override
	@WebMethod(operationName = "deleteEmpleado")
	public boolean deleteEmpleado(@WebParam(name = "id") int id) {
		facadeEmpleados fac = new facadeEmpleados();
		return fac.deleteEmpleado(id);
	}

	@Override
	@WebMethod(operationName = "saveEmpleado")
	@Oneway
	public void saveEmpleado(
		@WebParam(name = "cedula") String cedula,
		@WebParam(name = "nombres") String nombres,
		@WebParam(name = "Apellidos") String Apellidos,
		@WebParam(name = "usuario") String usuario,
		@WebParam(name = "funcion") String funcion,
		@WebParam(name = "clave") String clave) {
		facadeEmpleados fac = new facadeEmpleados();
		fac.saveEmpleado(cedula, nombres, Apellidos, usuario, funcion, clave);
	}

	@Override
	@WebMethod(operationName = "updateEmpleado")
	public boolean updateEmpleado(
		@WebParam(name = "id") int id,
		@WebParam(name = "cedula") String cedula,
		@WebParam(name = "nombres") String nombres,
		@WebParam(name = "Apellidos") String Apellidos,
		@WebParam(name = "usuario") String usuario,
		@WebParam(name = "funcion") String funcion,
		@WebParam(name = "clave") String clave) {
		facadeEmpleados fac = new facadeEmpleados();
		return fac.updateEmpleado(id, cedula, nombres, Apellidos, usuario, funcion, clave);
	}

//######################################################### ESTADOS ##############################################
	@Override
	@WebMethod(operationName = "getListEstados")
	public List<Estado> getListEstados() {
		facadeEstados fac = new facadeEstados();
		return fac.getListEstados();
	}

	@Override
	@WebMethod(operationName = "getEstado")
	public Estado getEstado(@WebParam(name = "id") int id) {
		facadeEstados fac = new facadeEstados();
		return fac.getEstado(id);
	}

	@Override
	@WebMethod(operationName = "deleteEstado")
	public boolean deleteEstado(@WebParam(name = "id") int id) {
		facadeEstados fac = new facadeEstados();
		return fac.deleteEstado(id);
	}

	@Override
	@WebMethod(operationName = "saveEstado")
	@Oneway
	public void saveEstado(@WebParam(name = "descripcionEstado") String descripcionEstado) {
		facadeEstados fac = new facadeEstados();
		fac.saveEstado(descripcionEstado);
	}

	@Override
	@WebMethod(operationName = "updateEstado")
	public boolean updateEstado(
		@WebParam(name = "id") int id,
		@WebParam(name = "descripcionEstado") String descripcionEstado) {
		facadeEstados fac = new facadeEstados();
		return fac.updateEstado(id, descripcionEstado);
	}

//############################################################### FECHAS HABILES ################################################
	@Override
	@WebMethod(operationName = "getListFechasHabiles")
	public List<FechaHabil> getListFechasHabiles() {
		facadeFechasHabiles fac = new facadeFechasHabiles();
		return fac.getListFechasHabiles();
	}

	@Override
	@WebMethod(operationName = "getFechaHabil")
	public FechaHabil getFechaHabil(@WebParam(name = "id") int id) {
		facadeFechasHabiles fac = new facadeFechasHabiles();
		return fac.getFechaHabil(id);
	}

	@Override
	@WebMethod(operationName = "deleteFechaHabil")
	public boolean deleteFechaHabil(@WebParam(name = "id") int id) {
		facadeFechasHabiles fac = new facadeFechasHabiles();
		return fac.deleteFechaHabil(id);
	}

	@Override
	@WebMethod(operationName = "saveFechaHabil")
	@Oneway
	public void saveFechaHabil(
		@WebParam(name = "tipo") Tipo tipo,
		@WebParam(name = "fecha") Date fecha) {
		facadeFechasHabiles fac = new facadeFechasHabiles();
		fac.saveFechaHabil(tipo, fecha);
	}

	@Override
	@WebMethod(operationName = "updateFechaHabil")
	public boolean updateFechaHabil(
		@WebParam(name = "id") int id,
		@WebParam(name = "tipo") Tipo tipo,
		@WebParam(name = "fecha") Date fecha) {
		facadeFechasHabiles fac = new facadeFechasHabiles();
		return fac.updateFechaHabil(id, tipo, fecha);
	}

//##################################################################### HORARIO ###################################################
	@Override
	@WebMethod(operationName = "getListHorario")
	public List<Horario> getListHorario() {
		facadeHorario fac = new facadeHorario();
		return fac.getListHorario();
	}

	@Override
	@WebMethod(operationName = "getHorario")
	public Horario getHorario(@WebParam(name = "id") int id) {
		facadeHorario fac = new facadeHorario();
		return fac.getHorario(id);
	}

	@Override
	@WebMethod(operationName = "deleteHorario")
	public boolean deleteHorario(@WebParam(name = "id") int id) {
		facadeHorario fac = new facadeHorario();
		return fac.deleteHorario(id);
	}

	@Override
	@WebMethod(operationName = "saveHorario")
	@Oneway
	public void saveHorario(
		@WebParam(name = "empleado") Empleado empleado,
		@WebParam(name = "aula") Aula aula,
		@WebParam(name = "materia") Materia materia,
		@WebParam(name = "horaInicio") Date horaInicio,
		@WebParam(name = "horaFin") Date horaFin) {
		facadeHorario fac = new facadeHorario();
		fac.saveHorario(empleado, aula, materia, horaInicio, horaFin);
	}

	@Override
	@WebMethod(operationName = "updateHorario")
	public boolean updateHorario(
		@WebParam(name = "id") int id,
		@WebParam(name = "empleado") Empleado empleado,
		@WebParam(name = "aula") Aula aula,
		@WebParam(name = "materia") Materia materia,
		@WebParam(name = "horaInicio") Date horaInicio,
		@WebParam(name = "horaFin") Date horaFin) {
		facadeHorario fac = new facadeHorario();
		return fac.updateHorario(id, empleado, aula, materia, horaInicio, horaFin);
	}

//################################################################ JORNADA ############################################
	@Override
	@WebMethod(operationName = "getListJornada")
	public List<Jornada> getListJornada() {
		facadeJornada fac = new facadeJornada();
		return fac.getListJornada();
	}

	@Override
	@WebMethod(operationName = "getJornada")
	public Jornada getJornada(@WebParam(name = "id") int id) {
		facadeJornada fac = new facadeJornada();
		return fac.getJornada(id);
	}

	@Override
	@WebMethod(operationName = "deleteJornada")
	public boolean deleteJornada(@WebParam(name = "id") int id) {
		facadeJornada fac = new facadeJornada();
		return fac.deleteJornada(id);
	}

	@Override
	@WebMethod(operationName = "saveJornada")
	@Oneway
	public void saveJornada(
		@WebParam(name = "entraPrimerPeriodo") Date entraPrimerPeriodo,
		@WebParam(name = "salePrimerPeriodo") Date salePrimerPeriodo,
		@WebParam(name = "entraSegundoPeriodo") Date entraSegundoPeriodo,
		@WebParam(name = "saleSegundoPeriodo") Date saleSegundoPeriodo) {
		facadeJornada fac = new facadeJornada();
		fac.saveJornada(entraPrimerPeriodo, salePrimerPeriodo, entraSegundoPeriodo, saleSegundoPeriodo);
	}

	@Override
	@WebMethod(operationName = "updateJornada")
	public boolean updateJornada(
		@WebParam(name = "id") int id,
		@WebParam(name = "entraPrimerPeriodo") Date entraPrimerPeriodo,
		@WebParam(name = "salePrimerPeriodo") Date salePrimerPeriodo,
		@WebParam(name = "entraSegundoPeriodo") Date entraSegundoPeriodo,
		@WebParam(name = "saleSegundoPeriodo") Date saleSegundoPeriodo) {
		facadeJornada fac = new facadeJornada();
		return fac.updateJornada(id, entraPrimerPeriodo, salePrimerPeriodo, entraSegundoPeriodo, saleSegundoPeriodo);
	}

//######################################################################## MATERIAS ################################################
	@Override
	@WebMethod(operationName = "getListMaterias")
	public List<Materia> getListMaterias() {
		facadeMateria fac = new facadeMateria();
		return fac.getListMaterias();
	}

	@Override
	@WebMethod(operationName = "getMateria")
	public Materia getMateria(@WebParam(name = "id") int id) {
		facadeMateria fac = new facadeMateria();
		return fac.getMateria(id);
	}

	@Override
	@WebMethod(operationName = "deleteMateria")
	public boolean deleteMateria(@WebParam(name = "id") int id) {
		facadeMateria fac = new facadeMateria();
		return fac.deleteMateria(id);
	}

	@Override
	@WebMethod(operationName = "saveMateria")
	@Oneway
	public void saveMateria(
		@WebParam(name = "descripcionMateria") String descripcionMateria) {
		facadeMateria fac = new facadeMateria();
		fac.saveMateria(descripcionMateria);
	}

	@Override
	@WebMethod(operationName = "updateMateria")
	public boolean updateMateria(
		@WebParam(name = "id") int id,
		@WebParam(name = "descripcionMateria") String descripcionMateria) {
		facadeMateria fac = new facadeMateria();
		return fac.updateMateria(id, descripcionMateria);
	}

//################################################################3 TIPOS EMPLEADOS #######################################################
	@Override
	@WebMethod(operationName = "getListTiposEmpleados")
	public List<TipoEmpleado> getListTiposEmpleados() {
		facadeTipoEmpleado fac = new facadeTipoEmpleado();
		return fac.getListTiposEmpleados();
	}

	@Override
	@WebMethod(operationName = "getTipoEmpleado")
	public TipoEmpleado getTipoEmpleado(@WebParam(name = "id") int id) {
		facadeTipoEmpleado fac = new facadeTipoEmpleado();
		return fac.getTipoEmpleado(id);
	}

	@Override
	@WebMethod(operationName = "deleteTipoEmpleado")
	public boolean deleteTipoEmpleado(@WebParam(name = "id") int id) {
		facadeTipoEmpleado fac = new facadeTipoEmpleado();
		return fac.deleteTipoEmpleado(id);
	}

	@Override
	@WebMethod(operationName = "saveTipoEmpleado")
	@Oneway
	public void saveTipoEmpleado(
		@WebParam(name = "empleado") Empleado empleado,
		@WebParam(name = "tipo") Tipo tipo,
		@WebParam(name = "jornada") Jornada jornada) {
		facadeTipoEmpleado fac = new facadeTipoEmpleado();
		fac.saveTipoEmpleado(empleado, tipo, jornada);
	}

	@Override
	@WebMethod(operationName = "updateTipoEmpleado")
	public boolean updateTipoEmpleado(
		@WebParam(name = "id") int id,
		@WebParam(name = "empleado") Empleado empleado,
		@WebParam(name = "tipo") Tipo tipo,
		@WebParam(name = "jornada") Jornada jornada) {
		facadeTipoEmpleado fac = new facadeTipoEmpleado();
		return fac.updateTipoEmpleado(id, empleado, tipo, jornada);
	}

//######################################################################33 TIPOS #######################################
	@Override
	@WebMethod(operationName = "getListTipos")
	public List<Tipo> getListTipos() {
		facadeTipos fac = new facadeTipos();
		return fac.getListTipos();
	}

	@Override
	@WebMethod(operationName = "getTipo")
	public Tipo getTipo(@WebParam(name = "id") int id) {
		facadeTipos fac = new facadeTipos();
		return fac.getTipo(id);
	}

	@Override
	@WebMethod(operationName = "deleteTipo")
	public boolean deleteTipo(@WebParam(name = "id") int id) {
		facadeTipos fac = new facadeTipos();
		return fac.deleteTipo(id);
	}

	@Override
	@WebMethod(operationName = "saveTipo")
	@Oneway
	public void saveTipo(
		@WebParam(name = "descripcionTipo") String descripcionTipo) {
		facadeTipos fac = new facadeTipos();
		fac.saveTipo(descripcionTipo);
	}

	@Override
	@WebMethod(operationName = "updateTipo")
	public boolean updateTipo(
		@WebParam(name = "id") int id,
		@WebParam(name = "descripcionTipo") String descripcionTipo) {
		facadeTipos fac = new facadeTipos();
		return fac.updateTipo(id, descripcionTipo);
	}

	
//################################################ VISTAS ######################################
	@Override
	public List<ViewNombreCompleto> getListNombresCompletos() {
		facadeViewNombreCompleto fac = new facadeViewNombreCompleto();
		return fac.getListNombresCompletos();
	}

	@Override
	public ViewNombreCompleto getNombreCompleto(int id) {
	facadeViewNombreCompleto fac = new facadeViewNombreCompleto();
		return fac.getNombreCompleto(id);
	}

	@Override
	public List<VistaDocente> getListVistaDocente() {
		facadeViewDocente fac = new facadeViewDocente();
		return fac.getListVistaDocente();
	}

	@Override
	public VistaDocente getVistaDocente(int id) {
		facadeViewDocente fac = new facadeViewDocente();
		return fac.getVistaDocente(id);
	}

	@Override
	public List<VistaJornada> getListVistaJornada() {
		facadeViewJornada fac = new facadeViewJornada();
		return fac.getListVistaJornada();
	}

	@Override
	public VistaJornada getVistaJornada(int id) {
		facadeViewJornada fac = new facadeViewJornada();
		return fac.getVistaJornada(id);
	}

}
