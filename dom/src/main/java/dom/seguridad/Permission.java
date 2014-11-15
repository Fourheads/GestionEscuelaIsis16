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

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(
        strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
         column="id")
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")
@ObjectType("Permission")
@Bounded
public class Permission implements Comparable<Permission>{

	// {{ PermissionName (property)
	private String permissionDescription;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	public String getPermissionDescription() {
		return permissionDescription;
	}

	public void setPermissionDescription(final String permissionDescription) {
		this.permissionDescription = permissionDescription;
	}
	// }}
	
	// {{ PermissionText (property)
	private String permissionText;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	public String getPermissionText() {
		return permissionText;
	}

	public void setPermissionText(final String permissionText) {
		this.permissionText = permissionText;
	}
	// }}


	

	public String title(){
		String text = permissionDescription;
		return text;
	}

	@Override
	public int compareTo(Permission other) {
		int last = this.getPermissionDescription().compareTo(other.getPermissionDescription());
        return last;
	}
}
