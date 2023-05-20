package validator;

import java.io.File;

public class PathValidator {

    public static boolean validateFilePath(String path){
        File currentFile = new File(path);
        return currentFile.exists();
    }
}
