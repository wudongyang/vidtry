/***
	Copyright (c) 2008-2009 CommonsWare, LLC
	
	Licensed under the Apache License, Version 2.0 (the "License"); you may
	not use this file except in compliance with the License. You may obtain
	a copy of the License at
		http://www.apache.org/licenses/LICENSE-2.0
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
*/

package com.commonsware.android.vidtry;

import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.util.AttributeSet;
import java.util.ArrayList;

public class TappableSurfaceView extends SurfaceView {
	private ArrayList<TapListener> listeners=new ArrayList<TapListener>();
	private GestureDetector gesture=null;
	
	public TappableSurfaceView(Context context,
															AttributeSet attrs) {
		super(context, attrs);
	}
	
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction()==MotionEvent.ACTION_UP) {
			gestureListener.onSingleTapUp(event);
		}
		
		return(true);
	}
	
	void addTapListener(TapListener l) {
		listeners.add(l);
	}
	
	void removeTapListener(TapListener l) {
		listeners.remove(l);
	}
	
	private GestureDetector.SimpleOnGestureListener gestureListener=
		new GestureDetector.SimpleOnGestureListener() {
		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			for (TapListener l : listeners) {
				l.onTap(e);
			}
			
			return(true);
		}
	};
	
	interface TapListener {
		void onTap(MotionEvent event);
	}
}