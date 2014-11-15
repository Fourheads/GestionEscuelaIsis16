/*
 * This is a software made for highschool management 
 * 
 * Copyright (C) 2014, Fourheads
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * 
 * 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */

package dom.simple;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.NotContributed;
import org.apache.isis.applib.annotation.NotContributed.As;
import org.apache.isis.applib.query.QueryDefault;

import dom.horario.HorarioCurso;
import dom.horario.HorarioCursoRepositorio;
import dom.horario.HorarioCursoService;
import dom.horario.HorarioCursoView;
import dom.libroDiario.LibroDiario;
import dom.libroDiario.LibroDiarioRepositorio;
import dom.planEstudio.Anio;
import dom.planEstudio.AnioRepositorio;
import dom.planEstudio.Materia;
import dom.planEstudio.Plan;
import dom.planEstudio.PlanRepositorio;
import dom.simple.Funcion.E_funciones;

@Named("Cursos")
@DomainService(repositoryFor = Curso.class, menuOrder = "20")
public class CursoRepositorio {

	@MemberOrder(sequence = "1")
	@NotContributed
	public Curso crearCurso(Plan plan, Anio anio,
			@Named("División") String division, @Named("Turno") Turno turno) {

		Curso curso = container.newTransientInstance(Curso.class);
		curso.setAnio(anio);
		curso.setDivision(division);
		curso.setTurno(turno);
		curso.setHabilitado('S');

		for (Materia materia : anio.getMateriaList()) {

			materiaDelCursoRepositorio.crearMateriaDelCurso(curso, materia);
		}
		curso.setHorarioCurso(horarioCursoRepositorio.crearHorarioCurso(curso));

		container.persistIfNotAlready(curso);

		libroDiarioRepositorio.crearLibroDiario(curso);

		return curso;
	}

	public List<Plan> choices0CrearCurso() {

		return planRepositorio.queryListarPlanesAlfabeticamente();
	}

	public Plan default0CrearCurso() {
		if (choices0CrearCurso().isEmpty()) {
			return null;
		}

		return choices0CrearCurso().get(0);
	}

	public List<Anio> choices1CrearCurso(Plan plan) {
		return anioRepositorio.listarAniosDeUnPlan(plan);
	}

	public String validateCrearCurso(Plan plan, Anio anio,
			@Named("División") String division, @Named("Turno") Turno turno) {

		List<Curso> listadoCursos = container
				.allMatches(new QueryDefault<Curso>(Curso.class,
						"listarCursosDeUnAnio", "plan", plan.getDescripcion(),
						"anio", anio.getAnioNumero()));

		for (Curso curso : listadoCursos) {
			if (curso.getDivision().equals(division)) {
				return "Ya existe un curso con esa división en ese año";
			}
		}

		return null;
	}

	// {{ listarCursosDeUnPlan (action)
	@MemberOrder(sequence = "7")
	public List<Curso> listarCursosDeUnPlan(@Named("Plan") final Plan plan) {

		return filtroCu(container.allMatches(new QueryDefault<Curso>(Curso.class,
				"listarCursosDeUnPlan", "plan", plan.getDescripcion())),'S');
	}

	public List<Plan> choices0ListarCursosDeUnPlan() {

		return planRepositorio.queryListarPlanesAlfabeticamente();
	}

	public Plan default0ListarCursosDeUnPlan() {
		if (choices0ListarCursosDeUnPlan().isEmpty()) {
			return null;
		}

		return choices0ListarCursosDeUnPlan().get(0);
	}
	
	public List<Curso> listarCursoConAlumnos(){
		return filtroCu(container.allMatches(new QueryDefault<Curso>(Curso.class, "buscarCursoConAlumnos")),'S');
	}
	
	// {{ listarCursosDeUnAnio (action)
	@MemberOrder(sequence = "6")
	@NotContributed
	public List<Curso> listarCursosDeUnAnio(@Named("Plan") final Plan plan,
			@Named("Año") final Anio anio) {

		return filtroCu(container.allMatches(new QueryDefault<Curso>(Curso.class,
				"listarCursosDeUnAnio", "plan", plan.getDescripcion(), "anio",
				anio.getAnioNumero())),'S');
	}

