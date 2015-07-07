package rahhali.expression;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class NestedExpression {
	private String value;
	private NestedExpression parent;
	private List<NestedExpression> children;

	NestedExpression() {
		this(null);
	}

	private NestedExpression(NestedExpression parent) {
		this.parent = parent;
	}

	void dot() {
		if (!expressionContains("\\.\\d*$")) {
			value += ".";
		}
	}

	void zero() {
		appendDigit("0");
	}

	void one() {
		appendDigit("1");
	}

	void two() {
		appendDigit("2");
	}

	void three() {
		appendDigit("3");
	}

	void four() {
		appendDigit("4");
	}

	void five() {
		appendDigit("5");
	}

	void six() {
		appendDigit("6");
	}

	void seven() {
		appendDigit("7");
	}

	void eight() {
		appendDigit("8");
	}

	void nine() {
		appendDigit("9");
	}

	NestedExpression openBracket() {
		if (expressionContains("[\\d.]$")) {
			return this;
		}
		value += "()";
		NestedExpression child = new NestedExpression(this);
		children.add(child);
		return child;
	}

	NestedExpression closeBracket() {
		if (value.isEmpty() || parent == null) {
			return this;
		}
		return parent;
	}

	void dividedBy() {
		if (expressionContains("\\d$")) {
			value += "/";
		}
	}

	void times() {
		if (expressionContains("\\d$")) {
			value += "*";
		}
	}

	void plus() {
		if (expressionContains("\\d$")) {
			value += "+";
		}
	}

	void minus() {
		if (expressionContains("([*/\\d]$)|(^$)")) {
			value += "-";
		}
	}

	String equals() {
		bedmas();
		return value;
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
			value = value.replaceFirst("()", nestedEvaluatedExpression);
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
		Matcher matcher = pattern.matcher(value);
		return matcher.find();
	}

	private void performBinaryOperation(String regexOperator,
			BiFunction<BigDecimal, BigDecimal, BigDecimal> operation) {
		String regexPattern = "(\\d*\\.?\\d*)" + regexOperator + "(\\d*\\.?\\d*)";
		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(value);
		while (matcher.find()) {
			BigDecimal operand1 = new BigDecimal(matcher.group(1));
			BigDecimal operand2 = new BigDecimal(matcher.group(2));
			BigDecimal answer = operation.apply(operand1, operand2);
			value.replaceFirst(regexPattern, answer.toPlainString());
			matcher = pattern.matcher(value);
		}
	}

	private void appendDigit(String digit) {
		if (!expressionContains("(^|[^\\d.])0$")) {
			value += digit;
		}
	}
}
