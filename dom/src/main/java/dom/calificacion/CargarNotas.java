package dom.calificacion;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Disabled;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ObjectType;
import org.apache.isis.applib.annotation.Programmatic;
import org.apache.isis.applib.annotation.Render;
import org.apache.isis.applib.annotation.Render.Type;


@javax.jdo.annotations.PersistenceCapable(identityType = IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(strategy = javax.jdo.annotations.IdGeneratorStrategy.IDENTITY, column = "id")
@javax.jdo.annotations.Version(strategy = VersionStrategy.VERSION_NUMBER, column = "version")
@ObjectType("CargarNotas")
public class CargarNotas {

	// {{ AlumnoActivo (property)
	private MateriaCalificacion alumnoActivo;

	@MemberOrder(sequence = "1")
	@Column(allowsNull = "true")
	public MateriaCalificacion getAlumnoActivo() {
		return alumnoActivo;
	}

	public void setAlumnoActivo(final MateriaCalificacion materiaCalificacion) {
		this.alumnoActivo = materiaCalificacion;
	}
	// }}
	
	// {{ ListMateriaCalificacion (property)
	@Join
	@Element(dependent = "false")
	private List<MateriaCalificacion> listMateriaCalificacion = new ArrayList<MateriaCalificacion>();
	
	@Disabled
	@Render(Type.EAGERLY)
	@MemberOrder(sequence = "1")
	public List<MateriaCalificacion> getListMateriaCalificacion() {
		return listMateriaCalificacion;
	}

	public void setListMateriaCalificacion(final List<MateriaCalificacion> listMateriaCalificacion) {
		this.listMateriaCalificacion = listMateriaCalificacion;
	}
	// }}

	public CargarNotas calificar(){
	
		cambiarAlSiguienteAlumno();
		return this;
	}
	
	public List<Integer> choices0Calificar(){
		List<Integer> choicesNota = new ArrayList<Integer>();
		
		for(int i = 1; i < 11; i++ ){
			choicesNota.add(i);
		}
		
		return choicesNota;
	}

	@Programmatic
	public void cambiarAlSiguienteAlumno() {
		
		int activo = this.getListMateriaCalificacion().indexOf(this.getAlumnoActivo());
		System.out.println(activo);
		int siguiente = activo + 1;
		if (siguiente == this.getListMateriaCalificacion().size()) {
			siguiente = 0;
		}
		System.out.println(siguiente);
		this.setAlumnoActivo(this.getListMateriaCalificacion().get(siguiente));
	}

}
