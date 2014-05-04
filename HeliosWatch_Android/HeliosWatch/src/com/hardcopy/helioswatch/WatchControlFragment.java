/*
 * Copyright (C) 2014 The Retro Watch - Open source smart watch project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hardcopy.helioswatch;

import com.hardcopy.helioswatch.utils.Settings;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

/**
 * This fragment shows user defined message filters.
 */
public class WatchControlFragment extends Fragment {

	private Context mContext = null;
	private IFragmentListener mFragmentListener = null;
	
	private EditText mEditGmailAddr = null;
	private Spinner mSpinnerClockStyle = null;
	private Spinner mSpinnerIndicator = null;
    private Spinner mSpinnerMotor = null;
	
	private int mPresetClockStyle = -1;
	private int mPresetIndicator = -1;

	public WatchControlFragment(Context c, IFragmentListener l) {
		mContext = c;
		mFragmentListener = l;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_watch_control, container, false);
		
		mEditGmailAddr = (EditText) rootView.findViewById(R.id.edit_email_addr);
		String defaultAddr = Settings.getInstance(mContext).getGmailAddress();
		if(defaultAddr != null && !defaultAddr.isEmpty())
			mEditGmailAddr.setText(defaultAddr);
		mEditGmailAddr.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				String addr = s.toString();
				if(addr != null && !addr.isEmpty()) {
					mFragmentListener.OnFragmentCallback(IFragmentListener.CALLBACK_REQUEST_SET_EMAIL_ADDRESS, 
							0, 0, addr, null, null);
				}
			}
			@Override public void beforeTextChanged(CharSequence s, int start, int count,int after) {}
			@Override public void afterTextChanged(Editable s) {}
		});
		
		mSpinnerClockStyle = (Spinner) rootView.findViewById(R.id.spinner_clock_style);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mContext, 
				R.array.clock_style_array, 
				R.layout.spinner_simple_item2);
		adapter.setDropDownViewResource(R.layout.spinner_dropdown_simple_item);
		mSpinnerClockStyle.setPrompt(mContext.getString(R.string.clock_style_title));
		mSpinnerClockStyle.setAdapter(adapter);
		mSpinnerClockStyle.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if(mPresetClockStyle > -1 && mPresetClockStyle != position) {
					mFragmentListener.OnFragmentCallback(IFragmentListener.CALLBACK_REQUEST_CLOCK_STYLE, 
							position+1, 0, null, null, null);
				}
				mPresetClockStyle = position;
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});
		
		mSpinnerIndicator = (Spinner) rootView.findViewById(R.id.spinner_show_indicator);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(mContext,
                R.array.clock_indicator_array,
                R.layout.spinner_simple_item2);
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown_simple_item);
        mSpinnerIndicator.setPrompt(mContext.getString(R.string.clock_indicator_title));
        mSpinnerIndicator.setAdapter(adapter2);
        mSpinnerIndicator.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(mPresetIndicator > -1 && mPresetIndicator != position) {
                    mFragmentListener.OnFragmentCallback(IFragmentListener.CALLBACK_REQUEST_SHOW_INDICATOR,
                            position+1, 0, null, null, null);
                }
                mPresetIndicator = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {}
        });

        mSpinnerMotor = (Spinner) rootView.findViewById(R.id.spinner_vibration_motor);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(mContext,
                R.array.vibration_motor_array,
                R.layout.spinner_simple_item2);
        adapter3.setDropDownViewResource(R.layout.spinner_dropdown_simple_item);
        mSpinnerMotor.setPrompt(mContext.getString(R.string.vibration_motor_title));
        mSpinnerMotor.setAdapter(adapter3);
        mSpinnerMotor.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(mPresetIndicator > -1 && mPresetIndicator != position) {
                    mFragmentListener.OnFragmentCallback(IFragmentListener.CALLBACK_REQUEST_VIBRATE_MOTOR,
                            position+1, 0, null, null, null);
                }
                mPresetIndicator = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {}
        });
		
		return rootView;
	}
	
	
}
