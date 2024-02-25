/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertymodifier.beans;

import propertymodifier.editors.base.ConsumerVoid;
import java.util.function.Function;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import propertymodifier.beans.light.IntrospectionException;
import propertymodifier.beans.light.Introspector;
import propertymodifier.beans.light.PropertyDescriptor;
import propertymodifier.beans.light.BeanInfo;
import propertymodifier.editors.base.AbstractBeanPropertyItem;

/**
 *
 * @author user
 */
public class BeanPropertyUtility {
    
    public static ObservableList<AbstractBeanPropertyItem> getProperties(final Object bean, ConsumerVoid consume)
    {
        return getProperties(
                bean, 
                //https://stackoverflow.com/questions/3752636/java-split-string-when-an-uppercase-letter-is-found
                //https://attacomsian.com/blog/capitalize-first-letter-of-string-java#:~:text=The%20simplest%20way%20to%20capitalize,substring(0%2C%201).
                (p)->{
                    String[] r = p.split("(?=\\p{Upper})");
                    r[0] = r[0].substring(0, 1).toUpperCase() + r[0].substring(1);
                    StringBuilder string = new StringBuilder();
                    for(String str: r)
                    {
                        string.append(str).append(" ");
                    }
                    return string.toString();
                }, 
                consume);
    }
    
    public static ObservableList<AbstractBeanPropertyItem> getProperties(final Object bean, final Function<String, String> displayNameCall, ConsumerVoid consume)
    {
       // return getProperties(bean, (p)->{return true;});
        ObservableList<AbstractBeanPropertyItem> list = FXCollections.observableArrayList();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass(), Object.class);
            for (PropertyDescriptor p : beanInfo.getPropertyDescriptors()) {     
                //custom display name if any
                String displayName = displayNameCall.apply(p.getName());
                p.setDisplayName(displayName);
                
                //init bean property
                BeanProperty property = new BeanProperty(bean, p, consume);
                if(property.isObservable() && property.isEditable())
                {                            
                    list.add(property);
                }                
            }
         } catch (IntrospectionException e) {
            System.err.println(e);
        }
        //Collections.reverse(list);
        return list;
    }    
}
