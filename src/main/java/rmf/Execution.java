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
     */
    void start(final List<File> files, final NamesEditor edit) {
        try{
            new Rename()
                    .renameFiles(
                            edit.edit(
                                    files,
                                    new TempFileBuilder(files)
                            ),
                            files
                    );
        }catch (final Exception exc){
            System.out.println(exc.getMessage());
        }
    }
}
