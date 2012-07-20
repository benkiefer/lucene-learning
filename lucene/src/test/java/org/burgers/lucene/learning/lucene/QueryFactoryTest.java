package org.burgers.lucene.learning.lucene;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Query;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class QueryFactoryTest {
    private QueryFactory queryFactory;

    @Before
    public void setUp(){
        queryFactory = new QueryFactory();
    }

    @Test
    public void buildContentQuery() throws ParseException {
        Query result = queryFactory.buildContentsQuery("test");
        assertEquals(result.toString(), "contents:test");
    }


}
