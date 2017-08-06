
package validarDados;

import java.io.IOException;
import static sql.ControladorMain.adicionaUrl;

public class ValidarParametros {
    // Método que recebe a url
    public ValidarParametros(String url) throws IOException {
        parseUrl(url);
    }
    // Método que separa os parametros
    public void parseUrl(String urlRecebe) throws IOException{
        String url = urlRecebe; // Salvando url na variavel
        char simbolo = '&'; // Simbolo que vai ser procurado
        StringBuffer url2 = null; 
        for (int i = 0; i < url.length(); i++) {            
            url2 = new StringBuffer(url); // StringBuffer com a url
            if (url.charAt(i) == simbolo) { // Verificando se tem o simbolo 
                url2.replace(i, i, "!-!"); // Inserindo !-! na url 
                adicionaUrl(url2.toString()); // Enviando a url para o teste
            }
            if (i == url.length()-1){ // Verificando se o index chego no final
                url2.replace(i+1, i+1, "!-!"); // Inserindo !-! no final da url
                adicionaUrl(url2.toString()); // Enviando a url para o teste
            }            
        }
    }
}
