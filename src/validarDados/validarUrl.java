package validarDados;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import sql.ControladorMain;
import static sql.ControladorMain.adicionaUrl;

public class validarUrl {

    public validarUrl(String url) throws IOException {
        this.url = url;
        validaRespose();
    }
    private String url;

    private HttpClient client() {
        return new DefaultHttpClient();
    }
    HttpResponse response;
    ValidarParametros parametros;
    //ControladorMain controladorMain = new ControladorMain();
    public void validaRespose() throws IOException {
        HttpEntity entity = null;
        try {
            //System.out.println("Teste- " + url);
            HttpGet request = new HttpGet(validaLink(this.url)); // Monta o get com a url eo '
            response = client().execute(request); // Executando o get
            entity = response.getEntity();
            String responseCode = response.getStatusLine().toString();
            if (responseCode.contains("200")) {
                EntityUtils.consume(entity);
                if (url.contains("&")){
                   // System.out.println("Entro");
                    parametros = new ValidarParametros(url);                    
                }else{
                    adicionaUrl(url);
                }
            }
        } catch (Exception e) {
            Logger.getLogger(ControladorMain.class.getName()).log(Level.SEVERE, null, e);
        }
        EntityUtils.consume(entity);
        
    }

    private String validaLink(String url) {
        String urlValidada = url;
        if (urlValidada.contains("http") && urlValidada.contains("=")) {
            return urlValidada;
        }
        return null;
    }
}
