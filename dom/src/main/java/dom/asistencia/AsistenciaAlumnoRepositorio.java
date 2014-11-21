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

package dom.asistencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.query.QueryDefault;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;
import org.joda.time.LocalDate;

import dom.escuela.Alumno;

@Hidden
@DomainService
public class AsistenciaAlumnoRepositorio {
	
	
	public List<AsistenciaAlumno> queryAsistenciaAlumnoPorCursoPorDia(
			LocalDate fecha, int anio, String division, String asistencia) {
		return filtroAL(container.allMatches(new QueryDefault<AsistenciaAlumno>(
				AsistenciaAlumno.class, "asistenciaAlumno_asistenciaDiaCurso", 
				"anio", anio, 
				"division", division,
				"fecha", fecha,
				"asistencia", asistencia
				)),'S');
	}
	
	
	public List<AsistenciaAlumno> queryAsistenciaAlumnoPorCursoEnUnIntervalo (	
												String asistencia,
												int anio, 
												String division,
												LocalDate desde,
												LocalDate hasta,
												int dni
											){
		
		List<AsistenciaAlumno> tempList = container.allMatches(new
				QueryDefault<AsistenciaAlumno>( AsistenciaAlumno.class,
				"asistenciaAlumno_ContarAsistencias", 
				"asistencia", asistencia,
				"anio", anio, 
				"division",	division,		
				"desde", desde, 
				"hasta", hasta, 
				"dni", dni 
				));
			
		return filtroAL(tempList,'S');
	}

	
	
	
	private List<AsistenciaAlumno>  filtroAL(List<AsistenciaAlumno>  Alumnos, char A)
	{
		List<AsistenciaAlumno>  filtroAL=new ArrayList<AsistenciaAlumno> ();
		
		for(AsistenciaAlumno al:Alumnos)
		{
			if(al.getAlumno().getHabilitado()==A)
				filtroAL.add(al);
		}
		
		return filtroAL;
	}
	
	
	// region > injected services
	@javax.inject.Inject
	DomainObjectContainer container;

	// endregion
	
}
