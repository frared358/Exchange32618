package com.affwl.exchange.fx;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.affwl.exchange.R;
import com.affwl.exchange.RowItem1;

import java.util.List;

public class CustomAdapter1 extends BaseAdapter {

	Context context;
	List<RowItem1> rowItems;


	CustomAdapter1(Context context, List<RowItem1> rowItems) {
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

	/* private view holder class */
	private class ViewHolder {

		TextView member_name2;
		TextView status2;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;

		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_item3, null);
			holder = new ViewHolder();

			holder.member_name2 = (TextView) convertView
					.findViewById(R.id.member_name2);

			holder.status2 = (TextView) convertView.findViewById(R.id.status2);


			RowItem1 row_pos = rowItems.get(position);


					holder.member_name2.setText(row_pos.getMember_name2());
					holder.status2.setText(row_pos.getStatus2());


			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		return convertView;
	}

}
