import java.io.IOException;
import java.io.FileWriter;

public class FileWrite {
    String fileName;
    int fileCount;
    String[] lines;


    FileWrite(String filename, int fileCount, String[] lines) {
        this.fileName=filename;
        this.fileCount=fileCount;
        this.lines=lines;
    }

    public void makeFile(int importArr, int deleteArr, String newFileName, String outputDir) throws IOException {
        //System.out.println(outputDir + newFileName + ".txt");
        FileWriter file = new FileWriter(outputDir + newFileName + ".txt");

        int i;
        for ( i = importArr+1; i < deleteArr; i++) {
            file.write(lines[i]);
            file.write("\n");
            file.flush();
        }
    }
}
