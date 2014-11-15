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

package dom.libroDiario;

import java.util.ArrayList;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.NotContributed;
import org.apache.isis.applib.annotation.PublishedAction;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;
import org.joda.time.LocalDate;





import dom.planEstudio.Plan;
import dom.simple.Curso;
import dom.simple.CursoRepositorio;

@Hidden
@DomainService()
public class HojaLibroDiarioSevice {

	
	@Hidden
	public String getId() {
		return "hojalibrodiario";
	}

	public String title() {
		return "Hoja del libro diario";
	}

	public String iconName() {//Ver que onda??
		return "SimpleObject";
	}
	

	private ValoresHojadellibro values=new ValoresHojadellibro();
	
	//@MemberOrder(name = "Hoja del libro",sequence = "10")
	@PublishedAction
	public List<Hojadellibro> Crearview(String plan,int anio, String division,LocalDate fecha) 
	{
		LocalDate temporallocal=new LocalDate();
		values.setMin(fecha);
		values.setMax(temporallocal);
		
		return returnListhojadellibro(traerLibro(traercurso(crealistacurso(anio, division, creaplan(plan)), anio, division)),fecha);
	}

	@Hidden
	public List<Hojadellibro> returnListhojadellibro(LibroDiario newlibro, LocalDate fecha)
	{
		List<Hojadellibro> Listhojadellibro=new ArrayList<Hojadellibro>(); 
		
		for(MateriaDelLibroDiario matelibro:crealistamateria(newlibro))
		{
			
			for(EntradaLibroDiario entralibro:crealistaentrada(matelibro))
			{
				if(entralibro.getFecha().isBefore(values.getMax()))
					values.setMin(entralibro.getFecha());
				
				if(entralibro.getFecha().isAfter(values.getMin()))
					values.setMax(entralibro.getFecha());
			
				if(entralibro.getFecha().toString().contains(fecha.toString()))
				{
					for(HoraCatedra horacete:crealistahora(entralibro))
					{	
						Listhojadellibro.add(creanewHoja(matelibro, horacete));
					}
				}
			}
		}
		
		return Listhojadellibro;
	}
	
	@Hidden
	public void valoresHojalibro(final LibroDiario newlibro,final LocalDate fecha)
	{
		if(newlibro!=null)
		{
			List<Integer> horas=new ArrayList<Integer>();
			for(MateriaDelLibroDiario matelibro:crealistamateria(newlibro))
			{
				for(EntradaLibroDiario entralibro:crealistaentrada(matelibro))
					{
		
						if(entralibro.getFecha().equals(fecha))
						{
							for(HoraCatedra horacete:crealistahora(entralibro))
							{	
								horas.add(horacete.getNumerodehora());
								values.addUnidaddisponibles(horacete.getUnidad());
								values.addHorasdisponibles(horacete.getNumerodehora());
							}
						}
					}
			}
			//values.getUnidaddisponibles().remove(0);
			values.setHorasdisponibles(horas);
		}
	}
	
	@Hidden
	public List<Integer> unidades()
	{
		List<Integer> enteros=new ArrayList<Integer>();
		
		if(values.getUnidaddisponibles()!=null)
			enteros=values.getUnidaddisponibles();
		return enteros;
	}
	
	@Hidden
	public List<Integer> horas()
	{
		List<Integer> enteros=new ArrayList<Integer>();
		
		if(values.getHorasdisponibles()!=null)
			enteros=values.getHorasdisponibles();
		return enteros;
	}
	
	@Hidden
	public LocalDate maxvalue()
	{
	 return values.getMax();
	}
	
	@Hidden
	public LocalDate minvalue()
	{
		return values.getMin();
	}
	
	private Plan creaplan(String plan)
	{
		Plan newplan=new Plan();
		newplan.setDescripcion(plan);
		
		return newplan;
	}
	
	private List<Curso> crealistacurso(int anio, String divicion, Plan plan)
	{
		List<Curso> listacurso=new ArrayList<Curso>();
		listacurso=cursorepo.listarCursosDeUnPlan(plan);
		return listacurso;
	}
	
	private Curso traercurso(List<Curso> listacurso, int anio, String division)
	{
		Curso newCurso=new Curso();
		
		for(Curso curso:listacurso){
			
			if(curso.getAnio().getAnioNumero()==anio && curso.getDivision().contains(division))
			{
				newCurso=curso;
			}
		}
		return newCurso;
	}
	
	private LibroDiario traerLibro(Curso curso)
	{
		LibroDiario newlibro=new LibroDiario();
		newlibro=librorepo.mostrarLibroDiarioDelCurso(curso);
		return newlibro;
	}

	
	private List<MateriaDelLibroDiario> crealistamateria(LibroDiario libro)
	{
		List<MateriaDelLibroDiario> listamateria=new ArrayList<MateriaDelLibroDiario>();
		listamateria=libro.getMateriaDelLibroDiarioList();
		return listamateria;
	}
		
	private List<EntradaLibroDiario> crealistaentrada(MateriaDelLibroDiario materia)
	{
		 List<EntradaLibroDiario> listaentradalibro=new ArrayList<EntradaLibroDiario>();
		 listaentradalibro=materia.getEntradaLibroDiario();
		 return listaentradalibro;
	}
	
	private List<HoraCatedra> crealistahora(EntradaLibroDiario entradalibro)
	{
		List<HoraCatedra> listahoracatedra=new ArrayList<HoraCatedra>();
		listahoracatedra=entradalibro.getHoraCatedra();
		return listahoracatedra;
	}
	
	private Hojadellibro creanewHoja(MateriaDelLibroDiario matelibro, HoraCatedra horacete)
	{
		Hojadellibro newhojalibro=new Hojadellibro();
		
		newhojalibro.setMateria(matelibro);
		newhojalibro.setActividades(horacete.getActividad());
		newhojalibro.setObservaciones(horacete.getObservaciones());
		newhojalibro.setUnidad(horacete.getUnidad());
		newhojalibro.setHora(horacete.getNumerodehora());
		
		return newhojalibro;
	}
	
	
	
	@javax.inject.Inject
	DomainObjectContainer container;
	@javax.inject.Inject
	MementoService mementoService;
	@javax.inject.Inject
	LibroDiarioRepositorio librorepo;
	@javax.inject.Inject
	CursoRepositorio cursorepo;
}
