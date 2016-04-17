package tianqiw.myapplication.utils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import tianqiw.myapplication.model.TaskItem;
import tianqiw.myapplication.model.enums.TaskStatus;
import tianqiw.myapplication.model.enums.VisaType;

/**
 * Created by STuotuo.Wen on 2016/4/3.
 */
public class XmlParserHandler {
    public List<TaskItem> taskItemList;
    TaskItem item;
    private String text;

    public XmlParserHandler() {
        taskItemList = new ArrayList<TaskItem>();
    }

    public List<TaskItem> getTaskItems() {
        return taskItemList;
    }

    public List<TaskItem> parse(InputStream in) {
        XmlPullParserFactory factory = null;
        XmlPullParser parser = null;
        try {
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            parser = factory.newPullParser();

            parser.setInput(in, null);

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase("taskitem")) {
                            // set last updated date to current time
                            item = new TaskItem(TaskStatus.IN_PROGRESS);
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase("taskitem")) {
                            // add task object to list
                            taskItemList.add(item);
                        } else if (tagname.equalsIgnoreCase("id")) {
                            item.setTid(Integer.parseInt(text));
                        } else if (tagname.equalsIgnoreCase("title")) {
                            item.setTitle(text);
                        } else if (tagname.equalsIgnoreCase("description")) {
                            item.setDescription(text);
                        } else  if (tagname.equalsIgnoreCase("type")) {
                            item.setType(VisaType.values()[Integer.parseInt(text)]);
                        }
                        break;

                    default:
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return taskItemList;
    }
}
