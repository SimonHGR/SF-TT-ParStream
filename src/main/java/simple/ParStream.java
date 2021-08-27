package simple;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class ParStream {
  public static void main(String[] args) throws Throwable {
//    final int[] count = {0};
    AtomicInteger count = new AtomicInteger(0);
    ForkJoinPool es = new ForkJoinPool(4);

    Runnable r = () -> {
      IntStream.range(0, 1_000_000)
          .sequential()
          .parallel()
//        .peek(x -> count[0]++)
          .peek(x -> count.incrementAndGet())
          .peek(x -> {
            if (x % 100_000 == 0) {
              System.out.println("Thread name is " + Thread.currentThread().getName());
            }
          })
          .allMatch(x -> true);
//        .count();
//    .forEach(System.out::println);
//    System.out.println("count = " + count[0]);
      System.out.println("count = " + count.get());
    };
    es.submit(r).get();
    System.out.println("Finished");
  }
}
