package me.Tonus_.hatCosmetics.utility;

import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.jar.Attributes;
import java.util.jar.Manifest;


public class ManifestReader {
    private final Attributes attributes;

    /**
     * Create a ManifestReader to read MANIFEST.MF file from specified JAR.
     * @param clazz Base class of JAR file.
     */
    public ManifestReader(@NotNull Class<?> clazz) {
        URL classPath = clazz.getResource(clazz.getSimpleName() + ".class");
        if (classPath == null) throw new RuntimeException("Failed to retrieve class resource.");
        String classPathString = classPath.toString();

        if (!classPathString.contains("!")) throw new RuntimeException("Not running from a JAR file");

        String manifestPath = classPathString.substring(
                0,
                classPathString.lastIndexOf("!") + 1
        ) + "/META-INF/MANIFEST.MF";

        try (InputStream is = new URL(manifestPath).openStream()) {
            this.attributes =  new Manifest(is).getMainAttributes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieve specified attribute from Manifest.
     * @param name Attribute to retrieve.
     * @return attribute as String, or null.
     */
    public @Nullable String getAttribute(String name) {
        return attributes.getValue(name);
    }
}