package rmf;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Main {

    public static void main(String[] args) {
        new Execution().start(
                toFiles(args),
                new Vim(new ProcessBuilder()),
                System.out
        );
    }

    private static List<File> toFiles(final String[] args) {
        return Stream.of(args)
                .map(File::new)
                .collect(Collectors.toList());
    }
}
