package com.example.d.ebookreader.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.d.ebookreader.R;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;

public class demoRead extends AppCompatActivity {
   // private ViewFlipper vf;
    //private GestureDetector gd;
    TextView demo1;
    TextView show;
    private float dx,ux,dy,uy,width;
    private long pagenum;//总页面数
    private final int SIZE=900;//页面字节数固定
    private long bcount;//总字节数
    private long currentPage;//当前页面
   // TextView demo2=(TextView)findViewById(R.id.demo2);
   // TextView demo3=(TextView)findViewById(R.id.demo3);
   private RandomAccessFile readFile;
    private LinearLayout lr;
    File file;
    byte[] buff;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_read);
       //file=new File("/storage/emulated/0/A/a.txt");
        demo1=(TextView)findViewById(R.id.demot1);
        lr=(LinearLayout)findViewById(R.id.lr);
        show=(TextView)findViewById(R.id.show);
       // file=new File("/storage/emulated/0/A/a.txt");
       // ProcessText(file,1);
      // int length= Integer.parseInt(String.valueOf(bcount));
       // buff=new byte[length];
       // result=read();
       // demo1.setText(result);
       // AssetManager am=getAssets();
      lr.setOnTouchListener(new View.OnTouchListener(){
            public boolean onTouch(View v, MotionEvent event){
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        dx=event.getX();
                        dy=event.getY();
                        System.out.println("---action down-----");
                        show.setText("起始位置为："+"("+event.getX()+" , "+event.getY()+")");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        System.out.println("---action move-----");
                        //show.setText("移动中坐标为："+"("+event.getX()+" , "+event.getY()+")");
                        break;
                    case MotionEvent.ACTION_UP:
                        System.out.println("---action up-----");
                        ux=event.getX();
                        uy=event.getY();
                    /*    if(dx==ux&&dy==ux){
                            if(dx>demo1.getWidth()/2){//页面三等分，中间跳出设置
                                //加载下一页
                                demo1.setText(getNext());
                            }
                            else {
                                //加载上一页
                                demo1.setText(getPre());
                            }
                        }*/
                        System.out.println("---action up-----");
                        show.setText("最后位置为："+"("+event.getX()+" , "+event.getY()+")");
                        break;
                }

                return true;
            }
        });
    }

 /*   private void init(){
        vf=(ViewFlipper)findViewById(R.id.vf1);
        vf.addView(demo1);
        vf.addView(demo2);
        vf.addView(demo3);
       // vf.setInAnimation(inFromRightAnimation());//设置View进入屏幕时候使用的动画
       // vf.setOutAnimation(outToLeftAnimation());  //设置View退出屏幕时候使用的动画
    }*/
 public void  ProcessText(File file, int currentpage)  {//currentPage实现书签
     try {


         readFile = new RandomAccessFile(file,"r");


         bcount = readFile.length();//获得字节总数
         pagenum = bcount/SIZE;//计算得出文本的页数
         this.currentPage=currentpage;
     } catch (Exception e) {
         e.printStackTrace();
     }
 }
    private void seek(long page){
        try {
            //if(pages)
            readFile.seek(page*SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String read(){
        //内容重叠防止 末尾字节乱码
        byte[] chs = new byte[SIZE+30];
        try {

           readFile.read(chs);

        } catch (IOException e) {
            e.printStackTrace();
        }
        //String res=EncodingUtils.get
        return new String(chs, Charset.forName("GBK"));
    }
    public String getPre(){
        String content = null;
        //第一页 的情况 定位在0字节处 读取内容 当前页数不改变
        if(currentPage <= 1){
            seek(currentPage-1);
            content =read();
        }else{
            //其它页 则定位到当前页-2 处 在读取指定字节内容 例如当前定位到第二页的末尾 2*SIZE  上一页应该是第一页 也就是从0位置 开始读取SIZE个字节
            seek(currentPage-2);
            content = read();
            currentPage = currentPage - 1;
        }

        return content;
    }
    public String getNext(){

        String content = null;
        if(currentPage >= pagenum){//当前页为最后一页时候,显示的还是 最后一页
            seek(currentPage-1);
            content = read();
        }else{
            seek(currentPage);
            content = read();
            currentPage = currentPage +1;
        }

        return content;
    }

}
