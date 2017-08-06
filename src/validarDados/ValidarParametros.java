
package validarDados;

import java.io.IOException;
import sql.ControladorMain;
import static sql.ControladorMain.RecebeUrl;
import static sql.ControladorMain.adicionaUrl;

public class ValidarParametros {
    
    public ValidarParametros(String url) throws IOException {
        parseUrl(url);
    }
    
    public void parseUrl(String urlRecebe) throws IOException{
        String url = urlRecebe;
        char c = '&';
        StringBuffer url2 = null;
        for (int i = 0; i < url.length(); i++) {            
            url2 = new StringBuffer(url);
            if (url.charAt(i) == c) {
                int a = url.charAt(i);
                url2.replace(i, i, "!-!");
                adicionaUrl(url2.toString());                
            }
            if (i == url.length()-1){
                url2.replace(i+1, i+1, "!-!");
                adicionaUrl(url2.toString());           
            }            
        }
    }
}
