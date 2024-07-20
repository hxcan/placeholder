/**
 * Copyright (C) 2010 Regis Montoya (aka r3gis - www.r3gis.fr)
 * This file is part of CSipSimple.
 *
 *  CSipSimple is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  CSipSimple is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with CSipSimple.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.simo.ugmate.tools;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.AudioManager;
import android.net.Uri;
import android.net.Uri.Builder;
import android.util.Log;

public class Compatibility {

	private static final String THIS_FILE = "Compatibility";
	public final static String OS = "Android ";

	/**
	 * Get the stream id for in call track. Can differ on some devices. Current
	 * device for which it's different : Archos 5IT
	 * 
	 * @return
	 */
	public static int getInCallStream() {
		if (android.os.Build.BRAND.equalsIgnoreCase("archos")) {
			// Since archos has no voice call capabilities, voice call stream is
			// not implemented
			// So we have to choose the good stream tag, which is by default
			// falled back to music
			return AudioManager.STREAM_MUSIC;
		}
		// return AudioManager.STREAM_MUSIC;
		return AudioManager.STREAM_VOICE_CALL;
	}

	/**
	 * @return	true MODE_IN_CALL
	 * 			false MODE_NORMAL
	 */

	/**����4.0.4�汾�����޷�����pin����������ӷ�ʽ��Ҫ��Щ�޸�
	 * @return
	 */
	public static boolean shouldChangeConnectForBluetooth(){
		if (android.os.Build.MODEL.equalsIgnoreCase("GT-I9300")) {
			return true;
		}
		if (android.os.Build.MODEL.equalsIgnoreCase("GT-N8000")) {
			return true;
		}
		return false;
	}
	
	/**
	 * @return	true MODE_IN_CALL
	 * 			false MODE_NORMAL
	 */
	public static boolean shouldUseModeApi() {
		Log.d(THIS_FILE, "Brand=" + android.os.Build.BRAND + " Device="
				+ android.os.Build.DEVICE + " Product="
				+ android.os.Build.PRODUCT);
		
		// Samsung GT-I5508
		if (android.os.Build.DEVICE.equalsIgnoreCase("GT-I5508")) {
			return true;
		}
		
		// Samsung GT-S5570
		// Samsung GT-S5660
		// Samsung maguro
		
		
		
		
		
		// Samsung GT-I5500
		if (android.os.Build.DEVICE.equalsIgnoreCase("GT-I5500")) {
			return true;
		}
		
		// ZTE blade
		if (android.os.Build.DEVICE.equalsIgnoreCase("blade")) {
			return true;
		}
		
		// Vibo A688
		if (android.os.Build.DEVICE.equalsIgnoreCase("A688")) {
			return true;
		}

		// HTC evo 4G
		if (android.os.Build.PRODUCT.equalsIgnoreCase("htc_supersonic")) {
			return true;
		}
		// LG P500
		if (android.os.Build.PRODUCT.equalsIgnoreCase("thunderg")) {
			return true;
		}
		// Huawei
		if (android.os.Build.DEVICE.equalsIgnoreCase("U8150")
				|| android.os.Build.DEVICE.equalsIgnoreCase("U8110")) {
			return true;
		}

		return false;
	}

	public static boolean shouldUseEchoMode(){
		
//		if (android.os.Build.DEVICE.equalsIgnoreCase("GT-I5508")) {
//			return false;
//		}
		
		// Samsung GT-I5508
		// Samsung GT-S5570
		// Samsung GT-S5660
		// Samsung maguro
		return true;
	}
	
	public static boolean useFlipAnimation() {
		if (android.os.Build.BRAND.equalsIgnoreCase("archos")) {
			return false;
		}
		return true;
	}

	public static List<ResolveInfo> getPossibleActivities(Context ctxt, Intent i) {
		PackageManager pm = ctxt.getPackageManager();
		try {
			return pm.queryIntentActivities(i,
					PackageManager.MATCH_DEFAULT_ONLY
							| PackageManager.GET_RESOLVED_FILTER);
		} catch (NullPointerException e) {
			return new ArrayList<ResolveInfo>();
		}
	}

	public static Intent getPriviledgedIntent(String number) {
		Intent i = new Intent("android.intent.action.CALL_PRIVILEGED");
		Builder b = new Uri.Builder();
		b.scheme("tel").appendPath(number);
		i.setData(b.build());
		return i;
	}

	private static List<ResolveInfo> callIntents = null;

	public final static List<ResolveInfo> getIntentsForCall(Context ctxt) {
		if (callIntents == null) {
			callIntents = getPossibleActivities(ctxt,
					getPriviledgedIntent("123"));
		}
		return callIntents;
	}

	public static boolean canResolveIntent(Context context, final Intent intent) {
		final PackageManager packageManager = context.getPackageManager();
		// final Intent intent = new Intent(action);
		List<ResolveInfo> list = packageManager.queryIntentActivities(intent,
				PackageManager.MATCH_DEFAULT_ONLY);
		return list.size() > 0;
	}
}
