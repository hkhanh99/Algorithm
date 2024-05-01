import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void calculateExecutionTime(Runnable operation, String operationName) {
        long startTime = System.nanoTime();
        operation.run();
        long endTime = System.nanoTime();
        long executionTimeNano = endTime - startTime;
        double executionTimeSeconds = (double) executionTimeNano / 1_000_000_000.0;
        System.out.println(operationName + " Execution Time (Nano): " + executionTimeNano + " nanoseconds");
        System.out.println(operationName + " Execution Time (Seconds): " + executionTimeSeconds + " seconds");
    }

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            Queue queue = new Queue();
            Stack stack = new Stack();
            int selection = 0;
            do {
                System.out.println("\n===MENU SYSTEM===");
                System.out.println("1.Send Message");
                System.out.println("2.Receiver");
                System.out.println("3.Show Queue");
                System.out.println("4.Show stack");
                System.out.println("5.Exit");
                System.out.println("Enter your selection");
                try {
                    selection = input.nextInt();
                    input.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Error!!!");
                    input.nextLine();
                    continue;
                }
                switch (selection) {
                    case 1:
                        long inputStartTimeNano = System.nanoTime();
                        System.out.print("\nEnter your message: ");
                        String message = input.nextLine();
                        if (message.length() > 250 || message.length() < 1) {
                            System.out.println("\nError!!!\n");
                            return;
                        }
                        queue.enqueue(message);
                        long inputEndTimeNano = System.nanoTime();
                        long inputExecutionTimeNano = inputEndTimeNano - inputStartTimeNano;
                        double inputExecutionTimeSeconds = (double) inputExecutionTimeNano / 1_000_000_000.0;
                        System.out.println("Input Time (Nano): " + inputExecutionTimeNano + " nanoseconds");
                        System.out.println("Input Time (Seconds): " + inputExecutionTimeSeconds + " seconds");

                        calculateExecutionTime(() -> {
                            // Empty lambda, no additional enqueue here
                        }, "Enqueue");
                        break;

                    case 2:
                        long executionStartTimeNano2 = System.nanoTime();
                        calculateExecutionTime(() -> {
                            if (queue.isEmpty()) {
                                System.out.println("NULL!!!");
                                return;
                            }
                            stack.push(queue.dequeue());
                        }, "Dequeue and Push");
                        long executionEndTimeNano2 = System.nanoTime();
                        long executionExecutionTimeNano2 = executionEndTimeNano2 - executionStartTimeNano2;
                        double executionExecutionTimeSeconds2 = (double) executionExecutionTimeNano2 / 1_000_000_000.0;
                        System.out.println("Execution Time for Case 2 (Nano): " + executionExecutionTimeNano2 + " nanoseconds");
                        System.out.println("Execution Time for Case 2 (Seconds): " + executionExecutionTimeSeconds2 + " seconds");
                        break;
                    case 3:
                        long printStartTimeNano = System.nanoTime();
                        calculateExecutionTime(() -> {
                            queue.print();
                        }, "Print Queue");
                        long printEndTimeNano = System.nanoTime();
                        long printExecutionTimeNano = printEndTimeNano - printStartTimeNano;
                        double printExecutionTimeSeconds = (double) printExecutionTimeNano / 1_000_000_000.0;
                        System.out.println("Print Queue Time (Nano): " + printExecutionTimeNano + " nanoseconds");
                        System.out.println("Print Queue Time (Seconds): " + printExecutionTimeSeconds + " seconds");
                        System.out.println("Queue Size: " + queue.size());
                        System.out.println("Stack Size: " + stack.size());
                        break;

                    case 4:
                        long executionStartTimeNano4 = System.nanoTime();
                        calculateExecutionTime(() -> {
                            if (stack.isEmpty()) {
                                System.out.println("EMPTY!!!");
                                return;
                            }
                            stack.print();
                        }, "Print Stack");
                        long executionEndTimeNano4 = System.nanoTime();
                        long executionExecutionTimeNano4 = executionEndTimeNano4 - executionStartTimeNano4;
                        double executionExecutionTimeSeconds4 = (double) executionExecutionTimeNano4 / 1_000_000_000.0;
                        System.out.println("Execution Time for Case 4 (Nano): " + executionExecutionTimeNano4 + " nanoseconds");
                        System.out.println("Execution Time for Case 4 (Seconds): " + executionExecutionTimeSeconds4 + " seconds");
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("\nSelection is not valid!!!\n");
                        break;
                }
            } while (selection != 5);
        }
    }
}
