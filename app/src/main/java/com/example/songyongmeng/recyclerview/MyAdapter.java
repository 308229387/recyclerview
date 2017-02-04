package com.example.songyongmeng.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by songyongmeng on 2017/1/18.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>  {
    private Context context;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    public List<String> list;
    private LayoutInflater inflater;
    private View mHeaderView;

    public MyAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mHeaderView != null) {
            return new ViewHolder(mHeaderView);
        }
        return new ViewHolder(inflater.inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mTextView.setText(list.get(position));
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(v,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public View view;

        public ViewHolder(View view) {
            super(view);

            if (itemView == mHeaderView){
                return;
            }
            this.view = view;
            mTextView = (TextView) view.findViewById(R.id.wewe);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }


    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
