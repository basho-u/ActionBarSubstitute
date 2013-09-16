package jp.co.bashou.trial.actionbarsubstitute;

import java.util.LinkedHashMap;

import jp.co.bashou.trial.actionbarsubstitute.SlideActionbarSubstitute.OnCallbackActionbarListener;
import jp.co.bashou.trial.actionbarsubstitute.SlideActionbarSubstitute.PAGE;
import jp.co.bashou.trial.actionbarsubstitute.SlideActionbarSubstitute.WID;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.actionbarsubstitute.R;

public class MainActivity extends FragmentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initActionBar();
	}

	private SlideActionbarSubstitute actionbar;
	protected void initActionBar() {
		if ( findViewById(R.id.action_bar) instanceof SlideActionbarSubstitute ) {
			actionbar = (SlideActionbarSubstitute)findViewById(R.id.action_bar);
			actionbar.setOnCallbackActionbarListener(actionbarListener);
			actionbar.setWidList(getDefaultWid());
		}
	}

	private OnCallbackActionbarListener actionbarListener = new OnCallbackActionbarListener() {
		@Override
		public void onSelectedTab(PAGE page) {
		}
		@Override
		public void onPressedWid(WID wid) {
		}
		@Override
		public void onPressedStatus() {
		}
		@Override
		public void onPressedIcon() {
		}
	};


	public LinkedHashMap<WID, Integer> getDefaultWid() {
		LinkedHashMap<WID, Integer> wid = new LinkedHashMap<WID, Integer>();
		wid.put(WID.WIDOF_USERADD, Resources.getSystem().getIdentifier("ic_menu_invite", "drawable", "android"));
		return wid;
	}

}
