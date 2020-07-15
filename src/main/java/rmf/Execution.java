package rmf;

import java.io.File;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class Execution {

    void start(final String[] args) throws Exception {
        var files = Stream.of(args).map(File::new).collect(Collectors.toList());
        new Rename(new Duplicates())
                .renameFiles(
                        new Editor(new TempFile()).edit(new ProcessBuilder(), files),
                        files
                );
    }
}
