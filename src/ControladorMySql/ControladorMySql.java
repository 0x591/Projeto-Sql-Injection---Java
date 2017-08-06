package ControladorMySql;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sql.ControladorMain;

public class ControladorMySql {

    ArrayList<String> urlTeste = new ArrayList<>(); // ArrayList que salva as urls temporariamente
    private boolean ativo = true; // Variavel do monitoramento  True = Ativo / False = Desativado
    ErrorBased mySql; // Instaciando a classe detectError    
    UnionBased union;

    // Método construtor que iniciar o monitoramento do ArrayList
    public ControladorMySql() {
        new Thread(monitora).start(); // Inicia uma Thread com o método monitora
    }

    // Método que recebe as Url
    public void RecebeUrl(String urlR) {
        urlTeste.add(urlR); // Salva a url na ArrayList = urlTeste
    }

    // Método que monitora a ArrayList
    private Runnable monitora = new Runnable() {
        @Override
        public void run() {
            while (ativo) { // Se for true ele vai ficar sempre verificando a ArrayList
                try {
                    if (urlTeste.size() > 0) { // Verifica se a ArrayList é maior que Zero
                        for (int i = 0; i < urlTeste.size(); i++) { // Entrando em loop se i for menor que o tamanho do ArrayList         

                            mySql = new ErrorBased(urlTeste.get(i)); // Enviando a Url para a Classe detectError
                            union = new UnionBased(urlTeste.get(i));

                            urlTeste.remove(i); // Removendo a url do ArrayList
                            Thread.sleep(1000); // Espera 2 segundo pra enviar a proxima url                            
                        }
                    }
                    Thread.sleep(1000); // Espera 5 segundos pra verificar o ArrayList novamente
                } catch (InterruptedException ex) {
                    Logger.getLogger(ControladorMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    };

    private Runnable detectaErro = new Runnable() {
        @Override
        public void run() {

        }
    };
    private Runnable detectaUnion = new Runnable() {
        @Override
        public void run() {

        }
    };
}
