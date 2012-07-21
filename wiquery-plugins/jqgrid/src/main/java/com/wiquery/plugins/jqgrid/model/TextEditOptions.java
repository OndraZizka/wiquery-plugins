package com.wiquery.plugins.jqgrid.model;

import com.wiquery.plugins.jqgrid.model.IEditable.InputType;

public class TextEditOptions
    implements IEditOptions
{
    private static final long serialVersionUID = 1L;

    int size;
    int maxLength;

    public int getSize()
    {
        return size;
    }

    public void setSize( int size )
    {
        this.size = size;
    }

    public int getMaxLength()
    {
        return maxLength;
    }

    public void setMaxLength( int maxLength )
    {
        this.maxLength = maxLength;
    }

    public InputType getInputType()
    {
        return InputType.text;
    }
}
