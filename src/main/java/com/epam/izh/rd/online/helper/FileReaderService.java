package com.epam.izh.rd.online.helper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Properties;

public class FileReaderService implements IFileReaderService {

    @Override
    public String readFromFileToString(String fileName) throws URISyntaxException, IOException {
        Objects.requireNonNull(fileName, "Не указано имя файла");
        URL resource = Thread.currentThread().getContextClassLoader().getResource(fileName);
        Objects.requireNonNull(resource, "Файл не найден");
        URI resourceURI = resource.toURI();
        return new String(Files.readAllBytes(Paths.get(resourceURI)));
    }

    @Override
    public Properties loadProperties(String propertiesFileName) throws IOException {
        Objects.requireNonNull(propertiesFileName, "Не указано имя файла");
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesFileName);
        Properties properties = new Properties();
        properties.load(resourceAsStream);
        return properties;
    }
}
