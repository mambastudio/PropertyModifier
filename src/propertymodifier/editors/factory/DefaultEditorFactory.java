/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertymodifier.editors.factory;

import java.math.BigDecimal;
import java.math.BigInteger;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import propertymodifier.editors.Editors;
import propertymodifier.editors.base.InterfacePropertyEditor;
import propertymodifier.editors.base.AbstractBeanPropertyItem;

/**
 *
 * @author user
 */
public class DefaultEditorFactory implements Callback<AbstractBeanPropertyItem, InterfacePropertyEditor<?>> {
    
    @Override
    public InterfacePropertyEditor<?> call(AbstractBeanPropertyItem item) {
        Class<?> type = item.getType();
        
         
        if (isNumber(type))
            return Editors.createNumericEditor(item);        
        else if(type == Color.class)
            return Editors.createColorEditor(item);        
        else if(Enum.class.isAssignableFrom(type))        
            return Editors.createEnumEditor(item);     
        else if(isBoolean(type))
            return Editors.createBooleanEditor(item);      
        else if(type == String.class)
            return Editors.createStringEditor(item);
        return null; 
    }
    
    private static final Class<?>[] numericTypes = new Class[]{
        byte.class, Byte.class,
        short.class, Short.class,
        int.class, Integer.class,
        long.class, Long.class,
        float.class, Float.class,
        double.class, Double.class,
        BigInteger.class, BigDecimal.class
    };    
    
    // there should be better ways to do this
    private static boolean isNumber(Class<?> type)  {
        if ( type == null ) return false;
        for (Class<?> cls : numericTypes) {
            if ( type == cls ) return true;
        }
        return false;
    }
    
    private static boolean isBoolean(Class<?> type)
    {
        Class<?>[] booleanTypes = {boolean.class, Boolean.class};
        
        if ( type == null ) return false;
        for (Class<?> cls : booleanTypes) {
            if ( type == cls ) return true;
        }
        return false;
    }
}
