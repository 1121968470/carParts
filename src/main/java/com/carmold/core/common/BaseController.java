package com.carmold.core.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 派生使用 通用日志
 *
 * @author 林新强
 * @date 2017年8月28日 上午10:17:14
 */
public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    static String[] dateFormat = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd", "HH:mm"};

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        if (binder.getTarget() != null) {
            binder.registerCustomEditor(Date.class, new MyDateEditor());
            binder.registerCustomEditor(Double.class, new DoubleEditor());
            binder.registerCustomEditor(Integer.class, new IntegerEditor());
            // binder.registerCustomEditor(String.class, new StringEditor());
        }
    }

    private class MyDateEditor extends PropertyEditorSupport {

        @Override
        public void setAsText(String text) throws IllegalArgumentException {

            SimpleDateFormat format = null;
            Date date = null;
            for (String str : dateFormat) {
                try {
                    format = new SimpleDateFormat(str);
                    date = format.parse(text);
                } catch (Exception e) {

                }
                if (date != null) {
                    break;
                }
            }
            setValue(date);
        }
    }

    public class DoubleEditor extends PropertiesEditor {
        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            if (text == null || text.equals("")) {
                text = "0";
            }
            setValue(Double.parseDouble(text));
        }

        @Override
        public String getAsText() {
            return getValue().toString();
        }
    }

    public class IntegerEditor extends PropertiesEditor {
        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            if (text == null || text.equals("")) {
                text = "0";
            }
            setValue(Integer.parseInt(text));
        }

        @Override
        public String getAsText() {
            return getValue().toString();
        }
    }
}
