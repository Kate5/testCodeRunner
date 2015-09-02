import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.property.ObjectProperty;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by backendlessdev on 8/31/15.
 */
public class DataService
{

  @Before
  public void init()
  {
    Backendless.setUrl( "http://localhost:9000" );
    Backendless.initApp( "A92906D6-6B36-FAD1-FFE0-718BFD7CAE00", "37E7561F-9BAE-9EAC-FFDB-0735D9F00300", "v1" );
  }

  @Test
  public void testCreate() throws Throwable
  {
    Contact contact = new Contact();
    contact.setName( "test" );

    try
    {

      Backendless.Persistence.save( contact );
      Backendless.Persistence.of( Contact.class ).save( contact );

      Backendless.Data.save( contact );
      Backendless.Data.of( Contact.class ).save( contact );
    } catch ( Throwable e ) {
      System.out.println( e );
      Assert.assertNull( "", e );
    }

  }



  @Test
  public void testCreate2() throws Throwable
  {
    RelatedToGeo toGeo = new RelatedToGeo();
    try
    {
      Backendless.Persistence.save( toGeo );
      Backendless.Persistence.of( RelatedToGeo.class ).save( toGeo );

      Backendless.Data.save( toGeo );
      Backendless.Data.of( RelatedToGeo.class ).save( toGeo );
    } catch ( Throwable e ) {
      System.out.println( e );
      Assert.assertNull( "", e );
    }
  }

  @Test
  public void testFindById() throws Throwable
  {
    Contact contact = new Contact();
    contact.setName( "test" );

    try
    {

      Contact contact1 = Backendless.Persistence.save( contact );

      Contact contact2 = Backendless.Persistence.of( Contact.class ).findById( contact1 );
      System.out.println( contact2 );
    } catch ( Throwable e ) {
      System.out.println( e );
      Assert.assertNull( "", e );
    }
  }

  @Test
  public void testLoadRelations() throws Throwable
  {

    Address address = new Address();
    address.setStreet( "TN 55" );
    address.setCity( "Lynchburg" );
    address.setState( "Tennessee" );

    Contact owner = new Contact();
    owner.setName( "Jack Daniels" );
    owner.setAge( 147 );
    owner.setPhone( "777-777-777" );
    owner.setTitle( "Favorites" );
    owner.setAddress( address );

    PhoneBook phoneBook = new PhoneBook();
    phoneBook.setOwner( owner );

    PhoneBook savedPhoneBook = Backendless.Persistence.save( phoneBook );

    PhoneBook firstPhoneBook = Backendless.Data.of( PhoneBook.class ).findFirst();
    ArrayList<String> relationProps = new ArrayList<String>();
    relationProps.add( "owner" );
    relationProps.add( "owner.address" );
    try
    {
      Backendless.Data.of( PhoneBook.class ).loadRelations( firstPhoneBook, relationProps );
      System.out.println( firstPhoneBook );
      System.out.println( firstPhoneBook.getObjectId() );
      System.out.println( firstPhoneBook.getOwner() );
    } catch ( Throwable e ) {
      System.out.println( e );
      Assert.assertNull( "", e );
    }

//
//    List test;
//    test.add( 0 , "ss" );

//    Backendless.Persistence.of( Contact.class ).loadRelations( contact1, "props" );

  }

  @Test
  public void testRemove() throws Throwable
  {

    Contact contact = new Contact();
    contact.setName( "1" );
    Contact c = Backendless.Persistence.of( Contact.class ).save( contact );

    try
    {
      Long l = Backendless.Persistence.of( Contact.class ).remove( c );
      System.out.println( l );
    }
    catch( Throwable t )
    {
      Assert.assertNull( "", t );
      System.out.println( t );
    }
  }

