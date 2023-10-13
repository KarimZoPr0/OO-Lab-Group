package se.kth.Abdikarim.Simon.Lab4;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import se.kth.Abdikarim.Simon.Lab4.model.ImageProcessingModel;
import se.kth.Abdikarim.Simon.Lab4.view.ImageProcessingController;
import se.kth.Abdikarim.Simon.Lab4.view.ImageProcessingView;

public class Main extends Application
{
    @Override
    public void start( Stage primaryStage )
    {
        ImageProcessingModel model = new ImageProcessingModel();
        ImageProcessingView view = new ImageProcessingView();
        ImageProcessingController controller = new ImageProcessingController(model, view);

        // Create a menu bar
        MenuBar menuBar = view.getMenuBar();
        view.setStage( primaryStage );

        // Vertical Box -> Top to Down
        BorderPane root = new BorderPane(  );
        root.setTop( menuBar );
        root.setCenter( view );

        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setScene( scene );
        primaryStage.setTitle( "ImageProcessing" );
        primaryStage.setResizable( true );
        primaryStage.show();
    }
}