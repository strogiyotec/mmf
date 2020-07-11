package rmf;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

public final class Editor {

    List<String> writeFileNames(final File tempFile, final ProcessBuilder builder) throws Exception {
        var process = builder
                .command(
                        Optional.ofNullable(System.getenv("TERMINAL")).orElse("xterm"),
                        "-e",
                        Optional.ofNullable(System.getenv("EDITOR")).orElse("vim"),
                        tempFile.getAbsolutePath()
                )
                .start();
        process.waitFor();
        return Files.readAllLines(tempFile.toPath());
    }
}
