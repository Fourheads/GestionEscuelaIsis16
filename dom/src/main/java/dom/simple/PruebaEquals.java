package dom.simple;

import java.util.HashSet;
import java.util.Set;

public class PruebaEquals {

	public static void main (String ... a)
	{
		A uno = new A();
		uno.b = 10;
		uno.z = "Hola";
		
		A otro = new A();
		otro.b = 10;
		otro.z  = "Hola";
		
		Set<A> cjto = new HashSet<A>();
		cjto.add(uno);
		cjto.add(otro);
		
		System.out.println("cant: "+ cjto.size());
	}
	
	
	private static class A
	{
		private int b;
		private String z;
		
		@Override
		public boolean equals(Object obj) {
			A that = (A) obj;
			return this.b == that.b && this.z.equals(that.z);
		}
		
		@Override
		public int hashCode() {
			return z.hashCode() * b;
		}
	}
}
