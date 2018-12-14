package server;
public interface TTS extends java.rmi.Remote{
	void speak(String text) throws java.rmi.RemoteException;
}
