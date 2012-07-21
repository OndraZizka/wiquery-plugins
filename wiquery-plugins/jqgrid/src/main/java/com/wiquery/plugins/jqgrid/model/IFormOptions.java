package com.wiquery.plugins.jqgrid.model;

import java.io.Serializable;

public interface IFormOptions
    extends Serializable
{
    String getPrefix();
    void setPrefix( String aPrefix );
    String getSuffix();
    void setSuffix( String aSuffix );
    String getLabel();
    void setLabel( String aLabel );
    int getRowPosition();
    void setRowPosition( int pos );
    int getColPosition();
    void setColPosition( int pos );
}
