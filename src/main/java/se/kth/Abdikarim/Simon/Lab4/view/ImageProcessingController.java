package se.kth.Abdikarim.Simon.Lab4.view;

import javafx.application.Platform;
import se.kth.Abdikarim.Simon.Lab4.FileIO;
import se.kth.Abdikarim.Simon.Lab4.ImagePixelMatrixConverter;
import se.kth.Abdikarim.Simon.Lab4.model.ImageProcessingModel;

public class ImageProcessingController
{
    private ImageProcessingModel model;
    private ImageProcessingView view;
    private FileIO fileIO;
    private int[][] pixelMatrix;

    public ImageProcessingController( ImageProcessingModel model, ImageProcessingView view )
    {
        this.model = model;
        this.view = view;
        fileIO = new FileIO();
    }

    public void handleOpenImage( )
    {
        view.loadImage( );
        view.updateGenerateView( model.getProcessorState() );
    }

    public void handleSaveImage( )
    {
        System.out.println( "Save" );
        var imageToSave = ImagePixelMatrixConverter.getImage( pixelMatrix );
        fileIO.saveProcessedImage(imageToSave);
    }

    public void handleExitApp( )
    {
        System.out.println( "Exit" );
    }

    public void generateImage( )
    {
        var originalImg = ImagePixelMatrixConverter.getPixelMatrix( view.getFileIO( ).getImage( ) );
        pixelMatrix = model.processImage( originalImg );
        view.updateGenerateView( model.getProcessorState());
        view.setImage( pixelMatrix );
    }

    public void handleHistogram( )
    {
        if ( !view.pixelReaderExists( ) ) return;
        int[][] pixelMatrix = model.generateHistogram( ImagePixelMatrixConverter.getPixelMatrix( view.getFileIO( ).getImage( )));
        view.updateChartHistogram( pixelMatrix );
    }
}

