package com.example.lakalaka.webviewtest;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by lakalaka on 2018/1/17/0017.
 */

public class ContentHandler extends DefaultHandler {
    private String nodeName;
    private StringBuilder id;
    private StringBuilder name;
    private StringBuilder version;

    @Override
    public void startDocument() throws SAXException {
        id = new StringBuilder();
        name=new StringBuilder();
        version=new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        nodeName=localName;//记录当前节点名
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if("app".equals(localName)){
            Log.d("Tag", "id is :"+id.toString().trim());
            Log.d("Tag", "name is :"+name.toString().trim());
            Log.d("Tag", "version is :"+version.toString().trim());
            id.setLength(0);
            name.setLength(0);
            version.setLength(0);

        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        //根据当前的节点名判断将内容添加到哪一个StringBulider对象中
        if("id".equals(nodeName)){
            id.append(ch,start,length);
        }else if ("name".equals(nodeName)){
            name.append(ch,start,length);
        }else if("version".equals(nodeName)){
            version.append(ch,start,length);
        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
