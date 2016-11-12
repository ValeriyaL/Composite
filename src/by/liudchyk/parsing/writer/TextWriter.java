package by.liudchyk.parsing.writer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Admin on 09.11.2016.
 */
public class TextWriter {
    private final static Logger LOG = LogManager.getLogger();

    public static void writeToFile(String file, String text){
        try(FileWriter writer = new FileWriter(file))
        {
            writer.write(text);
            writer.flush();
        }
        catch(IOException ex){
            LOG.error("Error in writing to the file", ex);
        }
    }
}
