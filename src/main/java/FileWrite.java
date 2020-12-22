import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileWrite {
    //String fileName;
    int fileCount;
    String[] lines;


    FileWrite( int fileCount, String[] lines) {
        this.fileCount=fileCount;
        this.lines=lines;
    }

    public void makeFile(int importArr, int deleteArr, String fileName, String outputDir) throws IOException {
        //System.out.println(outputDir + newFileName + ".txt");
        FileWriter file = new FileWriter(outputDir + fileName + ".txt");

        int i;
        outer: for ( i = importArr+1; i < deleteArr; i++) {
            String[] arrOfStr = lines[i].split(" ");    //Tjekker om der er overset et DELETE og bryder loopet hvis det findes.
            for (String word : arrOfStr) {
                if ("DELETE".equals(word)) {
                    break outer;
                }
            }

            file.write(lines[i]);
            file.write("\n");
            file.flush();
        }
    }
}
