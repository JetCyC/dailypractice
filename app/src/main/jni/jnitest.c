//
// Created by 曹延昌 on 2018/5/30.
//
#include <yc_com_app_biz_ndk_JniTest.h>
JNIEXPORT jstring JNICALL Java_yc_com_app_biz_ndk_JniTest_getStrFromC
  (JNIEnv *env, jclass clazz){
  jstring  ss=(*env)->NewStringUTF(env,"hello world from cc");
  return ss;
  }
 //jni调java
 void callJavaMethod(JNIEnv *env,jobject thiz){

 //jclass clazz =  (*env)->FindClass("yc.com.app.biz.ndk.NdkActivity");
         //类
     //方法
     //调用


 }