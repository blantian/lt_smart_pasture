#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_lantian_lib_1lan_net_tcp_TCPServer_helloTCP(JNIEnv * env, jclass clazz) {
// TODO: implement helloTCP()
    using namespace std;
    string hello = "helloTCP";
    return env->NewStringUTF(hello.c_str());
}



extern "C"
JNIEXPORT jint JNICALL Java_com_lantian_lib_1lan_net_tcp_TCPServer_helloInt
            (JNIEnv * env, jclass){

}
