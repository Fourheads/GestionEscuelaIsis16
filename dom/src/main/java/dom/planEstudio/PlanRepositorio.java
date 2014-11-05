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

package dom.planEstudio;

import java.util.ArrayList;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.NotContributed;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.query.Query;
import org.apache.isis.applib.query.QueryDefault;

import dom.horario.HorarioPlan;
import dom.horario.HorarioPlanRepositorio;
@Named("Planes")
@DomainService(menuOrder = "10", repositoryFor = Plan.class)
public class PlanRepositorio {

	// {{ crearPlan (action)
	@MemberOrder(sequence = "1")
	public Plan crearPlan(final String descripcion) {

		Plan plan = container.newTransientInstance(Plan.class);

		plan.setDescripcion(descripcion);
		plan.setHorarioPlan(horarioPlanRepositorio.crearHorarioPlan(plan));

		container.persistIfNotAlready(plan);

		return plan;
	}

	// }}

	// {{ listarPlanes (action)
	@MemberOrder(sequence = "1.2")
	public List<Plan> listarPlanes() {
		return queryListarPlanesAlfabeticamente();
	}
	@Programmatic
	public List<Plan> queryListarPlanesAlfabeticamente() {
		return container.allMatches(new QueryDefault<Plan>(Plan.class,
				"listarPlanes" 
				));
	}

	// }}

	// {{ seleccionar un Plan (action)
	@MemberOrder(sequence = "1.5")
	@NotContributed
	public Plan seleccionarUnPlan(Plan plan) {
		return plan;
	}
	
	public List<Plan> choices0SeleccionarUnPlan(){
		return queryListarPlanesAlfabeticamente();
	}
	
	public Plan default0SeleccionarUnPlan(){
		List<Plan> planList = queryListarPlanesAlfabeticamente();
		if (planList.isEmpty()){
			return null;
		}
		return planList.get(0);
	}

	// }}

	

	// {{ EliminarPlan (action)
	@MemberOrder(sequence = "1.1")
	@NotContributed
	public String eliminarPlan(final @Named("Plan a eliminar") Plan plan,
			final @Named("Esta seguro?") Boolean seguro) {
		String descripcion = plan.getDescripcion();
		plan.getHorarioPlan().setPlan(null);
		plan.setHorarioPlan(null);
		plan.getAnioList().clear();
		plan.setAnioList(null);
		container.remove(plan);
		return "El plan de estudio '" + descripcion + "' ha sido Eliminado";
	}

	public String validateEliminarPlan(Plan plan, Boolean seguro) {
		if (!seguro) {
			return "Marque en la opcion si está seguro!!! Si no lo está cancele esta opción";
		}

		return null;
	}

	// region > injected services
	// //////////////////////////////////////

	@javax.inject.Inject
	DomainObjectContainer container;
	@javax.inject.Inject
	HorarioPlanRepositorio horarioPlanRepositorio;
	// endregion

}
