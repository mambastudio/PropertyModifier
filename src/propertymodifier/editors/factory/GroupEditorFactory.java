/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertymodifier.editors.factory;

import javafx.util.Callback;
import propertymodifier.editors.base.AbstractBeanPropertyItem;
import propertymodifier.editors.base.InterfacePropertyEditor;

/**
 *
 * @author user
 */
public class GroupEditorFactory implements Callback<AbstractBeanPropertyItem, InterfacePropertyEditor<?>> {

    
    @Override
    public InterfacePropertyEditor<?> call(AbstractBeanPropertyItem param) {
        Class<?> type = param.getType();
        
        return null;
    }
    
}
