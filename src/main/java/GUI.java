import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GUI {
    private JPanel rootPanel;
    private JButton inputBut;
    private JButton outputBut;
    private JLabel chooseFileL;
    private JLabel chooseOutputL;
    private JLabel inputDistL;
    private JLabel outputDistL;
    private JButton runBut;
    private JLabel runMessage;
    String filename ;
    String outputDir;
    boolean inputDone;
    boolean outputDone;


    public GUI() {
        JFrame frame = new JFrame("Filsplitter");
        frame.add(rootPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        inputBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser inDist = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                //JFileChooser inDist = new JFileChooser(new File("C:\\Users\\Marcus PC\\OneDrive\\Random stuff\\ProMarkFilOpdeler\\testing"));
                inDist.showSaveDialog(null);
                //inputDist.setText(j.getSelectedFile().getAbsolutePath());
                inputDistL.setText(inDist.getSelectedFile().getName());
                filename = inDist.getSelectedFile().getAbsolutePath();
                inputBut();
            }
        });

        outputBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser outDist = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                //JFileChooser outDist = new JFileChooser(new File("C:\\Users\\Marcus PC\\OneDrive\\Random stuff\\ProMarkFilOpdeler\\testing"));
                outDist.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                outDist.showSaveDialog(null);
                outputDistL.setText(outDist.getSelectedFile().getName());
                outputDir = outDist.getSelectedFile().getAbsolutePath();
                outputBut();
            }
        });

        runBut.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Controller start = new Controller(filename, outputDir);
                    try {
                        start.run();    //Kører resten af programmet
                    } catch (IOException ioException) {
                        runMessage.setText("Fejl i indlæsning af fil!");
                        ioException.printStackTrace();
                    }
                    runMessage.setText("Filer oprettet");
                }
            });
    }

    public void inputBut(){
        inputDone=!inputDone;
        if (inputDone && outputDone) {
            runBut.setEnabled(true);
            runBut.setText("Kør");
            runBut.setBackground(Color.decode("#55efc4"));
        }
    }

    public void outputBut(){
        outputDone=!outputDone;
        if (inputDone && outputDone) {
            runBut.setEnabled(true);
            runBut.setText("Kør");
            runBut.setBackground(Color.decode("#55efc4"));
        }
    }

    public static void main(String[] args) throws IOException {
        new GUI();
    }
}