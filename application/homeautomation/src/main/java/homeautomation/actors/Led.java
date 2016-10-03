package homeautomation.actors;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import homeautomation.Home;
import homeautomation.base.Device;
import homeautomation.base.PinLayoutException;


public class Led extends Device {
  
  private GpioPinDigitalOutput output;
  private PinState state;

  public Led(Pin... pins) throws PinLayoutException {
    super(pins);
    if (getPins().length > 1) {
      throw new PinLayoutException("Pin count for a Led should not be more than 1!");
    }
    //FIXME move this to higher order component.
    this.state = PinState.LOW;
    this.output = Home.getInstance().getReservationSerice().getDigitalOutput(this.getPins()[0], this.state);
  }

  /**
   * Emits light from the current led for the given time.
   * 
   * <P>
   * 
   * Time is in milliseconds.
   * 
   * <P>
   * 
   * Will block all other access to this {@link Led} for the time given but returns
   * immediately.
   * 
   * @param ms time to emit in milliseconds.
   */
  public synchronized void emit(final long ms) {
    final Led that = this;
    (new Thread(new Runnable() {
      
      @Override
      public void run() {
        that.emitPinl(ms);
        
      }
    })).start();
  }
  
  private synchronized void emitPinl(long ms) {
    output.pulse(ms, true);
  }
  
  public synchronized void toggle() {
    
    this.state = this.state == PinState.LOW ? PinState.HIGH : PinState.LOW;
    // TODO use this.output.toggle();
    this.output.setState(this.state);
  }
  
  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  public String toString() {
    return "Led on pin " + this.getPins()[0].getName();
  }
}
