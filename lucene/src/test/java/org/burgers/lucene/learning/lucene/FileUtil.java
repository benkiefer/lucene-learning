package org.burgers.lucene.learning.lucene;

import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class FileUtil {
    public File findFileInClassPath(String fileName) throws URISyntaxException, IOException {
        return new PathMatchingResourcePatternResolver(this.getClass().getClassLoader()).getResource(fileName).getFile();
    }
}
