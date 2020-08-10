package fr.flashback083;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String line;
        String path;
        String filename;
        System.out.println("Please enter a path, example : E:/MXM/EXTRACTED/Data/Sound/GeneratedSoundBanks/French(France)/");
        Scanner scan = new Scanner(System.in);
        path = scan.next();
        if (!new File(path).exists()){
            System.out.println("Path not found");
            main(args);
        }
        System.out.println("Please enter the file name you want to read, example : VOX_Monster.txt");
        filename = scan.next();
        if (!new File(path+filename).exists()){
            System.out.println("File not found");
            main(args);
        }
        System.out.println(path+filename);
        Path file = new File(path+filename).toPath();
        try (InputStream in = Files.newInputStream(file);
             BufferedReader reader =
                     new BufferedReader(new InputStreamReader(in))) {
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith("Streamed")){
                    String[] list = line.split("\\s+");
                if (list.length>=3){
                    //System.out.println("number " + list[1]);
                    //System.out.println("name "  + list[2]);
                    if (new File(path+list[1]+".wem").exists()){
                        File file1 = new File(path+list[1]+".wem");
                        if (file1.renameTo(new File(path+list[2]+".wem"))){
                            System.out.println("Rename success for previous id : " + list[1] + " & name : " + list[2]);
                        }else {
                            System.out.println("Can't rename");
                        }
                    }else {
                        System.out.println("File not found");
                    }
                }
                }
            }
        } catch (IOException x) {
            System.err.println(x);
        }
    }


}
