package server;

import java.rmi.Naming;
public class TTSserver {
public TTSserver() {
try {
TTS obj = new TTSImp();
Naming.rebind("//localhost/TTSService", obj);
}
catch( Exception e) {
System.out.println("Erro: " + e);
}
}
public static void main( String[] args ) {
new TTSserver();
}
}
