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

package dom.escuela;

import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.query.QueryDefault;

import dom.planEstudio.Materia;
@DomainService
public class MateriaDelCursoRepositorio {
	
	@Hidden
	public void crearMateriaDelCurso (Curso curso, Materia materia){
		MateriaDelCurso materiaDelCurso = new MateriaDelCurso();
		materiaDelCurso.setMateria(materia);
		curso.getMateriaDelCursoList().add(materiaDelCurso);
	}
	
	@Hidden
	public void asignarProfesorAMateriaDelCurso(MateriaDelCurso materia, Personal profesor){
		
		materia.setProfesor(profesor);
	}
	
	@Hidden
	public List<MateriaDelCurso> listarMateriaDelCursoParaUnCurso(Curso curso){
		
		return container.allMatches(new QueryDefault<MateriaDelCurso>(MateriaDelCurso.class,
				"MateriaDelCursoDeUnCurso", 
				"plan", curso.getAnio().getPlan().getDescripcion(), 
				"anio", curso.getAnio().getAnioNumero(),
				"division", curso.getDivision()));
	}	

	@javax.inject.Inject
	DomainObjectContainer container;
}
