package rmf;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

final class ExecutionTestCase {

    @Test
    @DisplayName("Test that original file was renamed")
    void test() throws Exception {
        final File original = new TempFileBuilder("test").build();
        Files.write(original.toPath(), "Original file".getBytes());
        final File replace = new TempFileBuilder("new-name").build();
        replace.delete();
        new Execution().start(
                List.of(original),
                new MockedEditor(List.of(replace.getAbsolutePath()))
        );
        //now original file doesn't exist because it was renamed to name of 'replace' file
        Assertions.assertFalse(original.exists());
        Assertions.assertTrue(replace.exists());
        Assertions.assertEquals(
                new String(Files.readAllBytes(replace.toPath())).trim(),
                "Original file"
        );
    }

}