  @Test
  public void testUpdate() throws Throwable
  {
    Contact contact = new Contact();
    contact.setName( "Jack Daniels" );
    contact.setAge( 147 );
    contact.setPhone( "777-777-777" );
    contact.setTitle( "Favorites" );

    Contact savedContact = Backendless.Persistence.save( contact );

    // now update the saved object
    savedContact.setTitle( "Most favorite" );
    savedContact.setPhone( "666-666-666" );
    Contact contact1 = Backendless.Persistence.save( savedContact );
    System.out.println( contact1.getTitle() );
    Assert.assertEquals( "Most favorite", contact1.getTitle().toString() );
  }

  @Test
  public void testDescribe() throws Throwable
  {

    Contact contact = new Contact();
    contact.setName( "Jack Daniels" );
    contact.setAge( 147 );
    contact.setPhone( "777-777-777" );
    contact.setTitle( "Favorites" );

    Contact savedContact = Backendless.Persistence.save( contact );
    try
    {
      List<ObjectProperty> contactsSchema = Backendless.Persistence.describe( "Contact" );
      System.out.println( contactsSchema );
    } catch (Throwable e ) {
      System.out.println( e );
      Assert.assertNull( "", e );
    }
  }

  @Test
  public void testFind() throws Throwable
  {

    Contact contact = new Contact();
    contact.setName( "Jack Daniels" );
    contact.setAge( 147 );
    contact.setPhone( "777-777-777" );
    contact.setTitle( "Favorites" );

    Contact savedContact = Backendless.Persistence.save( contact );
    try
    {
      BackendlessCollection<Contact> cont = Backendless.Persistence.of( Contact.class ).find();
      System.out.println( cont );
    } catch ( Throwable e ) {
      System.out.println( e );
    }

  }

  @Test
  public void testFirst() throws Throwable
  {
    Contact contact = new Contact();
    contact.setName( "Jack Daniels" );
    contact.setAge( 147 );
    contact.setPhone( "777-777-777" );
    contact.setTitle( "Favorites" );

    Contact savedContact = Backendless.Persistence.save( contact );
    try
    {
      Contact cont = Backendless.Persistence.of( Contact.class ).findFirst();
      System.out.println( cont );
    } catch ( Throwable e ) {
      System.out.println( e );
    }


  }

  @Test
  public void testLast() throws Throwable
  {

    Contact contact = new Contact();
    contact.setName( "Jack Daniels" );
    contact.setAge( 147 );
    contact.setPhone( "777-777-777" );
    contact.setTitle( "Favorites" );

    Contact savedContact = Backendless.Persistence.save( contact );
    try
    {
      Contact cont = Backendless.Persistence.of( Contact.class ).findLast();
      System.out.println( cont );
    } catch ( Throwable e ) {
      System.out.println( e );
    }

  }



  @Test
  public void testUpdateBulk() throws Throwable
  {

  }

  @Test
  public void testRemoveBulk() throws Throwable
  {

  }


/// tests for phone book

  @Test
  public void testCreatePhoneBook() throws Throwable
  {
    PhoneBook phoneBook = new PhoneBook();
    try
    {
      Backendless.Persistence.save( phoneBook );
      Backendless.Persistence.of( PhoneBook.class ).save( phoneBook );

      Backendless.Data.save( phoneBook );
      Backendless.Data.of( PhoneBook.class ).save( phoneBook );
    } catch ( Throwable e ) {
      System.out.println( e );
      Assert.assertNull( "", e );
    }
  }


  @Test
  public void testFindByIdPhoneBook() throws Throwable
  {
    PhoneBook phoneBook = new PhoneBook();
    try
    {
      PhoneBook phoneBook1 = Backendless.Persistence.save( phoneBook );
      PhoneBook phoneBook2 = Backendless.Persistence.of( PhoneBook.class ).findById( phoneBook1 );
      System.out.println( phoneBook2 );
    } catch ( Throwable e ) {
      System.out.println( e );
      Assert.assertNull( "", e );
    }
  }

