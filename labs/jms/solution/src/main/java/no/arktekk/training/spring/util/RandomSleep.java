package no.arktekk.training.spring.util;

public class RandomSleep {

	public static void delayMaxSeconds(int seconds) {
		try {
			Thread.sleep((long) (Math.random() * 1000 * seconds));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
