package com.wiquery.plugins.jqgrid.model;

import java.io.Serializable;

import com.wiquery.plugins.jqgrid.component.event.AbstractCustomEvent;

public class DefaultCustomNavButton<B extends Serializable>
    implements INavButton<B>
{
    private static final long serialVersionUID = 1L;

    private String buttonIcon;
    private String caption;
    private String cursor;
    private String id;
    private String positionString;
    private String title;
    private AbstractCustomEvent<B> event;

    public DefaultCustomNavButton()
    {
        buttonIcon = "none";
        caption = "";
        cursor = "pointer";
        positionString = Position.last.name();
        title = "";
    }

    public String getButtonIcon()
    {
        return buttonIcon;
    }

    public void setButtonIcon( String buttonIcon )
    {
        this.buttonIcon = buttonIcon;
    }

    public String getPositionString()
    {
        return positionString;
    }

    public void setPositionString( String positionString )
    {
        this.positionString = positionString;
    }

    public String getCursor()
    {
        return cursor;
    }

    public void setCursor( String cursor )
    {
        this.cursor = cursor;
    }

    public String getCaption()
    {
        return caption;
    }

    public void setCaption( String caption )
    {
        this.caption = caption;
    }

    public String getId()
    {
        return id;
    }

    public void setId( String id )
    {
        this.id = id;
    }

    public Position getPosition()
    {
        final Position position = Position.valueOf( positionString );
        return position;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle( String title )
    {
        this.title = title;
    }

    public AbstractCustomEvent<B> getEvent()
    {
        return event;
    }

    public void setEvent( AbstractCustomEvent<B> event )
    {
        this.event = event;
    }
}
