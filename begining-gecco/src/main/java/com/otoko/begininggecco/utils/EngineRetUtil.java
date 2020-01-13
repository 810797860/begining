package com.otoko.begininggecco.utils;

import com.otoko.begininggecco.spider.SpiderThreadLocal;

public class EngineRetUtil{

    public static Object getRet(){
        return SpiderThreadLocal.get().getEngine().getRet();
    }

    public static void setRet(Object o){
        SpiderThreadLocal.get().getEngine().setRet(o);
    }

}
