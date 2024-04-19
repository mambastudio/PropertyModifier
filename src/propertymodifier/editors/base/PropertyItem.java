/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertymodifier.editors.base;

import java.util.Optional;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author user
 */
public interface PropertyItem {
    public Class<?> getType();
       
    public String getCategory();

    public String getName();

    public String getDescription();        

    public Object getValue();

    public void setValue(Object value);

    public Optional<ObservableValue<? extends Object>> getObservableValue();

    default public Optional<Class<? extends PropertyEditor<?>>> getPropertyEditorClass() {
        return Optional.empty();
    }

    default public boolean isEditable() {
        return true;
    }
}
