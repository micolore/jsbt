package com.kubrick.jsbt.cglib;

import java.beans.PropertyChangeListener;

/**
 * @author k
 * @version 1.0.0
 * @ClassName Bean
 * @description: TODO
 * @date 2021/3/13 下午7:04
 */
public abstract class Bean implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    String sampleProperty;

    abstract public void addPropertyChangeListener(PropertyChangeListener listener);

    abstract public void removePropertyChangeListener(PropertyChangeListener listener);

    public String getSampleProperty() {
        return sampleProperty;
    }

    public void setSampleProperty(String value) {
        this.sampleProperty = value;
    }

    @Override
    public String toString() {
        return "sampleProperty is " + sampleProperty;
    }

}
