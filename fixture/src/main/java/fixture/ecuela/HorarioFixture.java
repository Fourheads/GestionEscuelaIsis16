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

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.fixturescripts.FixtureScript.Discoverability;
import org.apache.isis.applib.fixturescripts.FixtureScript.ExecutionContext;

import dom.planEstudio.*;
import dom.escuela.CursoRepositorio;
import dom.horario.*;

public class HorarioFixture extends FixtureScript {

	public HorarioFixture() {
        withDiscoverability(Discoverability.DISCOVERABLE);
    }
	
	@Override
	protected void execute(ExecutionContext executionContext) {
		
		BorrarDBHorario(executionContext);
		
		E_HorarioHoraTipo horatipo = null;
		
		HorarioPlan horaplan=new HorarioPlan();
		
		Hora horaInicio = new Hora();
		horaInicio.setHora(8);
		horaInicio.setMinutos(0);
		
		for(Plan plan:PlanRepo.listarPlanes())
		{
			plan.getHorarioPlan().setInicioClases(horaInicio);			
			horaplan=plan.getHorarioPlan();
			
			for(int y=1;y<=2;y++)
			{
				for(int x=1;x<=8;x++)
				{
					if(x==3 || x==6)
					{
						horaplan=crearHorarioPlanHora(plan.getHorarioPlan(), horatipo.RECREO, 10, executionContext);
					}
					else
					{
						horaplan=crearHorarioPlanHora(plan.getHorarioPlan(), horatipo.HORA_CATEDRA, 40, executionContext);
					}
					plan.setHorarioPlan(horaplan);
				}
				
				if(y==1)
				{
					horaplan=crearHorarioPlanHora(plan.getHorarioPlan(), horatipo.ALMUERZO, 70, executionContext);
				}
			}
		}
		
		
		

	}
	
	
    private void BorrarDBHorario(ExecutionContext executionContext) {
		for(Plan plan:PlanRepo.listarPlanes())
		{
			plan.getHorarioPlan().setInicioClases(null);
			for(HorarioPlanHora hora:plan.getHorarioPlan().getHorarioPlanHoraList())
			{
				EliminarUltimaHora(plan.getHorarioPlan(), executionContext);
			}
			
		}
    	return;	
	}
    
    
	private HorarioPlan EliminarUltimaHora(HorarioPlan horarioPlan, ExecutionContext executionContext)
	{
		return executionContext.add(this,horarioPlanRepositorio.eliminarUltimaHora(horarioPlan));
	}
	
	private HorarioPlan crearHorarioPlanHora(HorarioPlan horarioPlan, E_HorarioHoraTipo e_HorarioHoraTipo, Integer duracion, ExecutionContext executionContext)
	{
		return executionContext.add(this, horarioPlanRepositorio.crearHorarioPlanHora(horarioPlan, e_HorarioHoraTipo, duracion));
	}
	
	
	
	@Inject
	DomainObjectContainer container;
    @javax.inject.Inject
    PlanRepositorio PlanRepo;
	@Inject
	HorarioPlanRepositorio horarioPlanRepositorio;
	@Inject
	CursoRepositorio cursoRepositorio;
}
