package se.kth.Abdikarim.Simon.Lab4;

import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

public class FileIO
{
    private FileChooser fileChooser;

    public FileIO()
    {
        fileChooser = new FileChooser();
    }

    public String openFile( Stage stage )
    {
        File file = fileChooser.showOpenDialog( stage );
        return file != null ? file.getPath( ) : null;
    }
}
