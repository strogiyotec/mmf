package rmf;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

public final class Editor {

    List<String> writeFillNames(final File tempFile, final ProcessBuilder builder) throws Exception {
        var process = builder
                .command(
                        System.getProperty("TERMINAL", "xterm"),
						"-e",
						System.getProperty("EDITOR","vim"),
                        tempFile.getAbsolutePath()
                )
                .start();
        process.waitFor();
        return Files.readAllLines(tempFile.toPath());
    }
}
