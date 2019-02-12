package com.example.yanjiang.stockchart.util;

import android.content.Context;

import org.apache.http.util.EncodingUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil {
	public static String ReadFile(Context context, String fileName) {
		String str = "";
		try {
			// FileInputStream fileInputStream = new FileInputStream(Path);
			InputStream in = context.getAssets().open(fileName);
			int length = in.available();
			byte[] buffer = new byte[length];
			in.read(buffer);
			in.close();
			str = EncodingUtils.getString(buffer, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 拷贝assert目录下的文件到SD卡中
	 * @param context
	 * @param path
	 * @param filename
	 */
	public static void copyFileToSD(Context context, String path, String filename) {
		try {
			// 打开资产目录下的文件
			InputStream inputStream = context.getAssets().open(filename);
			File f = new File(path);
			if(!f.exists()){
				f.mkdirs();
			}
			File file = new File(path, filename);
			FileOutputStream fos = new FileOutputStream(file);
			int len = 0;
			byte[] buffer = new byte[1024];
			while ((len = inputStream.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
			}
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
