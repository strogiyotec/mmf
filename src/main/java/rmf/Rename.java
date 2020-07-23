package rmf;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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
        var newNames = Files.readAllLines(tempFile.toPath());
        this.validate(files, newNames);
        var oldToNew = this.toFiles(files, newNames);
        for (var entry : oldToNew.entrySet()) {
            Files.move(entry.getKey().toPath(), entry.getValue().toPath());
        }
    }

    private void validate(final List<File> files, final List<String> newNames) {
        if (newNames.size() != files.size()) {
            throw new IllegalStateException("rmv doesn't delete or add files");
        }
        this.checkDuplicateNames(newNames);
    }

    private Map<File, File> toFiles(final List<File> files, final List<String> newNames) {
        var oldAndNew = new HashMap<File, File>(files.size() + 2);
        for (int i = 0; i < files.size(); i++) {
            var newFile = new File(newNames.get(i));
            if (newFile.compareTo(files.get(i)) != 0) {
                if (newFile.exists()) {
                    throw new IllegalStateException(String.format("File %s already exists", newFile.getName()));
                }
                if (newFile.isDirectory()) {
                    throw new IllegalStateException(String.format("File %s is a directory", newFile.getName()));
                }
                final File file = newFile.getParentFile();
                if (file != null) {
                    file.mkdir();
                }
                oldAndNew.put(files.get(i), newFile);
            }
        }
        return oldAndNew;
    }

    private void checkDuplicateNames(final List<String> args) {
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
