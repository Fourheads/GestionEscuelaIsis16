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
import java.util.SortedSet;

import javax.inject.Inject;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.applib.fixturescripts.FixtureScript.Discoverability;
import org.apache.isis.applib.fixturescripts.FixtureScript.ExecutionContext;
import org.apache.isis.applib.query.QueryDefault;

import com.lowagie.text.pdf.hyphenation.TernaryTree.Iterator;

import dom.planEstudio.*;
import dom.escuela.Curso;
import dom.escuela.CursoRepositorio;
import dom.escuela.MateriaDelCurso;
import dom.escuela.Turno;
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
			
			
			/*
			for(Curso cu:cursoRepositorio.listarCursosDeUnPlan(plan))
			{


					//hora.setMateriaDelCurso(materia);

			   // cu.getHorarioCurso().getHorarioDiaList().iterator().next().getHorarioHoraList().get(0).setMateriaDelCurso(cu.getMateriaDelCursoList().get(0));
			
				
				//@SuppressWarnings("unchecked")
				//List<HorarioDia> listhoradia=(List<HorarioDia>) cu.getHorarioCurso().getHorarioDiaList();
				/*
				//int dia=0;
				int hora=0;
				java.util.Iterator<HorarioDia> it=cu.getHorarioCurso().getHorarioDiaList().iterator();
				HorarioDia dia=it.next();
				
				if(cu.getMateriaDelCursoList().size()<36)
				{
					int limite=1;
					if(cu.getMateriaDelCursoList().size()<16)
					{
						if(cu.getMateriaDelCursoList().size()<9)
						{
							if(cu.getMateriaDelCursoList().size()<5)
								limite=4;
							else
								limite=3;
						}
						else
							limite=2;
					}
				
				//for(int y=0;y<limite;y++)
					for(MateriaDelCurso materia:cu.getMateriaDelCursoList())
						{
							if(materia.getMateria().getNombre().equals("Educacion fisica") || materia.getMateria().getNombre().equals("Taller"))
							{
								//if(y==0)
								{
									for(int x=0; x<2;x++)
									{
										if(cu.getTurno()==Turno.Mañana)
										{
											//Horariocurso(listhoradia.get(GenericData.Random(0, 4)), horariohoraporturno(listhoradia.get(dia), Turno.Tarde).get(GenericData.Random(0, 4)), materia, executionContext);
											//horariohoraporturno(listhoradia.get(GenericData.Random(0, 4))).get(GenericData.Random(6, 11)).setMateriaDelCurso(materia);											
											//horariohoraporturno(dia).get(GenericData.Random(6, 11)).setMateriaDelCurso(materia);	
											  
										}
										else
										{
											//Horariocurso(listhoradia.get(GenericData.Random(0, 4)), horariohoraporturno(listhoradia.get(dia), Turno.Tarde).get(GenericData.Random(0, 4)), materia, executionContext);
											//horariohoraporturno(listhoradia.get(GenericData.Random(0, 4))).get(GenericData.Random(6, 11)).setMateriaDelCurso(materia);	
											horariohoraporturno(dia).get(GenericData.Random(6, 11)).setMateriaDelCurso(materia);	
										}
									}
								}
							}
							else
							{
								//Horariocurso(listhoradia.get(dia), horariohoraporturno(listhoradia.get(dia), cu.getTurno()).get(hora), materia, executionContext);
								//horariohoraporturno(listhoradia.get(dia)).get(hora).setMateriaDelCurso(materia);
								//horariohoraporturno(dia).get(hora).setMateriaDelCurso(materia);
								//dia.getHorarioHoraList().get(hora).setMateriaDelCurso(materia);
								it.next().getHorarioHoraList().get(hora).setMateriaDelCurso(materia);
							}
							
							if(hora< horariohoraporturno(dia).size()-6)
							{
								hora++;
							}
							else
							{
								hora=0;
								if(it.hasNext())
									dia=it.next();
							}
							
						}
				}
			}*/
		}

	}
	/*
	private List<HorarioHora> horariohoraporturno(HorarioDia dia)
	{
		if (dia != null) {
			List<HorarioHora> listaHoras = dia.getHorarioHoraList();

			List<HorarioHora> listaHorasFiltrada = new ArrayList<HorarioHora>();
			for (HorarioHora horarioHora : listaHoras) {
				if (horarioHora.getHorarioHoraTipo() == E_HorarioHoraTipo.HORA_CATEDRA
						&& horarioHora.getMateriaDelCurso() == null) {
					listaHorasFiltrada.add(horarioHora);
				}
			}

			return listaHorasFiltrada;
		}
		return null;
	}*/
	
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
    
    /*
    private HorarioCursoView Horariocurso(HorarioDia dia, HorarioHora hora,MateriaDelCurso materia, ExecutionContext executionContext)
    {
    	return executionContext.add(this,horariocursoview.asignarMateria(dia, hora, materia));
    }
    
    private HorarioCursoView EliminarHorariocurso(HorarioDia dia, HorarioHora hora, ExecutionContext executionContext)
    {
    	return executionContext.add(this,horariocursoview.quitarMateria(dia, hora));
    }*/
    
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
	@Inject
	HorarioCursoView horariocursoview;
}
