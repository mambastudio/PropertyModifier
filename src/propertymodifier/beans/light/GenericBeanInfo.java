/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertymodifier.beans.light;

/**
 *
 * @author user
 */
public class GenericBeanInfo implements BeanInfo{
    protected final BeanDescriptor _bean;
    protected final PropertyDescriptor[] _properties;

    GenericBeanInfo(BeanDescriptor bean, PropertyDescriptor[] properties) {
        _bean = bean;
        _properties = properties;
    }

    @Override
    public BeanDescriptor getBeanDescriptor() {
        return _bean;
    }

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        return _properties;
    }
}
