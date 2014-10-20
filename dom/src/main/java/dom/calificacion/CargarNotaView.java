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
import org.apache.isis.applib.annotation.MultiLine;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.PublishedAction;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.annotation.Render.Type;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;

import dom.asistencia.TomarAsistenciaView;
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
		
		Memento newMemento = mementoService.parse(memento);
		
		////titulo, periodo, anio, division, materia, plan, alumnoIndex
		this.title = newMemento.get("titulo", String.class);
		setPeriodo(newMemento.get("periodo", String.class));
		setAnio(newMemento.get("anio", Integer.class));
		setDivision(newMemento.get("division", String.class));
		setMateria(newMemento.get("materia", String.class));
		setPlanCurso(newMemento.get("plan", String.class));
		setAlumnoIndice(newMemento.get("indiceAlumno", Integer.class));
		
		try{
			inicializarListAlumnos();
		}catch(Exception e) {
			e.getMessage();
		}
		
		inicializarAlumnoActivo();
		
	}

	@Override
	public String viewModelMemento() {
		return memento;
	}
	
	@Programmatic
	public void inicializarListAlumnos(){
		//setMateriasCalificacion(matCalificacion.listMateriaCalificacionPorCursoPorMateria(anio, division, plan, materia));
		
		setMateriasCalificacion(matCalificacion.listPorCursoPorMateriaPorPeriodo(anio, plan, division, materia, periodo));
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
	private int anio;

	@MemberOrder(sequence = "1.2")
	@Column(allowsNull = "true")
	@Named("Año")
	public int getAnio() {
		return anio;
	}

	public void setAnio(final int anio) {
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
	///////////////////////////////////////////////////////////// }}
	
	// {{ Nota (property)
	private int nota;
	
	@Hidden
	@Column(allowsNull = "false")
	@MemberOrder(sequence = "1.8")
	public int getNota() {
		return nota;
	}

	public void setNota(final int nota) {
		this.nota = nota;
	}
	// }}

	// {{ Observacion (property)
	private String observacion;
	@Hidden
	@Column(allowsNull = "false")
	@MemberOrder(sequence = "1.9")
	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(final String observacion) {
		this.observacion = observacion;
	}
	///////////////////////////////////////////////////////////// }}

	@Named("Alumno Anterior")
	@MemberOrder(sequence = "2", name = "alumnoActivo")
	//@PublishedAction
	public CargarNotaView pasarAlAlumnoAnterior() {
					
		return generarNuevoViewModel(alumnoIndiceAnterior());
	}
	
	@MemberOrder(sequence = "5", name = "alumnoActivo")
	@Named("Calificar")
	public CargarNotaView calificar(final @Named("Nota: ") int inNota, final @MultiLine @Optional @Named("Observacion: ") String inObservacion){
		this.setNota(inNota);
		this.setObservacion(inObservacion);
		
		
		getAlumnoActivo().setNota(inNota);
		getAlumnoActivo().setObservacion(inObservacion.toUpperCase());
		
		return generarNuevoViewModel(alumnoIndiceSiguiente());
	}
	
	@Programmatic
	private CargarNotaView generarNuevoViewModel(int nuevoIndice) {
		
		Memento memento = mementoService.create();

		memento.set("titulo", "Cargar Notas");
		memento.set("periodo", getPeriodo());
		memento.set("anio", getAnio());
		memento.set("division", getDivision());
		memento.set("materia", getMateria());
		memento.set("plan", getPlanCurso());
		memento.set("indiceAlumno", nuevoIndice);

		return container.newViewModelInstance(CargarNotaView.class,
				memento.asString());
	}
	
	@Programmatic
	public int alumnoIndiceSiguiente() {
		int nuevoIndice = getAlumnoIndice() + 1;

		if (nuevoIndice == getMateriasCalificacion().size()) {
			nuevoIndice = 0;
		}
		return nuevoIndice;
	}
	@Programmatic
	public int alumnoIndiceAnterior() {
		int nuevoIndice = getAlumnoIndice() - 1;

		if (nuevoIndice == -1) {
			nuevoIndice = getMateriasCalificacion().size() -1;
		}
		return nuevoIndice;
	}
	
	@javax.inject.Inject
	private DomainObjectContainer container;
		
	@javax.inject.Inject
	private dom.calificacion.MateriaCalificacionRepositorio matCalificacion;
	
	@javax.inject.Inject
    MementoService mementoService;
	
	@javax.inject.Inject
	AlumnoCalificacionRepositorio aluCalRepositorio;
}
