package playground;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class NonThreadSafeArray {
	static int asd = 0;

	public static void main(String[] args) throws InterruptedException {
		final Omg omg = new Omg();
		
		final List<Integer> l = new CopyOnWriteArrayList<>();
		
		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i < 10000; i++) {
//					synchronized (omg) {
//						omg.asd++;
//					}
//					omg.asd++;
					asd++;
					//l.add(i);
				}
			}
		};
		
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i < 10000; i++) {
//					synchronized (omg) {
//						omg.asd++;	
//					}
//					omg.asd++;
					//l.add(i);
					asd++;
				}
			}
		};
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
//		System.out.println(l.size());
//		System.out.println(omg.asd);
		System.out.println(asd);
	}

}

class Omg {
	public int asd;
}