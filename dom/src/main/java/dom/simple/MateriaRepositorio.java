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
import org.apache.isis.applib.annotation.ActionSemantics;
import org.apache.isis.applib.annotation.Bookmarkable;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.Hidden;
import org.apache.isis.applib.annotation.MaxLength;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.MultiLine;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.RegEx;
import org.apache.isis.applib.annotation.ActionSemantics.Of;
import org.apache.isis.applib.annotation.Where;

@DomainService(menuOrder = "17", repositoryFor = Materia.class)
@Named("Materias")
public class MateriaRepositorio {
	
	
    // //////////////////////////////////////
    // Identification in the UI
    // //////////////////////////////////////

    public String getId() {
        return "Materia";
    }

    public String iconName() {
        return "SimpleObject";
    }
    
 // //////////////////////////////////////
    // List (action)
    // //////////////////////////////////////
    @Bookmarkable
    @ActionSemantics(Of.SAFE)
    @MemberOrder(sequence = "1.1")
    @Named ("Listar Materias")
    public List<Materia> complete() {
        return container.allInstances(Materia.class);
    }
    

        // //////////////////////////////////////
        // Create (action)
        // //////////////////////////////////////
          
        
        @MemberOrder(sequence = "1.0")
        @Named ("Crear Materia")
        public Materia create(
                final @Named("Nombre") String Nombre,
                final @Named("Año") @RegEx(validation = "/d{1,1}") int anio,
                final @MaxLength(2048) @MultiLine @Named("Programa") String Programa
                )
        {
        	final Materia obj = container.newTransientInstance(Materia.class);
            obj.setNombre(Nombre);
            obj.setPrograma(Programa);
        	
            container.persistIfNotAlready(obj);
            return obj;
        }
      
        
        //region > remove User (action)
        // //////////////////////////////////////
        
      
        @ActionSemantics(Of.NON_IDEMPOTENT)
        @MemberOrder(sequence = "1.6")
        @Named("Borrar Materia")
        public String removeMateria(@Named("Materia") Materia mate) {
    			container.remove(mate);
    			return "La materia " + mate.getNombre()+ " fue borrada con exito";
    	}
        
        
        //endregion
      
        //region > injected services
        // //////////////////////////////////////
        
        // //////////////////////////////////////
        // Injected services
        // //////////////////////////////////////

        @javax.inject.Inject 
        DomainObjectContainer container;
    }

