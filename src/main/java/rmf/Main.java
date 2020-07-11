package rmf;

import java.io.File;

public final class Main {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            return;
        }
        var file = new File(args[0]);
        if (!file.exists()) {
            System.out.println("Not exist");
        } else {
            System.out.println(
                    new Editor().writeFileNames(
                            file,
                            new ProcessBuilder()
                    ));
        }
    }
}
