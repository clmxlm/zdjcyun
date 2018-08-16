package com.zdjc.zdjcyun.util;

import android.util.SparseArray;
import android.view.View;

/**
 * ViewHolder一劳永逸做法
 */
public class ViewHolderUtils {

    private ViewHolderUtils() {}

    public static <T extends View> T get(View convertView, int id) {
        SparseArray<View> holder = (SparseArray<View>) convertView.getTag();
        if (holder == null) {
            holder = new SparseArray<View>();
            convertView.setTag(holder);
        }

        View view = holder.get(id);

        if (view == null) {
            view = convertView.findViewById(id);
            holder.put(id, view);
        }

        return (T) view;
    }

}
