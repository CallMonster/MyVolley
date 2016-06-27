package com.android.volley.toolbox.multipart;

import java.io.File;
import java.util.Map;

/**
 * Created by Lee on 2015/12/17.
 */
public interface MultiPartRequest {

    /**
     * 添加file类型的params
     * @param param
     * @param file
     */
    public void addFileParams(String param,File file);

    /**
     * 添加string类型的params
     * @param param
     * @param content
     */
    public void addStringParams(String param,String content);

    /**
     * 只添加file类型的params
     * @return
     */
    public Map<String,File> getFileParams();

    /**
     * 只填加String类型的params
     * @return
     */
    public Map<String,String> getStringParams();

    public void setFileParams(Map<String,File> fileUploads);

    public void setStringParams(Map<String,String> stringUploads);

}
