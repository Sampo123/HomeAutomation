package homeautomation.actors;

/**
 * @author sampo
 *
 */
public class StepMotor {
	
	
	private int currentAngle;
	
	/**
	 * Rotates the current step motor for the fiven angle.
	 * 
	 * @param angle angle to rotate. angle > 0 = CV, angle < 0 = CCV
	 * @return true if successful, false is parameter is out of bounds.
	 */
	public boolean rotate(int angle) {
		return true;
	}

}
