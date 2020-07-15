package rmf;

import java.io.File;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class Execution {

    void start(final String[] args) throws Exception {
        var files = Stream.of(args).map(File::new).collect(Collectors.toList());
        new Rename()
                .renameFiles(
                        new Editor().edit(
                                new ProcessBuilder(),
                                files,
                                new TempFileBuilder()
                        ),
                        files
                );
    }
}
