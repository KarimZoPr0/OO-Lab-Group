package se.kth.Abdikarim.Simon.Lab4.model.GenerateMethods;

public class ImageHistogram
{

    public ImageHistogram( )
    {

    }

    public int[][] calculateHistogram( int[][] imageData )
    {
        var pixelMatrix = new int[ 3 ][ 256 ];
        for ( int y = 0; y < imageData.length; y++ )
        {
            for ( int x = 0; x < imageData[ 0 ].length; x++ )
            {
                int argb = imageData[ x ][ y ];
                int r = ( 0xff & ( argb >> 16 ) );
                int g = ( 0xff & ( argb >> 8 ) );
                int b = ( 0xff & argb );

                pixelMatrix[ 0 ][ r ]++;
                pixelMatrix[ 1 ][ g ]++;
                pixelMatrix[ 2 ][ b ]++;
            }
        }
        return pixelMatrix;
    }
}