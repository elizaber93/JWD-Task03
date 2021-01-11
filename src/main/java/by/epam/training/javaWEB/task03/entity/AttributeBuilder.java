package by.epam.training.javaWEB.task03.entity;

public class AttributeBuilder {
    Attribute attribute = new Attribute();

    public Attribute build() {
        return this.attribute;
    }

    public AttributeBuilder setName(String name) {
        this.attribute.setName(name);
        return this;
    }

    public AttributeBuilder setValue(String value) {
        this.attribute.setValue(value);
        return this;
    }
}
