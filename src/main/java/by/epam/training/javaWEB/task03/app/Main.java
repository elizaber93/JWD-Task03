package by.epam.training.javaWEB.task03.app;

import by.epam.training.javaWEB.task03.output.Printer;
import by.epam.training.javaWEB.task03.output.XMLConsolePrinter;
import by.epam.training.javaWEB.task03.parser.Parser;
import by.epam.training.javaWEB.task03.parser.XMLParser;
import by.epam.training.javaWEB.task03.reader.DataReader;
import by.epam.training.javaWEB.task03.reader.XMLDataReader;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DataReader fileReader = new XMLDataReader();
        Parser parser = new XMLParser();
        Printer printer = new XMLConsolePrinter();
        printer.print(parser.parse(fileReader.read("src/main/resources/xml_doc.txt")).get(0));
    }


}
