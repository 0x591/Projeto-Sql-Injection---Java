package sql;

import ControladorMySql.ControladorMySql;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import validarDados.validarUrl;

public class ControladorMain {

    public ControladorMain() {
    }

    public ControladorMain(boolean ativoR) {
        ativo = ativoR;
        new Thread(monitora).start();
    }
    static ArrayList<String> urls = new ArrayList<>();
    boolean ativo = true;
    static validarUrl validarUrl;

    public static void adicionaUrl(String url){        
        urls.add(url);
        System.out.println(url + " " + urls.size());
    }
    // Método que recebe as Url
    public static void RecebeUrl(String urlR) throws IOException {
        validarUrl = new validarUrl(urlR);        
    }
        
    ControladorMySql mySql = new ControladorMySql();
    private Runnable monitora = new Runnable() {
        @Override
        public void run() {
            //System.out.println("Começo");
            while (ativo) { // Se for true ele vai ficar sempre verificando a ArrayList
                //System.out.println("while");
                try {
                    //System.out.println(urls.size());
                    if (urls.size() > 0) { // Verifica se a ArrayList é maior que Zero
                        for (int i = 0; i < urls.size(); i++) { // Entrando em loop se i for menor que o tamanho do ArrayList                      
                            if (urls.get(i) != null) {
                                // System.out.println(urls.get(i));
                                mySql.RecebeUrl(urls.get(i)); // Enviando a Url para a Classe ControllerMySql
                                urls.remove(i); // Removendo a url do ArrayList
                            } else {
                                urls.remove(i); // Removendo a url do ArrayList
                            }
                        }
                    }
                    Thread.sleep(5000); // Espera 5 segundos pra verificar o ArrayList novamente
                } catch (Exception ex) {
                    Logger.getLogger(ControladorMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    };
}
