package rmf;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

final class Rename {

    /**
     * Rename files.
     * Rename given list of files with names from tempFile
     *
     * @param tempFile Temp file that contain new file names
     * @param files    Files to rename
     * @throws IOException                If failed
     * @throws IllegalStateException      If amount of names in temp file is not equal to amount of files
     * @throws FileAlreadyExistsException If at least one file with name from temp file exists
     * @throws IllegalStateException      If at least one name in temp file is a directory instead of file name
     * @throws IllegalArgumentException   If temp file has duplicate names
     */
    void renameFiles(final File tempFile, final List<File> files) throws IOException {
        var newNames = Files.readAllLines(tempFile.toPath());
        this.validate(files, newNames);
        var oldToNew = this.toFiles(files, newNames);
        for (var entry : oldToNew.entrySet()) {
            Files.move(
                    entry.getKey().toPath(),
                    entry.getValue().toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );
        }
    }

    private void validate(final List<File> files, final List<String> newNames) {
        if (newNames.size() != files.size()) {
            throw new IllegalStateException("rmv doesn't delete or add files");
        }
        this.checkDuplicateNames(newNames);
    }

    /**
     * Map files with their new names.
     *
     * @param files    Files to rename
     * @param newNames New names
     * @return Map where key is file with old name and value is file with new name
     * @throws IOException If failed
     */
    private Map<File, File> toFiles(final List<File> files, final List<String> newNames) throws IOException {
        var oldAndNew = new HashMap<File, File>(files.size() + 2);
        for (int i = 0; i < files.size(); i++) {
            var newFile = new File(newNames.get(i));
            if (newFile.compareTo(files.get(i)) != 0) {
                //If given file is directory
                if (newFile.isDirectory()) {
                    throw new IllegalStateException(String.format("File %s is a directory", newFile.getName()));
                }
                //If file already exists then stop
                if (newFile.exists()) {
                    throw new FileAlreadyExistsException(
                            String.format(
                                    "File %s already exists", newFile.getName()
                            )
                    );
                }
                //Create sub folders if file is moved to another
                newFile.mkdirs();
                newFile.createNewFile();
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
