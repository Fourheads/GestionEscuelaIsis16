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

package dom.simple;

import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.query.QueryDefault;
import org.joda.time.LocalDate;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.annotation.ActionSemantics.Of;
import dom.simple.Localidad.E_localidades;
import dom.simple.Persona.E_nacionalidad;
import dom.simple.Persona.E_sexo;


///GestionEscuela

@DomainService(menuOrder = "16", repositoryFor = Alumno.class)
@Named("Alumnos")
public class AlumnoRepositorio {


    // //////////////////////////////////////
    // Identification in the UI
    // //////////////////////////////////////

    public String getId() {
        return "alumno";
    }

    public String iconName() {
        return "SimpleObject";
    }

    // //////////////////////////////////////
    // List (action)
    // //////////////////////////////////////
    
    @Bookmarkable
    @ActionSemantics(Of.SAFE)
    @MemberOrder(sequence = "1")
    @Named ("Listar Alumnos")
    public List<Alumno> listAll() {
        return container.allInstances(Alumno.class);
    }


    // //////////////////////////////////////
    // Create (action)
    // //////////////////////////////////////
      
    
    @MemberOrder(sequence = "2")
    @Named ("Crear Alumno")
    public Alumno create(
            final @RegEx(validation = "[A-Za-z]+") @Named("Nombre") String nombre,
            final @RegEx(validation = "[A-Za-z]+") @Named("Apellido") String apellido,
            final @Named("Sexo") E_sexo sexo,
            final @RegEx(validation = "/d{6,10}") @Named("DNI") int dni, 
            final @Named("Fecha Nacimiento") LocalDate nacimiento,
            final @Named("Nacionalidad") E_nacionalidad nacionalidad,
            final @Named("Domicilio. Localidad") E_localidades localidad,
            final @Named("Domicilio. Calle") String calle,
            final @RegEx(validation = "/d{5}") @Named("Domicilio. Numero") int numero,
            final @RegEx(validation = "/d+")@org.apache.isis.applib.annotation.Optional @Named("Domicilio. Piso") String piso,
            final @org.apache.isis.applib.annotation.Optional @Named("Domicilio. Departamento") String departamento,
            @RegEx(validation = "/d+") @SuppressWarnings("deprecation") final @Mask("(NNNN)NNN-NNNNNNN") @org.apache.isis.applib.annotation.Optional @Named("Teléfono") String telefono) {
        
    	final Alumno obj = container.newTransientInstance(Alumno.class);
        final Direccion dire = new Direccion();
        final Localidad loca = new Localidad();
        final Legajo legajo = new Legajo();
        
        String propietario = nombre.substring(0, 1) + ". " + apellido; 
        
        loca.setNombre(localidad);
        
        dire.setCalle(calle.toUpperCase());
        dire.setNumero(numero);
        dire.setPiso(piso);
        dire.setDepartamento(departamento);
        dire.setLocalidad(loca);
        
        legajo.setPropietario(propietario);
        
        obj.setSexo(sexo);
        obj.setNombre(nombre.toUpperCase());
        obj.setApellido(apellido.toUpperCase());
        obj.setDni(dni);
        obj.setFechaNacimiento(nacimiento);
        obj.setDireccion(dire);
        obj.setLegajo(legajo);
        obj.setTelefono(telefono);
        
        
        container.persistIfNotAlready(obj);
        return obj;
    }
    
    // Validar atributos dni y fechaNacimiento
    public String validateCreate(String nombre, String apellido, E_sexo sexo, int dni, LocalDate nacimiento, E_nacionalidad nacionalidad,
	   		 E_localidades localidad, String calle, int numero, String piso, String departamento, String telefono){	
    	List<Alumno> dniAlumno = container.allMatches((new QueryDefault<Alumno>(Alumno.class, "findByDni", "dni", dni)));
    	if(!dniAlumno.isEmpty()){					
			return "El número de dni ya existe";			
		}
    	if (nacimiento.isAfter(LocalDate.now())){		
			return "La fecha de nacimiento debe ser menor al día actual";
		}
		return null;
			
	}
    
	
    
	// //////////////////////////////////////
    // FindByDni (action)
    // //////////////////////////////////////
	
	@MemberOrder(sequence = "2")
    @Named ("Listar por DNI")
    public List<Alumno> ListByDni(
            final @Named("DNI") int dni){
		
		return listByDni(dni);
		
	}
	
	
	@Programmatic
    public List<Alumno> listByDni(int dni) {
        return container.allMatches(
            new QueryDefault<Alumno>(Alumno.class, 
                    "findByDni", 
                    "dni", dni));
    }
	
		
	@Programmatic
	public List<Alumno> queryListarAlumnosDeUnCurso(final int anio, final String division) {
		return container.allMatches(new QueryDefault<Alumno>(Alumno.class,
				"alumnosDeUnCurso", "anio", anio, "division", division));
	}
	

	@Hidden(where = Where.OBJECT_FORMS)    
    @ActionSemantics(Of.NON_IDEMPOTENT)
    @MemberOrder(sequence = "3")
    @Named("Eliminar")    
    public String removeAlumno(@Named("Eliminar: ") Alumno delAlumno) {
    		String remAlumno = delAlumno.title();			
			container.removeIfNotAlready(delAlumno);			
			return  remAlumno + " fue eliminado";
			
	}
	
    // //////////////////////////////////////
    // Injected services
    // //////////////////////////////////////

    @javax.inject.Inject
	DomainObjectContainer container;
}
