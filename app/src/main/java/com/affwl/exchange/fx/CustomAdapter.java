package com.affwl.exchange.fx;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.affwl.exchange.R;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

	Context context;
	List<RowItem_news> rowItems;

	CustomAdapter(Context context, List<RowItem_news> rowItems) {
		this.context = context;
		this.rowItems = rowItems;
	}

	@Override
	public int getCount() {
		return rowItems.size();
	}

	@Override
	public Object getItem(int position) {
		return rowItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return rowItems.indexOf(getItem(position));
	}

	/* private view holder class class */
	private class ViewHolder {
		ImageView profile_pic;
		TextView member_name;
		TextView status;
		TextView contactType;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;

		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_item, null);
			holder = new ViewHolder();

			holder.member_name = (TextView) convertView.findViewById(R.id.member_name);
			holder.profile_pic = (ImageView) convertView.findViewById(R.id.profile_pic);
			holder.status = (TextView) convertView.findViewById(R.id.status);
			holder.contactType = (TextView) convertView.findViewById(R.id.contactType);

			RowItem_news row_pos = rowItems.get(position);

			//holder.profile_pic.setBackground(R.drawable.circle);
			holder.profile_pic.setBackground(ContextCompat.getDrawable(context, R.drawable.circle));
			holder.member_name.setText(row_pos.getMember_name());
			holder.status.setText(row_pos.getStatus());
			holder.contactType.setText(row_pos.getContactType());

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		return convertView;
	}

}
