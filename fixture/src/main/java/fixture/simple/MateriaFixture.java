package fixture.simple;

import dom.simple.*;


import org.apache.isis.applib.fixturescripts.FixtureScript;

public class MateriaFixture extends FixtureScript{

    public MateriaFixture() {
        withDiscoverability(Discoverability.DISCOVERABLE);
    }

    @Override
    protected void execute(ExecutionContext executionContext) {

        // prereqs
        execute(new GenericTearDownFixture("Materia"), executionContext);
        
        int Cantidad=10;
       
        // create
        for(int x=0; x<=Cantidad;x++)
        {
        	create(GenericData.ObtenerCiencia(),GenericData.Random(1, 5),"sdfjkbsdjkaeqn",executionContext);
        }

        
    }

    // //////////////////////////////////////

    private Materia create(final String Nombre,int anio,String Programa,ExecutionContext executionContext) {
        return executionContext.add(this, materia.create(Nombre, anio, Programa));
    }

    // //////////////////////////////////////

    @javax.inject.Inject
    private MateriaRepositorio materia;

}