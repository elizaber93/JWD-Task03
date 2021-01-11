package by.epam.training.javaWEB.task03.reader;


import java.io.*;

public class XMLDataReader implements DataReader {
    public String read(String fileName) throws IOException {
        StringBuilder result = new StringBuilder();
        File file = new File(fileName);
        if (!file.exists()) {
            throw new IllegalArgumentException("Illegal file name");
        }
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String readString = reader.readLine();
        while (readString != null) {
            result.append(readString);
            readString = reader.readLine();
        }
        return result.toString();
    }
}
