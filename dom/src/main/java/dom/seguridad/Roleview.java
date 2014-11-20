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

package dom.seguridad;

import java.util.List;

import org.apache.isis.applib.AbstractViewModel;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.PublishedAction;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Render.Type;

import dom.libroDiario.HojaLibroDiarioView;

@Named("Roles")
@Bookmarkable
@MemberGroupLayout(columnSpans = { 5, 0, 0, 7 })
public class Roleview extends AbstractViewModel{

	
	// {{ ListaPremission (property)
	private List<Role> ListaRole;

	@Render(Type.EAGERLY)
	@MemberOrder(sequence = "2")
	@Named("Listado de Roles")
	public List<Role> getListaRoles() {
		return ListaRole;
	}

	public void setListaRoles(final List<Role> ListaRole) {
		this.ListaRole = ListaRole;
	}
	// }}


	@Named("Crear un nuevo rol")
	@MemberOrder(sequence = "1", name = "Menu")
	@PublishedAction
	public Role crearunnuevorol(final @Named("Nombre") String roleName,
            final @Named("Permiso") Permission permission) {

		return rolerepo.create(roleName, permission);
	}


	@Named("Eliminar un rol")
	@MemberOrder(sequence = "2", name = "Menu")
	@PublishedAction
	public String eliminarunrol(@Named("Rol") Role Role) {
						
		return rolerepo.removeRole(Role);
	}
	
	//private String Menu="Menu";
	
	@Named("Menu")
	public void getMenu()
	{
	}
	
	private String title="Roles";

	// region > identification in the UI
	public String title() {
		return title;
	}
	
	String memento;
	
	@Override
	public String viewModelMemento() {
		return memento;
	}

	@Override
	public void viewModelInit(String mementoString) {
		this.memento = mementoString;
		setListaRoles(rolerepo.listAll());
		
	}

	@javax.inject.Inject 
	RoleRepository rolerepo;
}
