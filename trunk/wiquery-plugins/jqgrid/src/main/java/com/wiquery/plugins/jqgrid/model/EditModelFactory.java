package com.wiquery.plugins.jqgrid.model;

import com.wiquery.plugins.jqgrid.model.IEditable.InputType;

public class EditModelFactory
{
    public IEditable newModel( InputType type )
    {
        final IEditOptions editOptions;

        if( InputType.text == type )
        {
            editOptions = new TextEditOptions();
        }
        else if( InputType.textarea == type )
        {
            editOptions = new TextAreaEditOptions();
        }
        else
        {
            throw new IllegalArgumentException( "Unknown type" );
        }

        return new GridColumnEditModel( type, editOptions );
    }
}
