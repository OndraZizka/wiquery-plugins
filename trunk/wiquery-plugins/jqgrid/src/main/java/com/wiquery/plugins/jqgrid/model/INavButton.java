package com.wiquery.plugins.jqgrid.model;

import java.io.Serializable;

import com.wiquery.plugins.jqgrid.component.event.AbstractCustomEvent;

public interface INavButton<B extends Serializable>
    extends Serializable
{
    /**
     * The caption of the button, can be a empty string.
     * @return
     */
    String getCaption();

    /**
     * The ui icon name from UI theme icon set. If this option is set to “none” only the text appear.
     * Default is "none"
     */
    String getButtonIcon();

    public enum Position { first, last }

    /**
     * The position where the button will be added (i.e., before or after the standard buttons).
     */
    Position getPosition();

    /**
     * A tooltip for the button.
     */
    String getTitle();

    /**
     * Determines the cursor when we mouseover the element.
     * Default is "pointer".
     */
    String getCursor();

    /**
     * If set, defines the id of the button (actually the id of TD element) for future manipulation
     */
    String getId();

    /**
     * The event to trigger when this custom button is clicked.
     */
    AbstractCustomEvent<B> getEvent();
}
