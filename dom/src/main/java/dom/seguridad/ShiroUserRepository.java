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
import java.util.SortedSet;
import java.util.TreeSet;

import javax.annotation.PostConstruct;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionSemantics;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.ActionSemantics.Of;
import org.apache.isis.applib.annotation.Where;

@Hidden
@DomainService(menuOrder = "25", repositoryFor = ShiroUser.class)
@Named("Usuarios")
public class ShiroUserRepository {

	// region > identification in the UI
	// //////////////////////////////////////

	public String getId() {
		return "Usuarios";
	}

	public String iconName() {
		return "SimpleObject";
	}

	// endregion

	// region > listAll (action)
	// //////////////////////////////////////

	@Bookmarkable
	@ActionSemantics(Of.SAFE)
	@MemberOrder(sequence = "1")
	@Named("Listar todos los usuarios")
	public List<ShiroUser> listAll() {
		return container.allInstances(ShiroUser.class);
	}

	// endregion

	// region > create (action)
	// //////////////////////////////////////

	@MemberOrder(sequence = "2")
	@Named("Crear un nuevo usuario")
	//@Hidden(where = Where.OBJECT_FORMS)
	public ShiroUser create(final @Named("Nombre") String userName,
			final @Named("Contraseña") String password,
			final @Named("Rol") Role role) {
		final ShiroUser obj = container.newTransientInstance(ShiroUser.class);

		final SortedSet<Role> rolesList = new TreeSet<Role>();
		rolesList.add(role);
		obj.setUserName(userName);
		obj.setPassword(password);
		obj.setRolesList(rolesList);
		container.persistIfNotAlready(obj);
		return obj;
	}

	// endregion

	// region > remove User (action)
	// //////////////////////////////////////

	@ActionSemantics(Of.NON_IDEMPOTENT)
	@MemberOrder(sequence = "4")
	@Named("Borrar usuario")
	public String removeUser(@Named("User") ShiroUser shiroUser) {
		String userName = shiroUser.getUserName();
		container.remove(shiroUser);
		return "The user " + userName + " has been removed";
	}

	// endregion

	// region > initialization
	// //////////////////////////////////////

	@Programmatic
	@PostConstruct
	public void init() {
		List<ShiroUser> users = listAll();
		if (users.isEmpty()) {
			Permission permission = new Permission();
			Role role = new Role();
			SortedSet<Permission> permissions = new TreeSet<Permission>();

			permission.setPermissionDescription("EVERYTHING");
			permission.setPermissionText("*");
			permissions.add(permission);
			role.setRoleName("ADMINISTRATOR");
			role.setPermissionsList(permissions);

			create("sven", "pass", role);
		}
	}

	// endregion

	// region > injected services
	// //////////////////////////////////////

	@javax.inject.Inject
	DomainObjectContainer container;

	// endregion

}
