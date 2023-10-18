package se.kth.Abdikarim.Simon.Lab4.view;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;

/**
 * Handles read and write images to/from files
 */
public class FileIO
{
    private FileChooser fileChooser;
    private Image image = null;
    private Stage primaryStage;

    public FileIO()
    {
        fileChooser = new FileChooser();
    }

    /**
     * Sets filter to make it easier for the user what images are allowed
     * Creates an image based on file url
     * Returns if image doesn't exist or exception has been caught
     */
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

    /**
     * Sets filter to make it easier for the user what images are allowed
     *
     * @param image writes to hard-drive
     */
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

    /**
     * Converts an image to 2D array of ARGB pixel data
     *
     * @param image
     * Obtains a PixelReader object from image
     * Iterates and stores the argb values of each pixel
     *
     * @return pixelMatrix
     */
    public static int[][] getPixelMatrix( Image image )
    {
        int width = ( int ) image.getWidth( );
        int height = ( int ) image.getHeight( );

        int[][] pixelMatrix = new int[ width ][ height ];
        PixelReader reader = image.getPixelReader( );
        for ( int x = 0; x < width; x++ )
        {
            for ( int y = 0; y < height; y++ )
            {
                // store alpha, red, green, blue, each one byte, in an int (four bytes in Java)
                pixelMatrix[ x ][ y ] = reader.getArgb( x, y );
            }
        }
        return pixelMatrix;
    }

    /**
     * Converts a 2D array of ARGB pixel data to image
     *
     * @param pixelMatrix
     * Obtains a PixelWrite object and writes the pixel
     * Iterates and retrieves the argb values of each pixel
     *
     * @return image
     */
    public static Image getImage( int[][] pixelMatrix )
    {
        // Create a writable image
        WritableImage writableImage = new WritableImage( pixelMatrix.length, pixelMatrix[ 0 ].length );
        // Get a PixelWriter from the WritableImage
        PixelWriter pixelWriter = writableImage.getPixelWriter( );

        for ( int x = 0; x < writableImage.getWidth( ); x++ )
        {
            for ( int y = 0; y < writableImage.getHeight( ); y++ )
            {
                // Store alpha, red, green, blue, each one byte, in an int (four bytes in Java)
                int pixel = pixelMatrix[ x ][ y ];
                pixelWriter.setArgb( x, y, pixel );
            }
        }

        // Return the writable image
        return writableImage;
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
