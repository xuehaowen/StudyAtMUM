/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs472;

import java.io.IOException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author toby_
 */
public class Label extends SimpleTagSupport {

    String foreColor;
    String text;

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     * @throws javax.servlet.jsp.JspException
     * @throws java.io.IOException
     */
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        if (foreColor != null) {
            out.write(String.format("<span style='color:%s'>%s</span>", foreColor, text));
        } else {
            out.write(String.format("<span>%s</span>", text));
        }

    }
    //Need a setter for each attribute of custom tag

    public void setForeColor(String foreColor) {
        this.foreColor = foreColor;
    }

    public void setText(String text) {
        this.text = text;
    }

}
