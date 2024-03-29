package com.jianjunhuang.common_base.utils;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import androidx.core.content.FileProvider;

import java.io.File;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2018/04/20
 *     desc  : URI 相关
 * </pre>
 */
public final class UriUtil {

  private UriUtil() {
    throw new UnsupportedOperationException("u can't instantiate me...");
  }

  private static Context mContext;

  public static void init(Context context) {
    mContext = context;
  }

  /**
   * File to uri.
   *
   * @param file The file.
   * @return uri
   */
  public static Uri file2Uri(final File file) {
    if (file == null) {
      return null;
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      String authority = mContext.getPackageName() + ".utilcode.provider";
      return FileProvider.getUriForFile(mContext, authority, file);
    } else {
      return Uri.fromFile(file);
    }
  }

  /**
   * Uri to file.
   *
   * @param uri The uri.
   * @param columnName The name of the target column.
   * <p>e.g. {@link MediaStore.Images.Media#DATA}</p>
   * @return file
   */
  public static File uri2File(final Uri uri, final String columnName) {
    if (uri == null) {
      return null;
    }
    CursorLoader cl = new CursorLoader(mContext);
    cl.setUri(uri);
    cl.setProjection(new String[]{columnName});
    Cursor cursor = cl.loadInBackground();
    int columnIndex = cursor.getColumnIndexOrThrow(columnName);
    cursor.moveToFirst();
    return new File(cursor.getString(columnIndex));
  }
}