	public List<Plan> choices0ListarCursosDeUnAnio() {

		return planRepositorio.queryListarPlanesAlfabeticamente();
	}

	public Plan default0ListarCursosDeUnAnio() {
		if (choices0ListarCursosDeUnAnio().isEmpty()) {
			return null;
		}

		return choices0ListarCursosDeUnAnio().get(0);
	}

	public SortedSet<Anio> choices1ListarCursosDeUnAnio(final Plan plan) {
		return plan.getAnioList();
	}

	// {{ SeleccionarUnCurso (action)
	@MemberOrder(sequence = "5")
	@NotContributed
	public Curso seleccionarUnCurso(Plan plan, Curso curso) {
		return curso;
	}

	public List<Plan> choices0SeleccionarUnCurso() {

		return planRepositorio.queryListarPlanesAlfabeticamente();
	}

	public Plan default0SeleccionarUnCurso() {
		if (choices0SeleccionarUnCurso().isEmpty()) {
			return null;
		}

		return choices0SeleccionarUnCurso().get(0);
	}

	public List<Curso> choices1SeleccionarUnCurso(Plan plan, Curso curso) {
		return filtroCu(container.allMatches(new QueryDefault<Curso>(Curso.class,
				"listarCursosDeUnPlan", "plan", plan.getDescripcion())),'S');
	}

	// }}

	// region > Asignar Preceptor a Curso (accion)
	// //////////////////////////////////////

	@MemberOrder(sequence = "3")
	@NotContributed
	public Curso asignarPreceptorAlCurso(@Named("Plan") final Plan plan,
			@Named("Curso") final Curso curso,
			@Named("Preceptor") final Personal preceptor) {

		curso.setPreceptor(preceptor);

		return curso;
	}

	public List<Plan> choices0AsignarPreceptorAlCurso() {

		return planRepositorio.queryListarPlanesAlfabeticamente();
	}

	public Plan default0AsignarPreceptorAlCurso() {
		if (choices0AsignarPreceptorAlCurso().isEmpty()) {
			return null;
		}
		return choices0AsignarPreceptorAlCurso().get(0);
	}

	public List<Curso> choices1AsignarPreceptorAlCurso(final Plan plan) {

		return cursoRepositorio.listarCursosDeUnPlan(plan);
	}

	public List<Personal> choices2AsignarPreceptorAlCurso() {
		return personalRepositorio
				.listarPersonalSegunFuncion(E_funciones.PRECEPTOR);
	}

	// endregion

	// region > Asignar profesor a materia (accion)
	// //////////////////////////////////////
	@Named("Asignar Profesor a Materia")
	@MemberOrder(sequence = "2")
	@NotContributed
	public Curso asignarProfesorAMateriaDelCurso(
			@Named("Plan") final Plan plan, @Named("Curso") final Curso curso,
			@Named("Materia") final MateriaDelCurso materia,
			@Named("Profesor") final Personal profesor) {

		materiaDelCursoRepositorio.asignarProfesorAMateriaDelCurso(materia,
				profesor);

		return materia.getCurso();
	}

	public List<Plan> choices0AsignarProfesorAMateriaDelCurso() {

		return planRepositorio.queryListarPlanesAlfabeticamente();
	}

	public Plan default0AsignarProfesorAMateriaDelCurso() {
		if (choices0AsignarProfesorAMateriaDelCurso().isEmpty()) {
			return null;
		}
		return choices0AsignarProfesorAMateriaDelCurso().get(0);
	}

	public List<Curso> choices1AsignarProfesorAMateriaDelCurso(final Plan plan) {

		return filtroCu(cursoRepositorio.listarCursosDeUnPlan(plan),'S');
	}

	public List<MateriaDelCurso> choices2AsignarProfesorAMateriaDelCurso(
			@Named("Plan") final Plan plan, @Named("Curso") final Curso curso) {
		if (curso != null) {
			return materiaDelCursoRepositorio
					.listarMateriaDelCursoParaUnCurso(curso);
		}
		return null;
	}

	public List<Personal> choices3AsignarProfesorAMateriaDelCurso() {
		return personalRepositorio
				.listarPersonalSegunFuncion(E_funciones.PROFESOR);
	}

	// endregion

