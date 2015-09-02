import com.backendless.Backendless;
import com.backendless.DeviceRegistration;
import com.backendless.Subscription;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.messaging.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by backendlessdev on 8/31/15.
 */
public class MessagingService
{
  @Before
  public void init()
  {
    Backendless.setUrl( "http://localhost:9000" );
    Backendless.initApp( "A92906D6-6B36-FAD1-FFE0-718BFD7CAE00", "37E7561F-9BAE-9EAC-FFDB-0735D9F00300", "v1" );
  }

  @Test
  public void testSubscribe() throws Throwable
  {

    AsyncCallback<List<Message>> subscriptionResponder = new AsyncCallback<List<Message>>()
    {
      public void handleResponse( List<Message> response )
      {
      }

      public void handleFault( BackendlessFault fault )
      {
      }
    };

    SubscriptionOptions subscriptionOptions = new SubscriptionOptions();
    subscriptionOptions.setSelector( "city='Tokyo'" );

    Subscription subscription = Backendless.Messaging.subscribe( subscriptionResponder, subscriptionOptions );
    System.out.println( subscription.getChannelName().toString() );
  }

  @Test
  public void testPoll() throws Throwable
  {


  }

  @Test
  public void testCancel() throws Throwable
  {

//    try
//    {
//      Backendless.Messaging.unregisterDevice();
//    }
//    catch( BackendlessException exception )
//    {
//      // either device is not registered or an error occurred during de-registration
//    }

    DeliveryOptions deliveryOptions = new DeliveryOptions();
    Date publishDate = new Date( System.currentTimeMillis() + 20000 ); // add 20 seconds
    deliveryOptions.setPublishAt( publishDate );

    MessageStatus status = Backendless.Messaging.publish( "Test Message", null, deliveryOptions );
    boolean result = Backendless.Messaging.cancel( status.getMessageId() );
    System.out.println( result );
    Assert.assertTrue( result );
  }

  @Test
  public void testDeviceRegistration() throws Throwable
  {

    DeviceRegistration devReg = Backendless.Messaging.getRegistrations();
  }

  @Test
  public void testPublish() throws Throwable
  {

    MessageStatus status = Backendless.Messaging.publish( "['><script>]" );
    System.out.println( status );

    PublishOptions publishOptions = new PublishOptions();
    publishOptions.putHeader( "city", "Tokyo" );
    Contact weather = new Contact();
    weather.setAddress( null );
    weather.setName( "80" );
    MessageStatus status1 = Backendless.Messaging.publish( weather, publishOptions );
    System.out.println( status1 );

    PublishOptions publishOptions1 = new PublishOptions();
    publishOptions1.setSubtopic( "news" );
    PhoneBook test = new PhoneBook();

    HashMap<Object, Object> test2 = new HashMap<Object, Object>();
    test2.put( "1", "1" );

    MessageStatus status2 = Backendless.Messaging.publish( (Object) test2, publishOptions1 );
    System.out.println( status2 );

    DeliveryOptions deliveryOptions3 = new DeliveryOptions();
    deliveryOptions3.setPushPolicy( PushPolicyEnum.ONLY );
    deliveryOptions3.setPushBroadcast( PushBroadcastMask.ALL );
    MessageStatus status4 = Backendless.Messaging.publish( "Hi Devices!", null, deliveryOptions3 );
    System.out.println( status4 );
  }

  @Test
  public void testPublish2() throws Throwable
  {

    DeliveryOptions deliveryOptions = new DeliveryOptions();
    deliveryOptions.setPushPolicy( PushPolicyEnum.ALSO );
    deliveryOptions.setPushBroadcast( PushBroadcastMask.ANDROID );

    PublishOptions publishOptions = new PublishOptions();
    publishOptions.putHeader( "android-ticker-text", "You just got a push notification!" );
    publishOptions.putHeader( "android-content-title", "This is a notification title" );
    publishOptions.putHeader( "android-content-text", "Push Notifications are cool" );

    MessageStatus status = Backendless.Messaging.publish( "Hi Devices!", publishOptions, deliveryOptions );
    System.out.println( status );

    DeliveryOptions deliveryOptions1 = new DeliveryOptions();
    deliveryOptions1.setPushPolicy( PushPolicyEnum.ONLY );
    deliveryOptions1.addPushSinglecast( "sdsdds" );

    PublishOptions publishOptions1 = new PublishOptions();
    publishOptions1.putHeader( "android-ticker-text", "You just got a private push notification!" );
    publishOptions1.putHeader( "android-content-title", "This is a notification title" );
    publishOptions1.putHeader( "android-content-text", "Push Notifications are cool" );

    MessageStatus status2 = Backendless.Messaging.publish( "this is a private message!", publishOptions1, deliveryOptions1 );
    System.out.println( status2 );
  }

  @Test
  public void testPublish3() throws Throwable
  {

    DeliveryOptions deliveryOptions = new DeliveryOptions();
    Date publishDate = new Date( System.currentTimeMillis() + 20000 ); // add 20 seconds
    deliveryOptions.setPublishAt( publishDate );

    MessageStatus status = Backendless.Messaging.publish( "This message was scheduled 20 sec ago", null, deliveryOptions );
    System.out.println( status );

    DeliveryOptions deliveryOptions1 = new DeliveryOptions();
    deliveryOptions1.setRepeatEvery( 20 ); // the message will be delivered every 20 seconds
    deliveryOptions1.setRepeatExpiresAt( new Date( System.currentTimeMillis() + 60000 ) );

    MessageStatus status1 = Backendless.Messaging.publish( "This message was scheduled 20 sec ago", null, deliveryOptions );
    System.out.println( status1 );
  }
}
