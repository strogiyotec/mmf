package rmf;

import java.io.File;
import java.util.List;

interface EditedFileNames {

    /**
     * Get file that stores new file names.
     *
     * @param toRename Files to rename
     * @param toStore  Temp file to store new names
     * @return File with new names
     * @throws Exception If failed
     */
    File store(List<File> toRename, TempFileBuilder toStore) throws Exception;

}
