package com.wiquery.plugins.jqgrid.model;

import java.io.Serializable;

import com.wiquery.plugins.jqgrid.model.IEditable.InputType;

public interface IEditOptions
    extends Serializable
{
    InputType getInputType();
}
