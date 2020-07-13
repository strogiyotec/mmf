package rmf;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public final class TempFile {

    File create(final String[] fileNames) throws IOException {
        this.checkFilesExist(fileNames);
        final File tempFile = File.createTempFile(Defaults.APP_NAME, "txt");
        tempFile.deleteOnExit();

        Files.write(tempFile.toPath(), List.of(fileNames));
        return tempFile;
    }

    private void checkFilesExist(final String[] fileNames) {
        for (final String name : fileNames) {
            if (!Files.exists(Paths.get(name))) {
                throw new IllegalArgumentException(String.format("File %s doesn't exist", name));
            }
        }
    }
}
