package Tags;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author shah
 */
public class RequiredFieldTag extends TagSupport {

    private String fieldName;
    private String color = "blue";

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        
        try {
            if (fieldName == null || fieldName.length() == 0) {
                out.print("<font color=" + color + "> *</font>");
            }
        } catch (java.io.IOException ex) {
            throw new JspException("Error in RequiredFieldTag tag", ex);
        }
        return SKIP_BODY;
    }
    
}
