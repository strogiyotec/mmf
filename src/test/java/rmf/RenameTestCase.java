package rmf;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.util.List;

final class RenameTestCase {

    @Test
    @DisplayName("Test that rename fails if new file name is a directory")
    void testFailsByDirInsteadOfName() throws Exception {
        final File namesStorage = new TempFileBuilder("test.txt").build();
        //write two lines
        Files.write(namesStorage.toPath(), List.of("/tmp/"));
        final File toRename = new TempFileBuilder("toRename1.txt").build();
        Assertions.assertThrows(
                IllegalStateException.class,
                //now temp file has two names but we want to rename only one
                () -> new Rename().renameFiles(namesStorage, List.of(toRename))
        );
    }

    @Test
    @DisplayName("Test that rename fails if file with name from temp file already exists")
    void testFailsByExistingFile() throws Exception {
        final File existingFile = new TempFileBuilder("exists.txt").build();
        final File namesStorage = new TempFileBuilder("test.txt").build();
        //write two lines
        Files.write(namesStorage.toPath(), List.of(existingFile.getAbsolutePath()));
        final File toRename = new TempFileBuilder("toRename1.txt").build();
        Assertions.assertThrows(
                FileAlreadyExistsException.class,
                //now temp file has two names but we want to rename only one
                () -> new Rename().renameFiles(namesStorage, List.of(toRename))
        );
    }

    @Test
    @DisplayName("Test that rename fails if temp file has two duplicate file names")
    void testFailsByDuplicateNames() throws Exception {
        final File namesStorage = new TempFileBuilder("test.txt").build();
        //write two lines
        Files.write(namesStorage.toPath(), List.of("same_name", "same_name"));
        final File first = new TempFileBuilder("toRename1.txt").build();
        final File second = new TempFileBuilder("toRename2.txt").build();
        Assertions.assertThrows(
                IllegalArgumentException.class,
                //now temp file has two names but we want to rename only one
                () -> new Rename().renameFiles(namesStorage, List.of(first, second))
        );
    }

    @Test
    @DisplayName("Test that rename fails when amount of lines in temp files is not the same as amount of given files")
    void testFailsBySize() throws Exception {
        final File namesStorage = new TempFileBuilder("test.txt").build();
        //write two lines
        Files.write(namesStorage.toPath(), List.of("first", "second"));
        final File fileToRename = new TempFileBuilder("toRename.txt").build();
        Assertions.assertThrows(
                IllegalStateException.class,
                //now temp file has two names but we want to rename only one
                () -> new Rename().renameFiles(namesStorage, List.of(fileToRename))
        );
    }
}
