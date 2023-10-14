package se.kth.Abdikarim.Simon.Lab4.model;

import se.kth.Abdikarim.Simon.Lab4.model.GenerateMethods.Contrast;
import se.kth.Abdikarim.Simon.Lab4.model.GenerateMethods.ProcessorState;
import se.kth.Abdikarim.Simon.Lab4.model.GenerateMethods.ImageHistogram;
import se.kth.Abdikarim.Simon.Lab4.view.IProcessor;

public class ImageProcessingModel
{
    private ImageHistogram histogram;
    private IProcessor processor;

    private ProcessorState processorState;

    public ImageProcessingModel( )
    {
        histogram = new ImageHistogram();
        processor = new Contrast();
        processorState = ProcessorState.NONE;
    }

    public int[][] processImage(int[][] pixelMatrix)
    {
        return processor.processImage( pixelMatrix );
    }

    public int[][] generateHistogram( int[][] pixelMatrix)
    {
        return histogram.calculateHistogram( pixelMatrix );
    }

    public IProcessor getProcessor( )
    {
        return processor;
    }

    public void setProcessor( IProcessor processor )
    {
        this.processor = processor;
    }

    public void setProcessorState( ProcessorState processorState )
    {
        this.processorState = processorState;
    }
    public ProcessorState getProcessorState( )
    {
        return processorState;
    }
}
