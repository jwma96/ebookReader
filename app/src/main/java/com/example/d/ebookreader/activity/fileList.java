package com.example.d.ebookreader.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.d.ebookreader.R;
import com.example.d.ebookreader.model.FileAdapter;
import com.example.d.ebookreader.model.bookFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class fileList extends AppCompatActivity {
    private List<bookFile> list_map=new ArrayList<bookFile>();
    boolean finish=false;
    private LinearLayout l1;
    private ListView listView;
    private CheckBox cb;
  //  private boolean firstCk;//第一次被选中弹出popup window
    private int cNum=0;// 选中次数


    //private List<Map<String,Object>> list_map = new ArrayList<Map<String,Object>>(); //定义一个适配器对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_list);
       l1=(LinearLayout)findViewById(R.id.L1);//l1加载列表  可改为gifView
        listView=(ListView)findViewById(R.id.list_view);//lView列表
        cb=(CheckBox)findViewById(R.id.cb_st);//复选框
        //listView.setOnItemClickListener();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
                //list_map.get(1).getIsCho()
//点击后在标题上显示点击了第几行
                setTitle("你点击了第"+arg2+"行");
                //设置复选框被选中
            }
        });
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView,  boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){
                    //选中复选框
                    if(cNum==0){
                        cNum++;
                        //弹出popup window

                    }
                    else{
                        cNum++;
                    }
                }else{
                    //取消选中
                }
            }
            });
        loadTask lt=new loadTask();
        lt.execute();
      /*  initFile();
        FileAdapter adapter=new FileAdapter(this,R.layout.file_item,fileList);
        ListView listView=(ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
       if(finish){//finish可以省略//时间监听器的设置，服务线程 完成时通知？？？
            l1.setVisibility(View.GONE);
            lView.setVisibility(View.VISIBLE);
        }*/
        //File sdDir = Environment.getExternalStorageDirectory();
    }
    private void initFile(){
        Log.i("init","正在加载");
        File path = Environment.getExternalStorageDirectory();
        if (Environment.getExternalStorageState().
                equals(Environment.MEDIA_MOUNTED)) {
            File[] files = path.listFiles();// 读取文件夹下文件
           getFileName(files);

            Log.i("init","加载完毕");
        }
        finish=true;
    }
    private String getFileName(File[] files) {
        float n=1024;
        String str = "";
        if (files != null) {	// 先判断目录是否为空，否则会报空指针
            for (File file : files) {
                if (file.isDirectory()){//检查此路径名的文件是否是一个目录(文件夹)
                    getFileName(file.listFiles());
                } else {
                    String fileName = file.getName();
                    if (fileName.endsWith(".txt")) {
                        if(file.length()/1024>50) {
                            long size=file.length()/1024;
                            String sizes;
                            if(size<1024){
                                sizes=size+"kb";
                            }else {
                                sizes=size/n+"M";
                            }
                            //String s=fileName.substring(0,fileName.lastIndexOf(".")).toString();
                            str = fileName.substring(0, fileName.lastIndexOf(".")) + "\n";
                           // Map<String, Object> items = new HashMap<String, Object>(); //创建一个键值对的Map集合，用来存放名字和头像
                            bookFile bf=new bookFile(str,R.drawable.txt,sizes,false);
                            //fileList.add(bf);
                           // items.put("pic", R.drawable.txt);
                           // items.put("name", str);
                            list_map.add(bf);
                        }

                    }
                }
            }

        }
        return str;
    }
class loadTask extends AsyncTask<Void,Void,Void>{
    protected  void onPreExecute(){
        l1.setVisibility(View.VISIBLE);

    }
    protected  Void doInBackground(Void... parms) {
        initFile();
        return null;
        //Log.i("ope","listview初始化完毕");
    }
    protected void onPostExecute(Void avoid){
        FileAdapter adapter=new FileAdapter(fileList.this,R.layout.list_items,list_map);
       // ListView listView=(ListView)findViewById(R.id.list_view);
       listView.setAdapter(adapter);
      // SimpleAdapter simpleAdapter = new SimpleAdapter(
             //   fileList.this,/*传入一个上下文作为参数*/
              //  list_map,         /*传入相对应的数据源，这个数据源不仅仅是数据而且还是和界面相耦合的混合体。*/
              //  R.layout.list_items, /*设置具体某个items的布局，需要是新的布局，而不是ListView控件的布局*/
               // new String[]{"pic","name"}, /*传入上面定义的键值对的键名称,会自动根据传入的键找到对应的值*/
                //new int[]{R.id.items_imageView1,R.id.items_textView1});/*传入items布局文件中需要指定传入的控件，这里直接上id即可*/
       // listView.setAdapter(simpleAdapter);
        Log.i("set","准备切换界面");
        l1.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
    }
}
}
