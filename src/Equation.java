import java.util.LinkedList;

public class Equation {

	private LinkedList<Component> parts;
	private LinkedList<Double> function;
	
	public Equation(String str) {
		parts = new LinkedList<>();
		function = new LinkedList<>();
		while(str.length() > 0) {
			parts.add(new Component(str.substring(0, str.indexOf(")") + 1)));
			str = str.substring(str.indexOf(")") + 1);
			if (str.length() > 0) {
				function.add((double) Component.findOperation(str.charAt(0)));
				str = str.substring(1);
			}
			System.out.println(str);
		}
	}
	
	public double solve(double d) {
		LinkedList<Component> c = (LinkedList<Component>) parts.clone();
		LinkedList<Double> f = (LinkedList<Double>) function.clone();
		LinkedList<Double> n = new LinkedList<>();
		for (Component C : c) {
			n.add(C.solve(d));
		}
		for (int i = 0; i < n.size() - 1; i++) {
			if (f.get(i) == 5) {
				n.set(i, Math.pow(n.get(i), n.remove(i + 1)));
				f.remove(i);
				i--;
			}
		}
		for (int i = 0; i < n.size() - 1; i++) {
			if (f.get(i) == 3) {
				n.set(i, n.get(i) * n.remove(i + 1));
				f.remove(i);
				i--;
			} else if (f.get(i) == 4) {
				n.set(i, n.get(i) / n.remove(i + 1));
				f.remove(i);
				i--;
			}
		}
		for (int i = 0; i < n.size() - 1; i++) {
			if (f.get(i) == 1) {
				n.set(i, n.get(i) + n.remove(i + 1));
				f.remove(i);
				i--;
			} else if (f.get(i) == 2) {
				n.set(i, n.get(i) - n.remove(i + 1));
				f.remove(i);
				i--;
			}
		}
		return n.get(0);
	}
	
	public String toString() {
		String str = "";
		for (int i = 0; i < parts.size(); i++) {
			str += parts.get(i).toString();
			if (i < parts.size() - 1 && Component.giveOperation(function.get(i)) != '0') {
				str += Component.giveOperation(function.get(i));
			}
		}
		
		return str;
	}
	
	public static void main(String[] args) {
		Equation e = new Equation("(x)");
		System.out.println(e);
		System.out.println("The value for this equation at x = .5 is: " + e.solve(.5));
		System.out.println(e);
	}
	
}
