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


import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.MemberGroupLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.services.memento.MementoService;
import org.apache.isis.applib.services.memento.MementoService.Memento;

import dom.libroDiario.HojaLibroDiarioView;

@DomainService(menuOrder = "45")
@Named("Cuentas")
public class Cuentas {

    @MemberOrder(sequence = "1")
    @Named("Permisos")
	public Permissionview Permisos()
	{
		Memento memento = mementoService.create();
		memento.set("Title","Titulo");
		return container.newViewModelInstance(
				Permissionview.class, memento.asString());
	}
	
    @MemberOrder(sequence = "2")
    @Named("Roles")
	public Roleview Roles()
	{
		Memento memento = mementoService.create();
		memento.set("Title","Titulo");
		return container.newViewModelInstance(
				Roleview.class, memento.asString());
	}
    
    @MemberOrder(sequence = "3")
    @Named("Usuarios")
	public ShiroUserview Usuarios()
	{
		Memento memento = mementoService.create();
		memento.set("Title","Titulo");
		return container.newViewModelInstance(
				ShiroUserview.class, memento.asString());
	}
	
    @javax.inject.Inject
	DomainObjectContainer container;
	@javax.inject.Inject
	MementoService mementoService;
}

