package dom.simple;

import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.query.QueryDefault;

import dom.planEstudio.Anio;
import dom.planEstudio.Materia;
import dom.planEstudio.Plan;
import dom.planEstudio.PlanRepositorio;

@Named("Cursos")
@DomainService(repositoryFor = Curso.class, menuOrder = "20")
public class CursoRepositorio {

	@MemberOrder(sequence = "1")
	public Curso crearCurso(Plan plan, Anio anio,
			@Named("División") String division, @Named("Turno") Turno turno) {

		Curso curso = container.newTransientInstance(Curso.class);
		curso.setAnio(anio);
		curso.setDivision(division);
		curso.setTurno(turno);

		for (Materia materia : anio.getMateriaList()) {

			materiaDelCursoRepositorio.crearMateriaDelCurso(curso, materia);

		}

		container.persistIfNotAlready(curso);
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

	// {{ listarCursosDeUnPlan (action)
	@MemberOrder(sequence = "2")
	public List<Curso> listarCursosDeUnPlan(@Named("Plan") final Plan plan) {

		return container.allMatches(new QueryDefault<Curso>(Curso.class,
				"listarCursosDeUnPlan", "plan", plan.getDescripcion()));
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

	// {{ listarCursosDeUnAnio (action)
	@MemberOrder(sequence = "2.5")
	public List<Curso> listarCursosDeUnAnio(@Named("Plan") final Plan plan,
			@Named("Año") final Anio anio) {

		return container.allMatches(new QueryDefault<Curso>(Curso.class,
				"listarCursosDeUnAnio", "plan", plan.getDescripcion(), "anio",
				anio.getAnioNumero()));
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

	public List<Anio> choices1ListarCursosDeUnAnio(final Plan plan) {
		return plan.getAnioList();
	}

	// {{ SeleccionarUnCurso (action)
	@MemberOrder(sequence = "1")
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
	// }}
	

	// region > Asignar profesor a materia (accion)
	// //////////////////////////////////////
	@Named("Asignar Profesor a Materia")
	@MemberOrder(sequence = "4")
	public Curso asignarProfesorAMateriaDelCurso(
			@Named("Plan") final Plan plan, @Named("Curso") final Curso curso,
			@Named("Materia") final MateriaDelCurso materia,
			@Named("Profesor") final Personal profesor) {

		materiaDelCursoRepositorio.asignarProfesorAMateriaDelCurso(materia,
				profesor);

		return materia.getCurso();
	}
	
	public List<Plan> choices0AsignarProfesorAMateriaDelCurso(){
		
		return planRepositorio.queryListarPlanesAlfabeticamente();
	}
	
	public Plan default0AsignarProfesorAMateriaDelCurso(){
		if (choices0AsignarProfesorAMateriaDelCurso().isEmpty()){
			return null;
		}
		return choices0AsignarProfesorAMateriaDelCurso().get(0);
	}
	
	public List<Curso> choices1AsignarProfesorAMateriaDelCurso(final Plan plan){
		
		return cursoRepositorio.listarCursosDeUnPlan(plan);
	}
	
	

	// endregion
	
	

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

	// endregion

}