	// {{ verHorarioDelCurso (action)
	@MemberOrder(sequence = "1.1")
	@NotContributed
	public HorarioCursoView verHorarioDelCurso(Plan plan, Curso curso) {
		return horarioCursoService.verHorarioDeCurso(curso);
	}

	public List<Plan> choices0VerHorarioDelCurso() {

		return planRepositorio.queryListarPlanesAlfabeticamente();
	}

	public Plan default0VerHorarioDelCurso() {
		if (choices0VerHorarioDelCurso().isEmpty()) {
			return null;
		}

		return choices0VerHorarioDelCurso().get(0);
	}

	public List<Curso> choices1VerHorarioDelCurso(Plan plan, Curso curso) {
		return filtroCu(container.allMatches(new QueryDefault<Curso>(Curso.class,
				"listarCursosDeUnPlan", "plan", plan.getDescripcion())),'S');
	}

	// }}

	@MemberOrder(sequence = "4")
	@NotContributed
	public String recuperarCurso(Plan plan, Curso curso)
	{
		curso.setHabilitado('S');
				
		String anio = String.valueOf(curso.getAnio().getAnioNumero());
		String division = curso.getDivision();
		return "El curso " + anio + "° '" + division + "' ha sido recuperado exitosamente";
	}
	
	@Named("Libro Diario")
	@MemberOrder(sequence = "5")
	public LibroDiarioRepositorio trarlibrodiariorepo()
	{
		return libroDiarioRepositorio;
	}
	
	// {{ eliminarCurso (action)
	@MemberOrder(sequence = "4.5")
	@NotContributed
	public String eliminarCurso(Plan plan, Curso curso,
			@Named("¿Esta Seguro?") Boolean seguro) {

		curso.setHabilitado('N');
		
		curso.setPreceptor(null);
		curso.setHorarioCurso(null);
		
		for(Alumno al:curso.getAlumnos())
		{
			al.setCurso(null);
		}
		
		for(MateriaDelCurso matecurso:curso.getMateriaDelCursoList())
		{
			matecurso.setProfesor(null);
			//matecurso.setCurso(null);
		}
		
		curso.setAlumnos(null);

		
		String anio = String.valueOf(curso.getAnio().getAnioNumero());
		String division = curso.getDivision();
		return "El curso " + anio + "° '" + division + "' ha sido eliminado";
	}


	public List<Plan> choices0EliminarCurso() {

		return planRepositorio.queryListarPlanesAlfabeticamente();
	}

	
	
	public Plan default0EliminarCurso() {
		if (choices0EliminarCurso().isEmpty()) {
			return null;
		}

		return choices0EliminarCurso().get(0);
	}

	public List<Curso> choices1EliminarCurso(Plan plan, Curso curso) {
		return filtroCu(container.allMatches(new QueryDefault<Curso>(Curso.class,
				"listarCursosDeUnPlan", "plan", plan.getDescripcion())),'S');
	}

	/*
	public String validateEliminarCurso(Plan plan, Curso curso, Boolean seguro) {
		if (!seguro) {
			return "Marque en la opcion si está seguro!!! Si no lo está cancele esta opción";
		}

		return null;
	} 
	*/
	
	private List<Curso> filtroCu(List<Curso> Cursos, char A)
	{
		List<Curso> filtroCu=new ArrayList<Curso>();
		
		for(Curso cu:Cursos)
		{
			if(cu.getHabilitado()==A)
				filtroCu.add(cu);
		}
		
		return filtroCu;
	}
	
	// }}

	// region > injected services
	// //////////////////////////////////////

	@javax.inject.Inject
	DomainObjectContainer container;
	@javax.inject.Inject
	PlanRepositorio planRepositorio;
	@javax.inject.Inject
	MateriaDelCursoRepositorio materiaDelCursoRepositorio;
	@javax.inject.Inject
	CursoRepositorio cursoRepositorio;
	@javax.inject.Inject
	PersonalRepositorio personalRepositorio;
	@javax.inject.Inject
	HorarioCursoRepositorio horarioCursoRepositorio;
	@javax.inject.Inject
	AnioRepositorio anioRepositorio;
	@javax.inject.Inject
	HorarioCursoService horarioCursoService;
	@javax.inject.Inject
	LibroDiarioRepositorio libroDiarioRepositorio;
	// endregion

}
