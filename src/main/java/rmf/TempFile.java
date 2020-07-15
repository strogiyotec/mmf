package rmf;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public final class TempFile {

    /**
     * Create temp file.
     * Write all fileNames to temp file
     * Delete this file on exit
     *
     * @param files Files
     * @return Temp file
     * @throws IOException              If failed
     * @throws IllegalArgumentException If at least one file doesn't exist
     */
    File create(final List<File> files) throws IOException {
        this.checkFilesExist(files);
        final File tempFile = File.createTempFile(Defaults.APP_NAME, "txt");
        tempFile.deleteOnExit();
        Files.write(
                tempFile.toPath(),
                files.stream().map(File::getName).collect(Collectors.toList())
        );
        return tempFile;
    }

    private void checkFilesExist(final List<File> files) {
        for (final File file : files) {
            if (!file.exists()) {
                throw new IllegalArgumentException(String.format("File %s doesn't exist", file.getName()));
            }
        }
    }
}
