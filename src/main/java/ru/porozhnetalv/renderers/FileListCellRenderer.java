package ru.porozhnetalv.renderers;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class FileListCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        return FileRendererHelper.initLabelProperties((JLabel) c, (File) value);
    }


}