package Controller;

import io.javalin.http.Handler;

public class StaticHTMLController {


    public static Handler loadLandingPage = ctx -> {
        ctx.html("<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <head>\n" +
                "        <link rel=\"stylesheet\" href=\"/basics.css\"/>\n" +
                "        <meta charset =\"UTF-8\"/>\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <header>\n" +
                "            \n" +
                "            <div id = \"data\"></div>\n" +
                "          <div id = \"TITLES\">  \n" +
                "             <h1 style=\"font-family: 'Times New Roman', Times, serif;\">\n" +
                "            Super Weenie Hut Srs\n" +
                "            </h1>         \n" +
                "            <h2>\n" +
                "            Reimbursement Requests\n" +
                "            </h2>\n" +
                "                <h3>\n" +
                "                    My favorite dog breed.\n" +
                "                    <p>My favorite dog breed is the <span id = \"doggo\"></span></p>\n" +
                "\n" +
                "                </h3>\n" +
                "\n" +
                "        </div>\n" +
                "        <div id=\"SWHJDIV\" class=\"image_divs\">\n" +
                "\n" +
                "        </div>\n" +
                "        </header>\n" +
                "            <nav>\n" +
                "                <a href=\"https://www.youtube.com/watch?v=QnD6B8Ck0T8\">Must be this tough to enter</a>\n" +
                "            </nav>\n" +
                "            <div class = \"credentials\">\n" +
                "                <form id = \"form\">\n" +
                "                    <label for = \"username\">Username</label><br>\n" +
                "                    <input type = \"text\" id = \"username\" name = \"username\"><br>\n" +
                "                    <label for = \"password\">Password</label><br>\n" +
                "                    <input type= \"password\" id = \"password\" name = \"password\"><br>\n" +
                "                   <br>\n" +
                "                    <input type = \"button\" id = \"finish\" value = \"Login\">\n" +
                "                </form>\n" +
                "            </div>\n" +
                "       <script src=\"/P1Scripts.js\"></script>\n" +
                "       <script src= \"/fetch.js\"></script>\n" +
                "    </body>\n" +
                "</html>");
    };
}
