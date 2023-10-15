package se.kth.Abdikarim.Simon.Lab4.model.GenerateMethods;

import se.kth.Abdikarim.Simon.Lab4.view.IProcessor;

public class InvertColors implements IProcessor
{

    public InvertColors()
    {

    }

    @Override
    public int[][] processImage( int[][] originalImg )
    {
        int width = originalImg[0].length;
        int height = originalImg.length;

        int[][] invertedPixelMatrix = new int[width][height];

        for ( int x = 0; x < width; x++ )
        {
            for ( int y = 0; y < height; y++ )
            {
                int argb = originalImg[x][y];

                int a = ((argb >> 24) & 0xFF);
                int r = 255 - ((argb >> 16) & 0xFF);
                int g = 255 - ((argb >> 8) & 0xFF);
                int b = 255 - (argb & 0xFF);

                invertedPixelMatrix [x][y] = (a << 24) | (r << 16) | (g << 8) | b;
            }
        }
        return invertedPixelMatrix;
    }
}
