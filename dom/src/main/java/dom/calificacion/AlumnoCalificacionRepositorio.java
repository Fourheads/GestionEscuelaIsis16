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

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.NotContributed;
import org.apache.isis.applib.query.QueryDefault;

import dom.escuela.Curso;
//@Hidden
@DomainService
public class AlumnoCalificacionRepositorio {
	
	@NotContributed
	public List<AlumnoCalificacion> alumnoCalificacionPorAlumnoPorPeriodo(final int inDni, final String periodo){
		return container.allMatches(new QueryDefault<AlumnoCalificacion>(AlumnoCalificacion.class, "findAlumnoCalificacionPorAlumnoPorPeriodo",
				"dni", inDni, "periodo", periodo));
	}
	@NotContributed
	public AlumnoCalificacion porAlumnoPorPeriodo(final int dni, final String periodo){
		return container.firstMatch(new QueryDefault<AlumnoCalificacion>(AlumnoCalificacion.class, "findAlumnoCalificacionPorAlumnoPorPeriodo",
				"dni", dni, "periodo", periodo));
	}
	@NotContributed
	public List<AlumnoCalificacion> listPorPeriodoPorCursoPorMateria(final String inPeriodo, final String inPlan, final String inDivision,
																	final String inMateria, final int inAnio){
		return container.allMatches(new QueryDefault<AlumnoCalificacion>(AlumnoCalificacion.class, "findAlumnoCalificacionPorPeriodoPorCursoPorMateria",
				"periodo", inPeriodo, "plan", inPlan, "anio", inAnio, "division", inDivision, "materia", inMateria));
	}
	@NotContributed
	public List<AlumnoCalificacion> listPorPeriodoPorCurso(final String inPeriodo, final int inAnio, final String inDivision){
		return container.allMatches(new QueryDefault<AlumnoCalificacion>(AlumnoCalificacion.class, "findAlumnoCalificacionPorPeriodoPorCurso",
				"periodo", inPeriodo, "anio", inAnio, "division", inDivision));
	}
	@NotContributed
	public List<AlumnoCalificacion> listAll(){
		return container.allInstances(AlumnoCalificacion.class);
	}
	@NotContributed
	public AlumnoCalificacion listPorAlumno(int dni){
		return container.firstMatch(new QueryDefault<AlumnoCalificacion>(AlumnoCalificacion.class, "findAlumnoCalificacionPorAlumno", "dni", dni));
	}
	@NotContributed
	public List<AlumnoCalificacion> listPorPeriodo(Periodo inPeriodo){
		return container.allMatches(new QueryDefault<AlumnoCalificacion>(AlumnoCalificacion.class, "findAlumnoCalificacionPorPeriodo", "periodo", inPeriodo.getNombre()));
	}
	
	public List<Periodo> choices0ListPorPeriodo(Periodo inPeriodo){
		return periodoRepo.listarTodos();
	}
	
	@NotContributed
	public List<Curso> listCursoPorPeriodo(Periodo inPeriodo){
		List<Curso> listCurso = new ArrayList<Curso>();
		
		for(AlumnoCalificacion a: container.allMatches(new QueryDefault<AlumnoCalificacion>(AlumnoCalificacion.class, "findAlumnoCalificacionPorPeriodo", "periodo", inPeriodo.getNombre()))){
			if(listCurso.isEmpty()){
				listCurso.add(a.getAlumno().getCurso());
			}else{				
				if(!(listCurso.contains(a.getAlumno().getCurso()))){
					listCurso.add(a.getAlumno().getCurso());				
				}
			}
		}
		
		return listCurso;
	}
	@javax.inject.Inject
	private DomainObjectContainer container;
	
	@javax.inject.Inject
	PeriodoRepositorio periodoRepo;
}
