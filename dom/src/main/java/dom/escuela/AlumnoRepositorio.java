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

package dom.escuela;

import java.util.ArrayList;
import java.util.List;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.query.QueryDefault;
import org.joda.time.LocalDate;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.annotation.ActionSemantics.Of;

import dom.escuela.Localidad.E_localidades;
import dom.escuela.Persona.E_nacionalidad;
import dom.escuela.Persona.E_sexo;
import dom.planEstudio.Plan;


///GestionEscuela

@DomainService(menuOrder = "30")
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
    @MemberOrder(sequence = "5")
    @Named ("Listar Alumnos")
    public List<Alumno> listAll() {
        return filtroAL(container.allMatches(new QueryDefault<Alumno>(Alumno.class,
				"ListarAlumnos")),'S');
    }


    // //////////////////////////////////////
    // Create (action)
    // //////////////////////////////////////
      
    
    @MemberOrder(sequence = "1")
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
            final @RegEx(validation = "/d+")@Optional @Named("Domicilio. Piso") String piso,
            final @Optional @Named("Domicilio. Departamento") String departamento,
            final @RegEx(validation = "/d+") @Optional @Named("Teléfono") String telefono) {
        
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
        obj.setHabilitado('S');
        
        container.persistIfNotAlready(obj);
        return obj;
    }
    
    // Validar atributos dni y fechaNacimiento
    public String validateCreate(String nombre, String apellido, E_sexo sexo, int dni, LocalDate nacimiento, E_nacionalidad nacionalidad,
	   		 E_localidades localidad, String calle, int numero, String piso, String departamento, String telefono){	
    	List<Alumno> dniAlumno =  filtroAL(container.allMatches((new QueryDefault<Alumno>(Alumno.class, "findByDni", "dni", dni))),'S');
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
	
	@MemberOrder(sequence = "4")
    @Named ("Buscar por DNI")
    public List<Alumno> ListByDni(
            final @Named("DNI") int dni){
		
		return filtroAL(listByDni(dni),'S');
		
	}
	
	
	@Programmatic
    public List<Alumno> listByDni(int dni) {
		return filtroAL(container.allMatches(
            new QueryDefault<Alumno>(Alumno.class, 
                    "findByDni", 
                    "dni", dni)),'S');
    }
	
		
	@Programmatic
	public List<Alumno> queryListarAlumnosDeUnCurso(final int anio, final String division) {
		return filtroAL(container.allMatches(new QueryDefault<Alumno>(Alumno.class,
				"alumnosDeUnCurso", "anio", anio, "division", division)),'S');
		
	}
	
	@Hidden(where = Where.OBJECT_FORMS)    
    @ActionSemantics(Of.NON_IDEMPOTENT)
    @MemberOrder(sequence = "2")
    @Named("Recuperar") 
	public String recoverAlumno(@Named("Recupuerar: ") Alumno delAlumno) {
		delAlumno.setHabilitado('S');
		delAlumno.setCurso(null);
		String remAlumno = delAlumno.title();						
		return  remAlumno + " fue recuperado exitosamente";
	}
	
	public List<Alumno> choices0RecoverAlumno(){
		return filtroAL(container.allMatches(new QueryDefault<Alumno>(Alumno.class,
				"ListarAlumnos")),'N');
	}

	@Hidden(where = Where.OBJECT_FORMS)    
    @ActionSemantics(Of.NON_IDEMPOTENT)
    @MemberOrder(sequence = "3")
    @Named("Eliminar")    
    public String removeAlumno(@Named("Eliminar: ") Alumno delAlumno, @Named("¿Está seguro?") Boolean seguro) {
    		
		delAlumno.setHabilitado('N');
		String remAlumno = delAlumno.title();						
		return  remAlumno + " fue eliminado";
			
	}
	
	public List<Alumno> choices0RemoveAlumno(){
		return filtroAL(container.allMatches(new QueryDefault<Alumno>(Alumno.class,
				"ListarAlumnos")),'S');
	}
	
	public String validateRemoveAlumno(Alumno delAlumno, Boolean seguro) {
		if (!seguro) {
			return "Marque en la opcion si está seguro!!! Si no lo está cancele esta opción";
		}

		return null;
	} 
	
	private List<Alumno> filtroAL(List<Alumno> Alumnos, char A)
	{
		List<Alumno> filtroAL=new ArrayList<Alumno>();
		
		for(Alumno al:Alumnos)
		{
			if(al.getHabilitado()==A)
				filtroAL.add(al);
		}
		
		return filtroAL;
	}
	
	
    // //////////////////////////////////////
    // Injected services
    // //////////////////////////////////////

    @javax.inject.Inject
	DomainObjectContainer container;
}
