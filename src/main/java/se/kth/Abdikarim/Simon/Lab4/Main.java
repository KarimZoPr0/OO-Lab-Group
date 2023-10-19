package se.kth.Abdikarim.Simon.Lab4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import se.kth.Abdikarim.Simon.Lab4.model.ProcessingModel;
import se.kth.Abdikarim.Simon.Lab4.view.ProcessingView;

public class Main extends Application
{
    @Override
    public void start( Stage primaryStage )
    {
        ProcessingModel model = new ProcessingModel();
        ProcessingView view = new ProcessingView(model);

        // Create a menu bar
        MenuBar menuBar = view.getMenuBar();

        // Vertical Box -> Top to Down
        BorderPane root = new BorderPane(  );
        root.setTop( menuBar );
        root.setCenter( view );

        Scene scene = new Scene(root, 1000, 585);
        primaryStage.setScene( scene );
        primaryStage.setTitle( "ImageProcessing" );
        primaryStage.setResizable( true );
        primaryStage.show();
    }
}