package se.kth.Abdikarim.Simon.Lab4.view;

import se.kth.Abdikarim.Simon.Lab4.FileIO;
import se.kth.Abdikarim.Simon.Lab4.model.ImageProcessingModel;

public class ImageProcessingController
{
    private ImageProcessingModel model;
    private ImageProcessingView view;

    public ImageProcessingController( ImageProcessingModel model, ImageProcessingView view )
    {
        this.model = model;
        this.view = view;
    }

    public void handleOpenImage( )
    {
        view.loadImage( );
        view.updateGenerateView( model.getProcessorState( ) );
    }

    public void handleSaveImage( )
    {
        view.saveImage( );
    }

    public void handleExitApp( )
    {
        System.out.println( "Exit" );
    }

    public void generateImage( )
    {
        if ( !view.pixelReaderExists( ) ) return;
        var originalImg = FileIO.getPixelMatrix( view.getOriginalImage( ) );
        var pixelMatrix = model.processImage( originalImg );
        view.updateGenerateView( model.getProcessorState( ) );
        view.setImage( pixelMatrix );
    }

    public void handleHistogram( )
    {
        if ( !view.pixelReaderExists( ) ) return;
        int[][] pixelMatrix = model.generateHistogram( FileIO.getPixelMatrix( view.getOriginalImage( ) ) );
        view.updateChartHistogram( pixelMatrix );
    }
}

