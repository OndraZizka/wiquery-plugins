package com.wiquery.plugins.jqgrid.model;

public class FormOptions
    implements IFormOptions
{
    private static final long serialVersionUID = 1L;

    private String prefix;
    private String suffix;
    private String label;
    private int rowPosition;
    private int colPosition;
    public String getPrefix()
    {
        return prefix;
    }
    public void setPrefix( String prefix )
    {
        this.prefix = prefix;
    }
    public String getSuffix()
    {
        return suffix;
    }
    public void setSuffix( String suffix )
    {
        this.suffix = suffix;
    }
    public String getLabel()
    {
        return label;
    }
    public void setLabel( String label )
    {
        this.label = label;
    }
    public int getRowPosition()
    {
        return rowPosition;
    }
    public void setRowPosition( int rowPosition )
    {
        this.rowPosition = rowPosition;
    }
    public int getColPosition()
    {
        return colPosition;
    }
    public void setColPosition( int colPosition )
    {
        this.colPosition = colPosition;
    }
}
