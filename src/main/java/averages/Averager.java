package averages;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.DoubleStream;

class Average {
  private double sum;
  private long count;

  public Average() {
    this(0, 0);
  }

  public Average(double sum, long count) {
    this.sum = sum;
    this.count = count;
  }

  public void include(double d) {
    this.sum += d;
    this.count++;
  }

  public void merge(Average other) {
//    System.out.println("MERGE!!!");
    this.sum += other.sum;
    this.count += other.count;
  }

  public Optional<Double> get() {
    if (count > 0) {
      return Optional.of(sum / count);
    } else {
      return Optional.empty();
    }
  }
}

public class Averager {
  // See JIT compilations running
  // -XX:+PrintCompilation
  public static void main(String[] args) {
    long start = System.nanoTime();
    ThreadLocalRandom.current().doubles(20_000_000_000L, -Math.PI, +Math.PI)
//    DoubleStream.iterate(0.0, x -> ThreadLocalRandom.current().nextDouble(-Math.PI, +Math.PI))
//        .limit(20_000_000_000L)
        .parallel()
//        .unordered()
//        .collect(() -> new Average(), (a, d) -> a.include(d), (a1, a2) -> a1.merge(a2))
        .collect(Average::new, Average::include, Average::merge)
        .get()
    .ifPresentOrElse(System.out::println, () -> System.out.println("hmm, no data!"));
    long time = System.nanoTime() - start;
    System.out.println("Time taken is " + (time / 1_000_000_000.0));

  }
}
