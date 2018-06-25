/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs472;

import java.io.IOException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author toby_
 */
public class Head extends SimpleTagSupport {

    private String thecolor;
    private String words;

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
        if (thecolor != null) {
            out.write(String.format("<span style='color:%s'>%s</span>", thecolor, words));
        } else {
            out.write(String.format("<span>%s</span>", words));
        }
    }

    public void setThecolor(String thecolor) {
        this.thecolor = thecolor;
    }

    public void setWords(String words) {
        this.words = words;
    }
    
}
