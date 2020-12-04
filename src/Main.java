package src;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        Berlin berlin = new Berlin();
        NewDelhi newDelhi = new NewDelhi();
        London london = new London();
        NewYork newYork = new NewYork();
        Moscow moscow = new Moscow();

        Map<String, City> cities = new HashMap<String, City>();
        cities.put(berlin.cityCode, berlin);
        cities.put(newDelhi.cityCode, newDelhi);
        cities.put(london.cityCode, london);
        cities.put(newYork.cityCode, newYork);
        cities.put(moscow.cityCode, moscow);
        TreeMap<String, City> citiesSorted = new TreeMap<String, City>(cities);

        String[] inputCodes;
        boolean isCorrectInput;
        do {
            isCorrectInput = true;

            System.out.println(" Please select min 3, max 5 city code: with spaces between them");
            System.out.println(citiesSorted);
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            inputCodes = Arrays.stream(input.split(" ")).toArray(String[]::new);
            for (String code : inputCodes) {
                if (!citiesSorted.containsKey(code)) {
                    isCorrectInput = false;
                    System.out.println(code + " is not a valid code.");
                    break;
                }
            }
            isCorrectInput = isCorrectInput && inputCodes.length >= 3 && inputCodes.length <= 5;
        }
        while (!isCorrectInput);
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
        for (String code : inputCodes) {
            Runnable runnable =
                    () -> {
                                citiesSorted.get(code).showTime();
                    };
            executorService.scheduleAtFixedRate(runnable, 0,5, TimeUnit.SECONDS);
        }
       //executorService.shutdownNow();

    }
}
