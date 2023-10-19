package se.kth.Abdikarim.Simon.Lab4.view;

import javafx.application.Platform;
import se.kth.Abdikarim.Simon.Lab4.model.ProcessingModel;

/**
 *  Manages all the controller methods
 */
public class ProcessingController
{
    private final ProcessingModel model;
    private final ProcessingView view;

    public ProcessingController( ProcessingModel model, ProcessingView view )
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
        Platform.exit();
    }

    /**
     * Checks if image exists
     * Sets image based on pixelmatrix
     */
    public void generateImage( )
    {
        if ( !view.pixelReaderExists( ) ) return;
        var originalImg = FileIO.getPixelMatrix( view.getOriginalImage( ) );
        var pixelMatrix = model.getProcessor( ).processImage( originalImg );
        view.updateGenerateView( model.getProcessorState( ) );
        view.setImage( pixelMatrix );
    }

    /**
     * Checks if file exists
     * Calculates histogram
     * Updates the ChartHistogram
     */
    public void handleHistogram( )
    {
        if ( !view.pixelReaderExists( ) ) return;
        int[][] pixelMatrix = model.getHistogram( ).calculateHistogram( FileIO.getPixelMatrix( view.getOriginalImage( ) ) );
        view.updateChartHistogram( pixelMatrix );
    }
}

