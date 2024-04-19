/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertymodifier.editors.base;

import java.util.function.Function;
import javafx.collections.ObservableList;

/**
 *
 * @author user
 * @param <PropertyItem>
 */
public interface PropertyExtractor<PropertyItem> {
    public ObservableList<PropertyItem> getProperties(final Object bean);
    public ObservableList<PropertyItem> getProperties(final Object bean, final Function<String, String> displayNameCall);
    
}
