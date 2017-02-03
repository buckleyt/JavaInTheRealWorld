
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.regex.*;

public class Component {

	private LinkedList<Double> numbers;
	private LinkedList<Double> function;
	private char variable;

	public Component(String str) {
		numbers = new LinkedList<>();
		function = new LinkedList<>();
		for (int i = 1; i < str.length() - 1; i += 2) {
			if (str.charAt(i) == 'x') {
				variable = str.charAt(i);
				numbers.add(Double.MAX_VALUE);
				function.add((double) Component.findOperation(str.charAt(i + 1)));
			} else {
				ArrayList<Double> chars = new ArrayList<>();
				chars.add((double) Character.getNumericValue(str.charAt(i)));
				while (isNumber(str.charAt(i + 1)) || str.charAt(i + 1) == '.') {
					if (str.charAt(i + 1) == '.') {
						chars.add(Double.MAX_VALUE);
						str = str.substring(0, i + 1) + str.substring(i + 2);
					}
					if (isNumber(str.charAt(i + 1))) {
						chars.add((double) Character.getNumericValue(str.charAt(i + 1)));
						str = str.substring(0, i + 1) + str.substring(i + 2);
					}
				}	
				numbers.add(makeDouble(chars));
				function.add((double) Component.findOperation(str.charAt(i + 1)));
			}
		}
	}

	/**
	 * Returns 1 if the operation is addition, 2 if subtraction, 3 if
	 * multiplication, 4 if division, and 5 if to the power of (^). Returns 0 if
	 * the character is a ')'
	 */
	public static int findOperation(char c) {
		if (c == '+') {
			return 1;
		} else if (c == '-') {
			return 2;
		} else if (c == '*') {
			return 3;
		} else if (c == '/') {
			return 4;
		} else if (c == '^') {
			return 5;
		}
		return 0;
	}

	public static char giveOperation(double i) {
		if (i == 1) {
			return '+';
		} else if (i == 2) {
			return '-';
		} else if (i == 3) {
			return '*';
		} else if (i == 4) {
			return '/';
		} else if (i == 5) {
			return '^';
		}
		return '0';
	}

	public String toString() {
		String str = "(";
		for (int i = 0; i < function.size(); i++) {
			if (numbers.get(i) == Double.MAX_VALUE) {
				str += "x";
			} else {
				str += numbers.get(i);
			}
			if (Component.giveOperation(function.get(i)) != '0') {
				str += Component.giveOperation(function.get(i));
			}
		}
		str += ")";
		return str;
	}

	public boolean isNumber(char c) {
		if (Character.isDigit(c) || c == '.') {
			return true;
		} else {
			return false;
		}
	}
	
	public double makeDouble(ArrayList<Double> a) {
		double d = 0;
		int i = 0;
		while (i < a.size()) {
			if (a.get(i) == Double.MAX_VALUE) {
				i++;
				break;
			}
			if (i != 0) {
				d *= 10;
			}
			d += a.get(i);
			i++;
		}
		double d1 = 0;
		while (i < a.size()) {
			d1 += a.get(i);
			d1 /= 10;
			i++;
		}
		d += d1;
		return d;
	}

	public double solve(double d) {
		LinkedList<Double> n = (LinkedList<Double>) numbers.clone();
		LinkedList<Double> f = (LinkedList<Double>) function.clone();
		for (int i = 0; i < n.size(); i++) {
			if (n.get(i) == Double.MAX_VALUE) {
				n.set(i, d);
			}
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
}
