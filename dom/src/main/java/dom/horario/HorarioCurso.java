package dom.horario;
import dom.simple.Curso;

import java.util.Set;
import java.util.HashSet;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
@SuppressWarnings("unused")
@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
//@javax.jdo.annotations.Queries({ @javax.jdo.annotations.Query(name = "listarAniosDeUnPlan", language = "JDOQL", value = "SELECT "
//		+ "FROM dom.planEstudio.Anio "
//		+ "WHERE this.plan.descripcion == :descripcion") })
@ObjectType("CRONOGRAMA_CURSO")
@Bookmarkable
@Bounded
public class HorarioCurso
{
	
	// {{ Curso (property)
	private Curso curso;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public Curso getCurso() {
		return curso;
	}

	public void setCurso(final Curso curso) {
		this.curso = curso;
	}
	// }}


	

}

