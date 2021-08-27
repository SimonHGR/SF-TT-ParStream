package reduce;

import java.util.OptionalInt;
import java.util.stream.IntStream;

public class Ex1 {
  public static void main(String[] args) {
//    OptionalInt oi = IntStream.rangeClosed(1, 10)
//        .reduce((a, b) -> a + b);
////        .forEach(System.out::println);
//    oi.ifPresentOrElse(System.out::println,
//        () -> System.out.println("oops, there weren't any values"));

    int sum = IntStream.rangeClosed(1, 10)
        .reduce(0, (a, b) -> a + b);
    System.out.println(sum);
  }
}
