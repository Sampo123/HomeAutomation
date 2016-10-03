package homeautomation.sensors;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.service.GpioService;

import homeautomation.Location;

public class TemperatureSensor {
	
	private Location location;
	
	public TemperatureSensor(Location location) {
		this.location = location;
		
	}
	
	
	/**
	 * Reads temperature of the sensor.
	 * 
	 * @return temperature read by the sensor (as float) in Celcius.
	 */
	public float readTemperature() {
		GpioController controller = GpioFactory.getInstance();
		GpioPinDigitalOutput output = controller.provisionDigitalOutputPin(null);
		
		
		
		
		return 0f;
	}
	
	
}
