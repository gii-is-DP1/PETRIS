<!DOCTYPE html>

<html lang="es">    
        <head>
        <meta charset="utf-8"/>
        <title>Form login</title>
    <style>
* {
    margin: 0px;
    padding: 0px;
}

body{
    background: url(https://fotografias.lasexta.com/clipping/cmsimages02/2018/06/18/21F93752-00FB-4A7D-AAD3-4CCCA693F10C/98.jpg?crop=1000,563,x0,y53&width=1900&height=1069&optimize=high&format=webply);
    background-size: cover;
}

#contenedor1{
    background: #f9f7f7;
    width: 460px;
    height: 370px;
    margin: auto;
    margin-top: 100px;
}

#form1{
    width: 100%;
    padding: 50px 20px 10px 20px;
    box-sizing: border-box;
}

#contenedor1 h1{
    text-align: center;
    padding-top: 20px;
    color: #566573;
    font-size: 45px;
    font-family: Verdana;

}

#form1 input[type="text"],
#form1 input[type="password"]{
    border: none;
    background: none;
    width: 90%;
    height: 40px;
    font-size: 15px;
    font-weight: bold;
    text-align: center;
}
#form1 input[type="submit"]{
    width: 100%;
    height: 60px;
    background: #1b4f72;
    color: white;
    cursor: pointer;
    border: 2px solid white;
    font-size: 25px;
    font-weight: 900;

}

#form1 input[type="submit"]:hover{
    background: #3498db;
}

#form1 hr{
    margin-bottom: 40px;
    color: #aed6f1;
}

#contenedor2{
    background: #f9f7f7;
    width: 460px;
    height: 140px;
    margin: auto;
    margin-top: 10px;
}

#form2{
    width: 100%;
    padding: 23px 20px 0px 20px;
    box-sizing: border-box;
}

#form2 input[type="submit"]{
    width: 100%;
    height: 60px;
    background: white;
    border: 2px solid white;
    cursor: pointer;
    font-size: 25px;
    color: #1b4f72;
}

#form2 input[type="submit"]:hover{
    background: #3498db;
    color: white;
}

#referencias{
    margin-left: 110px;
    margin-top: 14px;
}

#referencias a{
    padding-left: 10px;
    text-decoration: none;
    color: #1b4f72;
}

img{
    float: left;
}

       
    </style>
    </head>
    <body>
        
        <div id="contenedor1">
            <h1>Iniciar sesion</h1>
            <form id="form1">
                <img src="https://www.avante.es/wp-content/uploads/2018/03/471a1ad342659289433e05a611d206f8.png" width="40px" height="38px" alt=""/>
                <input type="text" name="" placeholder="Usuario"/>
                <hr>
                <img src="https://www.avante.es/wp-content/uploads/2018/03/471a1ad342659289433e05a611d206f8.png"width="40px" height="38px" alt=""/>
                <input type="password" name="" placeholder="Contrase&ntilde;a">
                <hr>
                <input type="submit" value="Sign in">
            </form>
        </div>
        <div id="contenedor2">
            <form id="form2">
                <input type="submit" value="Create Account"/>
            </form>
            <div id="referencias">
                <a>Daniel</a>
                <a href="">Term of Use</a>
                <a href="">Privace Policy</a>
            </div>
        </div>
    </body>
</html>