package org.burgers.lucene.learning.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.*;

public class Indexer {
    private File indexDirectory;
    private DocumentFactory documentFactory;

    public int index(File directoryToIndex) throws IOException {
        IndexWriter writer = prepareWriter();

        if (directoryToIndex.isDirectory()){
            File[] textFiles = getTextFilesInInputDirectory(directoryToIndex);

            for (File  file : textFiles) {
                writer.addDocument(documentFactory.build(file));
            }

            int result = writer.numDocs();
            writer.close();
            return result;
        } else{
            throw new RuntimeException("File: " + directoryToIndex.getAbsolutePath() + " is not a directory.");
        }
    }

    private File[] getTextFilesInInputDirectory(File directory) {
        return directory.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.getName().toLowerCase().endsWith(".txt");
                }
            });
    }

    private IndexWriter prepareWriter() throws IOException {
        Directory directory = FSDirectory.open(indexDirectory);
        return new IndexWriter(directory, new StandardAnalyzer(Version.LUCENE_36), true, IndexWriter.MaxFieldLength.UNLIMITED);
    }

    public File getIndexDirectory() {
        return indexDirectory;
    }

    public void setIndexDirectory(File indexDirectory) {
        this.indexDirectory = indexDirectory;
    }

    public void setDocumentFactory(DocumentFactory documentFactory) {
        this.documentFactory = documentFactory;
    }
}
