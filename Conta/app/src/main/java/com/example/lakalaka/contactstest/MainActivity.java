package com.example.lakalaka.contactstest;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static android.provider.ContactsContract.CommonDataKinds.Phone;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    List<String> contactsList=new ArrayList<>();

    private void readContacts(){
        Cursor cursor=null;
        try {

            cursor=getContentResolver().query(Phone.CONTENT_URI,null,null,null,null);
            if(cursor!=null){
                while (cursor.moveToNext()){
                    String displayName=cursor.getString(cursor.getColumnIndex(Phone.DISPLAY_NAME));
                    String number=cursor.getString(cursor.getColumnIndex(Phone.NUMBER));
                    contactsList.add(displayName+"\n"+number);
                }
                adapter.notifyDataSetChanged();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor!=null){
                cursor.close();
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView contactsView= (ListView) findViewById(R.id.contacts_view);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contactsList);
        contactsView.setAdapter(adapter);
        if(ContextCompat.checkSelfPermission(this, READ_CONTACTS)!= PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{READ_CONTACTS},1);
        }else{
            readContacts();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]== PERMISSION_GRANTED){
                    readContacts();
                }else{
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
            break;
            default:
            break;
         }

    }
}
