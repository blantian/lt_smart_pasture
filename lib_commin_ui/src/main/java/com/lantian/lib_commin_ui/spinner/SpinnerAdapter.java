package com.lantian.lib_commin_ui.spinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lantian.lib_base.entity.items.Item;

import java.util.List;

/**
 * Created by Sherlock·Holmes on 2020-03-28
 */
public class SpinnerAdapter extends BaseAdapter {


    private List<Item> objects;
    private int resource;
    private Context context;

    public SpinnerAdapter(@NonNull Context context, int resource, @NonNull List<Item> objects) {
        this.objects = objects;
        this.resource = resource;
        this.context = context;

    }

    /**
     * 获取item下标
     * @param value
     * @return
     */
    public int findItemPostion(String value) {
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).getValue().equals(value)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Item getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);

        if (convertView instanceof TextView) {
            ((TextView) convertView).setText(getItem(position).getItem());
        }

        return convertView;
    }
}
