package edu.escuelaing.arep;

import static spark.Spark.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.print.attribute.standard.Media;

import spark.Request;
import spark.Response;

public class SparkWebApp {

    public static void main(String[] args) {
        port(getPort());
        get("/inputdata", (req, res) -> inputDataPage(req, res));
        get("/results", (req, res) -> resultsPage(req, res));
    }

    private static String inputDataPage(Request req, Response res) {
        String pageContent = "<!DOCTYPE html>" + "<html>" + "<body>" + "<h2>AWS SQUARE NUMBER</h2>"
                + "<form action=\"/results\">" + "  please insert the Number: :<br>"
                + "  <input type=\"text\" name=\"Numero\" value=\"\">" + "  <br>"
                + "  <input type=\"submit\" value=\"Calculate\">" + "</form>"
                + "<p> select the  \"Calculate\" button to display the results in the page  \"/results\".</p>"
                + "</body>" + "</html>";
        return pageContent;
    }

    private static String resultsPage(Request req, Response res) throws Exception {
        String value = req.queryParams("Numero");
        URL url = new URL("https://vwz0ynhjcj.execute-api.us-east-1.amazonaws.com/beta?value=" + value);
        String inputline = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String result=reader.readLine();
        String pageContent = "<!DOCTYPE html>" + "<html>" + "<body >" + "<h2>"+result+"</h2>" + "<script>" + "</script>"
                + "</body>" + "</html>";
        return pageContent;

    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
        // returns default port if heroku-port isn't set (i.e. on localhost)

    }
}