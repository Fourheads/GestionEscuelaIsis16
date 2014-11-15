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

package dom.calificacion;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.query.QueryDefault;
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
	
	@Named("Eliminar Periodo")
	public Calificaciones eliminarPeriodo(final @Named("Periodo") Periodo inPeriodo){
		
		//List<AlumnoCalificacion> listalumno = aluCalRepositorio.listPorPeriodo(inPeriodo);
		final Calificaciones calif = inPeriodo.getCalificaciones();
		
		if(aluCalRepositorio.listPorPeriodo(inPeriodo).isEmpty()){
			calif.getPeriodos().remove(inPeriodo);
			container.removeIfNotAlready(inPeriodo);
			return calif;
		}
		
		for(AlumnoCalificacion ac: aluCalRepositorio.listPorPeriodo(inPeriodo)){
			for(MateriaCalificacion m: ac.getListMateriaCalificacion()){
				ac.getListMateriaCalificacion().remove(m);
				container.removeIfNotAlready(m);
			}
			container.removeIfNotAlready(ac);
		}
		
		calif.getPeriodos().remove(inPeriodo);
		container.removeIfNotAlready(inPeriodo);		
		
		return calif;		
	}
	
	@Named("Agregar Alumno")	
	public AlumnoCalificacion agregarAlumno(final @Named("Alumno: ") Alumno inAlumno,
											
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
										
										final @Named("Periodo: ") Periodo inPeriodo){
		
		if(inAlumno.getCurso() == null){
			return "Debe asociar el alumno a un curso con materias.";			
		}
		if(!(aluCalRepositorio.alumnoCalificacionPorAlumnoPorPeriodo(inAlumno.getDni(), inPeriodo.getNombre()).isEmpty())){
			return "El alumno ya se encuentra registrado en este periodo.";
		}
		 
		return null;
	}
	
	@Hidden
	public List<Periodo> periodoPorCiclo(final int ciclo){
		return container.allMatches(new QueryDefault<Periodo>(Periodo.class, "PeriodosPorCiclo", "ciclo", ciclo));
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
