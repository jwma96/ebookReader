package com.example.d.ebookreader.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.d.ebookreader.R;
import com.example.d.ebookreader.model.ReadView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.CharBuffer;

public class bookRead extends Activity  {
    private CharBuffer buff;
   private ReadView t1;
    private int position=0;
    private Intent intent;
    private float dx,ux,dy,uy,width;
    private long pagenum;//总页面数
    private final int SIZE=900;//页面字节数固定
    private long bcount;//总字节数
    private long currentPage;//当前页面

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_read);
        intent=getIntent();
        init();
        loadPage(0);//书签
        ReadView brt1=(ReadView)super.findViewById(R.id.brt1);
        brt1.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event){
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        dx=event.getX();
                        dy=event.getY();
                        //System.out.println("---action down-----");
                        //show.setText("起始位置为："+"("+event.getX()+" , "+event.getY()+")");
                        break;
                    case MotionEvent.ACTION_MOVE:
                      //  System.out.println("---action move-----");
                        //show.setText("移动中坐标为："+"("+event.getX()+" , "+event.getY()+")");
                        break;
                    case MotionEvent.ACTION_UP:
                        ux=event.getX();
                        uy=event.getY();
                        //System.out.println("---action up-----");
                        //show.setText("最后位置为："+"("+event.getX()+" , "+event.getY()+")");
                }
                if(dx==ux&&dy==ux){
                    if(dx>t1.getWidth()/2){//页面三等分，中间跳出设置
                        //加载下一页
                    }
                    else {
                        //加载上一页
                    }
                }
                return true;
            }
        });
    }
    private void init(){  if(intent!=null) {
        //Toast.makeText(this,"intent is not null",Toast.LENGTH_SHORT).show();
        Log.e("intent","intent is not null");
        String data = intent.getDataString();
        String res = loadFile(Uri.decode(data).substring(7));
        Toast.makeText(this,res,Toast.LENGTH_SHORT).show();
        //Uri.decode()防止路径中有中文乱码data中含有file：//需要去除
        t1 = (ReadView) this.findViewById(R.id.brt1);
        //TextView t2=(TextView)this.findViewById(R.id.brt2);
        // t1.setMovementMethod(new ScrollingMovementMethod());//设置上下滚动 翻页？？？
        //t1.setText(buff);
       // loadPage(0);

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
    public void nextBtn(View view) {
        Toast.makeText(this,"xiayiye",Toast.LENGTH_SHORT).show();
        position += t1.getCharNum();
        loadPage(position);
        t1.resize();
    }
    public void preBtn(View view){

    }
}
