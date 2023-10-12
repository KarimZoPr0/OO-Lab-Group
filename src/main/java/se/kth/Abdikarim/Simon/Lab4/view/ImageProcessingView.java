package se.kth.Abdikarim.Simon.Lab4.view;

import javafx.geometry.Pos;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import se.kth.Abdikarim.Simon.Lab4.FileIO;

import java.io.File;
import java.net.MalformedURLException;

public class ImageProcessingView extends HBox
{
    ImageView firstView;
    private boolean hasSwitchedGenerateMethod = true;
    private IImageProcessingEvents iImageProcessingEvents = null;
    private MenuBar menuBar;
    private FileChooser fileChooser;
    private Stage stage;
    private FileIO fileIO;

    public ImageProcessingView()
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
        seriesAlpha = new XYChart.Series( );
        seriesRed = new XYChart.Series( );
        seriesGreen = new XYChart.Series( );
        seriesBlue = new XYChart.Series( );
        seriesAlpha.setName( "alpha" );
        seriesRed.setName( "red" );
        seriesGreen.setName( "green" );
        seriesBlue.setName( "blue" );
        chartHistogram.getData( ).addAll( seriesRed, seriesGreen, seriesBlue );

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
        contrastItem.setOnAction( event -> System.out.println( "Contrast" ) );
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

        this.getChildren( ).addAll( chartHistogram, firstView );
        this.setAlignment( Pos.CENTER );
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

}
