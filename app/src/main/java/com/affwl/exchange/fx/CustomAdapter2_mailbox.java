package com.affwl.exchange.fx;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.affwl.exchange.R;

import java.util.List;

public class CustomAdapter2_mailbox extends BaseAdapter {

	Context context;
	List<RowItem2_mailbox> rowItems2;


	CustomAdapter2_mailbox(Context context, List<RowItem2_mailbox> rowItems) {
		this.context = context;
		this.rowItems2 = rowItems2;

	}

	@Override
	public int getCount() {
		return rowItems2.size();
	}

	@Override
	public Object getItem(int position) {
		return rowItems2.get(position);
	}

	@Override
	public long getItemId(int position) {
		return rowItems2.indexOf(getItem(position));
	}

	/* private view holder class */
	private class ViewHolder {
		ImageView profile_pic1;
		TextView member_name1;
		TextView status1;
		TextView contactType1;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;

		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_item2, null);
			holder = new ViewHolder();

			holder.member_name1 = (TextView) convertView
					.findViewById(R.id.member_name1);
			holder.profile_pic1 = (ImageView) convertView
					.findViewById(R.id.profile_pic1);
			holder.status1 = (TextView) convertView.findViewById(R.id.status1);
			holder.contactType1 = (TextView) convertView
					.findViewById(R.id.contact_type1);

			RowItem2_mailbox row_pos = rowItems2.get(position);
			//holder.profile_pic1.setBackground(ContextCompat.getDrawable(context, R.drawable.main));
					holder.profile_pic1.setImageResource(row_pos.getProfile_pic_id1());
					holder.member_name1.setText(row_pos.getMember_name1());
					holder.status1.setText(row_pos.getStatus1());
					Log.i("Dip",""+row_pos.getContactType1());
					holder.contactType1.setText(row_pos.getContactType1());

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		return convertView;
	}

}
