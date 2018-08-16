package com.zdjc.zdjcyun.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;


public class MyTextWatcher implements TextWatcher {

	private boolean check = false, check_ = true;
	private Button btSubmit;

	public MyTextWatcher(Button btSubmit) {
		this.btSubmit=btSubmit;
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		if (s.length() > 0) {
			check = true;
		} else if(s.length() == 0){
			check_ = true;							//开门
			check = false;
		}
		if(check){
			if(check_){
				EdtCheckEntity.checkNum++;
				check_ = false;						//关门
				if(EdtCheckEntity.checkNum == 2){
					btSubmit.setEnabled(true);
				}
			}
		}else{
			EdtCheckEntity.checkNum--;
			if(EdtCheckEntity.checkNum < 2){
				btSubmit.setEnabled(false);
			}
		}
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

	@Override
	public void afterTextChanged(Editable s) {}
	
}