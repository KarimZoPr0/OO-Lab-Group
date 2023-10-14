package se.kth.Abdikarim.Simon.Lab4.model.GenerateMethods;
import se.kth.Abdikarim.Simon.Lab4.view.IProcessor;

public class EdgeDetection implements IProcessor
{

    private int[][] kernelX = {
            { -1, 0, 1 },
            { -2, 0, 2 },
            { -1, 0, 1 }
    };

    private int[][] kernelY = {
            { -1, -2, -1 },
            { 0, 0, 0 },
            { 1, 2, 1 }
    };

    public int[][] computeIntensity( int[][] originalImg )
    {
        int width = originalImg.length;
        int height = originalImg[ 0 ].length;

        int[][] intensity = new int[ width ][ height ];
        for ( int x = 0; x < width; x++ )
        {
            for ( int y = 0; y < height; y++ )
            {
                int argb = originalImg[ x ][ y ];
                int r = ( 0xFF & ( argb >> 16 ) );
                int g = ( 0xFF & ( argb >> 8 ) );
                int b = ( 0xFF & argb );

                intensity[ x ][ y ] = ( r + g + b ) / 3;
            }
        }
        return intensity;
    }

    @Override
    public int[][] processImage( int[][] originalImg )
    {
        int width = originalImg.length;
        int height = originalImg[ 0 ].length;

        int[][] intensity = computeIntensity( originalImg );
        int[][] outputPixelMatrix = new int[ width ][ height ];
        for ( int x = 1; x < width - 1; x++ )
        {
            for ( int y = 1; y < height - 1; y++ )
            {
                int magX = 0;
                int magY = 0;

                for ( int a = 0; a < 3; a++ )
                {
                    for ( int b = 0; b < 3; b++ )
                    {
                        int xn = x + a - 1;
                        int yn = y + b - 1;
                        magX += intensity[ xn ][ yn ] * kernelX[ a ][ b ];
                        magY += intensity[ xn ][ yn ] * kernelY[ a ][ b ];
                    }
                }
                int color = ( int ) Math.sqrt( magX * magX + magY * magY );
                color = Math.min( color, 255 );

                outputPixelMatrix[ x ][ y ] = ( 255 << 24 ) | ( color << 16 ) | ( color << 8 ) | color;
            }
        }
        return outputPixelMatrix;
    }
}