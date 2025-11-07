package com.example.demo;

import java.util.List;

public record Product(
        int id,
        String name,
        String category,
        String tagline,
        String description,
        double price,
        String unit,
        String image,
        List<String> features
) {}