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
				//if(aluCalRepositorio.alumnoCalificacionPorAlumnoPorPeriodo(a.getDni(), newPeriodo.getNombre()).isEmpty()){
					
					AlumnoCalificacion newAlumnoCal = new AlumnoCalificacion();			
					List<MateriaCalificacion> materiasCalificacion = new ArrayList<MateriaCalificacion>();
					
					List<MateriaDelCurso> materiasDelAlumno = matDelCursoRepositorio.listarMateriaDelCursoParaUnCurso(a.getCurso());
					
					for(MateriaDelCurso mdc: materiasDelAlumno){
						MateriaCalificacion unaMateriaCalificacion  = new MateriaCalificacion();
						
						unaMateriaCalificacion.setAlumno(a);
						unaMateriaCalificacion.setMateriaDelCurso(mdc);
						unaMateriaCalificacion.setAlumnoCalificacion(newAlumnoCal);
						
						materiasCalificacion.add(unaMateriaCalificacion);
					}
					newAlumnoCal.setAlumno(a);
					newAlumnoCal.setListMateriaCalificacion(materiasCalificacion);
					newAlumnoCal.setPeriodo(newPeriodo);
					
					alumnosCalificacion.add(newAlumnoCal);
				//}
			}
		}
		
		//newPeriodo.setAlumnoCalificaciones(alumnosCalificacion);		
		inCalificaciones.getPeriodos().add(newPeriodo);
		
		container.persistIfNotAlready(newPeriodo);
		
		if(!alumnosCalificacion.isEmpty()){
			for(AlumnoCalificacion ac: alumnosCalificacion){
				container.persistIfNotAlready(ac);
			}
		}
		return inCalificaciones;
	}
	
	public AlumnoCalificacion agregarAlumno(final @Named("Alumno: ") Alumno inAlumno,
											final @Named("Ciclo: ") Calificaciones inCalificacion,
											final @Named("Periodo: ") Periodo inPeriodo){
		final AlumnoCalificacion newAlCalificacion= new AlumnoCalificacion();
		List<MateriaCalificacion> listMC = new ArrayList<MateriaCalificacion>();
		
		newAlCalificacion.setAlumno(inAlumno);
		newAlCalificacion.setPeriodo(inPeriodo);
		
		for(MateriaDelCurso mc: inAlumno.getCurso().getMateriaDelCursoList()){
			MateriaCalificacion newMateriaCalificacion = new MateriaCalificacion();
			
			newMateriaCalificacion.setAlumno(inAlumno);
			newMateriaCalificacion.setAlumnoCalificacion(newAlCalificacion);
			newMateriaCalificacion.setMateriaDelCurso(mc);
			
			listMC.add(newMateriaCalificacion);
		}
		
		newAlCalificacion.setListMateriaCalificacion(listMC);
		
		container.persistIfNotAlready(newAlCalificacion);
		return newAlCalificacion;
		
	}
	
	public String validateAgregarAlumno(final @Named("Alumno: ") Alumno inAlumno,
										final @Named("Ciclo: ") Calificaciones inCalificacion,
										final @Named("Periodo: ") Periodo inPeriodo){
		
		if(inAlumno.getCurso() == null){
			return "Debe asociar el alumno a un curso con materias.";			
		}
		if(!(aluCalRepositorio.alumnoCalificacionPorAlumnoPorPeriodo(inAlumno.getDni(), inPeriodo.getNombre()).isEmpty())){
			return "El alumno ya se encuentra registrado en este periodo.";
		}
		 
		return null;
	}
	
	 @javax.inject.Inject
	 DomainObjectContainer container;
	 
	 @javax.inject.Inject
	 AlumnoRepositorio alumnoRepositorio;
	 
	 @javax.inject.Inject
	 MateriaCalificacionRepositorio materiaCalifRepositorio;
	 
	 @javax.inject.Inject
	 MateriaDelCursoRepositorio matDelCursoRepositorio;
	 
	 @javax.inject.Inject
	 AlumnoCalificacionRepositorio aluCalRepositorio;
	
}
