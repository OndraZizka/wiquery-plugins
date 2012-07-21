package com.wiquery.plugins.jqgrid.model;

import java.io.Serializable;

public interface IEditable
    extends Serializable
{
    boolean isEditable();

    enum InputType
    {
        button,
        checkbox,
        file,
        hidden,
        image,
        password,
        radio,
        reset,
        submit,
        text,
        textarea;
    }

    InputType getInputType();
    IEditOptions getEditOptions();
    IEditRules getEditRules();
    IFormOptions getFormOptions();
}
