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

import java.util.List;

import javax.inject.Named;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.NotContributed;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;


@Named("Calificaciones por Alumno")
@DomainService
public class AlumnoMateriasService {
	@Hidden
	public String getId() {
		return "alumnoMateriasService";
	}

	public String title() {
		return "Calificaciones";
	}

	public String iconName() {
		return "SimpleObject";
	}
	
	@NotContributed	
	@MemberOrder(name = "Alumnos", sequence = "8")
	@Named("Calificaciones por alumno")
	public AlumnoMateriasView seleccionarAlumno(final @Named("Ciclo") Calificaciones ciclo,
													  final @Named("Periodo") Periodo periodo,
													  final @Named("Alumno") AlumnoCalificacion alumno){
		//titulo, alumno, ciclo, curso, division, dni, periodo, turno
		Memento newMemento = mementoService.create();
		
		newMemento.set("titulo", "Calificaciones del Alumno");
		newMemento.set("alumno", alumno.title());
		newMemento.set("ciclo", ciclo.getCicloCalificacion());
		newMemento.set("curso", alumno.getAlumno().getCurso().getAnio().getAnioNumero());
		newMemento.set("division", alumno.getAlumno().getCurso().getDivision());
		newMemento.set("dni", alumno.getAlumno().getDni());
		newMemento.set("periodo", periodo.getNombre());
		newMemento.set("turno", alumno.getAlumno().getCurso().getTurno().toString());
		
		final AlumnoMateriasView newView = container.newViewModelInstance(AlumnoMateriasView.class, newMemento.asString());
		return newView;
		
	}
	
		//Choices CICLO
	    public List<Calificaciones> choices0SeleccionarAlumno(final @Named("Ciclo") Calificaciones ciclo) {

			List<Calificaciones> listCalificaciones = califRepositorio.ciclosConPeriodo();
			if (listCalificaciones.isEmpty()) {
				
				return null;
			}
			return listCalificaciones;
		}  
	   
	    
	    //Choices PERIODO
	    public List<Periodo> choices1SeleccionarAlumno(final @Named("Ciclo") Calificaciones ciclo,
				final @Named("Periodo") Periodo periodo,
				final @Named("Alumno") AlumnoCalificacion alumno){
	    	if(ciclo != null){	    		
	    		if(!ciclo.getPeriodos().isEmpty()){
		    		return ciclo.getPeriodos();
		    	}
	    	}
	    	return null;   	
	    	
	    }	   
	    
	    //Choices ALUMNOCALIFICACION
	    public List<AlumnoCalificacion> choices2SeleccionarAlumno(final @Named("Ciclo") Calificaciones ciclo,
				final @Named("Periodo") Periodo periodo,
				final @Named("Alumno") AlumnoCalificacion alumno){
	    	if(periodo != null){
	    		if(!aluCal.listPorPeriodo(periodo).isEmpty()){
	    			return aluCal.listPorPeriodo(periodo);	
	    		}
	    	}
	    	return null;
	    }

	@javax.inject.Inject
    MementoService mementoService;
	
	@javax.inject.Inject
	DomainObjectContainer container;
	
	@javax.inject.Inject
	AlumnoCalificacionRepositorio aluCal;
	
	@javax.inject.Inject
	CalificacionRepositorio califRepositorio;
}
