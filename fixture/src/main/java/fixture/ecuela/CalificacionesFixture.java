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

package fixture.ecuela;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.fixturescripts.FixtureScript.Discoverability;
import org.apache.isis.applib.fixturescripts.FixtureScript.ExecutionContext;
import org.joda.time.LocalDate;

import dom.calificacion.AlumnoCalificacion;
import dom.calificacion.AlumnoCalificacionRepositorio;
import dom.calificacion.CalificacionRepositorio;
import dom.calificacion.Calificaciones;
import dom.calificacion.MateriaCalificacion;
import dom.calificacion.MateriaCalificacionRepositorio;
import dom.calificacion.PeriodoRepositorio;

public class CalificacionesFixture extends FixtureScript {

	public CalificacionesFixture() {
        withDiscoverability(Discoverability.DISCOVERABLE);
    }
	
	@Override
	protected void execute(ExecutionContext executionContext) {
		
		BorrarDBCalificaciones(executionContext);
		
		LocalDate hoy=new LocalDate();
		LocalDate hoymassemana=new LocalDate();
		hoy=hoy.now();
		hoymassemana=hoy.plusDays(+7);
		
		createPeriodo(createCalificacion(hoy.year().get(), executionContext),"POEC",hoy,hoymassemana,executionContext);
		
		for(AlumnoCalificacion alucal:aluCalRepositorio.listAll())
		{
			for(MateriaCalificacion matecal:alucal.getListMateriaCalificacion())
			{
				matecal.setNota(GenericData.Random(1, 10));
				matecal.setObservacion("Sin observacion");
			}
			createAlumnocal(alucal, executionContext);
		}
		
	}
	
    private void BorrarDBCalificaciones(ExecutionContext executionContext) {
    	execute(new GenericTearDownFixture("Calificaciones"),executionContext);

    	return;	
	}

	private Calificaciones createCalificacion(int ciclo, ExecutionContext executionContext) {
        return executionContext.add(this, calificacionesrepo.createCalificacion(ciclo));
    }
	
	private Calificaciones createPeriodo(Calificaciones inCalificaciones,String inNombre,LocalDate inFechaI,LocalDate inFechaF, ExecutionContext executionContext) {
        return executionContext.add(this, periodoRepositorio.createPeriodo(inCalificaciones, inNombre, inFechaI, inFechaF));
    }

	private AlumnoCalificacion createAlumnocal(AlumnoCalificacion al, ExecutionContext executionContext) {
        return executionContext.add(this, al);
    }

	@javax.inject.Inject
	CalificacionRepositorio calificacionesrepo;
	@javax.inject.Inject
	PeriodoRepositorio periodoRepositorio;
	@javax.inject.Inject
	AlumnoCalificacionRepositorio aluCalRepositorio;
}
