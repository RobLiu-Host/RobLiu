package com.example.helper;


import com.example.util.ReflexUtil;
import com.proxy.MockProxy;

import java.lang.reflect.Proxy;

public class HookHelp {

    public static void AMN() throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        //singleTon的值
        Object gDefault = ReflexUtil.getFieldValue("android.app.ActivityManagerNative", "gDefault");
        //对应接口的值
        Object rawIActivityManger = ReflexUtil.getFieldValue("android.util.Singleton", gDefault, "mInstance");

        Class<?> iActivityManger = ReflexUtil.getClass("android.app.IActivityManager");

        Object rawIActivityMangerProxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{iActivityManger}, new MockProxy(rawIActivityManger));

        ReflexUtil.setField("android.util.Singleton", gDefault, "mInstance", rawIActivityMangerProxy);
    }
}
