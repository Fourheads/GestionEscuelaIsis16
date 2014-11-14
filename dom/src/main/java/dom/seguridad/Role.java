package dom.seguridad;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Render;

@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@ObjectType("Role")
@Bounded
public class Role implements Comparable<Role> {

	// {{ RoleName (property)
	private String roleName;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "false")
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(final String roleName) {
		this.roleName = roleName;
	}

	// }}

	// {{ PermissionsList (Collection)
	@Join
	@Element(dependent = "false")
	private SortedSet<Permission> permissionsList = new TreeSet<Permission>();

	@MemberOrder(sequence = "3")
	@Render(org.apache.isis.applib.annotation.Render.Type.EAGERLY)
	public SortedSet<Permission> getPermissionsList() {
		return permissionsList;
	}

	public void setPermissionsList(final SortedSet<Permission> permissionsList) {
		this.permissionsList = permissionsList;
	}

	// }}
	
	//region > add permission (action)
    // //////////////////////////////////////
    
    @MemberOrder(sequence = "4")
    @Named("Add Permission for this Role")
    public Role addPermission(
            final @Named("Permission") Permission permission) {
              
        permissionsList.add(permission);
        
        return this;
    }

    //endregion

	public String title() {
		String text = roleName;
		return text;
	}

	@Override
	public int compareTo(Role other) {
		int last = this.getRoleName().compareTo(other.getRoleName());
		return last;
	}
	
	//region > remove permission (action)
    // //////////////////////////////////////
    
    @MemberOrder(sequence = "5")
    
    @Named("Remove Permission for this Role")
    public Role removePermission(
            final @Named("Permission") Permission permission) {
    	
    	getPermissionsList().remove(permission);    	
        return this;
    }

    //endregion

    
    public SortedSet<Permission> choices0RemovePermission(){
    	return getPermissionsList();
    }
}
