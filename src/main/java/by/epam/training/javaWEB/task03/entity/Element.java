package by.epam.training.javaWEB.task03.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Element implements Serializable {
    private String title;
    private String content;
    private List<Element> elementList = new LinkedList<>();
    private List<Attribute> attributeList = new ArrayList<>();

    public Element() {
    }

    public Element(String title, String content, List<Attribute> attributeList) {
        this.title = title;
        this.content = content;
        this.attributeList = attributeList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Attribute> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List<Attribute> attributeList) {
        this.attributeList = attributeList;
    }

    public List<Element> getElementList() {
        return elementList;
    }

    public void setElementList(List<Element> elementList) {
        this.elementList = elementList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Element)) return false;
        Element element = (Element) o;
        return title.equals(element.title) &&
                content.equals(element.content) &&
                Objects.equals(attributeList, element.attributeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, content, attributeList);
    }

    @Override
    public String  toString() {
        return "Element{\n" +
                "title='" + title + "'\n" +
                "content='" + content + "'\n" +
                "elementList=" + elementList +
                "\nattributeList=" + attributeList +
                '}';
    }

    public void addElement(Element element) {
        this.elementList.add(element);
    }
}
