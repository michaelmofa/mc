package test;

public class Test {

	public static void main(String[] args) {
		System.out.println("开始....");
		Test t = new Test();
		AABB aa = t.new A();
		
		aa.print();
		System.out.println("结束....");
	}
	
	interface AABB{
		public void print();
	}
	
	class A implements AABB{
		public String getA(){
			return "A";
		}

		@Override
		public void print() {
			System.out.println("AAAA");
		}
	}
	
	class B implements AABB{
		public String getB(){
			return "B";
		}

		@Override
		public void print() {
			System.out.println("BBBB");
		}
	}

}
