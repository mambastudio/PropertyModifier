/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertymodifier.beans;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import propertymodifier.editors.EditorFactory;
import propertymodifier.editors.base.PropertyEditor;
import propertymodifier.editors.base.PropertyItem;

/**
 *
 * @author user
 */
public class DefaultEditorList implements Callback<PropertyItem, PropertyEditor<?>> {
    
    Map<Class<?>, Callback<PropertyItem, PropertyEditor<?>>> editors;
    
    public DefaultEditorList()
    {
        editors = FXCollections.observableHashMap();
        
        //if wrong editor is added, no error is shown in advance until runtime. Maybe redesign
        add(Color.class, item -> EditorFactory.createColorEditor(item));
        add(Enum.class, item -> EditorFactory.createEnumEditor(item));
        add(String.class, item ->  EditorFactory.createStringEditor(item));
    }
    
    public void add(Class<?> type, Callback<PropertyItem, PropertyEditor<?>> editor)
    {
        if(isNumber(type) || isBoolean(type))
            return;
        editors.put(type, editor);
    }
    
    @Override
    public PropertyEditor<?> call(PropertyItem item) {
        Class<?> type = item.getType();        
         
        if (isNumber(type))
            return EditorFactory.createNumericEditor(item);   
        else if(isBoolean(type))
            return EditorFactory.createBooleanEditor(item);      
        else if(editors.containsKey(type))
            return editors.get(type).call(item);
        return null; 
    }
    
    final Class<?>[] numericTypes = new Class[]{
        byte.class, Byte.class,
        short.class, Short.class,
        int.class, Integer.class,
        long.class, Long.class,
        float.class, Float.class,
        double.class, Double.class,
        BigInteger.class, BigDecimal.class
    };    
    
    // there should be better ways to do this
    private boolean isNumber(Class<?> type)  {
        if ( type == null ) return false;
        for (Class<?> cls : numericTypes) {
            if ( type == cls ) return true;
        }
        return false;
    }
    
    private boolean isBoolean(Class<?> type)
    {
        Class<?>[] booleanTypes = {boolean.class, Boolean.class};
        
        if ( type == null ) return false;
        for (Class<?> cls : booleanTypes) {
            if ( type == cls ) return true;
        }
        return false;
    }
}
