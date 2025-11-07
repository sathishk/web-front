package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductLoader {

    private static final ObjectMapper mapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    public static List<Product> loadProducts() {
        List<Product> products = new ArrayList<>();

        try {
            // Path inside classpath
            Path dir = Paths.get(ProductLoader.class
                    .getClassLoader()
                    .getResource("data/products")
                    .toURI());

            try (Stream<Path> paths = Files.list(dir)) {
                products = paths
                        .filter(p -> p.toString().endsWith(".json"))
                        .sorted()
                        .map(ProductLoader::readProductFile)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
            }

        } catch (Exception e) {
            System.err.println("Error loading product files: " + e.getMessage());
            e.printStackTrace();
        }

        return products;
    }

    private static Product readProductFile(Path path) {
        try (InputStream is = Files.newInputStream(path)) {
            return mapper.readValue(is, Product.class);
        } catch (IOException e) {
            System.err.println("Failed to read file: " + path + " -> " + e.getMessage());
            return null;
        }
    }

}