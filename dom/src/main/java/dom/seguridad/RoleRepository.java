package dom.seguridad;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.ActionSemantics;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Where;
import org.apache.isis.applib.annotation.ActionSemantics.Of;


@DomainService(menuOrder = "35", repositoryFor = Role.class)
@Named("Roles")
public class RoleRepository {
	
	 //region > identification in the UI
    // //////////////////////////////////////

    public String getId() {
        return "role";
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
    @Named("List Roles")
    public List<Role> listAll() {
        return container.allInstances(Role.class);
    }

    //endregion

    //region > create (action)
    // //////////////////////////////////////
    
    @MemberOrder(sequence = "2")
    @Named("Create New Role")
    @Hidden(where = Where.OBJECT_FORMS)
    public Role create(
            final @Named("Name") String roleName,
            final @Named("Permission") Permission permission) {
        final Role obj = container.newTransientInstance(Role.class);
        final SortedSet<Permission> permissionsList = new TreeSet<Permission>();
        
        permissionsList.add(permission);
        obj.setRoleName(roleName);
        obj.setPermissionsList(permissionsList);
        container.persistIfNotAlready(obj);
        return obj;
    }

    //endregion

    //region > remove Role (action)
    // //////////////////////////////////////
    
    @ActionSemantics(Of.NON_IDEMPOTENT)
    @MemberOrder(sequence = "4")
    @Named("Remove Role")
    public String removeRole(@Named("Role") Role role) {
    	String roleName = role.getRoleName();
    	container.remove(role);
        return "The role " + roleName + " has been removed";
    }

    //endregion
    
    
    //region > injected services
    // //////////////////////////////////////

    @javax.inject.Inject 
    DomainObjectContainer container;

    //endregion

}
