package server;
import com.sun.speech.freetts.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TTSImp extends java.rmi.server.UnicastRemoteObject implements TTS {
    public TTSImp() throws java.rmi.RemoteException{

    }
	public byte[] speak(String text) throws java.rmi.RemoteException{
	    String voicename = "kevin16";//uma voz padrão do freetts
        Voice voice;
        VoiceManager vm = VoiceManager.getInstance();
        voice = vm.getVoice(voicename);
        voice.allocate();
        try{
            voice.speak(text);
            voice.setWaveDumpFile("teste.wav");
        }catch(Exception e){
            System.out.println("Erro!!");
        }
        String file = voice.getWaveDumpFile();
        FileInputStream fis = null;
        byte[] arq = null;
        try {
            fis = new FileInputStream(file);
            arq = new byte[fis.available()];
            int i=0;
            while(fis.available() > 0){
                arq[i]=(byte)fis.read();
            }
            fis.close();
            return arq;
        }catch(Exception fnfex){
            System.out.println("Exception!!! Causa: "+fnfex);
        }
        return arq;
	}
}
