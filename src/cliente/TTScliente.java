package cliente;

import server.TTS;

import javax.swing.*;
import java.awt.event.ActionEvent;
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
        this.add(painel);
        this.setSize(300,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.btn = new JButton("Falar");
        this.entrada = new JTextField();
        painel.add(entrada);
        painel.add(btn);
        btn.addActionListener(new BotaoListener());
    }

    private class BotaoListener implements java.awt.event.ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                TTS tts = (TTS) Naming.lookup("//localhost/TTSService");
                tts.speak(entrada.getText());
            }
            catch ( MalformedURLException murle ) {
                System.out.println( );
                System.out.println( "MalformedURLException" );
                System.out.println( murle );
            }catch(RemoteException ex){
                System.out.println( "RemoteException" );
            }catch(NotBoundException nbe){
                System.out.println( "NotBoundException" );
            }
        }
    }
public static void main( String[] args ) {
    new TTSCliente().setVisible(true);
}
}
