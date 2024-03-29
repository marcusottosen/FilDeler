import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class FileRead  {
    String[] lines;
    int numberOfLines = 0;
    int importHits = 0;
    int deleteHits = 0;
    int deleteAllHits = 0;
    ArrayList<Integer> impArr = new ArrayList<Integer>();
    ArrayList<Integer> delArr = new ArrayList<>();
    ArrayList<Integer> delRemoveArr = new ArrayList<Integer>();
    private String fileName;

    public FileRead(String fileName) {
        this.fileName=fileName;
    }


    public void readFile() throws IOException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file, "ISO_8859_1");
        System.out.print("number of lines ");
        numberOfLines = (int) Files.lines(Paths.get(fileName), StandardCharsets.ISO_8859_1).count(); // gets number of lines in file
        System.out.println("is: " + numberOfLines);

        lines = new String[numberOfLines];
        System.out.print("FILENAME: ");
        System.out.println(fileName);

        // Adds each line in file to array
        int i = 0;
        while (scanner.hasNextLine()) {     //TODO Stops at line 525?????
            lines[i] = scanner.nextLine();
            i++;
        }
        System.out.println("i ==== " + i);
        scanner.close();

        makeHitArrays(lines);
        removeDelIndex();
        removeEmptyIndex();

        System.out.println("Number of lines: " + numberOfLines);
        System.out.println("Inddelinger i alt: " + (deleteHits+deleteAllHits));
        System.out.println("Der fjernes: " + ((deleteHits+deleteAllHits)-importHits));
        System.out.println("Efter fjernelse af DELETE og tomme inddelinger / antal filer der laves: " + importHits);
        System.out.println("delArr: " + delArr);
        System.out.println("impArr: " + impArr);
        System.out.println("delRemoveArr: " + delRemoveArr);
    }

    // Hvis DELETEALL, DELETE eller IMPORT findes i linjen, tilføj linjens nummer i arrays.
    public void makeHitArrays(String[] lines){
        int hits=0;
        for (int j = 0; j < numberOfLines; j++) {
            System.out.println(j + " out of: " + numberOfLines);
            System.out.println(lines[j]);
            try {
                String[] arrOfStr = lines[j].split(" ");    //Deler hvert string index op ved mellemrum
                for (String word : arrOfStr) {

                    System.out.println(word);
                    if ("DELETEALL".equals(word)) {
                        delArr.add(j);
                        deleteAllHits++;
                    } else if ("DELETE".equals(word)) {
                        delArr.add(j);
                        delRemoveArr.add(hits); //hits svarer til det index på delArr+1 og impArr som skal fjernes.
                        deleteHits++;
                    } else if ("IMPORT".equals(word)) {
                        impArr.add(j);
                        hits++;
                        importHits++;
                    }
                }
            }catch (Exception e){
                System.out.println("ERROR: " + e);
            }
        }
    }

    public void removeDelIndex(){   //Fjerner alle index' der starter på DELETE
        for (int i = delRemoveArr.size()-1; i >= 0 ; i--) {
            int index = delRemoveArr.get(i);
            delArr.remove(index);
            impArr.remove(index);
            importHits--;
        }
    }

    public void removeEmptyIndex(){ //Fjerner alle tommer index'
        int size = impArr.size()-1;
        try {
            for (int i = size; i >= 1; i--) {
                int imp = impArr.get(i-1);
                int del =  delArr.get(i);

                if (del - 1 == imp) {
                    delArr.remove(i);
                    impArr.remove(i-1);
                    importHits--;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//programmet tilføger alt fra DELETEALL/IMPORT til den næsten DELETEALL