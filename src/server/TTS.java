package server;

import java.io.FileInputStream;

public interface TTS extends java.rmi.Remote{
	FileInputStream speak(String text) throws java.rmi.RemoteException;
}
