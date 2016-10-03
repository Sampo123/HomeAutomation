package homeautomation.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

import homeautomation.Home;
import homeautomation.actors.Led;
import homeautomation.base.PinLayoutException;

public class LightningService {
  
  private final Map<String, Led> leds;
  
  
  public LightningService() {
    this.leds = new HashMap<String, Led>();
  }
  
  public synchronized void registerLed(String name, int pin) throws IllegalArgumentException, PinLayoutException {
    if(this.leds.containsKey(name)) {
      throw new IllegalArgumentException("Tried to register duplicate led!");
    }
    Pin rpin = Home.getInstance().getReservationSerice().reservePin(pin);
    if(rpin == null) {
      throw new IllegalArgumentException("Pin number " + pin + " could not be reserved");
      
    }
    else {
      // we got the PIN YAYAYA
      Led l = new Led(rpin);
      this.leds.put(name, l);
    }
  }
  
  public Map<String, Led> getRegisteredLeds() {
    return this.leds;
  }
  
  public void emitLed(String name, long time) {
    Led l = this.leds.get(name);
    synchronized (l) {
      l.emit(time);
    }
  }
  
  public void toggleLed(String name) {
    Led l = this.leds.get(name);
    synchronized (l) {
      l.toggle();
    }
    
   
  }
}
