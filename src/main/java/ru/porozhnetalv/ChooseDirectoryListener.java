package ru.porozhnetalv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ChooseDirectoryListener implements ActionListener {
    private JFilePairsChooser dialogParent;
    private final JDirectoryChooser chooser;

    public ChooseDirectoryListener(JFilePairsChooser dialogParent, JDirectoryChooser chooser) {
        this.dialogParent = dialogParent;
        this.chooser = chooser;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (chooser.showOpenDialog(dialogParent) == JFileChooser.APPROVE_OPTION) {
            chooser.setSelectedDirectory(chooser.getSelectedFile());
            dialogParent.init();
        }
    }
}
