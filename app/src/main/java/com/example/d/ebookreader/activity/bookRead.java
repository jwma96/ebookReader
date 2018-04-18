package com.example.d.ebookreader.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.d.ebookreader.R;
import com.example.d.ebookreader.model.ReadView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.CharBuffer;

public class bookRead extends AppCompatActivity {
    CharBuffer buff;
    ReadView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_read);
        Intent intent=getIntent();
        if(intent!=null) {
            Toast.makeText(this,"intent is not null",Toast.LENGTH_SHORT).show();
            Log.e("intent","intent is not null");
            String data = intent.getDataString();
            String res = loadFile(Uri.decode(data).substring(7));
            //Uri.decode()防止路径中有中文乱码data中含有file：//需要去除
             t1 = (ReadView) this.findViewById(R.id.brt1);
            //TextView t2=(TextView)this.findViewById(R.id.brt2);
           // t1.setMovementMethod(new ScrollingMovementMethod());//设置上下滚动 翻页？？？
            t1.setText(buff);

        }
        else
            Log.e("intent","intent is null");
    }
   private void loadPage(int position) {
        buff.position(position);
     t1.setText(buff);
    }//从指定位置载入下一页
    private String loadFile(String fdir){
        String result=fdir;
        try {
            File file =new File(fdir);
            int length=(int)file.length();
            buff=CharBuffer.allocate(length);
            //Charset c=Charset.forName("UTF-8");
            InputStreamReader isr=new InputStreamReader(new FileInputStream(file),"UTF-8");
            BufferedReader fis=new BufferedReader(isr);
            fis.read(buff);
            fis.close();
            //result=new String(buff,"UTF-8");
//
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this,"没有找到指定文件",Toast.LENGTH_SHORT).show();
        }
        return result;

    }
}
