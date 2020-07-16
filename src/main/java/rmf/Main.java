package rmf;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Main {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            return;
        }
        new Execution().start(
                toFiles(args),
                new UsingEditor(
                        new ProcessBuilder()
                )
        );
    }

    private static List<File> toFiles(final String[] args) {
        return Stream.of(args)
                .map(File::new)
                .collect(Collectors.toList());
    }
}
