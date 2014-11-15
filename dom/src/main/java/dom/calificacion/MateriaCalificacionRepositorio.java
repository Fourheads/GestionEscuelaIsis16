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

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.query.QueryDefault;
@Hidden
@DomainService
public class MateriaCalificacionRepositorio {
	
	//Hacer query de materias por curso
		
	public List<MateriaCalificacion> materiaCalificacionPorAlumno(final int inDni){
		return container.allMatches(new QueryDefault<MateriaCalificacion>(MateriaCalificacion.class, "findMateriaCalificacionPorAlumno",
				"dni", inDni));
	}
	
	public List<MateriaCalificacion> listAll(){
		return container.allInstances(MateriaCalificacion.class);
	}
	
	public List<MateriaCalificacion> listMateriaCalificacionPorCursoPorMateria(int inAnio, 
										String inDivision, String inPlan, String inMateria){
		return container.allMatches(new QueryDefault<MateriaCalificacion>(MateriaCalificacion.class, "findMateriaCalificacionPorCursoPorMateria",
				"anio", inAnio, "plan", inPlan, "division", inDivision, "materia", inMateria));
	}
	
	public List<MateriaCalificacion> listMateriCalificacionPorMateria(String materia){
		return container.allMatches(new QueryDefault<MateriaCalificacion>(MateriaCalificacion.class, "findMateriaCalificacionPorMateria",
				"materia", materia));
	}
	
	public List<MateriaCalificacion> listPorCursoPorMateriaPorPeriodo(int inAnio, String inPlan, String inDivision, String inMateria, 
											String inPeriodo){
		return container.allMatches(new QueryDefault<MateriaCalificacion>(MateriaCalificacion.class, "findMateriaCalificacionPorCursoPorMateriaPorPeriodo",
							"anio", inAnio, "plan", inPlan, "division", inDivision, "materia", inMateria, "periodo", inPeriodo));		
	}
	
	@javax.inject.Inject
	private DomainObjectContainer container;
}
