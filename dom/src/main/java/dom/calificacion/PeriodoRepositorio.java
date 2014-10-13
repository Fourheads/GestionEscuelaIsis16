package dom.calificacion;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.Named;
import org.joda.time.LocalDate;

import dom.simple.Alumno;
import dom.simple.AlumnoRepositorio;
import dom.simple.MateriaDelCurso;
import dom.simple.MateriaDelCursoRepositorio;

@Hidden
@DomainService
public class PeriodoRepositorio {

	@Named("Nuevo Periodo")
	public Calificaciones createPeriodo(
			final @Named("CicloLectivo: ") Calificaciones inCalificaciones,
			final @Named("Nombre: ") String inNombre,
			final @Named("Inicio: ") LocalDate inFechaI,
			final @Named("Cierre: ") LocalDate inFechaF) {

		final Periodo newPeriodo = new Periodo();
		List<Alumno> alumnosTodos = alumnoRepositorio.listAll();
		List<AlumnoCalificacion> alumnosCalificacion = new ArrayList<AlumnoCalificacion>();
		
		newPeriodo.setNombre(inNombre.toUpperCase());
		newPeriodo.setFechaInicio(inFechaI);
		newPeriodo.setFechaFinal(inFechaF);
		newPeriodo.setCalificaciones(inCalificaciones);
				
		for(Alumno a: alumnosTodos){
			if(!(a.getCurso() == null)){
				AlumnoCalificacion newAlumnoCal = new AlumnoCalificacion();			
				List<MateriaCalificacion> materiasCalificacion = new ArrayList<MateriaCalificacion>();
				
				List<MateriaDelCurso> materiasDelAlumno = matDelCursoRepositorio.listarMateriaDelCursoParaUnCurso(a.getCurso());
				
				for(MateriaDelCurso mdc: materiasDelAlumno){
					MateriaCalificacion unaMateriaCalificacion  = new MateriaCalificacion();
					
					unaMateriaCalificacion.setAlumno(a);
					unaMateriaCalificacion.setMateriaDelCurso(mdc);
					
					materiasCalificacion.add(unaMateriaCalificacion);
				}
				newAlumnoCal.setAlumno(a);
				newAlumnoCal.setListMateriaCalificacion(materiasCalificacion);
				newAlumnoCal.setPeriodo(newPeriodo);
				
				alumnosCalificacion.add(newAlumnoCal);
			}
		}
		
		//newPeriodo.setAlumnoCalificaciones(alumnosCalificacion);		
		inCalificaciones.getPeriodos().add(newPeriodo);
		
		container.persistIfNotAlready(newPeriodo);
		
		for(AlumnoCalificacion ac: alumnosCalificacion){
			container.persistIfNotAlready(ac);
		}
		
		return inCalificaciones;
	}
	
	 @javax.inject.Inject
	 DomainObjectContainer container;
	 
	 @javax.inject.Inject
	 AlumnoRepositorio alumnoRepositorio;
	 
	 @javax.inject.Inject
	 MateriaCalificacionRepositorio materiaCalifRepositorio;
	 
	 @javax.inject.Inject
	 MateriaDelCursoRepositorio matDelCursoRepositorio;
	
}
