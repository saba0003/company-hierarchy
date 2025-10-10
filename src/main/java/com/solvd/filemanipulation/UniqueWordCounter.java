package com.solvd.filemanipulation;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class UniqueWordCounter {

    private static final Logger log = LogManager.getLogger(UniqueWordCounter.class);

    public static void main(String[] args) throws Exception {

        URL resource = UniqueWordCounter.class.getClassLoader().getResource("harry_potter.txt");
        if (resource == null)
            throw new IllegalArgumentException("File not found!");

        File input = new File(resource.toURI());
        File output = new File(input.getParentFile(), "unique_words.txt");

        Set<String> uniqueWords = Arrays.stream(
                StringUtils.split(
                        FileUtils.readFileToString(input, StandardCharsets.UTF_8)
                                .toLowerCase()
                                .replaceAll("[^a-z ]", " ")
                )
        ).collect(Collectors.toSet());

        FileUtils.writeStringToFile(output,
                "Unique words: " + uniqueWords.size() + "\n" + uniqueWords,
                StandardCharsets.UTF_8);

        log.info("Done! Unique words count = {}", uniqueWords.size());
    }
}
