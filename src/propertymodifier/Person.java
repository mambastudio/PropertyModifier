/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertymodifier;

/**
 *
 * @author user
 */
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Person
{
    private final StringProperty nameProperty;
    private final FloatProperty heightProperty;
    private final IntegerProperty ageProperty;
    private final DoubleProperty weightProperty;

    public Person()
    {
        this.nameProperty = new SimpleStringProperty();
        this.heightProperty = new SimpleFloatProperty();
        this.ageProperty = new SimpleIntegerProperty();
        this.weightProperty = new SimpleDoubleProperty();
    }

    public String getName()
    {
        return this.nameProperty.get();
    }

    public void setName(String name)
    {
        this.nameProperty.set(name);
    }

    public StringProperty nameProperty()
    {
        return this.nameProperty;
    }

    public float getHeight()
    {
        return this.heightProperty.get();
    }

    public void setHeight(float height)
    {
        this.heightProperty.set(height);
    }

    public FloatProperty heightProperty()
    {
        return this.heightProperty;
    }

    public int getAge()
    {
        return this.ageProperty.get();
    }

    public void setAge(int age)
    {
        this.ageProperty.set(age);
    }

    public IntegerProperty ageProperty()
    {
        return this.ageProperty;
    }

    public double getWeight()
    {
        return this.weightProperty.get();
    }

    public void setWeight(double weight)
    {
        this.weightProperty.set(weight);
    }

    public DoubleProperty weightProperty()
    {
        return this.weightProperty;
    }
}
