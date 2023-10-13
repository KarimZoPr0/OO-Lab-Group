package se.kth.Abdikarim.Simon.Lab4.view;

import se.kth.Abdikarim.Simon.Lab4.ImagePixelMatrixConverter;
import se.kth.Abdikarim.Simon.Lab4.model.GenerateMethods.Contrast;
import se.kth.Abdikarim.Simon.Lab4.model.GenerateMethods.ImageHistogram;
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

    @Override
    public void generateHistogramEvent( )
    {
        if(!view.pixelReaderExists()) return;
        ImageHistogram histogram = model.getHistogram( );
        int[][] pixelMatrix = histogram.calculateHistogram( ImagePixelMatrixConverter.getPixelMatrix( view.getFileIO( ).getImage( ) ));
        view.updateChartHistogram(pixelMatrix);
    }

    @Override
    public void generateContrastEvent( )
    {
        model.getContrast().setLevel( view.getLevelSliderValue() );
        model.getContrast().setWindow( view.getWindowSliderValue() );
        int[][] pixelMatrix = model.getContrast().processImage(  ImagePixelMatrixConverter.getPixelMatrix( view.getFileIO( ).getImage( ) ));
        view.setImage(pixelMatrix);
    }
}

