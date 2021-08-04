package exemplo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.aspectj.lang.Signature;

public aspect CaptureCoveredGoals {

	String exceptionType = "EXPLICIT";
	String testClassName = "ExampleTest.java";
	String coveredGoalFilePath = "covered.goals";
	String testStatementsFilePath = "testsStatements.txt";
	String methodsCallFilePath = "methods.txt";
	String lastMethod = "";
	ArrayList<Integer> assertLines = getAssertLines(
			"C:\\Users\\Brenno\\eclipse-workspace\\Exemplo\\test\\exemplo\\ExampleTest.java");

	// pointcuts
	pointcut getTestNames(): execution(* ExampleTest.*(..));

	pointcut getMethods() : call(* Calculadora.*(..)) || call(Calculadora.new(..));

	pointcut getConstructorsAndVoidMethodsNoException() : call(void Calculadora.*(..)) || call(Calculadora.new(..));

	pointcut getMethodsWithReturnNoException() : call(* Calculadora.*(..)) && !call(void Calculadora.*(..));

	pointcut getDeclaredException() : call(* Calculadora.*(..) throws *) || call(Calculadora.new(..) throws *);

	pointcut getUndeclaredException() : (call(* *.*(..)) || call(*.new(..))) && (!call(* Calculadora.*(..) throws *) || !call(Calculadora.new(..) throws *));

	before() : getTestNames(){
		writeInFile(coveredGoalFilePath, thisJoinPointStaticPart.getSignature().getName());
		System.out.println(thisJoinPointStaticPart.getSignature().getName());
	}

	before(): getMethods(){
		if (thisJoinPoint.getSourceLocation().getFileName().equals(testClassName)
				&& !isAssertLine(thisJoinPoint.getSourceLocation().getLine())) {
			Signature signature = thisJoinPointStaticPart.getSignature();

			String methodSignature = signature.toString();

			String className = signature.toShortString().split("\\.")[0].replaceAll("\\(.*", "");

			// System.out.println(className);

			String methodName = signature.getName();

			lastMethod = methodName;

			String[] methodSignatureSplit = methodSignature.split(" ", 2);

			String outputType = "V";

			if (methodSignatureSplit.length > 1) {
				String type = methodSignatureSplit[0];
				outputType = getTypeRepresentation(type);
			}

			Object[] args = thisJoinPoint.getArgs();

			String methodCall = thisJoinPoint.getSourceLocation().getLine() + " " + methodName + "()";

			String goal = className + " " + methodName + "()" + outputType;

			// System.out.println(goal);

			if (args.length > 0) {
				String argumentsString = "";
				String argumentsStringToInput = "";

				String[] inputTypes = methodSignature
						.substring(methodSignature.indexOf('(') + 1, methodSignature.indexOf(')')).split(", ");

				for (String inputType : inputTypes) {
					argumentsString += getTypeRepresentation(inputType);
					argumentsStringToInput += getTypeRepresentation(inputType) + " ";
				}

				goal = goal.replaceFirst("\\(.*\\)", "(" + argumentsString + ")");

				String goalToInputs = goal.replaceFirst("\\(.*\\)", "( " + argumentsStringToInput + ")");

				methodCall = methodCall.replaceFirst("\\(.*\\)", "( " + argumentsStringToInput + ")");

				String[] inputsCoverages = getInputsCoverages(goalToInputs, args, inputTypes);

				if (!methodName.contains("<init>")) {
					for (String inputCoverage : inputsCoverages) {
						writeInFile(coveredGoalFilePath, inputCoverage);
						//// System.out.println(inputCoverage);
					}
				}
			}

			writeInFile(coveredGoalFilePath, "[METHOD] " + goal);
			methodCall = methodCall.replace("()", "(  )");
			writeInFile(methodsCallFilePath, methodCall);
			//// System.out.println("[METHOD] " + goal);

		}
	}

	after() returning (Object output) : getConstructorsAndVoidMethodsNoException() {
		if (thisJoinPoint.getSourceLocation().getFileName().equals(testClassName)
				&& !isAssertLine(thisJoinPoint.getSourceLocation().getLine())) {
			Signature signature = thisJoinPointStaticPart.getSignature();

			String methodSignature = signature.toString();

			String className = signature.toShortString().split("\\.")[0].replaceAll("\\(.*", "");
			String methodName = signature.getName();

			String[] methodSignatureSplit = methodSignature.split(" ", 2);

			String outputType = "V";

			if (methodSignatureSplit.length > 1) {
				String type = methodSignatureSplit[0];
				outputType = getTypeRepresentation(type);
			}

			Object[] args = thisJoinPoint.getArgs();

			String goal = className + " " + methodName + "()" + outputType;

			if (args.length > 0) {
				String argumentsString = "";

				String[] inputTypes = methodSignature
						.substring(methodSignature.indexOf('(') + 1, methodSignature.indexOf(')')).split(", ");

				for (String inputType : inputTypes) {
					argumentsString += getTypeRepresentation(inputType);
				}

				goal = goal.replaceFirst("\\(.*\\)", "(" + argumentsString + ")");
			}

			writeInFile(coveredGoalFilePath, "[METHODNOEX] " + goal);
			//// System.out.println("[METHODNOEX] " + goal);
		}
	}

	after() returning (Object output) : getMethodsWithReturnNoException() {
		if (thisJoinPoint.getSourceLocation().getFileName().equals(testClassName)
				&& !isAssertLine(thisJoinPoint.getSourceLocation().getLine())) {
			Signature signature = thisJoinPointStaticPart.getSignature();
			String methodSignature = signature.toString();
			String outputType = methodSignature.split(" ", 2)[0];

			String outputTypeRepresentation = getTypeRepresentation(outputType);

			String className = signature.toShortString().split("\\.")[0].replaceAll("\\(.*", "");
			String methodName = signature.getName();

			Object[] args = thisJoinPoint.getArgs();

			String goal = className + " " + methodName + "()" + outputTypeRepresentation;

			if (args.length > 0) {
				String argumentsString = "";

				String[] inputTypes = methodSignature
						.substring(methodSignature.indexOf('(') + 1, methodSignature.indexOf(')')).split(", ");

				for (String inputType : inputTypes) {
					argumentsString += getTypeRepresentation(inputType);
				}

				goal = goal.replaceFirst("\\(.*\\)", "(" + argumentsString + ")");
			}

			if (output == null)
				writeInFile(coveredGoalFilePath, "[OUTPUT] " + goal + " " + "Null");
			//// System.out.println("[OUTPUT] " + goal + " " + "Null");
			else
				writeInFile(coveredGoalFilePath, "[OUTPUT] " + goal + " " + getInputOutputValue(outputType, output));
			//// System.out.println("[OUTPUT] " + goal + " " +
			//// getInputOutputValue(outputType, output));

			writeInFile(coveredGoalFilePath, "[METHODNOEX] " + goal);

			//// System.out.println("[METHODNOEX] " + goal);
		}
	}

	after() throwing (Exception exception) : getDeclaredException() {
		if (thisJoinPoint.getSourceLocation().getFileName().equals(testClassName)) {
			Signature signature = thisJoinPointStaticPart.getSignature();

			String methodSignature = signature.toString();

			String className = signature.toShortString().split("\\.")[0].replaceAll("\\(.*", "");
			String methodName = signature.getName();

			String[] methodSignatureSplit = methodSignature.split(" ", 2);

			String outputType = "";

			if (methodSignatureSplit.length > 1) {
				String type = methodSignatureSplit[0];
				outputType = getTypeRepresentation(type);
			}

			Object[] args = thisJoinPoint.getArgs();

			String goal = "[EXCEPTION] " + className + " " + methodName + "()" + outputType + " "
					+ exception.getClass().getName() + " DECLARED";

			if (args.length > 0) {
				String argumentsString = "";

				String[] inputTypes = methodSignature
						.substring(methodSignature.indexOf('(') + 1, methodSignature.indexOf(')')).split(", ");

				for (String inputType : inputTypes) {
					argumentsString += getTypeRepresentation(inputType);
				}

				goal = goal.replaceFirst("\\(.*\\)", "(" + argumentsString + ")");
			}

			writeInFile(coveredGoalFilePath, goal);

			//// System.out.println("[EXCEPTION] " + goal + " " +
			//// exception.getClass().getName() + " DECLARED");

			exceptionType = "DECLARED";

		}
	}

	after() throwing (Exception exception)  : getUndeclaredException() {
		if (exceptionType.equals("DECLARED")) {
			exceptionType = "EXPLICIT";
		} else {
			Signature signature = thisJoinPointStaticPart.getSignature();

			String methodSignature = signature.toString();

			String className = signature.toShortString().split("\\.")[0].replaceAll("\\(.*", "");
			String methodName = signature.getName();

			String[] methodSignatureSplit = methodSignature.split(" ", 2);

			String outputType = "";

			if (methodSignatureSplit.length > 1) {
				String type = methodSignatureSplit[0];
				outputType = getTypeRepresentation(type);
			}

			Object[] args = thisJoinPoint.getArgs();

			String goal = "[EXCEPTION] " + className + " " + methodName + "()" + outputType + " "
					+ exception.getClass().getName() + " " + exceptionType;

			if (args.length > 0) {
				String argumentsString = "";

				String[] inputTypes = methodSignature
						.substring(methodSignature.indexOf('(') + 1, methodSignature.indexOf(')')).split(", ");

				for (String inputType : inputTypes) {
					argumentsString += getTypeRepresentation(inputType);
				}

				goal = goal.replaceFirst("\\(.*\\)", "(" + argumentsString + ")");
			}

			String stackTrace = exception.getStackTrace()[0].toString();

			if (stackTrace.contains(className) && lastMethod.equals(methodName)) {
				writeInFile(coveredGoalFilePath, goal);
				// System.out.println(goal);
				if (exceptionType.equals("IMPLICIT"))
					exceptionType = "EXPLICIT";
			} else if (!stackTrace.contains(className)) {
				exceptionType = "IMPLICIT";
			}
		}
	}

	// auxiliaryMethods
	private String[] getInputsCoverages(String methodSignature, Object[] args, String[] types) {
		String[] inputsCoverages = new String[args.length];

		for (int i = 0; i < args.length; i++) {
			String inputPrefix = "[INPUT] " + methodSignature + " " + i + " ";

			if (args[i] == null) {
				inputsCoverages[i] = inputPrefix + "Null";
			} else {

				inputsCoverages[i] = inputPrefix + getInputOutputValue(types[i], args[i]);
			}
		}
		return inputsCoverages;
	}

	private String getInputOutputValue(String type, Object arg) {
		String value = "";

		if (type.contains("[]"))
			type = "array";

		switch (type) {
		case "boolean":
		case "java.lang.Boolean":
			value = ((Boolean) arg).toString().substring(0, 1).toUpperCase() + ((Boolean) arg).toString().substring(1);
			break;
		case "int":
		case "java.lang.Integer":
			int intInput = (int) arg;
			if (intInput < 0)
				value = "Negative";
			else if (intInput == 0)
				value = "Zero";
			else if (intInput > 0)
				value = "Positive";
			break;
		case "byte":
		case "java.lang.Byte":
			byte byteInput = (byte) arg;
			if (byteInput < 0)
				value = "Negative";
			else if (byteInput == 0)
				value = "Zero";
			else if (byteInput > 0)
				value = "Positive";
			break;
		case "short":
		case "java.lang.Short":
			short shortInput = (short) arg;
			if (shortInput < 0)
				value = "Negative";
			else if (shortInput == 0)
				value = "Zero";
			else if (shortInput > 0)
				value = "Positive";
			break;
		case "long":
		case "java.lang.Long":
			long longInput = (long) arg;
			if (longInput < 0)
				value = "Negative";
			else if (longInput == 0)
				value = "Zero";
			else if (longInput > 0)
				value = "Positive";
			break;
		case "float":
		case "java.lang.Float":
			float floatInput = (float) arg;
			if (floatInput < 0)
				value = "Negative";
			else if (floatInput == 0)
				value = "Zero";
			else if (floatInput > 0)
				value = "Positive";
			break;
		case "double":
		case "java.lang.Double":
			Double doubleInput = (Double) arg;
			if (doubleInput < 0)
				value = "Negative";
			else if (doubleInput == 0)
				value = "Zero";
			else if (doubleInput > 0)
				value = "Positive";
			break;
		case "char":
		case "java.lang.Character":
			Character characterInput = (Character) arg;
			if (Character.isAlphabetic(Character.getType(characterInput)))
				value = "AlphabeticChar";
			else if (Character.isDigit(characterInput))
				value = "Digit";
			else
				value = "OtherChar";
			break;
		case "array":
			if (((Object[]) arg).length > 0)
				value = "NonEmptyArray";
			else
				value = "EmptyArray";
			break;
		case "java.util.List":
			if (((List) arg).size() > 0)
				value = "NonEmptyList";
			else
				value = "EmptyList";
			break;
		case "java.util.Set":
			if (((Set) arg).size() > 0)
				value = "NonEmptySet";
			else
				value = "EmptySet";
			break;
		case "java.util.Map":
			if (((Map) arg).size() > 0)
				value = "NonEmptyMap";
			else
				value = "EmptyMap";
			break;
		case "java.lang.String":
			if (((String) arg).isEmpty())
				value = "EmptyString";
			else
				value = "NonEmptyString";
			break;
		default:
			value = "NonNull";
			break;
		}

		return value;
	}

	private String getTypeRepresentation(String type) {
		String returnType = type;

		if (returnType.contains("[]"))

			returnType = "array";

		switch (returnType) {
		case "boolean":
		case "Boolean":
		case "java.lang.Boolean":
			returnType = "Z";
			break;
		case "int":
		case "Integer":
		case "java.lang.Integer":
			returnType = "I";
			break;
		case "byte":
		case "Byte":
		case "java.lang.Byte":
			returnType = "B";
			break;
		case "short":
		case "Short":
		case "java.lang.Short":
			returnType = "S";
			break;
		case "long":
		case "Long":
		case "java.lang.Long":
			returnType = "J";
			break;
		case "float":
		case "Float":
		case "java.lang.Float":
			returnType = "F";
			break;
		case "double":
		case "Double":
		case "java.lang.Double":
			returnType = "D";
			break;
		case "char":
		case "Char":
		case "java.lang.Character":
			returnType = "C";
			break;
		case "array":
			returnType = "[" + getTypeRepresentation(type.substring(0, type.length() - 2));
			break;
		case "List":
		case "java.util.List":
			returnType = "Ljava/util/List;";
			break;
		case "Set":
		case "java.util.Set":
			returnType = "Ljava/util/Set;";
			break;
		case "Map":
		case "java.util.Map":
			returnType = "Ljava/util/Map;";
			break;
		case "String":
		case "java.lang.String":
			returnType = "Ljava/lang/String;";
			break;
		case "Object":
		case "java.lang.Object":
			returnType = "Ljava/lang/Object;";
			break;
		case "void":
			returnType = "V";
			break;
		default:
			returnType = "L" + type + ";";
			break;
		}
		return returnType;
	}

	private void writeInFile(String filePath, String text) {
		BufferedWriter buffWrite;
		try {
			buffWrite = new BufferedWriter(new FileWriter(filePath, true));
			buffWrite.append(text + "\n");
			buffWrite.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private ArrayList<Integer> getAssertLines(String filePath) {
		ArrayList<Integer> assertLines = new ArrayList<>();

		try {
			BufferedReader buffRead = new BufferedReader(new FileReader(filePath));
			String linha = "";

			int lineNumber = 1;

			linha = buffRead.readLine().trim();

			String testCaseStatements = "";

			Boolean isStatement = false;

			String assertRegex = "(.*\\n)*.*assert(?!Fail).*\\([a-zA-Z\\(\\)'\\\"=\\-\\+!0-9,\\s]*.*(.*\\n.*)*";

			String discardedAssertRegex = ".*assert.*(True|Equals|Same|That|False|Null)\\(.*\\..*]*\\).*";

			while (linha != null) {
				linha = linha.trim();

				if (linha.contains("assert")) {
					assertLines.add(lineNumber);
				}

				if (linha.contains("public void test")) {
					isStatement = true;

					if (testCaseStatements.matches(assertRegex)) {
						writeInFile(testStatementsFilePath, testCaseStatements);
					}

					// writeInFile(testStatementsFilePath, linha);
					writeInFile(testStatementsFilePath, lineNumber + " " + linha);

					testCaseStatements = "";
				}

				else if (isStatement && !linha.equals("\n") && !linha.equals("") && !linha.contains("@Test")
						&& !linha.matches(discardedAssertRegex) && !linha.contains("if")
						&& !linha.contains("System.out.") && !linha.contains("try") && !linha.contains("catch")
						&& !linha.contains("Assert.fail")) {
					// testCaseStatements += linha + "\n";
					testCaseStatements += lineNumber + " " + linha + "\n";
				}

				linha = buffRead.readLine();

				lineNumber++;
			}

			if (testCaseStatements.matches(assertRegex)) {
				writeInFile(testStatementsFilePath, testCaseStatements);
			}

			buffRead.close();

			return assertLines;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private boolean isAssertLine(int lineNumber) {
		return assertLines.contains(lineNumber);
	}
}