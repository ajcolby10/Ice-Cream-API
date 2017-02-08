package my.application;

import java.util.Arrays;
import java.util.List;
 
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
 
@ApplicationPath("rest")
@Path("iceCreamAPI")
public class IceCreamWebService extends Application {
	
	@GET
	@Path("getFavorite")
	@Produces("application/json")
	public String getFavorite( @FormParam("name") String name ) {
		return IceCreamApp.getFavorite(name);
	}
	
	@POST
	@Path("addFavorite")
	@Produces("application/json")
	public String addFavorite( @FormParam("name") String name, @FormParam("iceCream") String iceCream ) {
		return IceCreamApp.newFavorite(name, iceCream);
	}
}