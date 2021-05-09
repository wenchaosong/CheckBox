package com.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ms.checkbox.CheckBoxGroupView;

import java.util.List;

public class TestAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mData;
    private CheckBoxGroupView cb;

    public TestAdapter(Context context, List<String> data, CheckBoxGroupView cb) {
        this.mData = data;
        this.mContext = context;
        this.cb = cb;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.content_main, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.mTextView = (TextView) convertView.findViewById(R.id.tv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mTextView.setText(mData.get(i));
        viewHolder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i == mData.size() - 1) {
                    cb.resetStatus(mData.get(i));
                    mData.remove(i);
                    notifyDataSetChanged();
                }
            }
        });

        return convertView;
    }

    class ViewHolder {
        TextView mTextView;
    }
}
