package com.android.icefire.zhihudailydagger.support.utils;

import android.os.Environment;

import com.android.icefire.zhihudailydagger.app.ZhihuApp;
import com.orhanobut.logger.Logger;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;

/**
 * Created by yangchj on 16/7/23.
 */
public class FileUtils {
    public static final String baseDicectory = Environment.getExternalStorageDirectory() + File.separator;

    public static String getDownloadDic() {
        String downloadDic = baseDicectory + ZhihuApp.get().getPackageName();
        try {
            File dicFile = new File(downloadDic);
            if (!dicFile.exists()) {
                dicFile.mkdirs();
            }
        } catch (Exception e) {
            downloadDic = null;
            Logger.e(e, ExceptionUtils.getErrorInfo(e));
        }
        return downloadDic;
    }

    public static boolean saveResponseBodyToDisk(ResponseBody body, String saveName) {
        String dic=getDownloadDic();
        if(dic==null){
            return false;
        }
        try {
            File file = new File(dic + File.separator + saveName);
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                byte[] fileReader = new byte[4096];
                long fileSize=body.contentLength();
                long fileSizeDownloaded=0;
                inputStream=body.byteStream();
                outputStream=new FileOutputStream(file);
                while (true){
                    int read=inputStream.read(fileReader);
                    if(read==-1){
                        break;
                    }
                    outputStream.write(fileReader,0,read);
                    fileSizeDownloaded+=read;
                    Logger.w("saveFile:%d,%s,%d",fileSizeDownloaded,"of",fileSize);
                }
                outputStream.flush();
                return true;
            }catch (IOException ex){
                Logger.e(ex,ExceptionUtils.getErrorInfo(ex));
                return false;
            }finally {
                if(inputStream!=null){
                    inputStream.close();
                }
                if(outputStream!=null){
                    outputStream.close();
                }
            }
        }catch (Exception e){
            Logger.e(e,ExceptionUtils.getErrorInfo(e));
            return false;
        }
    }
}
