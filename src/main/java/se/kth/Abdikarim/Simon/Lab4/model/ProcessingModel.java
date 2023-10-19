package se.kth.Abdikarim.Simon.Lab4.model;

import se.kth.Abdikarim.Simon.Lab4.model.GenerateMethods.Contrast;
import se.kth.Abdikarim.Simon.Lab4.model.GenerateMethods.Histogram;
import se.kth.Abdikarim.Simon.Lab4.view.IProcessor;

/**
 * Manages all the processing methods
 */
public class ProcessingModel
{
    private final Histogram histogram;
    private IProcessor processor;
    private ProcessorState processorState;

    public ProcessingModel( )
    {
        histogram = new Histogram();
        processor = new Contrast();
        processorState = ProcessorState.NONE;
    }

    /**
     * @return the histogram
     */
    public Histogram getHistogram( )
    {
        return histogram;
    }

    /**
     * @return the processor
     */
    public IProcessor getProcessor( )
    {
        return processor;
    }

    /**
     * @param processor the new processor
     */
    public void setProcessor( IProcessor processor )
    {
        this.processor = processor;
    }

    /**
     * @param processorState the new processState
     */
    public void setProcessorState( ProcessorState processorState )
    {
        this.processorState = processorState;
    }

    /**
     * @return The processorState
     */
    public ProcessorState getProcessorState( )
    {
        return processorState;
    }
}
