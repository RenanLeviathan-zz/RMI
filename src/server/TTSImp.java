package server;
import com.sun.speech.freetts.*;
public class TTSImp extends java.rmi.server.UnicastRemoteObject implements TTS {
    public TTSImp() throws java.rmi.RemoteException{

    }
	public void speak(String text) throws java.rmi.RemoteException{
	    String voicename = "kevin16";//uma voz padrão do freetts
        Voice voice;
        VoiceManager vm = VoiceManager.getInstance();
        voice = vm.getVoice(voicename);
        voice.allocate();
        try{
            voice.speak(text);
        }catch(Exception e){
            System.out.println("Erro!!");
        }

	}
}
