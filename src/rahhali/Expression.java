package rahhali;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Expression {
	private static Expression active;
	private String expression = "";
	private Expression parent = null;
	private List<Expression> children = new ArrayList<>();
	
	public Expression() {
		active = this;
		parent = null;
	}
	
	private Expression(Expression parent) {
		this.parent = parent;
	}
	
	public void dot() {
		if (!expressionContains("\\.\\d*$")) {
			active.expression += ".";
		}
	}
	
	public void zero() {
		active.appendDigit("0");
	}
	
	public void one() {
		active.appendDigit("1");
	}
	
	public void two() {
		active.appendDigit("2");
	}
	
	public void three() {
		active.appendDigit("3");
	}	
	
	public void four() {
		active.appendDigit("4");
	}	
	
	public void five() {
		active.appendDigit("5");
	}
	
	public void six() {
		active.appendDigit("6");
	}
	
	public void seven() {
		active.appendDigit("7");
	}
	
	public void eight() {
		active.appendDigit("8");
	}
	
	public void nine() {
		active.appendDigit("9");
	}		
	
	public void openBracket() {
		if (expressionContains("[\\d.]$")) {
			return;
		}
		active.expression += "()";
		Expression child = new Expression(this);
		active.children.add(child);		
		active = child;
	}
	
	public void closeBracket() {
		if (active.expression.isEmpty() || active.parent == null) {
			return;
		}
		active = active.parent;
	}
	
	void dividedBy() {
		if (expressionContains("\\d$")) {
			active.expression += "/";
		}
	}	
	
	void times() {
		if (expressionContains("\\d$")) {
			active.expression += "*";
		}
	}	
	
	void plus() {
		if (expressionContains("\\d$")) {
			active.expression += "+";
		}
	}
	
	void minus() {
		if (expressionContains("([*/\\d]$)|(^$)")) {
			active.expression += "-";
		}
	}
	
	String equals() {
		bedmas();
		return expression;
	}	
	
	private void bedmas() {
		brackets();
		// TODO: exponents();
		division();
		multiplication();
		addition();
		subtraction();
	}	
	
	private void brackets() {
		for (int i = 0; i < children.size(); i++) {
			String nestedEvaluatedExpression = children.get(i).equals();
			expression = expression.replaceFirst("()", nestedEvaluatedExpression);
		}
	}	
	
	private void division() {
		performBinaryOperation("/", (operand1, operand2) -> operand1.divide(operand2, MathContext.DECIMAL128));
	}	
	
	private void multiplication() {
		performBinaryOperation("\\*", (operand1, operand2) -> operand1.divide(operand2, MathContext.DECIMAL128));	
	}	

	private void addition() {
		performBinaryOperation("\\+", (operand1, operand2) -> operand1.divide(operand2, MathContext.DECIMAL128));	
	}
	
	private void subtraction() {
		performBinaryOperation("-", (operand1, operand2) -> operand1.divide(operand2, MathContext.DECIMAL128));	
	}
	
	private boolean expressionContains(String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(active.expression);
		return matcher.find();	
	}
	
	private void performBinaryOperation(String regexOperator, BiFunction<BigDecimal, BigDecimal, BigDecimal> operation) {
		String regexPattern = "(\\d*\\.?\\d*)" + regexOperator + "(\\d*\\.?\\d*)";
		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(expression);
		while(matcher.find()) {
			BigDecimal operand1 = new BigDecimal(matcher.group(1));
			BigDecimal operand2 = new BigDecimal(matcher.group(2));
			BigDecimal answer = operation.apply(operand1, operand2);
			expression.replaceFirst(regexPattern,  answer.toPlainString());
			matcher = pattern.matcher(expression);
		}
	}
	
	private void appendDigit(String digit) {
		if (!expressionContains("(^|[^\\d.])0$")) {
			active.expression += digit;
		}
	}		
}