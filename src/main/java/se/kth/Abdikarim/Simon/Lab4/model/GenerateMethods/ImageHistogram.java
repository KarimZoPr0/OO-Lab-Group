package se.kth.Abdikarim.Simon.Lab4.model.GenerateMethods;

public class ImageHistogram
{


    public ImageHistogram( )
    {

    }

    public int[][] calculateHistogram( int[][] imageData )
    {
        var pixelMatrix = new int[ 4 ][ 256 ];
        for ( int y = 0; y < imageData.length; y++ )
        {
            for ( int x = 0; x < imageData[ 0 ].length; x++ )
            {
                int argb = imageData[ x ][ y ];
                int a = ( 0xff & ( argb >> 24 ) );
                int r = ( 0xff & ( argb >> 16 ) );
                int g = ( 0xff & ( argb >> 8 ) );
                int b = ( 0xff & argb );

                pixelMatrix[ 0 ][ a ]++;
                pixelMatrix[ 1 ][ r ]++;
                pixelMatrix[ 2 ][ g ]++;
                pixelMatrix[ 3 ][ b ]++;
            }

        }
        return pixelMatrix;
    }
}