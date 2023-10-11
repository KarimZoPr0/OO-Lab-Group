package se.kth.Abdikarim.Simon.Lab4.view;
import javafx.geometry.Pos;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import se.kth.Abdikarim.Simon.Lab4.FileIO;

import java.io.File;
import java.net.MalformedURLException;

public class ImageProcessingView extends HBox
{
    private IImageProcessingEvents iImageProcessingEvents = null;

    private MenuBar menuBar;

    private FileChooser fileChooser;

    private Stage stage;

    private FileIO fileIO;

    public ImageProcessingView()
    {
        createMenuBar();
    }

    public void setIImageProcessingEvents(IImageProcessingEvents imageProcessingEvents)
    {
        fileIO = new FileIO();
        fileChooser = new FileChooser();
        fileChooser.setTitle( "Open Image" );
        this.iImageProcessingEvents = imageProcessingEvents;
    }

    private void createMenuBar( )
    {
        // Menu
        Menu fileMenu = new Menu( "File" );
        MenuItem openImageItem = new MenuItem( "Open Image" );
        openImageItem.setOnAction( e -> iImageProcessingEvents.openImageEvent());
        MenuItem saveItem = new MenuItem( "Save" );
        saveItem.setOnAction( event -> iImageProcessingEvents.saveImageEvent());
        MenuItem exitItem = new MenuItem( "Exit" );
        exitItem.setOnAction( event -> iImageProcessingEvents.closeAppEvent());
        fileMenu.getItems().addAll( openImageItem, saveItem, exitItem );

        Menu generateMenu = new Menu( "Generate" );
        MenuItem histogramItem = new MenuItem("Histogram");
        histogramItem.setOnAction( event -> System.out.println( "Histogram" ) );
        MenuItem contrastItem = new MenuItem( "Contrast" );
        contrastItem.setOnAction( event -> System.out.println( "Contrast" ) );
        MenuItem blurItem = new MenuItem( "Blur" );
        blurItem.setOnAction( event -> System.out.println( "Blur" ) );
        MenuItem invertItem = new MenuItem( "Invert Color" );
        invertItem.setOnAction( event -> System.out.println( "Invert color" ) );

        generateMenu.getItems().addAll( histogramItem, contrastItem, blurItem, invertItem );

        menuBar = new MenuBar(  );
        menuBar.getMenus().addAll( fileMenu, generateMenu );
    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
    }


    public String openImage()
    {
        return fileIO.openFile( stage );
    }


    public void loadImage(String path)
    {
        File file = new File( path );
        Image image = null;
        try
        {
            image = new Image( file.toURL().toString() );
        } catch ( MalformedURLException e )
        {
            throw new RuntimeException( e );
        }

        // Display the image as is.
        ImageView firstView = new ImageView();
        firstView.setImage(image);


        // Display the image as is.
        ImageView secondView = new ImageView();
        secondView.setImage(image);

        this.getChildren().addAll( firstView, secondView );
        this.setAlignment( Pos.CENTER );
    }

    public MenuBar getMenuBar( )
    {
        return menuBar;
    }
}
