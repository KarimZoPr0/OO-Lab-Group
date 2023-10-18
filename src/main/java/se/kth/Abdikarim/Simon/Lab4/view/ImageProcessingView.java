package se.kth.Abdikarim.Simon.Lab4.view;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import se.kth.Abdikarim.Simon.Lab4.model.GenerateMethods.*;
import se.kth.Abdikarim.Simon.Lab4.model.ImageProcessingModel;
import se.kth.Abdikarim.Simon.Lab4.model.ProcessorState;



public class ImageProcessingView extends BorderPane
{
    private final PauseTransition windowDebounce;
    private final PauseTransition levelDebounce;
    private ImageView firstView;
    private MenuBar menuBar;
    private Stage stage;
    private FileIO fileIO;
    private PixelReader pixelReader;
    private LineChart<String, Number> chartHistogram;
    private Slider windowSlider;
    private Slider levelSlider;
    private ImageProcessingModel model;
    private Button updateBtn;
    private Label windowLabel;
    private Label levelLabel;
    private Label histogramLbl;

    private Alert alert;

    public ImageProcessingView( ImageProcessingModel model )
    {
        windowDebounce = new PauseTransition( Duration.millis( 300 ) );
        levelDebounce = new PauseTransition( Duration.millis( 300 ) );
        alert = new Alert( Alert.AlertType.NONE );
        histogramLbl = new Label( );
        windowLabel = new Label( "Window: " );
        levelLabel = new Label( "Level: : " );
        this.model = model;
        firstView = new ImageView( );
        fileIO = new FileIO( );
        updateBtn = new Button( "Update" );
        final CategoryAxis xAxis = new CategoryAxis( );
        final NumberAxis yAxis = new NumberAxis( );
        chartHistogram = new LineChart<>( xAxis, yAxis );
        chartHistogram.setCreateSymbols( false );
        windowSlider = new Slider( );
        levelSlider = new Slider( );
        ImageProcessingController controller = new ImageProcessingController( model, this );
        createMenuBar( controller );

        addListenerToSlider( model, controller );
    }

    private void createMenuBar( ImageProcessingController controller )
    {
        updateBtn.setOnAction( e ->
        {
            histogramLbl.setText( "Histogram generated" );
            controller.handleHistogram( );
        } );

        // Menu
        Menu fileMenu = new Menu( "File" );
        MenuItem openImageItem = new MenuItem( "Open Image" );
        openImageItem.setOnAction( e -> controller.handleOpenImage( ) );
        MenuItem saveItem = new MenuItem( "Save" );
        saveItem.setOnAction( event -> controller.handleSaveImage( ) );
        MenuItem exitItem = new MenuItem( "Exit" );
        exitItem.setOnAction( event -> controller.handleExitApp( ) );
        fileMenu.getItems( ).addAll( openImageItem, saveItem, exitItem );

        Menu generateMenu = new Menu( "Generate" );
        MenuItem histogramItem = new MenuItem( "Histogram" );
        histogramItem.setOnAction( event ->
        {
            model.setProcessorState( ProcessorState.HISTOGRAM );
            updateGenerateView( model.getProcessorState( ) );
        } );

        MenuItem contrastItem = new MenuItem( "Contrast" );
        contrastItem.setOnAction( event ->
        {
            model.setProcessorState( ProcessorState.CONTRAST );
            model.setProcessor( new Contrast( ) );
            updateGenerateView( model.getProcessorState( ) );
        } );

        MenuItem blurItem = new MenuItem( "Blur" );
        blurItem.setOnAction( event ->
        {
            model.setProcessorState( ProcessorState.BLUR );
            model.setProcessor( new Blur( ) );
            controller.generateImage( );
        } );

        MenuItem invertedColorsItem = new MenuItem( "Inverted Colors" );
        invertedColorsItem.setOnAction( event ->
        {
            model.setProcessorState( ProcessorState.INVERTED_COLORS );
            model.setProcessor( new InvertColors( ) );
            controller.generateImage( );
        } );

        generateMenu.getItems( ).addAll( histogramItem, contrastItem, blurItem, invertedColorsItem );

        menuBar = new MenuBar( );
        menuBar.getMenus( ).addAll( fileMenu, generateMenu );
    }

    private void addListenerToSlider( ImageProcessingModel model, ImageProcessingController controller )
    {
        windowSlider.valueProperty( ).addListener( ( observableValue, oldValue, newValue ) ->
        {
            windowDebounce.setOnFinished( e ->
            {
                ( ( Contrast ) model.getProcessor( ) ).setWindow( newValue.intValue( ) );
                windowLabel.setText( "Window: " + getWindowSliderValue( ) );
                controller.generateImage( );
            } );
            windowDebounce.playFromStart( );
        } );

        levelSlider.valueProperty( ).addListener( ( observableValue, oldValue, newValue ) ->
        {
            levelDebounce.setOnFinished( e ->
            {
                ( ( Contrast ) model.getProcessor( ) ).setLevel( newValue.intValue( ) );
                levelLabel.setText( "Level: " + getLevelSliderValue( ) );
                controller.generateImage( );
            } );
            levelDebounce.playFromStart( );
        } );
    }

