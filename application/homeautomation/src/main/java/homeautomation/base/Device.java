package homeautomation.base;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public abstract class Device {
	
	private final Pin[] pins;
	
	public Device(Pin... pins) throws PinLayoutException {
		this.pins = pins;
	}
	
	protected Pin[] getPins() {
		return this.pins;
	}

}
