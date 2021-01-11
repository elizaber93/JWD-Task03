package by.epam.training.javaWEB.task03.parser;


import by.epam.training.javaWEB.task03.entity.Attribute;
import by.epam.training.javaWEB.task03.entity.AttributeBuilder;
import by.epam.training.javaWEB.task03.entity.Element;
import by.epam.training.javaWEB.task03.entity.ElementBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLParser implements Parser {

    private final static String OPEN_TAG = "<([a-zA-Z\\-]+)[\\s]*([a-zA-Z0-9=\\s\"]*)>";
    private final static String TAG = "<[a-z-/=\\s\"0-9]+>";
    private final static String CLOSE_TAG = "</[a-zA-Z\\-]+>";
    private final static String STRING_CONTENT = "[a-zA-Z0-9$.\\s]+";

    /***
     * Метод преобразующий xml-строку в стек из отдльных строк, содержащих либо теги, либо строковое содержимое тега
     * @param inputString xml-строка
     * @return стек linkedList
     */
    public LinkedList<String> getStack(String inputString) {
        LinkedList<String> list = new LinkedList<>();
        String element = "";
        while (!inputString.equals("")) {
            if (inputString.startsWith("<")) {
                element = getTag(inputString);
                inputString = inputString.replaceFirst(TAG,"").trim();

            } else {
                element = getStringContent(inputString);
                inputString = inputString.replaceFirst(STRING_CONTENT,"").trim();
            }
            list.add(element);
        }
        return list;
    }

    /***
     * Метод возвращающий первый тег (открывающий или закрывающий) из строки
     * @param inputString исходная строка
     * @return строка, содержащая только тег
     */
    public String getTag(String inputString) {
        Pattern pattern = Pattern.compile(TAG);
        Matcher matcher = pattern.matcher(inputString);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    /***
     * Метод, получающий получающий строку, отображающую стрококвое содержимое тега
     * @param inputString исходная строка
     * @return строка контента
     */
    public String getStringContent(String inputString) {
        Pattern pattern = Pattern.compile(STRING_CONTENT);
        Matcher matcher = pattern.matcher(inputString);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    /***
     * Метод преобразующий стек строг в древо элементов
     * @param list стек строк
     * @return root
     */
    public Element parseStack(LinkedList<String> list) {
        LinkedList<Element> elements = new LinkedList<>();
         for (String o : list) {
             if (isOpenTag(o)) {

                     elements.add(getElement(o));

             } else if (isCloseTag(o)) {
                     if (elements.size()!=1) {
                         Element element = elements.pollLast();
                         elements.getLast().addElement(element);
                     }
                 } else {
                     elements.getLast().setContent(o);
                 }
             }
        return elements.peekFirst();
    }

    /***
     * Метод, преобразующий строку открывающего тега в элемент с заполненными полями названия и списком атрибутов
     * @param inputString строка тега
     * @return элемент
     */
    public Element getElement(String inputString) {
        Pattern openingTagPattern = Pattern.compile(OPEN_TAG);
        Matcher openingTagMatcher = openingTagPattern.matcher(inputString);
        if (openingTagMatcher.find()) {
            return new ElementBuilder()
                    .setTitle(openingTagMatcher.group(1))
                    .setAttributeList(getAttribute(openingTagMatcher.group(2)))
                    .build();
        }
        return null;
    }

    /***
     * Метод осуществляющий проверку открывающий ли тег
     * @param stackElement эемент из стека строк
     * @return true, если является
     */
     public boolean isOpenTag(String stackElement) {
         return stackElement.matches(OPEN_TAG);
     }

    /***
     * Метод осуществляющий проверку закрывающий ли тег
     * @param stackElement элемент из стека строк
     * @return true, если является
     */
     public boolean isCloseTag(String stackElement) {
        return stackElement.matches(CLOSE_TAG);
     }

    /***
     * метод преобазующий xml-строку в массив объектов, в данном случае элементов
     * @param xmlString xml-строка
     * @return в случае структуры, представляющей дерево, массив элементов, состоящий из одного корневого элемента
     */
    @Override
    public ArrayList<Object> parse(String xmlString) {
        xmlString = replaceSpaces(xmlString);
        LinkedList<String> stack = getStack(xmlString);
        ArrayList<Object> result = new ArrayList<>();
        result.add(parseStack(stack));
        return result;
    }

    /***
     * Метод, удаляющий лишние пробелы
     * @param string строка
     * @return отформатированная строка
     */
    public String replaceSpaces(String string) {
        return string.replaceAll("[\\s]+"," ");
    }

    /***
     * Метод получающий лист атрибутов тега
     * @param attributeString строка из открывающего тега, содержащая атрибуты тега
     * @return лист с атрибутами
     */
    public List<Attribute> getAttribute(String attributeString) {
        if (attributeString != null){
            List<Attribute> attributes = new ArrayList<>();
            Pattern attributePattern = Pattern.compile("([a-zA-Z0-9_]+)[\\s]*=[\\s]*\"([a-zA-Z0-9_\\s]+)\"");
            Matcher attributeMatcher = attributePattern.matcher(attributeString);
            while (attributeMatcher.find()) {
                attributes.add(new AttributeBuilder()
                        .setName(attributeMatcher.group(1))
                        .setValue(attributeMatcher.group(2))
                        .build());
            }
            return attributes;
        }
        return null;
    }
}
