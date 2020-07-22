package rmf;

import java.io.File;
import java.util.List;

interface NamesEditor {

    /**
     * Edit file names and store new names in file.
     *
     * @param toRename Files to rename
     * @param toStore  Temp file to edit new names
     * @return File with new names
     * @throws Exception If failed
     */
    File edit(List<File> toRename, TempFileBuilder toStore) throws Exception;

}
