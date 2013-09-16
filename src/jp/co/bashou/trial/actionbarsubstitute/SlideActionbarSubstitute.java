package jp.co.bashou.trial.actionbarsubstitute;

import java.util.Iterator;
import java.util.LinkedHashMap;

import com.example.actionbarsubstitute.R;


import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 *
 * @author fior
 */
public class SlideActionbarSubstitute extends FrameLayout {

	private Context mContext;

	public SlideActionbarSubstitute(Context context) {
		super(context);
		mContext = context;
		initView();
	}
	public SlideActionbarSubstitute(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		initView();
	}
	public SlideActionbarSubstitute(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		initView();
	}

	private int mWidHeight = 100;
	private int mTabHeight = 60;
	private int textSize   = 22;


	private LinearLayout linearTab;
	private LinearLayout linearWid;
	private ImageButton  icon;
	private TextView     status;

	private void initView() {

		initViewDoubleLine();
	}

	private void initViewDoubleLine() {

		LinearLayout linearParent = new LinearLayout(mContext);
		linearParent.setOrientation(LinearLayout.VERTICAL);
		linearParent.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		linearParent.setBackgroundResource(R.color.bw_1);

		//linear
		LinearLayout linearFirstLine = new LinearLayout(mContext);
		linearFirstLine.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, mWidHeight));
		linearParent.addView(linearFirstLine);

		//icon
		icon = new ImageButton(mContext);
		icon.setLayoutParams(new LayoutParams(mWidHeight, mWidHeight));
		icon.setScaleType(ScaleType.FIT_XY);
		icon.setImageResource(DEFAULT_ICON_DRAWABLE);
		icon.setPadding(0, 0, 0, 0);
		icon.setBackgroundResource(R.color.alpha_zero);
		icon.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if ( mListener instanceof OnCallbackActionbarListener ) {
					mListener.onPressedIcon();
				}
			}
		});

		//status
		LinearLayout linearStatus = new LinearLayout(mContext);
		linearStatus.setOrientation(LinearLayout.VERTICAL);
		linearStatus.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
		linearStatus.setGravity(Gravity.CENTER_VERTICAL);
		status = new TextView(mContext);
		status.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
		status.setTextSize(textSize);
		status.setTextColor(Color.WHITE);
		status.setPadding(20, 0, 20, 0);
		linearStatus.addView(status);
		linearStatus.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if ( mListener instanceof OnCallbackActionbarListener ) {
					mListener.onPressedStatus();
				}
			}
		});

		//scroll
		LinearLayout linearScroll = new LinearLayout(mContext);
		linearScroll.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT,1));
		linearScroll.setOrientation(LinearLayout.HORIZONTAL);
		linearScroll.setGravity(Gravity.RIGHT);
		HorizontalScrollView scrollParentWid = new HorizontalScrollView(mContext);
		scrollParentWid.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, mWidHeight));
		scrollParentWid.setHorizontalScrollBarEnabled(false);

		linearScroll.addView(scrollParentWid);

		linearFirstLine.addView(icon);
		linearFirstLine.addView(linearStatus);
		linearFirstLine.addView(linearScroll);


		//secoundline
		LinearLayout linearSecondLine = new LinearLayout(mContext);
		linearSecondLine.setOrientation(LinearLayout.HORIZONTAL);
		linearSecondLine.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,mTabHeight));
		linearParent.addView(linearSecondLine);

		HorizontalScrollView scrollParentTab = new HorizontalScrollView(mContext);
		scrollParentTab.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		scrollParentTab.setHorizontalScrollBarEnabled(false);
		linearSecondLine.addView(scrollParentTab);


		//left
		linearTab = new LinearLayout(mContext);
		linearTab.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
		//right
		linearWid = new LinearLayout(mContext);
		linearWid.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));

		scrollParentWid.addView(linearWid);
		scrollParentTab.addView(linearTab);

		addView(linearParent);

	}



