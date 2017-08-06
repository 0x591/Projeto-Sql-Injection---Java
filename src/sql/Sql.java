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
        
        ControladorMySql cms = new ControladorMySql();        
        ControladorMain main = new ControladorMain(true);
        
        String url = "http://testphp.vulnweb.com/listproducts.php?cat=1&ads=231";
        main.RecebeUrl(url);
        
    }
}
