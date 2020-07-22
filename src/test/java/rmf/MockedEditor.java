package rmf;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

/**
 * Mocked editor that stores predefined names in temp file.
 */
final class MockedEditor implements NamesEditor {

    private final List<String> names;

    MockedEditor(final List<String> names) {
        this.names = names;
    }

    @Override
    public File store(final List<File> toRename, final TempFileBuilder toStore) throws Exception {
        var tempFile = toStore.build();
        Files.write(tempFile.toPath(), this.names);
        return tempFile;
    }
}
