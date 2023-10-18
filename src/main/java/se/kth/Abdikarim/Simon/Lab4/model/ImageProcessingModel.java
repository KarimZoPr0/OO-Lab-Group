package se.kth.Abdikarim.Simon.Lab4.model;

import se.kth.Abdikarim.Simon.Lab4.model.GenerateMethods.Contrast;
import se.kth.Abdikarim.Simon.Lab4.model.GenerateMethods.ImageHistogram;
import se.kth.Abdikarim.Simon.Lab4.view.IProcessor;

/**
 * Manages all the processing methods
 */
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
    public ImageHistogram getHistogram( )
    {
        return histogram;
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
