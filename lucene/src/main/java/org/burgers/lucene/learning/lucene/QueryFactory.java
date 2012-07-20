package org.burgers.lucene.learning.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;

public class QueryFactory {
    Query buildContentsQuery(String content) throws ParseException {
        return new QueryParser(Version.LUCENE_36, "contents", new StandardAnalyzer(Version.LUCENE_36)).parse(content);
    }
}
