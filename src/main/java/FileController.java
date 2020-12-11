import java.io.IOException;
import java.util.ArrayList;

public class FileController{
    String fileName;
    String outputDir;
    String[] lines;
    ArrayList<Integer> impArr;
    ArrayList<Integer> delArr;

    FileController(String filename,  ArrayList<Integer> impArr, ArrayList<Integer> delArr, String[] lines, String outputDir) {
        this.impArr = impArr;
        this.delArr = delArr;
        this.fileName = filename;
        this.outputDir = outputDir;
        this.lines = lines;
    }

    public String getFileName(int fileCount){
        String nameLine;
        String name;
        nameLine = lines[Integer.parseInt(String.valueOf(impArr.get(fileCount)))]; //nameLine = hele linjens string

        String[] lineArr = nameLine.split(" "); //deler linjen op ved mellemrum
        name = lineArr[1];

        //System.out.println(name);     //Printer alle navnene til filerne
        return name;
    }

    public void writeFile() throws IOException {
        FileWrite[] myName = new FileWrite[impArr.size()];
        delArr.add(delArr.size()+1);        //Tilføjer ekstra index på delArr for at undgå outOfBounds.
        for (int fileCount = 0; fileCount < impArr.size() ; fileCount++) {

            myName[fileCount] = new FileWrite(fileName, fileCount, lines);
            myName[fileCount].makeFile(impArr.get(fileCount), delArr.get(fileCount+1), getFileName(fileCount), outputDir);
        }
    }
}
