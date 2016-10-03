package homeautomation;

import homeautomation.services.LightningService;
import homeautomation.services.PinReservationService;

public class Home {
  
  private static Home singleton;
  
  
  public static Home getInstance() {
    if(singleton == null) {
      synchronized (Home.class) {
        if(singleton == null) {
          singleton = new Home();
        }
      }
    }
    return singleton;
  }
  
  
  private final PinReservationService reservator;
  private final LightningService ledService;
  
  public Home() {
    this.reservator = new PinReservationService();
    this.ledService = new LightningService();
  }
  
  
  public PinReservationService getReservationSerice() {
    return this.reservator;
  }
  
  public LightningService getLedService() {
    return this.ledService;
  }
	
}
