/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertymodifier.editors;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.effect.Blend;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.MotionBlur;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;
import propertymodifier.editors.base.PropertyEditor;
import propertymodifier.editors.base.PropertyItem;

/**
 *
 * @author user
 */
public class EditorFactory {
    private EditorFactory()
    {
        
    }
    
    public static final PropertyEditor<?> createNumericEditor(PropertyItem propertyItem) {
        
        return new AbstractPropertyEditor<Number, NumericField>(
                propertyItem, 
                new NumericField( (Class<? extends Number>) propertyItem.getType())) 
                {                           
                    @Override
                    protected ObservableValue<Number> getEditorObservableValue() {
                        return getEditor().valueProperty();
                    }
                    
                    @Override
                    public void initEditorValue() {       
                        
                        getEditor().setText(this.getPropertyValue().toString());                        
                    }
                };
    }
    
    public static final PropertyEditor<?> createColorEditor(PropertyItem propertyItem) {
        
        return new AbstractPropertyEditor<Color, ColorPicker>(
                propertyItem, 
                new ColorPicker()) 
                {                           
                    @Override
                    protected ObservableValue<Color> getEditorObservableValue() {
                        return getEditor().valueProperty();
                    }
                    
                    @Override
                    public void initEditorValue() {  
                        getEditor().setValue(this.getPropertyValue());                        
                    }
                };
    }
    
    public static final PropertyEditor<?> createStringEditor(PropertyItem propertyItem) {
        TextField field = new TextField();
        return new AbstractPropertyEditor<String, TextField>(
                propertyItem, 
                field) 
                {                           
                    @Override
                    protected StringProperty getEditorObservableValue() {
                        return getEditor().textProperty();
                    }
                    
                    @Override
                    public void initEditorValue() {                           
                        getEditor().setText(this.getPropertyValue());                        
                    }
                };
    }
    
    public static final PropertyEditor<?> createBooleanEditor(PropertyItem propertyItem) {
        
        return new AbstractPropertyEditor<Boolean, CheckBox>(
                propertyItem, 
                new CheckBox()) 
                {                  
                
                    @Override
                    protected ObservableValue<Boolean> getEditorObservableValue() {
                        return getEditor().selectedProperty();
                    }
                    
                    @Override
                    public void initEditorValue() {                        
                        getEditor().setSelected(this.getPropertyValue());                        
                    }
                };
    }
    
    public static final PropertyEditor<?> createEnumEditor(PropertyItem propertyItem) {
        Enum enumValue = (Enum) propertyItem.getValue();
        ObservableList<Enum> enumValueList = FXCollections.observableArrayList(enumValue.getClass().getEnumConstants());        
        
        return new AbstractPropertyEditor<Enum, ComboBox>(
                propertyItem, 
                new ComboBox(enumValueList)) 
                {                           
                    @Override
                    protected ObservableValue<Enum> getEditorObservableValue() {
                        return getEditor().valueProperty();
                    }
                    
                    @Override
                    public void initEditorValue() {                        
                        getEditor().setValue(this.getPropertyValue());                        
                    }
                };
    }
    
    public static final PropertyEditor<?> createEffectEditor(PropertyItem propertyItem) {
        ObservableList<Effect> effectValueList = FXCollections.observableArrayList();        
        effectValueList.add(new DropShadow());
        effectValueList.add(new BoxBlur());
        effectValueList.add(new Reflection());
        effectValueList.add(new Blend());
        effectValueList.add(new Bloom());
        effectValueList.add(new MotionBlur());
        
        
        
        ComboBox<Effect> comboBox = new ComboBox(effectValueList);
        comboBox.setMaxWidth(Double.MAX_VALUE);
        comboBox.setPromptText("NULL");
        comboBox.setConverter(new StringConverter<Effect>(){
            @Override
            public String toString(Effect t) {
                if(t != null)
                    return t.getClass().getSimpleName();
                else
                    return "Null";
            }

            @Override
            public Effect fromString(String string) {
                return null;
            }
            
        });
        
        Button remove = new Button("R");
        remove.setOnAction(e->comboBox.setValue(null));
        
        HBox hbox = new HBox(comboBox, remove);
        hbox.setSpacing(5);
        HBox.setHgrow(comboBox, Priority.ALWAYS);
                
        return new AbstractPropertyEditor<Effect, HBox>(
                propertyItem, 
                hbox) 
                {                           
                    @Override
                    protected ObservableValue<Effect> getEditorObservableValue() {
                        return comboBox.valueProperty();
                    }
                    
                    @Override
                    public void initEditorValue() {                        
                        comboBox.setValue(this.getPropertyValue());                        
                    }
                };
    }
}
