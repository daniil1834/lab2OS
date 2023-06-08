
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Lab3 {

    public static void main(String[] args) {
        int n = 10; // Задане число n
        CompletableFuture<Integer> fibonacciFuture = calculateFibonacciAsync(n);

        System.out.println("Очікування обчислення числа Фібоначчі...");

        try {
            int result = fibonacciFuture.get(); // Очікування завершення обчислень
            System.out.println("Результат: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static CompletableFuture<Integer> calculateFibonacciAsync(int n) {
        return CompletableFuture.supplyAsync(() -> calculateFibonacci(n));
    }

    private static int calculateFibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
        }
    }

}
