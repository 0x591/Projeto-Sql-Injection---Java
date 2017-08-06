package ControladorMySql;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import sql.ControladorMain;

public class PrincipalMySql {

    ArrayList<String> urlTeste = new ArrayList<>(); // ArrayList que salva as urls temporariamente
    private boolean ativo = true; // Variavel do monitoramento  True = Ativo / False = Desativado
    ErrorBased mySql; // Instaciando a classe detectError    
    UnionBased union;

    // Método construtor que iniciar o monitoramento do ArrayList
    public PrincipalMySql() {
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
    private static boolean Encontrei;

    private static HttpClient client() {
        return new DefaultHttpClient();
    }
    private static HttpResponse response;

    public static void enviaGet(String urlGet) {
        try {
            HttpGet request = new HttpGet(urlGet);

            response = client().execute(request);
            HttpEntity entity = response.getEntity();

            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));

            String line = "";
            Encontrei = false;
            while ((line = rd.readLine()) != null) {
                if (Encontrei != true) {
                    if (verifica(line)) {
                        System.err.println("->  " + " " + response.getStatusLine() + " Vulneravel - MySqlUnion \n" + urlGet);
                        Encontrei = true;
                    }
                }
            }
            EntityUtils.consume(entity);
        } catch (Exception e) {
            Logger.getLogger(UnionBased.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    private static String payload;

    private static boolean verifica(String recebeCodigoFonte) {
        String content = recebeCodigoFonte;
        boolean isMatch = false;
        int qntArquivo = 1;
        try {
            BufferedReader a = new BufferedReader(new FileReader("arquivo" + qntArquivo + ".txt"));
            while (a.ready()) {
                String pattern = ".*" + a.readLine() + ".*";

                isMatch = Pattern.matches(pattern, content);
                if (isMatch) {
                    payload = pattern;
                    //System.out.println(linha);
                    return isMatch;
                }
            }
            a.close();
        } catch (Exception e) {
            Logger.getLogger(UnionBased.class.getName()).log(Level.SEVERE, null, e);
        }
        return isMatch;
    }

}
