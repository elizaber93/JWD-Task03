package by.epam.training.javaWEB.task03.output;

import by.epam.training.javaWEB.task03.entity.Element;

public class XMLConsolePrinter implements Printer{

    @Override
    public void print(Object object) {
        if (object.getClass().getSimpleName().equals("Element")) {
            getInfo((Element)object);
        }
    }

    public void getInfo(Element element) {
        if (element.getAttributeList().size()!=0) {
            System.out.println(element.getAttributeList().get(0).getValue()+".");
        }
        if (element.getContent() != null) {
            System.out.print(element.getContent());
        }
        if (element.getElementList().size() != 0) {
            for (int i = 0; i < element.getElementList().size(); i++) {
                if (i != 0 && element.getAttributeList().size()!=0) System.out.print(" - ");
                getInfo(element.getElementList().get(i));
            }
            System.out.print("\n");
        }
    }
}
