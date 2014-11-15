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

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionSemantics;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.ActionSemantics.Of;


@DomainService(menuOrder = "45", repositoryFor = Permission.class)
@Named("Permission")
public class PermissionRepository {
	
	 //region > identification in the UI
    // //////////////////////////////////////

    public String getId() {
        return "Permission";
    }

    public String iconName() {
        return "SimpleObject";
    }

    //endregion

    //region > listAll (action)
    // //////////////////////////////////////

    @Bookmarkable
    @ActionSemantics(Of.SAFE)
    @MemberOrder(sequence = "1")
    @Named("List Permissions")
    public List<Permission> listAll() {
        return container.allInstances(Permission.class);
    }

    //endregion

    //region > create (action)
    // //////////////////////////////////////
    
    @MemberOrder(sequence = "2")
    @Named("Create New Permission")
    public Permission create(
            final @Named("Description") String permissionDescription,
            final @Named("Permission") String permissionText) {
        final Permission obj = container.newTransientInstance(Permission.class);
        
        
        obj.setPermissionDescription(permissionDescription);
        obj.setPermissionText(permissionText);
        
        container.persistIfNotAlready(obj);
        return obj;
    }
            
    //endregion

    
    
    //region > remove Permission (action)
    // //////////////////////////////////////
    
    @ActionSemantics(Of.NON_IDEMPOTENT)
    @MemberOrder(sequence = "4")
    @Named("Remove Permission")
    public String removePermission(@Named("Permission") Permission permission) {
    		String permissionDescription = permission.getPermissionDescription();
			container.remove(permission);
			return "The permission " + permissionDescription + " has been removed";
	}

    //endregion
    
    
    //region > injected services
    // //////////////////////////////////////

    @javax.inject.Inject 
    DomainObjectContainer container;

    //endregion

}
