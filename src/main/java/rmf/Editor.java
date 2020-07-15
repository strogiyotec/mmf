package rmf;

import java.io.File;
import java.util.List;
import java.util.Optional;

final class Editor {

    /**
     * Calls your editor.
     * Create temp file with list of file names
     * Then call editor to write new names
     *
     * @param builder  Builder to call editor
     * @param toRename Files to rename
     * @return Temp file with new file names
     * @throws Exception If failed
     */
    File edit(final ProcessBuilder builder, final List<File> toRename, final TempFileBuilder tempFileBuilder) throws Exception {
        final File tempFile = tempFileBuilder.build(toRename);
        var process = builder
                .command(
                        Optional.ofNullable(System.getenv("TERMINAL")).orElse("xterm"),
                        "-e",
                        Optional.ofNullable(System.getenv("EDITOR")).orElse("vim"),
                        tempFile.getAbsolutePath()
                )
                .start();
        process.waitFor();
        return tempFile;
    }
}
