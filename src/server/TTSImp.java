package server;
import com.sun.speech.freetts.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class TTSImp extends java.rmi.server.UnicastRemoteObject implements TTS {
    public TTSImp() throws java.rmi.RemoteException{

    }
	public FileInputStream speak(String text) throws java.rmi.RemoteException{
	    String voicename = "kevin16";//uma voz padrão do freetts
        Voice voice;
        VoiceManager vm = VoiceManager.getInstance();
        voice = vm.getVoice(voicename);
        voice.allocate();
        FileInputStream fis;
        try{
            voice.speak(text);
            voice.setWaveDumpFile("teste.wav");
            fis = new FileInputStream("teste.wav");
            return fis;
        }catch(Exception e){
            System.out.println("Erro!!");
        }
        return null;
	}
}
