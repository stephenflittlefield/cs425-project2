<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

    <head>

        <title>Log In</title>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8">

    </head>

    <body>

        <form id="loginform" name="loginform" method="POST" action="j_security_check" accept-charset="UTF-8">

            <!-- Insert your form elements here -->

        </form>

        <b>How to Log In</b>

        <ul>
            <li>Enter the Username and Password that you were provided.</li>                    
        </ul>

        <%

            String result = request.getParameter("error");

            if (result != null) {

        %>

        <div id="loginerror" style="color: red;">

            <b>There was a problem processing your login request.</b>

            <ul>
                <li>Please try entering your Username and Password again.</li>
            </ul>

        </div>

        <%

            }

        %>
        
        <script type="text/javascript">

        </script>

    </body>

</html>