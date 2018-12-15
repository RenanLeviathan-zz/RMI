package cliente;

import server.TTS;
import sun.audio.AudioStream;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;

class TTSCliente extends JFrame{
    private JPanel painel;
    private JTextField entrada;
    private JButton btn;
    public TTSCliente(){
        this.painel = new JPanel();
        GridLayout grade = new GridLayout(2,0);
        painel.setLayout(grade);
        this.add(painel);
        this.setSize(300,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.btn = new JButton("Falar");
        this.entrada = new JTextField();
        this.entrada.setSize(100,25);
        painel.add(entrada);
        painel.add(btn);
        btn.addActionListener(new BotaoListener());
    }

    private class BotaoListener implements java.awt.event.ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                TTS tts = (TTS) Naming.lookup("//localhost/TTSService");
                FileInputStream fis =  tts.speak(entrada.getText());
                AudioInputStream as = AudioSystem.getAudioInputStream(fis);
                Clip clip = AudioSystem.getClip();
                clip.open(as);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
            }
            catch ( MalformedURLException murle ) {
                System.out.println( );
                System.out.println( "MalformedURLException" );
                System.out.println( murle );
            }catch(RemoteException ex){
                System.out.println( "RemoteException" );
            }catch(NotBoundException nbe){
                System.out.println( "NotBoundException" );
            }catch(UnsupportedAudioFileException ex){
                System.out.println( "UnsupportedAudioException" );
            }catch(IOException ex){
                System.out.println( "IOException" );
            }catch(Exception ex){
                System.out.println( "Exception" );
            }
        }
    }
public static void main( String[] args ) {
    new TTSCliente().setVisible(true);
}
}
