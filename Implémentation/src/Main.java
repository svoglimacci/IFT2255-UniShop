import utilities.JSONHandler;
import views.AuthView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Plateforme UniShop : plateforme de commerce électronique crée dans le cadre du cours de Génie Logiciel
 * IFT2255 à l'Université de Montréal.
 *
 * @author : Simon Voglimacci   20002825
 * @author : Victor Lebblond    20244841
 * @author : Julie Yang         20239909
 * @author : Celina Zhang       20207461
 * @version : 1.0
 */


public class Main {

    public static void main(String[] args) throws IOException {

        JSONHandler jsonHandler = new JSONHandler();
        Map<String, Object> emptyObject = new HashMap<>();
        jsonHandler.writeJsonToFile(emptyObject, "src/data/users.json");
        jsonHandler.writeJsonToFile(emptyObject, "src/data/products.json");


        AuthView authView = new AuthView();
        authView.start();

    }

}