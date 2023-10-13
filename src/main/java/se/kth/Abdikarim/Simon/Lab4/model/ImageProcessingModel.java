package se.kth.Abdikarim.Simon.Lab4.model;

import se.kth.Abdikarim.Simon.Lab4.model.GenerateMethods.Contrast;
import se.kth.Abdikarim.Simon.Lab4.model.GenerateMethods.ImageHistogram;

public class ImageProcessingModel
{
    private ImageHistogram histogram;
    private Contrast contrast;


    public ImageProcessingModel( )
    {
        histogram = new ImageHistogram();
        contrast = new Contrast();
    }


    public ImageHistogram getHistogram( )
    {
        return histogram;
    }

    public Contrast getContrast( )
    {
        return contrast;
    }
}
