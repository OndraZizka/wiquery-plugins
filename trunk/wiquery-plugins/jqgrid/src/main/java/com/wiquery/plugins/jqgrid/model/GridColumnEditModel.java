package com.wiquery.plugins.jqgrid.model;

public class GridColumnEditModel
    implements IEditable
{
    private static final long serialVersionUID = 1L;

    boolean isEditable;
    String inputType;
    IEditOptions editOptions;
    IEditRules editRules;
    IFormOptions formOptions;

    GridColumnEditModel( InputType anInputType, IEditOptions anEditOptions )
    {
        inputType = anInputType.name();
        editOptions = anEditOptions;
        editRules = new EditRules();
        formOptions = new FormOptions();
    }

    public boolean isEditable()
    {
        return isEditable;
    }

    public void setEditable( boolean isEditable )
    {
        this.isEditable = isEditable;
    }

    public IEditOptions getEditOptions()
    {
        return editOptions;
    }

    public IEditRules getEditRules()
    {
        return editRules;
    }

    public IFormOptions getFormOptions()
    {
        return formOptions;
    }

    public InputType getInputType()
    {
        final InputType type = InputType.valueOf( inputType );
        return type;
    }
}
