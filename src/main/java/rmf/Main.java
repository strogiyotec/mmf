package rmf;

import java.io.File;

public final class Main {
    public static void main(String[] args) throws Exception {
        if(args.length == 0){
            return;
        }
        new Editor().writeFillNames(
                new File("/home/strogiyotec/IdeaProjects/test/a3.txt"),
                new ProcessBuilder()
        );
    }
}
