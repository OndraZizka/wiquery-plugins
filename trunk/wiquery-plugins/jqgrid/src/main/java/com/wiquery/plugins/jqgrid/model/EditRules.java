package com.wiquery.plugins.jqgrid.model;

public class EditRules
    implements IEditRules
{
    private static final long serialVersionUID = 1L;

    boolean isHidden;
    boolean isRequired;
    int minValue;
    int maxValue;
    String ruleType;

    public boolean isHidden()
    {
        return isHidden;
    }

    public void show()
    {
        isHidden = false;
    }

    public void hide()
    {
        isHidden = true;
    }

    public boolean isRequired()
    {
        return isRequired;
    }

    public void setRequired( boolean isRequired )
    {
        this.isRequired = isRequired;
    }

    public int getMinValue()
    {
        return minValue;
    }

    public void setMinValue( int min )
    {
        minValue = min;
    }

    public int getMaxValue()
    {
        return maxValue;
    }

    public void setMaxValue( int max )
    {
        maxValue = max;
    }

    public RuleType getRuleType()
    {
        final RuleType type = RuleType.valueOf( ruleType );
        return type;
    }

    public void setRuleType( RuleType type )
    {
        ruleType = type.name();
    }

    public boolean isDate()
    {
        return ( RuleType.date == getRuleType() );
    }

    public boolean isEmail()
    {
        return ( RuleType.email == getRuleType() );
    }

    public boolean isInteger()
    {
        return ( RuleType.integer == getRuleType() );
    }

    public boolean isNumber()
    {
        return ( RuleType.number == getRuleType() );
    }

    public boolean isTime()
    {
        return ( RuleType.time == getRuleType() );
    }

    public boolean isUrl()
    {
        return ( RuleType.url == getRuleType() );
    }
}
