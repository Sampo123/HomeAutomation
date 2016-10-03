package homeautomation.utils;

import java.util.concurrent.Semaphore;

public class Mutex {
  
  public enum Mutexes {
    
    
    CONTROLLER(1);
    
    private final Semaphore mutex;
    
    private Mutexes(int permits) {
      this.mutex = new Semaphore(permits);
    }
    
    protected Semaphore getMutex() {
      return this.mutex;
    }
  }
  
  
  public static void mute(Mutexes mutex) throws InterruptedException {
    mutex.getMutex().acquire();
  }
  
  public static void unMute(Mutexes mutex) {
    mutex.getMutex().release();
  }
  
  

}
