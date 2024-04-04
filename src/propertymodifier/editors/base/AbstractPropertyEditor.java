/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertymodifier.editors.base;

import javafx.beans.value.ObservableValue;
import javafx.scene.Node;

/**
 *
 * @author user
 * @param <T>
 * @param <C>
 */
public abstract class AbstractPropertyEditor<T, C extends Node> implements InterfacePropertyEditor<T> {
    private final AbstractBeanPropertyItem property;
    private final C control;    
       
    public AbstractPropertyEditor(AbstractBeanPropertyItem property, C control) 
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
   
    public final AbstractBeanPropertyItem getMBeanProperty() {
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
