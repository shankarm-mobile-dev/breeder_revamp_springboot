/*
 * Copyright (c) 2025 Created By Manibharathi R(01040025), Suguna Foods PVT Ltd; on 20/11/2025
 */

package com.suguna.breeder_revamp.components;


import com.suguna.breeder_revamp.enums.FileStorageCategory;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.EnumSet;
import java.util.UUID;


@Component
public class FileStorageService {

    /**
     * Base directory from application properties (must be absolute).
     * Example: /home/farmer/
     */
    private final Path baseDir;

    /**
     * Final upload root: baseDir + "shed_ready"
     * Example: /home/farmer/shed_ready
     */
    private Path root;

    public FileStorageService(@Value("${file.upload-dir}") String basePath) {
        if (basePath == null || basePath.isBlank()) {
            throw new IllegalStateException("file.upload-dir property is missing or blank");
        }
        this.baseDir = Paths.get(basePath).toAbsolutePath().normalize();
        //this.root = this.baseDir.resolve("shed_ready").normalize();
    }

    /*@PostConstruct
    public void init() {
        try {
            if (!Files.exists(baseDir)) {
                Files.createDirectories(baseDir);
            }
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }
            if (!Files.isWritable(root)) {
                throw new IllegalStateException("Upload path is not writable: " + root);
            }
            System.out.println("FileStorageService initialized. Base: " + baseDir + " | Root: " + root);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to initialize upload directories. Base: " + baseDir + ", Root: " + root, e);
        }
    }*/


    @PostConstruct
    public void init() {
        try {
            if (!Files.exists(baseDir)) Files.createDirectories(baseDir);

            // Ensure all category folders exist and are writable
            for (FileStorageCategory cat : EnumSet.allOf(FileStorageCategory.class)) {
                Path p = baseDir.resolve(cat.folder()).normalize(); // /home/farmer/<category>
                if (!Files.exists(p)) Files.createDirectories(p);
                if (!Files.isWritable(p)) {
                    throw new IllegalStateException("Upload path is not writable: " + p);
                }
            }
            System.out.println("FileStorageService initialized at " + baseDir);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to initialize upload directories under " + baseDir, e);
        }
    }


    /*public String saveShedImage(MultipartFile file, String farmCode, Long activityId, FileStorageCategory category) throws IOException {
        // Sanity checks
        if (file == null || file.isEmpty()) return null;

        // Validate content type
        String ct = file.getContentType();
        if (ct == null || !(ct.startsWith("image/"))) {
            throw new IllegalArgumentException("Invalid file type. Only images are allowed.");
        }

        // Generate safe filename
        String ext = resolveExtension(ct, file.getOriginalFilename());
        String safeBase = UUID.randomUUID().toString();
        String filename = String.format("%s_%s_%s.%s",
                sanitize(farmCode),
                activityId,
                safeBase,
                ext
        );

        Path target = root.resolve(filename);
        try (InputStream is = file.getInputStream()) {
            Files.copy(is, target, StandardCopyOption.REPLACE_EXISTING);
        }

        // Return an app-relative URL (served via StaticResourceConfig below)
        return "/shed_ready/" + filename;
    }*/


    /**
     * Save image under `/home/farmer/<category>/` and return URL like `/<category>/<filename>`
     */
    public String saveImage(MultipartFile file, String farmCode, Long activityId, FileStorageCategory category) throws IOException {
        if (file == null || file.isEmpty()) return null;

        String ct = file.getContentType();
        if (ct == null || !(ct.startsWith("image/"))) {
            throw new IllegalArgumentException("Invalid file type. Only images are allowed.");
        }

        Path categoryRoot = baseDir.resolve(category.folder()).normalize(); // e.g., /home/farmer/mortality

        String ext = resolveExtension(ct, file.getOriginalFilename());
        String safeBase = UUID.randomUUID().toString();
        String filename = String.format("%s_%s_%s.%s",
                sanitize(farmCode), activityId, safeBase, ext);

        Path target = categoryRoot.resolve(filename);
        try (InputStream is = file.getInputStream()) {
            Files.copy(is, target, StandardCopyOption.REPLACE_EXISTING);
        }

        // Public URL (served via StaticResourceConfig below)
        return "/" + category.folder() + "/" + filename;
    }


    private String resolveExtension(String contentType, String originalName) {
        // Prefer contentType mapping
        if ("image/jpeg".equalsIgnoreCase(contentType)) return "jpg";
        if ("image/png".equalsIgnoreCase(contentType)) return "png";
        if ("image/webp".equalsIgnoreCase(contentType)) return "webp";
        if ("image/gif".equalsIgnoreCase(contentType)) return "gif";

        // Fallback: extract from original name if present
        if (originalName != null && originalName.contains(".")) {
            return originalName.substring(originalName.lastIndexOf('.') + 1).toLowerCase();
        }
        return "bin";
    }

    private String sanitize(String s) {
        return (s == null) ? "" : s.replaceAll("[^a-zA-Z0-9._-]", "_");
    }



}

