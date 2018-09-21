package com.dou361.ijkplayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dou361.ijkplayer.R;
import com.dou361.ijkplayer.bean.Resolution;

import java.util.List;

public class ResolutionSelectAdapter extends BaseAdapter {

    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 布局填充对象
     */
    private LayoutInflater layoutInflater;
    /**
     * 不同分辨率播放地址集合
     */
    private List<Resolution> resolutionList;

    public ResolutionSelectAdapter(Context context, List<Resolution> list) {
        this.mContext = context;
        this.resolutionList = list;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return resolutionList.size();
    }

    public Object getItem(int position) {
        return resolutionList.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public void setDatas(List<Resolution> itemList) {
        this.resolutionList = itemList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ResolutionSelectAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = this.layoutInflater.inflate(R.layout.simple_player_list_item, (ViewGroup) null);
            holder = new ResolutionSelectAdapter.ViewHolder();
            holder.streamName = (TextView) convertView.findViewById(R.id.simple_player_stream_name);
            convertView.setTag(holder);
        } else {
            holder = (ResolutionSelectAdapter.ViewHolder) convertView.getTag();
        }
        Resolution mVideoijkBean = resolutionList.get(position);
        String streamName = mVideoijkBean.getStream();
        holder.streamName.setText(streamName);
        if (mVideoijkBean.isSelect()) {
            holder.streamName.setTextColor(mContext.getResources().getColor(R.color.simple_player_stream_name_playing));
        } else {
            holder.streamName.setTextColor(mContext.getResources().getColor(R.color.simple_player_stream_name_normal));
        }
        return convertView;
    }

    class ViewHolder {
        public TextView streamName;

        ViewHolder() {
        }
    }


}
