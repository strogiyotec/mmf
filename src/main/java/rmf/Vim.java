package rmf;

import java.io.File;
import java.util.List;
import java.util.Optional;

/**
 * Rename using editor.
 */
final class Vim implements NamesEditor {

    private final ProcessBuilder editorProcess;

    Vim(final ProcessBuilder editorProcess) {
        this.editorProcess = editorProcess;
    }

    @Override
    public File store(final List<File> toRename, final TempFileBuilder tempFileBuilder) throws Exception {
        var tempFile = tempFileBuilder.build();
        var process = this.editorProcess
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
