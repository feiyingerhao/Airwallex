package calculator;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;


public class MyCalculator {
    Stack<Double> stack = new Stack<>();
    Stack<Stack<Double>> histories = new Stack<>();
    List<Operation> operations = new ArrayList<>();
    // 实际展示保留10位,如果后面为0那么去除后面的0,整数只显示整数
    DecimalFormat df = new DecimalFormat("#.##########");

    public static void main(String[] args) {
        MyCalculator calculator = new MyCalculator();
        calculator.readLine();
    }

    public void readLine() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            String line = scan.nextLine();
            if (line.equals("exit"))
                break;
            doOperation(line);

        }
        scan.close();
    }

    // 为了单测抽离出来
    public void doOperation(String line) {
        parser(line);
        calculate();
        display();
    }

    private void parser(String line) {
        // 最后加上空格为了统一格式,记录了数字、运算符以及他们的位置[异常使用]
        String input = line + " ";
        int len = input.length();
        int start = 0;
        // 每一次运算清空上一次的输入
        operations.clear();
        while (start < len) {
            int end = input.indexOf(" ");
            if (end == -1)
                break;
            String symbol = input.substring(0, end);
            input = input.substring(end + 1);
            if (!symbol.isEmpty()) {
                operations.add(new calculator.Operation(symbol, start + 1));
            }
            start += end + 1;
        }
    }

    private boolean checkIsLegal(String symbol) {
        if (symbol.equals("+") || symbol.equals("-") || symbol.equals("*") || symbol.equals("/")) {
            return stack.size() >= 2;
        } else if (symbol.equals("sqrt")) {
            return stack.size() >= 1;
        } else {
            return true;
        }
    }

    private void calculate() {
        Pattern pattern = Pattern.compile("[0-9]*");
        boolean isLegal = true;
        for (calculator.Operation item : operations) {
            String symbol = item.getSymbol();
            int pos = item.getPos();
            if (pattern.matcher(symbol).matches()) {
                stack.push(Double.parseDouble(symbol));
            } else if (symbol.equals("+")) {
                isLegal = checkIsLegal(symbol);
                if (isLegal) {
                    double num2 = stack.pop();
                    double num1 = stack.pop();
                    stack.push(num1 + num2);
                }
            } else if (symbol.equals("-")) {
                isLegal = checkIsLegal(symbol);
                if (isLegal) {
                    double num2 = stack.pop();
                    double num1 = stack.pop();
                    stack.push(num1 - num2);
                }
            } else if (symbol.equals("*")) {
                isLegal = checkIsLegal(symbol);
                if (isLegal) {
                    double num2 = stack.pop();
                    double num1 = stack.pop();
                    stack.push(num1 * num2);
                }
            } else if (symbol.equals("/")) {
                isLegal = checkIsLegal(symbol);
                if (isLegal) {
                    double num2 = stack.pop();
                    double num1 = stack.pop();
                    stack.push(num1 / num2);
                }
            } else if (symbol.equals("sqrt")) {
                isLegal = checkIsLegal(symbol);
                if (isLegal) {
                    double num = stack.pop();
                    stack.push(Math.sqrt(num));
                }
            } else if (symbol.equals("undo")) {
                // 弹出最近的快照之后取栈顶的快照
                histories.pop();
                stack = histories.peek();
            } else if (symbol.equals("clear")) {
                stack.clear();
            } else {
                isLegal = false;
            }
            if (!isLegal) {
                System.out.println("operator " + symbol + " (position: " + pos + "): insufficient parameters");
                break;
            }
            // undo不能undo自己
            if (!symbol.equals("undo")) {
                // 将当前的stack记录一个快照
                memo();
            }

        }
    }

    private void memo() {
        Stack<Double> tmp = new Stack<>();
        for (Double item : stack) {
            tmp.add(item);
        }
        histories.push(tmp);
    }

    private void display() {
        System.out.print("stack:");
        for (Double item : stack) {
            System.out.print(df.format(item) + " ");
        }
        System.out.println();
    }
}
