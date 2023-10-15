package se.kth.Abdikarim.Simon.Lab4;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class FileIO
{
    private FileChooser fileChooser;
    private Image image = null;
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
        if(file == null) return;
        image = null;
        try
        {
            image = new Image( file.toURL( ).toString( ) );
        } catch ( MalformedURLException e )
        {
            throw new RuntimeException( e );
        }
    }

    public void saveProcessedImage(Image image) {
        try {
            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
            fileChooser.setInitialFileName( "copy.png" );
            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter( "Image files", "*.png", ".jpg", "*.bmp");
            fileChooser.getExtensionFilters().add(filter);

            File file = fileChooser.showSaveDialog(primaryStage);

            if (file != null) {
                ImageIO.write(bufferedImage, "png", file);
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public void setImage(Image image)
    {
        this.image = image;
    }

}
