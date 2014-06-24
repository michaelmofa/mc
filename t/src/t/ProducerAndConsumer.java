package t;

import java.util.ArrayList;

public class ProducerAndConsumer {
	ArrayList<String> products = new ArrayList<String>();
	int MAX = 10;
	boolean cancel;

	synchronized void produce() {
		int size = products.size();
		if (size < MAX) {
			products.add(size + "");
			System.out.println("produce:" + size);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			notifyAll();
		}
	}

	synchronized void consume() {
		if (products.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("consume:" + products.remove(0));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private class Producer extends Thread {
		@Override
		public void run() {
			while (!cancel) {
				produce();
			}
		}
	}

	private class Consumer extends Thread {
		@Override
		public void run() {
			while (!cancel) {
				consume();
			}
		}
	}

	void cancel() {
		cancel = true;
	}

	public ProducerAndConsumer() {
		new Consumer().start();
		new Producer().start();
	}

	public static void main(String[] args) {
		// new ProducerAndConsumer();
		String key = "key";
		while(true){
			synchronized (key) {
				try {
					key.wait(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				for(int i=0;i<10;i++){
					System.out.print(i+"-");
					System.out.println();
				}
			}
		}
	}

}