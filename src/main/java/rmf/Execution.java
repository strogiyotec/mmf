package rmf;

import java.io.File;
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
     * @throws Exception If failed
     */
    void start(final List<File> files, final NamesEditor edit) throws Exception {
        new Rename()
                .renameFiles(
                        edit.edit(
                                files,
                                new TempFileBuilder(files)
                        ),
                        files
                );
    }
}
