package se.kth.Abdikarim.Simon.Lab4;

import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.net.MalformedURLException;

public class FileIO
{
    private FileChooser fileChooser;

    private Image image;

    private Stage primaryStage;

    public FileIO()
    {
        fileChooser = new FileChooser();
    }

    public void loadImage()
    {
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter( "Image files", "*.png", ".jpg", "*.bmp");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog( primaryStage );
        image = null;
        try
        {
            image = new Image( file.toURL( ).toString( ) );
        } catch ( MalformedURLException e )
        {
            throw new RuntimeException( e );
        }
    }

    public void setPrimaryStage( Stage primaryStage )
    {
        this.primaryStage = primaryStage;
    }

    public Image getImage( )
    {
        return image;
    }
}
