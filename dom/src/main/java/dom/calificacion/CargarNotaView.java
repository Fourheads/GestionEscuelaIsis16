package dom.calificacion;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.Join;

import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Disabled;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.annotation.Render.Type;
import org.apache.isis.applib.query.QueryDefault;

import dom.simple.Alumno;
import dom.simple.AlumnoRepositorio;
import dom.simple.MateriaDelCurso;
import dom.simple.MateriaDelCursoRepositorio;

@Named("Cargar Notas View")
@Bookmarkable
@MemberGroupLayout(columnSpans = { 5, 0, 0, 7 })
public class CargarNotaView extends AbstractViewModel{
	
	@Hidden
	public String getId() {
		return viewModelMemento();
	}

	private String title;

	// region > identification in the UI
	public String title() {
		return title;
	}

	public String iconName() {
		return "SimpleObject";
	}

	// endregion

	// region > ViewModel contract
	private String memento;

		
	@Override
	public void viewModelInit(String memento) {
		this.memento = memento;
		
		String[] parametros = memento.split(",");
		////titulo, periodo, anio, division, materia, alumnoIndex
		this.title = parametros[0];
		setPeriodo(parametros[1]);
		setAnio(parametros[2]);
		setDivision(parametros[3]);
		setMateria(parametros[4]);
		setPlanCurso(parametros[5]);
		setAlumnoIndice(Integer.valueOf(parametros[6]));
		
		try{
			inicializarListAlumnos();
		}catch(Exception e) {
			e.getMessage();
		}
		
		//inicializarAlumnoActivo();
	}

	@Override
	public String viewModelMemento() {
		return memento;
	}
	
	@Programmatic
	public void inicializarListAlumnos(){
		
		List<Alumno> listAlumno = alumnoRepositorio.queryListarAlumnosDeUnCurso(Integer.valueOf(anio), division);
		
		//AGREGAR PLAN A MEMENTO
		
		
		/*for(Alumno a: listAlumno){
			MateriaCalificacion newMatCalificacion = new MateriaCalificacion();
						
			newMatCalificacion.setAlumno(a);
			newMatCalificacion.setMateria(newMatCurso);
			setAlumnoActivo(newMatCalificacion);
			getMateriasCalificacion().add(newMatCalificacion);
			//SEGUIR ACÁ ********************************************************************
		}*/
		
	}
	
	@Programmatic
	public void inicializarAlumnoActivo(){
		setAlumnoActivo(getMateriasCalificacion().get(getAlumnoIndice()));		
	}
	
	// {{ PlanCurso (property)
	private String plan;

	@MemberOrder(sequence = "1")
	public String getPlanCurso() {
		return plan;
	}

	public void setPlanCurso(final String plan) {
		this.plan = plan;
	}
	// }}


	
	// {{ Periodo (property)
	private String periodo;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(final String periodo) {
		this.periodo = periodo;
	}
	// }}

	// {{ Anio (property)
	private String anio;

	@MemberOrder(sequence = "1.2")
	@Column(allowsNull = "true")
	@Named("Año")
	public String getAnio() {
		return anio;
	}

	public void setAnio(final String anio) {
		this.anio = anio;
	}
	// }}
	
	// {{ Division (property)
	private String division;
	
	@MemberOrder(sequence = "1.3")
	@Column(allowsNull = "true")
	public String getDivision() {
		return division;
	}

	public void setDivision(final String division) {
		this.division = division;
	}
	// }}

	// {{ AlumnoIndice (property)
	private int alumnoIndice;

	@Hidden
	@MemberOrder(sequence = "1.4")
	@Column(allowsNull = "true")
	public int getAlumnoIndice() {
		return alumnoIndice;
	}

	public void setAlumnoIndice(final int alumnoIndice) {
		this.alumnoIndice = alumnoIndice;
	}
	// }}
	
	// {{ Materia (property)
	private String materia;

	@MemberOrder(sequence = "1.5")
	@Column(allowsNull = "true")
	public String getMateria() {
		return materia;
	}

	public void setMateria(final String materia) {
		this.materia = materia;
	}
	// }}
	
	// {{ AlumnoActivo (property)
	private MateriaCalificacion alumnoActivo;

	@Disabled
	@MemberOrder(sequence = "1.6")
	@Column(allowsNull = "false")
	public MateriaCalificacion getAlumnoActivo() {
		return alumnoActivo;
	}

	public void setAlumnoActivo(final MateriaCalificacion alumnoAtivo) {
		this.alumnoActivo = alumnoAtivo;
	}
	// }}

	// {{ MateriasCalificacion (property)
	@Join
	@Element(dependent = "false")
	private List<MateriaCalificacion> materiasCalificacion = new ArrayList<MateriaCalificacion>();

	@Disabled(where = Where.EVERYWHERE)
	@Render(Type.EAGERLY)
	@MemberOrder(sequence = "1.7")
	public List<MateriaCalificacion> getMateriasCalificacion() {
		return materiasCalificacion;
	}

	public void setMateriasCalificacion(final List<MateriaCalificacion> materiasCalificacion) {
		this.materiasCalificacion = materiasCalificacion;
	}
	// }}


	@javax.inject.Inject
	private DomainObjectContainer container;
	
	@javax.inject.Inject
	private AlumnoRepositorio alumnoRepositorio;
	
	@javax.inject.Inject
	private MateriaDelCursoRepositorio matCursoRepositorio;
	
	@javax.inject.Inject
	private dom.calificacion.MateriaCalificacionRepositorio matCalificacion;
}