//////////////////////////////////////////////////////////////////////////////////////////////////
/**  setget  **/
////////////////////////////////////////////////////////////////////////////////////////////////

	private ImageButton createWid(int pageId, int imgId) {
		ImageButton imgbtn = new ImageButton(mContext);
		imgbtn.setImageResource(imgId);
		imgbtn.setId(pageId);
		imgbtn.setBackgroundResource(R.drawable.widget_bgp);
		LayoutParams params = new LayoutParams(mWidHeight, mWidHeight);
		params.setMargins(0, 0, 0, 0);
		imgbtn.setLayoutParams(params);
		imgbtn.setPadding(0, 0, 0, 0);
		imgbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if ( mListener instanceof OnCallbackActionbarListener ) {
					mListener.onPressedWid(WID.values()[v.getId()]);
				}
			}
		});
		return imgbtn;
	}

	private Button createBtn(int pageId, String pageText, int bgd) {
		Button btn = new Button(mContext);
		btn.setText(pageText);
		btn.setTextColor(Color.WHITE);
		btn.setId(pageId);
		btn.setBackgroundResource(bgd);
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.setMargins(0, 0, 0, 0);
		btn.setLayoutParams(params);
		btn.setPadding(0, 0, 0, 0);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				buttonInitBackground(v.getId());
				if ( mListener instanceof OnCallbackActionbarListener ) {
					mListener.onSelectedTab(PAGE.values()[v.getId()]);
				}
			}
		});
		return btn;
	}

	private void buttonInitBackground(int id) {
		int vcnt = linearTab.getChildCount();
		for ( int cnt = 0; cnt < vcnt; cnt++ ) {
			View v = linearTab.getChildAt(cnt);
			if ( v.getId() == id ) {
				v.setBackgroundResource(DEFAULT_BUTTON_BACKGROUND_SELECTED);
			} else {
				v.setBackgroundResource(DEFAULT_BUTTON_BACKGROUND_NONSELECTED);
			}
		}
	}


	private static final int DEFAULT_ICON_DRAWABLE                 = R.drawable.ic_app;
	private static final int DEFAULT_BUTTON_BACKGROUND_SELECTED    = R.drawable.tab_selected;
	private static final int DEFAULT_BUTTON_BACKGROUND_NONSELECTED = R.drawable.tab_nonselected;

//////////////////////////////////////////////////////////////////////////////////////////////////
/**  呼び出しメソッド  **/
////////////////////////////////////////////////////////////////////////////////////////////////


	/**
	 *
	 * @param tabMap
	 */
	public void setTabList( LinkedHashMap<PAGE, String> tabMap, PAGE selected ) {
		linearTab.removeAllViews();
		Iterator<PAGE> it = tabMap.keySet().iterator();
		while (it.hasNext()) {
			PAGE page = it.next();
			String  column = tabMap.get(page);
			int bgd = (page.equals(selected)) ? DEFAULT_BUTTON_BACKGROUND_SELECTED: DEFAULT_BUTTON_BACKGROUND_NONSELECTED;
			Button btn = createBtn(page.ordinal(), column, bgd);
			linearTab.addView(btn);
		}
	}

	/**
	 *
	 * @param tabMap
	 */
	public void setWidList( LinkedHashMap<WID, Integer> widMap ) {
		if ( widMap instanceof LinkedHashMap == false ) {
			return;
		}
		linearWid.removeAllViews();
		Iterator<WID> it = widMap.keySet().iterator();
		while (it.hasNext()) {
			WID page = it.next();
			int img = widMap.get(page);
			ImageButton imgbtn = createWid(page.ordinal(), img);
			linearWid.addView(imgbtn);
		}
	}

	/**
	 *
	 * @param iconId
	 * @param pageNum
	 */
	public void setStatus(String st) {
		status.setText(st);
	}


	public PAGE getSelectedTab() {
		return selectedTab;
	}
	private PAGE selectedTab;


//////////////////////////////////////////////////////////////////////////////////////////////////
/**  コールバックインターフェイス  **/
////////////////////////////////////////////////////////////////////////////////////////////////

	private OnCallbackActionbarListener mListener;
	public void setOnCallbackActionbarListener ( OnCallbackActionbarListener listener ) {
		mListener = listener;
	}
	public interface OnCallbackActionbarListener {
		public void onPressedIcon();
		public void onPressedStatus();
		public void onSelectedTab(PAGE page);
		public void onPressedWid(WID wid);
	}



	public enum PAGE {
		PAGEOF_TASKLIST,
		PAGEOF_TIMELINE_HOME,
		PAGEOF_HISTORYLIST,
		PAGEOF_USERLIST,
		PAGEOF_MENTION,
		PAGEOF_DIRECTMESSAGE,
		PAGEOF_SETTING,
		PAGEOF_USERDETAIL,

	}
	public enum WID {
		WIDOF_REFLESH,
		WIDOF_ADD,
		WIDOF_SHARE,
		WIDOF_WRITE,
		WIDOF_SEARCH,
		WIDOF_DELETE,
		WIDOF_USERADD,
		WIDOF_UP,
		WIDOF_DOWN,
	}



}




