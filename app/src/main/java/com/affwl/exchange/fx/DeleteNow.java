
package com.affwl.exchange.fx;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.affwl.exchange.R;

import java.util.List;

public class DeleteNow extends BaseAdapter {

    Context context;
    List<RowItem5_quotes> rowItems5;
    ViewHolder holder;

    DeleteNow(Context context, List<RowItem5_quotes> rowItems5) {
        this.context = context;
        this.rowItems5 = rowItems5;
    }

    @Override
    public int getCount() {
        return rowItems5.size();
    }

    @Override
    public Object getItem(int position) {
        return rowItems5.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rowItems5.indexOf(getItem(position));
    }

    /* private view holder class */
    private class ViewHolder {
        ImageView profile_pic5;
        TextView member_name5;
        TextView status5;
        CheckBox checkboxNow;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        holder = null;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.delete_item, null);
            holder = new ViewHolder();

            holder.member_name5 = (TextView) convertView.findViewById(R.id.member_name5);
            holder.profile_pic5 = (ImageView) convertView.findViewById(R.id.profile_pic5);
            holder.status5 = (TextView) convertView.findViewById(R.id.status5);
            holder.checkboxNow=convertView.findViewById(R.id.checkboxNow);


           final RowItem5_quotes row_pos = rowItems5.get(position);

            //holder.profile_pic.setBackground(R.drawable.circle);
        //    holder.profile_pic5.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_view_headline_blue_grey_200_18dp));//del
            holder.member_name5.setText(row_pos.getMember_name5());
            holder.status5.setText(row_pos.getStatus5());

            if(row_pos.getIntCheck()==1)
            {
                holder.checkboxNow.setTag(Integer.valueOf(position));
                holder.checkboxNow.setChecked(true);
            }
            else
            {
                holder.checkboxNow.setTag(Integer.valueOf(position));
                holder.checkboxNow.setChecked(false);
            }

            holder.checkboxNow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked)
                    {

                        row_pos.setIntCheck(1);
                    }
                    else
                    {
                        row_pos.setIntCheck(0);
                    }
                    notifyDataSetChanged();
                }
            });


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

}
