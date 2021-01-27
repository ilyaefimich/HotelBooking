package com.epam.jwd.finaltask.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

public class ShowErrorTag extends SimpleTagSupport {
    private String errorMessage;
    private StringWriter sw = new StringWriter();

    public void setErrorMessage(String msg) {
        this.errorMessage = msg;
    }

    public void doTag() throws JspException, IOException {
        if (errorMessage != null && !errorMessage.equals("")) {
            /* Use message from attribute */
            String html = "<div class=\"alert alert-warning\" role=\"alert\">" + errorMessage + "</div>";
            JspWriter out = getJspContext().getOut();
            out.println(html);
        } else if(getJspBody() != null) {
            /* use message from the body */
            getJspBody().invoke(sw);
            getJspContext().getOut().println(sw.toString());
        }
    }
}
