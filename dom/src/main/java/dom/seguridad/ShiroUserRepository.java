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

@DomainService(menuOrder = "25", repositoryFor = ShiroUser.class)
@Named("Shiro User")
public class ShiroUserRepository {

	// region > identification in the UI
	// //////////////////////////////////////

	public String getId() {
		return "shirouser";
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
	@Named("List All Users")
	public List<ShiroUser> listAll() {
		return container.allInstances(ShiroUser.class);
	}

	// endregion

	// region > create (action)
	// //////////////////////////////////////

	@MemberOrder(sequence = "2")
	@Named("Create new User")
	@Hidden(where = Where.OBJECT_FORMS)
	public ShiroUser create(final @Named("Name") String userName,
			final @Named("Password") String password,
			final @Named("Role") Role role) {
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
	@Named("Remove User")
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
