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

import java.util.SortedSet;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Title;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@ObjectType("ShiroUser")
@Bounded
public class ShiroUser {

	// {{ UserName (property)
	private String userName;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	@Title
	public String getUserName() {
		return userName;
	}

	public void setUserName(final String userName) {
		this.userName = userName;
	}

	// }}

	// {{ Password (property)
	private String password;

	@MemberOrder(sequence = "2")
	@Column(allowsNull = "false")
	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	// }}

	// {{ RolesList (Collection)
	@Join
	@Element(dependent = "false")
	private SortedSet<Role> rolesList = new TreeSet<Role>();

	@MemberOrder(sequence = "3")
	@Render(org.apache.isis.applib.annotation.Render.Type.EAGERLY)
	public SortedSet<Role> getRolesList() {
		return rolesList;
	}

	public void setRolesList(final SortedSet<Role> rolesList) {
		this.rolesList = rolesList;
	}

	// }}

	// region > add role (action)
	// //////////////////////////////////////

	@MemberOrder(sequence = "3")
	@Named("Add Role for this User")
	public ShiroUser addRole(final @Named("Role") Role role) {

		rolesList.add(role);

		return this;
	}

	// endregion

	// region > remove permission (action)
	// //////////////////////////////////////

	@MemberOrder(sequence = "5")
	@Named("Remove Role for this User")
	public ShiroUser removeRole(final @Named("Role") Role role) {

		getRolesList().remove(role);
		return this;
	}

	public SortedSet<Role> choices0RemoveRole() {
		return getRolesList();
	}

	// endregion

		
	// region > injected services
	// //////////////////////////////////////

	@javax.inject.Inject
	DomainObjectContainer container;

	// endregion

}
