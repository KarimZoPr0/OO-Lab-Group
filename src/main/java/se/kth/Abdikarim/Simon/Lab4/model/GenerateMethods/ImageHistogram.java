package se.kth.Abdikarim.Simon.Lab4.model.GenerateMethods;


/**
 * Shows a histogram based on image pixel frequency (rgb)
 */
public class ImageHistogram
{
    public ImageHistogram( )
    {

    }

    /**
     * Increments the rgb count in the pixel Matrix
     * @param imageData the original image representing a pixelMatrix
     * @return the output image representing frequency of the rgb
     */
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