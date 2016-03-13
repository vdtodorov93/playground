package playground;

public class Main {

	public static void main(String[] args) {
		A a = new A();
	}

}

class A {
	private B b;
	private int c;
	
	public A() {
		System.out.println("START INIT A");
		b = new B(this);
		c = 23;
		System.out.println("END INIT A");
	}

	public int getC() {
		return c;
	}
}

class B {
	private A a;
	
	public B(A a) {
		System.out.println("START INIT B");
		this.a = a;
		System.out.println(a.getC());
		System.out.println("END INIT B");
	}
	
}