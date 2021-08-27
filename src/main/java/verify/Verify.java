package verify;

public record Verify() {
  public static void main(String[] args) {
    Object o = "Hello";
    if (o instanceof String s) {
      System.out.println(s);
    }
  }
}
