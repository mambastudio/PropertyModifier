package propertymodifier;

import javafx.beans.property.*;

/**
 * Class Information
 * @author Your Name
 */
public class SimpleShape2D {

    private final SimpleStringProperty name;
    private final SimpleDoubleProperty width;
    private final SimpleDoubleProperty height;


    public SimpleShape2D() {
        name = new SimpleStringProperty();
        width = new SimpleDoubleProperty();
        height = new SimpleDoubleProperty();

    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public Double getWidth() {
        return width.get();
    }

    public void setWidth(Double width) {
        this.width.set(width);
    }

    public SimpleDoubleProperty widthProperty() {
        return width;
    }

    public Double getHeight() {
        return height.get();
    }

    public void setHeight(Double height) {
        this.height.set(height);
    }

    public SimpleDoubleProperty heightProperty() {
        return height;
    }
}