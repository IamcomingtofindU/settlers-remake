/*******************************************************************************
 * Copyright (c) 2015
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 *******************************************************************************/
package jsettlers.graphics.androidui.menu;

import jsettlers.graphics.action.Action;
import jsettlers.graphics.action.ActionFireable;
import android.view.View;
import android.view.View.OnClickListener;

public class ActionClickListener implements OnClickListener {

	private Action action;
	private ActionFireable fireable;
	private Hideable hide;

	public ActionClickListener(ActionFireable fireable, Action action,
			Hideable hide) {
		this.fireable = fireable;
		this.action = action;
		this.hide = hide;
	}

	@Override
	public void onClick(View arg0) {
		fireable.fireAction(action);
		if (hide != null) {
			hide.requestHide();
		}
	}

}
