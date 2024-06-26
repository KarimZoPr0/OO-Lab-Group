package se.kth.Abdikarim.Simon.Lab4.model.GenerateMethods;
import se.kth.Abdikarim.Simon.Lab4.view.IProcessor;

/**
 * Adds contrast to image using window level
 * Implements IProcessor to achieve strategy pattern for processing image
 */
public class Contrast implements IProcessor
{
    private int level = 135;
    private int window = 197;

    public Contrast(  )
    {

    }

    /**
     * @return An array which maps image pixel to a new value based on window level
     */
    public int[] generateLUT( )
    {
        int MP = 255;
        int[] LUT = new int[ MP + 1 ];

        int a = Math.max( level - window / 2, 0 );
        int b = Math.min( level + window / 2, MP );

        for ( int i = 0; i < a; i++ )
        {
            LUT[ i ] = 0;
        }

        for ( int i = a; i <= b; i++ )
        {
            LUT[ i ] = Math.round( ( float ) MP / window * ( i - a ) );
        }

        for ( int i = b + 1; i <= MP; i++ )
        {
            LUT[ i ] = MP;
        }
        return LUT;
    }


    /**
     * Uses LUT for the operation
     * @param originalImg the original image to apply contrast on
     * @return The output image representing a contrasted version of the original image
     */
    @Override
    public int[][] processImage( int[][] originalImg )
    {
        int[] LUT = generateLUT( );

        int width = originalImg.length;
        int height = originalImg[ 0 ].length;

        int[][] adjustedImage = new int[ width ][ height ];

        for ( int y = 0; y < height; y++ )
        {
            for ( int x = 0; x < width; x++ )
            {
                int argb = originalImg[ x ][ y ];
                int a = ( 0xff & ( argb >> 24 ) );
                int r = ( 0xff & ( argb >> 16 ) );
                int g = ( 0xff & ( argb >> 8 ) );
                int b = ( 0xff & argb );

                int newR = LUT[ r ];
                int newG = LUT[ g ];
                int newB = LUT[ b ];

                adjustedImage[ x ][ y ] = ( a << 24 ) | ( newR << 16 ) | ( newG << 8 ) | newB;
            }
        }
        return adjustedImage;
    }

    public void setWindow( int window )
    {
        this.window = window;
        System.out.println( window );
    }

    public void setLevel( int level )
    {
        this.level = level;
        System.out.println( level );
    }
}
