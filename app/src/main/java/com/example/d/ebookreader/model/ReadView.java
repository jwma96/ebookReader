package com.example.d.ebookreader.model;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.widget.TextView;

public class ReadView extends TextView {

	public ReadView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public ReadView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ReadView(Context context) {
		super(context);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		resize();
	}
	
	/**
	 * 取出当前页无法显示的字
	 * @return 去掉的字数
	 */
	public int resize() {
		CharSequence oldContent = getText();
		CharSequence newContent = oldContent.subSequence(0, getCharNum());//subString
		setText(newContent);
		return oldContent.length() - newContent.length();
	}
	
	/**
	 * 获取当前页总字数
	 */
	public int getCharNum() {
		return getLayout().getLineEnd(getLineNum());//获取最后一行的结束坐标
	}
	
	/**
	 * 获取当前页总行数  padding top指该控件内部内容距离该控件上边缘的边距 line height设置行间距离 行高
	 */
	public int getLineNum() {
		Layout layout = getLayout();
		int topOfLastLine = getHeight() - getPaddingTop() - getPaddingBottom() - getLineHeight();
		return layout.getLineForVertical(topOfLastLine);//获取行数
	}
}
