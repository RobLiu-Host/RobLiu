#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_ndk1_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "dald= 测试下输入中文版呢";
    return env->NewStringUTF(hello.c_str());
}
