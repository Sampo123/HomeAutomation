package homeautomation.services;

import java.util.HashSet;
import java.util.Set;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class PinReservationService {
  
  private final Set<Pin> reservedPins;
  private final GpioController gpio = GpioFactory.getInstance();

  // provision gpio pin #01 as an output pin and turn on
 
  
  public PinReservationService() {
    this.reservedPins = new HashSet<Pin>();
  }
  
  public synchronized Pin reservePin(int number) {
    Pin pin = RaspiPin.getPinByAddress(number);
    if(this.reservedPins.contains(pin)) {
      return null;
    }
    else {
      this.reservedPins.add(pin);
      return pin;
    }

  }
  
  public synchronized GpioPinDigitalOutput getDigitalOutput(Pin pin, PinState state) {
   return gpio.provisionDigitalOutputPin(pin ,state);
  }
  
  

}
