package qianfeng.filemanger2application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

/**
 * Created by Administrator on 2016/9/1 0001.
 */
public class MyAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private File[] arrayFile;

    public MyAdapter(Context context,  File[] arrayFile) {
        this.context = context;
        this.arrayFile = arrayFile;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arrayFile.length;
    }

    @Override
    public Object getItem(int position) {
        return arrayFile[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.lv_item,parent,false);
            holder = new ViewHolder();
            holder.iv = (ImageView) convertView.findViewById(R.id.iv);
            holder.tv = (TextView) convertView.findViewById(R.id.tv);
            convertView.setTag(holder);
        }else
        {
            holder = (ViewHolder) convertView.getTag();
        }

      if(arrayFile[position].isDirectory())
      {
          holder.iv.setImageResource(R.drawable.folder);
      }else
      {
          holder.iv.setImageResource(R.drawable.file);
      }
        holder.tv.setText(arrayFile[position].getName());

        return convertView;
    }

    class ViewHolder
    {
        ImageView iv;
        TextView tv;
    }
}
