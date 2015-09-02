import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by backendlessdev on 9/2/15.
 */
public class Custom
{

  @Before
  public void init()
  {
    Backendless.setUrl( "http://localhost:9000" );
    Backendless.initApp( "A92906D6-6B36-FAD1-FFE0-718BFD7CAE00", "37E7561F-9BAE-9EAC-FFDB-0735D9F00300", "v1" );
  }

  @Test
  public void testCustom() throws Throwable
  {

    HashMap args = new HashMap();
    args.put( "weather", "sunny" );

    Backendless.Events.dispatch( "test", args, new AsyncCallback<Map>()
    {
      @Override
      public void handleResponse( Map result )
      {
        System.out.println( "received result " + result );
      }

      @Override
      public void handleFault( BackendlessFault backendlessFault )
      {
        System.out.println( "got error " + backendlessFault.toString() );
      }
    } );
  }

  @Test
  public void testCustom1() throws Throwable
  {

    HashMap args = new HashMap();
    args.put( "weather", "sunny" );
    try
    {
      Backendless.Events.dispatch( "test", args );
      System.out.println();
    }
    catch( Throwable t )
    {
      System.out.println( t );
    }
  }
}
