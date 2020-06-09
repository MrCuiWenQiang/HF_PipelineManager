package com.zt.map.constant;

import android.os.Environment;
import android.text.TextUtils;

import com.zt.map.MyApplication;

import java.io.File;

import cn.faker.repaymodel.util.FileUtility;

public class FileContract {
    private static final String defPath = "/excel";
    private static  String excelPath;
    /**
     * excel导出文件夹
     * @return
     */
    public static String getOutFilePath(){
/*        if (TextUtils.isEmpty(excelPath)){
            excelPath = FileUtility.getFileDir(MyApplication.getContext()) + defPath;
//            excelPath = MyApplication.getContext().getFilesDir().getAbsolutePath() + defPath;
            File file = new File(excelPath);
            if (!file.exists())
                file.mkdirs();
        }*/

        if (excelPath == null) {
            excelPath = Environment.getExternalStorageDirectory() + "/PipManagerexcel";
            File file = new File(excelPath);
            if (!file.exists())
                file.mkdir();
        }

        return excelPath;
    }
}