    public void setStage( Stage stage )
    {
        this.stage = stage;
    }

    public void loadImage( )
    {
        fileIO.loadImage( );
        firstView.setImage( fileIO.getImage( ) );
    }

    public void updateChartHistogram( int[][] pixelMatrix )
    {
        XYChart.Series seriesRed = new XYChart.Series( );
        XYChart.Series seriesGreen = new XYChart.Series( );
        XYChart.Series seriesBlue = new XYChart.Series( );

        seriesRed.setName( "red" );
        seriesGreen.setName( "green" );
        seriesBlue.setName( "blue" );

        chartHistogram.getData( ).clear( );

        for ( int i = 0; i < 256; i++ )
        {
            seriesRed.getData( ).add( new XYChart.Data( String.valueOf( i ), pixelMatrix[ 0 ][ i ] ) );
            seriesGreen.getData( ).add( new XYChart.Data( String.valueOf( i ), pixelMatrix[ 1 ][ i ] ) );
            seriesBlue.getData( ).add( new XYChart.Data( String.valueOf( i ), pixelMatrix[ 2 ][ i ] ) );
        }

        chartHistogram.getData( ).addAll( seriesRed, seriesGreen, seriesBlue );
    }

    public MenuBar getMenuBar( )
    {
        return menuBar;
    }

    public boolean pixelReaderExists( )
    {
        if ( fileIO.getImage( ) != null )
        {
            pixelReader = fileIO.getImage( ).getPixelReader( );
            return true;
        }
        alert.setAlertType( Alert.AlertType.WARNING );
        alert.setContentText( "Please open an image first!" );
        alert.show( );
        return false;
    }

    public int getArgb( int x, int y )
    {
        return pixelReader.getArgb( x, y );
    }

    public double getImageWidth( )
    {
        return fileIO.getImage( ).getWidth( );
    }

    public double getImageHeight( )
    {
        return fileIO.getImage( ).getHeight( );
    }


    public Image getOriginalImage()
    {
        return fileIO.getImage();
    }

    public void setImage( int[][] pixelMatrix )
    {
        var image = FileIO.getImage( pixelMatrix );
        firstView.setImage( image );
    }

    public Image getImageFromView( )
    {
        return firstView.getImage( );
    }

    public int getLevelSliderValue( )
    {
        return ( int ) levelSlider.getValue( );
    }

    public int getWindowSliderValue( )
    {
        return ( int ) windowSlider.getValue( );
    }

        public void updateGenerateView( ProcessorState state )
    {
        if ( !pixelReaderExists( ) ) return;
        model.setProcessorState( state );

        VBox vBox = new VBox( );
        vBox.setPadding( new Insets( 15, 15, 15, 15 ) );
        vBox.setStyle( "-fx-border-radius: 2; -fx-border-color: green;" );

        switch ( model.getProcessorState( ) )
        {
            case HISTOGRAM ->
            {
                vBox.getChildren( ).addAll( chartHistogram, updateBtn );
                vBox.setSpacing( 10 );
                vBox.setPadding( new Insets( 15, 15, 15, 15 ) );
                this.setBottom( histogramLbl );
            }
            case CONTRAST ->
            {
                windowSlider.setMin( 0 );
                windowSlider.setMax( 255 );
                windowSlider.setMajorTickUnit( 64 );
                windowSlider.setShowTickLabels( true );

                levelSlider.setMin( 0 );
                levelSlider.setMax( 255 );
                levelSlider.setMajorTickUnit( 64 );
                levelSlider.setShowTickLabels( true );

                vBox.getChildren( ).addAll( windowLabel, windowSlider, levelLabel, levelSlider );
                HBox hBoxValue = new HBox( windowLabel, levelLabel );
                hBoxValue.setSpacing( 5 );
                this.setBottom( hBoxValue );
            }
        }
        this.setCenter( vBox );
        this.setRight( firstView );
        this.setPadding( new Insets( 10, 10, 10, 10 ) );
        BorderPane.setMargin( vBox, new Insets( 5, 10, 5, 5 ) );
    }

    public void saveImage( )
    {
        if(firstView.getImage() != null)
        {
            fileIO.saveProcessedImage( firstView.getImage( ) );
            return;
        }
        alert.setAlertType( Alert.AlertType.WARNING );
        alert.setContentText( "Please open an image first!" );
        alert.show( );
    }
}
