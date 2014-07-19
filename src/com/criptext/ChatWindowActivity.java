package com.criptext;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.ListView;
import de.svenjacobs.loremipsum.LoremIpsum;

public class ChatWindowActivity extends Activity {
	private com.criptext.ChatArrayAdapter adapter;
	private ListView lv;
	private LoremIpsum ipsum;
	private EditText editText1;
	private static Random random;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_discuss);
		random = new Random();
		ipsum = new LoremIpsum();

		lv = (ListView) findViewById(R.id.listView1);

		adapter = new ChatArrayAdapter(getApplicationContext(), R.layout.listitem_discuss);

		lv.setAdapter(adapter);

		editText1 = (EditText) findViewById(R.id.editText1);
		editText1.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
					adapter.add(new SingleChat(false, editText1.getText().toString()));
					editText1.setText("");
					removeItems();
					return true;
				}
				return false;
			}
		});
		
		addItems();
		removeItems();	
	}
	

	private void addItems() {
		adapter.add(new SingleChat(true, "Hello!"));
		adapter.add(new SingleChat(false, "Hello There!"));
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
		  public void run() {
			  for (int i = 0; i < 4; i++) {
					boolean left = getRandomInteger(0, 1) == 0 ? true : false;
					int word = getRandomInteger(1, 10);
					int start = getRandomInteger(1, 40);
					String text = ipsum.getWords(word, start);
					adapter.add(new SingleChat(left, text));
					removeItems();
				}
		  }
		}, 5000);
		
	}
	
	private void removeItems(){
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
		  public void run() {
			adapter.remove(0);
		  }
		}, 10000);
	}
	
	private static int getRandomInteger(int aStart, int aEnd) {
		if (aStart > aEnd) {
			throw new IllegalArgumentException("Start cannot exceed End.");
		}
		long range = (long) aEnd - (long) aStart + 1;
		long fraction = (long) (range * random.nextDouble());
		int randomNumber = (int) (fraction + aStart);
		return randomNumber;
	}

}