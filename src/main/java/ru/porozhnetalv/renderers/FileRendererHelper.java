package ru.porozhnetalv.renderers;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

class FileRendererHelper {
    static JLabel initLabelProperties(JLabel jLabel, File file) {
        jLabel.setText(file.getName());
        jLabel.setIcon(FileSystemView.getFileSystemView().getSystemIcon(file));

        return jLabel;
    }
}
