package rmf;

import java.io.File;
import java.io.PrintStream;
import java.util.List;

/**
 * Rmf entry point.
 */
final class Execution {

    /**
     * Edit file names.
     *
     * @param files Files to rename
     * @param edit  Editor to use
     */
    void start(final List<File> files, final NamesEditor edit, final PrintStream output) {
        if (files.isEmpty()) {
            this.version(output);
        } else {
            try {
                new Rename()
                        .renameFiles(
                                edit.edit(
                                        files,
                                        new TempFileBuilder(files)
                                ),
                                files
                        );
            } catch (final Exception exc) {
                output.println(exc.getMessage());
            }
        }
    }

    private void version(final PrintStream output) {
        output.printf("rmf %s\n", Defaults.VERSION);
    }
}
