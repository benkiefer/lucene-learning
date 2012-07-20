package org.burgers.lucene.learning.lucene;

import org.apache.lucene.document.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class DocumentFactoryTest {
    private DocumentFactory factory;
    private File file;

    @Before
    public void setUp() throws IOException {
        file = File.createTempFile("test", "txt");
        Writer out = new OutputStreamWriter(new FileOutputStream(file));
        try {
            out.write("a");
        } finally {
            out.flush();
            out.close();
        }
        factory = new DocumentFactory();
    }

    @Test
    public void build() throws FileNotFoundException {
        Document result = factory.build(file);
        System.out.println("result.getField(\"contents\") = " + result.getField("contents"));
        assertEquals(result.getField("fileName").stringValue(), file.getName());
        assertEquals(result.getField("fullpath").stringValue(), file.getAbsolutePath());
    }

    @After
    public void tearDown(){
        file.delete();
    }

}