  @Test
  public void testLoadRelationsPhoneBook() throws Throwable
  {
    Address address = new Address();
    address.setStreet( "TN 55" );
    address.setCity( "Lynchburg" );
    address.setState( "Tennessee" );

    Contact owner = new Contact();
    owner.setName( "Jack Daniels" );
    owner.setAge( 147 );
    owner.setPhone( "777-777-777" );
    owner.setTitle( "Favorites" );
    owner.setAddress( address );

    PhoneBook phoneBook = new PhoneBook();
    phoneBook.setOwner( owner );

    PhoneBook savedPhoneBook = Backendless.Persistence.save( phoneBook );

    PhoneBook firstPhoneBook = Backendless.Data.of( PhoneBook.class ).findFirst();
    ArrayList<String> relationProps = new ArrayList<String>();
    relationProps.add( "owner" );
    relationProps.add( "owner.address" );
    try
    {
      Backendless.Data.of( PhoneBook.class ).loadRelations( firstPhoneBook, relationProps );
      System.out.println( firstPhoneBook );
      System.out.println( firstPhoneBook.getObjectId() );
      System.out.println( firstPhoneBook.getOwner() );
    } catch ( Throwable e ) {
      System.out.println( e );
      Assert.assertNull( "", e );
    }

  }

  @Test
  public void testRemovePhoneBook() throws Throwable
  {

    PhoneBook phoneBook = new PhoneBook();
    phoneBook.setProperty( "1" );
    PhoneBook c = Backendless.Persistence.of( PhoneBook.class ).save( phoneBook );

    try
    {
      Long l = Backendless.Persistence.of( PhoneBook.class ).remove( c );
      System.out.println( l );
    }
    catch( Throwable t )
    {
      Assert.assertNull( "", t );
      System.out.println( t );
    }
  }

  @Test
  public void testUpdatePhoneBook() throws Throwable
  {
    PhoneBook phoneBook = new PhoneBook();
    phoneBook.setProperty( "Jack Daniels" );


    PhoneBook savedPhone = Backendless.Persistence.save( phoneBook );

    // now update the saved object
    savedPhone.setProperty( "Most favorite" );
    PhoneBook updated = Backendless.Persistence.save( savedPhone );
    System.out.println( updated.getProperty() );
    Assert.assertEquals( "Most favorite", updated.getProperty().toString() );
  }

  @Test
  public void testDescribePhoneBook() throws Throwable
  {

    PhoneBook phoneBook = new PhoneBook();
    phoneBook.setProperty( "Jack Daniels" );


    PhoneBook savedPhone = Backendless.Persistence.save( phoneBook );
    try
    {
      List<ObjectProperty> contactsSchema = Backendless.Persistence.describe( "PhoneBook" );
      System.out.println( contactsSchema );
    } catch (Throwable e ) {
      System.out.println( e );
      Assert.assertNull( "", e );
    }
  }

  @Test
  public void testFindPhoneBook() throws Throwable
  {

    PhoneBook phoneBook = new PhoneBook();
    phoneBook.setProperty( "Jack Daniels" );

    PhoneBook savedPhone = Backendless.Persistence.save( phoneBook );
    try
    {
      BackendlessCollection<PhoneBook> cont = Backendless.Persistence.of( PhoneBook.class ).find();
      System.out.println( cont );
    } catch ( Throwable e ) {
      System.out.println( e );
    }

  }

  @Test
  public void testFirstPhoneBook() throws Throwable
  {
    PhoneBook phoneBook = new PhoneBook();
    phoneBook.setProperty( "Jack Daniels" );

    PhoneBook savedPhone = Backendless.Persistence.save( phoneBook );
    try
    {
      PhoneBook cont = Backendless.Persistence.of( PhoneBook.class ).findFirst();
      System.out.println( cont );
    } catch ( Throwable e ) {
      System.out.println( e );
    }


  }

  @Test
  public void testLastPhoneBook() throws Throwable
  {

    Contact contact = new Contact();
    contact.setName( "Jack Daniels" );
    contact.setAge( 147 );
    contact.setPhone( "777-777-777" );
    contact.setTitle( "Favorites" );

    Contact savedContact = Backendless.Persistence.save( contact );
    try
    {
      Contact cont = Backendless.Persistence.of( Contact.class ).findLast();
      System.out.println( cont );
    } catch ( Throwable e ) {
      System.out.println( e );
    }

  }




}
