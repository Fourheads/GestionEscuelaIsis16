package fixture.simple;


import org.apache.isis.applib.fixturescripts.FixtureScript;
import org.apache.isis.objectstore.jdo.applib.service.support.IsisJdoSupport;

public class GenericTearDownFixture extends FixtureScript {

	String Clase;
	
	GenericTearDownFixture(String clase)
	{
		Clase=clase;
	}
	
    @Override
    protected void execute(ExecutionContext executionContext) {
        isisJdoSupport.executeUpdate("delete from \""+Clase+"\"");
    }


    @javax.inject.Inject
    private IsisJdoSupport isisJdoSupport;

}
