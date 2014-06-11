package com.bonsai.wallet32;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.google.bitcoin.core.NetworkParameters;
import com.google.bitcoin.params.MainNetParams;
import com.google.bitcoin.params.TestNet3Params;

/**
 * Created by harald on 11.06.14.
 */
public class Constants
{
	private static NetworkParameters networkParameters = null;
	public static String CHECKPOINTS_FILENAME = null;

	public static NetworkParameters getNetworkParameters(Context context)
	{
		if (networkParameters == null) {
			boolean TEST = true;

			try {
				PackageInfo pinfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
				TEST = pinfo.versionName.contains("-test");
			} catch (PackageManager.NameNotFoundException e) {
				e.printStackTrace();
			}

			networkParameters = TEST ? TestNet3Params.get() : MainNetParams.get();
			CHECKPOINTS_FILENAME = TEST ? "checkpoints-testnet" : "checkpoints";
		}

		return networkParameters;
	}
}
