package by.liudchyk.parsing.creator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


/**
 * Created by Admin on 01.11.2016.
 */
public class TextCreator {
    private static final Logger LOG = LogManager.getLogger();

    public static String readTextFromFile(String path) {
        String text = "";
        File file = new File(path);
        FileReader fr;
        try {
            fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                text += line;
                text += '\n';
            }
        } catch (IOException e) {
            LOG.fatal(e);
            throw new RuntimeException();
        }
        return text;
    }
}
