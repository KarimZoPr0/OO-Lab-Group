
package se.kth.Abdikarim.Simon.Lab3.PartB.io;

import se.kth.Abdikarim.Simon.Lab3.PartB.model.Project;

import java.io.*;
import java.util.List;


/**
 * Hints on how to implement serialization and deserialization
 * of lists of projects and users.
 */

public class ProjectsFileIO {


/**
     * Call this method before the application exits, to store the users and projects,
     * in serialized form.
 */

    public static void serializeToFile(File file, List< Project > data) throws IOException {
        // ...
        // and then, make sure the file always get closed
        try(FileOutputStream fos = new FileOutputStream( file  );
            BufferedOutputStream bos = new BufferedOutputStream( fos );
            ObjectOutputStream oos = new ObjectOutputStream( bos ))
            {
                oos.writeObject( data );
            }
    }


/*
     * Call this method at startup of the application, to deserialize the users and
     * from file the specified file.
*/

    @SuppressWarnings("unchecked")
    public static List<Project> deSerializeFromFile(File file) throws IOException, ClassNotFoundException {
        // ...
        // and then, make sure the file always get closed
        try(ObjectInputStream ois = new ObjectInputStream( new BufferedInputStream( new FileInputStream( file ) ) ))
        {
            return (List<Project>) ois.readObject();
        }
    }

    private ProjectsFileIO() {

    }
}
