import java.util.List;

/**
 * Created by backendlessdev on 9/1/15.
 */
public class PhoneBook
{
  private String objectId;
  private Contact owner;
  private List<Contact> contacts;

  public String getObjectId()
  {
    return objectId;
  }

  public void setObjectId( String objectId )
  {
    this.objectId = objectId;
  }

  public Contact getOwner()
  {
    return owner;
  }

  public void setOwner( Contact owner )
  {
    this.owner = owner;
  }

  public List<Contact> getContacts()
  {
    return contacts;
  }

  public void setContacts( List<Contact> contacts )
  {
    this.contacts = contacts;
  }
}
