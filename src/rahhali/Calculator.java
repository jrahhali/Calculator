package rahhali;

public class Calculator {
	Expression expression = new Expression();
	
	public Calculator dot() {
		expression.dot();
		return this;
	}
	
	public Calculator zero() {
		expression.zero();
		return this;
	}
	
	public Calculator one() {
		expression.one();
		return this;
	}
	
	public Calculator two() {
		expression.two();
		return this;
	}
	
	public Calculator three() {
		expression.three();
		return this;
	}	
	
	public Calculator four() {
		expression.four();
		return this;
	}	
	
	public Calculator five() {
		expression.five();
		return this;
	}
	
	public Calculator six() {
		expression.six();
		return this;
	}
	
	public Calculator seven() {
		expression.seven();
		return this;
	}
	
	public Calculator eight() {
		expression.eight();
		return this;
	}
	
	public Calculator nine() {
		expression.nine();
		return this;
	}
	
	public Calculator openBracket() {
		expression.openBracket();
		return this;
	}
	
	public Calculator closeBracket() {
		expression.closeBracket();
		return this;
	}
	
	public Calculator dividedBy() {
		expression.dividedBy();
		return this;
	}
	
	public Calculator times() {
		expression.times();
		return this;
	}	
	
	public Calculator plus() {
		expression.plus();
		return this;
	}
	
	public Calculator minus() {
		expression.minus();
		return this;
	}
	
	public String equals() {
		return expression.equals();
	}
}