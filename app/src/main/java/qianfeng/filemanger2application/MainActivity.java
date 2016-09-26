package qianfeng.filemanger2application;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ListView lv;

    private List<File> list;
    private File[] arrayFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = ((ListView) findViewById(R.id.lv));
        list = new ArrayList<>();  // 每次从MainAcitivity.this---->跳转到 MainActivity.class 的时候，都会调用onCreate()方法

        File rootFile = Environment.getExternalStorageDirectory();

        String filepath = getIntent().getStringExtra("filepath");
        if(filepath != null)
        {
            rootFile = new File(filepath);
        }
        // listFiles: 是继续拆分当前目录，拿到其下一级所有目录和子文件。
        arrayFile  = rootFile.listFiles(); // arrayFile 一直都是数据源,是要显示的数据源


        lv.setAdapter(new MyAdapter(this,arrayFile));

        // 主要是考察从本界面 跳。转 到 本界面的 操作。  跳转界面，每次都会调用onCreate()方法

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                File file = arrayFile[position]; // 这个positon是点击的item的索引，而里面填充的数据，恰好是 arrayFile[]， 里面File对象是作为数据源的
                // file 就是你的目的地，不要再获取了，这里按钮的监听事件已经获取了。
                if(file.isDirectory()) // file就是你点击了的 item ,  file就是你要去到的界面
                {
                    // 跳转到另外一个界面，就会重新加载onCreate()方法
                    Intent intent = new Intent(MainActivity.this, MainActivity.class); // 注意这样加载的话，每次都会调用MainActivity的onCreate方法，
                                                        // 所以要判断是否是第一次加载，如果是第一次加载的话，要注意什么
                    intent.putExtra("filepath",file.getAbsolutePath());
                    startActivity(intent);

                }else
                {
                    if(file.getName().endsWith(".mp3")) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.fromFile(file), "audio/mp3");
                        startActivity(intent);
                    }
                }

            }
        });
    }



}
