package org.burgers.lucene.learning.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Searcher {
    private File indexDirectory;

    public QueryFactory getQueryFactory() {
        return queryFactory;
    }

    public void setQueryFactory(QueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    private QueryFactory queryFactory;

    public List<Document> contentSearch(String content) throws IOException, ParseException {
        Directory directory = FSDirectory.open(indexDirectory);
        IndexSearcher indexSearcher = new IndexSearcher(directory);
        TopDocs hits = indexSearcher.search(queryFactory.buildContentsQuery(content), 10, Sort.RELEVANCE);
        List<Document> results = new ArrayList<Document>();
        for (ScoreDoc scoreDoc : hits.scoreDocs) {
            results.add(indexSearcher.doc(scoreDoc.doc));
        }
        indexSearcher.close();
        return results;
    }

    public File getIndexDirectory() {
        return indexDirectory;
    }

    public void setIndexDirectory(File indexDirectory) {
        this.indexDirectory = indexDirectory;
    }
}
