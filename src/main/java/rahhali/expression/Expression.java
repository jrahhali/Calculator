package rahhali.expression;

public class Expression {
	private NestedExpression current;
	private NestedExpression root;

	public Expression() {
		root = new NestedExpression();
		current = root;
	}

	public void dot() {
		current.dot();
	}

	public void zero() {
		current.zero();
	}

	public void one() {
		current.one();
	}

	public void two() {
		current.two();
	}

	public void three() {
		current.three();
	}

	public void four() {
		current.four();
	}

	public void five() {
		current.five();
	}

	public void six() {
		current.six();
	}

	public void seven() {
		current.seven();
	}

	public void eight() {
		current.eight();
	}

	public void nine() {
		current.nine();
	}

	public void openBracket() {
		current = current.openBracket();
	}

	public void closeBracket() {
		current = current.closeBracket();
	}

	public void dividedBy() {
		current.dividedBy();
	}

	public void times() {
		current.times();
	}

	public void plus() {
		current.plus();
	}

	public void minus() {
		current.minus();
	}

	public String equals() {
		return root.equals();
	}
}