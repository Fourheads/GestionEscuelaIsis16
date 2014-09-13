package fixture.simple;

import dom.simple.*;

import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.joda.time.LocalDate;

public class PersonalFixture extends FixtureScript {


    public PersonalFixture() {
        withDiscoverability(Discoverability.DISCOVERABLE);
    }

    @Override
    protected void execute(ExecutionContext executionContext) {

        // prereqs
        execute(new GenericTearDownFixture("Personal"), executionContext);

        int Cantidad=20;
        
        // create
        for(int x=0; x<=Cantidad;x++)
        {
        	create(GenericData.ObtenerNombre(),GenericData.ObtenerApellido() ,Persona.E_sexo.MASCULINO,GenericData.Random(10000000, 88888888),LocalDate.now(),Persona.E_nacionalidad.ARGENTINA, Localidad.E_localidades.NEUQUEN,GenericData.ObtenerCalle(), GenericData.Random(1, 9999),null,null,String.valueOf(GenericData.Random(10000000, 88888888)), executionContext);
        }
    }
    // //////////////////////////////////////

    private Personal create(final String nombre, String apellido,Persona.E_sexo sexo, int dni, LocalDate nacimiento,Persona.E_nacionalidad nacionalidad, Localidad.E_localidades localidad, String calle, int numero, String piso,String departamento,String telefono, ExecutionContext executionContext) {
        return executionContext.add(this, personal.create(nombre, apellido, sexo, dni, nacimiento, nacionalidad, localidad, calle, numero, piso, departamento, telefono));
    }

    // //////////////////////////////////////

    @javax.inject.Inject
    private PersonalRepositorio personal;

}