package rmf;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;

final class Rename {

    /**
     * Rename files.
     * Rename given list files with names from tempFile
     *
     * @param tempFile Temp file that contain new file names
     * @param files    Files to rename
     * @throws IOException If failed
     */
    void renameFiles(final File tempFile, final List<File> files) throws IOException {
        final List<String> newNames = Files.readAllLines(tempFile.toPath());
        this.validate(files, newNames);
        for (int i = 0; i < files.size(); i++) {
            if (!newNames.get(i).equals(files.get(i).getName())) {
                Files.move(files.get(i).toPath(), Paths.get(newNames.get(i)));
            }
        }
    }

    private void validate(final List<File> files, final List<String> newNames) {
        if (newNames.size() != files.size()) {
            throw new IllegalStateException("rmv doesn't delete or add files");
        }
        this.checkDuplicates(newNames);
    }

    private void checkDuplicates(final List<String> args) {
        var set = new HashSet<String>(args.size());
        for (var arg : args) {
            if (!set.add(arg)) {
                throw new IllegalArgumentException(
                        String.format(
                                "Duplicate file name %s", arg
                        )
                );
            }
        }
    }

}
