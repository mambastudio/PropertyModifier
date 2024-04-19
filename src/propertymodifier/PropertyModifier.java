/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propertymodifier;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import propertymodifier.beans.BeanPropertySheet;
import propertymodifier.beans.DefaultBeanExtractor;
import propertymodifier.beans.DefaultBeanItem;
import propertymodifier.beans.DefaultEditorList;
import propertymodifier.editors.base.PropertyItem;

/**
 *
 * @author jmburu
 */
public class PropertyModifier extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Person shape = new Person();        
        BeanPropertySheet propertySheet = new BeanPropertySheet(new DefaultBeanExtractor(), new DefaultEditorList());   
        propertySheet.init(shape);
        
         //complete launch of ui
        Scene scene = new Scene(propertySheet);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Irregular Grid GPU");
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(550);
        primaryStage.show();        
    }
    
}
