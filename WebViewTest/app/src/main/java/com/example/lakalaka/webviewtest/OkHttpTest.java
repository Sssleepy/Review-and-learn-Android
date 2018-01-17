package com.example.lakalaka.webviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpTest extends AppCompatActivity implements View.OnClickListener {

    TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http_test);
        responseText = (TextView) findViewById(R.id.response_text_okhttp);
        findViewById(R.id.send_request_okHttp).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.send_request_okHttp) {
            sendRequestWithOkHttp();
        }
    }

    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url("http://192.168.1.177:8080/get_data.json").build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithGSON(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }




        }).start();
    }

    private void parseJSONWithGSON(String jsonData) {
        Gson gson = new Gson();
        List<App> appList = gson.fromJson(jsonData, new TypeToken<List<App>>() {
        }.getType());
        for(App app:appList){
            Log.d("Tag", "id is: "+app.getId());
            Log.d("Tag", "name is: "+app.getName());
            Log.d("Tag", "version is: "+app.getVersion());
        }
    }

    private void parseJSONWithJSONObject(String jsonData) {
        try {
            JSONArray jsonArray=new JSONArray(jsonData);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                String id=jsonObject.getString("id");
                String name=jsonObject.getString("name");
                String version=jsonObject.getString("version");
                Log.d("Tag", "id is: "+id);
                Log.d("Tag", "name is: "+name);
                Log.d("Tag", "version is: "+version);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void parseXMLWithSAX(String xmlData) {
        try{
            SAXParserFactory factory=SAXParserFactory.newInstance();
            XMLReader xmlReader=factory.newSAXParser().getXMLReader();
            ContentHandler handler=new ContentHandler();
            xmlReader.setContentHandler(handler);
            xmlReader.parse(new InputSource(new StringReader(xmlData)));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void parseXMLWithPull(String xmlData) {
        try{
            XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser=factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType=xmlPullParser.getEventType();
            String id="";
            String name="";
            String version="";
            while (eventType!=XmlPullParser.END_DOCUMENT){
                String nodeName=xmlPullParser.getName();
                switch(eventType){
                    case XmlPullParser.START_TAG:{
                        if("id".equals(nodeName)){
                            id=xmlPullParser.nextText();
                        }else if ("name".equals(nodeName)){
                            name=xmlPullParser.nextText();
                        }else if("version".equals(nodeName)){
                            version=xmlPullParser.nextText();
                        }
                    }
                    break;
                    case XmlPullParser.END_TAG:{
                        if("app".equals(nodeName)){
                            Log.d("Tag", "id is: "+id);
                            Log.d("Tag", "name is: "+name);
                            Log.d("Tag", "version is: "+version);
                        }
                        break;
                    }
                    default:
                    break;
                 }
                 eventType=xmlPullParser.next();

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //进行UI的更新操作
                responseText.setText(response);
            }
        });
    }

}