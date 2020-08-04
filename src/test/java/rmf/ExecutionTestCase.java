package rmf;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.Collections;
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
                new MockedEditor(List.of(replace.getAbsolutePath())),
                System.out
        );
        //now original file doesn't exist because it was renamed to name of 'replace' file
        Assertions.assertFalse(original.exists());
        Assertions.assertTrue(replace.exists());
        Assertions.assertEquals(
                new String(Files.readAllBytes(replace.toPath())).trim(),
                "Original file"
        );
    }

    @Test
    @DisplayName("Test that in case of error, message will be printed into print stream")
    void testErrorPrint() throws Exception {
        try (var stream = new ByteArrayOutputStream()) {
            try (var print = new PrintStream(stream)) {
                new Execution()
                        .start(
                                Collections.singletonList(
                                        new TempFileBuilder("testFileToRename").build()
                                ),
                                new MockedEditor(
                                        List.of("first", "second")
                                ),
                                print
                        );
                Assertions.assertTrue(stream.toString().contains("rmv doesn't delete or add files"));
            }
        }
    }

    @Test
    void testVersion() throws IOException {
        try (var stream = new ByteArrayOutputStream()) {
            try (var print = new PrintStream(stream)) {
                new Execution()
                        .start(
                                Collections.emptyList(),
                                new MockedEditor(Collections.emptyList()),
                                print
                        );
                Assertions.assertTrue(stream.toString().contains(String.format("rmf %s", Defaults.VERSION)));
            }
        }
    }
}
