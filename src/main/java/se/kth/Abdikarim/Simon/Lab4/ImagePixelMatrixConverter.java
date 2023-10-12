package se.kth.Abdikarim.Simon.Lab4;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class ImagePixelMatrixConverter
{
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

    public static Image getImage( int[][] pixelMatrix )
    {
        // create an object of type WritableImage (a subclass to Image)
        WritableImage writableImage = new WritableImage( pixelMatrix.length, pixelMatrix[0].length );
        // get a PixelWriter from the WritableImage and ...
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        // ... use the PixelWriter to write pixels from int[][] to the image object
        for ( int x = 0; x < writableImage.getWidth(); x++ )
        {
            for ( int y = 0; y < writableImage.getHeight(); y++ )
            {
                // store alpha, red, green, blue, each one byte, in an int (four bytes in Java)
                int pixel = pixelMatrix[x][y];
                int rValue = ((pixel >> 16) & 0xff);
                int gValue = ((pixel >> 8) & 0xff);
                int bValue = ((pixel) & 0xff);
                pixelWriter.setArgb( rValue, gValue, bValue );
            }
        }


        // return the (Writable) Image object
        return writableImage;

    }
}
