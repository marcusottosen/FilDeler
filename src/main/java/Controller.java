import java.io.IOException;

public class Controller{
    String filename ;
    String outputDir;

    public Controller(String filename, String outputDir) {
        this.filename = filename;
        this.outputDir = outputDir+"/";
    }


    public void run() throws IOException {
        FileRead read = new FileRead(filename);
        read.readFile();

        FileController write = new FileController(filename, read.impArr, read.delArr, read.lines, outputDir);
        write.writeFile();


        /*
         *
         * EmpVacationTransfer har fejl
         *
         * Programmet tjekker ikke om den sidste indeksering er tom, da den løber tør for indexes. (EmpVacationTransfer)
         * ^Den skriver slet ikke til den sidste fil - da programmet ikke ved til hvilken linje den skal skrive til, men kun fra.
         *
         */
    }
}
