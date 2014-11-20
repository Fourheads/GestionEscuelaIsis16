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

import java.util.ArrayList;
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

@Named("Usuarios")
@Bookmarkable
@MemberGroupLayout(columnSpans = { 5, 0, 0, 7 })
public class ShiroUserview extends AbstractViewModel{

	
	// {{ ListaPremission (property)
	private List<ShiroUser> ListaShiroUser;

	@Render(Type.EAGERLY)
	@MemberOrder(sequence = "2")
	@Named("Listado de Usuarios")
	public List<ShiroUser> getListaPremission() {
		return ListaShiroUser;
	}

	public void setListaPremission(final List<ShiroUser> ListaShiroUser) {
		this.ListaShiroUser = ListaShiroUser;
	}
	// }}


	@Named("Crear un nuevo usuario")
	@MemberOrder(sequence = "1", name = "Menu")
	@PublishedAction
	public void crearunnuevousuario(final @Named("Nombre") String userName,
			final @Named("Contraseña") String password,
			final @Named("Rol") Role role) {

		shirorepo.create(userName, password, role);
	}


	@Named("Eliminar un usuario")
	@MemberOrder(sequence = "2", name = "Menu")
	@PublishedAction
	public void eliminarunusuario(@Named("ShiroUser") ShiroUser ShiroUser) {
						
		shirorepo.removeUser(ShiroUser);
	}
	
	//private String Menu="Menu";
	
	@Named("Menu")
	public void getMenu()
	{
	}
	
	private String title="Usuarios";

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
		List<ShiroUser> ListaShiroUser =new ArrayList<ShiroUser>();
		ListaShiroUser=shirorepo.listAll();
		for(ShiroUser User:ListaShiroUser)
			User.setPassword("************");
		
		setListaPremission(ListaShiroUser);
		
	}

	@javax.inject.Inject 
	ShiroUserRepository shirorepo;
}
