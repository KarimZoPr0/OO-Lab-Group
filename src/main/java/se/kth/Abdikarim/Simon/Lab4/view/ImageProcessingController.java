package se.kth.Abdikarim.Simon.Lab4.view;

import se.kth.Abdikarim.Simon.Lab4.model.ImageProcessingModel;

public class ImageProcessingController implements IImageProcessingEvents
{
    private ImageProcessingModel model;
    private ImageProcessingView view;
    public ImageProcessingController( ImageProcessingModel model, ImageProcessingView view)
    {
        this.model = model;
        this.view = view;

        view.setIImageProcessingEvents( this );
    }

    @Override
    public void openImageEvent( )
    {
        view.loadImage();
    }

    @Override
    public void saveImageEvent( )
    {
        System.out.println("Save" );
    }

    @Override
    public void closeAppEvent( )
    {
        System.out.println( "Exit" );
    }
}
