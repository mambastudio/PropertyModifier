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
public interface BeanInfo {
    BeanDescriptor getBeanDescriptor();

    PropertyDescriptor[] getPropertyDescriptors();
}
