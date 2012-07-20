package org.burgers.lucene.learning.lucene;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:contexts/ApplicationContext.xml")
public class IndexerTest {
    @Autowired
    private Indexer indexer;

    private File indexDirectory;

    @Before
    public void setUp() {
        indexDirectory = indexer.getIndexDirectory();
        prepareIndexingDirectory();
    }

    @Test
    public void index() throws IOException {
        createIndexableFile("a");
        createIndexableFile("b");
        int result = indexer.index();
        assertEquals(2, result);
    }

    private void createIndexableFile(String fileInfo) throws IOException {
        String fileName = fileInfo + ".txt";
        File file = new File(indexDirectory, fileName);
        Writer out = new OutputStreamWriter(new FileOutputStream(file));
        try {
            out.write(fileInfo);
        } finally {
            out.close();
        }
    }

    private void prepareIndexingDirectory() {
        System.out.println("indexDirectory = " + indexDirectory.getAbsolutePath());
        if (!indexDirectory.exists()) {
            indexDirectory.mkdir();
        } else {
            for (File file : indexDirectory.listFiles()) {
                file.delete();
            }
        }
        assertEquals(0, indexDirectory.listFiles().length);
    }

    public Indexer getIndexer() {
        return indexer;
    }

    public void setIndexer(Indexer indexer) {
        this.indexer = indexer;
    }
}
