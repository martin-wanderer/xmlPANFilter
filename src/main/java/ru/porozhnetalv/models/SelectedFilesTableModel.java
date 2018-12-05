package ru.porozhnetalv.models;

import javax.swing.table.AbstractTableModel;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SelectedFilesTableModel extends AbstractTableModel {

    private String[] columnNames = {"xlsx", "xml"};
    private List<File[]> rows = new ArrayList<>();

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return File.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public File getValueAt(int rowIndex, int columnIndex) {
        return rows.get(rowIndex)[columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        rows.get(rowIndex)[columnIndex] = (File) aValue;
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public void addRow(File selectedXls, File selectedXml) {
        rows.add(new File[]{selectedXls, selectedXml});
        fireTableDataChanged();
    }

    public void removeRow(int rowIndex) {
        rows.remove(rowIndex);
        fireTableDataChanged();
    }

    public List<File[]> getRows() {
        return rows;
    }
}
