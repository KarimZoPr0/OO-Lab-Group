package se.kth.Abdikarim.Simon.Lab4.model.GenerateMethods;
import se.kth.Abdikarim.Simon.Lab4.view.IProcessor;

public class Blur implements IProcessor
{
    private double[][] kernel = {
            { 1.0 / 9, 1.0 / 9, 1.0 / 9 },
            { 1.0 / 9, 1.0 / 9, 1.0 / 9 },
            { 1.0 / 9, 1.0 / 9, 1.0 / 9 }
    };

    @Override
    public int[][] processImage( int[][] originalImg )
    {
        int width = originalImg[ 0 ].length;
        int height = originalImg.length;

        int offset = kernel.length / 2;

        int[][] outputImg = new int[ height ][ width ];

        for ( int x = offset; x < width - offset; x++ )
        {
            for ( int y = offset; y < height - offset; y++ )
            {
                double[] acc = { 0, 0, 0, 0 };

                for ( int a = 0; a < kernel.length; a++ )
                {
                    for ( int b = 0; b < kernel.length; b++ )
                    {
                        int xn = x + a - offset;
                        int yn = y + b - offset;

                        int argb = originalImg[ yn ][ xn ];

                        acc[ 0 ] += ( ( 0xFF & argb >> 24 ) ) * kernel[ a ][ b ];
                        acc[ 1 ] += ( ( 0xFF & argb >> 16 ) ) * kernel[ a ][ b ];
                        acc[ 2 ] += ( ( 0xFF & argb >> 8 ) ) * kernel[ a ][ b ];
                        acc[ 3 ] += ( ( 0xFF & argb ) ) * kernel[ a ][ b ];
                    }
                }

                int a = Math.min( 255, ( int ) acc[ 0 ] );
                int r = Math.min( 255, ( int ) acc[ 1 ] );
                int g = Math.min( 255, ( int ) acc[ 2 ] );
                int b = Math.min( 255, ( int ) acc[ 3 ] );

                outputImg[ y ][ x ] = ( a << 24 ) | ( r << 16 ) | ( g << 8 ) | b;
            }
        }

        return outputImg;
    }
}
