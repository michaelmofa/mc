package thread._01;

public class GateTest {
	public static void main(String[] args) {
		Gate gate = new Gate();
		new UserThread(gate, "a123", "a一二三").start();
		new UserThread(gate, "b456", "b四五六").start();
		new UserThread(gate, "c789", "c七八九").start();
	}
}

class Gate {
	private long count;
	private String name;
	private String address;
	private MyThreadLocal myl;
	
	public Gate() {
		myl = MyThreadLocal.getInstance();
	}

	public void pass(String name, String address) {
		count++;
		myl.addName(name);
		myl.addAddress(address);
//		this.name = myl.getName();
//		this.address = myl.getAddress();
		check();
	}

	public void check() {
		if (myl.getName().charAt(0) != myl.getAddress().charAt(0)) {
			 System.out.println("****** BROKEN ******"+toString());
		}
	}

	@Override
	public String toString() {
		return "Gate [count=" + count + ", name=" + name + ", address=" + address + "]";
	}
}

class UserThread extends Thread {
	private final Gate gate;
	private final String name;
	private final String address;

	public UserThread(Gate gate, String name, String address) {
		this.gate = gate;
		this.name = name;
		this.address = address;
	}

	@Override
	public void run() {
		System.out.println(name + ":begin...");
		while (true) {
			gate.pass(name, address);
		}
	}

}


class MyThreadLocal{
	private static MyThreadLocal myThreadLocal = new MyThreadLocal();
	private ThreadLocal<String> names = new ThreadLocal<String>();
	private ThreadLocal<String> addreses = new ThreadLocal<String>();
	private MyThreadLocal(){}
	public static MyThreadLocal getInstance(){
		if(myThreadLocal==null){
			myThreadLocal = new MyThreadLocal();
		}
		return myThreadLocal;
	}
	
	public void addName(String name){
		names.set(name);
	}
	
	public String getName(){
		return names.get();
	}
	
	public void addAddress(String address){
		addreses.set(address);
	}
	
	public String getAddress(){
		return addreses.get();
	}
}
