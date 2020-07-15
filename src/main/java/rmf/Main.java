package rmf;

public final class Main {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            return;
        }
        new Execution().start(args);
    }
}
