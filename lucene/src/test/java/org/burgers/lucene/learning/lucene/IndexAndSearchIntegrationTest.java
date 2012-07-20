package org.burgers.lucene.learning.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:contexts/ApplicationContext.xml")
public class IndexAndSearchIntegrationTest {
    @Autowired
    private Indexer indexer;

    @Autowired
    private Searcher searcher;

    private File indexDirectory;

    @Before
    public void setUp() {
        indexDirectory = indexer.getIndexDirectory();
        prepareIndexDirectory();
    }

    @Test
    public void indexAndSearch() throws Exception {
        File inputDirectory = new FileUtil().findFileInClassPath("poems");
        int result = indexer.index(inputDirectory);
        assertEquals(3, result);

        List<Document> results = searcher.contentSearch("Humpty");
        assertTrue(results.size() == 1);
        assertEquals(results.get(0).get("fileName"), "HumptyDumpty.txt");
    }

    private void prepareIndexDirectory() {
        if (!indexDirectory.exists()) {
            indexDirectory.mkdir();
        } else {
            for (File file : indexDirectory.listFiles()) {
                file.delete();
            }
        }
        assertEquals(0, indexDirectory.listFiles().length);
    }

    public void setIndexer(Indexer indexer) {
        this.indexer = indexer;
    }

    public void setSearcher(Searcher searcher) {
        this.searcher = searcher;
    }
}
