package rmf;

import java.util.HashSet;
import java.util.List;

final class Duplicates {

    void check(final List<String> args) {
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
