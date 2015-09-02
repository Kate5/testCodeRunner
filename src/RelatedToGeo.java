import com.backendless.geo.GeoPoint;

import java.util.Date;

/**
 * Created by backendlessdev on 9/2/15.
 */
public class RelatedToGeo
{
  private String objectId;
  private String name;
  private int age;
  private String phone;
  private String title;
  private Address address;
  private Date updated;
  private GeoPoint geopoint;

  public String getObjectId()
  {
    return objectId;
  }

  public void setObjectId( String objectId )
  {
    this.objectId = objectId;
  }

  public String getName()
  {
    return name;
  }

  public void setName( String name )
  {
    this.name = name;
  }

  public int getAge()
  {
    return age;
  }

  public void setAge( int age )
  {
    this.age = age;
  }

  public String getPhone()
  {
    return phone;
  }

  public void setPhone( String phone )
  {
    this.phone = phone;
  }

  public String getTitle()
  {
    return title;
  }

  public void setTitle( String title )
  {
    this.title = title;
  }

  public Address getAddress()
  {
    return address;
  }

  public void setAddress( Address address )
  {
    this.address = address;
  }

  public Date getUpdated()
  {
    return updated;
  }

  public void setUpdated( Date updated )
  {
    this.updated = updated;
  }

  public GeoPoint getGeopoint()
  {
    return geopoint;
  }

  public void setGeopoint( GeoPoint geopoint )
  {
    this.geopoint = geopoint;
  }
}
