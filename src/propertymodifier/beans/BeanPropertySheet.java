package propertymodifier.beans;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;
import java.util.function.BiConsumer;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import propertymodifier.editors.base.PropertyEditor;
import propertymodifier.editors.base.PropertyExtractor;
import propertymodifier.editors.base.PropertyItem;
import propertymodifier.editors.base.PropertySheet;

/**
 *
 * @author user
 * @param <P>
 * @param <E>
 */
public class BeanPropertySheet<P extends PropertyItem, E extends PropertyEditor> extends VBox implements PropertySheet<P, E> {
    private Callback<P, E> propertyEditorFactory;
    private PropertyExtractor<P> propertyExtractor;
    private static final int MIN_COLUMN_WIDTH = 60;
    private static final int MIN_ROW_HEIGHT = 25;
    private final BiConsumer<Label, Node> labelNodeConsume;
    
    public BeanPropertySheet(PropertyExtractor<P> propertyExtractor, Callback<P, E> propertyEditorFactory)
    {         
       this(propertyExtractor, propertyEditorFactory, null);
    }
    
    public BeanPropertySheet(PropertyExtractor<P> propertyExtractor, 
                             Callback<P, E> propertyEditorFactory,
                             BiConsumer<Label, Node> labelNodeConsume)
    {         
       setMinSize(USE_PREF_SIZE, USE_PREF_SIZE);
       this.propertyExtractor = propertyExtractor;
       this.propertyEditorFactory = propertyEditorFactory;   
       this.labelNodeConsume = labelNodeConsume;
    }
    
    public final void init(Object object)
    {
        List<P> list = propertyExtractor.getProperties(object);
        PropertyPane propertyPane = new PropertyPane(list);
        getChildren().setAll(propertyPane);
    }
    
    private final class PropertyPane extends GridPane {
        public PropertyPane( List<P> properties ) 
        {
            this( properties, 0 );
        }
        
        public PropertyPane( List<P> properties, int nestingLevel ) {
            setVgap(5);
            setHgap(5);
            setPadding(new Insets(5, 15, 5, 15 + nestingLevel*10 ));
            getStyleClass().add("property-pane"); //$NON-NLS-1$
            setItems(properties);
            //setGridLinesVisible(true);
        }
        
        public void setItems( List<P> properties ) {
            getChildren().clear();

            int row = 0;
            
            for (P item : properties) {

                // filter properties
                String title = item.getName();
                
                // setup property label
                Label label = new Label(title);
                label.setMinWidth(MIN_COLUMN_WIDTH);
                label.setMinHeight(MIN_ROW_HEIGHT);
                
                // show description as a tooltip
                String description = item.getDescription();
                if ( description != null && !description.trim().isEmpty()) {
                    label.setTooltip(new Tooltip(description));
                }
                
                add(label, 0, row);

                // setup property editor
                Node editor = getEditor(item);
                
                if(editor != null)
                {                    
                    label.setLabelFor(editor);               
                    add(editor, 1, row);
                    GridPane.setHgrow(editor, Priority.ALWAYS);
                    
                    if(labelNodeConsume != null)
                    {
                        
                        labelNodeConsume.accept(label, editor);
                    }
                    
                    //TODO add support for recursive properties                
                   
                }
                 row++;
            }            
        }
    }
    
    private Node getEditor(P item)
    {
        E editor = propertyEditorFactory.call(item);
        if(editor != null)
        {
            return editor.getEditor();
        }
        return null;
    }
}