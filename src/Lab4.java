import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Lab4 {
    private static final int ARRAY_SIZE = 10000;
    private static final int MAX_VALUE = 100;

    public static void main(String[] args) {
        int[] input1 = generateRandomArray();
        int[] input2 = generateRandomArray();

        int[] resultSync = multiplyArraysSync(input1, input2);
        int[] resultParallel = multiplyArraysParallel(input1, input2);

        // Затримка та вимірювання часу для синхронного виконання
        long time1 = System.currentTimeMillis();
        try {
            multiplyArraysWithDelay(input1, input2, 0);
        } finally {
            long elapsedTime = System.currentTimeMillis() - time1;
            System.out.printf("Sync with delay (sleep = 0): %dms\n", elapsedTime);
        }

        // Затримка та вимірювання часу для паралельного виконання
        long time2 = System.currentTimeMillis();
        try {
            multiplyArraysWithDelay(input1, input2, 1);
        } finally {
            long elapsedTime = System.currentTimeMillis() - time2;
            System.out.printf("Parallel with delay (sleep = 1): %dms\n", elapsedTime);
        }

        // Перевірка результатів
        boolean syncResultCorrect = Arrays.equals(resultSync, multiplyArraysWithDelay(input1, input2, 0));
        boolean parallelResultCorrect = Arrays.equals(resultParallel, multiplyArraysWithDelay(input1, input2, 1));

        System.out.println("Sync result is correct: " + syncResultCorrect);
        System.out.println("Parallel result is correct: " + parallelResultCorrect);
    }

    private static int[] multiplyArraysSync(int[] input1, int[] input2) {
        int[] result = new int[ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; i++) {
            result[i] = input1[i] * input2[i];
        }
        return result;
    }

    private static int[] multiplyArraysParallel(int[] input1, int[] input2) {
        return IntStream.range(0, ARRAY_SIZE)
                .parallel()
                .map(i -> input1[i] * input2[i])
                .toArray();
    }

    private static int[] multiplyArraysWithDelay(int[] input1, int[] input2, int sleep) {
        int[] result = new int[ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; i++) {
            result[i] = input1[i] * input2[i];
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    private static int[] generateRandomArray() {
        Random random = new Random();
        int[] array = new int[ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = random.nextInt(MAX_VALUE + 1);
        }
        return array;
    }

}
