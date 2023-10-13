package se.kth.Abdikarim.Simon.Lab4.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import se.kth.Abdikarim.Simon.Lab4.FileIO;
import se.kth.Abdikarim.Simon.Lab4.ImagePixelMatrixConverter;

public class ImageProcessingView extends BorderPane
{
    private ImageView firstView;
    private IImageProcessingEvents iImageProcessingEvents = null;
    private MenuBar menuBar;
    private FileChooser fileChooser;
    private Stage stage;
    private FileIO fileIO;
    private XYChart.Series seriesAlpha;
    private XYChart.Series seriesRed;
    private XYChart.Series seriesGreen;
    private XYChart.Series seriesBlue;

    private PixelReader pixelReader;

    private LineChart<String, Number> chartHistogram;

    private Slider windowSlider;
    private Slider levelSlider;

    private Label windowLabel;
    private Label levelLabel;


    public ImageProcessingView( )
    {
        createMenuBar( );
    }

    public void setIImageProcessingEvents( IImageProcessingEvents imageProcessingEvents )
    {
        firstView = new ImageView( );
        fileIO = new FileIO( );
        fileChooser = new FileChooser( );
        fileChooser.setTitle( "Open Image" );
        this.iImageProcessingEvents = imageProcessingEvents;
        final CategoryAxis xAxis = new CategoryAxis( );
        final NumberAxis yAxis = new NumberAxis( );
        chartHistogram = new LineChart<>( xAxis, yAxis );
        chartHistogram.setCreateSymbols( false );
        windowSlider = new Slider(  );
        levelSlider = new Slider(  );
    }

    private void createMenuBar( )
    {
        // Menu
        Menu fileMenu = new Menu( "File" );
        MenuItem openImageItem = new MenuItem( "Open Image" );
        openImageItem.setOnAction( e -> iImageProcessingEvents.openImageEvent( ) );
        MenuItem saveItem = new MenuItem( "Save" );
        saveItem.setOnAction( event -> iImageProcessingEvents.saveImageEvent( ) );
        MenuItem exitItem = new MenuItem( "Exit" );
        exitItem.setOnAction( event -> iImageProcessingEvents.closeAppEvent( ) );
        fileMenu.getItems( ).addAll( openImageItem, saveItem, exitItem );

        Menu generateMenu = new Menu( "Generate" );
        MenuItem histogramItem = new MenuItem( "Histogram" );
        histogramItem.setOnAction( event -> iImageProcessingEvents.generateHistogramEvent( ) );
        MenuItem contrastItem = new MenuItem( "Contrast" );
        contrastItem.setOnAction( event -> iImageProcessingEvents.generateContrastEvent() );
        MenuItem blurItem = new MenuItem( "Blur" );
        blurItem.setOnAction( event -> System.out.println( "Blur" ) );
        MenuItem invertItem = new MenuItem( "Invert Color" );
        invertItem.setOnAction( event -> System.out.println( "Invert color" ) );

        generateMenu.getItems( ).addAll( histogramItem, contrastItem, blurItem, invertItem );

        menuBar = new MenuBar( );
        menuBar.getMenus( ).addAll( fileMenu, generateMenu );
    }

    public void setStage( Stage stage )
    {
        this.stage = stage;
    }

    public void loadImage( )
    {
        fileIO.loadImage( );

        firstView.setImage( fileIO.getImage( ) );

        iImageProcessingEvents.generateHistogramEvent( );

        windowSlider.setMin(0);
        windowSlider.setMax(255);
        windowSlider.setValue(100);
        windowSlider.setMajorTickUnit( 64 );
        windowSlider.setShowTickLabels( true );

        levelSlider.setMin(0);
        levelSlider.setMax(255);
        levelSlider.setValue(100);
        levelSlider.setMajorTickUnit( 64 );
        levelSlider.setShowTickLabels( true );

        Label windowLabel = new Label( "Window" );
        Label levelLabel = new Label( "Level" );

        Label windowValueLabel = new Label( "Window: " );
        Label levelValueLabel = new Label( "Level: : " );


       windowSlider.valueProperty().addListener( ( observableValue, number, t1 ) -> windowValueLabel.setText( "Window: " + t1.intValue() ) );
       levelSlider.valueProperty().addListener( ( observableValue, number, t1 ) -> levelValueLabel.setText( "Level: " + t1.intValue() ) );

        StackPane stackPane = new StackPane( firstView );
        stackPane.setPadding( new Insets( 10,10,10,10 ) );

        VBox vbox = new VBox( windowLabel, windowSlider, levelLabel, levelSlider );
        HBox hBoxValue = new HBox( windowValueLabel, levelValueLabel );
        hBoxValue.setSpacing( 10 );
        hBoxValue.setPadding( new Insets( 5,5,5,5 ) );
        vbox.setPadding( new Insets( 15,15,15,15 ) );
        vbox.setStyle( "-fx-border-radius: 2; -fx-border-color: green;" );
        StackPane pane = new StackPane( vbox );
        pane.setPadding( new Insets( 15,15,15,15 ) );

        this.setCenter( pane);
        this.setRight( stackPane );
        this.setBottom( hBoxValue );

        // this.getChildren( ).addAll( chartHistogram, firstView );
    }

    public void updateChartHistogram( int[][] pixelMatrix )
    {
        seriesAlpha = new XYChart.Series( );
        seriesRed = new XYChart.Series( );
        seriesGreen = new XYChart.Series( );
        seriesBlue = new XYChart.Series( );
        seriesAlpha.setName( "alpha" );
        seriesRed.setName( "red" );
        seriesGreen.setName( "green" );
        seriesBlue.setName( "blue" );

        chartHistogram.getData( ).clear( );

        for ( int i = 0; i < 256; i++ )
        {
            seriesAlpha.getData( ).add( new XYChart.Data( String.valueOf( i ), pixelMatrix[ 0 ][ i ] ) );
            seriesRed.getData( ).add( new XYChart.Data( String.valueOf( i ), pixelMatrix[ 1 ][ i ] ) );
            seriesGreen.getData( ).add( new XYChart.Data( String.valueOf( i ), pixelMatrix[ 2 ][ i ] ) );
            seriesBlue.getData( ).add( new XYChart.Data( String.valueOf( i ), pixelMatrix[ 3 ][ i ] ) );
        }

        chartHistogram.getData( ).addAll( seriesRed, seriesGreen, seriesBlue );
    }

    public MenuBar getMenuBar( )
    {
        return menuBar;
    }

    public boolean pixelReaderExists( )
    {
        pixelReader = fileIO.getImage( ).getPixelReader( );
        return pixelReader != null;
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

    public FileIO getFileIO( )
    {
        return fileIO;
    }

    public void setImage(int[][] pixelMatrix )
    {
        var image = ImagePixelMatrixConverter.getImage( pixelMatrix );
        fileIO.setImage( image );
        firstView.setImage( fileIO.getImage() );
    }

    public int getLevelSliderValue()
    {
        return (int) levelSlider.getValue();
    }

    public int getWindowSliderValue()
    {
        return (int)windowSlider.getValue();
    }
}
