package com.criptext;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChatArrayAdapter extends ArrayAdapter<SingleChat> {

	private TextView chatView;
	private List<SingleChat> singleChat = new ArrayList<SingleChat>();
	private LinearLayout wrapper;

	@Override
	public void add(SingleChat object) {
		singleChat.add(object);
		super.add(object);
	}
	
	public void remove(int pos) {
		singleChat.remove(pos);
		notifyDataSetChanged();
	}
	
	public ChatArrayAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
	}

	public int getCount() {
		return this.singleChat.size();
	}

	public SingleChat getItem(int index) {
		return this.singleChat.get(index);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.listitem_discuss, parent, false);
		}
		
		wrapper = (LinearLayout) row.findViewById(R.id.wrapper);

		SingleChat coment = getItem(position);

		chatView = (TextView) row.findViewById(R.id.comment);

		chatView.setText(coment.comment);

		chatView.setBackgroundResource(coment.left ? R.drawable.bubble_yellow : R.drawable.bubble_green);
		wrapper.setGravity(coment.left ? Gravity.LEFT : Gravity.RIGHT);

		return row;
	}
	

	public Bitmap decodeToBitmap(byte[] decodedByte) {
		return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
	}

}