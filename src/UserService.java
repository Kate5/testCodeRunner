import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.property.ObjectProperty;
import com.backendless.property.UserProperty;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by backendlessdev on 8/31/15.
 */
public class UserService
{

  @Before
  public void init()
  {
    Backendless.setUrl( "http://localhost:9000" );
    Backendless.initApp( "A92906D6-6B36-FAD1-FFE0-718BFD7CAE00", "37E7561F-9BAE-9EAC-FFDB-0735D9F00300", "v1" );
  }

  @Test
  public void testRegister() throws Throwable
  {

    String email = "foo@foo" + System.currentTimeMillis() + ".com";
    String password = "1";

    BackendlessUser user = new BackendlessUser();
    user.setEmail( email );
    user.setPassword( password );

    try
    {
      BackendlessUser u = Backendless.UserService.register( user );
      System.out.println( u );
    } catch (Throwable e) {

      System.out.println(e);
      Assert.assertNull( e );
    }
  }

  @Test
  public void testLogin() throws Throwable
  {

    String email = "foo@foo" + System.currentTimeMillis() + ".com";
    String password = "1";

    BackendlessUser user = new BackendlessUser();
    user.setEmail( email );
    user.setPassword( password );

    BackendlessUser user0 = Backendless.UserService.register( user );
    System.out.println( user0 );

    BackendlessUser user1 = Backendless.UserService.login( email, password );
    System.out.println( user1 );
  }

  @Test
  public void testUpdate() throws Throwable
  {

    String email = "foo@foo" + System.currentTimeMillis() + ".com";
    String password = "1";

    BackendlessUser user = new BackendlessUser();
    user.setEmail( email );
    user.setPassword( password );

    BackendlessUser user0 = Backendless.UserService.register( user );
    System.out.println( user0 );

    try
    {
      user0.setEmail( "updated@foo" + System.currentTimeMillis() + ".com" );
      BackendlessUser user2 = Backendless.UserService.update( user0 );
      System.out.println( user2 );
    }
    catch( Throwable e )
    {
      System.out.println( e );
    }
  }

  @Test
  public void testRemove() throws Throwable
  {

    String email = "foo@foo" + System.currentTimeMillis() + ".com";
    String password = "1";

    BackendlessUser user = new BackendlessUser();
    user.setEmail( email );
    user.setPassword( password );

    BackendlessUser user1 = Backendless.UserService.register( user );
    System.out.println( user1 );

    Long l = Backendless.Persistence.of( BackendlessUser.class ).remove( user1 );
    System.out.println( l );

    // test with Users.class
//    Long l = Backendless.Persistence.of( BackendlessUser.class ).remove( user1 );
//    System.out.println(l);

  }

  @Test
  public void testRestorePassword() throws Throwable
  {

    String email = "foo@foo" + System.currentTimeMillis() + ".com";
    String password = "1";

    BackendlessUser user = new BackendlessUser();
    user.setEmail( email );
    user.setPassword( password );

    BackendlessUser user1 = Backendless.UserService.register( user );
    System.out.println( user1 );

    try
    {
      Backendless.UserService.restorePassword( email );
    } catch (Throwable t) {
      System.out.println( t );
    }



  }

  @Test
  public void testLogout() throws Throwable
  {

    String email = "foo@foo" + System.currentTimeMillis() + ".com";
    String password = "1";

    BackendlessUser user = new BackendlessUser();
    user.setEmail( email );
    user.setPassword( password );

    BackendlessUser user0 = Backendless.UserService.register( user );
    System.out.println( user0 );

    BackendlessUser user1 = Backendless.UserService.login( email, password );
    System.out.println( user1 );

    Backendless.UserService.logout();

  }

  @Test
  public void testFind() throws Throwable
  {
    BackendlessCollection<BackendlessUser> u1 = Backendless.Persistence.of( BackendlessUser.class ).find();

    System.out.println( u1.getPage( 100, 0 ) );

    BackendlessCollection<Users> u2 = Backendless.Persistence.of( Users.class ).find();
    System.out.println( u2.getPage( 100, 0 ) );

  }

  @Test
  public void testDescribe() throws Throwable {

    List<UserProperty> props = Backendless.UserService.describeUserClass();
    System.out.println( "test properties 1" );

    System.out.println( props.get( 0 ) );

    List<ObjectProperty> props1 = Backendless.Persistence.describe( "Users" );
    System.out.println( "test properties 2" );
    System.out.println( props1 );

  }

  @Test
  public void testFindById() throws Throwable
  {

    String email = "foo@foo" + System.currentTimeMillis() + ".com";
    String password = "888";

    BackendlessUser user = new BackendlessUser();
    user.setEmail( email );
    user.setPassword( password );

    BackendlessUser user0 = Backendless.UserService.register( user );
    System.out.println("test with find user");
    System.out.println( user0 );

    BackendlessUser user3 = Backendless.UserService.findById( user0.getObjectId() );
    System.out.println( user3 );

  }

  @Test
  public void testUpdateBulk() throws Throwable
  {

  }

  @Test
  public void testRemoveBulk() throws Throwable
  {

  }
}
