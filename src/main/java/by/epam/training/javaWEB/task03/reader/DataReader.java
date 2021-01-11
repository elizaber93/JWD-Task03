package by.epam.training.javaWEB.task03.reader;

import java.io.IOException;

public interface DataReader {
    String read(String fileName) throws IOException;
}
