package com.zz.ccy.menu;

/**
 * 复合菜单
 * @author lez
 * 2015-07-28
 */
public class ComplexButton extends Button {
	private Button[] sub_button;

	public Button[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}
}
