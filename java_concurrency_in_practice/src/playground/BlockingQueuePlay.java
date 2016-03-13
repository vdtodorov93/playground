package playground;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueuePlay {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> bq = new ArrayBlockingQueue<>(1);
		Producer p1 = new Producer(bq, 1);
		Producer p2 = new Producer(bq, 2);
		
		Consumer c = new Consumer(bq);
		Thread[] threads = new Thread[3];

		threads[0] = new Thread(p1);
		threads[1] = new Thread(p2);
		threads[2] = new Thread(c);
		
		for(Thread t: threads) {
			t.start();
		}
		
		for(Thread t: threads) {
			t.join();
		}
	}
}

class Consumer implements Runnable {

	private BlockingQueue<String> bq;

	public Consumer(BlockingQueue<String> bq) {
		this.bq = bq;
	}

	@Override
	public void run() {
		String line = null;
		
		try {
			line = bq.take();
			while (!line.equals("END")) {
				System.out.println(line);
				line = bq.take();
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}

class Producer implements Runnable {
	private int number;
	private BlockingQueue<String> bq;

	public Producer(BlockingQueue<String> bq, int number) {
		this.bq = bq;
		this.number = number;
	}

	@Override
	public void run() {
		String[] strings = {"asd", "12321", "fsfsd", "dfdsfds", "sdfdsfsd", "dsfsd33", "hghghgf"};
		
		try {
			for(String str: strings) {
				bq.put(number + " " + str);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}