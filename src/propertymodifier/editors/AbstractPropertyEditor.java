/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertymodifier.editors;

import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import propertymodifier.editors.base.PropertyEditor;
import propertymodifier.editors.base.PropertyItem;

/**
 *
 * @author user
 * @param <T>
 * @param <C>
 */
public abstract class AbstractPropertyEditor<T, C extends Node> implements PropertyEditor<T> {
    private final PropertyItem property;
    private final C control;    
       
    public AbstractPropertyEditor(PropertyItem property, C control) 
    {        
        this.control = control;
        this.property = property;
        this.initEditorValue();
        
        getEditorObservableValue().addListener(
                (o, ov, nv)->{                           
                    setPropertyValue(nv);
                });        
    }
    
    protected abstract ObservableValue<T> getEditorObservableValue();
   
    public final PropertyItem getMBeanProperty() {
        return property;
    }
     
    @Override 
    public C getEditor() {
        return control;
    }
    
    @Override
    public void setPropertyValue(T value) {
        getMBeanProperty().setValue(value);
    } 
    
    @Override     
    public T getPropertyValue() {
        return (T) getMBeanProperty().getValue();
    }    
}
