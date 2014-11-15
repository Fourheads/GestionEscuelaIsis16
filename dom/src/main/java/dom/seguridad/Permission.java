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
