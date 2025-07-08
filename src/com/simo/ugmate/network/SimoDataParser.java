package com.simo.ugmate.network;

import java.io.InputStream;
import java.net.CookieStore;
import java.util.ArrayList;

/**
 * @author JinPing.Lin
 * @since Mar 13, 2014
 */
public final class SimoDataParser {
	private static final String TAG = "SimoDataParser";

	public interface DataParseStratergy<T extends SimoResponseData> {
		/**
		 * @param is
		 *            xml input stream, don't worry stream close issue.
		 * @return SimoResponseData instance
		 */
		public ArrayList<T> parse(InputStream is,  CookieStore cookieStore) throws Exception;
	}

	public interface OnCompletionListener {
		public void onCompleted(ParseResult result);
	}

	public class ParseResult {
		public boolean success;
		public ArrayList<? extends SimoResponseData> data;
		public Throwable error;
	}
}
