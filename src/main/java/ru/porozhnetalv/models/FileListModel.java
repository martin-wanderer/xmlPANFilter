package ru.porozhnetalv.models;

import ru.porozhnetalv.ExtensionFilenameFilter;

import javax.swing.*;
import java.io.File;
import java.util.*;

public class FileListModel extends DefaultListModel<File> {

    public FileListModel(File directory, String extension) {
        for (File file: Objects.requireNonNull(directory.listFiles(new ExtensionFilenameFilter(extension)))) {
            addElement(file);
        }
    }
}
