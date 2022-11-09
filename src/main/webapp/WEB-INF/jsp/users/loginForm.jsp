<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  


<layout pageName="home">
    <div class="inicio">
        <html  lang="en">
            <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE-edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Login Page</title>
            
            
            <style>

                
            
            * {

                box-sizing: border-box;

            }

            body {

            
                padding: 0;
                margin: 0;
                font-family: "Inter", sans-serif;
                background: linear-gradient(45deg, #131086, #b621f3);
                padding: 40px;

            }

            #container {

                min-height: calc(100vh - 40px * 2);
                display: grid;
                grid-template-columns: 50% 50%;
                border-radius: 10px;
                overflow: hidden;

            }

            #login-left {

                display: flex;
                flex-direction: column;
                justify-content: center;
                padding-left: 150px;
                background-color: white;

            }

            #login-right {

                background-color: #eee;
                display: flex;
                justify-content: center;

                

            }

            #login-right #img { 

                width: 80%;

            }

            #login-header {

                margin-bottom: 50px;
            }

            #login-header #h1 {

                font-size: 40px;
                margin-bottom: 10px;

            }
            
            #login-header #p {

                opacity: .7;
        
            }
            
            
            #login-form {

                width: 450px;

            }

            #login-form #content {

                display: flex;
                flex-direction: column;
                gap: 35px;

            }

            #login-form #footer {

                display: flex;
                gap: 30px;
                margin-top: 70px;

            }
            
            #login-form #footer #a {

                flex: 6;
                gap: 15px;
                border: 1px solid black;
                border-radius: 100px;
                padding: .6rem;
                justify-content: center;
                display: flex;
                color: black;
                text-decoration: none;


            }

            #login-form #footer #a #hoover {

                background-color: #eee;


            }

            #form-item label:not(.checkboxLabel) { 

                display: inline-block;
                background-color: white;
                margin-bottom: absolute;
                padding: 0 10px;
                transform: translate(30px, -10px);
                font-size: 14px;

            }

            input[type='text'],
            input[type='password'] {

                border: 1px solid black;
                height: 55px;
                padding: 0 2rem;
                width: 100%;
                outline: none;
                transition: background .5s;
                font-size: 18px;
                border-radius: 100px;

            }

            #checkbox {

                display: flex;
                align-items: center;
                gap: 7px;

            }

            input[type='checkbox'] {

                width: 20px;
                height: 20px;
                accent-color: #131086;

            }

            #button {

                border: none;
                background: linear-gradient(45deg, #131086, #b621f3);
                color: white;
                padding: 1rem;
                border-radius: 100px;
                text-align: center;
                text-transform: uppercase;
                font-size: 18px;
                height: 55px;
                cursor: pointer;

            }

            /* Responsive */

            @media (max-width:1350px) {

                #login-left {
                    padding: 50px !important;
                }

                #login-form {

                    width: 100%;
                }

                #login-form #footer {

                    flex-direction: column;
                    gap: 15px;

                }

            }

            @media (max-width:768px) {

                #body {
                    padding: 20px;
                }

                #container {
                    grid-template-columns: auto;

                }

                #login-right {
                    display: none;
                }

            }
                
               
            </style>   
            </head>
            <body>

                <div class="container">
                    <div class="login-left">
                        <div class="login-header">
                            <h1>Welcome to Our Application</h1>
                            <p>Please login ti use the platform</p>
                        </div>
                        <form class="login-form">
                            <div class="login-form-content">
                                <div class="form-item">
                                    <label for="email">Enter Email</label>
                                    <input type="text" id="email">
                                </div>
                                <div class="form-item">
                                    <label for="password">Enter Password</label>
                                    <input type="password" id="password">
                                </div>
                                <div class="form-item">
                                    <div class="checkbox">
                                        <input type="checkbox" id="rememberMeCheckbox" checked>
                                        <label for="rememberMeCheckbox" class="checkboxLabel">Remember me</label>
                                    </div>
                                </div>
                                <button type="submit">Sign In</button>
                            </div>
                            <div class="login-form-footer">
                                <a href="#">
                                    <img width="30" src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/51/Facebook_f_logo_%282019%29.svg/2048px-Facebook_f_logo_%282019%29.svg.png"
                                    alt=""> Facebook Login
                                </a>
                                <a href="#">
                                    <img width="30" src="https://rotulosmatesanz.com/wp-content/uploads/2017/09/2000px-Google_G_Logo.svg_.png" 
                                    alt=""> Google Login
                                </a>
                            </div>
                        </form>
                    </div>
                    <div class="login-right">
                        <img width="30" src="https://upload.wikimedia.org/wikipedia/commons/thumb/e/e1/Google_Chrome_icon_%28February_2022%29.svg/2048px-Google_Chrome_icon_%28February_2022%29.svg.png" alt="">
                    </div>

                </div>

            </body>
            
            
        </html>
      </div>

</layout>
