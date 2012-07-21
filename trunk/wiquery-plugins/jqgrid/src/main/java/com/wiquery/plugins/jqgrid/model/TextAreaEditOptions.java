package com.wiquery.plugins.jqgrid.model;

import com.wiquery.plugins.jqgrid.model.IEditable.InputType;

public class TextAreaEditOptions
    implements IEditOptions
{
    private static final long serialVersionUID = 1L;

    int rows;
    int cols;

    public int getRows()
    {
        return rows;
    }

    public void setRows( int rows )
    {
        this.rows = rows;
    }

    public int getCols()
    {
        return cols;
    }

    public void setCols( int cols )
    {
        this.cols = cols;
    }

    public InputType getInputType()
    {
        return InputType.textarea;
    }
}
