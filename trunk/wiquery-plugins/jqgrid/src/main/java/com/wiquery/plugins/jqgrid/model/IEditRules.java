package com.wiquery.plugins.jqgrid.model;

import java.io.Serializable;

public interface IEditRules
    extends Serializable
{
    public enum RuleType
    {
        number,
        integer,
        email,
        url,
        date,
        time;
    }

    boolean isHidden();
    void hide();
    void show();
    boolean isRequired();
    void setRequired( boolean isRequired );
    int getMinValue();
    void setMinValue( int min );
    int getMaxValue();
    void setMaxValue( int max );

    void setRuleType( RuleType type );
    boolean isNumber();
    boolean isInteger();
    boolean isEmail();
    boolean isUrl();
    boolean isDate();
    boolean isTime();
}
