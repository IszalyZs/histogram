package com.mycompany.histogram;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TextReaderTest {
    private TextReader textReader;

    @Test
    public void TextReader_fileNameInCorrect_thrownFileNotFoundException() {
        textReader = new TextReader("src/main/resources/tex.txt");
        assertThrows(FileNotFoundException.class, () -> textReader.read());
    }

    @Test
    public void read_emptyFile_zeroLine() throws IOException {
        String emptyFile = "src/test/resources/empty.txt";
        textReader = new TextReader(emptyFile);
        assertEquals(0, textReader.read().length());
    }

    @Test
    public void read_oneLineInTheFile_oneLine() throws IOException {
        String oneLineFile = "src/test/resources/test.txt";
        textReader = new TextReader(oneLineFile);
        assertEquals(1, textReader.read().split(System.lineSeparator()).length);
    }

    @Test
    public void read_multipleLineTextInTheFile_multipleLine() throws IOException {
        String multipleLinesFile = "src/test/resources/text.txt";
        textReader = new TextReader(multipleLinesFile);
        assertEquals(33, textReader.read().split(System.lineSeparator()).length);
    }

}
