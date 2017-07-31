package com.example.view.myapplication;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class ViewHolder {
    private SparseArray<View> mViews;
    private Context mContext;
    private int mPosition;
    private View mConvertView;

    public ViewHolder(Context context, ViewGroup parent, int layoutId, int position){
        mPosition=position;
        mViews=new SparseArray<View>();
        mConvertView= LayoutInflater.from(context).inflate(layoutId,parent,false);
        mConvertView.setTag(this);
    }

    public static ViewHolder getViewHolder(Context context,ViewGroup parent,
                                           int layoutId,int position,View convertView){
        if(convertView==null){
            return new ViewHolder(context,parent,layoutId,position);
        }else{
            ViewHolder holder= (ViewHolder) convertView.getTag();
            holder.mPosition=position;
            return holder;
        }
    }

    /**
     * 通过viewId获取控件
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId){
        View view=mViews.get(viewId);
        if(view==null){
            view=mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T)view;
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 为TextView设置text值
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId,String text){
        TextView tv= getView(viewId);
        tv.setText(text);
        return this;
    }

    /**
     * 为ImageView设置src
     * @param viewId
     * @param resId
     * @return
     */
    public ViewHolder setImageResource(int viewId,int resId){
        ImageView iv=getView(viewId);
        iv.setImageResource(resId);
        return this;
    }

    public int getPosition() {
        return mPosition;
    }
}
