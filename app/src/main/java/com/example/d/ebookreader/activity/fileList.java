package com.example.d.ebookreader.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.d.ebookreader.R;
import com.example.d.ebookreader.model.FileAdapter;
import com.example.d.ebookreader.model.bookFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class fileList extends AppCompatActivity {
    private List<bookFile> fileList=new ArrayList<bookFile>();
    boolean finish=false;
    private LinearLayout l1;
    private ListView lView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_list);
       l1=(LinearLayout)findViewById(R.id.L1);
        lView=(ListView)findViewById(R.id.list_view);
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
           String name= getFileName(files);
            bookFile bf=new bookFile(name,R.drawable.ic_launcher,false);
            fileList.add(bf);
            Log.i("init","加载完毕");
        }
        finish=true;
    }
    private String getFileName(File[] files) {
        String str = "";
        if (files != null) {	// 先判断目录是否为空，否则会报空指针
            for (File file : files) {
                if (file.isDirectory()){//检查此路径名的文件是否是一个目录(文件夹)
                    getFileName(file.listFiles());
                } else {
                    String fileName = file.getName();
                    if (fileName.endsWith(".txt")) {
                        //String s=fileName.substring(0,fileName.lastIndexOf(".")).toString();
                        str += fileName.substring(0,fileName.lastIndexOf("."))+"\n";
                    }
                }
            }

        }
        return str;
    }
class loadTask extends AsyncTask<Void,Void,Boolean>{
    protected  void onPreExecute(){
        initFile();
        FileAdapter adapter=new FileAdapter(fileList.this,R.layout.file_item,fileList);
        ListView listView=(ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        Log.i("ope","listview初始化完毕");
    }
    protected  Boolean doInBackground(Void... parms) {
        return true;
    }
    protected void onPostExecute(Void...parms){
        Log.i("set","准备切换界面");
        l1.setVisibility(View.GONE);
        lView.setVisibility(View.VISIBLE);
    }
}
}
