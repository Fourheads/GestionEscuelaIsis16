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


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.fixturescripts.FixtureScript.Discoverability;
import org.apache.isis.applib.fixturescripts.FixtureScript.ExecutionContext;
import org.joda.time.LocalDate;

import dom.asistencia.Asistencia;
import dom.asistencia.AsistenciaAlumno;
import dom.asistencia.AsistenciaDia;
import dom.asistencia.AsistenciaDiaRepositorio;
import dom.asistencia.AsistenciaRepositorio;


public class AsistenciaFixture extends FixtureScript {

	public AsistenciaFixture() {
        withDiscoverability(Discoverability.DISCOVERABLE);
    }
	
	@Override
	protected void execute(ExecutionContext executionContext) {
		
		BorrarDBAsistencia(executionContext);
		
		Asistencia asi=create("Asistencia", executionContext);
		
		LocalDate hoy=new LocalDate();
		hoy=hoy.now();
		
		
		
		for(int x=0;x<=7;x++)
		{
			createAsistenciaDia(asi, hoy, executionContext);
			for(AsistenciaDia asistenciadia:Asistenciadiarepo.porFechaParaUnEsquema(hoy, asi))
			{
				List<AsistenciaAlumno> listalumno =new ArrayList<AsistenciaAlumno>();
				
				for(AsistenciaAlumno alumno:asistenciadia.getAsistenciaAlumnoList())
				{
					int valor=GenericData.Random(1, 100);
					if(valor>=GenericData.Random(20, 60))
					{
						if(valor>=50)
						{
							alumno.setEstaPresente(true);
						}
						else
						{
							alumno.setEstaPresente(true);
							alumno.setLlegoTarde(true);
						}
							
					}
					else
					{
						alumno.setEstaPresente(false);
						alumno.setLlegoTarde(false);
					}
						
					listalumno.add(createAlumnoAsistencia(alumno, executionContext));
				}
				
				asistenciadia.setAsistenciaAlumnoList(listalumno);
			}
			hoy=hoy.plusDays(+1);
		}
		
	}

    private void BorrarDBAsistencia(ExecutionContext executionContext) {
    	execute(new GenericTearDownFixture("Asistencia"),executionContext);

    	return;	
	}

	private Asistencia create(String descripcion, ExecutionContext executionContext) {
		return executionContext.add(this,Asistenciarepo.create(descripcion));
    }
    
	private Asistencia createAsistenciaDia(Asistencia asistencia,LocalDate fecha, ExecutionContext executionContext) {
		return executionContext.add(this,Asistenciarepo.createAsistenciaDia(asistencia, fecha));
    }
    
	private AsistenciaAlumno createAlumnoAsistencia(AsistenciaAlumno asisalum, ExecutionContext executionContext) {
		return executionContext.add(this,asisalum);
    }
	
	@javax.inject.Inject
	AsistenciaRepositorio Asistenciarepo;
	@javax.inject.Inject
	AsistenciaDiaRepositorio Asistenciadiarepo;
	
}
