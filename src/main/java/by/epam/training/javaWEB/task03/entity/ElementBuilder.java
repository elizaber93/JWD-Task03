package by.epam.training.javaWEB.task03.entity;

import java.util.List;

public class ElementBuilder {
    Element element = new Element();

    public Element build() {
        return element;
    }

    public ElementBuilder setTitle(String title) {
        this.element.setTitle(title);
        return this;
    }

    public ElementBuilder setContent(String content) {
        this.element.setContent(content);
        return this;
    }

    public ElementBuilder addElement(Element element) {
        List<Element> elementList = this.element.getElementList();
        elementList.add(element);
        this.element.setElementList(elementList);
        return this;
    }

    public ElementBuilder addAttribute(Attribute attribute) {
        List<Attribute> attributeList = this.element.getAttributeList();
        attributeList.add(attribute);
        this.element.setAttributeList(attributeList);
        return this;
    }

    public ElementBuilder setElements(List<Element> elements) {
        this.element.setElementList(elements);
        return this;
    }

    public ElementBuilder setAttributeList(List<Attribute> attributes) {
        this.element.setAttributeList(attributes);
        return this;
    }



}
