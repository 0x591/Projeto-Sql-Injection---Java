package ControladorMySql;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class ErrorBased {
    
    private String url; // Variavel que recebe as url temporariamente

    // Método construtor padrão
    public ErrorBased() {
    }

    // Método construtor que recebe Url
    public ErrorBased(String url) {
        setUrl(url); // Envia a url para o metodo setUrl
        new Thread(iniciaTeste).start(); // Inicia uma Thread com o método iniciaTeste
    }

    // Método getter 
    private String getUrl() {
        return url; // retorna a variavel
    }

    // Método setter 
    private void setUrl(String url) {
        this.url = url; // Setando a url na variavel
    }
    // Método que inicia o teste
    private Runnable iniciaTeste = new Runnable() {
        @Override
        public void run() {
            get(getUrl()); // Enviando a url para o metodo get
        }
    };
    
    private HttpClient client() {
        return new DefaultHttpClient();
    }
    HttpResponse response;
    private boolean Encontrei;

    // Método que obtem o codigo fonte da url
    private void get(String recebeUrl) { // Recebe como parametro a Url 
        try {
            HttpGet request = new HttpGet(recebeUrl.replaceAll("=", "='")); // Monta o get com a url eo '
            response = client().execute(request); // Executando o get
            HttpEntity entity = response.getEntity();
            
            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));
            
            String line = "";
            Encontrei = false;
            while ((line = rd.readLine()) != null) {
                if (Encontrei != true) {
                    if (verifica(line)) {
                        System.err.println("->  " + recebeUrl + " " + response.getStatusLine() + " Vulneravel - MySql - Regex:" + payload);
                        Encontrei = true;
                    }
                }
            }
            EntityUtils.consume(entity);
        } catch (Exception e) {
        }
    }
    
    String payload;
    
    private boolean verifica(String recebeCodigoFonte) {
        String content = recebeCodigoFonte;
        boolean isMatch = false;
        int qntArquivo = 2;
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
            //System.out.println(e);
        }
        return isMatch;
    }
}
