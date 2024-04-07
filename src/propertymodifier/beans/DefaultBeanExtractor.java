/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertymodifier.beans;

import java.util.function.Function;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import propertymodifier.beans.light.BeanInfo;
import propertymodifier.beans.light.IntrospectionException;
import propertymodifier.beans.light.Introspector;
import propertymodifier.beans.light.PropertyDescriptor;
import propertymodifier.editors.base.PropertyExtractor;

/**
 *
 * @author user
 */
public class DefaultBeanExtractor implements PropertyExtractor<DefaultBeanItem> {

    @Override
    public ObservableList<DefaultBeanItem> getProperties(Object bean) {
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
                });
    }

    @Override
    public ObservableList<DefaultBeanItem> getProperties(Object bean, Function<String, String> displayNameCall) {
        // return getProperties(bean, (p)->{return true;});
        ObservableList<DefaultBeanItem> list = FXCollections.observableArrayList();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass(), Object.class);
            for (PropertyDescriptor p : beanInfo.getPropertyDescriptors()) {     
                //custom display name if any
                String displayName = displayNameCall.apply(p.getName());
                p.setDisplayName(displayName);
                
                //init bean property
                DefaultBeanItem property = new DefaultBeanItem(bean, p);
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
