package ru.porozhnetalv;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class JDirectoryChooser extends JFileChooser {
    private File selectedDirectory;
    public JDirectoryChooser(String s) {
        super(s);
        setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        setApproveButtonText("Select folder");
    }

    @Override
    public void approveSelection() {
        if (getSelectedFile().isFile()) {
            return;
        }
        super.approveSelection();
    }

    @Override
    public void changeToParentDirectory() {
        super.changeToParentDirectory();
        setSelectedFile(new File("."));
    }

    public File getSelectedDirectory() {
        return selectedDirectory;
    }

    public void setSelectedDirectory(File selectedDirectory) {
        this.selectedDirectory = selectedDirectory;
    }
}
