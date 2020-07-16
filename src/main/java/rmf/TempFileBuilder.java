package rmf;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

final class TempFileBuilder {

    private final Callable<File> callable;


    TempFileBuilder(final String name) {
        this.callable = () -> {
            var tempFile = File.createTempFile(name,"test");
            tempFile.deleteOnExit();
            return tempFile;
        };
    }

    /**
     * Ctor.
     * Create temp file that contains name of each file
     *
     * @param files Files
     */
    TempFileBuilder(final List<File> files) {
        this.callable = () -> {
            checkFilesExist(files);
            var tempFile = File.createTempFile(Defaults.APP_NAME, "txt");
            tempFile.deleteOnExit();
            Files.write(
                    tempFile.toPath(),
                    files.stream().map(File::getName).collect(Collectors.toList())
            );
            return tempFile;
        };
    }

    File build() throws Exception {
        return this.callable.call();
    }

    private static void checkFilesExist(final List<File> files) {
        for (var file : files) {
            if (!file.exists()) {
                throw new IllegalArgumentException(
                        String.format(
                                "File %s doesn't exist",
                                file.getName()
                        )
                );
            }
        }
    }
}
