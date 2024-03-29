/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertymodifier.beans.light;

import java.lang.ref.WeakReference;

/**
 *
 * @author user
 */
public final class BeanDescriptor extends FeatureDescriptor {
    private String _beanName;
    private final WeakReference<Class<?>> _beanClass;

    BeanDescriptor(Class<?> beanClass) {
        _beanName = beanClass.getSimpleName();
        _beanClass = new WeakReference<>(beanClass);
    }

    @Override
    public String getName() {
        return _beanName;
    }

    public Class<?> getBeanClass() {
        return _beanClass.get();
    }

    @Override
    public void setName(String name) {
        this._beanName = name;
    }
}
