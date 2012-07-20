package org.burgers.lucene.learning.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class DocumentFactory {
    Document build(File file) throws FileNotFoundException {
        Document document = new Document();
        document.add(new Field("contents", new FileReader(file)));
        document.add(new Field("fileName", file.getName(), Field.Store.YES, Field.Index.NOT_ANALYZED));
        document.add(new Field("fullpath", file.getAbsolutePath(), Field.Store.YES, Field.Index.NOT_ANALYZED));
        return document;
    }

}
