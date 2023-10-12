package se.kth.Abdikarim.Simon.Lab4.model;

import se.kth.Abdikarim.Simon.Lab4.model.GenerateMethods.ImageHistogram;

public class ImageProcessingModel
{
    private ImageHistogram imageHistogram;


    public ImageProcessingModel( )
    {
        imageHistogram = new ImageHistogram();
    }


    public ImageHistogram getImageHistogram( )
    {
        imageHistogram = new ImageHistogram();
        return imageHistogram;
    }
}
