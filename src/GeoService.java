import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.geo.BackendlessGeoQuery;
import com.backendless.geo.GeoCategory;
import com.backendless.geo.GeoPoint;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by backendlessdev on 8/31/15.
 */
public class GeoService
{
  @Before
  public void init()
  {
    Backendless.setUrl( "http://localhost:9000" );
    Backendless.initApp( "A92906D6-6B36-FAD1-FFE0-718BFD7CAE00", "37E7561F-9BAE-9EAC-FFDB-0735D9F00300", "v1" );
  }

  @Test
  public void testAddCategory() throws Throwable
  {

  GeoCategory cat = Backendless.Geo.addCategory( "mycategory");
    System.out.println( cat );


  }

  @Test
  public void testAddPoint() throws Throwable
  {

    Contact contact = new Contact();
    contact.setAge( 1 );

    List<String> categories = new ArrayList<String>();
    categories.add( "restaurants" );
    categories.add( "cool_places" );

//    Map<Object, Object> meta = new HashMap<Object, Object>();
//    meta.put( "name", "Eatzi's" );

    Map<Object, Object> meta1 = new HashMap<Object, Object>();
    meta1.put( "name", contact );

    GeoPoint geopoint = new GeoPoint();
    geopoint.setDistance( 13.3232 );
    geopoint.setLatitude( 1.323232 );
    geopoint.setLongitude( 32 );
    geopoint.putMetadata( "test", contact );

    //Backendless.Geo.savePoint( 32.81, -96.80, categories, meta );

   // Backendless.Geo.savePoint( 32.81, -96.80, categories);

   // Backendless.Geo.savePoint( 32.81, -96.80);

    GeoPoint point = Backendless.Geo.savePoint( geopoint);
    System.out.println( point );

    GeoPoint point1 = Backendless.Geo.savePoint( geopoint);
    System.out.println( point1 );

    //  Backendless.Geo.addCategory( "mycategory", new AsyncCallback<GeoCategory>()
  }

  @Test
  public void testRelativeFind() throws Throwable
  {

    //  Backendless.Geo.addCategory( "mycategory", new AsyncCallback<GeoCategory>()

  }

  @Test
  public void testRemovePoint() throws Throwable
  {

    GeoPoint geoPoint = new GeoPoint( -31.96, 115.84 );
    GeoPoint savedGeoPoint = Backendless.Geo.savePoint( geoPoint );
    try
    {
      Backendless.Geo.removePoint( savedGeoPoint );
    } catch (Throwable t) {
      System.out.println( t );
    }


  }

  @Test
  public void testDeleteCategory() throws Throwable
  {

    Backendless.Geo.addCategory( "mycategory");
    try
    {
      Backendless.Geo.deleteCategory( "mycategory" );
    } catch (Throwable t) {
      System.out.println( t );
    }



  }

  @Test
  public void testUpdatePoint() throws Throwable {


    Contact contact = new Contact();
    contact.setAge( 1 );

    List<String> categories = new ArrayList<String>();
    categories.add( "restaurants" );
    categories.add( "cool_places" );

    Map<Object, Object> meta1 = new HashMap<Object, Object>();
    meta1.put( "name", contact );

    GeoPoint geopoint = new GeoPoint();
    geopoint.setDistance( 13.3232 );
    geopoint.setLatitude( 1.323232 );
    geopoint.setLongitude( 32 );
    geopoint.putMetadata( "test", contact );

    GeoPoint point = Backendless.Geo.savePoint( geopoint );
    System.out.println( point );

    point.setLatitude( 10 );
    GeoPoint point1 = Backendless.Geo.savePoint( point );
    System.out.println( point + " " + point1.getLatitude().toString());


  }


  @Test
  public void testGetCategories() throws Throwable {

    Backendless.Geo.getCategories();
  }


  @Test
  public void testGetPoints() throws Throwable {

    BackendlessGeoQuery geoQuery = new BackendlessGeoQuery();
    geoQuery.addCategory( "Restaurants" );
    BackendlessCollection<GeoPoint> geoPoints = Backendless.Geo.getPoints( geoQuery );
    System.out.println( geoPoints );
    geoPoints.getData();

  }




}
